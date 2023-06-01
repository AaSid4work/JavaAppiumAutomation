import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Ex7 extends CoreTestCase {
    public MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testScreenRotation()
    {
        String title_value = "Spirited Away";
        String  search_title_locator  = "org.wikipedia:id/view_page_title_text";

        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                title_value,
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[contains(@text,'Spirited Away')]"),
                "Cannot find" + title_value,
                5
        );
        String TitleBeforeRotation = MainPageObject.waitForElementAndGetAtribute(
                By.id(search_title_locator),
                "text",
                "Cannot find title of article",
                5
        );
        driver.rotate(ScreenOrientation.LANDSCAPE);

        String TitleAfterRotation = MainPageObject.waitForElementAndGetAtribute(
                By.id(search_title_locator),
                "text",
                "Cannot find title of article",
                5
        );
        Assert.assertEquals(
                "Article title have been changed after screen rotation",
                TitleBeforeRotation,
                TitleAfterRotation
        );

    }

}
