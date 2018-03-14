import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import pageobjects.HomePage;
import pageobjects.registration.LoginPage;
import pageobjects.registration.WelcomePage;

import java.net.MalformedURLException;

public class LaunchAndLogin extends BaseTest {

    @BeforeClass
    @Override
    public void beforeTestMethod() throws MalformedURLException {
        super.beforeTestMethod();

        WelcomePage welcomePage = new WelcomePage(driver);

        welcomePage.allowNotifications();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        webDriverWait.until(ExpectedConditions.visibilityOf(welcomePage.getBtnUseEmail()));

        welcomePage.tapUseEmailButton();

        LoginPage loginPage = new LoginPage(driver);

        // Enter data on LoginPage screen

        String email = "user1@mail.com";
        String password = "111111";

        HomePage homePage = loginPage.inputEmail(email)
                .inputPassword(password)
                .tapLoginButton();

        // waiting for Home screen

        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getHomeTab()));

        logger.info("Home screen is opened");
    }

//    @AfterClass
//    public void afterClass() {
//
//        driver.resetApp();
//
//    }


}
