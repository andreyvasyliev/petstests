package pageobjects.registration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.BasePage;
import utilities.PlatformManager;

import java.util.concurrent.TimeUnit;

public class WelcomePage extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(WelcomePage.class);

    public WelcomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    @AndroidFindBy(id = "com.foxtrapp.pets:id/cvEmailPassLogin_FW")
    @iOSFindBy(accessibility = "Use Email Instead")
    private MobileElement btnUseEmail;

    private String allowNotificationsButtoniOS = "Allow"; // Name


    public MobileElement getBtnUseEmail() {
        return btnUseEmail;
    }

    public LoginPage tapUseEmailButton() {
        btnUseEmail.click();

        logger.info("LoginPage screen is opened");

        return new LoginPage(driver);

    }

    public void allowNotifications() {

        if(platform.equals(PlatformManager.Platform.IOS)) {

            driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

            try {
                driver.findElementByName(allowNotificationsButtoniOS).click();
            } catch (WebDriverException e) {
            }

            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);


        } else return;

    }




}
