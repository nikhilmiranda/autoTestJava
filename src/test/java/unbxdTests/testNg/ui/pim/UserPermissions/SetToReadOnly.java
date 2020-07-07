package unbxdTests.testNg.ui.pim.UserPermissions;

import core.pages.pim.Products.ManageProducts.ManageProducts_Page;
import core.pages.pim.Products.ProductsListing.Actions_Page;
import core.pages.pim.Products.ProductsListing.Products_Page;
import core.pages.pim.Properties.Properties_Page.PropertyListing_page;
import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.MyOrganisation.Members.MembersListing_Page;
import core.pages.pim.MyOrganisation.MyOrganisation_Page;
import core.pages.pim.MyOrganisation.Roles.ManageRole_Page;
import core.pages.pim.MyOrganisation.Roles.RolesListing_Page;
import core.pages.pim.Tasks.Tasks_Page.TaskListing_Page;
import lib.JsonReader;
import lib.constants.Notification;
import lib.constants.SystemPermissions;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

public class SetToReadOnly extends BaseTest {

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

    List<String> systemPermissions = Arrays.asList(SystemPermissions.ORGANIZATION_SETUP.getDesc(), SystemPermissions.IMPORT.getDesc(), SystemPermissions.PROPERTY_DEFINITION.getDesc(),SystemPermissions.PRODUCT_MANAGEMENT.getDesc(), SystemPermissions.NETWORK.getDesc(),SystemPermissions.TASK.getDesc(), SystemPermissions.CATALOG.getDesc(),SystemPermissions.CATEGORY.getDesc(), SystemPermissions.ASSETS.getDesc(),SystemPermissions.WORKFLOW.getDesc());

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    MembersListing_Page membersListingPage;

    @Page
    RolesListing_Page rolesListingPage;

    @Page
    ManageRole_Page manageRolePage;

    @Page
    MyOrganisation_Page myOrganisationPage;

    @Page
    Actions_Page actionsPage;

    @Page
    Products_Page productsPage;

    @Page
    ManageProducts_Page manageProductsPage;

    @Page
    PropertyListing_page propertyListingPage;

    @Page
    TaskListing_Page taskListingPage;


    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
    }

    @AfterMethod
    public void returnToCustomAdapterPage() {
         homePage.clickOnOverviewTab();
    }

    @Test(priority = 1)
    public void changeAllTheSystemPermissionsToHide() throws InterruptedException {
        homePage.clickOnUserMenuIcon();
        homePage.clickOnViewOrganization();
        myOrganisationPage.clickOnRolesTab();
        rolesListingPage.clickOnRoleEditIcon(role2);
        manageRolePage.clickOnSystemPermissionsTab();
        manageRolePage.setSystemPermission1(systemPermissions, SystemPermissions.READ_ONLY.getDesc());
        manageRolePage.clickOnUpdate();
        Assert.assertEquals(manageRolePage.getSuccessMessage(), Notification.SYSTEM_PERMISSION_UPDATE.getDesc());
    }

    @Test(priority = 2)
    public void verifyOrganisationSetupIsHidden() throws InterruptedException {
        homePage.clickOnUserMenuIcon();
        homePage.clickOnLogout();
        loginPage.login(emailId2, password2);
        homePage.clickOnUserMenuIcon();
        homePage.clickOnViewOrganization();
        Assert.assertEquals(membersListingPage.isCreateMemberButtonVisible().booleanValue(), false);
        myOrganisationPage.clickOnRolesTab();
        Assert.assertEquals(rolesListingPage.isCreateRoleButtonVisible().booleanValue(), false);
    }

    @Test(priority = 3)
    public void verifyIfPropertiesIsHidden() {
        homePage.clickOnManageTab();
        homePage.clickOnProperties();
        Assert.assertEquals(propertyListingPage.isCreatePropertyButtonPresent().booleanValue(), false);
    }

    @Test(priority = 4)
    public void verifyIfTasksIsHidden() {
        homePage.clickOnManageTab();
        homePage.clickOnTasks();
        Assert.assertEquals(taskListingPage.isAssignNewTaskButtonPresent().booleanValue(), false);
    }

    @Test(priority = 5)
    public void verifyIfProductsIsHidden() throws InterruptedException {
        homePage.clickOnProductsTab();
        Assert.assertEquals(productsPage.isCreateProductPresent().booleanValue(), false);
        Assert.assertEquals(actionsPage.isActionsIconPresent().booleanValue(), false);
        productsPage.searchProduct("snooky Men Vest");
        productsPage.clickOnProductEditIcon();
        Assert.assertEquals(manageProductsPage.isSaveProductButtonPresent().booleanValue(),false);
    }

//    @Test(priority = 6)
//    public void verifyIfImportIsHidden() {
//        Assert.assertEquals(homePage.isSubMenuDisplayed("Imports").booleanValue(),false);
//
//    }
//
//    @Test(priority = 7)
//    public void verifyIfNetworkIsHidden() {
//        Assert.assertEquals(homePage.isMenuDisplayed("Network").booleanValue(),false);
//
//    }
//
//    @Test(priority = 8)
//    public void verifyIfCatalogIsHidden() {
//        Assert.assertEquals(homePage.isSubMenuDisplayed("Catalogs").booleanValue(),false);
//
//    }



//    @Test(priority = 6)
//    public void changeThePropertyPermissionToReadOnly() throws InterruptedException {
//        homePage.clickOnUserMenuIcon();
//        homePage.clickOnViewOrganization();
//        myOrganisationPage.clickOnRolesTab();
//        rolesListingPage.clickOnRoleEditIcon(role2);
//        manageRolePage.clickOnSystemPermissionsTab();
//        manageRolePage.waitForManageRoleToAppear();
//        manageRolePage.setPropertyPermission1("Fabric", "read only");
//        manageRolePage.clickOnUpdate();
//        Assert.assertEquals(manageRolePage.getSuccessMessage(), "Member details have been successfully updated.");
//
//    }

//    @Test(priority = 8)
//    public void verifyIfThePropertyIsHiddenInProductSection() {
//        productsPage.clickOnProductEditIcon();
//       Assert.assertEquals(manageProductsPage.updateProperty("Fabric"), "");
//        Assert.assertEquals( manageProductsPage.getPropertyValue("Fabric"), "No Property Found");
//        Assert.assertEquals(homePage.isSubMenuDisplayed("Catalogs").booleanValue(), false);
//
//    }

//    @Test(priority = 9)
//    public void verifyIfThePropertyIsHiddenInPropertySection() {
//        propertiesPage.searchPropertyName("Fabric");
//        Assert.assertEquals( manageProductsPage.getPropertyValue("Fabric"), "No Property Found");
//        Assert.assertEquals(homePage.isSubMenuDisplayed("Catalogs").booleanValue(), false);
//
//    }


}
