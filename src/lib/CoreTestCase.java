package lib;

import io.appium.java_client.AppiumDriver;
import junit.framework.TestCase;
import lib.ui.WelcomeScreenPageObject;
import org.openqa.selenium.ScreenOrientation;




public class CoreTestCase extends TestCase {

    protected AppiumDriver driver;
    protected Platform Platform;



    @Override
    protected void setUp() throws Exception {

        super.setUp();
        driver = Platform.getInstance().getDriver();
        this.rotateScreenPortrait();
        this.skipWelcomePageForIOSApp();


    }




    @Override
    protected   void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        if (ScreenOrientation.LANDSCAPE.equals(true)){
        driver.rotate(ScreenOrientation.PORTRAIT);}
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void background()
    {
        driver.runAppInBackground(2);
    }

    private void skipWelcomePageForIOSApp()
    {
        if (Platform.getInstance().isIOS())
        {
            WelcomeScreenPageObject WelcomeScreenPageObject = new WelcomeScreenPageObject(driver);
            WelcomeScreenPageObject.skipWelcomeScreen();
        }
    }





    }





