import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.remote.MobileCapabilityType;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;

import java.io.File;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class AndroidPageObjectTest_PageObjectLikeComplexElement {

    private WebDriver driver;
    private ApiDemosListViewScreenSimple apiDemosPageObject;

    @Before
    public void setUp() throws Exception {
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/src/main/resources/");
        File app = new File(appDir, "ApiDemos-debug.apk");
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Android Emulator");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

        apiDemosPageObject = new ApiDemosListViewScreenSimple();
        PageFactory.initElements(new AppiumFieldDecorator(driver.
                        findElement(By.id("android:id/list")) /*(!!!)*/
                        , 5, TimeUnit.SECONDS),
                apiDemosPageObject);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
    }

    /**
     * Page Object best practice is to describe interactions with target
     * elements by methods. These methods describe business logic of the page/screen.
     * Here test interacts with lazy instantiated elements directly.
     * It was done so just for obviousness
     */

    @Test
    public void findByElementsTest() {
        org.testng.Assert.assertNotEquals(0, apiDemosPageObject.textVieWs.size());
    }

    @Test
    public void findByElementTest() {
        org.testng.Assert.assertNotEquals(null, apiDemosPageObject.textView.getAttribute("text"));
    }

    @Test
    public void androidFindByElementsTest() {
        org.testng.Assert.assertNotEquals(0, apiDemosPageObject.androidTextViews.size());
    }

    @Test
    public void androidFindByElementTest() {
        org.testng.Assert.assertNotEquals(null, apiDemosPageObject.androidTextView.getAttribute("text"));
    }

    @Test
    public void checkThatElementsWereNotFoundByIOSUIAutomator() {
        Assert.assertEquals(0, apiDemosPageObject.iosTextViews.size());
    }

    @Test
    public void checkThatElementWasNotFoundByIOSUIAutomator() {
        NoSuchElementException nsee = null;
        try {
            apiDemosPageObject.iosTextView.getAttribute("text");
        } catch (Exception e) {
            nsee = (NoSuchElementException) e;
        }
        Assert.assertNotNull(nsee);
    }

    @Test
    public void androidOrIOSFindByElementsTest() {
        org.testng.Assert.assertNotEquals(0, apiDemosPageObject.androidOriOsTextViews.size());
    }

    @Test
    public void androidOrIOSFindByElementTest() {
        org.testng.Assert.assertNotEquals(null, apiDemosPageObject.androidOriOsTextView.getAttribute("text"));
    }

    @Test
    public void androidFindByUIAutomatorElementsTest() {
        org.testng.Assert.assertNotEquals(0, apiDemosPageObject.androidUIAutomatorViews.size());
    }

    @Test
    public void androidFindByUIAutomatorElementTest() {
        org.testng.Assert.assertNotEquals(null, apiDemosPageObject.androidUIAutomatorView.getAttribute("text"));
    }

    @Test
    public void areMobileElementsTest() {
        org.testng.Assert.assertNotEquals(0, apiDemosPageObject.mobileElementViews.size());
    }

    @Test
    public void isMobileElementTest() {
        org.testng.Assert.assertNotEquals(null, apiDemosPageObject.mobileElementView.getAttribute("text"));
    }

    @Test
    public void areMobileElements_FindByTest() {
        org.testng.Assert.assertNotEquals(0, apiDemosPageObject.mobiletextVieWs.size());
    }

    @Test
    public void isMobileElement_FindByTest() {
        org.testng.Assert.assertNotEquals(null, apiDemosPageObject.mobiletextVieW.getAttribute("text"));
    }

    @Test
    public void areRemoteElementsTest() {
        org.testng.Assert.assertNotEquals(0, apiDemosPageObject.remoteElementViews.size());
    }

    @Test
    public void isRemoteElementTest() {
        org.testng.Assert.assertNotEquals(null, apiDemosPageObject.remotetextVieW.getAttribute("text"));
    }
}