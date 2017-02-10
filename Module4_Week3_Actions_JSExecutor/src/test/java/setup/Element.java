package setup;

import action.JSAction;
import action.TakeScreenshot;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Element {

    private WebDriver driver;

    private By by;
    private TakeScreenshot takeScreenshot;

    public Element(By by) {
        this.by = by;
    }

    public By getBy() {
        return by;
    }

    public WebElement getWrappedWebElement() {

        return Browser.getBrowser().findElement(by);
    }

    public void click() {
        WebElement element = getWrappedWebElement();
        element.click();
    }

    public void clickSignInButtonWithJS() {
        JSAction.clickSignInButton();
    }

    public void typeValue(String value){
        WebElement element = getWrappedWebElement();
        JSAction.highlightElement(element);

        element.clear();
        element.sendKeys(value);

        takeScreenshot = new TakeScreenshot();
        takeScreenshot.takeScreenshot();

        JSAction.unHighlightElement(element);
    }

    public void clickCtrlAOnKeyboard() {
        WebElement element = getWrappedWebElement();
        element.sendKeys(Keys.CONTROL, "a");
    }

    public void clickDeleteOnKeyboard(){
        WebElement element = getWrappedWebElement();
        element.sendKeys(Keys.DELETE);
    }

    public boolean isDisplayed(){
        return getWrappedWebElement().isDisplayed();
    }

    public boolean isPresent(){
       return Browser.getBrowser().findElements(by).size() > 0;
    }

    public boolean isNotPresent(){
        return Browser.getBrowser().findElements(by).size() <= 0;
    }

    public String getText(){
        return getWrappedWebElement().getText();
    }

    public String getAttributeValue(String value){
        return getWrappedWebElement().getAttribute(value);
    }
}