package util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import setup.Browser;

public class JSActionUtils {

    private static String backgroundColor;
    private static WebDriver driver = Browser.getBrowser("firefox");

    private static JavascriptExecutor jsExecutor;

    public static void clickSignInButton() {
        jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("document.getElementById('mailbox__auth__button').click()");
    }

    public static void highlightElement(WebElement element) {
        backgroundColor = element.getCssValue("backgroundColor");
        jsExecutor = ((JavascriptExecutor) driver);
        jsExecutor.executeScript("arguments[0].style.backgroundColor = '" + "green" + "'", element);
    }

    public static void unHighlightElement(WebElement element) {
        jsExecutor = ((JavascriptExecutor) driver);
        jsExecutor.executeScript("arguments[0].style.backgroundColor = '" + backgroundColor + "'", element);
    }
}