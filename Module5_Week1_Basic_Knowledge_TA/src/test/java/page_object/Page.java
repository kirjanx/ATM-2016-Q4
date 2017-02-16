package page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class Page {

    protected final WebDriver driver;

    public Page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}