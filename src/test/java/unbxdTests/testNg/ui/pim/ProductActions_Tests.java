package unbxdTests.testNg.ui.pim;

import core.components.pim.ActionsDDModal;
import core.components.pim.NotificationMsg;
import core.components.pim.PrimaryButton;
import core.components.pim.SelectProducts;
import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.ManageProduct.CreateVariantProduct_Page;
import core.pages.pim.Products.ManageProducts.ManageProducts_Page;
import core.pages.pim.Products.ProductGroups.BulkEditProducts_Page;
import core.pages.pim.Products.ProductGroups.ManageProductGroup_Page;
import core.pages.pim.Products.ProductGroups.ProductGroupListing_Page;
import core.pages.pim.Products.ProductsListing.Products_Page;
import core.pages.pim.WorkFlow.Nodes.CategoryNode_Page;
import core.pages.pim.WorkFlow.Nodes.ProductGrpNode_Page;
import core.pages.pim.WorkFlow.Nodes.TaskNode_Page;
import lib.FunctionLibrary;
import lib.JsonReader;
import lib.constants.Notification;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class ProductActions_Tests extends BaseTest {

    String jsonPathPropertyData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/property.json";
    String propertyTypeData = "Property Data";
    int totalPropertyDataRow = 2;
    int totalPropertyColumnEntry = 6;

    String jsonPathProductData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/product.json";
    String productTypeData = "Product Data";
    int totalProductDataRow = 3;
    int totalProductColumnEntry = 3;

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json";
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    String jsonPathProductGrpData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/productGroup.json";
    String productGrpTypeData = "Product Group";
    int totalProductGrpDataRow = 1;
    int totalProductGrpColumnEntry = 8;

    String jsonPathTaskData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/task.json" ;
    String taskTypeData = "Task Data";
    int totalTaskDataRow = 2;
    int totalTaskColumnEntry = 13;

    String emailId, password, productID, productName, updatedProductName, variantProperty, variantProductID, variantProductName, productGrpName, productGrpDesc, taskTemplateNameUpdated, taskName3;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            productID = JsonReader.getdata(jsonPathProductData, productTypeData, totalProductDataRow, totalProductColumnEntry)[0][0].toString();
            productName = JsonReader.getdata(jsonPathProductData, productTypeData, totalProductDataRow, totalProductColumnEntry)[0][1].toString();
            variantProductID = JsonReader.getdata(jsonPathProductData, productTypeData, totalProductDataRow, totalProductColumnEntry)[2][0].toString();
            variantProductName = JsonReader.getdata(jsonPathProductData, productTypeData, totalProductDataRow, totalProductColumnEntry)[2][1].toString();
            updatedProductName = JsonReader.getdata(jsonPathProductData, productTypeData, totalProductDataRow, totalProductColumnEntry)[0][2].toString();
            variantProperty = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry)[0][4].toString();
            productGrpName = JsonReader.getdata(jsonPathProductGrpData, productGrpTypeData, totalProductGrpDataRow, totalProductGrpColumnEntry)[0][0].toString();
            productGrpDesc = JsonReader.getdata(jsonPathProductGrpData, productGrpTypeData, totalProductGrpDataRow, totalProductGrpColumnEntry)[0][1].toString();
            taskTemplateNameUpdated = JsonReader.getdata(jsonPathTaskData, taskTypeData, totalTaskDataRow, totalTaskColumnEntry)[0][1].toString();
            taskName3 = JsonReader.getdata(jsonPathTaskData, taskTypeData, totalTaskDataRow, totalTaskColumnEntry)[0][11].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    Products_Page productsPage;

    @Page
    ManageProducts_Page manageProductPage;

    @Page
    CreateVariantProduct_Page createVariantProductPage;

    @Page
    ProductGroupListing_Page productGroupListingPage;

    @Page
    ManageProductGroup_Page manageProductGroupPage;

    @Page
    ManageProducts_Page manageProductsPage;

    @Page
    ActionsDDModal actionsDDModal;

    @Page
    PrimaryButton primaryButton;

    @Page
    ProductGrpNode_Page productGrpNodePage;

    @Page
    SelectProducts selectProducts;

    @Page
    NotificationMsg notificationMsg;

    @Page
    BulkEditProducts_Page bulkEditProductsPage;

    @Page
    TaskNode_Page taskNodePage;

    @Page
    CategoryNode_Page categoryNodePage;

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        homePage.clickOnProductsTab();
        productsPage.waitForProductListingPageToLoad();
    }

    @AfterMethod
    public void returnToOverview() {
        homePage.clickOnProductsTab();
        FunctionLibrary.refreshPage();
        productsPage.waitForProductListingPageToLoad();
    }

    @Test(priority = 1)
    public void verifyPGCreationAddition() throws InterruptedException {
        productsPage.searchProduct("prival revaux the grace sunglasses");
        selectProducts.clickOnCheckboxAgainstProduct();
        actionsDDModal.clickOnActionsDropDownIcon();
        actionsDDModal.selectDDListValue("Add to static product group");
        productGrpNodePage.createNewPG(productGrpName, productGrpDesc);
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), productGrpName + " "+Notification.PRODUCT_GRP_CREATED_ADDED_PDP.getDesc());
    }

    @Test(priority = 2)
    public void verifyPGAddition() throws InterruptedException {
        productsPage.searchProduct("prival revaux the benz sunglasses");
        selectProducts.clickOnCheckboxAgainstProduct();
        actionsDDModal.clickOnActionsDropDownIcon();
        actionsDDModal.selectDDListValue("Add to static product group");
        productGrpNodePage.addToExistingPG(productGrpName);
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(),Notification.PRODUCT_GRP_CREATED_PDP.getDesc()+ " "+productGrpName + " product group!");
    }

    @Test(priority = 3)
    public void verifyAddProductsToProductGrp() throws InterruptedException {
        productsPage.clickOnProductGrpTab();
        productGroupListingPage.waitForPropertyGroupToLoad();
        productGroupListingPage.openProductGroup(productGrpName);
        Thread.sleep(2000);
        System.out.println(manageProductGroupPage.getAllTheProductsName());
        Assert.assertEquals(manageProductGroupPage.getAllTheProducts().size(), 2);
        Assert.assertTrue(manageProductGroupPage.getAllTheProductsName().contains("prival revaux the benz sunglasses"));
        Assert.assertTrue(manageProductGroupPage.getAllTheProductsName().contains("prival revaux the grace sunglasses"));
    }

    @Test(priority = 4)
    public void verifyBulkEditOfTextProductInProductGrp() throws InterruptedException {
        productsPage.searchProduct("prival revaux the benz sunglasses");
        selectProducts.clickOnCheckboxAgainstProduct();
        actionsDDModal.clickOnActionsDropDownIcon();
        actionsDDModal.clickOnListDropDownValue("Bulk edit");
        bulkEditProductsPage.selectAPropertyToEdit("Fabric");
        Assert.assertEquals(bulkEditProductsPage.getThePropertyToBeEdited(), "Fabric");
        bulkEditProductsPage.updateTextTypeProperty("updated fabric value");
        FunctionLibrary.refreshPage();
        productsPage.clickOnProductTab();
        productsPage.waitForProductListingPageToLoad();
        productsPage.searchProduct("prival revaux the benz sunglasses");
        productsPage.clickOnProductEditIcon();
        Assert.assertEquals(manageProductsPage.getPropertyValue("Fabric"), "updated fabric value");
    }

    @Test(priority = 5)
    public void verifyAssigningTask() throws InterruptedException {
        productsPage.searchProduct("prival revaux the grace sunglasses");
        selectProducts.clickOnCheckboxAgainstProduct();
        actionsDDModal.clickOnActionsDropDownIcon();
        actionsDDModal.selectDDListValue("Assign task for 1 product(s)");
        taskNodePage.selectTaskTemplate(taskTemplateNameUpdated, taskName3, "desc");
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), Notification.TASK_CREATION.getDesc());
    }

    @Test(priority = 6)
    public void verifyAssigningCategory() throws InterruptedException {
        productsPage.searchProduct("prival revaux the grace sunglasses");
        selectProducts.clickOnCheckboxAgainstProduct();
        actionsDDModal.clickOnActionsDropDownIcon();
        actionsDDModal.selectDDListValue("Assign category for 1 product(s)");
        categoryNodePage.assignCategory("Women>Top Wear>Shirts");
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), Notification.CATEGORY_PRODUCT_UPDATE.getDesc());
    }

    @Test(priority = 7)
    public void verifyTheAssignedCategory() throws InterruptedException {
        productsPage.searchProduct("prival revaux the grace sunglasses");
        productsPage.clickOnProductEditIcon();
        manageProductGroupPage.waitForPageToLoad();
        Assert.assertTrue(manageProductPage.getTheAssociatedCategories().contains("Women>Top Wear>Shirts"));
    }
}
