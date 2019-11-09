package Tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;
import org.openqa.selenium.By;

public class MyListsTests extends CoreTestCase {

    @Test
    public void testSaveFirstArticleToMyList() {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitlePresent();
        String article_title= ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";



        ArticlePageObject.addArticleToNewList(name_of_folder);

        ArticlePageObject.closeArticle();
        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);
        MyListsPageObject.swipeArticleToDelete(article_title);






    }

    //Написать тест, который:

    //1. Сохраняет две статьи в одну папку

    //2. Удаляет одну из статей

    //3. Убеждается, что вторая осталась

    //4. Переходит в неё и убеждается, что title совпадает

    @Test
    public void testSaveTwoArticles() {
        //Find first arcticle

        SearchPageObject SearchPageObject = new SearchPageObject(driver);
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickByArticleWithSubstring("Object-oriented programming language");
        ArticlePageObject.waitForTitlePresent();

        String article_title_1= ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";



        ArticlePageObject.addArticleToNewList(name_of_folder);

        ArticlePageObject.closeArticle();



        //add second arcticle

        String search_line_2 = "Appium";

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine(search_line_2);
        SearchPageObject.clickByArticleWithSubstring("Appium");
        ArticlePageObject.waitForTitlePresent();
        String article_title_2= ArticlePageObject.getArticleTitle();
        ArticlePageObject.addArticleToExistingList(name_of_folder);
        ArticlePageObject.closeArticle();



        //Check that both acticles are added


        NavigationUI NavigationUI = new NavigationUI(driver);
        NavigationUI.clickMyLists();
        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);
        MyListsPageObject.openFolderByName(name_of_folder);

        MyListsPageObject.waitForArticleAppearByTitle(article_title_1);
        MyListsPageObject.waitForArticleAppearByTitle(article_title_2);



        //delete first article
        MyListsPageObject.swipeArticleToDelete(article_title_1);




        //Check that the second article stay untouched
        MyListsPageObject.waitForArticleAppearByTitle(article_title_2);
        MyListsPageObject.openArticleByTitle(article_title_2);


        String title_second_article_after_deletion = ArticlePageObject.getArticleTitle();


        assertEquals(
                "Article title is not correct", title_second_article_after_deletion, article_title_2);
    }




}
