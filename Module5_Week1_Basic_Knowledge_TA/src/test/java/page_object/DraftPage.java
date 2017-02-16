package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.Browser;
import setup.Element;

public class DraftPage extends Page {

    private WebDriverWait webDriverWait = new WebDriverWait(driver, Browser.WEBDRIVER_WAIT_TIME_OUT);

    private static final By DRAFT_FOLDER_MAIL_FIELD_LOCATOR = By.xpath("//div[text()='someEmail@yandex.ru']");
    private static final By WAIT_TEXT_SENT_FOLDER_LOCATOR = By.xpath("//div[@class='b-kav-protected']");
    private static final By SENT_FOLDER_LINK_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_send']");
    private static final By DRAFT_FOLDER_LINK_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_drafts']");
    private static final By TRASH_ICON_IN_CONTEXT_MENU_LOCATOR = By.xpath("//i[@class='ico ico_folder ico_folder_trash']");
    private static final By TRASH_ICON_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_trash']");

    Element draftFolderMailField = new Element(DRAFT_FOLDER_MAIL_FIELD_LOCATOR);
    Element sentFolderLink = new Element(SENT_FOLDER_LINK_LOCATOR);
    Element draftFolderLink = new Element(DRAFT_FOLDER_LINK_LOCATOR);
    Element trashIconInContextMenuLink = new Element(TRASH_ICON_IN_CONTEXT_MENU_LOCATOR);

    public DraftPage(WebDriver driver) {
        super(driver);
    }

    public String getMailAddressFromDraftPage() {
        return draftFolderMailField.getText();
    }

    public DraftPage openDraftPage() {
        driver.switchTo().defaultContent();
        draftFolderLink.click();
        return new DraftPage(driver);
    }

    public ComposeMailPage openMailFromDraft() {
        draftFolderMailField.click();
        return new ComposeMailPage(driver);
    }

    public boolean isDraftFolderHasLetter() {
        return draftFolderMailField.isPresent();
    }

    public SentPage openSentPage() {
        driver.switchTo().defaultContent();
        sentFolderLink.click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(WAIT_TEXT_SENT_FOLDER_LOCATOR)));
        return new SentPage(driver);
    }

    public InboxPage clearDraftMail() {
        sentFolderLink.clickCtrlAOnKeyboard();
        sentFolderLink.clickDeleteOnKeyboard();
        return new InboxPage(driver);
    }

    public DraftPage openContextMenu() {
        draftFolderMailField.contextClick();
        return new DraftPage(driver);
    }

    public DraftPage deleteLetterByContextMenu() {
        String windowHandle = driver.getWindowHandle();
        driver.switchTo().window(windowHandle);
        trashIconInContextMenuLink.click();
        return new DraftPage(driver);
    }

    public DraftPage dragAndDropDraftMail() {
        new Actions(driver).dragAndDrop(driver.findElement(DRAFT_FOLDER_MAIL_FIELD_LOCATOR),
                driver.findElement(TRASH_ICON_LOCATOR)).build().perform();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(WAIT_TEXT_SENT_FOLDER_LOCATOR)));
        return new DraftPage(driver);
    }
}