package unbxdTests.testNg.ui.pim;

import core.pages.pim.Catalog.CatalogListing_Page;
import core.pages.pim.Catalog.CatalogView_Page;
import core.pages.pim.Catalog.ManageCatalog.Facets_Page;
import core.pages.pim.Catalog.ManageCatalog.ManageCatalog_Page;
import core.pages.pim.Catalog.ManageCatalog.Products_Page;
import core.pages.pim.Catalog.ManageCatalog.Properties_Page;
import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import unbxdTests.testNg.ui.BaseTest;
import lib.BrowserInitializer;
import lib.FunctionLibrary;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;


public class Catalog_Tests extends BaseTest {

    String jsonPathCatalogData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/catalog.json" ;
    String catalogTypeData = "Catalog Data";
    int totalCatalogDataRow = 1;
    int totalCatalogColumnEntry = 5;

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    public String emailId, password, catalogName, property1, property2, facet1, facet2;
    Boolean moveToCatalog = true;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            catalogName = JsonReader.getdata(jsonPathCatalogData, catalogTypeData, totalCatalogDataRow, totalCatalogColumnEntry )[0][0].toString();
            property1 = JsonReader.getdata(jsonPathCatalogData, catalogTypeData, totalCatalogDataRow, totalCatalogColumnEntry )[0][1].toString();
            property2 = JsonReader.getdata(jsonPathCatalogData, catalogTypeData, totalCatalogDataRow, totalCatalogColumnEntry )[0][2].toString();
            facet1 = JsonReader.getdata(jsonPathCatalogData, catalogTypeData, totalCatalogDataRow, totalCatalogColumnEntry )[0][3].toString();
            facet2 = JsonReader.getdata(jsonPathCatalogData, catalogTypeData, totalCatalogDataRow, totalCatalogColumnEntry )[0][4].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    CatalogListing_Page catalogListingPage;

    @Page
    Products_Page productsPage;

    @Page
    Properties_Page propertiesPage;

    @Page
    Facets_Page facetsPage;

    @Page
    CatalogView_Page catalogViewPage;

    @Page
    ManageCatalog_Page manageCatalogPage;



    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnManageTab();
        homePage.clickOnCatalogs();
        catalogListingPage.waitForElementToLoad();
    }

    @AfterMethod
    public void returnToCustomAdapterPage() {
        if(moveToCatalog == false){
            FunctionLibrary.refreshPage();
        }
        else {
            homePage.clickOnManageTab();
            homePage.clickOnCatalogs();
            catalogListingPage.waitForElementToLoad();
        }
    }

    @Test(priority = 1)
    public void createCatalog() {
        catalogListingPage.createCatalog(catalogName);
        manageCatalogPage.waitForPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("action=manageCatalog;catalogId="));
    }

    @Test(priority = 2)
    public void addParentProductToCatalog() throws InterruptedException {
        catalogListingPage.editCatalog(catalogName);
        manageCatalogPage.waitForPageToLoad();
        productsPage.addProductToCatalog("snooky Men Vest");
        Assert.assertEquals(productsPage.getSuccessMessage(), "Catalog successfully updated");

    }

    @Test(priority = 3)
    public void verifyDefaultPropertiesAddedToCatalog() throws InterruptedException {
        catalogListingPage.editCatalog(catalogName);
        manageCatalogPage.clickOnPropertiesTab();
        propertiesPage.waitForPageToLoad();
        Assert.assertEquals(propertiesPage.getAllThePropertiesAddedToCatalog(), Arrays.asList("Product Id", "Product Name", "Image"));

    }


    @Test(priority = 4)
    public void verifyAddingPropertiesToCatalog() throws InterruptedException {
        catalogListingPage.editCatalog(catalogName);
        manageCatalogPage.clickOnPropertiesTab();
        propertiesPage.waitForPageToLoad();
        propertiesPage.clickOnPropertyDropDown();
        propertiesPage.selectAPropertyVariant(property1);
        propertiesPage.selectAPropertyVariant(property2);
        propertiesPage.clickOnPropertyDropDown();
        propertiesPage.clickOnUpdateButton();
        Assert.assertEquals(propertiesPage.fetchInfoMsg(), "Catalog successfully updated");
        Assert.assertTrue(propertiesPage.getAllThePropertiesAddedToCatalog().containsAll(Arrays.asList("Product Id", "Product Name", "Image", property1, property2)));

    }

    @Test(priority = 5)
    public void verifyTheOrderOfPropertyAddedToCatalog() {
        catalogListingPage.editCatalog(catalogName);
        manageCatalogPage.clickOnPropertiesTab();
        propertiesPage.waitForPageToLoad();
        Assert.assertEquals(propertiesPage.getAllThePropertiesAddedToCatalog(), Arrays.asList("Product Id", "Product Name", "Image", property1, property2));

    }

//    @Test(priority = 4)
//    public void addFacetsToCatalog() throws InterruptedException {
//        catalogListingPage.editCatalog(catalogName);
//        manageCatalogPage.clickOnFacetsTab();
//        facetsPage.waitForPageToLoad();
//        facetsPage.addFacetsToCatalog(facet1);
//        facetsPage.addFacetsToCatalog(facet2);
//        Assert.assertEquals(facetsPage.getAllTheFacetsAdded(), Arrays.asList(facet1, facet2));
//        facetsPage.clickOnUpdateButton();
//        Assert.assertEquals(facetsPage.fetchInfoMsg(), "Catalog has been successfully updated.");
//        Assert.assertEquals(facetsPage.getAllTheFacetsAdded(), Arrays.asList(facet1, facet2));
//
//
//    }

    @Test(priority = 6)
    public void publishCatalog() {
        catalogListingPage.editCatalog(catalogName);
        manageCatalogPage.waitForPageToLoad();
        manageCatalogPage.publishCatalog();
        Assert.assertEquals(propertiesPage.fetchInfoMsg(), "Catalog successfully updated");


    }

    @Test(priority = 7)
    public void openCatalogPage() {
        catalogListingPage.openCatalog(catalogName);
        String curWindow = BrowserInitializer.getDriver().getWindowHandle();
        System.out.println(curWindow);
        Set<String> windows = BrowserInitializer.getDriver().getWindowHandles();
        System.out.println(windows);
        System.out.println(windows.size());
        Iterator<String> itr = windows.iterator();
        String catalogTab = null;
        while(itr. hasNext()){
            if(curWindow != itr.next()){
                catalogTab = itr.next();
                System.out.println(catalogTab);
                break;
            }
            itr.next();
        }
        BrowserInitializer.getDriver().switchTo().window(catalogTab);

        System.out.println(FunctionLibrary.getPageURL());
        moveToCatalog = false;
    }

    @Test(priority = 8)
    public void verifyTheProductsListedInCatalog() {
        Assert.assertEquals(catalogViewPage.getAlltheProductNames(), Arrays.asList("snooky Men Vest"));

    }

    @Test(priority = 9)
    public void verifyTheCatalogSearchSuggestions() throws InterruptedException {
        catalogViewPage.clickOnSearch();
        Assert.assertEquals(catalogViewPage.getAllTheSearchSuggesstions(), Arrays.asList("Snooky Men Vest"));

    }

    @Test(priority = 10)
    public void verifyTheProductProperties() throws InterruptedException {
        catalogViewPage.openProduct("snooky Men Vest");
        Assert.assertEquals(catalogViewPage.getAllTheProductPropertiesDetails(), Arrays.asList("Product Id20", "SizeS,  M,  L,  XL,  XLL"));

    }
}