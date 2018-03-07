package pageobjects;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.TabBarPage;
import utilities.PlatformManager;

public class HomePage extends TabBarPage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(HomePage.class);


    public HomePage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    // HEADER ELEMENTS

    @iOSFindBy(accessibility = "ic home calendar")
    private MobileElement calendarIcon;

    @iOSFindBy(xpath = "//XCUIElementTypeNavigationBar[@name=\"ic home nav bar logo\"]/XCUIElementTypeButton[3]")
    private MobileElement searchIcon;

    // LIST OF SERVICES

    @iOSFindBy(accessibility = "Clinics")
    private MobileElement clinicsItem;

    @iOSFindBy(accessibility = "Grooming")
    private MobileElement groomingItem;

    @iOSFindBy(accessibility = "Trainers")
    private MobileElement trainersItem;

    @iOSFindBy(accessibility = "Day Care")
    private MobileElement dayCareItem;

    @iOSFindBy(accessibility = "Boarding")
    private MobileElement boardingItem;

    @iOSFindBy(accessibility = "Lost & Found")
    private MobileElement lostAndFoundItem;

    @iOSFindBy(accessibility = "Adoprion")
    private MobileElement adoptionItem;

    @iOSFindBy(accessibility = "Additional Services")
    private MobileElement additionalServicesItem;

}
