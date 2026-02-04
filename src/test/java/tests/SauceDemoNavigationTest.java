package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.saucedemo.LoginPage;
import pages.saucedemo.ProductsPage;

@Epic("SauceDemo Tests")
@Feature("Навигация")
@Owner("Шерикбекова А.Э.")
public class SauceDemoNavigationTest extends BaseTest {

    @Test(description = "Проверка заголовка страницы товаров")
    @Severity(SeverityLevel.MINOR)
    public void verifyProductsPageTitle() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver, wait);

        boolean isPageLoaded = productsPage.isPageLoaded();
        Assert.assertTrue(isPageLoaded, "Страница товаров должна загрузиться");

        String pageTitle = driver.findElement(
                org.openqa.selenium.By.className("title")
        ).getText();
        Assert.assertEquals(pageTitle, "Products",
                "Заголовок страницы должен быть 'Products'");
    }

    @Test(description = "Проверка наличия элементов на странице товаров")
    @Severity(SeverityLevel.MINOR)
    public void verifyProductsPageElements() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver, wait);

        // Проверяем наличие ключевых элементов
        boolean hasShoppingCart = productsPage.hasShoppingCartIcon();
        boolean hasInventoryItems = productsPage.hasInventoryItems();
        boolean hasSortFilter = productsPage.hasSortFilter();

        Assert.assertTrue(hasShoppingCart, "Должна быть иконка корзины");
        Assert.assertTrue(hasInventoryItems, "Должны быть товары");
        Assert.assertTrue(hasSortFilter, "Должен быть фильтр сортировки");
    }
}