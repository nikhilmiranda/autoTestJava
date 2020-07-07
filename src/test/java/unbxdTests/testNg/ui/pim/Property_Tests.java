package unbxdTests.testNg.ui.pim;

import core.pages.pim.*;
import core.pages.pim.Properties.Properties_Page.ManageProperty_Page;
import core.pages.pim.Properties.Properties_Page.PropertyListing_page;
import core.pages.pim.Properties.PropertyGroup_Page.ManagePropertyGroup_Page;
import core.pages.pim.Properties.PropertyGroup_Page.PropertyGroup_Page;
import lib.FunctionLibrary;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Property_Tests extends BaseTest {



    String jsonPathPropertyData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/property.json" ;
    String propertyTypeData = "Property Data";
    int totalPropertyDataRow = 2;
    int totalPropertyColumnEntry = 6;

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    String emailId, emailId2, password, password2, roleName, roleName2, propertyName, propertyDataType, updatedPropertyName, propertyGroupName;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            roleName = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][3].toString();
            emailId2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][0].toString();
            password2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][1].toString();
            roleName2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][3].toString();

            propertyName = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry )[0][0].toString();
            propertyDataType = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry )[0][1].toString();
            updatedPropertyName = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry )[0][2].toString();
            propertyGroupName = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry )[0][3].toString();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    PropertyListing_page propertyPage;

    @Page
    ManageProperty_Page managePropertyPage;

    @Page
    PropertyGroup_Page propertyGroupPage;

    @Page
    ManagePropertyGroup_Page managePropertyGroupPage;

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
    }


    @AfterMethod
    public void returnToOverview() {
        homePage.clickOnOverviewTab();
    }

    @Test(priority = 1)
    public void openPropertyPageFromManageTab() throws InterruptedException {
        homePage.clickOnManageTab();
        homePage.clickOnProperties();
        propertyPage.waitForPropertyListingPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("/manageProperty/"));
    }

    @Test(priority = 2)
    public void openPropertyPageFromCreateProperties() {
        homePage.clickOnCreateProperties();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/manageProperty/"));
    }

    @Test(priority = 3)
    public  void verifyPropertiesPageContentWhenThereIsNoProperty() throws InterruptedException {
        openPropertyPageFromManageTab();
        propertyPage.waitForPropertyListingPageToLoad();
        Assert.assertEquals(propertyPage.getPageContentOfBlankPropertiesListingPage(), "Properties are product characteristics, customizable units of product information, for example, product_color, product_size etc.\nJust enter the property name, select data type, and you have a new property.");
    }
    @Test(priority = 4)
    public void createProperty() {
        openPropertyPageFromCreateProperties();
        propertyPage.createProperty(propertyName, propertyDataType);
        managePropertyPage.waitForManagePropertyToLoad();
        Assert.assertEquals(managePropertyPage.getPropertyName() , propertyName);
        Assert.assertEquals(managePropertyPage.getPropertyDataType(), propertyDataType);
        managePropertyPage.clickOnPermissionForRoles();
        Assert.assertEquals(managePropertyPage.fetchRoles(), Arrays.asList(roleName, roleName2));
        Assert.assertEquals(managePropertyPage.getPermissions(), Arrays.asList("Manage", "Manage"));
    }

    @Test(priority = 5)
    public void searchProperty() {
        openPropertyPageFromCreateProperties();
        propertyPage.searchPropertyName(propertyName);
        Assert.assertEquals(propertyPage.getPropertyNames(), Arrays.asList(propertyName));

    }

    @Test(priority = 6)
    public void editPropertyDetails() throws InterruptedException {
        searchProperty();
        propertyPage.clickOnEditProperty();
        managePropertyPage.waitForManagePropertyToLoad();
        managePropertyPage.updatePropertyName(updatedPropertyName);
        managePropertyPage.saveManagePropertyDetails();
        System.out.println(getDriver().getCurrentUrl());
        Assert.assertTrue(getDriver().getCurrentUrl().contains("action=edit;propertyId="));
        Assert.assertEquals(managePropertyPage.getSuccessMessage(), "Property successfully updated");
        managePropertyPage.clickOnBackToPropertyListing();
        propertyPage.waitForPropertyListingPageToLoad();
        propertyPage.searchPropertyName(updatedPropertyName);
        Assert.assertEquals(propertyPage.getPropertyNames(), Arrays.asList(updatedPropertyName));
        propertyPage.searchPropertyName(propertyName);
        Assert.assertEquals(propertyPage.getPageContentForPropertyNotFound(), "No results found");

    }

    @Test(priority = 7)
    public void searchRolesInPermissionForRoles() throws InterruptedException {
        openPropertyPageFromCreateProperties();
        propertyPage.searchPropertyName(updatedPropertyName);
        propertyPage.clickOnEditProperty();
        managePropertyPage.waitForManagePropertyToLoad();
        managePropertyPage.clickOnPermissionForRoles();
        managePropertyPage.searchRole("admin");
        Assert.assertEquals(managePropertyPage.fetchRoles(), Arrays.asList(roleName));
        Assert.assertEquals(managePropertyPage.getPermissions(), Arrays.asList("Manage"));
        managePropertyPage.searchRole("invalid-role");
        Assert.assertEquals(managePropertyPage.getNoResultFoundMessage(), "No results found");
    }

    @Test(priority = 8)
    public  void verifyPropertyGroupPageContentWhenThereIsNoProperty() throws InterruptedException {
        openPropertyPageFromManageTab();
        propertyPage.clickOnProductGroupstab();
        propertyGroupPage.waitForPropertyGroupToLoad();
        Assert.assertEquals(propertyGroupPage.getPageContentOfBlankPropertyGroupPage(), "Property group lets you group multiple properties so that you have easy accessibility of product information.\nJust enter the group name, select associated properties, and you have a new property group.");
    }

    @Test(priority = 9)
    public void createPropertyGroup() throws InterruptedException {
        openPropertyPageFromManageTab();
        propertyPage.clickOnProductGroupstab();
        propertyGroupPage.waitForPropertyGroupToLoad();
        propertyGroupPage.createPropertyGroup(propertyGroupName);
        managePropertyGroupPage.clickOnPropertyGroupDetailsTab();
        Assert.assertEquals(managePropertyGroupPage.getPropertyGroupName(), propertyGroupName);
        managePropertyGroupPage.clickOnBackToPropertyGroupListingLink();
        propertyGroupPage.searchPropertyGroup(propertyGroupName);
        Assert.assertEquals(propertyGroupPage.getPropertyGroupName(), Arrays.asList(propertyGroupName + " (0 Property )"));

    }

    @Test(priority = 10)
    public void verifyPropertiesAvailable() throws InterruptedException {
        openPropertyPageFromManageTab();
        propertyPage.clickOnProductGroupstab();
        propertyGroupPage.searchPropertyGroup(propertyGroupName);
        propertyGroupPage.clickOnPropertyGroupEditIcon();
        managePropertyGroupPage.waitForManagePropertyGroupToLoad();
        Assert.assertEquals(managePropertyGroupPage.getPropertiesName(), Arrays.asList(updatedPropertyName +" "+ propertyDataType));
    }

    @Test(priority = 11)
    public void verifyPropertyAdditionToProductGroup() throws InterruptedException {
        openPropertyPageFromManageTab();
        propertyPage.clickOnProductGroupstab();
        propertyGroupPage.searchPropertyGroup(propertyGroupName);
        propertyGroupPage.clickOnPropertyGroupEditIcon();
        managePropertyGroupPage.waitForManagePropertyGroupToLoad();
        managePropertyGroupPage.addPropertyToProductGroup(updatedPropertyName);
        managePropertyGroupPage.clickOnUpdate();
        Assert.assertEquals(managePropertyGroupPage.getSuccessMessage(), "Property group successfully updated");
        managePropertyGroupPage.clickOnBackToPropertyGroupListingLink();
        propertyGroupPage.waitForPropertyGroupToLoad();
        propertyGroupPage.searchPropertyGroup(propertyGroupName);
        Assert.assertEquals(propertyGroupPage.getPropertyGroupName(), Arrays.asList("+\n" +propertyGroupName + " (1 Property )"));
        propertyPage.clickOnPropertiesTab();
        propertyPage.waitForPropertyListingPageToLoad();
        Assert.assertTrue(propertyPage.getPropertyDetails().get(0).contains(propertyGroupName));
    }


    @Test(priority = 12)
    public void deleteProperty() throws InterruptedException {
        openPropertyPageFromCreateProperties();
        propertyPage.searchPropertyName(updatedPropertyName);
        propertyPage.clickOnEditProperty();
        managePropertyPage.deleteProperty();
        propertyPage.waitForPropertyListingPageToLoad();
        //  propertyPage.searchPropertyName(updatedPropertyName);
        Assert.assertEquals(propertyPage.getPageContentOfBlankPropertiesListingPage(), "Properties are product characteristics, customizable units of product information, for example, product_color, product_size etc.\nJust enter the property name, select data type, and you have a new property.");

    }

    @Test(priority = 13)
    public void deletePropertyGroup() throws InterruptedException {
        openPropertyPageFromCreateProperties();
        propertyPage.clickOnProductGroupstab();
        propertyGroupPage.searchPropertyGroup(propertyGroupName);
        propertyGroupPage.clickOnPropertyGroupEditIcon();
        managePropertyGroupPage.waitForManagePropertyGroupToLoad();
        managePropertyGroupPage.deletePropertyGroup();
        propertyGroupPage.waitForPropertyGroupToLoad();
        // propertyGroupPage.searchPropertyGroup(propertyGroupName);
        Assert.assertEquals(propertyGroupPage.getPageContentOfBlankPropertyGroupPage(), "Property group lets you group multiple properties so that you have easy accessibility of product information.\nJust enter the group name, select associated properties, and you have a new property group.");

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        FunctionLibrary.closeDriver();
    }

}
