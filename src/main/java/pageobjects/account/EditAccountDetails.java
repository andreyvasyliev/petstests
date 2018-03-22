package pageobjects.account;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.aspectj.lang.annotation.AdviceName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.BasePage;
import utilities.PlatformManager;

public class EditAccountDetails extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(EditAccountDetails.class);


    public EditAccountDetails(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    // LIST OF FIELDS AND BUTTON

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etFirstName_FEA")
    @iOSFindBy(accessibility = "First Name")
    private MobileElement firstNameField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etEmail_FEA")
    @iOSFindBy(accessibility = "Email Address")
    private MobileElement emailAddressField;

    public MobileElement getFirstNameField() {
        return firstNameField;
    }

    public MobileElement getEmailAddressField() {
        return emailAddressField;
    }
}
