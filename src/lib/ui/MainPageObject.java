package lib.ui;

import com.google.common.collect.ImmutableMap;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import lib.Platform;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;


public class MainPageObject {

    protected AppiumDriver driver;

    public MainPageObject(AppiumDriver driver) {
        this.driver = driver;
    }


    public WebElement waitForElementPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );

    }
    public List<WebElement> waitForElementsPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
    }

    public WebElement waitForElementAndClick(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.click();
        return element;
    }
    public WebElement waitForElementAndSendKeys(String locator, String error_message, String value, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndClear(String locator, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public boolean waitForElementNotPresent(String locator, String error_message, long timeoutInSeconds) {
        By by = this.getLocatorByString(locator);
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );
    }

    public int getAmountOfElements(String locator) {
        List<WebElement> elements = driver.findElements(By.xpath(locator));
        return (elements).size();
    }
    public void swipeUp (int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        PointOption pointOption = new PointOption<>();


        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);

        action
                .press(PointOption.point(x,start_y))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeOfSwipe)))
                .moveTo(PointOption.point(x,end_y))
                .release()
                .perform();
    }
    public void swipeUpQuick() {
        swipeUp(100);
    }

    public void swipeElementToLeft(String locator, String error_message) {
        WebElement element = waitForElementPresent(locator, error_message, 10);

        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action.press(PointOption.point(right_x, middle_y));
        action.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1500)));
        if (Platform.getInstance().isAndroid()) {
            int offset_x = (-1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(offset_x,middle_y));
        } else {
            action.moveTo(PointOption.point(left_x, middle_y));
        }
        action.release();
        action.perform();
    }
    public void swipeIOS (String locator, String error_message, long timeoutInSeconds, String direction) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        JavascriptExecutor js = driver;
        Map<String, Object> params = new HashMap<>();
        params.put("direction", direction);
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: swipe", params);
    }

    public String waitForElementAndGetAtribute(String locator, String atribute, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, error_message, timeoutInSeconds);
        return element.getAttribute(atribute);

    }

    public WebElement assertElementPresent(String locator, String error_message) {

        By by = getLocatorByString(locator);
        try {
        WebElement element = driver.findElement(by);
        return element;
    } catch (NoSuchElementException e) {
        throw new AssertionError(error_message);
    }
        public WebElement assertElementNotPresent (String locator, String error_message)
        {
            int amount_of_elements = getAmountOfElements(locator);
            if (amount_of_elements>0)
            {
                String default_message = "An element" +locator+"supposed to not present";
                throw new AssertionError(default_message+ " "+error_message);
            }
        }
       public void  assertArticleHasTitle (String locator, String expected_text, String error_message, long timeoutInSeconds)
        {
            WebElement element = waitForElementPresent(locator,error_message,timeoutInSeconds);
            String actual_title = element.getText();
            Assert.assertEquals(expected_text, actual_title, error_message);
            return element;
        }



        }
    private By getLocatorByString(String locator_with_type)
    {
        String [] Exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = Exploded_locator[0];
        String locator = Exploded_locator[1];

        if (by_type.equals("xpath"))
        {
            return By.xpath(locator);
        }
        else if (by_type.equals("id"))
        {
            return By.id(locator);
        }
        else
        {
            throw new IllegalAccessException("Cannot get tybe of locator "+locator_with_type);
        }
        }

}

