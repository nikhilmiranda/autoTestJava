package unbxdTests.testNg.ui.pim.UserPermissions;

import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.MyOrganisation.MyOrganisation_Page;
import core.pages.pim.MyOrganisation.Roles.ManageRole_Page;
import core.pages.pim.MyOrganisation.Roles.RolesListing_Page;
import lib.JsonReader;
import lib.constants.Notification;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;

public class SetToDefault extends BaseTest {

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    public String emailId, password, role2, emailId2, password2;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            emailId2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][0].toString();
            password2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][1].toString();
            role2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][3].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    ManageRole_Page manageRolePage;

    @Page
    RolesListing_Page rolesListingPage;

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
    public void changeAllTheSystemPermissionsToHide() throws InterruptedException {
        homePage.clickOnUserMenuIcon();
        homePage.clickOnViewOrganization();
        myOrganisationPage.clickOnRolesTab();
        rolesListingPage.clickOnRoleEditIcon(role2);
        manageRolePage.clickOnSystemPermissionsTab();
        manageRolePage.clickOnUpdate();
        Assert.assertEquals(manageRolePage.getSuccessMessage(), Notification.SYSTEM_PERMISSION_UPDATE.getDesc());
    }

    @Test(priority = 2)
    public void verifyOrganisationSetupIsHidden() throws InterruptedException {
        homePage.clickOnUserMenuIcon();
        homePage.clickOnLogout();
        loginPage.login(emailId2, password2);
        homePage.clickOnUserMenuIcon();
        Assert.assertEquals(homePage.isViewOrganizationPresent().booleanValue(),true);
        homePage.clickOnUserMenuIcon();
    }

    @Test(priority = 3)
    public void verifyIfPropertiesIsHidden() {
        Assert.assertEquals(homePage.isSubMenuDisplayed("Properties").booleanValue(),true);
    }

    @Test(priority = 4)
    public void verifyIfTasksIsHidden() {
        Assert.assertEquals(homePage.isSubMenuDisplayed("Tasks").booleanValue(),true);
    }

    @Test(priority = 5)
    public void verifyIfProductsIsHidden() {
        Assert.assertEquals(homePage.isMenuDisplayed("Products").booleanValue(),true);
    }

    @Test(priority = 6)
    public void verifyIfImportIsHidden() {
        Assert.assertEquals(homePage.isSubMenuDisplayed("Imports").booleanValue(),true);

    }

    @Test(priority = 7)
    public void verifyIfNetworkIsHidden() {
        Assert.assertEquals(homePage.isMenuDisplayed("Network").booleanValue(),true);
    }

    @Test(priority = 8)
    public void verifyIfCatalogIsHidden() {
        Assert.assertEquals(homePage.isSubMenuDisplayed("Catalogs").booleanValue(),true);
    }
}
