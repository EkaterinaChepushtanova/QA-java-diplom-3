package pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MainPage {
    // адрес сайта
    private final String url = "https://stellarburgers.nomoreparties.site/";
    //кнопка "Войти в аккаунт" на главной странице
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    // кнопка "Оформить заказ"
    private final By makeOrderButton = By.xpath((".//div/main/section[2]/div/button"));
    //кнопка "Личный кабинет"
    private final By personalCabinetButton = By.xpath((".//div/header/nav/a/p"));
    //текст "Соберите бургер"
    private final By makeBurgerText = By.xpath((".//div/main/section[1]/h1"));
    //кнопка "Конструктор" в хэдере сайта
    private final By constructorButton = By.xpath(".//div/header/nav/ul/li[1]/a/p");
    //логотип в хэдере сайта
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    //раздел "Булки" в конструкторе
    private final By bunsSection = By.xpath(".//div/span[text()='Булки']");
    //текст "Булки"
    private final By bunsText = By.xpath(".//div/h2[text()='Булки']");
    //раздел "Соусы" в конструкторе
    private final By saucesSection = By.xpath(".//div/span[text()='Соусы']");
    //текст "Соусы"
    private final By saucesText = By.xpath(".//div/h2[text()='Соусы']");
    //раздел "Начинки" в конструкторе
    private final By fillingsSection = By.xpath(".//div/span[text()='Начинки']");
    //текст "Начинки"
    private final By fillingsText = By.xpath(".//div/h2[text()='Начинки']");

    private final WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get(url);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void getButtonText() {
        String text = driver.findElement(makeOrderButton).getText();
        Assert.assertEquals("Оформить заказ", text);
    }

    public void clickPersonalCabinetButton() {
        driver.findElement(personalCabinetButton).click();
    }

    public void getMakeBurgerText() {
        String text = driver.findElement(makeBurgerText).getText();
        Assert.assertEquals("Соберите бургер", text);
    }

    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    public void clickLogoButton() {
        driver.findElement(logoButton).click();
    }

    public void clickBunsSection() {
        driver.findElement(bunsSection).click();
    }

    public void clickSaucesSection() {
        driver.findElement(saucesSection).click();
    }

    public void clickFillingsSection() {
        driver.findElement(fillingsSection).click();
    }

    public void getBunsText() {
        String text = driver.findElement(bunsText).getText();
        Assert.assertEquals("Булки", text);
    }

    public void getSaucesText() {
        String text = driver.findElement(saucesText).getText();
        Assert.assertEquals("Соусы", text);
    }

    public void getFillingsText() {
        String text = driver.findElement(fillingsText).getText();
        Assert.assertEquals("Начинки", text);
    }
}