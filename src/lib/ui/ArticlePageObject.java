package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import lib.ui.factories.MyListsObjectFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

abstract public class ArticlePageObject extends MainPageObject {
    protected static String
        TITLE,
        FOOTER_ELEMENT,
        OPTIONS_BUTTON,
        OPTIONS_ADD_TO_MY_LIST_BUTTON,
        ADD_TO_MY_LIST_OVERLAY,
        MY_LIST_NAME_INPUT,
        MY_LIST_OK_BUTTON,
        CLOSE_ARTICLE_BUTTON,
        TITLE_ELEMENT;



    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitlePresent()
    {
        return this.waitForElementPresent((TITLE),
                "Cannot find article title on page",
                15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitlePresent();
        if (Platform.getInstance().isAndroid()){
            return title_element.getAttribute("text");

        }
        else
        {return title_element.getAttribute("name");}
    }

    public void swipeToFooter()


    {
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement((FOOTER_ELEMENT),
                    "Cannot find the end of the article",
                    40);
        }
        else {
            this.swipeUpTillElementAppear((FOOTER_ELEMENT),
                    "Cannot find the end of the article",
                    40);
        }
    }





    public void addArticleToNewList(String name_of_folder)
    {
        initiateArticleAdditionToList();

        this.waitForElementAndClick(
                (ADD_TO_MY_LIST_OVERLAY),
                "Cannot find Got it tip overlay",
                5);

        this.waitForElementAndClear(
                (MY_LIST_NAME_INPUT),
                "Cannot find input to set name of acticle",
                5
        );


        this.waitForElementAndSendKeys(
                (MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot input folder name.",
                5
        );

        this.waitForElementAndClick(
                (MY_LIST_OK_BUTTON),
                "Cannot press OK button.",
                5
        );
    }

    public void initiateArticleAdditionToList() {
        this.waitForElementAndClick(
                (OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                (OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );
    }

    public void addArticleToExistingList(String name_of_folder)
    {
        this.initiateArticleAdditionToList();
        MyListsPageObject MyListsPageObject = MyListsObjectFactory.get(driver);
        MyListsPageObject.openFolderByName(name_of_folder);



    }



    public void closeArticle()
    {
       this.waitForElementAndClick(
                (CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                5);
    }

    public void assertActicleTitleElementPresent()
    {
        assertElementPresent((TITLE_ELEMENT),
                "The title element of article is not present");
    }

    public void checkTextInAllFoundArticles()
    {
        List<WebElement> search_result = driver.findElements(By.id(TITLE));
        Assert.assertTrue("Not all search results contains required text", this.checkTextForItemsInListWebElements(search_result, "Java"));
    }


    public void addArticlesToMySaved() {
        this.waitForElementAndClick(OPTIONS_ADD_TO_MY_LIST_BUTTON,
                "Cannot find option to add article to reading list",
                5);
    }
}
