import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class MailRuTest {

    private static final String LOGIN = "tester.atm";
    private static final String PWD = "q123456";
    private static final String EMAIL_ADDRESS = "someEmail@yandex.ru";
    private static final String EMAIL_SUBJECT = "Test Subject";
    private static final String BODY_TEXT = "BodyTextTest";
    private static final String BASE_URL = "https://mail.ru/";
    private static final int IMPLICITY_WAIT = 30;
    private static final int PAGE_LOAD_TIMEOUT = 60;

    private WebDriver driver;
    private MailBoxPage mailBoxPage;
    private HomePage homePage;

    @BeforeClass(description = "Start browser and initialize pages")
    public void setUpBefore(){
        driver = new FirefoxDriver();
        driver.get(BASE_URL);
        mailBoxPage = new MailBoxPage(driver);
        homePage = new HomePage(driver);
    }

    @BeforeClass(dependsOnMethods = "setUpBefore", description = "Add implicity and maximize window")
    public void addImplicity(){
        driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test(description = "Login to Mail.ru mailbox")
    public void loginMailBox(){
        boolean loginIsComplete = homePage.loginMailBox(LOGIN, PWD).userMailAfterLoginIsDisplayed();
        Assert.assertTrue(loginIsComplete, "Yor are NOT logged in");
        System.out.println("Login was completed correctly");
        mailBoxPage.logout();
    }

    @Test(description = "Login to Mail Box, create new Email, save as Draft")
    public void createMail(){
        homePage.loginMailBox(LOGIN, PWD).createMail(EMAIL_ADDRESS, EMAIL_SUBJECT, BODY_TEXT).openDraft();
        Assert.assertEquals(mailBoxPage.getMailAddressFromDraftFolder(), EMAIL_ADDRESS,
                "Created Email isn't presented in the Draft folder");
        System.out.println("Email was created and saved in Draft folder");
        mailBoxPage.clearDraft().logout();
    }

    @Test(description = "Login to Mail Box, create new Email, save as Draft, open Draft and check Mail Address")
    public void getMailAddress(){
        homePage.loginMailBox(LOGIN, PWD).createMail(EMAIL_ADDRESS, EMAIL_SUBJECT, BODY_TEXT).
                openDraft().openMailFromDraft();
        Assert.assertEquals(mailBoxPage.getAddressFromMail(), EMAIL_ADDRESS + ",",
                "Address in the draft message is invalid");
        mailBoxPage.openDraft().clearDraft().logout();
    }

    @Test(description = "Login to Mail Box, create new Email, save as Draft, open Draft and check Mail Subject")
    public void getMailSubject(){
        homePage.loginMailBox(LOGIN, PWD).createMail(EMAIL_ADDRESS, EMAIL_SUBJECT, BODY_TEXT).
                openDraft().openMailFromDraft();
        Assert.assertEquals(mailBoxPage.getSubjectFromMail(), EMAIL_SUBJECT,
                "Subject in the draft message is invalid");
        mailBoxPage.openDraft().clearDraft().logout();
    }

    @Test(description = "Login to Mail Box, create new Email, save as Draft, open Draft and check Mail Body Text")
    public void getMailBody(){
        homePage.loginMailBox(LOGIN, PWD).createMail(EMAIL_ADDRESS, EMAIL_SUBJECT, BODY_TEXT).
                openDraft().openMailFromDraft();
        Assert.assertEquals(mailBoxPage.getBodyFromMail(), BODY_TEXT,
                "Body text in the draft message is invalid");
        mailBoxPage.openDraft().clearDraft().logout();
    }

    @Test(description = "Login to Mail Box, create new Email, save as Draft and send Mail from Draft folder")
    public void sendMailAndGetDraftData(){
        homePage.loginMailBox(LOGIN, PWD).createMail(EMAIL_ADDRESS, EMAIL_SUBJECT, BODY_TEXT).
                openDraft().openMailFromDraft().sendMail();

        boolean isDraftEmpty = mailBoxPage.openDraft().isEmpty();
        Assert.assertFalse(isDraftEmpty, "Sent mail PRESENTS in the Draft folder");
        System.out.println("Mail was sent successfully");

        mailBoxPage.openSent().clearSent().logout();
    }

    @Test(description = "Login to Mail Box, create new Email, save as Draft and send Mail from Draft folder")
    public void sendMailAndGetSentData(){
        homePage.loginMailBox(LOGIN, PWD).createMail(EMAIL_ADDRESS, EMAIL_SUBJECT, BODY_TEXT).
                openDraft().openMailFromDraft().sendMail();

        boolean isSentEmpty = mailBoxPage.openSent().isEmpty();
        Assert.assertTrue(isSentEmpty, "Sent folder is EMPTY");
        System.out.println("Mail was sent successfully");

        mailBoxPage.clearSent().logout();
    }

    @Test(description = "Login the mail box and perform logout")
    public void logout(){
        homePage.loginMailBox(LOGIN, PWD).logout().isLogoutSuccessfull();
        System.out.println("Logout was completed correctly");
    }

    @AfterClass(description = "Close browser")
    public void tearDown(){
        driver.quit();
    }
}