package step;

import org.openqa.selenium.WebDriver;
import page.BaseClass;
import page.MailBoxPage;

public class MailBoxPageSteps extends BaseClass{

    public MailBoxPageSteps(WebDriver driver) {
        super(driver);
    }

    public void closeBrowser() {
        this.driver.quit();
    }


    public void loginMailBoxMailRu(String login, String password){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        //mailBoxPage.openMainPageMailRu();
        mailBoxPage.loginMailBoxMailRu(login, password);
    }


    public String getLoggedInUserEmail(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        return mailBoxPage.getLoggedInUserEmail();
    }


    public void createEmailAndSaveDraft(String emailAddress, String subject, String bodyText){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        mailBoxPage.createNewEmailAndSaveAsDraft(emailAddress, subject, bodyText);
        mailBoxPage.openDraft();
    }


    public void openDraft(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        mailBoxPage.openDraft();
    }


    public String verifyThatMailPresentsInDraft(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        return mailBoxPage.verifyThatMailPresentsInDraft();
    }


    public void openLetterFromDraft(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        mailBoxPage.openLetterFromDraft();
    }


    public void openLetterFromDraft2(){////////////////////////////////////////avoid duplicate
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        mailBoxPage.openLetterFromDraft2();
    }


    public String verifyAddressOfDraftLetter(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        return mailBoxPage.verifyAddressOfDraftLetter();
    }


    public String verifySubjectOfDraftLetter(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        return mailBoxPage.verifySubjectOfDraftLetter();
    }


    public String verifyBodyOfDraftLetter(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        return mailBoxPage.verifyBodyOfDraftLetter();
    }


    public void sendEmailFromDraft(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        mailBoxPage.sendEmailFromDraft();
    }


    public boolean isDraftMailSentAndNOTPresentsInDraft(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        return mailBoxPage.isDraftMailSentAndNOTPresentsInDraft();
    }


    public void openSentFolder(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        mailBoxPage.openSentFolder();
    }


    public boolean isSentMailPresentsInSentFolder(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        return mailBoxPage.isSentMailPresentsInSentFolder();
    }


    public void logout(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        mailBoxPage.logout();
    }


    public boolean isLogoutSuccessfull(){
        MailBoxPage mailBoxPage = new MailBoxPage(driver);
        return mailBoxPage.isLogoutSuccessfull();
    }

}
