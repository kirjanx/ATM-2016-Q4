package test;

import com.codeborne.selenide.Condition;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Configuration.startMaximized;
import static com.codeborne.selenide.Configuration.timeout;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.WebDriverRunner.closeWebDriver;

public class MailRuTest {

    private static final String LOGIN = "tester.atm";
    private static final String PASSWORD = "q123456";
    private static final String MAIL_STRING = "tester.atm@mail.ru";
    private static final String BASE_URL = "https://mail.ru/";
    private static final String EMAIL_ADDRESS = "someEmail@yandex.ru";
    private static final String EMAIL_SUBJECT = "Test_Subject";

    @BeforeClass
    public void setUpBefore() {
        timeout = 20000;
        startMaximized = true;
    }

    @Test(description = "Login to Mail.ru mailbox")
    public void loginMailBox() {
        open(BASE_URL);
        $(By.xpath("//input[@id='mailbox__login']")).setValue(LOGIN);
        $(By.xpath("//input[@id='mailbox__password']")).setValue(PASSWORD).pressEnter();
        $(By.xpath("//i[@id='PH_user-email']")).shouldBe(Condition.visible).shouldHave(Condition.text(MAIL_STRING));
        System.out.println("Logged in successfully");
    }

    @Test(description = "Create new Email and send it", dependsOnMethods = "loginMailBox")
    public void createMailAndSaveAsDraft() {
        $(By.xpath("//a[@class='b-toolbar__btn js-shortcut' and @data-name='compose']")).pressEnter();
        $(By.xpath("//textarea[@class='js-input compose__labels__input' and @data-original-name='To']"))
                .setValue(EMAIL_ADDRESS);
        $(By.xpath("//input[@class='b-input' and @name='Subject']")).setValue(EMAIL_SUBJECT)
                .sendKeys(Keys.CONTROL, Keys.ENTER);
        $(By.xpath("//div[@id='MailRuConfirm']//div[3]/form//button[@type='submit']")).pressEnter();
        $(By.xpath("//div[@class='message-sent__title']")).shouldBe(Condition.visible);
        System.out.println("Mail was sent successfully");
    }

    @Test(description = "Logout Mail.ru mailbox", dependsOnMethods = "createMailAndSaveAsDraft")
    public void logoutMailBox() {
        $(By.xpath("//a[@id='PH_logoutLink']")).pressEnter();
        $(By.xpath("//a[@id='PH_authLink']")).shouldBe(Condition.visible).shouldBe(Condition.enabled);
        System.out.println("Logged out successfully");
    }

    @AfterClass
    public static void tearDown() {
        closeWebDriver();
    }
}