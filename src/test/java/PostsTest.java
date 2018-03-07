import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.posts.CommunityPage;
import pageobjects.posts.CreatePostPage;
import pageobjects.posts.PostDetailsPage;

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

        HomePage homePage = new HomePage(driver);

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);

        webDriverWait.until(ExpectedConditions.visibilityOf(homePage.getHomeTab()));

        homePage.tapOnPostsTab();

        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[@text='Community']")));
        logger.info("Community screen is opened");

    }

    @Test(testName = "Open Create Post")
    public void openCreatePost() {

        CommunityPage communityPage = new CommunityPage(driver);

        openPostsList();

        CreatePostPage createPostPage = communityPage.tapAddPostButton();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);

        webDriverWait.until(ExpectedConditions.visibilityOf(createPostPage.getPostTitleField()));

//        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[@text='Create Post']")));
        logger.info("Create Post screen is opened");

    }

    @Test(testName = "Create Post")
    public void createPost() {

        String postTitle = "Post at " + currentDateAndTime();
        String postMessage = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt " +
                "ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris " +
                "nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit " +
                "esse cillum dolore eu fugiat nulla pariatur. ";

        CreatePostPage createPostPage = new CreatePostPage(driver);

         openCreatePost();

        createPostPage.tapDogIcon()
                .inputPostTitle(postTitle)
                .inputMessage(postMessage)
                .tapSendButton();


        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath("//*[@text='Community']")));
        logger.info("Community screen is opened");

        String locator = String.format("//*[@text='%s']", postTitle);
        MobileElement element = driver.findElementByXPath(locator);
        logger.info("Post " + "'" + postTitle + "'" + " is displayed in the feed");

    }

    @Test(testName = "Open Post Details")
    public void openPostDetails(){

        openPostsList();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);

        CommunityPage communityPage = new CommunityPage(driver);

        MobileElement post = (MobileElement) webDriverWait.until(
                ExpectedConditions.visibilityOf(communityPage.getFirstPostInTheList()));

        String selectedPostTitle = post.getText();

        logger.info(post.getText());

        post.click();

        PostDetailsPage postDetailsPage = new PostDetailsPage(driver);

        webDriverWait.until(ExpectedConditions.visibilityOf(postDetailsPage.getPageTitle()));
        logger.info("'Post Details' screen is opened");

        String postTitle = postDetailsPage.getPostTitle().getText();

        logger.info("Post Title is " + postTitle);

        Assert.assertEquals(selectedPostTitle, postTitle, "Post titles are different!");

    }

    @Test(testName = "Add Comment")
    public void addComment(){

        openPostDetails();

        String comment = "Comment at " + currentDateAndTime();
        String locator = String.format("//*[@text='%s']", comment);

        PostDetailsPage postDetailsPage = new PostDetailsPage(driver);

        postDetailsPage.inputComment(comment)
                .tapSendCommentButton();


        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        webDriverWait.until(ExpectedConditions.visibilityOf(driver.findElementByXPath(locator)));
        logger.info("Comment is displayed");

    }

    public String currentDateAndTime() {

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);

    }


}
