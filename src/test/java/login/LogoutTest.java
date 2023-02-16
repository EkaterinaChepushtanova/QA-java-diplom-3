package login;

import api.User;
import api.UserClient;
import api.UserGenerator;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
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
    private final UserGenerator generator = new UserGenerator();
    private final UserClient client = new UserClient();
    private String accessToken;
    private String email;
    private String password;
    User user = generator.randomData();

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/katerina/WebDriver/bin/chromedriver");
        driver = new ChromeDriver();
        MainPage mainPage = new MainPage(driver);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        Response createResponse = client.create(user);
        accessToken = createResponse.path("accessToken");
        email = user.getEmail();
        password = user.getPassword();

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
        loginPage.assertSignInButtonText();
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) {
            client.delete(accessToken);
        }
    }
}