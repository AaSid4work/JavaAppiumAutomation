package lib;

import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.WebElement;

public class Ex8_NotafailSearch extends CoreTestCase {
    public MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }


    @Test
    public void testAssertTitle() {
        {
            SearchPageObject searchPageObject = new SearchPageObject(driver);
            searchPageObject.initSearchInput();
            searchPageObject.TypeSearchLine("Java");
            searchPageObject.waitForSearchResult("Java");
            SearchPageObject.getSearchResultXpathByTitleAndDescription("Java", "Island of Indonesia, Southeast Asia");

        }
    }
}