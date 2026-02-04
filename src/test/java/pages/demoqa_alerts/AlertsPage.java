package pages.demoqa_alerts;

import io.qameta.allure.Step;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BasePage;

public class AlertsPage extends BasePage {

    private final By alertButton = By.id("alertButton");
    private final By confirmButton = By.id("confirmButton");
    private final By promptButton = By.id("promptButton");
    private final By confirmResult = By.id("confirmResult");
    private final By promptResult = By.id("promptResult");

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    @Step("Открыть локальную страницу с алертами")
    public void open() {
        // Простая HTML-страница с алертами
        String html = "<html><body>" +
                "<h1>Alerts Test Page</h1>" +
                "<button id='alertButton' onclick='alert(\"Simple Alert\")'>Simple Alert</button>" +
                "<button id='confirmButton' onclick='if(confirm(\"Confirm?\")) { document.getElementById(\"confirmResult\").innerText=\"You clicked OK\" } else { document.getElementById(\"confirmResult\").innerText=\"You clicked Cancel\" }'>Confirm Alert</button>" +
                "<button id='promptButton' onclick='var name=prompt(\"Enter your name:\"); if(name) { document.getElementById(\"promptResult\").innerText=\"Hello \"+name }'>Prompt Alert</button>" +
                "<p id='confirmResult'></p>" +
                "<p id='promptResult'></p>" +
                "</body></html>";

        driver.get("data:text/html;charset=utf-8," + html);
    }

    @Step("Кликнуть на кнопку простого алерта")
    public void clickSimpleAlert() {
        click(alertButton);
    }

    @Step("Принять простой алерт")
    public void acceptAlert() {
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Step("Кликнуть на кнопку confirm и нажать OK")
    public void clickConfirmAndAccept() {
        click(confirmButton);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();
    }

    @Step("Кликнуть на кнопку confirm и нажать Cancel")
    public void clickConfirmAndDismiss() {
        click(confirmButton);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.dismiss();
    }

    @Step("Кликнуть на prompt, ввести текст и нажать OK")
    public void clickPromptAndSendText(String text) {
        click(promptButton);
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.sendKeys(text);
        alert.accept();
    }

    @Step("Получить текст результата confirm")
    public String getConfirmResultText() {
        return driver.findElement(confirmResult).getText();
    }

    @Step("Получить текст результата prompt")
    public String getPromptResultText() {
        return driver.findElement(promptResult).getText();
    }
}