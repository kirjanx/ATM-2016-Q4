package util.webDriverDecorator;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.HasInputDevices;

public abstract class AbstractDecorator implements WebDriver, HasInputDevices, JavascriptExecutor, TakesScreenshot {

    protected WebDriver driver;

    public AbstractDecorator(WebDriver driver) {
        this.driver = driver;
    }
}
