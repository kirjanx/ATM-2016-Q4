package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import setup.Element;

public class SentPage extends Page{

    private static final By SENT_PAGE_MAIL_FIELD_LOCATOR = By.xpath("//div[text()='someEmail@yandex.ru']");
    private static final By INBOX_PAGE_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_inbox']");
    private static final By SENT_PAGE_LOCATOR = By.xpath("//i[@class='ico ico_folder ico ico_folder_send']");

    Element sentPageMailField = new Element(SENT_PAGE_MAIL_FIELD_LOCATOR);
    Element inboxPage = new Element(INBOX_PAGE_LOCATOR);
    Element sentPage = new Element(SENT_PAGE_LOCATOR);

    public SentPage(WebDriver driver) {
        super(driver);
    }

    public boolean isNotSentEmpty(){
        return sentPageMailField.isPresent();
    }

    public InboxPage clearSentMail(){
        inboxPage.clickCtrlAOnKeyboard();
        inboxPage.clickDeleteOnKeyboard();
        return new InboxPage(driver);
    }
}