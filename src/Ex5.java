import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MainPageObject;
import lib.ui.MyListsPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex5 extends CoreTestCase {
    public MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testSaveArticlesToMyList() {
        //действия на слое поиска
        SearchPageObject SearchPageObject = new SearchPageObject( driver);// инициализация страницы поиска

        SearchPageObject.initSearchInput(); // ищем поле ввода
        SearchPageObject.TypeSearchLine("Cats"); //вводим текст 1
        SearchPageObject.ClickByArticleWithSubstring("Cat"); // кликаем в текст в найденном списке

        ArticlePageObject ArticlePageObject = new ArticlePageObject( driver); // инициализация страницы статьи
        ArticlePageObject.waitForTitleElement();
        String ArticleTitle = ArticlePageObject.GetArticleTitle(); // выявляем имя заголовка статьи
        String name_of_folder = "Pets"; //задаем имя папке тут

        ArticlePageObject.AddArticleToMyList(name_of_folder); //создаем папку и добавляем статью 1
        ArticlePageObject.CloseArticle(); //закрываем статью

        //действия на слое поиска
        SearchPageObject.initSearchInput(); // ищем поле ввода
        SearchPageObject.TypeSearchLine("Dogs"); //вводим текст 2
        SearchPageObject.waitForSearchResult("Dog"); // проверяем что отобразился нужный заголовок в списке
        SearchPageObject.ClickByArticleWithSubstring("Dog"); // кликаем в текст в найденном списке
        SearchPageObject.waitForSearchResultOnPage();  // проверяем что отобразился нужный заголовок статьи

        //действия на слое статьи
        ArticlePageObject.AddArticleToFolderInMyList(name_of_folder); //добавляем статью 2 в существующую папку
        ArticlePageObject.CloseArticle(); //закрываем статью

        //действия на слое избранное
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);// инициализация страницы избранное
        MyListsPageObject.OpenFolderByName(name_of_folder); // открываем созданную папку
        MyListsPageObject.swipeByArticleToDelete(ArticleTitle); // находим статью/удаляем свайпом/убеждаемся что статья удалена
    }
}
