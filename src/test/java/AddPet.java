import io.appium.java_client.MobileElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.HomePage;
import pageobjects.account.MyAccountScreen;
import pageobjects.pets.AddPetPage;
import pageobjects.pets.PicturesPage;
import pageobjects.pets.SpecificationsPage;
import pageobjects.pets.VaccinationPage;

public class AddPet extends LaunchAndLogin{

    @BeforeMethod
    public void setUp() throws Exception {
        driver.launchApp();
        new HomePage(driver).tapMyAccountTab();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        driver.resetApp();
    }

    @Test
    public void addPet(){
        // Open My Account

        MyAccountScreen myAccountScreen = new MyAccountScreen(driver);
        AddPetPage addPetPage = myAccountScreen.tapAddPetIcon();

        WebDriverWait webDriverWait = new WebDriverWait(driver, 60);
        webDriverWait.until(ExpectedConditions.visibilityOf(addPetPage.getAvatar()));

        logger.info("Add Pet screen is opened");


        // Open Add Pet Screen

        addPetPage
                .inputPetName("Jack")
                .selectBirthday();

        addPetPage.tapAvatar();
        addPetPage.selectCamera();
        addPetPage.allowPhotos();
        addPetPage.takePicture();

        SpecificationsPage specificationsPage = addPetPage.tapNextButton();

        webDriverWait.until(ExpectedConditions.visibilityOf(specificationsPage.getCatIcon()));

        logger.info("Specifications screen is opened");


        // Input Specifications

        specificationsPage
                .tapDogIcon()
                .inputMicrochip("123456789012")
                .inputMunicipalityTag("D12555 2016")
                .inputWeight("4.5");

        VaccinationPage vaccinationPage = specificationsPage.tapNextButton();

        // Go through Vaccination and Pictures screens

        webDriverWait.until(ExpectedConditions.visibilityOf(vaccinationPage.getVaccinationListItem()));

        logger.info("Vaccination screen is opened");

        PicturesPage picturesPage = vaccinationPage.tapNextButton();

        webDriverWait.until(ExpectedConditions.visibilityOf(picturesPage.getBigPlusButton()));

        logger.info("Pictures page is opened");

        myAccountScreen = picturesPage.tapDoneButton();

        webDriverWait.until(ExpectedConditions.visibilityOf(myAccountScreen.getMyAppointmentsItem()));

        logger.info("My Account Screen is opened");

        //TODO Add Check that Pet is added in the list

    }



}
