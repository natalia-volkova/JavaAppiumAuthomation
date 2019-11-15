package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.ArticlePageObject;

public class IOSArticlePageObject extends ArticlePageObject {
    static {
        TITLE="";
        FOOTER_ELEMENT = "";
        OPTIONS_BUTTON="";
        OPTIONS_ADD_TO_MY_LIST_BUTTON="";
        ADD_TO_MY_LIST_OVERLAY = "";
        MY_LIST_NAME_INPUT="";
        MY_LIST_OK_BUTTON="";
        CLOSE_ARTICLE_BUTTON="";
        TITLE_ELEMENT="";
    }

    public IOSArticlePageObject(AppiumDriver driver) {
        super(driver);
    }
}
