package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import junit.framework.TestCase;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class CoreTestCase extends TestCase {


    protected AppiumDriver driver;
    private static String AppiunURL = "http://127.0.0.1:4723/wd/hub";

    private static final String
            PLATFORM_ANDROID = "android",
            PLATFORM_IOS = "ios";

    @Override
    protected void setUp() throws Exception {

        super.setUp();
        setRequiredDriver();
        this.rotateScreenPortrait();


    }




    @Override
    protected   void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }

    protected void rotateScreenPortrait()
    {
        driver.rotate(ScreenOrientation.PORTRAIT);
    }

    protected void rotateScreenLandscape()
    {
        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

    protected void background()
    {
        driver.runAppInBackground(2);
    }

    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {
        String platform=System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals(PLATFORM_ANDROID)){
            capabilities.setCapability("platformName", "Android");
            capabilities.setCapability("deviceName", "AndroidTestDevice");
            capabilities.setCapability("platformVersion", "8.0");
            capabilities.setCapability("automationName", "Appium");
            capabilities.setCapability("appPackage", "org.wikipedia");
            capabilities.setCapability("app", "C:/My/GitHub/JavaAppiumAuthomation/apks/org.wikipedia.apk");
        }
        else if(platform.equals(PLATFORM_IOS)){
            capabilities.setCapability("platformName", "iOS");
            capabilities.setCapability("deviceName", "iPhone SE");
            capabilities.setCapability("platformVersion", "11.3");
            capabilities.setCapability("app", "Users/hi/Desktop/JavaAppiumAutomation/apks/Wikipedia.app");
        }
        else {
            throw new Exception("Cannot get run platform from env variable. Platform value "+platform);
        }
        return capabilities;


    }

    private void setRequiredDriver() throws Exception {
        String platform=System.getenv("PLATFORM");


        if(platform.equals(PLATFORM_ANDROID)) {
            driver = new AndroidDriver(new URL(AppiunURL), getCapabilitiesByPlatformEnv());
        }

        else if(platform.equals(PLATFORM_IOS)){
                driver = new IOSDriver(new URL(AppiunURL), getCapabilitiesByPlatformEnv());
        }
        else {
            throw new Exception("Cannot get run platform from env variable. Platform value "+platform);
        }
        }


    }





