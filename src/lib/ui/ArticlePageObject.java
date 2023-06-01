package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject {
    public ArticlePageObject(AppiumDriver driver) {
        super(driver);
    }

    private static final String
            TITLE = "org.wikipedia:id/view_page_title_text",
            MY_TITLE = "org.wikipedia:id/view_page_title_text",
    OPTIONS_BUTTON = "xpath://android.widget.ImageView[@content-desc='More options']",
    ADD_TO_MY_LIST_BUTTON = "//*[@text = 'Add to reading list']",
    ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
    MY_LIST_MAME_INPUT = "org.wikipedia:id/text_input",
    MY_LIST_OK_BUTTON = "//*[@text = 'OK']",
    MY_SAVED_FOLDER_NAME = "//*[@text = '{SAVED_FOLDER_NAME}']",
    CLOSE_ARTICLE_BUTTON = "//android.widget.ImageButton [@content-desc = 'Navigate up']",
            ELEMENT_TO_FIND_AFTER_SWIPE_TPL = "//*[contains(@text,'{NAME_OF_ELEMENT}')]";

    private static String GetSearchElementByxPath (String search_element)
{
    return ELEMENT_TO_FIND_AFTER_SWIPE_TPL.replace("{NAME_OF_ELEMENT}",search_element);
}
    private static String GetNameOfSavedFolder (String name_of_saved_folder)
    {
        return MY_SAVED_FOLDER_NAME.replace("{SAVED_FOLDER_NAME}",name_of_saved_folder);
    }

    public WebElement waitForTitleElement() {
        return this.waitForElementPresent(By.xpath(MY_TITLE), "Cannot find article title on page", 15);
    }
    public void ClickInSavedFolder (String name_of_saved_folder)
    {
        String SavedFolderNameXpath = GetNameOfSavedFolder(name_of_saved_folder);
                this.waitForElementAndClick(By.xpath(SavedFolderNameXpath),
                        "Cannot find saved folder with name "+name_of_saved_folder,
                        5
                );
    }

    public String GetArticleTitle() {
        WebElement title_element = waitForTitleElement();
        return title_element.getAttribute("text");
    }

    public WebElement waitForTitleElementMy() {
        return this.waitForElementPresent(By.id(TITLE), "Cannot find article title on page", 15);
    }

    public String GetArticleTitleMy() {
        WebElement title_element1 = waitForTitleElementMy();
        return title_element1.getAttribute("text");

    }

    public void AddArticleToMyList (String name_of_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "cannot find button 'more options'",
                5
        );

        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_BUTTON),
                "cannot find option to add article to reading list'",
                5
        );
        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "cannot find GOT IT tip overlay",
                5
        );
        this.waitForElementAndClear(
                By.id(MY_LIST_MAME_INPUT),
                "Cannot find input to set name of article folder ",
                5
        );
        this.waitForElementAndSendKeys(
                By.id(MY_LIST_MAME_INPUT),
                "Learning Programming",
                "Cannot put text into article folder input",
                5
        );
        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "cannot press OK button",
                5
        );

    }
    public void CloseArticle ()
    {
        waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "cannot close article, cannot find x button",
                5
        );
    }
    public void AddArticleToFolderInMyList (String name_of_saved_folder)
    {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "cannot find button 'more options'",
                5
        );
        this.waitForElementAndClick(
                By.xpath(ADD_TO_MY_LIST_BUTTON),
                "cannot find option to add article to reading list'",
                5
        );

        ClickInSavedFolder(name_of_saved_folder);

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "cannot press OK button",
                5
        );
    }

}
