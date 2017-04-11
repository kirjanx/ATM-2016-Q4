package steps;

import business_object.user.Letter.Letter;
import business_object.user.User;
import business_object.user.UserFactory;
import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_object.ComposeMailPage;
import page_object.DraftPage;
import page_object.HomePage;
import page_object.InboxPage;
import setup.Browser;
import setup.WebDriverTypes;

import java.util.List;

public class MailBoxExtendedFeatureSteps {

    private WebDriver driver = Browser.getWrappedDriver(WebDriverTypes.FIREFOX);
    private User user;
    private Letter letter;
    private static Logger logger = LogManager.getRootLogger();

    /**
     * Scenario: Delete draft email by context menu
     */
    @Given("^I create new email and save as draft$")
    public void iCreateNewEMail(List<Letter> letters) {
        user = UserFactory.createDefaultUser();
        new HomePage(driver).enterValidCredentialsForLogin(user).clickLoginButton();
        logger.info("Logged in");

        new ComposeMailPage(driver).open().createEmail(letter = letters.get(0))
                .clickSaveButton();
        logger.info("Mail #1 was created and saved as draft");

        new ComposeMailPage(driver).open().createEmail(letter = letters.get(1))
                .clickSaveButton();
        logger.info("Mail #2 was created and saved as draft");
    }

    @And("^I delete email by using context menu$")
    public void iDeleteEmailByContextMenu() {
        new DraftPage(driver).openDraftFolder().useContextMenu().deleteLetterUsingContextMenu();
        logger.info("Letter was deleted by context menu");
    }

    @And("^I delete email by drag and drop$")
    public void iDeleteEmailByDragAndDrop() {
        new DraftPage(driver).openDraftFolder().dragAndDropMail();
        logger.info("Letter was deleted by drag and drop");
    }

    @Then("^I can't find deleted emails in the Draft folder$")
    public void iCanNotFindEmailsInDraft() {
        Assert.assertFalse(new DraftPage(driver).isDraftFolderHasLetter());
        logger.info("Draft folder is empty");
    }

    @After("@logOut")
    public void clearDraftFolder() {
        new InboxPage(driver).performLogout();
        logger.info("Logged out");
    }
}