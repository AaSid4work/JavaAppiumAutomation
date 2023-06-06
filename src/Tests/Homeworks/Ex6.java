package Tests.Homeworks;

import lib.CoreTestCase;
import lib.ui.IOS.iOSArticlePageObject;
import lib.ui.IOS.SearchPageObject;
import org.junit.Assert;
import org.junit.Test;

public class Ex6 extends CoreTestCase {
    public iOSMainPageObject MainPageObject;

    public void setUp() throws Exception {
        super.setUp();
        MainPageObject = new iOSMainPageObject(driver);
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
        iOSArticlePageObject ArticlePageObject = new iOSArticlePageObject( driver); // инициализация слоя статьи
        String ArticleTitleMy = ArticlePageObject.GetArticleTitleMy(); //проверка что отображается нужный заголовок

        Assert.assertEquals("We see expected title",
                "Spirited Away",
                ArticleTitleMy
        );

    }

}



