import io.appium.java_client.TouchAction;
import lib.CoreTestCase;
import lib.ui.*;
import org.junit.Test;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;

public class Lesson4 extends CoreTestCase  {
    public MainPageObject MainPageObject;
    protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void TestSwipeArticle () {
        SearchPageObject SearchPageObject = new SearchPageObject (driver); // инициализация страницы поиска

        SearchPageObject.initSearchInput(); // ищем поле ввода

        SearchPageObject.TypeSearchLine("Appium"); //вводим текст

        SearchPageObject.ClickByArticleWithSubstring("Appium"); // кликаем в текст в найденном списке

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);// инициализация страницы статьи

        ArticlePageObject.waitForTitleElement(); //ждем определенного заголовка



    }
    @Test
    public void SaveFirstArticleToMyList() {
        //Клик в поле поиска и ввод текста
        SearchPageObject SearchPageObject = new SearchPageObject( driver);
        SearchPageObject.initSearchInput();
        SearchPageObject.TypeSearchLine("Java");
        //клик в заголовок
        SearchPageObject.ClickByArticleWithSubstring("Object-oriented programming language");
        //проверка что заголовок отобразился
        ArticlePageObject ArticlePageObject = new ArticlePageObject( driver);
        String ArticleTitle = ArticlePageObject.GetArticleTitle();
        String name_of_folder = "Anime";
        ArticlePageObject.AddArticleToMyList(name_of_folder);
        ArticlePageObject.CloseArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.ClickMyList();
//действия на слое избранное
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
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
                By.xpath("//*[@resourse_id='org.wikipedia:id/search_results_list']//*[contains(@text,'Linkin Park')]"),
                "Cannot find anything by the request" + searchLine,
                15
        );
        int amountOfSearchResult = MainPageObject.getAmountOfElements(
                By.xpath("//*[@resourse_id='org.wikipedia:id/search_results_list']//*[contains(@text,'Linkin Park')]")
        );
        Assert.assertTrue(
                "We found too few results",
                amountOfSearchResult > 0);
    }



}
