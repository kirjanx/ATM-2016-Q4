import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailBoxPage extends Page{

    public static final int TIME_OUT = 10;

    private static final By USER_EMAIL_STRING_AFTER_LOGIN_LOCATOR = By.xpath("//i[@id='PH_user-email']");
    private static final By CREATE_EMAIL_BUTTON_LOCATOR
            = By.xpath("//a[@class='b-toolbar__btn js-shortcut' and @data-name='compose']");
    private static final By EMAIL_FIELD_TO_LOCATOR
            = By.xpath("//textarea[@class='js-input compose__labels__input' and @data-original-name='To']");
    private static final By EMAIL_FIELD_SUBJECT_LOCATOR = By.xpath("//input[@class='b-input' and @name='Subject']");
    private static final By EMAIL_FIELD_BODY_IFRAME_LOCATOR = By.xpath("//iframe[starts-with(@id, 'toolkit')]");
    private static final By EMAIL_FIELD_BODY_LOCATOR = By.xpath("//body[@id='tinymce']");
    private static final By SAVE_EMAIL_BUTTON_LOCATOR = By.xpath("//div[@data-name='saveDraft']");
    private static final By NOTIFICATION_STATUS_DRAFT_LINK_LOCATOR
            = By.xpath("//a[@class='toolbar__message_info__link']");
    private static final By SEND_MAIL_BUTTON_LOCATOR = By.xpath("//div[@data-name='send']");
    private static final By DRAFT_FOLDER_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_drafts']");
    private static final By SENT_FOLDER_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_send']");
    private static final By CLEAR_DRAFT_CHECKBOX_LOCATOR
            = By.xpath("//div[@id='b-toolbar__right']/div[@data-cache-key='500001_undefined_false']" +
            "/div[@class='b-toolbar b-toolbar_adaptive']/div[@class='b-toolbar__group']" +
            "/div[@class='b-toolbar__item b-toolbar__item_selectAll']" +
            "/div[@class='b-dropdown b-dropdown_selectAll']/div[@class='b-dropdown__ctrl']" +
            "/div[@class='js-checkbox b-checkbox b-checkbox_transparent b-checkbox_false js-shortcut']" +
            "/div[@class='b-checkbox__box']");
    private static final By CLEAR_SENT_CHECKBOX_LOCATOR
            = By.xpath("//div[@id='b-toolbar__right']/div[@data-cache-key='500000_undefined_false']" +
            "/div[@class='b-toolbar b-toolbar_adaptive']/div[@class='b-toolbar__group']" +
            "/div[@class='b-toolbar__item b-toolbar__item_selectAll']/div[@class='b-dropdown b-dropdown_selectAll']" +
            "/div[@class='b-dropdown__ctrl']" +
            "/div[@class='js-checkbox b-checkbox b-checkbox_transparent b-checkbox_false js-shortcut']" +
            "/div[@class='b-checkbox__box']");
    private static final By DRAFT_FOLDER_MAIL_FIELD_LOCATOR = By.xpath("//div[text()='someEmail@yandex.ru']");
    private static final By ADDRESS_INPUT_IN_DRAFT_MAIL_LOCATOR = By.xpath("//input[@id='compose_to']");
    private static final By SUBJECT_INPUT_IN_DRAFT_MAIL_LOCATOR
            = By.xpath("//div[@class='compose-head__field']/input[1]");
    private static final By LOGOUT_LINK_LOCATOR = By.xpath("//a[@id='PH_logoutLink']");
    private static final By POP_UP_SUBMIT_SEND_MAIL_LOCATOR
            = By.xpath("//div[@id='MailRuConfirm']/div[@class='popup js-layer popup_dark popup_']" +
            "/div[@class='is-compose-empty_in']/form[/]/div[@class='popup__controls']/button[@type='submit']");
    private static final By INBOX_LINK_AFTER_MAIL_SENT_LOCATOR
            = By.xpath("//div[@class='message-sent__title']/a[@href='/messages/inbox/']");
    private static final By WAIT_TEXT_SENT_FOLDER_LOCATOR = By.xpath("//div[@class='b-kav-protected']");

    Element userMailStringAfterLogin = new Element(USER_EMAIL_STRING_AFTER_LOGIN_LOCATOR);
    Element createEmailButton = new Element(CREATE_EMAIL_BUTTON_LOCATOR);
    Element emailFieldTo = new Element(EMAIL_FIELD_TO_LOCATOR);
    Element emailFieldSubject = new Element(EMAIL_FIELD_SUBJECT_LOCATOR);
    Element emailFieldBody = new Element(EMAIL_FIELD_BODY_LOCATOR);
    Element saveEmailButton = new Element(SAVE_EMAIL_BUTTON_LOCATOR);
    Element sendMailButton = new Element(SEND_MAIL_BUTTON_LOCATOR);
    Element draftFolder = new Element(DRAFT_FOLDER_LOCATOR);
    Element sentFolder = new Element(SENT_FOLDER_LOCATOR);
    Element clearDraftCheckbox = new Element(CLEAR_DRAFT_CHECKBOX_LOCATOR);
    Element clearSentCheckbox = new Element(CLEAR_SENT_CHECKBOX_LOCATOR);
    Element draftFolderMailField = new Element(DRAFT_FOLDER_MAIL_FIELD_LOCATOR);
    Element addressInputInDraftMail = new Element(ADDRESS_INPUT_IN_DRAFT_MAIL_LOCATOR);
    Element subjectInputInDraftMail = new Element(SUBJECT_INPUT_IN_DRAFT_MAIL_LOCATOR);
    Element logoutLink = new Element(LOGOUT_LINK_LOCATOR);
    Element popUpSubmitSendMail = new Element(POP_UP_SUBMIT_SEND_MAIL_LOCATOR);

    public MailBoxPage(WebDriver driver) {
        super(driver);
    }

    public boolean userMailAfterLoginIsDisplayed(){
        return userMailStringAfterLogin.isDisplayed();
    }

    public HomePage logout(){
        logoutLink.click();
        return new HomePage(driver);
    }

    public MailBoxPage createMail(String address, String subject, String bodyText){
        createEmailButton.click();
        emailFieldTo.typeValue(address);
        emailFieldSubject.typeValue(subject);
        driver.switchTo().frame(driver.findElement(EMAIL_FIELD_BODY_IFRAME_LOCATOR));
        emailFieldBody.typeValue(bodyText);
        driver.switchTo().defaultContent();
        saveEmailButton.click();

        WebDriverWait waitForSaveSuccessNotification = new WebDriverWait(driver, TIME_OUT);
        waitForSaveSuccessNotification.until(ExpectedConditions.elementToBeClickable
                (driver.findElement(NOTIFICATION_STATUS_DRAFT_LINK_LOCATOR)));
        return new MailBoxPage(driver);
    }

    public MailBoxPage openDraft(){
        driver.switchTo().defaultContent();
        draftFolder.click();
        return new MailBoxPage(driver);
    }

    public MailBoxPage clearDraft(){
        clearDraftCheckbox.click();
        draftFolder.useKeybordDelete();
        return new MailBoxPage(driver);
    }

    public MailBoxPage openSent(){
        driver.switchTo().defaultContent();
        sentFolder.click();
        WebDriverWait waitForText = new WebDriverWait(driver, TIME_OUT);
        waitForText.until(ExpectedConditions.elementToBeClickable
                (driver.findElement(WAIT_TEXT_SENT_FOLDER_LOCATOR)));
        return new MailBoxPage(driver);
    }

    public MailBoxPage clearSent(){
        clearSentCheckbox.click();
        sentFolder.useKeybordDelete();
        return new MailBoxPage(driver);
    }

    public String getMailAddressFromDraftFolder(){
        return draftFolderMailField.getText();
    }

    public MailBoxPage openMailFromDraft(){
        draftFolderMailField.click();
        return new MailBoxPage(driver);
    }

    public String getAddressFromMail(){
        return addressInputInDraftMail.getAttributeValue("value");
    }

    public String getSubjectFromMail(){
        return subjectInputInDraftMail.getAttributeValue("value");
    }

    public String getBodyFromMail(){
        driver.switchTo().frame(driver.findElement(EMAIL_FIELD_BODY_IFRAME_LOCATOR));
        return emailFieldBody.getText();
    }

    public MailBoxPage sendMail(){
        sendMailButton.click();
        String handle = driver.getWindowHandle();
        driver.switchTo().window(handle);
        popUpSubmitSendMail.click();

        WebDriverWait waitForNotify = new WebDriverWait(driver, TIME_OUT);
        waitForNotify.until(ExpectedConditions.elementToBeClickable
                (driver.findElement(INBOX_LINK_AFTER_MAIL_SENT_LOCATOR)));
        return new MailBoxPage(driver);
    }

    public boolean isEmpty(){
        return draftFolderMailField.isPresent();
    }
}