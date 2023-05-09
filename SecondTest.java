import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.net.URL;
public class SecondTest
{
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

    //@After
    //public void tearDown() {
    //    driver.quit();
    //}

    @Test
    public void firstTest ()

    {
        WebElement element_to_init_search = driver.findElementByXPath( "//*[contains (@text, 'Search Wikipedia')]") ;
        element_to_init_search.click();

        WebElement element_to_enter_search_line = waitForElementPresentByXpath(
                "//*[contains (@text, 'Search…')]",
                "Cannot find search input",
                5
        );
        element_to_enter_search_line.sendKeys ( "Appium");
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
}


}
