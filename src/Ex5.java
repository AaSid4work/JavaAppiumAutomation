import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class Ex5 {
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "Pixel 3");
        capabilities.setCapability("udid", "127.0.0.1:5555");
        capabilities.setCapability("platformVersion", "Android 11.0");
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
    public void SaveArticlesToMyList() {
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Cats",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/fragment_feed_header"),
                "Cannot find 'Cat'",
                5
        );
        waitForElementPresent(
                By.id("org.wikipedia:id/view_page_subtitle_text"),
                "Cannot find 'Small domesticated carnivorous mammal'",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "cannot find button 'more options'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "cannot find option to add article to reading list'",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/onboarding_button"),
                "cannot find GOT IT tip overlay",
                5
        );
        waitForElementAndClear(
                By.id("org.wikipedia:id/text_input"),
                "Cannot find input to set name of article folder ",
                5
        );
        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/text_input"),
                "Pets",
                "Cannot put text into article folder input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'OK']"),
                "cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton [@content-desc = 'Navigate up']"),
                "cannot close article, cannot find x button",
                5
        );
        //добавляем вторую статью
        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Dogs",
                "Cannot find search input",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Domestic animal']"),
                "Cannot find 'Domestic animal'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@text ='Domesticated canid species']"),
                "Cannot find 'Domesticated canid species'",
                15
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc = 'More options']"),
                "cannot find button 'more options'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Add to reading list']"),
                "cannot find option to add article to reading list'",
                5
        );
        waitForElementAndClick(
                By.id("org.wikipedia:id/item_reading_list_statistical_description"),
                "cant find 'Pets' folder",
                5
        );
        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton [@content-desc = 'Navigate up']"),
                "cannot close article, cannot find x button",
                5
        );
//переходим в сохраненные статьи

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout [@content-desc = 'My lists']"),
                "cannot find navigation button to My lists",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Pets']"),
                "cannot find created folder",
                5
        );
        elementToSwipeLeft(
                By.xpath("//*[@text = 'Cat']"),
                "Cannot find saved article 'Cat'"
        );
        waitForElementPresent(
                By.xpath("//*[@text = 'Dog']"),
                "Cannot find 'Dog'",
                5
        );
        waitForElementAndClick(
                By.xpath("//*[@text = 'Dog']"),
                "cannot find created folder 'Dog'",
                5
        );
        waitForElementPresent(
                By.xpath("//*[@text ='Domesticated canid species']"),
                "Cannot find 'Domesticated canid species'",
                15
        );



    }
    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }
    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }
    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }
    protected void elementToSwipeLeft(By by,String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10
        );
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y+element.getSize().getHeight();
        int middle_y = (upper_y+lower_y)/2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x,middle_y)
                .waitAction(150)
                .moveTo(left_x,middle_y)
                .release()
                .perform();
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
