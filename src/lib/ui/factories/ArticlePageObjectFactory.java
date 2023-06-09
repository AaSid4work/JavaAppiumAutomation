package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.Android.AndroidArticlePageObject;
import lib.ui.ArticlePageObject;
import lib.ui.IOS.iOSArticlePageObject;


public class ArticlePageObjectFactory {

        public static ArticlePageObject get(AppiumDriver driver) {
            if (Platform.getInstance().isIOS()) {
                return new AndroidArticlePageObject(driver);
            } else {
                return new iOSArticlePageObject(driver);
            }

        }
    }
}
