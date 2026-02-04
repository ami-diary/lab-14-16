package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.demoqa_elements.TextBoxPage;

@Epic("SauceDemo UI Tests")
@Feature("Elements - Form Testing")
@Owner("Шерикбекова А.Э.")
@Story("Работа с элементами формы")
public class SauceDemoElementsTest extends BaseTest {

    @Test(description = "Позитивный тест: заполнение формы валидными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Link(name = "Form Testing", url = "https://demoqa.com/text-box")
    public void testValidFormSubmission() {
        System.out.println("🔍 Запускаю тест формы с валидными данными");

        TextBoxPage textBoxPage = new TextBoxPage(driver);
        textBoxPage.open();

        textBoxPage.fillForm(
                "Айгерим Шерикбекова",
                "aigerim@example.com",
                "ул. Тестовая, 123",
                "ул. Постоянная, 456"
        );

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        boolean isResultVisible = textBoxPage.isOutputDisplayed();
        Assert.assertTrue(isResultVisible, "Результат должен отображаться");
        System.out.println("✅ Тест формы пройден");
    }

    @Test(description = "Негативный тест: невалидный email")
    @Severity(SeverityLevel.NORMAL)
    public void testInvalidEmail() {
        System.out.println("🔍 Запускаю тест с невалидным email");

        TextBoxPage textBoxPage = new TextBoxPage(driver);
        textBoxPage.open();

        textBoxPage.fillForm("Айгерим", "неправильный-email", "адрес", "адрес");

        try { Thread.sleep(1000); } catch (InterruptedException e) {}

        boolean hasError = textBoxPage.hasEmailError();
        Assert.assertTrue(hasError, "Поле email должно подсвечиваться как ошибочное");
        System.out.println("✅ Тест валидации email пройден");
    }
}