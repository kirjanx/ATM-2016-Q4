package ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Browser {

    private static WebDriver driver;

    private Browser() {
        if (driver == null) {
            driver = getDriver();
        }
    }

    public static WebDriver getInstance() {
        new Browser();
        return driver;
    }

    private static WebDriver getDriver() {
        return new FirefoxDriver();



    }

    //prepare

}
