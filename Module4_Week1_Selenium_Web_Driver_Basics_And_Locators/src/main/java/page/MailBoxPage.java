package page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.Util;

public class MailBoxPage extends BaseClass{

    private static final String BASE_URL = "https://mail.ru/";

    private static final By LOGIN_FIELD_LOCATOR = By.xpath("//input[@id='mailbox__login']");
    private static final By PASSWORD_FIELD_LOCATOR = By.xpath("//input[@id='mailbox__password']");
    private static final By ENTER_BUTTON_LOCATOR = By.xpath("//input[@id='mailbox__auth__button']");
    private static final By USER_EMAIL_STRING_AFTER_LOGIN_LOCATOR = By.xpath("//i[@id='PH_user-email']");
    private static final By LOGIN_BUTTON_LOCATOR = By.xpath("//a[@id='PH_authLink']");
    private static final By CREATE_EMAIL_BUTTON_LOCATOR = By.xpath("//a[@class='b-toolbar__btn js-shortcut' and @data-name='compose']");
    private static final By EMAIL_FIELD_TO_LOCATOR = By.xpath("//textarea[@class='js-input compose__labels__input' and @data-original-name='To']");
    private static final By EMAIL_FIELD_SUBJECT_LOCATOR = By.xpath("//input[@class='b-input' and @name='Subject']");
    private static final By EMAIL_FIELD_SUBJECT_IFRAME_LOCATOR = By.xpath("//iframe[starts-with(@id, 'toolkit')]");
    private static final By EMAIL_FIELD_BODY_LOCATOR = By.xpath("//body[@id='tinymce']");
    private static final By EMAIL_SAVE_BUTTON_LOCATOR = By.xpath("//div[@data-name='saveDraft']");
    private static final By NOTIFICATION_STATUS_DRAFT_LINK_LOCATOR = By.xpath("//a[@class='toolbar__message_info__link']");
    private static final By EMAIL_SEND_BUTTON_LOCATOR = By.xpath("//div[@data-name='send']");
    private static final By EMAIL_DRAFT_FOLDER_LINK_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_drafts']");
    private final static By EMAIL_SENT_FOLDER_LINK_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_send']");
    private static final String SUBJECT_LOCATOR_PATTERN = "//div[text()='%s']";

    private WebElement draftFolderSubjectField;
    private By xpath = By.xpath(String.format(SUBJECT_LOCATOR_PATTERN, Util.generateRandomSubjectTitle()));

//    private static final String SUBJECT_LOCATOR_PATTERN = "//div[text()='%s']";
//    private WebElement draftFolderSubjectField;
//    private By xpath = By.xpath(String.format(SUBJECT_LOCATOR_PATTERN, Util.SUBJECT));

    private static final String SUBJECT_LOCATOR_PATTERN_2 = "//div[text()='%s']";
    private WebElement draftFolderSubjectField2;
    private By xpath2 = By.xpath(String.format(SUBJECT_LOCATOR_PATTERN_2, Util.SUBJECT_2));

    private static final By EMAIL_STRING_IN_THE_DRAFT_LETTER_LOCATOR = By.xpath("//input[@id='compose_to']");
    private static final By SUBJECT_STRING_IN_THE_DRAFT_LETTER_LOCATOR = By.xpath("//div[@class='compose-head__field']/input[1]");
    private static final By POP_UP_SUBMIT_BUTTON_SEND_MESSAGE = By.xpath("//*[@id='MailRuConfirm']/div/div[3]/form/div[2]/button[1]");
    private static final By LOGOUT_LINK_LOCATOR = By.xpath("//a[@id='PH_logoutLink']");

    public MailBoxPage(WebDriver driver) {
        super(driver);
    }

    public void openMainPageMailRu() {
        driver.get(BASE_URL);
    }

    public void loginMailBoxMailRu(String login, String password){
        driver.findElement(LOGIN_FIELD_LOCATOR).click();////////////////////////need to USE fields for elements to avoid double code
        driver.findElement(LOGIN_FIELD_LOCATOR).sendKeys(login);


        driver.findElement(PASSWORD_FIELD_LOCATOR).click();
        driver.findElement(PASSWORD_FIELD_LOCATOR).sendKeys(password);

        driver.findElement(ENTER_BUTTON_LOCATOR).click();
    }

    public String getLoggedInUserEmail(){
        return driver.findElement(USER_EMAIL_STRING_AFTER_LOGIN_LOCATOR).getText();
    }

    public void createNewEmailAndSaveAsDraft(String emailAddress, String subject, String bodyText) {
        driver.findElement(CREATE_EMAIL_BUTTON_LOCATOR).click();
        driver.findElement(EMAIL_FIELD_TO_LOCATOR).click();
        driver.findElement(EMAIL_FIELD_TO_LOCATOR).sendKeys(emailAddress);

        driver.findElement(EMAIL_FIELD_SUBJECT_LOCATOR).click();
        driver.findElement(EMAIL_FIELD_SUBJECT_LOCATOR).sendKeys(subject);

        driver.switchTo().frame(driver.findElement(EMAIL_FIELD_SUBJECT_IFRAME_LOCATOR));

        driver.findElement(EMAIL_FIELD_BODY_LOCATOR).click();
        driver.findElement(EMAIL_FIELD_BODY_LOCATOR).clear();
        driver.findElement(EMAIL_FIELD_BODY_LOCATOR).sendKeys(bodyText);

        driver.switchTo().defaultContent();

        driver.findElement(EMAIL_SAVE_BUTTON_LOCATOR).click();

        WebDriverWait waitForSaveSuccessNotification = new WebDriverWait(driver, 10);
        waitForSaveSuccessNotification.until(ExpectedConditions.elementToBeClickable(NOTIFICATION_STATUS_DRAFT_LINK_LOCATOR));
    }

    public void openDraft(){
        driver.switchTo().defaultContent();
        driver.findElement(EMAIL_DRAFT_FOLDER_LINK_LOCATOR).click();
    }

    public String verifyThatMailPresentsInDraft(){
        draftFolderSubjectField = driver.findElement(xpath);
        return draftFolderSubjectField.getText();
    }

    public void openLetterFromDraft(){
        draftFolderSubjectField = driver.findElement(xpath);
        draftFolderSubjectField.click();
    }

    public void openLetterFromDraft2(){///////////////////////////////////////////////avoid duplicate methods
        draftFolderSubjectField2 = driver.findElement(xpath2);
        draftFolderSubjectField2.click();
    }

    public String verifyAddressOfDraftLetter(){
        return driver.findElement(EMAIL_STRING_IN_THE_DRAFT_LETTER_LOCATOR).getAttribute("value");
    }

    public String verifySubjectOfDraftLetter(){
        return driver.findElement(SUBJECT_STRING_IN_THE_DRAFT_LETTER_LOCATOR).getAttribute("value");
    }

    public String verifyBodyOfDraftLetter(){
        driver.switchTo().frame(driver.findElement(EMAIL_FIELD_SUBJECT_IFRAME_LOCATOR));
        return driver.findElement(EMAIL_FIELD_BODY_LOCATOR).getText();
    }

    public void sendEmailFromDraft(){
        driver.findElement(EMAIL_SEND_BUTTON_LOCATOR).click();

        String handle = driver.getWindowHandle();
        driver.switchTo().window(handle);
        driver.findElement(POP_UP_SUBMIT_BUTTON_SEND_MESSAGE).click();
    }

    public boolean isDraftMailSentAndNOTPresentsInDraft(){
        try {
            driver.findElement(xpath2);
            return true;
        } catch(NoSuchElementException e) {
            return false;
        }
    }

    public void openSentFolder(){
        driver.findElement(EMAIL_SENT_FOLDER_LINK_LOCATOR).click();
    }

    public boolean isSentMailPresentsInSentFolder(){/////////////////////////////////////////////////////////////need to remove exceptions in all methods
        try {
            driver.findElement(xpath);
            return true;
        } catch(NoSuchElementException e) {
            return false;
        }
    }

    public void logout(){
        driver.findElement(LOGOUT_LINK_LOCATOR).click();
    }

    public boolean isLogoutSuccessfull(){
        try {
            driver.findElement(LOGIN_BUTTON_LOCATOR);
            return true;
        } catch(NoSuchElementException e) {
            return false;
        }
    }
}
