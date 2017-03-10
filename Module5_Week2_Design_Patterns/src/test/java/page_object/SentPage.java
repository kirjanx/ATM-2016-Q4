package page_object;

import business_object.letter.Letter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import setup.Element;

public class SentPage extends Page {

    private static final String SENT_FOLDER_MAIL_FIELD_LOCATOR_PATTERN = "//div[text()='%s']";
    private Element sentFolderMailField;

    private static final By INBOX_PAGE_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_inbox']");
    private Element inboxPage = new Element(INBOX_PAGE_LOCATOR);

    public SentPage(WebDriver driver) {
        super(driver);
    }

    public void getSentLetterAddress(Letter letter) {
        By SENT_FOLDER_MAIL_FIELD_LOCATOR = By.xpath(
                String.format(SENT_FOLDER_MAIL_FIELD_LOCATOR_PATTERN, letter.getAddress()));
        sentFolderMailField = new Element(SENT_FOLDER_MAIL_FIELD_LOCATOR);
    }

    public boolean isSentFolderHasLetter(Letter letter) {
        getSentLetterAddress(letter);
        return sentFolderMailField.isPresent();
    }

    public InboxPage clearSentMail() {
        inboxPage.clickCtrlAOnKeyboard();
        inboxPage.clickDeleteOnKeyboard();
        return new InboxPage(driver);
    }
}