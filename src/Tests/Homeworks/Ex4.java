package Tests.Homeworks;

import lib.CoreTestCase;
import lib.ui.IOS.SearchPageObject;
import org.junit.Test;


public class Ex4 extends CoreTestCase {
    public iOSMainPageObject MainPageObject;
    public void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new iOSMainPageObject(driver);
    }

    @Test
    public void testCompareArticleTitle()
    {
        //Клик в поле поиска и ввод текста
        SearchPageObject SearchPageObject = new SearchPageObject( driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.TypeSearchLine("Java");
        //клик в заголовок
        SearchPageObject.waitForSearchResult("Java");
        SearchPageObject.getSearchResultXpathByTitleAndDescription("Java", "Island of Indonesia, Southeast Asia");

    }

}

