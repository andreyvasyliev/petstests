import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.registration.LoginPage;
import pageobjects.registration.WelcomePage;

public class POLoginTest extends BaseTest {

    private String email = "user1@mail.com";

    private String password = "111111";

    @BeforeMethod
    public void setUp() throws Exception {
        driver.launchApp();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.closeApp();
    }

    @DataProvider(name = "emailsAndPasswords")
    public static Object[][] emailsAndPasswords() {
        return new Object[][] {
                {"user1@mail.com", "111111"},
                {"user2@mail.com", "111111"},
        };
    }


    @Test(testName = "Open login screen")
    public void openPOLoginScreen(){

        WelcomePage welcomePage = new WelcomePage(driver);

        welcomePage.tapUseEmailButton();
//        driver.findElementById("com.foxtrapp.pets:id/cvEmailPassLogin_FW").click();
        logger.info("LoginPage screen is opened");

    }

    @Test(testName = "LoginPage with valid email and password",
            description = "[P0] LoginPage",
            groups = {"smoke", "regression"},
            dataProvider = "emailsAndPasswords")
    public void loginWithValidCredentials(String email, String password) throws InterruptedException {
        openPOLoginScreen();

        LoginPage loginPage = new LoginPage(driver);

        // Enter data on LoginPage screen

        loginPage.inputEmail(email);
        logger.info("Email " + email + " is entered");
        driver.hideKeyboard();
        loginPage.inputPassword(password);
        logger.info("Password " + password + " is entered");
        Thread.sleep(1000);

        // REWORK checking that Keyboard is present

        if (IsKeyboardShown(driver) == true) {
            loginPage.tapLoginButton();
        }

        else {
            loginPage.tapLoginButton();
        }

        logger.info("LoginPage button is clicked");

        // waiting for Home screen

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        MobileElement homeTab = (MobileElement) webDriverWait.until(
                ExpectedConditions.visibilityOf(driver.findElementById("com.foxtrapp.pets:id/crbHome_AM")));
        logger.info("Home screen is opened");

        // open My Account details

        driver.findElementById("com.foxtrapp.pets:id/crbAccount_AM").click();
        logger.info("Account screen is opened");

        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[@text='Edit Account Details']")));
        MobileElement btnEditAccountDetails = driver.findElementByXPath("//*[@text='Edit Account Details']");
        logger.info("Button " + btnEditAccountDetails.getText() + " is selected");
        btnEditAccountDetails.click();

        // check user email

        MobileElement emailAddress = driver.findElementById("com.foxtrapp.pets:id/etEmail_FEA");

        logger.info("User's email is " + emailAddress.getText());

        Assert.assertEquals(emailAddress.getText(), email);

        driver.resetApp();


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
