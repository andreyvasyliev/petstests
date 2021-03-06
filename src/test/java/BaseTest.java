import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static AppiumDriver<MobileElement> driver;
    protected static Logger logger = LoggerFactory.getLogger(LoginTest.class);
    protected static String platform;
    protected static File appFile;


    //TODO Check Appium start
    //    @BeforeClass
//    public void sdf(){
//        AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
//        service.start();
//    }

    @BeforeClass
    public void beforeTestMethod() throws MalformedURLException {
        String pathToOurDir = System.getProperty("user.dir");
        platform = System.getProperty("platform", "Android").toLowerCase();

        if (LoginTest.platform.equals("android")) {
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
        } else if (LoginTest.platform.equals("ios")) {
            appFile = new File(pathToOurDir + "/app/ios/Pets.app");
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "iPhone SE");
            desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "11.1");
//            desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "XCUITest");
            desiredCapabilities.setCapability(MobileCapabilityType.APP, appFile);
            desiredCapabilities.setCapability("connectHardwareKeyboard", true);
            desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 500);
            desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, false);

            driver = new IOSDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilities);
            driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        }
    }

    @AfterClass
    public void afterClass() {

        driver.resetApp();

    }

}