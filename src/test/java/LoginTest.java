import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class LoginTest extends BaseTest {

    // Test suite
    // - test 1
    // -- test class 1
    // --- test method 1
    // -- test class 2
    // - test 2
    // - test 3


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
    public void openLoginScreen(){
        driver.findElementById("com.foxtrapp.pets:id/cvEmailPassLogin_FW").click();
        logger.info("Login screen is opened");

    }



    @Test(testName = "Login with valid email and password",
            description = "[P0] Login",
            groups = {"smoke", "regression"},
            dataProvider = "emailsAndPasswords")
    public void loginWithValidCredentials(String email, String password) throws InterruptedException {
        openLoginScreen();

        // Enter data on Login screen

        driver.findElement(By.id("com.foxtrapp.pets:id/etEmail_LLV")).sendKeys(email);
        logger.info("Email " + email + " is entered");
        driver.hideKeyboard();
        driver.findElementById("com.foxtrapp.pets:id/etPassword_LLV").sendKeys(password);
        logger.info("Password " + password + " is entered");
        Thread.sleep(1000);

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



//    @Test(testName = "Login with valid email and password",
//            description = "[P0] C134234 - Login",
//            groups = {"smoke", "regression"},
//            dataProvider = "emailsAndPasswords")
//    public void myFirstTest(String email, String password) throws InterruptedException {
//        driver.findElement(By.id("com.jayway.contacts:id/fab")).click();
//        Thread.sleep(2000);
//        System.out.println(driver.getPageSource());
//
//        String locator = String.format("//*[@text='%s']", email);
//        MobileElement element = driver.findElement(By.xpath(locator));
//        element.click();
//
//        // waiting
//
//        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
//        MobileElement detailName = (MobileElement) webDriverWait.until(
//                ExpectedConditions.visibilityOf(driver.findElementById("com.jayway.contacts:id/detail_name")));
//        Assert.assertEquals(detailName.getText(), name);
//
//        MobileElement phoneNumber = driver.findElementById("com.jayway.contacts:id/phonenumber");
//        Assert.assertEquals(phoneNumber.getText(), phone);
//    }
//
//    @Test(dependsOnMethods = "myFirstTest", enabled = false)
//    public void testMethodTwo() throws Exception {
//        String locatorForContact = "com.jayway.contacts:id/name";
//        List<MobileElement> elements = driver.findElements(By.id(locatorForContact));
//        MobileElement lastElement;
//        MobileElement firstElement;
//
//        if (!elements.isEmpty()) {
//            logger.info("Contacts were found");
//            lastElement = elements.get(elements.size() - 1);
//            firstElement = elements.get(0);
//        } else {
//            throw new IllegalStateException("No contacts!");
//        }
//
//        for (int i = 0; i < 10; i++) {
//            List<MobileElement> contactsBeforeScroll = driver.findElements(By.id(locatorForContact));
//            String lastContact = contactsBeforeScroll.get(contactsBeforeScroll.size() - 1).getText();
//            logger.info(String.format("Last contact is %s", lastContact));
//
//            new TouchAction(driver)
//                    .press(lastElement)
//                    .moveTo(firstElement)
//                    .release()
//                    .perform();
//
//            List<MobileElement> contactsAfterScroll = driver.findElements(By.id(locatorForContact));
//            String lastContactAfterScroll = contactsAfterScroll.get(contactsAfterScroll.size() - 1).getText();
//            logger.info(String.format("Last contact after scroll is %s", lastContactAfterScroll));
//
//            if (lastContact.equals(lastContactAfterScroll)) {
//                logger.info("Test passed!");
//                return;
//            } else if (i == 9) {
//                throw new IllegalStateException("Scroll is failed!");
//            }
//        }
//    }



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
