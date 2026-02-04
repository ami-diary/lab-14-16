package pages.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class ProductsPage extends BasePage {
    private final By pageTitle = By.className("title");
    private final By shoppingCart = By.className("shopping_cart_link");
    private final By firstProductAddButton = By.xpath("(//button[contains(@id, 'add-to-cart')])[1]");
    private final By inventoryContainer = By.id("inventory_container");
    private final By productSortContainer = By.className("product_sort_container");

    public ProductsPage(WebDriver d, WebDriverWait w) {
        super(d, w);
    }

    @Step("Проверить загрузку страницы товаров")
    public boolean isPageLoaded() {
        return isVisible(pageTitle) &&
                getText(pageTitle).equals("Products");
    }

    @Step("Добавить первый товар в корзину")
    public void addFirstProductToCart() {
        click(firstProductAddButton);
    }

    @Step("Перейти в корзину")
    public void openCart() {
        click(shoppingCart);
    }

    @Step("Проверить наличие элементов на странице товаров")
    public boolean hasShoppingCartIcon() {
        return isVisible(shoppingCart);
    }

    @Step("Проверить наличие товаров")
    public boolean hasInventoryItems() {
        return isVisible(inventoryContainer);
    }

    @Step("Проверить наличие фильтра сортировки")
    public boolean hasSortFilter() {
        return isVisible(productSortContainer);
    }
}