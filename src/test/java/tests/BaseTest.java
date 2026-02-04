package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.ByteArrayInputStream;
import java.time.Duration;

public abstract class BaseTest {
    protected WebDriver driver;

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setUp(@Optional("chrome") String browser,
                      @Optional("false") String headless) {
        System.out.println("🚀 Настройка драйвера для браузера: " + browser);

        boolean isHeadless = Boolean.parseBoolean(headless);

        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                if (isHeadless) firefoxOptions.addArguments("--headless");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (isHeadless) edgeOptions.addArguments("--headless");
                driver = new EdgeDriver(edgeOptions);
                break;

            default: // chrome
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (isHeadless) chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        System.out.println("✅ Драйвер готов: " + browser + (isHeadless ? " (headless)" : ""));
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result) {
        System.out.println("🛑 Завершаю тест: " + result.getName());

        if (!result.isSuccess() && driver != null) {
            try {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                Allure.addAttachment("FAIL: " + result.getName(),
                        new ByteArrayInputStream(screenshot));
            } catch (Exception e) {
                System.out.println("❌ Не удалось сделать скриншот");
            }
        }

        if (driver != null) {
            driver.quit();
        }
    }
}