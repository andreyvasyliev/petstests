import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class LaunchAndLogin {

    protected static AppiumDriver<MobileElement> driver;
    protected static Logger logger = LoggerFactory.getLogger(LoginTest.class);
    protected static String platform;
    protected static File appFile;

    @BeforeClass
    public void beforeTestMethod() throws MalformedURLException {
        String pathToOurDir = System.getProperty("user.dir");
        platform = System.getProperty("platform", "android").toLowerCase();

//        if (LoginTest.platform.equals("android")) {
            appFile = new File(pathToOurDir + "/app/android/Pets.apk");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android"); //cmd + d
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Any");
            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "Appium"); // optional
            desiredCapabilities.setCapability(MobileCapabilityType.APP, appFile);
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);

            driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        } else if (LoginTest.platform.equals("ios")) {
//            appFile = new File(pathToOurDir + "/app/ios/Pets.app");
//            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
//            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
//            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone 7");
//            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "10.3`");
////            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
//            desiredCapabilities.setCapability(MobileCapabilityType.APP, appFile);
//            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
//            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
//
//            driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
//            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        }


            String email = "user1@mail.com";
            String password = "111111";

            driver.findElementById("com.foxtrapp.pets:id/cvEmailPassLogin_FW").click();
            logger.info("Login screen is opened");

            driver.findElement(By.id("com.foxtrapp.pets:id/etEmail_LLV")).sendKeys(email);
            logger.info("Email " + email + " is entered");
            driver.hideKeyboard();
            driver.findElementById("com.foxtrapp.pets:id/etPassword_LLV").sendKeys(password);
            logger.info("Password " + password + " is entered");

            // REWORK checking that Keyboard is present

            if (IsKeyboardShown(driver) == true) {
                driver.findElementById("com.foxtrapp.pets:id/btn_CB").click();
            }

            else {
                driver.findElementById("com.foxtrapp.pets:id/btn_CB").click();
            }

            logger.info("Login button is clicked");

            // waiting for Home screen

            WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
            MobileElement homeTab = (MobileElement) webDriverWait.until(
                    ExpectedConditions.visibilityOf(driver.findElementById("com.foxtrapp.pets:id/crbHome_AM")));
            logger.info("Home screen is opened");

        }

    // Fix for randomly shown keyboard

    public boolean IsKeyboardShown(AppiumDriver driver)
    {
        try {
            driver.hideKeyboard();
            return true;
        }

        catch (WebDriverException e) {
            return false;
        }
    }


    }



