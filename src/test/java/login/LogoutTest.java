package login;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pom.LoginPage;
import pom.MainPage;
import pom.PersonalCabinetPage;

import java.util.concurrent.TimeUnit;

public class LogoutTest {

    private WebDriver driver;
    public String email = "kate_310101@mail.ru";
    public String password = "qwerty";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/katerina/WebDriver/bin/chromedriver");
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        mainPage.open();
    }

    @Test
    @DisplayName("Проверка выхода из аккаунта по кнопке «Выйти» из личного кабинета")
    public void logoutTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);

        mainPage.clickLoginButton();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.clickSignInButton();
        mainPage.clickPersonalCabinetButton();
        personalCabinetPage.clickLogoutButton();
        loginPage.getSignInButtonText();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}