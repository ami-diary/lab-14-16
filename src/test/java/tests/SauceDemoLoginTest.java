package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.saucedemo.LoginPage;

@Epic("SauceDemo Tests")
@Feature("Авторизация")
@Owner("Шерикбекова А.Э.")
public class SauceDemoLoginTest extends BaseTest {

    @Test(description = "Успешный логин стандартным пользователем")
    @Severity(SeverityLevel.CRITICAL)
    public void successfulLoginTest() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.login("standard_user", "secret_sauce");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "Должна открыться страница товаров");
    }

    @Test(description = "Логин заблокированным пользователем")
    @Severity(SeverityLevel.NORMAL)
    public void lockedUserLoginTest() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.login("locked_out_user", "secret_sauce");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "Для заблокированного пользователя должно появиться сообщение об ошибке");
    }

    @Test(description = "Логин с неверным паролем")
    @Severity(SeverityLevel.NORMAL)
    public void wrongPasswordLoginTest() {
        LoginPage loginPage = new LoginPage(driver, wait);
        loginPage.open();
        loginPage.login("standard_user", "wrong_password");

        Assert.assertTrue(loginPage.isErrorDisplayed(),
                "При неверном пароле должно появиться сообщение об ошибке");
    }
}