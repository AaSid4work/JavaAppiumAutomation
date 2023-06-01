import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex2 extends CoreTestCase {
    public MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSearchResults()
        {
            SearchPageObject SearchPageObject = new SearchPageObject( driver);
            SearchPageObject.initSearchInput();
            SearchPageObject.TypeSearchLine("Java");
            SearchPageObject.waitForSearchResult("Object-oriented programming language");
        }

}
