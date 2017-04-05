package steps;

import business_object.user.Letter.Letter;
import business_object.user.User;
import business_object.user.UserFactory;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import page_object.*;
import setup.Browser;
import setup.WebDriverTypes;

import java.util.List;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class MailBoxFeatureSteps {

    private WebDriver driver = Browser.getWrappedDriver(WebDriverTypes.FIREFOX);
    private User user;
    private Letter letter;

    /**
     * Scenario: Login with valid username and password
     */
    @Given("^I enter valid credentials on home page$")
    public void iEnterValidCredentialsOnHomePage() {
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
        boolean isLoginComplete = new InboxPage(driver).isUserMailAfterLoginDisplayed();
        Assert.assertTrue(isLoginComplete, "You are NOT logged in");
        System.out.println("Logged in");
    }

    /**
     * Scenario: Create new Email and save as draft
     */
    @Given("I click Create New Mail button on Inbox page$")
    public void iClickCreateNewMailButton() {
        new ComposeMailPage(driver).open();
        System.out.println("Create New Mail button is clicked");
    }

    @When("^I fill in address: \"([^\"]*)\"; subject: \"([^\"]*)\"; body: \"([^\"]*)\"; click Save button on the new mail page$")
    public void iFillInAddressSubjectAndPassword(String address, String subject, String body) {
        new ComposeMailPage(driver).fillInAddressSubjectAndPassword(address, subject, body);
        System.out.println("Address, subject and body fields are filled in");
        new ComposeMailPage(driver).clickSaveButton();
        System.out.println("Save button is clicked");
    }

    @Then("^I find saved letter with address: \"([^\"]*)\"; in the Draft folder$")
    public void iFindLetterInDraftFolder(String address) {
        new DraftPage(driver).openDraftFolder();
        System.out.println("Draft folder is opened");
        Assert.assertEquals(new DraftPage(driver).getMailAddressFromDraftFolder(), address,
                "Created Email isn't presented in the Draft folder");
        System.out.println("Created Email is presented in the Draft folder");
    }

    /**
     * Scenario: Send mail and verify Draft folder is empty
     */
    @Given("^I open Email in the Draft folder$")
    public void iOpenEmailInDraftFolder() {
        new DraftPage(driver).openMailFromDraftFolder();
        System.out.println("Mail is opened from draft");
    }

    @When("^I click Send button$")
    public void iClickSendButton() {
        new ComposeMailPage(driver).clickSendButton();
        System.out.println("Send button is clicked");
    }

    @Then("^I can't find sent letter in Draft folder$")
    public void iFindSentLetterInSentFolder() {
        Assert.assertFalse(new DraftPage(driver).openDraftFolder()
                .isDraftFolderHasLetter(), "Draft folder has letter");
        System.out.println("Draft folder is empty");
    }

    /**
     * Scenario: Verify that sent mail is presented in Sent folder
     */
    @Given("^I'm in the Draft folder$")
    public void iAmInDraftFolder() {
        new DraftPage(driver).openDraftFolder();
        System.out.println("Draft folder is opened");
    }

    @When("^I open Sent folder$")
    public void iOpenSentFolder() {
        new DraftPage(driver).openSentFolder();
        System.out.println("Sent folder is opened");
    }

    @Then("^I find sent letter with address: \"([^\"]*)\"; in Sent folder$")
    public void iFindSentLetterInSentFolder(String address) {
        Assert.assertEquals(new SentPage(driver).getMailAddressFromSentFolder(),
                address, "Sent folder is empty");
        System.out.println("Sent Letter is presented in the Sent folder");
    }

    /**
     * Scenario: Clear Sent folder and logout
     */
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
    public void iAmRedirectingToHomePage() {
        boolean logoutIsComplete = new HomePage(driver).isLogoutSuccessful();
        Assert.assertTrue(logoutIsComplete, "Logout wasn't completed");
        System.out.println("Logout is completed correctly");
    }

    /**
     * Scenario: Create mail, save as draft, open draft mail and verify data in all fields
     */
    @Given("^I create new email and save draft$")
    public void iCreateNewMail(List<Letter> letters) {
        user = UserFactory.createDefaultUser();
        new HomePage(driver).enterValidCredentialsForLogin(user).clickLoginButton();
        System.out.println("Logged in");

        new ComposeMailPage(driver).open().createEmail(letter = letters.get(0))
                .clickSaveButton();
        System.out.println("Mail is created and saved as draft");
    }

    @When("^I open Draft folder$")
    public void iOpenDraftFolder() {
        new DraftPage(driver).openDraftFolder();
        System.out.println("Draft folder is opened");
    }

    @Then("^I find created letter with valid data in the Draft folder$")
    public void iFindLetterInDraftFolder() {
        assertThat("Some of letter's properties are wrong or it's missing.", letter, allOf(
                Matchers.hasProperty("address", equalTo(new ComposeMailPage(driver).getAddressFromMail())),
                Matchers.hasProperty("subject", equalTo(new ComposeMailPage(driver).getSubjectFromMail())),
                Matchers.hasProperty("body", equalTo(new ComposeMailPage(driver).getBodyFromMail()))));
        System.out.println("Letter with appropriate address, subject and body is presented in Draft folder");
    }

    @After("@clearDraft")
    public void clearDraftFolder() {
        new DraftPage(driver).clearDraftFolderMail().performLogout();
    }
}