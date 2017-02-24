package setup;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import setup.exceptions.UnknownDriverTypeException;

import java.util.concurrent.TimeUnit;

public class DriverFactory {

    private static WebDriver driver;

    private static final int IMPLICIT_WAIT = 20;
    private static final int PAGE_LOAD_TIMEOUT = 20;
    public static final int WEBDRIVER_WAIT_TIME_OUT = 3;

    public static WebDriver getWebDriverInstance(WebDriverTypes type) throws Exception {
        switch (type) {
            case FIREFOX: {
                driver = new FirefoxDriver();
                break;
            }
            case IE: {
                System.setProperty("webdriver.ie.driver", "src/test/java/resource/driverbinaries/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            }
            case CHROME: {
                System.setProperty("webdriver.chrome.driver", "src/test/java/resource/driverbinaries/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            }
            default:
                throw new UnknownDriverTypeException("Unknown web driver specified: " + type);
        }
        prepareTimeouts();
        return driver;
    }

    public static WebDriver getFirefoxInstance() {
        driver = new FirefoxDriver();
        prepareTimeouts();
        return driver;
    }

    public static WebDriver getChromeInstance() {
        driver = new ChromeDriver();
        prepareTimeouts();
        return driver;
    }

    public static WebDriver getIEInstance() {
        driver = new InternetExplorerDriver();
        prepareTimeouts();
        return driver;
    }

    public static void prepareTimeouts() {
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}