import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    private WebDriver driver;

    @FindBy(xpath = "//input[@id='mailbox__login']")
    private WebElement loginInput;

    @FindBy(xpath = "//input[@id='mailbox__password']")
    private WebElement passwordInput;

    @FindBy(xpath = "//input[@id='mailbox__auth__button']")
    private WebElement signInButton;

    @FindBy(xpath = "//a[@id='PH_authLink']")
    private WebElement enterLink;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(this.driver, this);
    }

    public MailBoxPage loginMailBox(String login, String pwd){
        loginInput.clear();
        loginInput.sendKeys(login);

        passwordInput.clear();
        passwordInput.sendKeys(pwd);

        signInButton.click();
        return new MailBoxPage(driver);
    }

    public boolean isLogoutSuccessfull(){
        return enterLink.isDisplayed();
    }
}