package test;

import business_object.letter.Letter;
import business_object.letter.LetterBuilder;
import business_object.letter.LetterData;
import business_object.user.User;
import business_object.user.UserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_object.*;
import setup.Browser;
import setup.WebDriverTypes;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.junit.Assert.assertThat;

public class MailRuCriticalPathTest {

    private static final String RECIPIENT_LOGIN = "someEmail";
    private static final String RECIPIENT_PASSWORD = "123456";
    private static final String BASE_URL = "https://mail.ru/";

    private WebDriver driver;
    private ComposeMailPage composeMailPage;
    private DraftPage draftPage;
    private HomePage homePage;
    private InboxPage inboxPage;
    private SentPage sentPage;
    private Letter letter;
    private User sender;
    private User recipient;

    @BeforeClass(description = "Start browser and initialize pages")
    public void setUpBefore() {
        driver = Browser.getWrappedDriver(WebDriverTypes.FIREFOX);
        driver.get(BASE_URL);
        composeMailPage = new ComposeMailPage(driver);
        draftPage = new DraftPage(driver);
        homePage = new HomePage(driver);
        inboxPage = new InboxPage(driver);
        sentPage = new SentPage(driver);
        letter = new LetterBuilder()
                .address(LetterData.ADDRESS)
                .subject(LetterData.SUBJECT)
                .bodyText(LetterData.BODY)
                .build();
        sender = UserFactory.createDefaultUser();
        recipient = UserFactory.createUserWithLoginAndPassword(RECIPIENT_LOGIN, RECIPIENT_PASSWORD);
    }

    @Test(description = "Login to Mail.ru mailbox")
    public void loginMailBox() {
        new HomePage(driver).loginToMailBox(sender);
        boolean loginIsComplete = inboxPage.isUserMailAfterLoginDisplayed();
        Assert.assertTrue(loginIsComplete, "Yor are NOT logged in");
        System.out.println("Login was completed successfully");
    }

    @Test(description = "Create new Email and save as Draft", dependsOnMethods = "loginMailBox")
    public void createMailAndSaveAsDraft() {
        composeMailPage.createMailAndSaveDraft(letter).openDraftPage();
        Assert.assertEquals(draftPage.getMailAddressFromDraftPage(), letter.getAddress(),
                "Created Email isn't presented in the Draft folder");

        System.out.println("Email was created and saved in Draft folder");
    }

    @Test(description = "Send mail and verify Draft and Sent folders", dependsOnMethods = "createMailAndSaveAsDraft")
    public void sendMailFromDraft() {
        boolean isDraftFolderEmpty = !draftPage.openMailFromDraft().sendMail().openDraftPage().isDraftFolderHasLetter();
        boolean isSentFolderHasLetter = draftPage.openSentPage().isSentFolderHasLetter();

        assertThat(true, both(is(isSentFolderHasLetter)).and(is(isDraftFolderEmpty)));
        System.out.println("Mail was sent successfully");
    }

    @Test(description = "Perform performLogout", dependsOnMethods = "sendMailFromDraft")
    public void clearMailAndLogout() {
        sentPage.clearSentMail().logout();

        boolean logoutIsComplete = homePage.isLogoutSuccessful();
        Assert.assertTrue(logoutIsComplete, "Logout wasn't completed correctly");
        System.out.println("Logout was completed correctly");
    }

    @Test(description = "Create mail, save as draft, open draft mail and verify data in all fields",
            dependsOnMethods = "clearMailAndLogout")
    public void openMailFromDraft() {
        homePage.loginToMailBox(sender);
        composeMailPage.createMailAndSaveDraft(letter).openDraftPage();

        ArrayList email = new ArrayList();
        email.add(letter.getAddress());
        email.add(letter.getSubject());
        email.add(letter.getBodyText());

        draftPage.openMailFromDraft();
        assertThat(String.valueOf(email), both(containsString(composeMailPage.getAddressFromMail())).
                and(containsString(composeMailPage.getSubjectFromMail())).
                and(containsString(composeMailPage.getBodyFromMail())));

        draftPage.openDraftPage().clearDraftMail().logout();
    }

    @AfterClass(description = "Close browser")
    public void tearDown() {
        driver.quit();
    }
}