package lib;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

public class Platform {


    private static final String
            PLATFORM_ANDROID = "android",
            PLATFORM_IOS = "ios",
            APPIUM_URL="http://127.0.0.1:4723/wd/hub";

    public static Platform instance;

    public static Platform getInstance()
    {
        if (instance == null)
        {
            instance = new Platform();
        }
        return instance;
    }

    public boolean isAndroid(){
        return isPlatform(PLATFORM_ANDROID);
    }

    public boolean isIOS(){
        return isPlatform(PLATFORM_IOS);
    }

    private DesiredCapabilities getAndroidDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "8.0");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("app", "C:/My/GitHub/JavaAppiumAuthomation/apks/org.wikipedia.apk");
        return capabilities;
    }

    private DesiredCapabilities getIOSDesiredCapabilities()
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("platformName", "iOS");
        capabilities.setCapability("deviceName", "iPhone X");
        capabilities.setCapability("platformVersion", "11.4");
        capabilities.setCapability("app", "/Users/hi/Desktop/Wikipedia.app");
        return capabilities;
    }




    private DesiredCapabilities getCapabilitiesByPlatformEnv() throws Exception {

        String platform=System.getenv("PLATFORM");
        DesiredCapabilities capabilities = new DesiredCapabilities();

        if(platform.equals(PLATFORM_ANDROID)){
            capabilities=this.getAndroidDesiredCapabilities();
        }
        else if(platform.equals(PLATFORM_IOS)){
            capabilities = this.getIOSDesiredCapabilities();
        }
        else {
            throw new Exception("Cannot get run platform from env variable. Platform value "+platform);
        }
        return capabilities;


    }

    public AppiumDriver getDriver() throws Exception {

        if(this.isAndroid()) {
            return new AndroidDriver(new URL(APPIUM_URL), getAndroidDesiredCapabilities());
        }

        else if(this.isIOS()){
            return new IOSDriver(new URL(APPIUM_URL), getIOSDesiredCapabilities());
        }
        else {
            throw new Exception("Cannot detect type of driver. Platform value "+this.getPlatformVar());
        }
    }

    private boolean isPlatform(String my_platform) {
        String platform = this.getPlatformVar();
        return my_platform.equals(platform);
    }

    private String getPlatformVar() {
        return System.getenv("PLATFORM");
    }


}
