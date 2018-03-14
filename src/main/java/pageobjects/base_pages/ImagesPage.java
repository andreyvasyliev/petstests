package pageobjects.base_pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utilities.PlatformManager;

import java.util.concurrent.TimeUnit;

public class ImagesPage extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(ImagesPage.class);


    public ImagesPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    // BUTTONS

    @AndroidFindBy(id = "com.foxtrapp.pets:id/tvCamera_FBS")
    @iOSFindBy(accessibility = "Take Photo")
    private MobileElement cameraButton;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/tvGallery_FBS")
    @iOSFindBy(accessibility = "Choose From Library")
    private MobileElement galleryButton;

    // TODO Notification button on iOS need to fix
    @AndroidFindBy(id = "com.android.packageinstaller:id/permission_allow_button")
    private MobileElement allowButton;

    // HUAWEI CAMERA BUTTONS

    @AndroidFindBy(id = "com.huawei.camera:id/shutter_button")
    private MobileElement takePictureButton;

    @AndroidFindBy(id = "com.huawei.camera:id/btn_review_confirm")
    private MobileElement confirmPhoto;

    public void selectCamera() {
        cameraButton.click();
        logger.info("Camera is selected");
    }

    public void selectGallery() {
        galleryButton.click();
        logger.info("Gallery is selected");
    }

    public void allowPhotos() {

        if(platform.equals(PlatformManager.Platform.ANDROID)) {

            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

            try {
                allowButton.click();

            } catch (WebDriverException e) {
            }

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        } else return;
    }

    public void takePicture() {

        if(platform.equals(PlatformManager.Platform.ANDROID)) {

                takePictureButton.click();
                logger.info("Take Picture is clicked");
                confirmPhoto.click();
                logger.info("Camera Picture is confirmed");

        } else return;

    }

    public void addImage() {

    }

}

