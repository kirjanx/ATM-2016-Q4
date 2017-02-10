package setup;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class Browser {
    private static final int IMPLICIT_WAIT = 20;
    private static final int PAGE_LOAD_TIMEOUT = 20;

    private static WebDriver driver;

    public static WebDriver getBrowser() {
        new Browser();
        return driver;
    }

    private Browser() {
        if (driver == null) {
            driver = getDriver();
        }
    }

    //Launch with GRID
    private static WebDriver getDriver() {
        //IE
//        System.setProperty("webdriver.ie.driver", "d:\\Selenium\\IEDriverServer.exe");
//        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();

        //CHROME
//        System.setProperty("webdriver.chrome.driver", "d:\\Selenium\\chromedriver.exe");
//        DesiredCapabilities capabilities = DesiredCapabilities.chrome();

        //FIREFOX
        System.setProperty("webdriver.gecko.driver", "d:\\Selenium\\geckodriver.exe");
		DesiredCapabilities capabilities = DesiredCapabilities.firefox();

		capabilities.setPlatform(Platform.WINDOWS);
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        prepareDriver();
        return driver;
    }

    //Launch without GRID
//    private static WebDriver getDriver() {
//        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//        capabilities.setCapability(FirefoxDriver.BINARY, "c:\\Users\\Kiryl_Parkhomenka\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
//        driver = new FirefoxDriver(capabilities);
//        driver = new ChromeDriver();
//        prepareDriver();
//        return driver;
//   }

    public static void prepareDriver(){
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }
}