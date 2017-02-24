package test;

import business_object.letter.Letter;
import business_object.user.User;
import business_object.user.UserFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page_object.*;
import setup.Browser;
import setup.WebDriverTypes;

public class MailRuExtendedTest {

    private static final String BASE_URL = "https://mail.ru/";

    private WebDriver driver;
    private ComposeMailPage composeMailPage;
    private DraftPage draftPage;
    private HomePage homePage;
    private InboxPage inboxPage;
    private Letter letter;
    private User sender;

    @BeforeClass(description = "Start browser and initialize pages")
    public void setUpBefore() {
        driver = Browser.getWrappedDriver(WebDriverTypes.FIREFOX);
        driver.get(BASE_URL);
        composeMailPage = new ComposeMailPage(driver);
        draftPage = new DraftPage(driver);
        homePage = new HomePage(driver);
        inboxPage = new InboxPage(driver);
        letter = new Letter();
        sender = UserFactory.createDefaultUser();
    }

    @Test(description = "Delete draft email by context menu")
    public void deleteDraftByContextMenu() {
        homePage.loginToMailBox(sender);
        composeMailPage.createMailAndSaveDraft(letter).
                openDraftPage().openContextMenu().deleteLetterByContextMenu();

        Assert.assertTrue(!draftPage.isDraftFolderHasLetter());
        System.out.println("Draft folder is empty");
    }

    @Test(description = "Delete draft email by drag and drop the letter to the trash",
            dependsOnMethods = "deleteDraftByContextMenu")
    public void deleteDraftUsingMouse() {
        homePage.loginToMailBox(sender);
        composeMailPage.createMailAndSaveDraft(letter).openDraftPage().dragAndDropDraftMail();

        Assert.assertTrue(!draftPage.isDraftFolderHasLetter());
        System.out.println("Draft folder is empty");
    }

    @AfterMethod
    public void performLogout() {
        inboxPage.logout();
    }

    @AfterClass(description = "Close browser")
    public void tearDown() {
        driver.quit();
    }
}
