package steps;

import business_object.user.User;
import business_object.user.UserFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_object.ComposeMailPage;
import page_object.DraftPage;
import page_object.HomePage;
import page_object.InboxPage;
import setup.Browser;
import setup.WebDriverTypes;

public class MailBoxExtendedFeatureSteps {

    private WebDriver driver = Browser.getWrappedDriver(WebDriverTypes.FIREFOX);
    private User user;

//Scenario: Delete draft email by context menu
    @Given("^I enter MailBox$")
    public void iEnterMailBox() {
        user = UserFactory.createDefaultUser();
        new HomePage(driver).enterValidCredentialsForLogin(user).clickLoginButton();
        System.out.println("Login is successful");
    }

    @And("^I create new letter with address as \"([^\"]*)\", subject as \"([^\"]*)\" and body as \"([^\"]*)\"$")
    public void iCreateNewLetter(String address, String subject, String body) {
        new ComposeMailPage(driver).clickCreateMailButton().fillInAddressSubjectAndPassword(address, subject, body);
    }

    @And("^I save letter as draft$")
    public void iSaveLetterAsDraft() {
        new ComposeMailPage(driver).clickSaveButton();
        System.out.println("Letter is saved");
    }

    @When("^I open draft folder$")
    public void iOpenDraftFolder() {
        new DraftPage(driver).openDraftFolder();
        System.out.println("Draft folder is opened");
    }

    @Then("^I can delete letter by using context menu$")
    public void iCanDeleteLetterByContextMenu() {
        new DraftPage(driver).useContextMenu().deleteLetterUsingContextMenu();
        Assert.assertFalse(new DraftPage(driver).isDraftFolderHasLetter());
        System.out.println("Letter was deleted by context menu");
        new InboxPage(driver).performLogout();
        System.out.println("Logged out");
    }

//Scenario Outline: Delete draft email by drag and drop the letter to the trash
    @Given("^I open MailBox$")
    public void iOpenMailBox() {
        user = UserFactory.createDefaultUser();
        new HomePage(driver).enterValidCredentialsForLogin(user).clickLoginButton();
        System.out.println("Login is successful");
    }

    @And("^I create new mail with next address \"([^\"]*)\", subject \"([^\"]*)\" and body \"([^\"]*)\"")
    public void iCreateNewMail(String address, String subject, String body) {
        new ComposeMailPage(driver).clickCreateMailButton().fillInAddressSubjectAndPassword(address, subject, body);
    }

    @And("^I save letter as a draft$")
    public void iSaveLetterAsADraft() {
        new ComposeMailPage(driver).clickSaveButton();
        System.out.println("Letter is saved");
    }

    @When("^I go to draft folder$")
    public void iGoToDraftFolder() {
        new DraftPage(driver).openDraftFolder();
        System.out.println("Draft folder is opened");
    }

    @Then("^I can delete letter by drag and drop$")
    public void iCanDeleteLetterByDragAndDrop() {
        new DraftPage(driver).dragAndDropMail();
        Assert.assertFalse(new DraftPage(driver).isDraftFolderHasLetter());
        System.out.println("Letter was deleted by drag and drop");
        new InboxPage(driver).performLogout();
        System.out.println("Logged out");
    }
}