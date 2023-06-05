package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class WelcomePageObject extends MainPageObject {
    private static final String
    STEP_LEARN_MORE_LINK = "id:Википедия создается совместно волонтерами и состоит из более 40 миллионов статей на более чем 300 языках.",
    STEP_NEW_WAYS_TO_EXPLORE_TEXT = "id:Новые способы изучения",
    STEP_ADD_OR_ADDED_PREFER_LANG_LINK = "xpath://XCUIElementTypeStaticText[@name=\"Добавить или изменить предпочтительные языки\"]",
    STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK = "xpath://XCUIElementTypeStaticText[@name=\"Узнать подробнее о сборе данных\"]",
    NEXT_LINK = "xpath://XCUIElementTypeStaticText[@name=\"Далее\"]",
    GET_STARTED_BUTTON = "xpath://XCUIElementTypeStaticText[@name=\"Начать\"]",
    SKIP = "id:Skip";
    public WelcomePageObject(AppiumDriver driver) {
        super(driver);
    }

    public void waitForLearnMoreLink() {
        this.waitForElementPresent(
                STEP_LEARN_MORE_LINK,
                "Cannot find 'LearnMore'",
                10
        );
    }

    public void ClicknextButton()
    {
        this.waitForElementAndClick(NEXT_LINK,
                "Cant find 'Next' button",
                10
        );
    }
    public void waitForNewWayToExplore () {
        this.waitForElementPresent(STEP_NEW_WAYS_TO_EXPLORE_TEXT,
                "Cannot find 'Новые способы изучения'",
                10
        );
    }
    public void waitForAddedPreferLangText () {
        this.waitForElementPresent(
                STEP_ADD_OR_ADDED_PREFER_LANG_LINK,
                "Cannot find link to change language'",
                10
        );
    }
    public void waitForLearnMoreAboutDataCollectedText () {
        this.waitForElementPresent(
                STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
                "Cannot find link to data collected'",
                10
        );
    }
    public void ClickGetStartedButton ()
    {
        this.waitForElementAndClick(
                GET_STARTED_BUTTON,
                "cannot find Start button",
                10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(
                SKIP,
                "Cannot find and click skip button",
                5);
    }
}