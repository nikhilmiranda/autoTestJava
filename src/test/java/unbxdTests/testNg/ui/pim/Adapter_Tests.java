package unbxdTests.testNg.ui.pim;

import core.pages.pim.*;
import core.pages.pim.ManageNetwork.Adapter.CustomAdapter.CustomAdapter_Page;
import core.pages.pim.ManageNetwork.Adapter.CustomAdapter.PropertyMapping_Page;
import core.pages.pim.ManageNetwork.Adapter.SystemAdapter.SystemAdapterListing_Page;
import core.pages.pim.ManageNetwork.ManageNetwork_Page;
import core.pages.pim.ManageNetwork.Product.CertifyProducts.ProductsListing_Page;
import lib.FunctionLibrary;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;

public class Adapter_Tests extends BaseTest {

    String jsonPathProductData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/product.json" ;
    String productTypeData = "Product Data";
    int totalProductDataRow = 3;
    int totalProductColumnEntry = 3;

    String jsonPathAdapterData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/adapter.json" ;
    String adapterTypeData = "Adapter Data";
    int totalAdapterDataRow = 1;
    int totalAdapterColumnEntry = 10;

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    public String emailId, password, productID, productName, updatedProductName, adapterName, adapterDesc, adapterProperty1, adapterProperty2,adapterProperty3, adapterProperty4,adapterProperty5, adapterProperty6,adapterProperty7, adapterProperty8;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            productID = JsonReader.getdata(jsonPathProductData, productTypeData, totalProductDataRow, totalProductColumnEntry )[1][0].toString();
            productName = JsonReader.getdata(jsonPathProductData, productTypeData, totalProductDataRow, totalProductColumnEntry)[1][1].toString();
            updatedProductName = JsonReader.getdata(jsonPathProductData, productTypeData, totalProductDataRow, totalProductColumnEntry )[1][2].toString();
            adapterName = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][0].toString();
            adapterDesc = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][1].toString();
            adapterProperty1 = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][2].toString();
            adapterProperty2 = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][3].toString();
            adapterProperty3 = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][4].toString();
            adapterProperty4 = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][5].toString();
            adapterProperty5 = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][6].toString();
            adapterProperty6 = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][7].toString();
            adapterProperty7 = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][8].toString();
            adapterProperty8 = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][9].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    ProductsListing_Page productsListingPage;

    @Page
    ManageNetwork_Page manageNetworkPage;

    @Page
    CustomAdapter_Page customAdapterPage;

    @Page
    PropertyMapping_Page propertyMappingPage;

    @Page
    SystemAdapterListing_Page systemAdapterListingPage;


    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnNetworkTab();
        productsListingPage.waitForPageToLoad();
        manageNetworkPage.clickOnAdapterTab();
        systemAdapterListingPage.waitForPageToLoad();
        customAdapterPage.clickOnCustomAdaptersTab();
        customAdapterPage.waitForPageToLoad();
    }

    @AfterMethod
    public void returnToCustomAdapterPage() throws InterruptedException {
        homePage.clickOnNetworkTab();
        productsListingPage.waitForPageToLoad();
        manageNetworkPage.clickOnAdapterTab();
        systemAdapterListingPage.waitForPageToLoad();
        customAdapterPage.clickOnCustomAdaptersTab();
        customAdapterPage.waitForPageToLoad();

    }

    @Test(priority = 1)
    public void openAdapterPage() throws InterruptedException {
        homePage.clickOnNetworkTab();
        productsListingPage.waitForPageToLoad();
        manageNetworkPage.clickOnAdapterTab();
        systemAdapterListingPage.waitForPageToLoad();
    }

    @Test(priority = 2)
    public void verifyAdapterCreation() {
        customAdapterPage.createAdapter(adapterName, adapterDesc);
        propertyMappingPage.waitForPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("adapterId"));

    }

    @Test(priority = 3)
    public void verifyAdapterSearch() throws InterruptedException {
        customAdapterPage.searchAdapter(adapterName);
        Assert.assertTrue(customAdapterPage.fetchAdapters().get(0).contains(adapterName));

    }

    @Test(priority = 4)
    public void openAdaptorEditPage() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("adapterId"));

    }

    @Test(priority = 5)
    public void verifyPreFilledAdapterProperties() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().size() == 2);

    }

    @Test(priority = 6)
    public void addAdapterProperty_Image() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        propertyMappingPage.addAnPropertyToAdapter(adapterProperty1);
        propertyMappingPage.clickOnSaveMappings();
        Assert.assertEquals(propertyMappingPage.getSuccessInfoMsg(), "Adapter properties mapping successfully updated");
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().contains(adapterProperty1));
       Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().size() == 3);

    }

    @Test(priority = 7)
    public void addAdapterProperty_TreeList() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        propertyMappingPage.addAnPropertyToAdapter(adapterProperty3);
        propertyMappingPage.clickOnSaveMappings();
        Assert.assertEquals(propertyMappingPage.getSuccessInfoMsg(), "Adapter properties mapping successfully updated");
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().contains(adapterProperty3));
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().size() == 4);

    }

    @Test(priority = 8)
    public void addAdapterProperty_Boolean() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        propertyMappingPage.addAnPropertyToAdapter(adapterProperty4);
        propertyMappingPage.clickOnSaveMappings();
        Assert.assertEquals(propertyMappingPage.getSuccessInfoMsg(), "Adapter properties mapping successfully updated");
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().contains(adapterProperty4));
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().size() == 5);

    }

    @Test(priority = 9)
    public void addAdapterProperty_TextArea() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        propertyMappingPage.addAnPropertyToAdapter(adapterProperty5);
        propertyMappingPage.clickOnSaveMappings();
        Assert.assertEquals(propertyMappingPage.getSuccessInfoMsg(), "Adapter properties mapping successfully updated");
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().contains(adapterProperty5));
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().size() == 6);

    }

    @Test(priority = 10)
    public void addAdapterProperty_Link() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        propertyMappingPage.addAnPropertyToAdapter(adapterProperty6);
        propertyMappingPage.clickOnSaveMappings();
        Assert.assertEquals(propertyMappingPage.getSuccessInfoMsg(), "Adapter properties mapping successfully updated");
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().contains(adapterProperty6));
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().size() == 7);

    }

    @Test(priority = 11)
    public void addAdapterProperty_Number() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        propertyMappingPage.addAnPropertyToAdapter(adapterProperty7);
        propertyMappingPage.clickOnSaveMappings();
        Assert.assertEquals(propertyMappingPage.getSuccessInfoMsg(), "Adapter properties mapping successfully updated");
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().contains(adapterProperty7));
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().size() == 8);

    }


    @Test(priority = 12)
    public void addAdapterProperty_Date() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        propertyMappingPage.addAnPropertyToAdapter(adapterProperty8);
        propertyMappingPage.clickOnSaveMappings();
        Assert.assertEquals(propertyMappingPage.getSuccessInfoMsg(), "Adapter properties mapping successfully updated");
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().contains(adapterProperty8));
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().size() == 9);

    }


    @Test(priority = 13)
    public void addEmptyAdapterProperty() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        propertyMappingPage.clickOnAddEmptyProperty();
        propertyMappingPage.clickOnSaveMappings();
        Assert.assertEquals(propertyMappingPage.getSuccessInfoMsg(), "Adapter properties mapping successfully updated");
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().contains("New Property"));
        Assert.assertTrue(propertyMappingPage.fetchAdapterProperties().size() == 10);

    }

    @Test(priority = 14)
    public void performMappingForEmptyProperty() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        propertyMappingPage.createAdapterMappings("Product Id", "Product Id");
        propertyMappingPage.createAdapterMappings("Product Name", "Product Name");
        propertyMappingPage.createAdapterMappingsForEmptyProperty(adapterProperty2, adapterProperty2);
        propertyMappingPage.clickOnSaveMappings();
        Assert.assertEquals(propertyMappingPage.getSuccessInfoMsg(), "Adapter properties mapping successfully updated");
    }

    @Test(priority = 15)
    public void verifyTheCountOfMappedProperties() throws InterruptedException {
        customAdapterPage.openAdapter(adapterName);
        propertyMappingPage.clickOnBackToListIcon();
        Assert.assertTrue(customAdapterPage.fetchAdapters().get(0).contains("10 mapped properties"));
    }
}
