package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import utilities.PlatformManager;

public class HomePage extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;

    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }
}
