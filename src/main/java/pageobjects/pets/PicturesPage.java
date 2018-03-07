package pageobjects.pets;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.account.MyAccountScreen;
import pageobjects.base_pages.ImagesPage;
import utilities.PlatformManager;

public class PicturesPage extends ImagesPage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(PicturesPage.class);


    public PicturesPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    @AndroidFindBy(id = "com.foxtrapp.pets:id/ivAddPicture_FAPP")
    private MobileElement bigPlusButton;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/tvNext_BNB")
    private MobileElement doneButton;

    public MobileElement getBigPlusButton() {
        return bigPlusButton;
    }

    public MyAccountScreen tapDoneButton() {
        doneButton.click();
        logger.info("DONE button is clicked");
        return new MyAccountScreen(driver);
    }


}
