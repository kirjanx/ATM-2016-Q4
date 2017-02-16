package page_object;

import business_object.Letter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.Browser;
import setup.Element;

public class ComposeMailPage extends Page {

    private WebDriverWait webDriverWait;
//    public static final int WEBDRIVER_WAIT_TIME_OUT = 3;

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
    //    private static final By DRAFT_FOLDER_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_drafts']");
    private static final By ADDRESS_INPUT_IN_DRAFT_MAIL_LOCATOR = By.xpath("//input[@id='compose_to']");
    private static final By SUBJECT_INPUT_IN_DRAFT_MAIL_LOCATOR
            = By.xpath("//div[@class='compose-head__field']/input[1]");
    private static final By SEND_MAIL_BUTTON_LOCATOR = By.xpath("//div[@data-name='send']");
    private static final By POP_UP_SUBMIT_SEND_MAIL_LOCATOR = By.xpath("//div[@id='MailRuConfirm']" +
            "//div[3]/form//button[@type='submit']");
    private static final By INBOX_LINK_AFTER_MAIL_SENT_LOCATOR
            = By.xpath("//div[@class='message-sent__title']/a[@href='/messages/inbox/']");

    Element createEmailButton = new Element(CREATE_EMAIL_BUTTON_LOCATOR);
    Element emailFieldTo = new Element(EMAIL_FIELD_TO_LOCATOR);
    Element emailFieldSubject = new Element(EMAIL_FIELD_SUBJECT_LOCATOR);
    Element emailFieldBody = new Element(EMAIL_FIELD_BODY_LOCATOR);
    Element saveEmailButton = new Element(SAVE_EMAIL_BUTTON_LOCATOR);
    //    Element draftFolderLink = new Element(DRAFT_FOLDER_LOCATOR);
    Element addressInputInDraftMail = new Element(ADDRESS_INPUT_IN_DRAFT_MAIL_LOCATOR);
    Element subjectInputInDraftMail = new Element(SUBJECT_INPUT_IN_DRAFT_MAIL_LOCATOR);
    Element sendMailButton = new Element(SEND_MAIL_BUTTON_LOCATOR);
    Element popUpSubmitSendMail = new Element(POP_UP_SUBMIT_SEND_MAIL_LOCATOR);

    public ComposeMailPage(WebDriver driver) {
        super(driver);
    }

    public DraftPage createMailAndSaveDraft(Letter letter) {
        createEmailButton.click();
        emailFieldTo.typeValue(letter.getEmailAddress());
        emailFieldSubject.typeValue(letter.getEmailSubject());
        driver.switchTo().frame(driver.findElement(EMAIL_FIELD_BODY_IFRAME_LOCATOR));
        emailFieldBody.typeValue(letter.getEmailBodyText());
        driver.switchTo().defaultContent();
        saveEmailButton.click();

        webDriverWait = new WebDriverWait(driver, Browser.WEBDRIVER_WAIT_TIME_OUT);
        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (driver.findElement(NOTIFICATION_STATUS_DRAFT_LINK_LOCATOR)));
        return new DraftPage(driver);
    }

    public String getAddressFromMail() {
        return addressInputInDraftMail.getAttributeValue("value");
    }

    public String getSubjectFromMail() {
        return subjectInputInDraftMail.getAttributeValue("value");
    }

    public String getBodyFromMail() {
        driver.switchTo().frame(driver.findElement(EMAIL_FIELD_BODY_IFRAME_LOCATOR));
        return emailFieldBody.getText();
    }

    public DraftPage sendMail() {
        sendMailButton.click();
        String windowHandle = driver.getWindowHandle();
        driver.switchTo().window(windowHandle);
        popUpSubmitSendMail.click();

        webDriverWait = new WebDriverWait(driver, Browser.WEBDRIVER_WAIT_TIME_OUT);
        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (driver.findElement(INBOX_LINK_AFTER_MAIL_SENT_LOCATOR)));
        return new DraftPage(driver);
    }
}