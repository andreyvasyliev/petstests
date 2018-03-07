package pageobjects.registration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.BasePage;
import pageobjects.HomePage;
import utilities.PlatformManager;

public class LoginPage extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(LoginPage.class);


    public LoginPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etEmail_LLV")
    @iOSFindBy(xpath = "(//XCUIElementTypeTextField[@name=\"Email Address\"])[2]")
    private MobileElement emailField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etPassword_LLV")
    @iOSFindBy(xpath = "(//XCUIElementTypeSecureTextField[@name=\"Password\"])[2]")
    private MobileElement passwordField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/btn_CB")
    @iOSFindBy(accessibility = "Login")
    private MobileElement loginButton;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/rbSignUp_FL")
    @iOSFindBy(accessibility = "SIGN UP")
    private MobileElement signUpButton;


    public LoginPage inputEmail(String email) {
        emailField.sendKeys(email);
        logger.info(String.format("Email %s is inputed", email));
        hideOpenKeyboard(driver);
        return this;
    }

    public LoginPage inputPassword(String password) {
        passwordField.sendKeys(password);
        logger.info(String.format("Password '%s' is inputed", password));
        hideOpenKeyboard(driver);
        return this;
    }

    public HomePage tapLoginButton() {
        loginButton.click();
        logger.info("Login button is clicked");
        return new HomePage(driver);
    }

    public SignUpPage tapSignUpText() {
        signUpButton.click();
        logger.info("Sign Up button is clicked");
        return new SignUpPage(driver);
    }


}
