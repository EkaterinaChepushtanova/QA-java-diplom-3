package pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPage {
    //кнопка "Войти"
    private final By signInButton = By.xpath(".//div/main//p/a");
    //кнопка "Восстановить пароль"
    private final By recoverPasswordButton = By.xpath(".//div/main//p[2]/a");
    //поле "Имя"
    private final By nameField = By.xpath(".//fieldset[1]//input");
    //поле "Email"
    private final By emailField = By.xpath(".//fieldset[2]//input");
    //поле "Пароль"
    private final By passwordField = By.xpath(".//fieldset[3]//input");
    //текст "Некорректный пароль"
    private final By incorrectPasswordText = By.xpath(".//p[text()='Некорректный пароль']");
    //кнопка "Зарегистрироваться"
    private final By registrationButton = By.xpath(".//div/main/div/form/button");
    private final WebDriver driver;

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
    }

    public void inputName(String text) {
        driver.findElement(nameField).sendKeys(text);
    }

    public void inputEmail(String text) {
        driver.findElement(emailField).sendKeys(text);
    }

    public void inputPassword(String text) {
        driver.findElement(passwordField).sendKeys(text);
    }

    public void getIncorrectPasswordText() {
        String text = driver.findElement(incorrectPasswordText).getText();
        Assert.assertEquals("Некорректный пароль", text);
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }
}