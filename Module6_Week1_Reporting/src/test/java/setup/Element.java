package setup;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import util.JSActionUtils;
import util.ScreenshotUtils;

public class Element {

    private static WebDriver driver = Browser.getWrappedDriver(WebDriverTypes.FIREFOX);

    private By by;
    private ScreenshotUtils screenshotUtils;

    public Element(By by) {
        this.by = by;
    }

    public By getBy() {
        return by;
    }

    public WebElement getWrappedWebElement() {
        return driver.findElement(by);
    }

    public void click() {
        getWrappedWebElement().click();
    }

    public void clickSignInButtonWithJS() {
        JSActionUtils.clickSignInButton();
    }

    public void typeValue(String value) {
        WebElement element = getWrappedWebElement();
        JSActionUtils.highlightElement(element);

        element.clear();
        element.sendKeys(value);

        screenshotUtils = new ScreenshotUtils();
        screenshotUtils.takeScreenshot();

        JSActionUtils.unHighlightElement(element);
    }

    public void clickCtrlAOnKeyboard() {
        getWrappedWebElement().sendKeys(Keys.CONTROL, "a");
    }

    public void clickDeleteOnKeyboard() {
        getWrappedWebElement().sendKeys(Keys.DELETE);
    }

    public boolean isDisplayed() {
        return getWrappedWebElement().isDisplayed();
    }

    public boolean isPresent() {
        return driver.findElements(by).size() > 0;
    }

    public String getText() {
        return getWrappedWebElement().getText();
    }

    public String getAttributeValue(String value) {
        return getWrappedWebElement().getAttribute(value);
    }

    public void contextClick() {
        new Actions(driver).contextClick(getWrappedWebElement()).build().perform();
    }
}