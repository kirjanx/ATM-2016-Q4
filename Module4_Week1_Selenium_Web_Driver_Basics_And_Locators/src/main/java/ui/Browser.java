package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.util.concurrent.TimeUnit;

public class Browser {

    private static final int IMPLICITY_WAIT = 30;
    private static final int PAGE_LOAD_TIMEOUT = 60;

    private static WebDriver driver;

    private Browser() {
        if (driver == null) {
            driver = getDriver();
        }
    }

    public static WebDriver getBrowser() {
        new Browser();
        return driver;
    }

    private static WebDriver getDriver() {
        driver = new FirefoxDriver();
        prepareDriver();
        return driver;
    }

    public static void prepareDriver(){
        driver.manage().timeouts().implicitlyWait(IMPLICITY_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}
