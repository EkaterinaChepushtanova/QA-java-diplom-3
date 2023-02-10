package pom;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PersonalCabinetPage {

    //кнопка "Зарегистрироваться"
    private final By registrationButton = By.xpath(".//div/main//p[1]/a");
    //кнопка "История заказов" в личном кабинете
    private final By ordersHistoryButton = By.xpath(".//div/main/div/nav/ul/li[2]/a");
    //кнопка "Выход" в личном кабинете
    private final By logoutButton = By.xpath(".//div/main/div/nav/ul/li[3]/button");
    private final WebDriver driver;

    public PersonalCabinetPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public void getButtonText() {
        String text = driver.findElement(ordersHistoryButton).getText();
        Assert.assertEquals("История заказов", text);
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}