package util;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import setup.Browser;
import setup.WebDriverTypes;

import java.io.File;
import java.io.IOException;

public class ScreenshotUtils {

    private static WebDriver driver = Browser.getWrappedDriver(WebDriverTypes.FIREFOX);

    private static final String SCREENSHOT_PATH_NAME = "src/test/java/test/screenshots/";

    private static String generateRandomSubjectTitle() {
        return "Screenshot_" + RandomStringUtils.randomAlphanumeric(5) + ".png";
    }

    public void takeScreenshot() {
        File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(scrFile, new File(SCREENSHOT_PATH_NAME + generateRandomSubjectTitle()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}