package page_object;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import setup.Element;

public class InboxPage extends Page {

    private static final By USER_EMAIL_STRING_AFTER_LOGIN_LOCATOR = By.xpath("//i[@id='PH_user-email']");
    private static final By LOGOUT_LINK_LOCATOR = By.xpath("//a[@id='PH_logoutLink']");
    private static final By AGENT_LOCATOR = By.xpath("//span[@class='nwa-button-tab__nick']");

    private Element isUserMailStringAfterLogin = new Element(USER_EMAIL_STRING_AFTER_LOGIN_LOCATOR);
    private Element logoutLink = new Element(LOGOUT_LINK_LOCATOR);
    private Element agent = new Element(AGENT_LOCATOR);

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserMailAfterLoginDisplayed() {
        agent.isDisplayed();
        return isUserMailStringAfterLogin.isDisplayed();
    }

    public HomePage performLogout() {
        logoutLink.click();
        return new HomePage(driver);
    }
}