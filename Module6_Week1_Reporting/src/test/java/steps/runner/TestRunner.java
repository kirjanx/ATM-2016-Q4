package steps.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import setup.Browser;
import setup.WebDriverTypes;

@CucumberOptions(
        features = {"src/test/resources/features"},
        glue = "steps"
)

public class TestRunner extends AbstractTestNGCucumberTests {

    private WebDriver driver;
    private static final String BASE_URL = "https://mail.ru/";

    @BeforeClass(description = "Start browser and initialize pages")
    public void setUpBefore() {
        driver = Browser.getWrappedDriver(WebDriverTypes.FIREFOX);
        driver.get(BASE_URL);
    }

    @AfterClass(description = "Close browser")
    public void tearDown() {
        driver.quit();
    }
}