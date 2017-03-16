package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import setup.Browser;
import setup.WebDriverTypes;

public class JSActionUtils {

    private static String backgroundColor;
    private static WebDriver driver = Browser.getWrappedDriver(WebDriverTypes.FIREFOX);
    private static JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

    public static void clickSignInButton() {
        jsExecutor.executeScript("document.getElementById('mailbox__auth__button').click()");
    }

    public static void highlightElement(WebElement element) {
        backgroundColor = element.getCssValue("backgroundColor");
        jsExecutor.executeScript("arguments[0].style.backgroundColor = '" + "green" + "'", element);
    }

    public static void unHighlightElement(WebElement element) {
        jsExecutor.executeScript("arguments[0].style.backgroundColor = '" + backgroundColor + "'", element);
    }

}