package Tests;

import lib.CoreTestCase;
import lib.ui.WelcomeScreenPageObject;
import org.junit.Test;

public class WelcomeScreenTests extends CoreTestCase{
    @Test
    public void testPassTroughtWelcome(){
        if (Platform.getInstance().isAndroid()){
            return;
        }
        WelcomeScreenPageObject WelcomePage= new WelcomeScreenPageObject(driver);
        WelcomePage.waitForLearnMoreLink();
        WelcomePage.clickNextButton();

        WelcomePage.waitForNewWayToExploreText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForAddOrEditPrefferedLanguageText();
        WelcomePage.clickNextButton();

        WelcomePage.waitForLearnMoreAboutDataCollctedText();
        WelcomePage.clickGetStartedButton();
    }
}
