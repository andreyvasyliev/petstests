package pageobjects.account;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pageobjects.base_pages.TabBarPage;
import pageobjects.pets.AddPetPage;

public class MyAccountScreen extends TabBarPage {

    private Logger logger = LoggerFactory.getLogger(MyAccountScreen.class);


    @AndroidFindBy(id = "com.foxtrapp.pets:id/add_pet_MAP")
    @iOSFindBy(accessibility = "Add Pet button")
    private MobileElement addPetButton;

    // LIST OF ITEMS

    @AndroidFindBy(id = "com.foxtrapp.pets:id/llMyAppointments_FA")
    @iOSFindBy(accessibility = "My Appointments")
    private MobileElement myAppointmentsItem;

    @AndroidFindBy(id = "com.foxtrapp.pets:id/llEditAccount_FA")
    @iOSFindBy(accessibility = "Edit Account Details")
    private MobileElement editAccountItem;

    public MobileElement getMyAppointmentsItem() {
        return myAppointmentsItem;
    }

    public MyAccountScreen(AppiumDriver<MobileElement> driver) {
        super(driver);
    }


    public AddPetPage tapAddPetIcon() {
        addPetButton.click();
        logger.info("Add Pet button is clicked");
        return new AddPetPage(driver);
    }

    public EditAccountDetails tapEditAccountItem() {
        editAccountItem.click();
        logger.info("Edit Account Details is clicked");
        return new EditAccountDetails(driver);
    }



}
