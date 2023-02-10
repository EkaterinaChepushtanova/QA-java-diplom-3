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
import pom.RegistrationPage;

import java.util.concurrent.TimeUnit;

public class LoginTest {

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
    @DisplayName("Проверка входа по кнопке «Войти в аккаунт» на главной")
    public void loginThroughSignInButtonTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginButton();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.clickSignInButton();
        mainPage.getButtonText();
    }

    @Test
    @DisplayName("Проверка входа через кнопку «Личный кабинет»")
    public void loginThroughPersonalCabinetButtonTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickPersonalCabinetButton();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.clickSignInButton();
        mainPage.getButtonText();
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме регистрации")
    public void loginThroughRegistrationButtonTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        mainPage.clickLoginButton();
        personalCabinetPage.clickRegistrationButton();
        registrationPage.clickSignInButton();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.clickSignInButton();
        mainPage.getButtonText();
    }

    @Test
    @DisplayName("Проверка входа через кнопку в форме восстановления пароля")
    public void loginThroughRecoverPasswordButtonTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        mainPage.clickPersonalCabinetButton();
        registrationPage.clickRecoverPasswordButton();
        registrationPage.clickSignInButton();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.clickSignInButton();
        mainPage.getButtonText();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}