import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MailBoxPage {

    public static final int TIME_OUT = 10;

    private WebDriver driver;

    @FindBy(xpath = "//i[@id='PH_user-email']")
    private WebElement userMailAfterLogin;

    @FindBy(xpath = "//a[@class='b-toolbar__btn js-shortcut' and @data-name='compose']")
    private WebElement createEmailButton;

    @FindBy(xpath = "//textarea[@class='js-input compose__labels__input' and @data-original-name='To']")
    private WebElement mailFieldTo;

    @FindBy(xpath = "//input[@class='b-input' and @name='Subject']")
    private WebElement mailInputSubject;

    @FindBy(xpath = "//iframe[starts-with(@id, 'toolkit')]")
    private WebElement mailFieldBodyIFrame;

    @FindBy(xpath = "//body[@id='tinymce']")
    private WebElement mailFieldBody;

    @FindBy(xpath = "//div[@data-name='saveDraft']")
    private WebElement saveMailButton;

    @FindBy(xpath = "//a[@class='toolbar__message_info__link']")
    private WebElement notificationStatusDraftLink;

    @FindBy(xpath = "//div[@data-name='send']")
    private WebElement sendMailButton;

    @FindBy(xpath = "//i[@class='ico ico_folder ico ico_folder_drafts']")
    private WebElement draftFolder;

    @FindBy(xpath = "//i[@class='ico ico_folder ico ico_folder_send']")
    private WebElement sentFolder;

    @FindBy(xpath = "//div[text()='someEmail@yandex.ru']")
    private WebElement draftFolderMailField;

    @FindBy(xpath = "//input[@id='compose_to']")
    private WebElement addressFieldInDraftMail;

    @FindBy(xpath = "//div[@class='compose-head__field']/input[1]")
    private WebElement subjectFieldInDraftMail;

    @FindBy(xpath = "//a[@id='PH_logoutLink']")
    private WebElement logoutLink;

    @FindBy(xpath = "//div[@id='MailRuConfirm']//div[3]/form//button[@type='submit']")
    private WebElement popUpSubmitSendMail;

    @FindBy(xpath = "//div[@class='message-sent__title']/a[@href='/messages/inbox/']")
    private WebElement goToInboxLinkAfterMailSent;

    @FindBy(xpath = "//div[@class='b-kav-protected']")
    private WebElement textToWaitSentFolder;

    public MailBoxPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean userMailAfterLoginIsDisplayed(){
        return userMailAfterLogin.isDisplayed();
    }

    public MailBoxPage createMail(String address, String subject, String bodyText){
        createEmailButton.click();

        mailFieldTo.click();
        mailFieldTo.sendKeys(address);

        mailInputSubject.click();
        mailInputSubject.sendKeys(subject);

        driver.switchTo().frame(mailFieldBodyIFrame);

        mailFieldBody.click();
        mailFieldBody.clear();
        mailFieldBody.sendKeys(bodyText);

        driver.switchTo().defaultContent();

        saveMailButton.click();

        WebDriverWait waitForSaveSuccessNotification = new WebDriverWait(driver, TIME_OUT);
        waitForSaveSuccessNotification.until(ExpectedConditions.elementToBeClickable(notificationStatusDraftLink));
        return new MailBoxPage(driver);
    }

    public MailBoxPage openDraft(){
        driver.switchTo().defaultContent();
        draftFolder.click();
        return new MailBoxPage(driver);
    }

    public MailBoxPage clearMails(){
        draftFolder.sendKeys(Keys.CONTROL, "a");
        return new MailBoxPage(driver);
    }

    public MailBoxPage openSent(){
        driver.switchTo().defaultContent();
        sentFolder.click();
        WebDriverWait waitForText = new WebDriverWait(driver, TIME_OUT);
        waitForText.until(ExpectedConditions.elementToBeClickable(textToWaitSentFolder));
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
        return addressFieldInDraftMail.getAttribute("value");
    }

    public String getSubjectFromMail(){
        return subjectFieldInDraftMail.getAttribute("value");
    }

    public String getBodyFromMail(){
        driver.switchTo().frame(mailFieldBodyIFrame);
        return mailFieldBody.getText();
    }

    public MailBoxPage sendMail(){
        sendMailButton.click();
        String handle = driver.getWindowHandle();
        driver.switchTo().window(handle);
        popUpSubmitSendMail.click();

        WebDriverWait waitForNotify = new WebDriverWait(driver, TIME_OUT);
        waitForNotify.until(ExpectedConditions.elementToBeClickable(goToInboxLinkAfterMailSent));
        return new MailBoxPage(driver);
    }

    public boolean isEmpty(){
        return isElementPresent(By.xpath("//div[text()='someEmail@yandex.ru']"));
    }

    public boolean isElementPresent(By by){
        return !driver.findElements(by).isEmpty();
    }

    public HomePage logout(){
        logoutLink.click();
        return new HomePage(driver);
    }
}