import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Lesson3 {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 5 API 29");
        capabilities.setCapability("udid", "emulator-5554");
        capabilities.setCapability("platformVersion", "Android 10.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/paperbirdy/Desktop/JAA/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void Ex3() {
        waitForElementAndClick(
                By.xpath("//*[contains (@text, 'Search Wikipedia')]"),
                "'Search Wikipedia' not found",
                15
        );

        assertElementHasText(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_plate']//*[@text='Search…']"),
                "Search…",
                "Cannot find 'Search…'",
                15
        );


    }

    private WebElement assertElementHasText(By by, String expected_text, String error_message, long timeOut) {
        WebElement element = waitForElementPresent(by, error_message, timeOut);
        String actual_text = element.getText();
        Assert.assertEquals(expected_text, actual_text, error_message);
        return element;
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutinSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutinSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeOut) {
        WebElement element = waitForElementPresent(by, error_message, timeOut);
        element.click();
        return element;
    }
}
