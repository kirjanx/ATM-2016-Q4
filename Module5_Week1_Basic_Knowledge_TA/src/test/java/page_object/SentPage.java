package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import setup.Element;

public class SentPage extends Page {

    private static final By SENT_FOLDER_MAIL_FIELD_LOCATOR = By.xpath("//div[text()='someEmail@yandex.ru']");
    private static final By INBOX_PAGE_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_inbox']");

    Element sentFolderMailField = new Element(SENT_FOLDER_MAIL_FIELD_LOCATOR);
    Element inboxPage = new Element(INBOX_PAGE_LOCATOR);

    public SentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isSentFolderHasLetter() {
        return sentFolderMailField.isPresent();
    }

    public InboxPage clearSentMail() {
        inboxPage.clickCtrlAOnKeyboard();
        inboxPage.clickDeleteOnKeyboard();
        return new InboxPage(driver);
    }
}