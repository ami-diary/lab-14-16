package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasePage {
    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final JavascriptExecutor js;

    protected BasePage(WebDriver d, WebDriverWait w) {
        this.driver = d;
        this.wait = w;
        this.js = (JavascriptExecutor) driver;
    }

    protected WebElement el(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    protected void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    protected void clickWithJS(By locator) {
        WebElement element = el(locator);
        js.executeScript("arguments[0].click();", element);
    }

    protected void type(By locator, String text) {
        el(locator).clear();
        el(locator).sendKeys(text);
    }

    protected String getText(By locator) {
        return el(locator).getText();
    }

    protected boolean isVisible(By locator) {
        try {
            return el(locator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}