package Tests.Homeworks;

import lib.CoreTestCase;
import lib.ui.*;
import lib.ui.IOS.*;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;

public class Lesson4 extends CoreTestCase  {
    public MainPageObject MainPageObject;
    public void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void TestSwipeArticle () {
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver); // инициализация страницы поиска

        SearchPageObject.initSearchInput(); // ищем поле ввода

        SearchPageObject.typeSearchLine("Appium"); //вводим текст

        SearchPageObject.clickByArticleWithSubstring("Appium"); // кликаем в текст в найденном списке

        iOSArticlePageObject ArticlePageObject = new iOSArticlePageObject(driver);// инициализация страницы статьи

        ArticlePageObject.waitForTitleElement(); //ждем определенного заголовка



    }
    @Test
    public void SaveFirstArticleToMyList() {
        //Клик в поле поиска и ввод текста
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get( driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        //клик в заголовок
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        //проверка что заголовок отобразился
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get( driver);
        String ArticleTitle = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Anime";
        ArticlePageObject.AddArticleToMyList(name_of_folder);
        ArticlePageObject.CloseArticle();

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);
        NavigationUI.ClickMyList();
//действия на слое избранное
        MyListsPageObject MyListsPageObject = MyListPageObjectFactory.get(driver);
        MyListsPageObject.OpenFolderByName(name_of_folder);
        MyListsPageObject.swipeByArticleToDelete(ArticleTitle);

    }
    @Test
    public void TestAmoutOfNotEmptySearch () {
        MainPageObject.waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Search Wikipedia' input",
                15
        );
        String searchLine = "Linkin Park";

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                searchLine,
                "Cannot find search input",
                15
        );
        // String searchResultLocator = "//*[@resourse_id='org.wikipedia:id/page_list_item_container']//*[@text = 'Linkin Park']";

        MainPageObject.waitForElementPresent(
                ("//*[@resourse_id='org.wikipedia:id/search_results_list']//*[contains(@text,'Linkin Park')]"),
                "Cannot find anything by the request" + searchLine,
                15
        );
        int amountOfSearchResult = MainPageObject.getAmountOfElements(
                String.valueOf(By.xpath("//*[@resourse_id='org.wikipedia:id/search_results_list']//*[contains(@text,'Linkin Park')]"))
        );
        Assert.assertTrue(
                "We found too few results",
                amountOfSearchResult > 0);
    }



}
