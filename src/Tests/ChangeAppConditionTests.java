package Tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class ChangeAppConditionTests extends CoreTestCase {

    @Test
    public void testChangeScreenOrientationOnSearchResults() {
        String search_line = "Java";


        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");

        String title_before_rotation = ArticlePageObject.getArticleTitle();

        this.rotateScreenLandscape();

        String title_after_rotation = ArticlePageObject.getArticleTitle();

        assertEquals(
                "Acticale title have been chnaged after screen rotation", title_before_rotation, title_after_rotation
        );

        this.rotateScreenPortrait();
        String title_after_second_rotation = ArticlePageObject.getArticleTitle();
        assertEquals(
                "Acticale title have been chnaged after second screen rotation", title_after_second_rotation, title_after_rotation
        );
        this.rotateScreenLandscape();
    }

    @Test
    public void testCheckSearchActicleInBackground() {

        String search_line = "Java";
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line);

        SearchPageObject.waitForSearchResult("Object-oriented programming language");

        this.background();

        SearchPageObject.waitForSearchResult("Object-oriented programming language");

    }

}
