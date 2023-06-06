package Tests.Homeworks;

import lib.CoreTestCase;
import lib.ui.IOS.iOSArticlePageObject;
import lib.ui.IOS.SearchPageObject;
import org.junit.Test;
public class FirstTest  extends CoreTestCase {
    public iOSMainPageObject MainPageObject;

    public void setUp() throws Exception {
        super.setUp();
        MainPageObject = new iOSMainPageObject(driver);
    }

    @Test
    public void TestSearch() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.TypeSearchLine("Java");
        SearchPageObject.waitForSearchResult("Java");
    }

    @Test
    public void testSaveArticlesToMyList() throws InterruptedException {

        SearchPageObject searchPageObject = new SearchPageObject(driver);

        searchPageObject.initSearchInput();
        searchPageObject.TypeSearchLine("java");
        searchPageObject.ClickByArticleWithSubstring("Object-oriented programming language");

        iOSArticlePageObject articlePageObject = new iOSArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        articlePageObject.AddArticleToMyList(name_of_folder);
        articlePageObject.CloseArticle();

        searchPageObject.initSearchInput();
        searchPageObject.TypeSearchLine("Appium");
        searchPageObject.ClickByArticleWithSubstring("Search Wikipedia");


    }
}

