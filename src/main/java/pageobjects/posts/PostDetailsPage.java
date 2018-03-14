package pageobjects.posts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.BasePage;
import utilities.PlatformManager;

public class PostDetailsPage extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(PostDetailsPage.class);


    public PostDetailsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    @AndroidFindBy(xpath = "//*[@text='Post Details']")
    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"Post Details\"]")
    private MobileElement pageTitle;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/tvTitle_IPDH")
    @iOSFindBy(accessibility = "Post Title")
    private MobileElement postTitle;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etComment_FPD")
    @iOSFindBy(accessibility = "Comments Field")
    private MobileElement commentField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/ivSend_FPD")
    @iOSFindBy(accessibility = "ic send comment")
    private MobileElement sendCommentButton;


    public MobileElement getPageTitle() {
        return pageTitle;
    }

    public MobileElement getPostTitle() {
        return postTitle;
    }

    public PostDetailsPage inputComment(String comment) {
        commentField.sendKeys(comment);
        hideOpenKeyboard(driver);
        logger.info(String.format("Comment '%s' is inputed", comment));
        return this;
    }

    public PostDetailsPage tapSendCommentButton() {
        sendCommentButton.click();
        logger.info("Send Comment button is clicked");
        return this;
    }
}
