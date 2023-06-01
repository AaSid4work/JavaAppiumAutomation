package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class SearchPageObject extends MainPageObject {


    public SearchPageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            SEARCH_INIT_ELEMENT = "//*[contains (@text, 'Search Wikipedia')]",
            SEARCH_INPUT = "//*[contains (@text, 'Search…')]",
            SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='{SUBSTRING}']",
            SEARCH_CANCEL_BUTTON = "org.wikipedia:id/search_close_btn",
            SEARCH_RESULT_BY_STRING_ON_PAGE_TPL = "org.wikipedia:id/view_page_subtitle_text",
            ARTICLE_TITLE_AND_DESCRIPTION = "xpath://android.view.ViewGroup/android.widget.TextView[@text='{TITLE}']/../android.widget.TextView[@text='{DESCRIPTION}']/..";


    /*TEMPLATES METHODS */
    private static String GetResultSearchElement(String substring) {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }

    /*TEMPLATES METHODS */
    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init element", 15);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking init element", 15);
    }

    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent(By.id(SEARCH_CANCEL_BUTTON),"Cannot find X to cancel search", 10);
    }
    public void waitForCancelButtonToDissAppear()
    {
        this.waitForElementNotPresent(By.id(SEARCH_CANCEL_BUTTON),"Search Cancel button is steel present", 10);
    }
    public void ClickCancelSearch ()
    {
        this.waitForElementAndClick(By.id(SEARCH_CANCEL_BUTTON),"Cannot find X to click cancel search",10);
    }
    public void TypeSearchLine (String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find and type search input", 15);
    }
    public static String getSearchResultXpathByTitleAndDescription(String title, String description) {
        return ARTICLE_TITLE_AND_DESCRIPTION
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);

    }

    public  void waitForSearchResult(String substring)
    {
        String SearchResultXpath = GetResultSearchElement (substring);
        this.waitForElementPresent(By.xpath(SearchResultXpath),"Cannot find search result with substring"+ substring, 10);
    }
    public  void waitForSearchResultOnPage()
    {
        this.waitForElementPresent(By.id(SEARCH_RESULT_BY_STRING_ON_PAGE_TPL),"Cannot find search result with substring", 10);
    }
    public  void ClickByArticleWithSubstring(String substring)
    {
        String SearchResultXpath = GetResultSearchElement (substring);
        this.waitForElementAndClick(By.xpath(SearchResultXpath),"Cannot find and click search result with substring"+ substring, 10);
    }



}
