package pages.demoqa_elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.BasePage;

public class TextBoxPage extends BasePage {

    private final By fullNameField = By.id("userName");
    private final By emailField = By.id("userEmail");
    private final By currentAddressField = By.id("currentAddress");
    private final By permanentAddressField = By.id("permanentAddress");
    private final By submitButton = By.id("submit");
    private final By outputSection = By.id("output");

    public TextBoxPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть локальную тестовую страницу")
    public void open() {
        // Используем локальный файл вместо demoqa.com
        String filePath = getClass().getClassLoader().getResource("test_page.html").getPath();
        if (filePath.startsWith("/")) {
            filePath = filePath.substring(1);
        }
        driver.get("file:///" + filePath.replace(" ", "%20"));
    }

    @Step("Заполнить форму Text Box")
    public void fillForm(String name, String email, String currentAddr, String permanentAddr) {
        type(fullNameField, name);
        type(emailField, email);
        type(currentAddressField, currentAddr);
        type(permanentAddressField, permanentAddr);
        click(submitButton);
    }

    @Step("Проверить, отображается ли результат")
    public boolean isOutputDisplayed() {
        return isVisible(outputSection);
    }

    @Step("Получить CSS класс поля email")
    public String getEmailFieldClass() {
        return driver.findElement(emailField).getAttribute("class");
    }

    @Step("Проверить, есть ли ошибка у поля email")
    public boolean hasEmailError() {
        String className = getEmailFieldClass();
        return className.contains("field-error") ||
                className.contains("error") ||
                className.contains("invalid");
    }
}