package unbxdTests.testNg.ui.pim;

import core.pages.pim.Imports.ManageImport_Page;
import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.Properties.Properties_Page.PropertyListing_page;
import lib.FunctionLibrary;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;
import java.io.FileNotFoundException;


public class ProductImport_Tests extends BaseTest {


    String jsonPathPropertyData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/property.json" ;
    String propertyTypeData = "Property Data";
    int totalPropertyDataRow = 2;
    int totalPropertyColumnEntry = 6;

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    String productInputFile = System.getProperty("user.dir") + "/src/test/resources/testData/pim/suiteProductInputFile.csv";

    String emailId, password, roleName, propertyName, propertyDataType, updatedPropertyName, propertyGroupName, propertiesImported;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            roleName = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][3].toString();
            propertyName = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry )[0][0].toString();
            propertyDataType = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry )[0][1].toString();
            updatedPropertyName = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry )[0][2].toString();
            propertyGroupName = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry )[0][3].toString();
            propertiesImported = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry )[0][4].toString();

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
    ManageImport_Page manangeImportsPage;


    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
    }



    @Test(priority = 1)
    public void openPropertyPageFromManageTab() {
        homePage.clickOnManageTab();
        homePage.clickOnImports();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("/manageImports/"));
    }

    @Test(priority = 2)
    public void verifyProductImports() throws InterruptedException {
        openPropertyPageFromManageTab();
        manangeImportsPage.clickOnProceed();
        manangeImportsPage.uploadFile(productInputFile);
        manangeImportsPage.waitForProductPreImportToGetCompleted();
        manangeImportsPage.mapProductName("Product Name");
        manangeImportsPage.mapProductImage("Image");
        manangeImportsPage.mapParentId("Parent Id");
        manangeImportsPage.mapCategory("Category");
        manangeImportsPage.mapProperties();
        manangeImportsPage.startImport();
        manangeImportsPage.associateAnAdapter("Auto Adapter 1");
        manangeImportsPage.initializeImport();
        manangeImportsPage.waitForProductImportToComplete();
        Assert.assertEquals(manangeImportsPage.getImportSuccessMsg(), "Import Completed!");

    }

    @Test(priority = 3)
    public void verifyFailedProductsCount() {
        Assert.assertEquals(manangeImportsPage.getFailedProductCount(), "0");
    }

    @Test(priority = 4)
    public void verifyTotalProductsCount() {
        Assert.assertEquals(manangeImportsPage.getTotalProductCount(), "20");
    }

    @Test(priority = 5)
    public void verifyProcessedProductsCount() {
        Assert.assertEquals(manangeImportsPage.getProcessedProductCount(), "20");
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        FunctionLibrary.closeDriver();
    }
}
