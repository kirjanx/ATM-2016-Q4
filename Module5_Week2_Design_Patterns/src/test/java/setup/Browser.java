package setup;

import org.openqa.selenium.WebDriver;

import static setup.DriverFactory.getWebDriverInstance;

public class Browser {

    private static WebDriver driver;

    public static WebDriver getWrappedDriver(WebDriverTypes browserType) {
        new Browser(browserType);
        return driver;
    }

    private Browser(WebDriverTypes browserType) {
        if (driver == null) {
            try {
                driver = getWebDriverInstance(browserType);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}