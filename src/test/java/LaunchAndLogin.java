import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.net.MalformedURLException;

public class LaunchAndLogin extends BaseTest {

    @BeforeClass
    @Override
    public void beforeTestMethod() throws MalformedURLException {
        super.beforeTestMethod();

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
