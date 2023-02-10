package registration;

import api.User;
import api.UserClient;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomStringUtils;
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

public class RegistrationTest {

    private WebDriver driver;
    UserClient client = new UserClient();
    public String email = "kate_310101@mail.ru";
    public String password = "qwerty";
    public String name = "Kate";
    public String newEmail = RandomStringUtils.randomAlphanumeric(8) + "@mail.ru";
    public String invalidPassword = "qwert";

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/katerina/WebDriver/bin/chromedriver");
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        mainPage.open();
    }
    @Test
    @DisplayName("Проверка регистрации с валидными данными")
    public void registrationWithInvalidPasswordTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        mainPage.clickPersonalCabinetButton();
        personalCabinetPage.clickRegistrationButton();
        registrationPage.inputName(name);
        registrationPage.inputEmail(newEmail);
        registrationPage.inputPassword(password);
        registrationPage.clickRegistrationButton();
        loginPage.getSignInButtonText();

        Response response = client.login(new User(newEmail, password));
        String accessToken = response.path("accessToken");
        client.delete(accessToken);
    }

    @Test
    @DisplayName("Проверка ошибки при регистрации с невалидным паролем менее 6 символов")
    public void registrationWithCorrectDataTest() {

        MainPage mainPage = new MainPage(driver);
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        mainPage.clickPersonalCabinetButton();
        personalCabinetPage.clickRegistrationButton();
        registrationPage.inputName(name);
        registrationPage.inputEmail(email);
        registrationPage.inputPassword(invalidPassword);
        registrationPage.clickRegistrationButton();
        registrationPage.getIncorrectPasswordText();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}