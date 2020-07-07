package unbxdTests.testNg.ui.pim;

import core.components.pim.AssociateProperties;
import core.pages.pim.Categories.CategoriesListing_Page;
import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
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
import java.util.Arrays;

public class Category_Tests extends BaseTest {


    String jsonPathCategoryData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/category.json" ;
    String categoryTypeData = "Category Data";
    int totalCategoryDataRow = 1;
    int totalCategoryColumnEntry = 1;

    String jsonPathAdapterData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/adapter.json" ;
    String adapterTypeData = "Adapter Data";
    int totalAdapterDataRow = 1;
    int totalAdapterColumnEntry = 4;

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    public String emailId, password, categoryName;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            categoryName = JsonReader.getdata(jsonPathCategoryData, categoryTypeData, totalCategoryDataRow, totalCategoryColumnEntry)[0][0].toString();

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

    @Page
    CategoriesListing_Page categoriesListing_page;

    @Page
    AssociateProperties associateProperties;


    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnManageTab();
        homePage.clickOnCategoy();
        categoriesListing_page.waitForPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("/categories/"));
    }

//    @AfterMethod
//    public void returnToCustomAdapterPage() throws InterruptedException {
//        homePage.clickOnNetworkTab();
//        productsListingPage.waitForPageToLoad();
//        manageNetworkPage.clickOnAdapterTab();
//        systemAdapterListingPage.waitForPageToLoad();
//        customAdapterPage.clickOnCustomAdaptersTab();
//        customAdapterPage.waitForPageToLoad();
//
//    }

    @Test(priority = 1)
    public void verifyTheCategoryCreatedFromTheProductInputFIle() {
        Assert.assertEquals(categoriesListing_page.getAllCategoriesListed(), Arrays.asList("Women", "Foot Wear", "Sandals", "Sneakers", "Booties", "Top Wear", "Shirts", "Accessories", "Sunglasses", "Men", "Top Wear", "Vests"));

    }

    @Test(priority = 2)
    public void verifyCategoryCreation() {
        categoriesListing_page.createCategory(categoryName);
        Assert.assertTrue(categoriesListing_page.getAllCategoriesListed().contains(categoryName));
    }


}
