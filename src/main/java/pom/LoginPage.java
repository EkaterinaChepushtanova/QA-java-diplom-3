package pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    //поле для ввода Email
    private final By emailField = By.xpath(".//div/form/fieldset[1]//input");
    //поле для ввода Пароля
    private final By passwordField = By.xpath(".//div/form/fieldset[2]//input");
    //кнопка "Войти"
    private final By signInButton = By.xpath(".//button[text()='Войти']");
    private final WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void inputEmail(String text) {
        driver.findElement(emailField).sendKeys(text);
    }

    public void inputPassword(String text) {
        driver.findElement(passwordField).sendKeys(text);
    }

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
    }

    public void getSignInButtonText() {
        String text = driver.findElement(signInButton).getText();
        Assert.assertEquals("Войти", text);
    }
}