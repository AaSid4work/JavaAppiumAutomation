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

public class FirstTest {
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
    public void Test1 ()
    {
        WaitForElementByXpathAndClick(
                "//*[contains (@text, 'Search Wikipedia')]",
                "Wikipedia not found",
                5
        );
        WaitForElementByXpathAndSendKey(
                "//*[contains (@text, 'Search…')]",
                "Java",
                "Search not found",
                5
        );

        waitForElementPresentByXpath(
                "//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='Object-oriented programming language']",
                "Java",
                15
        );
    }
     @Test
     public void Test2()
     {
        WaitForElementByIdAndClick(
                "org.wikipedia:id/search_container",
                "Cannot find Search Wikipedia input",
                5
        );
        WaitForElementByIdAndClick(
                "org.wikipedia:id/search_close_btn",
                "Cannot find X to cancel search",
                5
        );
        waitForElementPresent(
                "org.wikipedia:id/search_close_btn",
                "X still present on the page",
                5
        );
     }
     @Test
     public  void Test3()
     {
         WaitForElementByXpathAndClick(
                 "//*[contains (@text, 'Search Wikipedia')]",
                 "Wikipedia not found",
                 5
         );
         WaitForElementByXpathAndSendKey(
                 "//*[contains (@text, 'Search…')]",
                 "Java",
                 "Search not found",
                 5
         );
         WaitForElementByXpathAndClick(
                 "//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='Object-oriented programming language']",
                 "Page not opened",
                 15
         );
         WebElement titleElement = waitForElementPresentById(
                 "org.wikipedia:id/view_page_title_text",
                 "Text not found",
                 5
         );
         String article_title = titleElement.getAttribute("text");
         Assert.assertEquals(
                 "We see unexpected title",
                 "Java (programming language)",
                 article_title
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
    private WebElement waitForElementPresentByXpath(String xpath, String error_message)
    {
        return waitForElementPresentByXpath(xpath, error_message, 5);
    }
    private WebElement WaitForElementByXpathAndClick(String xpath, String error_message,long timeoutinSeconds)
    {
        WebElement element = waitForElementPresentByXpath(xpath,error_message,timeoutinSeconds);
        element.click();
        return  element;
    }
    private WebElement WaitForElementByXpathAndSendKey(String xpath,String value, String error_message,long timeoutinSeconds)
    {
        WebElement element = waitForElementPresentByXpath(xpath,error_message,timeoutinSeconds);
        element.sendKeys(value);
        return element;
    }
    private WebElement waitForElementPresentById(String id, String error_message, long timeoutinSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutinSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement WaitForElementByIdAndClick(String id, String error_message,long timeoutinSeconds)
    {
        WebElement element = waitForElementPresentById(id,error_message,timeoutinSeconds);
        element.click();
        return  element;
    }
    private boolean waitForElementPresent(String id, String message, long timeoutinSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutinSeconds);
        wait.withMessage(message + "\n");
        By by = By.id(id);
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

}


