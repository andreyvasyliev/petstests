package pageobjects.registration;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.BasePage;
import pageobjects.pets.AddPetPage;
import utilities.PlatformManager;



public class SignUpPage extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(SignUpPage.class);


    public SignUpPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etFirstName_LSUV")
//    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Ur Pets Life\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[1]")
    @iOSFindBy(accessibility = "First Name")
    private MobileElement fieldFirstName;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etLastName_LSUV")
//    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Ur Pets Life\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[2]")
    @iOSFindBy(accessibility = "Last Name")
    private MobileElement fieldSurname;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/cetBirthday_LSUV")
    @iOSFindBy(accessibility = "Date Of Birth")
    private MobileElement fieldBirthday;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/csGender_LSUV")
    @iOSFindBy(accessibility = "Gender")
    private MobileElement fieldGender;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etMobileNumber_LSUV")
//    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Ur Pets Life\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[5]")
    @iOSFindBy(accessibility = "Mobile Number")
    private MobileElement fieldMobileNumber;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etEmail_LSUV")
//    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Ur Pets Life\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[6]")
    @iOSFindBy(accessibility = "Email Address")
    private MobileElement fieldEmailAddress;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etPassword_LSUV")
//    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Ur Pets Life\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[8]")
    @iOSFindBy(accessibility = "Password")
    private MobileElement fieldPassword;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etPasswordConfirm_LSUV")
//    @iOSFindBy(xpath = "//XCUIElementTypeApplication[@name=\"Ur Pets Life\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTable/XCUIElementTypeCell[9]")
    @iOSFindBy(accessibility = "Confirm Password")
    private MobileElement fieldConfirmPassword;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/btn_CB")
    @iOSFindBy(accessibility = "Sign Up")
    private MobileElement signUpButton;


    public SignUpPage inputFirstName(String firstName) {
        fieldFirstName.setValue(firstName);
        logger.info(String.format("Name '%s' is inputed", firstName));
        hideOpenKeyboard(driver);
        return this;
    }

    public SignUpPage inputSurname(String surname) {
        fieldSurname.setValue(surname);
        logger.info(String.format("Surname '%s' is inputed", surname));
        hideOpenKeyboard(driver);
        return this;
    }

    public SignUpPage inputMobileNumber(String mobileNumber) {
        if (platform.equals(PlatformManager.Platform.ANDROID))
           fieldMobileNumber.setValue(mobileNumber);
        else if(platform.equals(PlatformManager.Platform.IOS))
            fieldMobileNumber.setValue("+" + mobileNumber);

        logger.info(String.format("Mobile Number '%s' is inputed", mobileNumber));
        hideOpenKeyboard(driver);
        return this;
    }

    public SignUpPage inputEmailAddress(String emailAddress) {
        fieldEmailAddress.sendKeys(emailAddress);
        logger.info(String.format("Email '%s' is inputed", emailAddress));
        hideOpenKeyboard(driver);
        return this;
    }

    public SignUpPage inputPassword(String password) {
        fieldPassword.sendKeys(password);
        logger.info(String.format("Password '%s' is inputed", password));
        hideOpenKeyboard(driver);
        return this;
    }

    public SignUpPage inputConfirmPassword(String confirmPassword) {
        fieldConfirmPassword.sendKeys(confirmPassword);
        logger.info(String.format("Confirm Password '%s' is inputed", confirmPassword));
        hideOpenKeyboard(driver);
        return this;
    }

    public AddPetPage tapSignUpButton() {
        signUpButton.click();
        return new AddPetPage(driver);
    }

    public MobileElement getFieldConfirmPassword() {
        return fieldConfirmPassword;
    }

    //TODO Make scroll Base method

    public SignUpPage scrollPageUp() {
            int x = (int) (driver.manage().window().getSize().getWidth() * 0.50);
            int startY = (int) ((driver.manage().window().getSize().getHeight()) * 0.90);
            int endY = (int) ((driver.manage().window().getSize().getHeight()) * 0.10);

            TouchAction scrollUp = new TouchAction(driver);
            scrollUp.press(x, startY)
                    .waitAction()
                    .moveTo(x, endY)
                    .release()
                    .perform();


        return this;
    }

    public SignUpPage waitForConfirmPasswordField(SignUpPage signUpPage) {
        WebDriverWait webDriverWait = new WebDriverWait(driver, 10);
        webDriverWait.until(ExpectedConditions.visibilityOf(signUpPage.getFieldConfirmPassword()));
        return this;
    }



}
