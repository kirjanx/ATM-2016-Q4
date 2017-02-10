package page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import setup.Element;

public class InboxPage extends Page{

    private static final By USER_EMAIL_STRING_AFTER_LOGIN_LOCATOR = By.xpath("//i[@id='PH_user-email']");
    private static final By LOGOUT_LINK_LOCATOR = By.xpath("//a[@id='PH_logoutLink']");

    Element logoutLink = new Element(LOGOUT_LINK_LOCATOR);
    Element isUserMailStringAfterLogin = new Element(USER_EMAIL_STRING_AFTER_LOGIN_LOCATOR);

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserMailAfterLoginIsDisplayed(){
        return isUserMailStringAfterLogin.isDisplayed();
    }

    public HomePage logout(){
        logoutLink.click();
        return new HomePage(driver);
    }
}