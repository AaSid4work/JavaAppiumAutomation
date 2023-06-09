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
    public void assertElementNotPresent(String locator, String error_message) {
        int amount_of_elements = getAmountOfElements(locator);
        if (amount_of_elements > 0) {
            String default_message = "An element '" + locator.toString() + "' supposed to be not present";
            throw new AssertionError(default_message + " " + error_message);
        }
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
            action.moveTo(PointOption.point(left_x, middle_y));
        } else {
            int offset_x = (-1 * element.getSize().getWidth());
            action.moveTo(PointOption.point(offset_x,middle_y));
        }
        action.release();
        action.perform();
    }
    public void swipeUpToFindElement(String locator, String error_messege, int max_swipes) {
        By by = this.getLocatorByString(locator);
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {

            if (already_swiped > max_swipes) {
                waitForElementPresent(locator, "Cannot find element by swiping up. \n" + error_messege, 0);
                return;
            }
            swipeUpQuick();
            ++already_swiped;
        }
    }
    public boolean isElementLocatedOnTheScreen(String locator) {
        int element_located_by_y = this.waitForElementPresent(locator, "Cannot find element by locator", 5).getLocation().getY();
        int screen_size_by_y = driver.manage().window().getSize().getHeight();
        return element_located_by_y < screen_size_by_y;
    }

    public void swipeUpTitleElementAppear(String locator, String error_massage, int max_swipes){
        int already_swiped = 0;

        while (!this.isElementLocatedOnTheScreen(locator)) {
            if(already_swiped > max_swipes){
                Assert.assertTrue(error_massage,this.isElementLocatedOnTheScreen(locator));
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }


    public WebElement assertElementPresent(String locator, String error_message) {

        By by = getLocatorByString(locator);
        try {
            WebElement element = driver.findElement(by);
            return element;
        } catch (NoSuchElementException e) {
            throw new AssertionError(error_message);
        }
    }
    private By getLocatorByString(String locator_with_type) {
        String[] exploded_locator = locator_with_type.split(Pattern.quote(":"),2);
        String by_type = exploded_locator[0];
        String  locator = exploded_locator[1];

        if (by_type.equals("xpath")) {
            return By.xpath(locator);
        } else if (by_type.equals("id")) {
            return By.id(locator);
        } else {
            throw new IllegalArgumentException("Cannot get type of locator. Locator: " + locator_with_type);
        }
    }
    public void ClickElementToTheRightUpperCorner(String locator ,String error_message)
    {
        WebElement element = this.waitForElementPresent(locator+"/..",error_message,5);
                int right_x = element.getLocation().getX();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y)/2;
        int wight = element.getSize().getWidth();
        int point_to_click_x = (right_x+wight)-3;
        int point_to_click_y = middle_y;
        TouchAction action = new TouchAction(driver);
        action.tap(point_to_click_x,point_to_click_x).perform();
    }

}

