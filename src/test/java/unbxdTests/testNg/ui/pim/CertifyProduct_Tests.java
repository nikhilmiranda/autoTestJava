package unbxdTests.testNg.ui.pim;

import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.ManageNetwork.Product.NetworkProducts_Page;
import core.pages.pim.ManageNetwork.Product.ProductLabels.ManageLabel_Page;
import core.pages.pim.ManageNetwork.Product.ProductLabels.ProductsLabel_Page;
import core.pages.pim.ManageNetwork.Product.CertifyProducts.ProductsListing_Page;
import core.pages.pim.Products.ProductsListing.Products_Page;
import core.pages.pim.ProductsListing.Actions_Page;
import core.pages.pim.ProductsListing.CertifyProduct_Page;
import lib.FunctionLibrary;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class CertifyProduct_Tests extends BaseTest {

    String jsonPathProductData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/product.json" ;
    String productTypeData = "Product Data";
    int totalProductDataRow = 3;
    int totalProductColumnEntry = 3;

    String jsonPathLabelData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/label.json" ;
    String labelTypeData = "Label Data";
    int totalLabelDataRow = 1;
    int totalLabelColumnEntry = 2;

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    String emailId, password, productID, productName, updatedProductName, labelName;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            productID = JsonReader.getdata(jsonPathProductData, productTypeData, totalProductDataRow, totalProductColumnEntry )[1][0].toString();
            productName = JsonReader.getdata(jsonPathProductData, productTypeData, totalProductDataRow, totalProductColumnEntry)[1][1].toString();
            updatedProductName = JsonReader.getdata(jsonPathProductData, productTypeData, totalProductDataRow, totalProductColumnEntry )[1][2].toString();
            labelName = JsonReader.getdata(jsonPathLabelData, labelTypeData, totalLabelDataRow, totalLabelColumnEntry )[0][0].toString();

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
    CertifyProduct_Page certifyProductPage;

    @Page
    Actions_Page actionsMenuPage;

    @Page
    ProductsListing_Page certifiedProductsPage;

    @Page
    ProductsLabel_Page productsLabelPage;

    @Page
    ManageLabel_Page manageLabelPage;

    @Page
    NetworkProducts_Page networkProductsPage;

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnProductsTab();
        productsPage.waitForProductListingPageToLoad();

    }

    @Test
    public void verifyCertifyProduct() throws InterruptedException {
        productsPage.searchProduct(productName);
        productsPage.clickOnToggleGroupByParentSolo();
        productsPage.clickOnCheckBoxAgainstProduct();
        actionsMenuPage.clickOnActionsIcon();
        actionsMenuPage.clickOnCertifyForNetworkLink();
        Assert.assertEquals(certifyProductPage.fetchTheCountOfProductToBeCertified(), "Certify 1 product(s) for network");
        certifyProductPage.clickOnLabelSelectionDropDownIcon();
        certifyProductPage.createANewLabel(labelName);
        Assert.assertEquals(certifyProductPage.fetchCertificationSuccessMsg(), "Products certified to network successfully");
        FunctionLibrary.refreshPage();
        homePage.clickOnNetworkTab();
        networkProductsPage.clickOnProductLabelTab();
        productsLabelPage.waitForPageToLoad();
        if(productsLabelPage.getAllTheProductLabels().size() == 0){
            Thread.sleep(5000);
            FunctionLibrary.refreshPage();

        }
        Assert.assertEquals(productsLabelPage.getAllTheProductLabels(), Arrays.asList(labelName));
//         if(productsLabelPage.getAllTheProductLabelDetails().get(0).contains("4")){
//           //do nothing
//        }
//        else {
//            Thread.sleep(50000);
//            FunctionLibrary.refreshPage();
//        }
//        Assert.assertTrue(productsLabelPage.getAllTheProductLabelDetails().get(0).contains("4"));

        networkProductsPage.clickOnProductTab();
        certifiedProductsPage.waitForPageToLoad();
        if(certifiedProductsPage.getAllTheProducts().size() == 0){
            Thread.sleep(15000);
            FunctionLibrary.refreshPage();
        }
        Assert.assertEquals(certifiedProductsPage.getAllTheProducts(), Arrays.asList(productName, productName, productName, productName));

    }

    @Test
    public void verifyTheProductsAddedToLabel() {
        networkProductsPage.clickOnProductLabelTab();
        productsLabelPage.clickOnLabelEditIcon();
        manageLabelPage.waitForPageToLoad();
        Assert.assertEquals(manageLabelPage.getAllTheCertifiedProductNameInLabel(), Arrays.asList(productName +"\nVariant", productName +"\nVariant", productName +"\nVariant", productName +"\nVariant"));
        productsPage.clickOnToggleGroupByParentSolo();
        Assert.assertEquals(manageLabelPage.getAllTheCertifiedProductNameInLabel(), Arrays.asList(productName +"\nParent"));

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        FunctionLibrary.closeDriver();

    }


}
