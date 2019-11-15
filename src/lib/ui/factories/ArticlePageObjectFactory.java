package lib.ui.factories;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.android.AndroidArticlePageObject;
import lib.ui.ios.IOSArticlePageObject;


public class ArticlePageObjectFactory {
    
    public static ArticlePageObjectFactory get(AppiumDriver driver)
    {
        if(Platform.getInstance().isAndroid()){
            return new AndroidArticlePageObject(driver);
        }
        else{return new IOSArticlePageObject(driver);}

    }
}
