package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import setup.Element;

public class DraftPage extends Page{

    public static final int WEBDRIVER_WAIT_TIME_OUT = 3;
    private WebDriverWait webDriverWait;

    private static final By DRAFT_FOLDER_MAIL_FIELD_LOCATOR = By.xpath("//div[text()='someEmail@yandex.ru']");
    private static final By WAIT_TEXT_SENT_FOLDER_LOCATOR = By.xpath("//div[@class='b-kav-protected']");
    private static final By SENT_PAGE_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_send']");

    Element draftPageMailField = new Element(DRAFT_FOLDER_MAIL_FIELD_LOCATOR);
    Element sentPage = new Element(SENT_PAGE_LOCATOR);

    public DraftPage(WebDriver driver) {
        super(driver);
    }

    public String getMailAddressFromDraftPage(){
        return draftPageMailField.getText();
    }

    public ComposeMailPage openMailFromDraft(){
        draftPageMailField.click();
        return new ComposeMailPage(driver);
    }

    public boolean isDraftEmpty(){
        return draftPageMailField.isNotPresent();
    }

    public SentPage openSentPage(){
        driver.switchTo().defaultContent();
        sentPage.click();
        webDriverWait = new WebDriverWait(driver, WEBDRIVER_WAIT_TIME_OUT);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(driver.findElement(WAIT_TEXT_SENT_FOLDER_LOCATOR)));
        return new SentPage(driver);
    }

    public InboxPage clearDraftMail(){
        sentPage.clickCtrlAOnKeyboard();
        sentPage.clickDeleteOnKeyboard();
        return new InboxPage(driver);
    }
}