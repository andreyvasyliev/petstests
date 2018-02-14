package pageobjects.registration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import pageobjects.BasePage;
import pageobjects.HomePage;
import utilities.PlatformManager;

public class LoginPage extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;


    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    private String emailFieldAndroid = "com.foxtrapp.pets:id/etEmail_LLV"; // id
    private String emailFieldiOS = "//XCUIElementTypeApplication[@name=\"Ur Pets Life\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell[2]";

    private String passwordFieldAndroid = "com.foxtrapp.pets:id/etPassword_LLV"; // id
    private String passwordFieldiOS = "//XCUIElementTypeApplication[@name=\"Ur Pets Life\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[3]/XCUIElementTypeTable/XCUIElementTypeCell[3]";

    private String loginButtonAndroid ="com.foxtrapp.pets:id/btn_CB"; //id
    private String loginButtoniOS = "Login"; // accessibility id



    public void inputEmail(String email) {
        if(platform.equals(PlatformManager.Platform.ANDROID))
            driver.findElementById(emailFieldAndroid).sendKeys(email);
        else if(platform.equals(PlatformManager.Platform.IOS))
            driver.findElementByXPath(emailFieldiOS).sendKeys(email);
    }

    public void inputPassword(String password) {
        if(platform.equals(PlatformManager.Platform.ANDROID))
            driver.findElementById(passwordFieldAndroid).sendKeys(password);
        else if(platform.equals(PlatformManager.Platform.IOS))
            driver.findElementByXPath(passwordFieldiOS).sendKeys(password);
    }

    public HomePage tapLoginButton() {
        if(platform.equals(PlatformManager.Platform.ANDROID))
            driver.findElementById(loginButtonAndroid).click();
        else if(platform.equals(PlatformManager.Platform.IOS))
            driver.findElementByAccessibilityId(loginButtoniOS).click();

        return new HomePage(driver);
    }


}
