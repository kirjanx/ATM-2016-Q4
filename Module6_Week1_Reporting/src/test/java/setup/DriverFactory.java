package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import setup.exceptions.UnknownDriverTypeException;
import util.webDriverDecorator.CustomDecorator;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;

    private static final String IE_DRIVER_PATH = "src/test/java/resource/driverbinaries/IEDriverServer.exe";
    private static final String CHROME_DRIVER_PATH = "src/test/java/resource/driverbinaries/chromedriver.exe";

    private static final int IMPLICIT_WAIT = 30;
    private static final int PAGE_LOAD_TIMEOUT = 30;
    public static final int WEBDRIVER_WAIT_TIME_OUT = 5;

    public static WebDriver getWebDriverInstance(WebDriverTypes type) {
        switch (type) {
            case FIREFOX: {
                driver = getFirefoxInstance();
                break;
            }
            case CHROME: {
                driver = getChromeInstance();
                break;
            }
            case IE: {
                driver = getIEInstance();
                break;
            }
            default:
                throw new UnknownDriverTypeException("Unknown web driver specified: " + type);
        }
        prepareTimeouts();
        driver = new CustomDecorator(driver);
        return driver;
    }

    public static WebDriver getFirefoxInstance() {
        driver = new FirefoxDriver();
        prepareTimeouts();
        return driver;
    }

    public static WebDriver getChromeInstance() {
        System.setProperty("webdriver.chrome.driver", CHROME_DRIVER_PATH);
        driver = new ChromeDriver();
        prepareTimeouts();
        return driver;
    }

    public static WebDriver getIEInstance() {
        System.setProperty("webdriver.ie.driver", IE_DRIVER_PATH);
        driver = new InternetExplorerDriver();
        prepareTimeouts();
        return driver;
    }

    private static void prepareTimeouts() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}