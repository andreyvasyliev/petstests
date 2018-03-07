package pageobjects.posts;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.BasePage;
import utilities.PlatformManager;

public class CreatePostPage extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(CreatePostPage.class);


    public CreatePostPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }


    @AndroidFindBy(id = "com.foxtrapp.pets:id/rbDogType_FCP")
    @iOSFindBy(accessibility = "Dog")
    private MobileElement dogIcon;

    //TODO Add Accessibility id for Post title and comment field

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etTitle_FCP")
    private MobileElement postTitleField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etMessage_FCP")
    private MobileElement messageField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/btn_CB")
    @iOSFindBy(accessibility = "Send")
    private MobileElement sendButton;

    public MobileElement getPostTitleField() {
        return postTitleField;
    }

    public CreatePostPage tapDogIcon() {
        dogIcon.click();
        logger.info("Dog Icon is clicked");
        return this;
    }

    public CreatePostPage inputPostTitle(String postTitle) {
        postTitleField.sendKeys(postTitle);
        hideOpenKeyboard(driver);
        logger.info("Post Title {} is inputed", postTitle);
        return this;
    }

    public CreatePostPage inputMessage(String message) {
        messageField.sendKeys(message);
        hideOpenKeyboard(driver);
        logger.info("Message {} is inputed", message); //TODO Fix that  long message isn't shown in Logs
        return this;
    }

    public CommunityPage tapSendButton() {
        sendButton.click();
        logger.info("Send Post button is clicked");
        return new CommunityPage(driver);
    }
}
