package unbxdTests.testNg.ui.pim;

import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.ManageProduct.CreateVariantProduct_Page;
import core.pages.pim.Products.ManageProducts.ManageProducts_Page;
import core.pages.pim.Products.ProductsListing.Products_Page;
import lib.FunctionLibrary;
import lib.JsonReader;
import lib.constants.Constants;
import lib.constants.Notification;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Product_Tests extends BaseTest {

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

    String emailId, password, productID, productName, updatedProductName, variantProperty, variantProductID, variantProductName;
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
    ManageProducts_Page manageProductPage;

    @Page
    CreateVariantProduct_Page createVariantProductPage;

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void returnToOverview() {
        homePage.clickOnOverviewTab();
    }

    @Test(priority = 1)
    public void  openProductPage() {
        homePage.clickOnProductsTab();
        productsPage.waitForProductListingPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("/manageProducts/"));
    }

    @Test(priority = 2)
    public void createProduct() {
        openProductPage();
        productsPage.createProduct(productID, productName);
        manageProductPage.waitForManageProductPageToLoad();
        System.out.println(manageProductPage.getTheProductPropertiesWithAssignedValue().values());
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("productId"));
    }

    @Test(priority = 3)
    public void searchProduct() throws InterruptedException {
        openProductPage();
        productsPage.searchProduct(productName);
        Assert.assertTrue(productsPage.getProductsList().get(0).contains(productName));
    }

    @Test(priority = 4, enabled = false)
    public void updateProduct() throws InterruptedException {
        openProductPage();
        productsPage.searchProduct(productName);
        productsPage.clickOnProductEditIcon();
        manageProductPage.waitForManageProductPageToLoad();
        manageProductPage.updateProperty("Product Name", updatedProductName);
        manageProductPage.clickOnSaveProductButton();
        Assert.assertEquals(manageProductPage.getSuccessMessage(), Notification.PRODUCT_UPDATE_PDP.getDesc());
        manageProductPage.updateProperty("Product Name", productName);
        manageProductPage.clickOnSaveProductButton();
        Assert.assertEquals(manageProductPage.getSuccessMessage(), Notification.PRODUCT_UPDATE_PDP.getDesc());
    }

    @Test(priority = 5)
    public void verifyVariantPropertyCreation() throws InterruptedException {
        Map<String, List<String>> mp = new HashMap<String, List<String>>();
        mp.put("Unassigned", Arrays.asList(propertyImportedArray));
        openProductPage();
        productsPage.searchProduct(productName);
        productsPage.clickOnProductEditIcon();
        manageProductPage.waitForManageProductPageToLoad();
        productsPage.clickOnAddProductVariantLink();
        createVariantProductPage.clickOnPropertyVariantDropDown();
        // Assert.assertEquals(createVariantProductPage.getTheContentOfPropertyVariantDropDown(), mp);
        createVariantProductPage.selectAPropertyVariant(variantProperty);
        Assert.assertEquals(removeLastCharacter(createVariantProductPage.getAllThePropertieselected()), Arrays.asList(variantProperty));
        createVariantProductPage.clickOnPropertyVariantDropDown();
        createVariantProductPage.createPropertyVariant(variantProductID, variantProductName, variantProperty);
        System.out.println(manageProductPage.getTheProductPropertiesWithAssignedValueInEditMode());
        System.out.println(manageProductPage.getTheProductPropertiesWithAssignedValue());
    }

    @Test(priority = 6)
    public void deleteProduct() throws InterruptedException {
        openProductPage();
        productsPage.searchProduct(productName);
        productsPage.clickOnProductEditIcon();
        manageProductPage.waitForManageProductPageToLoad();
        manageProductPage.deleteProperty();
        FunctionLibrary.scrollToTop();
        Assert.assertEquals(manageProductPage.getSuccessMessage(), "Successfully deleted 1 products in PIM");
        productsPage.waitForProductListingPageToLoad();
        FunctionLibrary.refreshPage();
        productsPage.searchProduct(productName);
        Assert.assertEquals(manageProductPage.getNoResultFoundMessage(), Constants.NO_RESULTS_FOUND.getDesc());
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        FunctionLibrary.closeDriver();
    }


}
