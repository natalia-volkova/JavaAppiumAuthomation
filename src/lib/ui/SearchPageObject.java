package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class SearchPageObject extends MainPageObject {

    protected static String
            SEARCH_INIT_ELEMENT,
            SEARCH_INPUT ,
            SEARCH_CANCEL_BUTTON,
            SEARCH_RESULT_BY_SUBSTRING_TPL,
            SEARCH_RESULT_ELEMENT,
            SEARCH_EMPTY_RESULT_ELEMENT ,
            SEARCH_RESULT_TITLE_DESCRIPTION_TPL;

    public SearchPageObject(AppiumDriver driver){
        super(driver);
    }

    /*TEMPLATES METHODS*/
    private static String getResultSearchElement(String substring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", substring);
    }
    /*TEMPLATES METHODS*/

    /*TEMPLATES METHODS*/
    private static String getResultSearchTitleDescriptionElement(String title, String description)
    {
        return SEARCH_RESULT_TITLE_DESCRIPTION_TPL.replace("{TITLE}", title).replace("{DESCRIPTION}", description);
    }
    /*TEMPLATES METHODS*/


    public void waitForCancelButtonToAppear()
    {
        this.waitForElementPresent((SEARCH_CANCEL_BUTTON), "Cannot find search cancel button", 5);
    }

    public void waitForCancelButtonToDisappear()
    {
        this.waitForElementNotPresent((SEARCH_CANCEL_BUTTON), "Search cancel button is still present", 5);
    }

    public void clickCancelSearch()
    {
       this.waitForElementAndClick((SEARCH_CANCEL_BUTTON), "Cannot find and click search cancel button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(((SEARCH_INIT_ELEMENT)), "Cannot find and click init element", 15);
        this.waitForElementPresent((SEARCH_INPUT), "Cannot find search input after clicking search init element");
    }

    public void typeSearchLine(String search_line)

    {
        this.waitForElementAndClear(SEARCH_INPUT,
                "It is not possible to clear search input", 10);
        this.waitForElementAndSendKeys((SEARCH_INPUT), search_line, "Cannot find and type into search input", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath=getResultSearchElement(substring);
        this.waitForElementPresent((search_result_xpath), "Cannot find search result with substring "+substring);
    }

    public void clickByArticleWithSubstring(String substring)
    {
        String search_result_xpath=getResultSearchElement(substring);
        this.waitForElementAndClick((search_result_xpath), "Cannot find and click search result with substring "+substring, 10);
    }

    public String getSearchInputText()
    {
        WebElement search_input_element = waitSearchInputPresent();
        if (Platform.getInstance().isAndroid()){
            return search_input_element.getAttribute("text");

        }
        else
        {return search_input_element.getAttribute("name");}

    }

    public WebElement waitSearchInputPresent(){
        return this.waitForElementPresent((SEARCH_INPUT),
                "Cannot find search input",
                5);
    }

    public int getAmountOfFoundArticles()
    {


        waitFotSearchResultPresent();

        return this.getAmountOfElements(
                (SEARCH_RESULT_ELEMENT)
        );


    }

    public void waitFotSearchResultPresent() {

        this.waitForElementPresent(
               (SEARCH_RESULT_ELEMENT),
                "Cannot find anything by request",
                30
        );
    }

    public void waitFotSearchResultDisappears() {

        this.waitForElementNotPresent(
               (SEARCH_RESULT_ELEMENT),
                "Cannot find anything by request",
                30
        );
    }

    public void waitForEmptyResultLabel()
    {

        this.waitForElementPresent(
                (SEARCH_EMPTY_RESULT_ELEMENT),
                "Cannot find empty result element",
                15);

    }

    public void assertThereIsNoResultSearch()
    {
        this.assertElementNotPresent((SEARCH_RESULT_ELEMENT), "No supposed no to find any results");
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath=getResultSearchTitleDescriptionElement(title, description);
        this.waitForElementPresent((search_result_xpath), "Cannot find search result with title "+title+" and description "+description);
    }


}
