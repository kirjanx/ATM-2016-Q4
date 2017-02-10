package test;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.*;
import setup.Browser;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.junit.Assert.assertThat;

public class MailRuTest {

    private static final String LOGIN = "tester.atm";
    private static final String PASSWORD = "q123456";
    private static final String EMAIL_ADDRESS = "someEmail@yandex.ru";
    private static final String EMAIL_SUBJECT = "Test Subject";
    private static final String BODY_TEXT = "BodyTextTest";
    private static final String BASE_URL = "https://mail.ru/";

    private WebDriver driver;
    private ComposeMailPage composeMailPage;
    private DraftPage draftPage;
    private HomePage homePage;
    private InboxPage inboxPage;
    private SentPage sentPage;

    @BeforeClass(description = "Start browser and initialize pages")
    public void setUpBefore(){
        driver = Browser.getBrowser();
        driver.get(BASE_URL);
        composeMailPage = new ComposeMailPage(driver);
        draftPage = new DraftPage(driver);
        homePage = new HomePage(driver);
        inboxPage = new InboxPage(driver);
        sentPage = new SentPage(driver);
    }

    @Test(description = "Login to Mail.ru mailbox")
    public void loginMailBox(){
        new HomePage(driver).loginMailBox(LOGIN, PASSWORD);
        boolean loginIsComplete = inboxPage.isUserMailAfterLoginIsDisplayed();
        Assert.assertTrue(loginIsComplete, "Yor are NOT logged in");
        System.out.println("Login was completed successfully");
    }

    @Test(description = "Create new Email and save as Draft", dependsOnMethods = "loginMailBox")
    public void createMailAndSaveAsDraft(){
        composeMailPage.createMailAndSaveDraft(EMAIL_ADDRESS, EMAIL_SUBJECT, BODY_TEXT).openDraftPage();
        Assert.assertEquals(draftPage.getMailAddressFromDraftPage(), EMAIL_ADDRESS,
                "Created Email isn't presented in the Draft folder");
        System.out.println("Email was created and saved in Draft folder");
    }

    @Test(description = "Send mail and verify Draft and Sent folders", dependsOnMethods = "createMailAndSaveAsDraft")
       public void sendMailFromDraft(){
       boolean isDraftEmpty = draftPage.openMailFromDraft().sendMail().openDraftPage().isDraftEmpty();
       boolean isNotSentEmpty = draftPage.openSentPage().isNotSentEmpty();
       boolean customTrue = true;

       assertThat(customTrue, both(is(isNotSentEmpty)).and(is(isDraftEmpty)));
       System.out.println("Mail was sent successfully");
    }

    @Test(description = "Perform logout", dependsOnMethods = "sendMailFromDraft")
    public void clearMailAndLogout(){

           //sentPage.clearSentMail().clearDraftMail().logout().isLogoutSuccessful();

        sentPage.clearSentMail().logout().isLogoutSuccessful();

        boolean logoutIsComplete = homePage.isLogoutSuccessful();
        Assert.assertTrue(logoutIsComplete, "Logout wasn't completed correctly");
        System.out.println("Logout was completed correctly");
    }

    @Test(description = "Open draft mail and verify data in all fields", dependsOnMethods = "clearMailAndLogout")
    public void openMailFromDraft(){
        homePage.loginMailBox(LOGIN, PASSWORD);
        composeMailPage.createMailAndSaveDraft(EMAIL_ADDRESS, EMAIL_SUBJECT, BODY_TEXT).openDraftPage();

        ArrayList email = new ArrayList();
        email.add(EMAIL_ADDRESS);
        email.add(EMAIL_SUBJECT);
        email.add(BODY_TEXT);

        draftPage.openMailFromDraft();
        assertThat(String.valueOf(email), both(containsString(composeMailPage.getAddressFromMail())).
                and(containsString(composeMailPage.getSubjectFromMail())).
                and(containsString(composeMailPage.getBodyFromMail())));
        composeMailPage.openDraftPage().clearDraftMail().logout();
    }

    @AfterClass(description = "Close browser")
    public void tearDown(){
        driver.quit();
    }
}