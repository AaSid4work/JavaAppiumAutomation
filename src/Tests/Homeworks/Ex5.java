package Tests.Homeworks;
import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.MyListsPageObject;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Assert;
import org.junit.Test;
import java.lang.String;


public class Ex5 extends CoreTestCase {
    public MainPageObject MainPageObject;
    public void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSaveArticlesToMyList() {
        //действия на слое поиска
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);// инициализация страницы поиска

        SearchPageObject.initSearchInput(); // ищем поле ввода
        SearchPageObject.typeSearchLine("Cats"); //вводим текст 1
        SearchPageObject.clickByArticleWithSubstring("Cat"); // кликаем в текст в найденном списке

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver); // инициализация страницы статьи
        ArticlePageObject.waitForTitleElementMy();
        String ArticleTitle = ArticlePageObject.getArticleTitle(); // выявляем имя заголовка статьи
        String name_of_folder = "Pets"; //задаем имя папке тут

        ArticlePageObject.AddArticleToMyList(name_of_folder); //создаем папку и добавляем статью 1
        ArticlePageObject.CloseArticle(); //закрываем статью

        //действия на слое поиска
        SearchPageObject.initSearchInput(); // ищем поле ввода
        SearchPageObject.typeSearchLine("Dogs"); //вводим текст 2
        SearchPageObject.waitForSearchResult("Dog"); // проверяем что отобразился нужный заголовок в списке
        SearchPageObject.clickByArticleWithSubstring("Dog"); // кликаем в текст в найденном списке
        SearchPageObject.waitForSearchResultOnPage();  // проверяем что отобразился нужный заголовок статьи

        //действия на слое статьи
        ArticlePageObject.AddArticleToFolderInMyList(name_of_folder); //добавляем статью 2 в существующую папку
        ArticlePageObject.CloseArticle(); //закрываем статью

        //действия на слое избранное

        if (Platform.getInstance().isAndroid()) {
            MyListsPageObject.OpenFolderByName(name_of_folder);
        }
        MyListsPageObject.clickCloseButton();

        MyListsPageObject.swipeByArticleToDelete(ArticleTitle);
    }


}

