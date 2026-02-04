package pages.saucedemo;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;

public class LoginPage extends BasePage {
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.cssSelector("[data-test='error']");

    public LoginPage(WebDriver d, WebDriverWait w) {
        super(d, w);
    }

    @Step("Открыть страницу логина SauceDemo")
    public void open() {
        driver.get("https://www.saucedemo.com/");
    }

    @Step("Выполнить логин (username={username}, password={password})")
    public void login(String username, String password) {
        type(usernameField, username);
        type(passwordField, password);
        click(loginButton);
    }

    @Step("Получить текст ошибки")
    public String getErrorMessage() {
        return getText(errorMessage);
    }

    @Step("Проверить отображение ошибки")
    public boolean isErrorDisplayed() {
        return isVisible(errorMessage);
    }
}