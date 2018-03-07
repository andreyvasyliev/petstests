package pageobjects.pets;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.BasePage;
import pageobjects.base_pages.ImagesPage;
import pageobjects.registration.SignUpPage;
import utilities.PlatformManager;

public class SpecificationsPage extends BasePage {

    private PlatformManager platformManager;
    private PlatformManager.Platform platform;
    private Logger logger = LoggerFactory.getLogger(SpecificationsPage.class);


    public SpecificationsPage(AppiumDriver<MobileElement> driver) {
        super(driver);
        platformManager = new PlatformManager();
        platform = platformManager.getPlatform();

    }

    @AndroidFindBy(id = "com.foxtrapp.pets:id/rbCatType_FAPS")
    private MobileElement catIcon;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/rbDogType_FAPS")
    private MobileElement dogItem;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/rbOtherType_FAPS")
    private MobileElement otherItem;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etText_MS")
    private MobileElement breedField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etMicroChipNumber_FAPS")
    private MobileElement microchipField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etMunicipalityTagNumber_FAPS")
    private MobileElement municipalityField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/etWeight_FAPS")
    private MobileElement weightField;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/tvNext_BNB")
    private MobileElement nextButton;

    public MobileElement getCatIcon() {
        return catIcon;
    }

    public SpecificationsPage tapCatIcon() {
        catIcon.click();
        logger.info("Cat is selected");
        return this;
    }

    public SpecificationsPage tapDogIcon() {
        dogItem.click();
        logger.info("Dog is selected");
        return this;
    }

    public SpecificationsPage inputMicrochip(String microchip) {
        microchipField.sendKeys(microchip);
        logger.info(String.format("Microchip Number '%s' is inputed", microchip));
        hideOpenKeyboard(driver);
        return this;
    }

    public SpecificationsPage inputMunicipalityTag(String municipalityTagNumber) {
        municipalityField.sendKeys(municipalityTagNumber);
        logger.info(String.format("Municipality Tag Number '%s' is inputed", municipalityTagNumber));
        hideOpenKeyboard(driver);
        return this;
    }

    public SpecificationsPage inputWeight(String weight) {
        weightField.sendKeys(weight);
        logger.info(String.format("Weight %s is inputed", weight));
        hideOpenKeyboard(driver);
        return this;
    }

    public VaccinationPage tapNextButton() {
        nextButton.click();
        logger.info("Next button is clicked");
        return new VaccinationPage(driver);
    }
}
