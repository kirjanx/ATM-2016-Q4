package page_object;

import business_object.letter.Letter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.DriverFactory;
import setup.Element;

public class DraftPage extends Page {

    private WebDriverWait webDriverWait = new WebDriverWait(driver, DriverFactory.WEBDRIVER_WAIT_TIME_OUT);

    private static final String DRAFT_FOLDER_MAIL_FIELD_LOCATOR_PATTERN = "//div[text()='%s']";
    private Element draftFolderMailField;

    private static final By WAIT_TEXT_SENT_FOLDER_LOCATOR = By.xpath("//div[@class='b-kav-protected']");
    private static final By SENT_FOLDER_LINK_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_send']");
    private static final By DRAFT_FOLDER_LINK_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_drafts']");
    private static final By TRASH_ICON_IN_CONTEXT_MENU_LOCATOR = By.xpath("//i[@class='ico ico_folder ico_folder_trash']");
    private static final By TRASH_ICON_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_trash']");
    private static final By EMAIL_FIELD_TO_LOCATOR = By.xpath("//textarea[@class='js-input compose__labels__input' and @data-original-name='To']");

    Element sentFolderLink = new Element(SENT_FOLDER_LINK_LOCATOR);
    Element draftFolderLink = new Element(DRAFT_FOLDER_LINK_LOCATOR);
    Element trashIconInContextMenuLink = new Element(TRASH_ICON_IN_CONTEXT_MENU_LOCATOR);

    public DraftPage(WebDriver driver) {
        super(driver);
    }

    public void initializeDraftFolderMailFieldElement(Letter letter) {
        By DRAFT_FOLDER_MAIL_FIELD_LOCATOR = By.xpath(
                String.format(DRAFT_FOLDER_MAIL_FIELD_LOCATOR_PATTERN, letter.getAddress()));
        draftFolderMailField = new Element(DRAFT_FOLDER_MAIL_FIELD_LOCATOR);
    }

    public String getMailAddressFromDraftPage(Letter letter) {
        initializeDraftFolderMailFieldElement(letter);
        return draftFolderMailField.getText();
    }

    public DraftPage openDraftPage() {
        driver.switchTo().defaultContent();
        draftFolderLink.click();
        return new DraftPage(driver);
    }

    public ComposeMailPage openMailFromDraft(Letter letter) {
        initializeDraftFolderMailFieldElement(letter);
        draftFolderMailField.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable
                (driver.findElement(EMAIL_FIELD_TO_LOCATOR)));
        return new ComposeMailPage(driver);
    }

    public boolean isDraftFolderHasLetter(Letter letter) {
        initializeDraftFolderMailFieldElement(letter);
        return draftFolderMailField.isPresent();
    }

    public SentPage openSentPage() {
        driver.switchTo().defaultContent();
        sentFolderLink.click();
        return new SentPage(driver);
    }

    public InboxPage clearDraftMail() {
        sentFolderLink.clickCtrlAOnKeyboard();
        sentFolderLink.clickDeleteOnKeyboard();
        return new InboxPage(driver);
    }

    public DraftPage openContextMenu(Letter letter) {
        initializeDraftFolderMailFieldElement(letter);
        draftFolderMailField.contextClick();
        return new DraftPage(driver);
    }

    public DraftPage deleteLetterByContextMenu() {
        String windowHandle = driver.getWindowHandle();
        driver.switchTo().window(windowHandle);
        trashIconInContextMenuLink.click();
        return new DraftPage(driver);
    }

    public DraftPage dragAndDropDraftMail(Letter letter) {
        By DRAFT_FOLDER_MAIL_FIELD_LOCATOR = By.xpath(
                String.format(DRAFT_FOLDER_MAIL_FIELD_LOCATOR_PATTERN, letter.getAddress()));
        draftFolderMailField = new Element(DRAFT_FOLDER_MAIL_FIELD_LOCATOR);

        new Actions(driver).dragAndDrop(driver.findElement(DRAFT_FOLDER_MAIL_FIELD_LOCATOR),
                driver.findElement(TRASH_ICON_LOCATOR)).build().perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(WAIT_TEXT_SENT_FOLDER_LOCATOR)));
        return new DraftPage(driver);
    }
}