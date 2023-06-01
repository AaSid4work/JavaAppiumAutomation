import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;


public class Ex4 extends CoreTestCase {
    public MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
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

