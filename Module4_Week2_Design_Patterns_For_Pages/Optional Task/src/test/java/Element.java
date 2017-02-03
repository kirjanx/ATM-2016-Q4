import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class Element {

    private By by;

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
        getWrappedWebElement().click();
    }

    public void typeValue(String value){
        WebElement element = getWrappedWebElement();
        element.clear();
        element.sendKeys(value);
    }

    public void useKeybordDelete(){
        getWrappedWebElement().sendKeys(Keys.DELETE);
    }

    public void useKeyboardCtrlA(){
        getWrappedWebElement().sendKeys(Keys.CONTROL, "a");
    }

    public boolean isDisplayed(){
        return getWrappedWebElement().isDisplayed();
    }

    public boolean isPresent(){
        return Browser.getBrowser().findElements(by).size() > 0;
    }

    public String getText(){
       return getWrappedWebElement().getText();
    }

    public String getAttributeValue(String value){
        return getWrappedWebElement().getAttribute(value);
    }
}