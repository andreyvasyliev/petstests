package pageobjects.posts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.TabBarPage;
import utilities.PlatformManager;

public class CommunityPage extends TabBarPage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(CommunityPage.class);


    public CommunityPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    @AndroidFindBy(id = "com.foxtrapp.pets:id/tvPostName_IP")
    @iOSFindBy(xpath = "(//XCUIElementTypeImage[@name=\"ic_right_arrow_orange\"])[1]")
    private MobileElement firstPostInTheList;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/fabAddPost_FP")
    @iOSFindBy(accessibility = "ic add floating")
    private MobileElement addPostButton;


    public MobileElement getFirstPostInTheList() {
        return firstPostInTheList;
    }

    public CreatePostPage tapAddPostButton() {
        addPostButton.click();
        logger.info("Add Post Floating button is clicked");
        return new CreatePostPage(driver);
    }

}
