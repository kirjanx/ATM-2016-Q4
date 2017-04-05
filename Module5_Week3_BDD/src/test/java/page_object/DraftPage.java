package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.DriverFactory;
import setup.Element;

public class DraftPage extends Page {

    private static final By WAIT_TEXT_SENT_FOLDER_LOCATOR = By.xpath("//div[@class='b-kav-protected']");
    private static final By SENT_FOLDER_LINK_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_send']");
    private static final By DRAFT_FOLDER_LINK_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_drafts']");
    private static final By TRASH_ICON_IN_CONTEXT_MENU_LOCATOR = By.xpath("//i[@class='ico ico_folder ico_folder_trash']");
    private static final By TRASH_ICON_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_trash']");
    private static final By EMAIL_FIELD_TO_LOCATOR = By.xpath("//textarea[@class='js-input compose__labels__input' and @data-original-name='To']");
    private static final By DRAFT_FOLDER_ADDRESS_FIELD_LOCATOR = By.xpath("//a[@class='js-href b-datalist__item__link']");
    private static final By CLICKABLE_ICON_LOCATOR = By.xpath("//div[@class='b-datalist__item__pic']");

    private Element sentFolderLink = new Element(SENT_FOLDER_LINK_LOCATOR);
    private Element draftFolderLink = new Element(DRAFT_FOLDER_LINK_LOCATOR);
    private Element trashIconInContextMenuLink = new Element(TRASH_ICON_IN_CONTEXT_MENU_LOCATOR);
    private Element draftFolderAddressField = new Element(DRAFT_FOLDER_ADDRESS_FIELD_LOCATOR);

    public DraftPage(WebDriver driver) {
        super(driver);
    }

    private void waitForElementToBeClickable(By locator) {
        new WebDriverWait(driver, DriverFactory.WEBDRIVER_WAIT_TIME_OUT).
                until(ExpectedConditions.elementToBeClickable(driver.findElement(locator)));
    }

    public DraftPage openDraftFolder() {
        draftFolderLink.click();
        return new DraftPage(driver);
    }

    public String getMailAddressFromDraftFolder() {
        return draftFolderAddressField.getAttributeValue("title");
    }

    public ComposeMailPage openMailFromDraftFolder() {
        draftFolderAddressField.click();
        waitForElementToBeClickable(EMAIL_FIELD_TO_LOCATOR);
        return new ComposeMailPage(driver);
    }

    public boolean isDraftFolderHasLetter() {
        return draftFolderAddressField.isPresent();
    }

    public SentPage openSentFolder() {
        driver.switchTo().defaultContent();
        sentFolderLink.click();
        waitForElementToBeClickable(CLICKABLE_ICON_LOCATOR);
        return new SentPage(driver);
    }

    public InboxPage clearDraftFolderMail() {
        sentFolderLink.clickCtrlAOnKeyboard();
        sentFolderLink.clickDeleteOnKeyboard();
        return new InboxPage(driver);
    }

    public DraftPage useContextMenu() {
        draftFolderAddressField.contextClick();
        return new DraftPage(driver);
    }

    public DraftPage deleteLetterUsingContextMenu() {
        String windowHandle = driver.getWindowHandle();
        driver.switchTo().window(windowHandle);
        trashIconInContextMenuLink.click();
        return new DraftPage(driver);
    }

    public DraftPage dragAndDropMail() {
        new Actions(driver).dragAndDrop(driver.findElement(DRAFT_FOLDER_ADDRESS_FIELD_LOCATOR),
                driver.findElement(TRASH_ICON_LOCATOR)).build().perform();
        waitForElementToBeClickable(WAIT_TEXT_SENT_FOLDER_LOCATOR);
        return new DraftPage(driver);
    }
}