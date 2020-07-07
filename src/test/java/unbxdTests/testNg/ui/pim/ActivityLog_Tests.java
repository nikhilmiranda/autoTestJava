package unbxdTests.testNg.ui.pim;

import core.components.pim.FilterListModal;
import core.components.pim.NotificationMsg;
import core.components.pim.PrimaryButton;
import core.pages.pim.ActivityLog_Page;
import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.Products.ManageProducts.ManageProducts_Page;
import core.pages.pim.Products.ProductsListing.Products_Page;
import lib.JsonReader;
import lib.constants.Constants;
import lib.constants.Notification;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

public class ActivityLog_Tests extends BaseTest {

    String jsonPathPropertyData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/property.json";
    String propertyTypeData = "Property Data";
    int totalPropertyDataRow = 2;
    int totalPropertyColumnEntry = 6;

    String jsonPathProductData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/product.json";
    String productTypeData = "Product Data";
    int totalProductDataRow = 3;
    int totalProductColumnEntry = 3;

    String jsonPathActivityLogData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/activityLog.json";
    String activityLogTypeData = "Activity Log Data";
    int totalActivityLogDataRow = 1;
    int totalActivityLogColumnEntry = 2;

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json";
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    String emailId, password, productID, productName, updatedProductName, variantProperty, variantProductID, variantProductName, product1, product2;

    String[] propertyImportedArray = {"Product Id", "Image", "Product Name", "Price", "Product Details", "Color", "Gender", "Size", "Pattern", "Sleeve", "Fit", "Fabric", "Fabric Care", "Suitable For", "Neck Type", "Payment Mode", "Return Policy", "Parent Id"};

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
            product1 = JsonReader.getdata(jsonPathActivityLogData, activityLogTypeData, totalActivityLogDataRow, totalActivityLogColumnEntry)[0][0].toString();
            product2 = JsonReader.getdata(jsonPathActivityLogData, activityLogTypeData, totalActivityLogDataRow, totalActivityLogColumnEntry)[0][1].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    NotificationMsg notificationMsg;

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    Products_Page productsPage;

    @Page
    ManageProducts_Page manageProductPage;

    @Page
    FilterListModal filterListModal;

    @Page
    ActivityLog_Page activityLogPage;

    @Page
    PrimaryButton primaryButton;

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnProductsTab();
        productsPage.waitForProductListingPageToLoad();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void returnToOverview() {
        activityLogPage.clickOnCloseIcon();
        homePage.clickOnProductsTab();
        productsPage.waitForProductListingPageToLoad();
    }

    @Test(priority = 1)
    public void verifyActivityLogForAssetCreation() throws InterruptedException {
        productsPage.searchProduct(product1);
        productsPage.clickOnProductEditIcon();
        manageProductPage.waitForManageProductPageToLoad();
        manageProductPage.clickOnActivityLogBtn();
        Assert.assertTrue(activityLogPage.retrieveLatestActivityLog().contains(Constants.PRODUCT_MODIFICATION_IMPORT_LOG.getDesc()));
        activityLogPage.clickOnExpandIcon();
        Assert.assertTrue(activityLogPage.retrieveLatestDetailLog().contains("pim-assets.unbxd.com/images/"));
    }

    @Test(priority = 2)
    public void verifyAssetAssociationLog() throws InterruptedException {
        productsPage.searchProduct(product2);
        productsPage.clickOnProductEditIcon();
        manageProductPage.waitForManageProductPageToLoad();
        manageProductPage.clickOnActivityLogBtn();
        Assert.assertTrue(activityLogPage.retrieveLatestActivityLog().contains(Constants.PRODUCT_MODIFICATION_ASSET_LOG.getDesc()));
        activityLogPage.clickOnExpandIcon();
        Assert.assertTrue(activityLogPage.retrieveLatestDetailLog().contains("pim-assets.unbxd.com/images/"));
        Assert.assertTrue(activityLogPage.retrieveLatestDetailLog().contains("asset.jpeg"));

    }

    @Test(priority = 3)
    public void verifyImageLog() throws InterruptedException {
        productsPage.searchProduct(product2);
        productsPage.clickOnProductEditIcon();
        manageProductPage.waitForManageProductPageToLoad();
        manageProductPage.updateProperty("Price", "90.00");
        manageProductPage.clickOnSaveCommentIcon();
        manageProductPage.enterComment("Test Comment");
        primaryButton.clickOnPrimaryButton();
        manageProductPage.clickOnSaveProductButton();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), Notification.PRODUCT_UPDATE.getDesc());
        manageProductPage.clickOnActivityLogBtn();
        Assert.assertTrue(activityLogPage.retrieveLatestActivityLog().contains(Constants.PRODUCT_MODIFICATION_EDIT_LOG.getDesc()));
        activityLogPage.clickOnExpandIcon();
        Assert.assertTrue(activityLogPage.retrieveLatestDetailLog().contains("Price $12.50 90.00 Test Comment"));
    }

    @Test(priority = 4)
    public void verifyLogWhenOnNoModification() throws InterruptedException {
        productsPage.searchProduct(product2);
        productsPage.clickOnProductEditIcon();
        manageProductPage.waitForManageProductPageToLoad();
        manageProductPage.updateProperty("Price", "90.00");
        manageProductPage.clickOnSaveCommentIcon();
        manageProductPage.enterComment("Test Comment");
        primaryButton.clickOnPrimaryButton();
        manageProductPage.clickOnSaveProductButton();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), Notification.PRODUCT_UPDATE_NOT_MODIFIED.getDesc());
        manageProductPage.clickOnActivityLogBtn();
        Assert.assertTrue(activityLogPage.retrieveLatestActivityLog().contains(Constants.PRODUCT_MODIFICATION_EDIT_LOG.getDesc()));
        activityLogPage.clickOnExpandIcon();
        Assert.assertTrue(activityLogPage.retrieveLatestDetailLog().contains("Price $12.50 90.00 Test Comment"));
    }
}
