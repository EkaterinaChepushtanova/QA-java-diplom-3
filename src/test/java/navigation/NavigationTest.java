package navigation;

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

public class NavigationTest {

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
    @DisplayName("Проверка перехода по клику на «Личный кабинет»")
    public void moveToPersonalCabinetTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);
        PersonalCabinetPage personalCabinetPage = new PersonalCabinetPage(driver);

        mainPage.clickLoginButton();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.clickSignInButton();
        mainPage.clickPersonalCabinetButton();
        personalCabinetPage.assertOrdersHistoryButtonText();
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета по клику в «Конструктор»")
    public void moveToConstructorTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginButton();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.clickSignInButton();
        mainPage.clickPersonalCabinetButton();
        mainPage.clickConstructorButton();
        mainPage.assertMakeBurgerText();
    }

    @Test
    @DisplayName("Проверка перехода из личного кабинета по клику на логотип")
    public void moveToLogoTest() {

        MainPage mainPage = new MainPage(driver);
        LoginPage loginPage = new LoginPage(driver);

        mainPage.clickLoginButton();
        loginPage.inputEmail(email);
        loginPage.inputPassword(password);
        loginPage.clickSignInButton();
        mainPage.clickPersonalCabinetButton();
        mainPage.clickLogoButton();
        mainPage.assertMakeBurgerText();
    }

    @Test
    @DisplayName("Проверка перехода к разделам «Булки»")
    public void moveToBunsSectionsTest() {

        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingsSection();
        mainPage.clickBunsSection();
        mainPage.assertBunsTabActive();
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Соусы»")
    public void moveToSaucesSectionsTest() {

        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesSection();
        mainPage.assertSaucesTabActive();
    }

    @Test
    @DisplayName("Проверка перехода к разделу «Начинки»")
    public void moveToFillingsSectionsTest() {

        MainPage mainPage = new MainPage(driver);

        mainPage.clickFillingsSection();
        mainPage.assertFillingsTabActive();
    }

    @After
    public void tearDown() {
        driver.quit();
        if (accessToken != null) {
            client.delete(accessToken);
        }
    }
}