package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ArticlePageObject extends MainPageObject {
    private static final String
        TITLE="org.wikipedia:id/view_page_title_text",
        FOOTER_ELEMENT = "//*[@text='View page in browser']",
        OPTIONS_BUTTON="//android.widget.ImageView[@content-desc='More options']",
        OPTIONS_ADD_TO_MY_LIST_BUTTON="//*[@text='Add to reading list']",
        ADD_TO_MY_LIST_OVERLAY = "org.wikipedia:id/onboarding_button",
        MY_LIST_NAME_INPUT="org.wikipedia:id/text_input",
        MY_LIST_OK_BUTTON="//*[@text='OK']",
        CLOSE_ARTICLE_BUTTON="//android.widget.ImageButton[@content-desc='Navigate up']",
        TITLE_ELEMENT_="//*[@resource-id='org.wikipedia:id/page_list_item_title']";



    public ArticlePageObject(AppiumDriver driver){
        super(driver);
    }

    public WebElement waitForTitlePresent()
    {
        return this.waitForElementPresent(By.id(TITLE),
                "Cannot find article title on page",
                15);
    }

    public String getArticleTitle()
    {
        WebElement title_element = waitForTitlePresent();
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(By.xpath(FOOTER_ELEMENT),
                "Cannot find the end of the article",
                20);
    }

    public void addArticleToNewList(String name_of_folder)
    {
        initiateArticleAdditionToList();

        this.waitForElementAndClick(
                By.id(ADD_TO_MY_LIST_OVERLAY),
                "Cannot find Got it tip overlay",
                5);

        this.waitForElementAndClear(
                By.id(MY_LIST_NAME_INPUT),
                "Cannot find input to set name of acticle",
                5
        );


        this.waitForElementAndSendKeys(
                By.id(MY_LIST_NAME_INPUT),
                name_of_folder,
                "Cannot input folder name.",
                5
        );

        this.waitForElementAndClick(
                By.xpath(MY_LIST_OK_BUTTON),
                "Cannot press OK button.",
                5
        );
    }

    public void initiateArticleAdditionToList() {
        this.waitForElementAndClick(
                By.xpath(OPTIONS_BUTTON),
                "Cannot find button to open article options",
                5
        );

        this.waitForElementAndClick(
                By.xpath(OPTIONS_ADD_TO_MY_LIST_BUTTON),
                "Cannot find option to add article to reading list",
                5
        );
    }

    public void addArticleToExistingList(String name_of_folder)
    {
        this.initiateArticleAdditionToList();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);



    }



    public void closeArticle()
    {
       this.waitForElementAndClick(
                By.xpath(CLOSE_ARTICLE_BUTTON),
                "Cannot close article, cannot find X link",
                5);
    }

    public void assertActicleTitleElementPresent()
    {
        assertElementPresent(By.xpath(TITLE_ELEMENT_),
                "The title element of article is not present");
    }

    public void checkTextInAllFoundArticles()
    {
        List<WebElement> search_result = driver.findElements(By.id(TITLE));
        Assert.assertTrue("Not all search results contains required text", this.checkTextForItemsInListWebElements(search_result, "Java"));
    }




}
