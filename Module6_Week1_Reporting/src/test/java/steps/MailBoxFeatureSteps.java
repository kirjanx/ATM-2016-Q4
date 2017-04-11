package steps;

import business_object.user.Letter.Letter;
import business_object.user.User;
import business_object.user.UserFactory;
import cucumber.api.java.After;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
    private static Logger logger = LogManager.getRootLogger();

    /**
     * Scenario: Login with valid username and password
     */
    @Given("^I enter valid credentials on home page$")
    public void iEnterValidCredentialsOnHomePage() {
        user = UserFactory.createDefaultUser();
        new HomePage(driver).enterValidCredentialsForLogin(user);
        logger.info("Credentials were entered");
    }

    @When("^I click login button$")
    public void iClickLoginButton() {
        new HomePage(driver).clickLoginButton();
        logger.info("Login button was clicked");
    }

    @Then("^I should see my email address$")
    public void iShouldSeeMyEmailAddress() {
        boolean isLoginComplete = new InboxPage(driver).isUserMailAfterLoginDisplayed();
        Assert.assertTrue(isLoginComplete, "You are NOT logged in");
        logger.info("Logged in");
    }

    /**
     * Scenario: Create new Email and save as draft
     */
    @Given("I click Create New Mail button on Inbox page$")
    public void iClickCreateNewMailButton() {
        new ComposeMailPage(driver).open();
        logger.info("Create New Mail button was clicked");
    }

    @When("^I fill in address: \"([^\"]*)\"; subject: \"([^\"]*)\"; body: \"([^\"]*)\"; click Save button on the new mail page$")
    public void iFillInAddressSubjectAndPassword(String address, String subject, String body) {
        new ComposeMailPage(driver).fillInAddressSubjectAndPassword(address, subject, body);
        logger.info("Address, subject and body fields were entered");
        new ComposeMailPage(driver).clickSaveButton();
        logger.info("Save button was clicked");
    }

    @Then("^I find saved letter with address: \"([^\"]*)\"; in the Draft folder$")
    public void iFindLetterInDraftFolder(String address) {
        new DraftPage(driver).openDraftFolder();
        logger.info("Draft folder was opened");
        Assert.assertEquals(new DraftPage(driver).getMailAddressFromDraftFolder(), address,
                "Created Email isn't presented in the Draft folder");
        logger.info("Created Email is presented in the Draft folder");
    }

    /**
     * Scenario: Send mail and verify Draft folder is empty
     */
    @Given("^I open Email in the Draft folder$")
    public void iOpenEmailInDraftFolder() {
        new DraftPage(driver).openMailFromDraftFolder();
        logger.info("Mail was opened from draft");
    }

    @When("^I click Send button$")
    public void iClickSendButton() {
        new ComposeMailPage(driver).clickSendButton();
        logger.info("Send button was clicked");
    }

    @Then("^I can't find sent letter in Draft folder$")
    public void iFindSentLetterInSentFolder() {
        Assert.assertFalse(new DraftPage(driver).openDraftFolder()
                .isDraftFolderHasLetter(), "Draft folder has letter");
        logger.info("Draft folder was empty");
    }

    /**
     * Scenario: Verify that sent mail is presented in Sent folder
     */
    @Given("^I'm in the Draft folder$")
    public void iAmInDraftFolder() {
        new DraftPage(driver).openDraftFolder();
        logger.info("Draft folder was opened");
    }

    @When("^I open Sent folder$")
    public void iOpenSentFolder() {
        new DraftPage(driver).openSentFolder();
        logger.info("Sent folder was opened");
    }

    @Then("^I find sent letter with address: \"([^\"]*)\"; in Sent folder$")
    public void iFindSentLetterInSentFolder(String address) {
        Assert.assertEquals(new SentPage(driver).getMailAddressFromSentFolder(),
                address, "Sent folder was empty");
        logger.info("Sent Letter was presented in the Sent folder");
    }

    /**
     * Scenario: Clear Sent folder and logout
     */
    @Given("^I clear Sent folder$")
    public void iClearSentFolder() {
        new SentPage(driver).clearSentMailFolder();
        logger.info("Sent folder was empty");
    }

    @When("^I perform logout$")
    public void iPerformLogout() {
        new InboxPage(driver).performLogout();
        logger.info("Performed logout");
    }

    @Then("^I'm redirecting to the Home page$")
    public void iAmRedirectingToHomePage() {
        boolean logoutIsComplete = new HomePage(driver).isLogoutSuccessful();
        Assert.assertTrue(logoutIsComplete, "Logout wasn't completed");
        logger.info("Logout was completed successful");
    }

    /**
     * Scenario: Create mail, save as draft, open draft mail and verify data in all fields
     */
    @Given("^I create new email and save draft$")
    public void iCreateNewMail(List<Letter> letters) {
        user = UserFactory.createDefaultUser();
        new HomePage(driver).enterValidCredentialsForLogin(user).clickLoginButton();
        logger.info("Logged in");

        new ComposeMailPage(driver).open().createEmail(letter = letters.get(0))
                .clickSaveButton();
        logger.info("Mail was created and saved as draft");
    }

    @When("^I open Draft folder$")
    public void iOpenDraftFolder() {
        new DraftPage(driver).openDraftFolder();
        logger.info("Draft folder was opened");
    }

    @Then("^I find created letter with valid data in the Draft folder$")
    public void iFindLetterInDraftFolder() {
        assertThat("Some of letter's properties are wrong or it's missing.", letter, allOf(
                Matchers.hasProperty("address", equalTo(new ComposeMailPage(driver).getAddressFromMail())),
                Matchers.hasProperty("subject", equalTo(new ComposeMailPage(driver).getSubjectFromMail())),
                Matchers.hasProperty("body", equalTo(new ComposeMailPage(driver).getBodyFromMail()))));
        logger.info("Letter with appropriate address, subject and body was presented in Draft folder");
    }

    @After("@clearDraft")
    public void clearDraftFolder() {
        new DraftPage(driver).clearDraftFolderMail().performLogout();
    }
}