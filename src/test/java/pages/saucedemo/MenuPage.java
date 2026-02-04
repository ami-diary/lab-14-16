package pages.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class MenuPage extends BasePage {
    private final By menuButton = By.id("react-burger-menu-btn");
    private final By logoutLink = By.id("logout_sidebar_link");

    public MenuPage(WebDriver d, WebDriverWait w) {
        super(d, w);
    }

    @Step("Выполнить логаут")
    public void logout() {
        click(menuButton);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        clickWithJS(logoutLink);
    }
}