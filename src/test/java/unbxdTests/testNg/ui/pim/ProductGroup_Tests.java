package unbxdTests.testNg.ui.pim;

import core.components.pim.ActionsDDModal;
import core.components.pim.PrimaryButton;
import core.components.pim.SelectProducts;
import core.pages.pim.AdvanceFilter_Page;
import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.Products.ManageProducts.ManageProducts_Page;
import core.pages.pim.Products.ProductGroups.BulkEditProducts_Page;
import core.pages.pim.Products.ProductGroups.ManageProductGroup_Page;
import core.pages.pim.Products.ProductGroups.ProductGroupListing_Page;
import core.pages.pim.Products.ProductsListing.Products_Page;
import core.pages.pim.WorkFlow.Nodes.ProductGrpNode_Page;
import lib.FunctionLibrary;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ProductGroup_Tests extends BaseTest {

    String jsonPathProductGrpData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/productGroup.json";
    String productGrpTypeData = "Product Group";
    int totalProductGrpDataRow = 1;
    int totalProductGrpColumnEntry = 8;

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json";
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    String emailId, password, productGrpName, productGrpDesc, dynaProductGrpName, dynaProductGrpDesc, updatedProductGrpName, updatedProductGrpDesc, updatedDynaProductGrpName, updatedDynaProductGrpDesc, deleteMsg;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            productGrpName = JsonReader.getdata(jsonPathProductGrpData, productGrpTypeData, totalProductGrpDataRow, totalProductGrpColumnEntry)[0][0].toString();
            productGrpDesc = JsonReader.getdata(jsonPathProductGrpData, productGrpTypeData, totalProductGrpDataRow, totalProductGrpColumnEntry)[0][1].toString();
            dynaProductGrpName = JsonReader.getdata(jsonPathProductGrpData, productGrpTypeData, totalProductGrpDataRow, totalProductGrpColumnEntry)[0][2].toString();
            dynaProductGrpDesc = JsonReader.getdata(jsonPathProductGrpData, productGrpTypeData, totalProductGrpDataRow, totalProductGrpColumnEntry)[0][3].toString();
            updatedProductGrpName = JsonReader.getdata(jsonPathProductGrpData, productGrpTypeData, totalProductGrpDataRow, totalProductGrpColumnEntry)[0][4].toString();
            updatedProductGrpDesc = JsonReader.getdata(jsonPathProductGrpData, productGrpTypeData, totalProductGrpDataRow, totalProductGrpColumnEntry)[0][5].toString();
            updatedDynaProductGrpName = JsonReader.getdata(jsonPathProductGrpData, productGrpTypeData, totalProductGrpDataRow, totalProductGrpColumnEntry)[0][6].toString();
            updatedDynaProductGrpDesc = JsonReader.getdata(jsonPathProductGrpData, productGrpTypeData, totalProductGrpDataRow, totalProductGrpColumnEntry)[0][7].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public List<String> removeLastCharacter(List<String> strList) {
        List<String> modifiedList = new ArrayList<String>();
        for (String str : strList)
            if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
                modifiedList.add(str.substring(0, str.length() - 1));
            }
        return modifiedList;
    }

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    Products_Page productsPage;

    @Page
    ProductGroupListing_Page productGroupListingPage;

    @Page
    ManageProductGroup_Page manageProductGroupPage;

    @Page
    AdvanceFilter_Page advanceFilterPage;

    @Page
    ManageProducts_Page manageProductsPage;

    @Page
    BulkEditProducts_Page bulkEditProductsPage;

    @Page
    SelectProducts selectProducts;

    @Page
    ActionsDDModal actionsDDModal;

    @Page
    PrimaryButton primaryButton;

    @Page
    ProductGrpNode_Page productGrpNodePage;

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnProductsTab();
        productsPage.waitForProductListingPageToLoad();
        productsPage.clickOnProductGrpTab();
        productGroupListingPage.waitForPropertyGroupToLoad();
    }

    @AfterMethod
    public void returnToOverview() {
        productsPage.clickOnProductGrpTab();
        productGroupListingPage.waitForPropertyGroupToLoad();
    }

    @Test(priority = 1)
    public void  verifyTheProductGrpPageWhenThereIsNoProductGrpListing() {
        Assert.assertEquals(productGroupListingPage.getPageContentOfBlankPropertyGroupPage(), "Product group lets you group relevant products so that it is easy to organize and manage products.\nJust select the type of product group (Static or Dynamic), enter the product group name, and you have a new product group.");
    }

    @Test(priority = 2)
    public void  verifyStaticProductGrpCreation() {
        productGroupListingPage.createProductGroup(productGrpName, productGrpDesc);
        manageProductGroupPage.waitForPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("tab=productGroups"));
    }

    @Test(priority = 3)
    public void verifyAddProductsToProductGrp() throws InterruptedException {
        productGroupListingPage.openProductGroup(productGrpName);
        manageProductGroupPage.clickOnAddProductsButton();
        manageProductGroupPage.searchProductInModal("snooky Men Vest");
        productsPage.clickOnToggleGroupByParentSolo();
        manageProductGroupPage.clickOnCheckBoxAgainstProduct();
        manageProductGroupPage.clickOnAddProductToGroup();
        Thread.sleep(2000);
        System.out.println(manageProductGroupPage.getAllTheProducts());
        Assert.assertEquals(manageProductGroupPage.getAllTheProducts().size(), 4);
        //Assert.assertTrue(manageProductGroupPage.getAllTheProducts().contains(Arrays.asList("snooky Men VestVariant16", "snooky Men VestVariant17", "snooky Men VestVariant18", "snooky Men VestVariant19")));
    }

    @Test(priority = 4)
    public void verifyAddingMoreProductsToProductGroup() throws InterruptedException {
        productGroupListingPage.openProductGroup(productGrpName);
        manageProductGroupPage.clickOnAddMoreProduct();
        manageProductGroupPage.searchProductInModal("thick heel western booties");
        manageProductGroupPage.clickOnCheckBoxAgainstProduct();
        manageProductGroupPage.clickOnAddProductToGroup();
        Thread.sleep(2000);
        Assert.assertEquals(manageProductGroupPage.getAllTheProducts().size(), 5);
        // Assert.assertTrue(manageProductGroupPage.getAllTheProducts().contains(Arrays.asList("snooky Men Vest Variant16", "snooky Men Vest Variant17", "snooky Men Vest Variant18", "snooky Men Vest Variant19", "thick heel western booties 5")));
    }

    @Test(priority = 5)
    public void verifyProductGrpNameUpdation() throws InterruptedException {
        productGroupListingPage.openProductGroup(productGrpName);
        manageProductGroupPage.clickOnGrpInfoTab();
        manageProductGroupPage.setProductGrpName(updatedProductGrpName);
        manageProductGroupPage.setProductGrpDescription(updatedProductGrpDesc);
        manageProductGroupPage.updateProductGrp();
        Assert.assertEquals(manageProductGroupPage.getSuccessMsg(), "Product group information successfully updated");
        manageProductGroupPage.clickOnBackToProductGroupListingPage();
        productGroupListingPage.searchProductGroup(updatedProductGrpName);
        Assert.assertEquals(productGroupListingPage.getProductGroupDetails(), Arrays.asList(updatedProductGrpName + " Static " + updatedProductGrpDesc));
    }

    @Test(priority = 6)
    public void verifyBulkEditOfTextProductInProductGrp() throws InterruptedException {
        productGroupListingPage.openProductGroup(productGrpName);
        manageProductGroupPage.searchProduct("thick heel western booties");
        selectProducts.clickOnCheckboxAgainstProduct();
        actionsDDModal.clickOnActionsDropDownIcon();
        actionsDDModal.clickOnListDropDownValue("Bulk edit");
        bulkEditProductsPage.selectAPropertyToEdit("Fabric");
        Assert.assertEquals(bulkEditProductsPage.getThePropertyToBeEdited(), "Fabric");
        bulkEditProductsPage.updateTextTypeProperty("updated fabric value");
        FunctionLibrary.refreshPage();
        productsPage.clickOnProductTab();
        productsPage.waitForProductListingPageToLoad();
        productsPage.searchProduct("thick heel western booties");
        productsPage.clickOnProductEditIcon();
        Assert.assertEquals(manageProductsPage.getPropertyValue("Fabric"), "updated fabric value");
    }

//    @Test(priority = 7)
//    public void verifyBulkEditOfBooleanProductInProductGrp() throws InterruptedException {
//        productGroupListingPage.openProductGroup(productGrpName);
//        manageProductGroupPage.searchProduct("thick heel western booties");
//        selectProducts.clickOnCheckboxAgainstProduct();
//        actions.clickOnActionsDropdownIcon();
//        actions.clickOnListDropdownValue("Bulk edit");
//        bulkEditProductsPage.selectAPropertyToEdit("Return Policy");
//        Assert.assertEquals(bulkEditProductsPage.getThePropertyToBeEdited(), "Return Policy");
//        bulkEditProductsPage.updateTextTypeProperty("N");
//        FunctionLibrary.refreshPage();
//        productsPage.clickOnProductTab();
//        productsPage.waitForProductListingPageToLoad();
//        productsPage.searchProduct("thick heel western bootie");
//        productsPage.clickOnProductEditIcon();
//        Assert.assertEquals(manageProductsPage.getPropertyValue("Return Policy"), "N");
//
//    }

    @Test(priority = 8)
    public void verifyProductRemovalFromProdGrp() throws InterruptedException {
        productGroupListingPage.openProductGroup(productGrpName);
        productsPage.clickOnToggleGroupByParentSolo();
        manageProductGroupPage.searchProduct("snooky Men Vest");
        selectProducts.clickOnCheckboxAgainstProduct();
        actionsDDModal.clickOnActionsDropDownIcon();
        actionsDDModal.clickOnListDropDownValue("Remove");
        primaryButton.clickOnPrimaryModalButton();
        Assert.assertEquals(manageProductGroupPage.getSuccessMsg(), "Successfully removed product(s) from product group!");
        manageProductGroupPage.searchProduct("snooky Men Vest");
        Assert.assertEquals(productsPage.getNoResultFoundLabel(), "No results found");
    }

    @Test(priority = 9)
    public void verifyProductDeletionFromProdGrp() throws InterruptedException {
        productGroupListingPage.openProductGroup(productGrpName);
        manageProductGroupPage.waitForPageToLoad();
        productsPage.clickOnToggleGroupByParentSolo();
        manageProductGroupPage.searchProduct("thick heel western booties");
        selectProducts.clickOnCheckboxAgainstProduct();
        actionsDDModal.clickOnActionsDropDownIcon();
        actionsDDModal.clickOnListDropDownValue("Delete");
        primaryButton.clickOnPrimaryModalButton();
        deleteMsg = manageProductGroupPage.getSuccessMsg();
        Assert.assertTrue(deleteMsg.equals("Successfully deleted 1 products in PIM") || deleteMsg.equals("Successfully removed product(s) from product group!"));
        productsPage.searchProduct("thick heel western booties");
        Assert.assertEquals(productsPage.getNoResultFoundLabel(), "No results found");
    }

    @Test(priority = 10)
    public void  verifyDynamicProductGrpCreation() {
        productGroupListingPage.createDynamicProductGroup(dynaProductGrpName, dynaProductGrpDesc);
        manageProductGroupPage.waitForPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("tab=productGroups"));
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

    }

    @Test(priority = 11)
    public void verifyApplyingAdvanceFilter() throws InterruptedException {
        productGroupListingPage.openProductGroup(dynaProductGrpName);
        manageProductGroupPage.clickOnAddDynamicFiltersButton();
        advanceFilterPage.clickOnLabelDropdown();
        advanceFilterPage.selectDropdownValue("Property");
        advanceFilterPage.clickOnValueDropdown();
        advanceFilterPage.selectDropdownValue("Color");
        advanceFilterPage.clickOnOperatorDropdown();
        advanceFilterPage.selectDropdownValue("doesn't equal");
        advanceFilterPage.clickOnOptionDropdown();
        advanceFilterPage.searchOptionValueInDropdown("Red");
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
        advanceFilterPage.clickOnDropdownOption();
        Thread.sleep(4000);
        Assert.assertEquals(manageProductGroupPage.getAllTheProducts().size(), 3);
        //  Assert.assertTrue(manageProductGroupPage.getAllTheProducts().contains(Arrays.asList("snooky Men Vest 18", "snooky Men Vest 17", "snooky Men Vest 19")));
        manageProductGroupPage.clickOnSaveChangesToProductGroupButton();
        Assert.assertEquals(manageProductGroupPage.getAllTheProducts().size(), 3);
        //  Assert.assertTrue(manageProductGroupPage.getAllTheProducts().contains(Arrays.asList("snooky Men Vest 18", "snooky Men Vest 17", "snooky Men Vest 19")));
    }

//    @Test(priority = 12)
//    public void verifyUpdatingFilter() throws InterruptedException {
//        productGroupListingPage.openProductGroup(dynaProductGrpName);
//        manageProductGroupPage.clickOnUpdateDynamicFiltersButton();
//        manageProductGroupPage.clickOnAddAnotherFiterBlock();
//        advanceFilterPage.clickOnLabel1Dropdown();
//        advanceFilterPage.selectDropdownValue("Property");
//        advanceFilterPage.clickOnValue1Dropdown();
//        advanceFilterPage.selectDropdownValue("Color");
//        advanceFilterPage.clickOnOperator1Dropdown();
//        advanceFilterPage.selectDropdownValue("doesn't equal");
//        advanceFilterPage.clickOnOption1Dropdown();
//        advanceFilterPage.searchOptionValueInDropdown("Black");
//        advanceFilterPage.clickOnDropdownOption();
//        Thread.sleep(2000);
//        Assert.assertEquals(manageProductGroupPage.getAllTheProducts().size(), 3);
//      //  Assert.assertTrue(manageProductGroupPage.getAllTheProducts().containsAll(Arrays.asList("snooky Men Vest 18", "snooky Men Vest 17", "snooky Men Vest 19")));
//        manageProductGroupPage.clickOnSaveChangesToProductGroupButton();
//        Assert.assertEquals(manageProductGroupPage.getAllTheProducts().size(), 3);
//      //  Assert.assertTrue(manageProductGroupPage.getAllTheProducts().containsAll(Arrays.asList("snooky Men Vest 18", "snooky Men Vest 17", "snooky Men Vest 19")));
//
//    }

    @Test(priority = 13)
    public void verifyDynamicProductGrpDelete() throws InterruptedException {
        productGroupListingPage.openProductGroup(dynaProductGrpName);
        manageProductGroupPage.waitForPageToLoad();
        manageProductGroupPage.deleteProductGrp();
        productGroupListingPage.waitForPropertyGroupToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("action=list"));
        productGroupListingPage.searchProductGroup(dynaProductGrpName);
        Assert.assertEquals(productGroupListingPage.getPageContentForProductGrpNotFound(), "No results found");;
    }

    @Test(priority = 14)
    public void verifyBulkProductGrpDelete() throws InterruptedException {
        productGroupListingPage.deleteBulkProductGroup(productGrpName);
        Assert.assertEquals(productGroupListingPage.getPageContentOfBlankPropertyGroupPage(), "Product group lets you group relevant products so that it is easy to organize and manage products.\nJust select the type of product group (Static or Dynamic), enter the product group name, and you have a new product group.");
    }
}