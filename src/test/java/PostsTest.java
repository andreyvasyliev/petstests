import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PostsTest extends LaunchAndLogin {

    @BeforeMethod
    public void setUp() throws Exception {
        driver.launchApp();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.closeApp();
    }

    @Test(testName = "Open Posts List")
    public void openPostsList() {

        driver.findElementById("com.foxtrapp.pets:id/crbCommunity_AM").click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        MobileElement homeTab = (MobileElement) webDriverWait.until(
                ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[@text='Community']")));
        logger.info("Community screen is opened");

    }

    @Test(testName = "Open Create Post")
    public void openCreatePost() {

        openPostsList();
        driver.findElementById("com.foxtrapp.pets:id/fabAddPost_FP").click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        MobileElement homeTab = (MobileElement) webDriverWait.until(
                ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[@text='Create Post']")));
        logger.info("Create Post screen is opened");

    }

    @Test(testName = "Create Post")
    public void createPost() {

        String postTitle = "Post at " + currentDateAndTime();
        String postMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt " +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit " +
                "esse cillum dolore eu fugiat nulla pariatur. ";

        openCreatePost();
        driver.findElementById("com.foxtrapp.pets:id/rbDogType_FCP").click();
        driver.findElementById("com.foxtrapp.pets:id/etTitle_FCP").sendKeys(postTitle);

        if (IsKeyboardShown(driver) == true) {
            driver.findElementById("com.foxtrapp.pets:id/etMessage_FCP").click();
        }

        else {
            driver.findElementById("com.foxtrapp.pets:id/etMessage_FCP").click();
        }

        driver.findElementById("com.foxtrapp.pets:id/etMessage_FCP").sendKeys(postMessage);

        if (IsKeyboardShown(driver) == true) {
            driver.findElementById("com.foxtrapp.pets:id/btn_CB").click();
        }

        else {
            driver.findElementById("com.foxtrapp.pets:id/btn_CB").click();
        }

        logger.info("Send Post button is clicked");

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        MobileElement homeTab = (MobileElement) webDriverWait.until(
                ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[@text='Community']")));
        logger.info("Community screen is opened");

        String locator = String.format("//*[@text='%s']", postTitle);
        MobileElement element = driver.findElementByXPath(locator);
        logger.info("Post " + "'" + postTitle + "'" + " is displayed in the feed");

    }

    @Test(testName = "Open Post Details")
    public void openPostDetails(){

        openPostsList();

        MobileElement post = driver.findElementById("com.foxtrapp.pets:id/tvPostName_IP");

        String selectedPostTitle = post.getText();

        logger.info(post.getText());

        post.click();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        MobileElement homeTab = (MobileElement) webDriverWait.until(
                ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[@text='Post Details']")));
        logger.info("'Post Details' screen is opened");

        String postTitle = driver.findElementById("com.foxtrapp.pets:id/tvTitle_IPDH").getText();

        logger.info("Post Title is " + postTitle);

        Assert.assertEquals(selectedPostTitle, postTitle);

    }

    @Test(testName = "Add Comment")
    public void addComment(){

        openPostDetails();

        String comment = "Comment at " + currentDateAndTime();
        String locator = String.format("//*[@text='%s']", comment);

        driver.findElementById("com.foxtrapp.pets:id/etComment_FPD").sendKeys(comment);
        driver.findElementById("com.foxtrapp.pets:id/ivSend_FPD").click();

        logger.info(comment + " is sent");

        driver.hideKeyboard();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        MobileElement homeTab = (MobileElement) webDriverWait.until(
                ExpectedConditions.visibilityOf(driver.findElementByXPath(locator)));
        logger.info("Comment is displayed");

    }

    public String currentDateAndTime() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);

    }


}
