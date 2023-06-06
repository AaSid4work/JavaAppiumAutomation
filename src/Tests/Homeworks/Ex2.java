package Tests.Homeworks;

import lib.CoreTestCase;
import lib.ui.IOS.SearchPageObject;
import org.junit.Test;

public class Ex2 extends CoreTestCase {
    public iOSMainPageObject MainPageObject;
    public void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new iOSMainPageObject(driver);
    }

    @Test
    public void testSearchResults()
        {
            SearchPageObject SearchPageObject = new SearchPageObject(driver);
            SearchPageObject.initSearchInput();
            SearchPageObject.TypeSearchLine("Java");
            SearchPageObject.waitForSearchResult("Object-oriented programming language");
        }

}
