package registration;

import api.User;
import api.UserClient;
import api.UserGenerator;
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
    private final UserGenerator generator = new UserGenerator();
    private final UserClient client = new UserClient();
    private String accessToken;
    private String email;
    private String password;
    private String name;
    User user = generator.randomData();

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

        email = user.getEmail();
        password = user.getPassword();
        name = user.getName();

        mainPage.clickPersonalCabinetButton();
        personalCabinetPage.clickRegistrationButton();
        registrationPage.inputName(name);
        registrationPage.inputEmail(email);
        registrationPage.inputPassword(password);
        registrationPage.clickRegistrationButton();
        loginPage.assertSignInButtonText();

        Response loginResponse = client.login(new User(email, password));
        accessToken = loginResponse.path("accessToken");
    }

    @Test
    @DisplayName("Проверка ошибки при регистрации с невалидным паролем менее 6 символов")
    public void registrationWithCorrectDataTest() {

        MainPage mainPage = new MainPage(driver);
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);

        email = user.getEmail();
        name = user.getName();
        user.setPassword(RandomStringUtils.randomAlphanumeric(4));
        password = user.getPassword();

        mainPage.clickPersonalCabinetButton();
        personalCabinetPage.clickRegistrationButton();
        registrationPage.inputName(name);
        registrationPage.inputEmail(email);
        registrationPage.inputPassword(password);
        registrationPage.clickRegistrationButton();
        registrationPage.getIncorrectPasswordText();

        Response loginResponse = client.login(new User(email, password));
        accessToken = loginResponse.path("accessToken");
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) {
            client.delete(accessToken);
        }
    }
}