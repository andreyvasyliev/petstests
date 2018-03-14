package pageobjects.base_pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSFindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.account.MyAccountScreen;
import pageobjects.posts.CommunityPage;
import utilities.PlatformManager;

public class TabBarPage extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(TabBarPage.class);


    public TabBarPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    // TAB BAR ELEMENTS

    @AndroidFindBy(id = "com.foxtrapp.pets:id/crbHome_AM")
    @iOSFindBy(accessibility = "Home Tab")
    private MobileElement homeTab;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/crbCommunity_AM")
    @iOSFindBy(accessibility = "Discussions Tab")
    private MobileElement postsTab;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/ivEmergency_AM")
    @iOSFindBy(accessibility = "Emergency Tab")
    private MobileElement emergencyTab;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/crbNotifications_AM")
    @iOSFindBy(accessibility = "Notifications Tab")
    private MobileElement notificationsTab;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/crbAccount_AM")
    @iOSFindBy(accessibility = "Account Tab")
    private MobileElement myAccountTab;

    public MobileElement getHomeTab() {
        return homeTab;
    }

    public MyAccountScreen tapMyAccountTab(){
        myAccountTab.click();
        logger.info("My Account tab is clicked");
        return new MyAccountScreen(driver);
    }

    public CommunityPage tapOnPostsTab() {
        postsTab.click();
        logger.info("Posts tab is clicked");
        return new CommunityPage(driver);
    }


}
