package test;

import business_object.Letter;
import business_object.User;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_object.*;
import setup.Browser;

import java.util.ArrayList;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.CombinableMatcher.both;
import static org.junit.Assert.assertThat;

public class MailRuTest {

    private static final String BASE_URL = "https://mail.ru/";

    private WebDriver driver;
    private ComposeMailPage composeMailPage;
    private DraftPage draftPage;
    private HomePage homePage;
    private InboxPage inboxPage;
    private SentPage sentPage;
    private Letter letter;

    @BeforeClass(description = "Start browser and initialize pages")
    public void setUpBefore() {
        driver = Browser.getBrowser("firefox");
        driver.get(BASE_URL);
        composeMailPage = new ComposeMailPage(driver);
        draftPage = new DraftPage(driver);
        homePage = new HomePage(driver);
        inboxPage = new InboxPage(driver);
        sentPage = new SentPage(driver);
        letter = new Letter();
    }

    @Test(description = "Login to Mail.ru mailbox")
    public void loginMailBox() {
        new HomePage(driver).loginToMailBox(new User());
        boolean loginIsComplete = inboxPage.isUserMailAfterLoginIsDisplayed();
        Assert.assertTrue(loginIsComplete, "Yor are NOT logged in");
        System.out.println("Login was completed successfully");
    }

    @Test(description = "Create new Email and save as Draft", dependsOnMethods = "loginMailBox")
    public void createMailAndSaveAsDraft() {
        composeMailPage.createMailAndSaveDraft(new Letter()).openDraftPage();
        Assert.assertEquals(draftPage.getMailAddressFromDraftPage(), letter.getEmailAddress(),
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

    @Test(description = "Perform logout", dependsOnMethods = "sendMailFromDraft")
    public void clearMailAndLogout() {

        sentPage.clearSentMail().logout();

        boolean logoutIsComplete = homePage.isLogoutSuccessful();
        Assert.assertTrue(logoutIsComplete, "Logout wasn't completed correctly");
        System.out.println("Logout was completed correctly");
    }

    @Test(description = "Open draft mail and verify data in all fields", dependsOnMethods = "clearMailAndLogout")
    public void openMailFromDraft() {
        homePage.loginToMailBox(new User());
        composeMailPage.createMailAndSaveDraft(new Letter()).openDraftPage();

        ArrayList email = new ArrayList();
        email.add(letter.getEmailAddress());
        email.add(letter.getEmailSubject());
        email.add(letter.getEmailBodyText());

        draftPage.openMailFromDraft();
        assertThat(String.valueOf(email), both(containsString(composeMailPage.getAddressFromMail())).
                and(containsString(composeMailPage.getSubjectFromMail())).
                and(containsString(composeMailPage.getBodyFromMail())));
        draftPage.openDraftPage().clearDraftMail().logout();
    }

    @Test(description = "Delete draft email by context menu", dependsOnMethods = "openMailFromDraft")
    public void deleteDraftByContextMenu() {
        homePage.loginToMailBox(new User());
        composeMailPage.createMailAndSaveDraft(new Letter()).
                openDraftPage().openContextMenu().deleteLetterByContextMenu();

        Assert.assertTrue(!draftPage.isDraftFolderHasLetter());
        System.out.println("Draft folder is empty");
        inboxPage.logout();
    }

    @Test(description = "Delete draft email by drag and drop the letter to the trash"/*,
            dependsOnMethods = "deleteDraftByContextMenu"*/)
    public void deleteDraftUsingMouse() {
        homePage.loginToMailBox(new User());
        composeMailPage.createMailAndSaveDraft(new Letter()).openDraftPage().dragAndDropDraftMail();

        Assert.assertTrue(!draftPage.isDraftFolderHasLetter());
        System.out.println("Draft folder is empty");
        inboxPage.logout();
    }

    @AfterClass(description = "Close browser")
    public void tearDown() {
        driver.quit();
    }
}