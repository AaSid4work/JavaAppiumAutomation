import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class Ex6 extends CoreTestCase {
    public MainPageObject MainPageObject;

    protected void setUp() throws Exception {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testAssertTitle() {
        //Прекондишн
        String title_value = "Spirited Away";

       //Клик в поле поиска и ввод текста
        SearchPageObject SearchPageObject = new SearchPageObject( driver); // инициализация страницы поиска
        SearchPageObject.initSearchInput(); // ищем поле ввода
        SearchPageObject.TypeSearchLine(title_value); // вводим текст

        //клик в статью
        SearchPageObject.ClickByArticleWithSubstring("2001 Japanese animated film directed by Hayao Miyazaki");

        //проверка что статья найдена
        ArticlePageObject ArticlePageObject = new ArticlePageObject( driver); // инициализация слоя статьи
        String ArticleTitleMy = ArticlePageObject.GetArticleTitleMy(); //проверка что отображается нужный заголовок

        Assert.assertEquals("We see expected title",
                "Spirited Away",
                ArticleTitleMy
        );

    }

}



