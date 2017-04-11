package setup;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static setup.DriverFactory.getWebDriverInstance;

public class Browser {

    private static WebDriver driver;
    private static Logger logger = LogManager.getRootLogger();

    public static WebDriver getWrappedDriver(WebDriverTypes browserType) {
        new Browser(browserType);
        return driver;
    }

    private Browser(WebDriverTypes browserType) {
        if (driver == null) {
            try {
                driver = getWebDriverInstance(browserType);
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }
    }
}
