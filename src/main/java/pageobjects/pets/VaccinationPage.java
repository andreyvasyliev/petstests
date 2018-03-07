package pageobjects.pets;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.ImagesPage;
import utilities.PlatformManager;

public class VaccinationPage extends ImagesPage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(VaccinationPage.class);

    public VaccinationPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    @AndroidFindBy(id = "com.foxtrapp.pets:id/tvVaccinationName_IV")
    private MobileElement vaccinationListItem;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/tvNext_BNB")
    private MobileElement nextButton;



    public MobileElement getVaccinationListItem() {
        return vaccinationListItem;
    }

    public PicturesPage tapNextButton() {
        nextButton.click();
        logger.info("Next button is clicked");
        return new PicturesPage(driver);
    }

}
