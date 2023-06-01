import io.appium.java_client.AppiumDriver;
import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;
public class FirstTest  extends CoreTestCase {
    public MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
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

        ArticlePageObject articlePageObject = new ArticlePageObject(driver);
        articlePageObject.waitForTitleElement();
        String article_title = articlePageObject.GetArticleTitle();
        String name_of_folder = "Learning programming";

        articlePageObject.AddArticleToMyList(name_of_folder);
        articlePageObject.CloseArticle();

        searchPageObject.initSearchInput();
        searchPageObject.TypeSearchLine("Appium");
        searchPageObject.ClickByArticleWithSubstring("Search Wikipedia");


    }
}

