package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Browser {
    private static final int IMPLICIT_WAIT = 20;
    private static final int PAGE_LOAD_TIMEOUT = 20;
    public static final int WEBDRIVER_WAIT_TIME_OUT = 3;

    private static WebDriver driver;

    public static WebDriver getBrowser(String browserType) {
        new Browser(browserType);
        return driver;
    }

    private Browser(String browserType) {
        if (driver == null) {
            driver = getDriver(browserType);
        }
    }

    private static WebDriver getDriver(String browserType) {
        driver = new FirefoxDriver();
        prepareFirefoxDriver();
        return driver;
    }

    private static void prepareFirefoxDriver() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}