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
import java.util.List;
import java.net.URL;

public class Ex3 {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Google Pixel 3");
        capabilities.setCapability("udid", "127.0.0.1:5555");
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
    public void Ex3()
    {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Search Wikipedia not found",
                5
        );



        WaitForElementByXpathAndSendKey(
                "//*[contains (@text, 'Search…')]",
                "Holidays",
                "Holidays not found",
                15
        );

        List Result = driver.findElements(By.xpath("//*[contains (@text, 'Holidays')]"));

// Убеждаемся, что найдено более одной статьи
        Assert.assertTrue(Result.size()> 1);



        waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "field is not empty",
                15
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5
        );
        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5
        );
    }



    private WebElement waitForElementPresent(By by, String error_message, long timeoutinSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutinSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutinSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutinSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement WaitForElementByXpathAndClick(String xpath, String error_message, long timeoutinSeconds) {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutinSeconds);
        element.click();
        return element;
    }
    private WebElement assertElementHasText(By by, String expectedText, String errorMessage, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        if (!element.getText().equals(expectedText))
            throw new AssertionError(errorMessage);
        return element;
    }

    private WebElement WaitForElementByXpathAndSendKey(String xpath, String value, String error_message, long timeoutinSeconds) {
        WebElement element = waitForElementPresentByXpath(xpath, error_message, timeoutinSeconds);
        element.sendKeys(value);
        return element;
    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

}
