package pom;

import org.hamcrest.MatcherAssert;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.Matchers.containsString;

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
    private final By makeBurgerText = By.xpath((".//h1[text()='Соберите бургер']"));
    //кнопка "Конструктор" в хэдере сайта
    private final By constructorButton = By.xpath(".//p[text()='Конструктор']");
    //логотип в хэдере сайта
    private final By logoButton = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    //раздел "Булки" в конструкторе
    private final By bunsSection = By.xpath(".//div/span[text()='Булки']");
    //раздел "Соусы" в конструкторе
    private final By saucesSection = By.xpath(".//div/span[text()='Соусы']");
    //раздел "Начинки" в конструкторе
    private final By fillingsSection = By.xpath(".//div/span[text()='Начинки']");
    //активная вкладка  //*[@id="root"]/div/main/section[1]/div[1]/div[2]   /span[@class='text text_type_main-default']
    private final By activeTab = By.xpath(".//div//[@class='tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect']");
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

    public void assertMakeOrderButtonText() {
        String text = driver.findElement(makeOrderButton).getText();
        Assert.assertEquals("Оформить заказ", text);
    }

    public void clickPersonalCabinetButton() {
        driver.findElement(personalCabinetButton).click();
    }

    public void assertMakeBurgerText() {
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

    public void assertBunsTabActive() {
        String text = driver.findElement(By.xpath(".//div[@style]/div[1]")).getAttribute("class");
        MatcherAssert.assertThat(text, containsString("tab_tab_type_current"));
    }

    public void assertSaucesTabActive() {
        String text = driver.findElement(By.xpath(".//div[@style]/div[2]")).getAttribute("class");
        MatcherAssert.assertThat(text, containsString("tab_tab_type_current"));
    }

    public void assertFillingsTabActive() {
        String text = driver.findElement(By.xpath(".//div[@style]/div[3]")).getAttribute("class");
        MatcherAssert.assertThat(text, containsString("tab_tab_type_current"));
    }
}