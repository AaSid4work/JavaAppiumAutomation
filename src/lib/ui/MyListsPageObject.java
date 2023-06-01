package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class MyListsPageObject extends MainPageObject{
    public MyListsPageObject (AppiumDriver driver)
    {
        super(driver);
    }
    public static final String
    FOLDER_BY_NAME_TPL = "//*[@text = '{FOLDER_NAME}']",
    ARTICLE_BY_TITLE_TPL = "//*[@text = '{TITLE}']";
    private static String GetFolderXpathByName (String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);

    }
    private static String GetSavedArticleXpathByName(String ArticleTitle)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}",ArticleTitle );
    }
    public void OpenFolderByName (String name_of_folder)
    {
        String folderNameXpath = GetFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                By.xpath(folderNameXpath),
                "cannot find "+ name_of_folder,
                5
        );
    }
    public void waitForArticleToAppearByTitle(String ArticleTitle )
    {
        String articleXpath = GetSavedArticleXpathByName(ArticleTitle);
        this.waitForElementPresent(By.xpath(articleXpath), "Cannot find article by title "+ArticleTitle, 13);
    }
    public void waitForArticleToDisappearByTitle(String ArticleTitle )
    {
        String articleXpath = GetSavedArticleXpathByName(ArticleTitle);
        this.waitForElementNotPresent(By.xpath(articleXpath), "saved article slill present with title"+ArticleTitle, 13);
    }
    public void swipeByArticleToDelete(String ArticleTitle)
    {
        this.waitForArticleToAppearByTitle(ArticleTitle);
        String articleXpath = GetSavedArticleXpathByName(ArticleTitle);
        this.elementToSwipeLeft
                (
                By.xpath(articleXpath),
                "Cannot find saved article"
        );
        this.waitForArticleToDisappearByTitle(ArticleTitle);
    }

}
