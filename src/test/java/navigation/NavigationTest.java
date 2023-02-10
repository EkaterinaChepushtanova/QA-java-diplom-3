package navigation;

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

public class NavigationTest {

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
        personalCabinetPage.getButtonText();
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
        mainPage.getMakeBurgerText();
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
        mainPage.getMakeBurgerText();
    }

    @Test
    @DisplayName("Проверка перехода к разделам «Булки», «Соусы», «Начинки»")
    public void moveToBunsSectionTest() {

        MainPage mainPage = new MainPage(driver);

        mainPage.clickSaucesSection();
        mainPage.getSaucesText();
        mainPage.clickFillingsSection();
        mainPage.getFillingsText();
        mainPage.clickBunsSection();
        mainPage.getBunsText();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}