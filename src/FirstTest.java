import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("app", "C:/My/GitHub/JavaAppiumAuthomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);


    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void firstTest() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5);


        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find Search Wikipedia input",
                5);


        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can't find 'Object oriented programming language' topic searching by 'Java'",
                15);

    }

    @Test
    public void testCancelSearch() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5);


        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text, 'Search…')]"),
                "Java",
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndClear(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find search field",
                5);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5);

        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still present on the page",
                5);

    }

    @Test
    public void testCompareArticleTitle() {

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5);


        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search Wikipedia input",
                5);

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Object-oriented programming language']"),
                "Can't find 'Object oriented programming language' topic searching by 'Java",
                5);

        WebElement title_element = waitForElementPresent(
                By.id("org.wikipedia:id/view_page_title_text"),
                "Cannot find article title",
                15);

        String article_title = title_element.getAttribute("text");

        Assert.assertEquals(
                "We see unexpected title",
                article_title,
                "Java (programming language)"
        );

    }

    //Написать функцию, которая проверяет наличие текста “Search…” в строке поиска перед вводом текста и помечает тест упавшим, если такого текста нет.

    @Test
    public void testCheckTextInSearchField() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5);

        WebElement search_field = waitForElementPresent(
                By.id("org.wikipedia:id/search_src_text"),
                "Cannot find Search Wikipedia input",
                5);

        String search_field_text = search_field.getAttribute("text");

        Assert.assertEquals("The text is Search field is not correct", search_field_text, "Search…");

    }


    //Ищет какое-то слово
    //Убеждается, что найдено несколько статей
    //Отменяет поиск
    //Убеждается, что результат поиска пропал


    @Test
    public void test2CancelSearch()

    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5);


        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search Wikipedia input",
                5);

        waitForElementPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "The search result is not present",
                15);

        int number_of_elements_found = elementCount(By.id("org.wikipedia:id/page_list_item_container"));

        Assert.assertTrue("The number of items found is not more than 1", number_of_elements_found > 1);

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_close_btn"),
                "Cannot find X to cancel search",
                5);

        waitForElementNotPresent(
                By.id("org.wikipedia:id/page_list_item_container"),
                "The search results are still on the page.",
                5);

    }


    // Ищет какое-то слово
    //Убеждается, что в каждом результате поиска есть это слово

    @Test
    public void testCheckAllSearchResults() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find Search Wikipedia",
                5);


        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_src_text"),
                "Java",
                "Cannot find Search Wikipedia input",
                5);

        waitForElementPresent(
                By.id("page_list_item_title"),
                "The search result is not present",
                15);

        int number_of_elements_found = elementCount(By.id("org.wikipedia:id/page_list_item_title"));


        List<WebElement> search_result = driver.findElements(By.id("org.wikipedia:id/page_list_item_title"));
        Assert.assertTrue("Not all search results contains required text", checkTextForItemsInListWebElements(search_result, "Java"));

    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }


    private WebElement waitForElementAndClick(By by, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutSeconds);
        element.sendKeys(value);
        return element;
    }


    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");

        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    public int elementCount(By by) {
        return driver.findElements(by).size();
    }


    public boolean checkTextForItemsInListWebElements(List<WebElement> list, String text) {
        for (WebElement element : list) {
            //System.out.println(element.getText());
            if (!element.getText().contains(text)) {


                System.out.println("Element doesn't contain required text, it contains text:" + element.getText());
                return false;
            }


        }
        return true;
    }
}
