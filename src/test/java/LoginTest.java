import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.account.EditAccountDetails;
import pageobjects.account.MyAccountScreen;
import pageobjects.pets.AddPetPage;
import pageobjects.registration.LoginPage;
import pageobjects.registration.SignUpPage;
import pageobjects.registration.WelcomePage;

public class LoginTest extends BaseTest {


    @BeforeMethod
    public void setUp() throws Exception {
        driver.launchApp();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.resetApp();
    }

    @DataProvider(name = "emailsAndPasswords")
    public static Object[][] emailsAndPasswords() {
        return new Object[][] {
                {"user1@mail.com", "111111"},
                {"user2@mail.com", "111111"},
                {" user2@mail.com ", "111111"},

        };
    }

    @DataProvider(name = "invalidCredentials")
    public static Object[][] invalidCredentials() {
        return new Object[][] {
                {"user1@mail.com", "222222"},
                {"user12345@mail.com", "111111"},
                {"user12345@mail.com", ""},
                {"", "111111"},
        };
    }



    @Test(testName = "Open login screen")
    public void openLoginScreen(){

        WelcomePage welcomePage = new WelcomePage(driver);

        welcomePage.allowNotifications(); // for iOS

        welcomePage.tapUseEmailButton();


    }

    @Test(testName = "LoginPage with valid email and password",
            dataProvider = "emailsAndPasswords")
    public void loginWithValidCredentials(String email, String password) throws InterruptedException {
        openLoginScreen();

        LoginPage loginPage = new LoginPage(driver);

        // Enter data on LoginPage screen

        HomePage homePage = loginPage.inputEmail(email)
                .inputPassword(password)
                .tapLoginButton();

        // waiting for Home screen

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getHomeTab()));

        logger.info("Home screen is opened");
    }

    @Test(testName = "LoginPage with valid email and password",
            description = "[P0] LoginPage",
            groups = {"smoke", "regression"},
            dataProvider = "emailsAndPasswords")
    public void loginWithCheckEmailInProfile(String email, String password) throws InterruptedException {

        loginWithValidCredentials(email, password);

        // open My Home Screen

        HomePage homePage = new HomePage(driver);

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getHomeTab()));

        // open My Account details

        MyAccountScreen myAccountScreen = homePage.tapMyAccountTab();
        webDriverWait.until(ExpectedConditions.visibilityOf(myAccountScreen.getMyAppointmentsItem()));

        logger.info("Account screen is opened");

        // open Edit Account screen

        EditAccountDetails editAccountDetails = myAccountScreen.tapEditAccountItem();
        webDriverWait.until(ExpectedConditions.visibilityOf(editAccountDetails.getFirstNameField()));

        logger.info("Edit Account screen is opened");

        // check user email

        logger.info("User's email is " + editAccountDetails.getEmailAddressField().getText());

        Assert.assertEquals(editAccountDetails.getEmailAddressField().getText(), email.trim(), "Emails don't match!");

    }

    @Test(testName = "Open Sign Up screen")
    public void openSignUpScreen() {
        openLoginScreen();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.tapSignUpText();

    }

    @Test(testName = "Sign Up with Valid Data")
    public void signUpWithValidData() throws Exception{

        openSignUpScreen();

        SignUpPage signUpPage = new SignUpPage(driver);

        AddPetPage addPetPage = signUpPage.inputFirstName("Jayson")
                .inputSurname("Bob")
                .inputMobileNumber("12346678999")
                .scrollPageUp()
                .inputEmailAddress("user199@mail.com")
                .inputPassword("qwerty")
                .waitForConfirmPasswordField(signUpPage)
                .inputConfirmPassword("qwerty")
                .tapSignUpButton();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        webDriverWait.until(ExpectedConditions.visibilityOf(addPetPage.getAvatar()));

        logger.info("Add Pet screen is opened");

        Thread.sleep(3000);

    }

    @Test(testName = "Login with invalid credentials",
            dataProvider = "invalidCredentials")
    public void loginWithInvalidCredentials(String email, String password) throws Exception{

        openLoginScreen();

        LoginPage loginPage = new LoginPage(driver);

        loginPage.inputEmail(email)
                .inputPassword(password)
                .tapLoginButton();

        Thread.sleep(5);

        WebDriverWait webDriverWait = new WebDriverWait(driver, 2);
        webDriverWait.until(ExpectedConditions.visibilityOf(loginPage.getLoginButton()));

        logger.info("Login screen is still shown");

    }

}
