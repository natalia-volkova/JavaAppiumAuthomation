package lib.ui;

import io.appium.java_client.AppiumDriver;

public class WelcomeScreenPageObject extends MainPageObject{
    private static final String
            STEP_LEARN_MORE_LINK = "id:Learn more about Wikipedia",
            STEP_NEW_WAY_TO_EXPLORE_TEXT="id:New ways to explore",
            NEXT_LINK="id:Next",
            STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK="id:Add or edit preferred languages",
            STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK="id:Learn more about data collected",
            GET_STARTED_BUTTON="id:Get started",
            SKIP="id:Skip";



    public WelcomeScreenPageObject(AppiumDriver driver){
        super(driver);
    }

    public void waitForLearnMoreLink()

    {this.waitForElementPresent(STEP_LEARN_MORE_LINK,
            "Cannot find 'learn more about Wikipedia' link",
    10);
    }

    public void waitForNewWayToExploreText()

    {this.waitForElementPresent(STEP_NEW_WAY_TO_EXPLORE_TEXT,
            "Cannot find 'New ways to explore' link",
            10);
    }

    public void waitForAddOrEditPrefferedLanguageText()

    {this.waitForElementPresent(STEP_ADD_OR_EDIT_PREFERRED_LANG_LINK,
            "Cannot 'Add or edit preferred language' link",
            10);
    }

    public void waitForLearnMoreAboutDataCollctedText()

    {this.waitForElementPresent(STEP_LEARN_MORE_ABOUT_DATA_COLLECTED_LINK,
            "Cannot find 'Learn more about data collected' link",
            10);
    }

    public void clickNextButton()
    {
        waitForElementAndClick(NEXT_LINK,
                "Cannot find next link",10);
    }

    public void clickGetStartedButton()
    {
        waitForElementAndClick(GET_STARTED_BUTTON,
                "Cannot find get started button",10);
    }

    public void clickSkip() {
        this.waitForElementAndClick(SKIP, "Cannot find and click Skip button", 5);
    }
}
