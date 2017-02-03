import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import page.BaseClass;
import page.MailBoxPage;
import step.MailBoxPageSteps;
import ui.Browser;
import util.Util;

public class MailBoxMailRuTest {

    private static final String LOGIN = "tester.atm";
    private static final String PASSWORD = "q123456";
    private static final String EMAIL_ADDRESS = "someEmail@yandex.ru";
    private static final String BODY_TEXT = "BodyTextTest";



    private MailBoxPageSteps mailBoxPageSteps;
    private MailBoxPage mailBoxPage;

    private BaseClass baseClass;

    private Browser browser;


//    @BeforeClass
//    public void setUpBefore(){
//        mailBoxPageSteps = new MailBoxPageSteps();
//        mailBoxPageSteps.initBrowser();
//        mailBoxPage = new MailBoxPage();
//    }

    @BeforeClass
    public void setUpBefore(){
        browser = new Browser();
        browser.initBrowser();
        mailBoxPage = new MailBoxPage();
    }







    @Test (description = "Verify the login to the mail box")
    public void loginMailBox(){
        mailBoxPageSteps.loginMailBoxMailRu(LOGIN, PASSWORD);
        Assert.assertEquals(mailBoxPageSteps.getLoggedInUserEmail(), "tester.atm@mail.ru", "Login data not valid");
    }


    @Test (description = "Login the mail box, create new Email, save Email as draft, verify data in all fields of draft letter")
    public void createEmailAndSaveDraft(){
        mailBoxPageSteps.loginMailBoxMailRu(LOGIN, PASSWORD);

        String subject = Util.generateRandomSubjectTitle();
        mailBoxPageSteps.createEmailAndSaveDraft(EMAIL_ADDRESS, subject, BODY_TEXT);

        mailBoxPageSteps.openDraft();
        Assert.assertEquals(mailBoxPageSteps.verifyThatMailPresentsInDraft(), subject + BODY_TEXT, "Created Email isn't presented in the Draft folder");//////////////line breaks for long lines

        mailBoxPageSteps.openLetterFromDraft();
        Assert.assertEquals(mailBoxPageSteps.verifyAddressOfDraftLetter(), EMAIL_ADDRESS + ",", "Email in the draft message is invalid");//////////////line breaks for long lines
        Assert.assertEquals(mailBoxPageSteps.verifySubjectOfDraftLetter(), subject, "Subject in the draft message is invalid");
        Assert.assertEquals(mailBoxPageSteps.verifyBodyOfDraftLetter(), BODY_TEXT, "Invalid body text");
    }


    @Test (description = "Login the mail box, create new Email, save Email as draft, send Email from Draft folder and verify that Email was sent successfully, then Logoff")//////////////line breaks for long lines
    public void createEmailAndSendFromDraft(){
        mailBoxPageSteps.loginMailBoxMailRu(LOGIN, PASSWORD);//////////////line breaks for long lines

        mailBoxPageSteps.createEmailAndSaveDraft(EMAIL_ADDRESS, Util.SUBJECT_2, BODY_TEXT);//////////////line breaks for long lines


        mailBoxPageSteps.openDraft();
        mailBoxPageSteps.openLetterFromDraft2();

        mailBoxPageSteps.sendEmailFromDraft();

        mailBoxPageSteps.openSentFolder();
        Assert.assertTrue(mailBoxPageSteps.isSentMailPresentsInSentFolder(), "Sent Email is NOT presented in the SENT folder");//////////////line breaks for long lines

        mailBoxPageSteps.openDraft();
        Assert.assertFalse(mailBoxPageSteps.isDraftMailSentAndNOTPresentsInDraft(), "Sent Email is still presented in the DRAFT folder");//////////////verify FALSE

        mailBoxPageSteps.logout();
        Assert.assertTrue(mailBoxPageSteps.isLogoutSuccessfull(), "Logout was not performed");//////////////line breaks for long lines
    }


    @AfterClass
    public void tearDown(){
        mailBoxPageSteps.closeBrowser();
    }



}
