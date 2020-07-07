package unbxdTests.testNg.ui.pim;

import core.pages.pim.*;
import core.pages.pim.MyOrganisation.MyOrganisation_Page;
import core.pages.pim.MyOrganisation.OrganisationProfile.OrganisationProfile_Page;
import lib.FunctionLibrary;
import lib.Helper;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;

public class OrganisationProfile_Tests extends BaseTest {

    String JSON_path = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json";
    String typeData = "Login Data";
    int totalDataRow = 2;
    int totalColumnEntry = 7;

    String orgImage = System.getProperty("user.dir") + "/src/test/resources/testData/pim/styleCart.png";

    String emailId;
    String password;
    String orgName;

    {
        try {
            emailId = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][0].toString();
            password = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][1].toString();
            orgName = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][2].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Login_Page loginPage;

    @Page
    OrganisationProfile_Page organisationProfilePage;

    @Page
    Home_Page homePage;

    @Page
    MyOrganisation_Page myOrganisationPage;


    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
    }

    @Test(priority = 1)
    public void verifyOrganisationProfilePage() {
        homePage.clickOnUserMenuIcon();
        homePage.clickOnViewOrganization();
        myOrganisationPage.clickOnOrganisationProfileTab();
        organisationProfilePage.waitForOrganisationPageToLoad();
        Assert.assertTrue(driver.getCurrentUrl().contains("#/organisation/tab=orgProfile"));
    }

    @Test(priority = 2)
    public void verifyOrgName() {
        Assert.assertEquals(organisationProfilePage.getOrganizationName(), orgName);
    }

    @Test(priority = 3)
    public void uploadOrgImage() throws InterruptedException {
        organisationProfilePage.uploadOrgImage(orgImage);
    }

    @Test(priority = 4)
    public void verifyImageUrl() {
        Assert.assertTrue(organisationProfilePage.getOrgImageUrl().contains("unbxd-pimento.s3.amazonaws.com"));
        Assert.assertTrue(organisationProfilePage.getOrgImageUrl().contains("styleCart.png"));

    }

    @Test(priority = 5)
    public void addPimLogo() {
        organisationProfilePage.updatePimUiLogo();
        Assert.assertNotEquals(organisationProfilePage.getAppLogoImageUrl(), "/unbxd_logo_color.png");
        Assert.assertTrue(organisationProfilePage.getAppLogoImageUrl().contains("unbxd-pimento.s3.amazonaws.com"));
    }

    @Test(priority = 6)
    public void removePimLogo() {
        organisationProfilePage.updatePimUiLogo();
        Assert.assertNotEquals(organisationProfilePage.getAppLogoImageUrl().contains("/unbxd_logo_color.png"), true);
    }

    @AfterClass
    public void logout() throws InterruptedException {
        homePage.clickOnUserMenuIcon();
        homePage.clickOnLogout();
        loginPage.isThePageAtLoginScreen();
        Helper.tearDown();
        FunctionLibrary.closeDriver();

    }


}
