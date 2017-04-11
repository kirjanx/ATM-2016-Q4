package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import setup.Element;

public class SentPage extends Page {

    private static final By INBOX_PAGE_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_inbox']");
    private Element inboxPage = new Element(INBOX_PAGE_LOCATOR);

    private static final By SENT_FOLDER_ADDRESS_FIELD_LOCATOR = By.xpath("//a[@class='js-href b-datalist__item__link']");
    private Element sentFolderAddressField = new Element(SENT_FOLDER_ADDRESS_FIELD_LOCATOR);

    public SentPage(WebDriver driver) {
        super(driver);
    }

    public String getMailAddressFromSentFolder() {
        return sentFolderAddressField.getAttributeValue("title");
    }

    public InboxPage clearSentMailFolder() {
        inboxPage.clickCtrlAOnKeyboard();
        inboxPage.clickDeleteOnKeyboard();
        return new InboxPage(driver);
    }
}