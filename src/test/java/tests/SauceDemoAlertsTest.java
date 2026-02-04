package tests;

import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.demoqa_alerts.AlertsPage;

@Epic("SauceDemo UI Tests")
@Feature("Alerts - JavaScript Testing")
@Owner("Шерикбекова А.Э.")
@Story("Работа с JavaScript алертами")
public class SauceDemoAlertsTest extends BaseTest {

    @Test(description = "Тест простого алерта")
    @Severity(SeverityLevel.CRITICAL)
    public void testSimpleAlert() {
        System.out.println("🔍 Тестирую простой алерт");
        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.open();
        alertsPage.clickSimpleAlert();
        alertsPage.acceptAlert();
        System.out.println("✅ Простой алерт обработан");
    }

    @Test(description = "Тест confirm алерта - OK")
    @Severity(SeverityLevel.NORMAL)
    public void testConfirmAlertAccept() {
        System.out.println("🔍 Тестирую confirm алерт (OK)");
        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.open();
        alertsPage.clickConfirmAndAccept();
        String result = alertsPage.getConfirmResultText();
        Assert.assertTrue(result.contains("OK"), "Должен быть текст про OK");
        System.out.println("✅ Confirm алерт (OK) пройден");
    }

    @Test(description = "Тест confirm алерта - Cancel")
    @Severity(SeverityLevel.NORMAL)
    public void testConfirmAlertDismiss() {
        System.out.println("🔍 Тестирую confirm алерт (Cancel)");
        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.open();
        alertsPage.clickConfirmAndDismiss();
        String result = alertsPage.getConfirmResultText();
        Assert.assertTrue(result.contains("Cancel"), "Должен быть текст про Cancel");
        System.out.println("✅ Confirm алерт (Cancel) пройден");
    }

    @Test(description = "Тест prompt алерта")
    @Severity(SeverityLevel.NORMAL)
    public void testPromptAlert() {
        System.out.println("🔍 Тестирую prompt алерт");
        AlertsPage alertsPage = new AlertsPage(driver);
        alertsPage.open();
        String testName = "Айгерим";
        alertsPage.clickPromptAndSendText(testName);
        String result = alertsPage.getPromptResultText();
        Assert.assertTrue(result.contains(testName), "Результат должен содержать введенное имя");
        System.out.println("✅ Prompt алерт пройден");
    }
}