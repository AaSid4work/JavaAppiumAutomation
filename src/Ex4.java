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
        SearchPageObject.ClickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject ArticlePageObject = new ArticlePageObject( driver);
        String ArticleTitle = ArticlePageObject.GetArticleTitle();

        Assert.assertEquals("We see expected title",
                "Java (programming language)",
                ArticleTitle
        );

    }

}

