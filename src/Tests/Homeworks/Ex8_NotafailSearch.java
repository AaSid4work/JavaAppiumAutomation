package Tests.Homeworks;

import lib.CoreTestCase;
import lib.ui.IOS.SearchPageObject;
import org.junit.Test;

public class Ex8_NotafailSearch extends CoreTestCase {
    public iOSMainPageObject MainPageObject;

    public void setUp() throws Exception {
        super.setUp();
        MainPageObject = new iOSMainPageObject(driver);
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