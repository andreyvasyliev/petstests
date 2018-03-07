package pageobjects.pets;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.BasePage;
import pageobjects.base_pages.ImagesPage;
import utilities.PlatformManager;

import java.util.concurrent.TimeUnit;

public class AddPetPage extends ImagesPage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(AddPetPage.class);

    public AddPetPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    @AndroidFindBy(id = "com.foxtrapp.pets:id/ivPlaceHolder_FAPD")
    @iOSFindBy(accessibility = "ic camera")
    private MobileElement avatar;

    //TODO Add acces id for Name and Birthday

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etFirstName_FAPD")
    private MobileElement petNameField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etText_LCS")
    private MobileElement birthdayField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/rbMale_FAPD")
    @iOSFindBy(accessibility = "MALE")
    private MobileElement maleButton;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/rbFemale_FAPD")
    @iOSFindBy(accessibility = "FEMALE")
    private MobileElement femaleButton;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/tvNext_BNB")
    @iOSFindBy(accessibility = "Next")
    private MobileElement nextButton;

    // HUAWEI CALENDAR
    @AndroidFindBy(id = "android:id/button1")
    private MobileElement calendarOkButton;

    public MobileElement getAvatar() {
        return avatar;
    }

    public void tapAvatar() {
        avatar.click();
        logger.info("Avatar is clicked!");
    }

    public AddPetPage inputPetName(String petName) {
        petNameField.sendKeys(petName);
        logger.info(String.format("Name '%s' is inputed", petName));
        hideOpenKeyboard(driver);
        return this;
    }

    public AddPetPage selectBirthday() {
        birthdayField.click();
        logger.info("Birthday Field is clicked");
        calendarOkButton.click();
        logger.info("Calendar 'OK' button is clicked");
        return this;
    }

    public SpecificationsPage tapNextButton() {
        nextButton.click();
        logger.info("Next button is clicked");
        return new SpecificationsPage(driver);
    }

}
