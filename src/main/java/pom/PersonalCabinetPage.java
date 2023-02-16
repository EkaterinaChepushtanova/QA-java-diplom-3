package pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalCabinetPage {
    //кнопка "Зарегистрироваться"
    private final By registrationButton = By.xpath(".//a[text()='Зарегистрироваться']");
    //кнопка "История заказов" в личном кабинете
    private final By ordersHistoryButton = By.xpath(".//a[text()='История заказов']");
    //кнопка "Выход" в личном кабинете
    private final By logoutButton = By.xpath(".//button[text()='Выход']");
    private final WebDriver driver;

    public PersonalCabinetPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void assertOrdersHistoryButtonText() {
        String text = driver.findElement(ordersHistoryButton).getText();
        Assert.assertEquals("История заказов", text);
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}