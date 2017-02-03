package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BaseClass {

    protected final WebDriver driver;

    public BaseClass(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }
}
