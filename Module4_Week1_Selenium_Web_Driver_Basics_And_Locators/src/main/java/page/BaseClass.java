package page;

import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseClass {

    protected WebDriver driver;

    public BaseClass(WebDriver driver){
        this.driver = driver;

        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);////move timeouts to constants
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        driver.manage().window().maximize();
    }

    public void closeBrowser() {
        this.driver.quit();
    }

}
