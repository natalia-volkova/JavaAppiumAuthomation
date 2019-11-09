package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class MyListsPageObject extends MainPageObject {

    private static final String
        FOLDER_BY_NAME_TPL= "//android.widget.TextView[contains(@text,'{FOLDER_NAME}')]",
        ARTICLE_BY_TITLE_TPL="//*[@text='{TITLE}']";


    /*TEMPLATES METHODS*/
    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TPL.replace("{FOLDER_NAME}", name_of_folder);
    }
    /*TEMPLATES METHODS*/

    /*TEMPLATES METHODS*/
    private static String getSavedArticleXpathByTitle(String title)
    {
        return ARTICLE_BY_TITLE_TPL.replace("{TITLE}", title);
    }
    /*TEMPLATES METHODS*/



    public MyListsPageObject(AppiumDriver driver) {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)
    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(

                By.xpath(folder_name_xpath),
                "Cannot find folder by name "+name_of_folder,
                5
        );
    }

    public void swipeArticleToDelete(String article_title)
    {
        String article_title_xpath = this.getSavedArticleXpathByTitle(article_title);

        this.swipeElementToLeft(
                By.xpath(article_title_xpath),
                "Cannot find saved article"

        );
        this.waitForArticleDisappearByTitle(article_title);


    }

    public void waitForArticleDisappearByTitle(String article_title)
    {
        this.waitForArticleAppearByTitle(article_title);
        String article_title_xpath = this.getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                By.xpath(article_title_xpath),
                "Saved article still present with title "+article_title,
                5
        );
    }

    public void waitForArticleAppearByTitle(String article_title)
    {
        String article_title_xpath = this.getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                By.xpath(article_title_xpath),
                "Cannot find article with title "+article_title,
                5
        );
    }

    public void openArticleByTitle(String article_title) {
        String article_title_xpath = this.getSavedArticleXpathByTitle(article_title);
        this.waitForElementAndClick(
                By.xpath(article_title_xpath),
                "Cannot find and open article with title "+article_title,
                5
        );
    }
}
