package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.saucedemo.LoginPage;
import pages.saucedemo.MenuPage;
import pages.saucedemo.ProductsPage;

@Epic("SauceDemo Tests")
@Feature("Работа с корзиной")
@Owner("Шерикбекова А.Э.")
public class SauceDemoCartTest extends BaseTest {

    @Test(description = "Добавление товара в корзину")
    @Severity(SeverityLevel.CRITICAL)
    public void addProductToCartTest() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver, wait);
        productsPage.addFirstProductToCart();

        // Проверяем бейдж корзины
        try {
            String badgeText = wait.until(
                    org.openqa.selenium.support.ui.ExpectedConditions
                            .visibilityOfElementLocated(
                                    org.openqa.selenium.By.cssSelector(".shopping_cart_badge")
                            )
            ).getText();

            Assert.assertEquals(badgeText, "1", "В корзине должен быть 1 товар");
        } catch (Exception e) {
            // Альтернативная проверка
            String buttonText = driver.findElement(
                    org.openqa.selenium.By.xpath("(//button[contains(@id, 'add-to-cart')])[1]")
            ).getText();
            Assert.assertTrue(buttonText.contains("Remove"),
                    "Кнопка должна измениться на 'Remove'");
        }
    }

    @Test(description = "Выход из системы")
    @Severity(SeverityLevel.MINOR)
    public void logoutTest() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        MenuPage menuPage = new MenuPage(driver, wait);
        menuPage.logout();

        // Проверяем что вернулись на страницу логина
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        boolean isLoginFieldPresent = driver.findElements(
                org.openqa.selenium.By.id("user-name")
        ).size() > 0;

        Assert.assertTrue(isLoginFieldPresent,
                "Должно отображаться поле для логина");
    }
}