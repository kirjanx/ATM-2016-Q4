package step;

import org.openqa.selenium.WebDriver;
import page.MailBoxPage;

public class MailBoxPageSteps {

    private WebDriver driver;

    public void initBrowser(){
        //this.driver = new FirefoxDriver();
//        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }


    public void closeBrowser() {
        this.driver.quit();
    }


    public void loginMailBoxMailRu(String login, String password){
        MailBoxPage mailBoxPage = new MailBoxPage();
        mailBoxPage.openMainPageMailRu();
        mailBoxPage.loginMailBoxMailRu(login, password);
    }


    public String getLoggedInUserEmail(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        return mailBoxPage.getLoggedInUserEmail();
    }


    public void createEmailAndSaveDraft(String emailAddress, String subject, String bodyText){
        MailBoxPage mailBoxPage = new MailBoxPage();
        mailBoxPage.createNewEmailAndSaveAsDraft(emailAddress, subject, bodyText);
        mailBoxPage.openDraft();
    }


    public void openDraft(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        mailBoxPage.openDraft();
    }


    public String verifyThatMailPresentsInDraft(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        return mailBoxPage.verifyThatMailPresentsInDraft();
    }


    public void openLetterFromDraft(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        mailBoxPage.openLetterFromDraft();
    }


    public void openLetterFromDraft2(){////////////////////////////////////////avoid duplicate
        MailBoxPage mailBoxPage = new MailBoxPage();
        mailBoxPage.openLetterFromDraft2();
    }


    public String verifyAddressOfDraftLetter(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        return mailBoxPage.verifyAddressOfDraftLetter();
    }


    public String verifySubjectOfDraftLetter(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        return mailBoxPage.verifySubjectOfDraftLetter();
    }


    public String verifyBodyOfDraftLetter(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        return mailBoxPage.verifyBodyOfDraftLetter();
    }


    public void sendEmailFromDraft(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        mailBoxPage.sendEmailFromDraft();
    }


    public boolean isDraftMailSentAndNOTPresentsInDraft(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        return mailBoxPage.isDraftMailSentAndNOTPresentsInDraft();
    }


    public void openSentFolder(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        mailBoxPage.openSentFolder();
    }


    public boolean isSentMailPresentsInSentFolder(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        return mailBoxPage.isSentMailPresentsInSentFolder();
    }


    public void logout(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        mailBoxPage.logout();
    }


    public boolean isLogoutSuccessfull(){
        MailBoxPage mailBoxPage = new MailBoxPage();
        return mailBoxPage.isLogoutSuccessfull();
    }

}
