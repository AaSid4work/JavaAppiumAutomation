package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

abstract  public class NavigationUI  extends MainPageObject
{
 protected static  String
    MY_LISTS_LINK ;
    public NavigationUI (AppiumDriver driver)

    {
        super(driver);
    }

public void ClickMyList ()
{
    this.waitForElementAndClick(
            MY_LISTS_LINK,
            "cannot find navigation button to My lists",
            5
    );

}


}
