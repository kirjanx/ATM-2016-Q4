package action;

import org.apache.commons.lang3.RandomStringUtils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TakeScreenshot {

    public static final String SCREENSHOT_PATH_NAME = "src/test/java/test/testOutput/";
    public  String ScreenshotTitle = TakeScreenshot.generateRandomSubjectTitle();

    public static String generateRandomSubjectTitle(){
        return "Screenshot_" + RandomStringUtils.randomAlphanumeric(5) + ".png";
    }

    public void takeScreenshot(){
        BufferedImage image = null;
        try {
            image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
        } catch (AWTException e) {
            e.printStackTrace();
        }
        try {
            ImageIO.write(image, "png", new File(SCREENSHOT_PATH_NAME + ScreenshotTitle));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}