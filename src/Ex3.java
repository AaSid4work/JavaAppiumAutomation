import lib.CoreTestCase;
import lib.ui.MainPageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class Ex3 extends CoreTestCase {

    public MainPageObject MainPageObject;
        protected void setUp() throws Exception
    {
        super.setUp();
        MainPageObject = new MainPageObject(driver);
    }

    @Test
    public void testCancelSearch()
    {

        SearchPageObject SearchPageObject = new SearchPageObject( driver); // инициализация страницы поиска
        SearchPageObject.initSearchInput();// ищем поле ввода



        SearchPageObject.waitForCancelButtonToAppear(); //ищем кнопку отмены поиска
        SearchPageObject.ClickCancelSearch(); //тап в кнопку отмены поиска
        SearchPageObject.waitForCancelButtonToDissAppear(); //проверка что кнопки отмены поиска нет
    }


}
