package tests;

import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

@Epic("SauceDemo Tests")
@Feature("Проверка доступности")
@Owner("Шерикбекова А.Э.")
@Story("Основной функционал сайта")
public class SauceDemoAvailabilityTest extends BaseTest {

    @Test(description = "Проверка загрузки главной страницы SauceDemo")
    @Severity(SeverityLevel.BLOCKER)
    @Link(name = "SauceDemo", url = "https://www.saucedemo.com")
    public void testSauceDemoHomePage() {
        // Открываем реальный SauceDemo
        driver.get("https://www.saucedemo.com/");

        // Проверяем ключевые элементы
        boolean isLogoDisplayed = driver.findElement(By.className("login_logo")).isDisplayed();
        boolean isLoginButtonDisplayed = driver.findElement(By.id("login-button")).isDisplayed();
        boolean isUsernameFieldDisplayed = driver.findElement(By.id("user-name")).isDisplayed();

        Assert.assertTrue(isLogoDisplayed, "Логотип Swag Labs должен отображаться");
        Assert.assertTrue(isLoginButtonDisplayed, "Кнопка Login должна отображаться");
        Assert.assertTrue(isUsernameFieldDisplayed, "Поле для логина должно отображаться");

        System.out.println("✅ SauceDemo.com загружен корректно");
    }

    @Test(description = "Быстрая проверка логина с тестовыми данными")
    @Severity(SeverityLevel.CRITICAL)
    public void testQuickLogin() {
        driver.get("https://www.saucedemo.com/");

        // Заполняем форму (стандартный пользователь)
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // Проверяем что перешли на страницу товаров
        boolean isProductsPage = driver.getCurrentUrl().contains("inventory");
        boolean isShoppingCartVisible = driver.findElement(By.className("shopping_cart_link")).isDisplayed();

        Assert.assertTrue(isProductsPage, "После логина должна открыться страница товаров");
        Assert.assertTrue(isShoppingCartVisible, "Иконка корзины должна отображаться");

        System.out.println("✅ Быстрый логин на SauceDemo выполнен успешно");
    }
}