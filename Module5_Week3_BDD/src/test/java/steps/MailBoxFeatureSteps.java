package steps;

import business_object.user.User;
import business_object.user.UserFactory;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import page_object.*;
import setup.Browser;
import setup.WebDriverTypes;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.contains;

public class MailBoxFeatureSteps {

    private WebDriver driver = Browser.getWrappedDriver(WebDriverTypes.FIREFOX);
    private User user;

// Scenario: Login with valid username and password
    @Given("^I enter valid credentials on home page$")
    public void iEnterCredentialsOnHomePage() {
        user = UserFactory.createDefaultUser();
        new HomePage(driver).enterValidCredentialsForLogin(user);
        System.out.println("Credentials are entered");
    }

    @When("^I click login button$")
    public void iClickLoginButton() {
        new HomePage(driver).clickLoginButton();
        System.out.println("Login button is clicked");
    }

    @Then("^I should see my email address$")
    public void iShouldSeeMyEmailAddress() {
        boolean loginIsComplete = new InboxPage(driver).isUserMailAfterLoginDisplayed();
        Assert.assertTrue(loginIsComplete, "You are NOT logged in");
        System.out.println("Login is successful");
    }

//Scenario: Create new Email and save as draft
    @Given("^Situated on the Inbox page I click Create New Mail button$")
    public void iClickCreateMailButton() {
        new ComposeMailPage(driver).clickCreateMailButton();
        System.out.println("Create New Mail button is clicked");
    }

    @And("^On the opened page I fill in address as \"([^\"]*)\", subject as \"([^\"]*)\" and body as \"([^\"]*)\"")
    public void iFillInAddressSubjectAndPassword(String address, String subject, String body) {
        new ComposeMailPage(driver).fillInAddressSubjectAndPassword(address, subject, body);
        System.out.println("Address, subject and body fields are filled in");
    }

    @And("^I Click Save button$")
    public void iClickSaveButton() {
        new ComposeMailPage(driver).clickSaveButton();
        System.out.println("Save button is clicked");
    }

    @When("^I open Draft folder$")
    public void iOpenDraftFolder() {
        new DraftPage(driver).openDraftFolder();
        System.out.println("Draft folder is opened");
    }

    @Then("^I find saved letter with address as \"([^\"]*)\" in the Draft folder$")
    public void iFindSavedLetterInDraftFolder(String address) {
        Assert.assertEquals(new DraftPage(driver).getMailAddressFromDraftFolder(), address,
                "Created Email isn't presented in the Draft folder");
        System.out.println("Created Email is presented in the Draft folder");
    }

//Scenario: Send mail and verify Draft folder is empty
    @Given("^Situated in the Draft folder I open Email$")
    public void iOpenEmailFromDraftFolder() {
        new DraftPage(driver).openMailFromDraftFolder();
        System.out.println("Mail is opened from draft");
    }

    @When("^I click Send button$")
    public void iClickSendButton() {
        new ComposeMailPage(driver).clickSendButton();
        System.out.println("Send button is clicked");
    }

    @Then("^I can't find sent letter in Draft folder$")
    public void iCanNotFindSentLetterInDraftFolder() {
        Assert.assertTrue(!new DraftPage(driver).openDraftFolder()
                .isDraftFolderHasLetter(), "Draft folder has letter");
        System.out.println("Draft folder is empty");
    }

//Scenario: Verify that sent mail is presented in Sent folder
    @Given("^I situated in the Draft folder$")
    public void iSituatedInDraftFolder() {
        System.out.println("Draft folder is opened");
    }

    @When("^I open Sent folder$")
    public void iOpenSentFolder() {
        new DraftPage(driver).openSentFolder();
        System.out.println("Sent folder is opened");
    }

    @Then("^I find sent letter with address as \"([^\"]*)\" in Sent folder$")
    public void iCanNotFindSentLetterInDraftFolder(String address) {
        Assert.assertEquals(new SentPage(driver).getMailAddressFromSentFolder(),
                address, "Sent folder is empty");
        System.out.println("Sent Letter is presented in the Sent folder");
    }

//Scenario: Clear Sent folder and logout
    @Given("^I clear Sent folder$")
    public void iClearSentFolder() {
        new SentPage(driver).clearSentMailFolder();
        System.out.println("Sent folder is empty");
    }

    @When("^I perform logout$")
    public void iPerformLogout() {
        new InboxPage(driver).performLogout();
        System.out.println("Performed logout");
    }

    @Then("^I'm redirecting to the Home page$")
    public void iRedirectToHomePage() {
        boolean logoutIsComplete = new HomePage(driver).isLogoutSuccessful2();
        Assert.assertTrue(logoutIsComplete, "Logout wasn't completed");
        System.out.println("Logout is completed correctly");
    }

//Scenario Outline: Create mail, save as draft, open draft mail and verify data in all fields
    @Given("^I log in mailbox$")
    public void iLogInMailBox() {
        user = UserFactory.createDefaultUser();
        new HomePage(driver).enterValidCredentialsForLogin(user).clickLoginButton();
        System.out.println("Logged in");
    }

    @When("^I create new email with address as \"([^\"]*)\", subject as \"([^\"]*)\" and body as \"([^\"]*)\" and save it as draft$")
    public void iCreateNewEmail(String address, String subject, String body) {
        new ComposeMailPage(driver).clickCreateMailButton().fillInAddressSubjectAndPassword(address, subject, body)
                .clickSaveButton();
        System.out.println("Mail is created and saved as draft");
    }

    @Then("^I open Draft folder and find my letter with address as \"([^\"]*)\", subject as \"([^\"]*)\" and body as \"([^\"]*)\"$")
    public void iOpenDraftFolder(String address, String subject, String body) {
        new DraftPage(driver).openDraftFolder();
        System.out.println("Draft folder is opened");

        List<String> email = Arrays.asList(address,subject,body);
        assertThat(email, contains(new ComposeMailPage(driver).getAddressFromMail2(),
                new ComposeMailPage(driver).getSubjectFromMail(),
                new ComposeMailPage(driver).getBodyFromMail()));

        System.out.println("Letter with appropriate adress, subject and body is presented in Draft folder");
        new DraftPage(driver).openDraftFolder().clearDraftFolderMail().performLogout();
    }
}