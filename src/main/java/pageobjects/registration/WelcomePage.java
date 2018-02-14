package pageobjects.registration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pageobjects.BasePage;
import utilities.PlatformManager;

public class WelcomePage extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;


    public WelcomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    private String useEmailAndroid = "com.foxtrapp.pets:id/cvEmailPassLogin_FW";
    private String useEmailiOS = "Use Email Instead";

    public LoginPage tapUseEmailButton() {
        if(platform.equals(PlatformManager.Platform.ANDROID))
        driver.findElementById(useEmailAndroid).click();

        else if(platform.equals(PlatformManager.Platform.IOS))
            driver.findElementByAccessibilityId(useEmailiOS).click();

        return new LoginPage(driver);
    }


}
