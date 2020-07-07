package unbxdTests.testNg.ui.pim;

import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.ManageNetwork.Exports.ExportList.ExportList_Page;
import core.pages.pim.ManageNetwork.ManageNetwork_Page;
import core.pages.pim.ManageNetwork.Product.ProductLabels.ExportProduct_Page;
import core.pages.pim.ManageNetwork.Product.CertifyProducts.ProductsListing_Page;
import core.pages.pim.ManageNetwork.Product.NetworkProducts_Page;
import core.pages.pim.ManageNetwork.Product.ProductLabels.ManageLabel_Page;
import core.pages.pim.ManageNetwork.Product.ProductLabels.ProductsLabel_Page;
import lib.FunctionLibrary;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;

public class ExportProducts_Tests extends BaseTest {

    String jsonPathPartnerData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/partner.json" ;
    String partnerTypeData = "Partner Data";
    int totalPartnerDataRow = 2;
    int totalPartnerColumnEntry = 3;

    String jsonPathExportData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/export.json" ;
    String exportTypeData = "Export Data";
    int totalExportDataRow = 2;
    int totalExportColumnEntry = 2;

    String jsonPathSFTPData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/partnerSFTP.json" ;
    String sftpTypeData = "Partner SFTP Data";
    int totalSFTPDataRow = 2;
    int totalSFTPColumnEntry = 6;

    String jsonPathAdapterData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/adapter.json" ;
    String adapterTypeData = "Adapter Data";
    int totalAdapterDataRow = 1;
    int totalAdapterColumnEntry = 10;


    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    public String emailId, password, adapterName, updatedPartnerName, sftpName, exportName1, exportName2;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();

            updatedPartnerName = JsonReader.getdata(jsonPathPartnerData, partnerTypeData, totalPartnerDataRow, totalPartnerColumnEntry)[0][2].toString();
            adapterName = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][0].toString();
            sftpName = JsonReader.getdata(jsonPathSFTPData, sftpTypeData, totalSFTPDataRow, totalSFTPColumnEntry)[0][5].toString();

            exportName1 = JsonReader.getdata(jsonPathExportData, exportTypeData, totalExportDataRow, totalExportColumnEntry)[0][0].toString();
            exportName2 = JsonReader.getdata(jsonPathExportData, exportTypeData, totalExportDataRow, totalExportColumnEntry)[0][1].toString();

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
    ExportList_Page exportListPage;

    @Page
    ExportProduct_Page exportProductPage;

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
        homePage.clickOnNetworkTab();
        productsListingPage.waitForPageToLoad();
        networkProductsPage.clickOnProductLabelTab();
        productsLabelPage.waitForPageToLoad();
        productsLabelPage.clickOnLabelEditIcon();
        manageLabelPage.waitForPageToLoad();
    }

    @AfterMethod
    public void returnToPartnerPage() {


    }

    @Test(priority = 1)
    public void verifyExportWithoutReadinessCheckViaEmail() throws InterruptedException {
        manageLabelPage.clickOnOtherActionsButton();
        manageLabelPage.clickOnSendAllProductsToPartnerLink();
        exportProductPage.createExport(exportName1, updatedPartnerName, adapterName, true, true, true,true, true, false, true, false, "abhishek.sahu@unbxd.com", sftpName);
        Assert.assertEquals(manageLabelPage.getInfoMsg(), "Export created successfully");
    }

    @Test(priority = 2)
    public void verifyExportWithoutReadinessCheckViaSFTP() throws InterruptedException {
        manageLabelPage.clickOnOtherActionsButton();
        manageLabelPage.clickOnSendAllProductsToPartnerLink();
        exportProductPage.createExport(exportName2, updatedPartnerName, adapterName, true, true, true,true, false, true, true, false, "abhishek.sahu@unbxd.com", sftpName);
        Assert.assertEquals(manageLabelPage.getInfoMsg(), "Export created successfully");
    }

    @Test(priority = 3)
    public void verifyTheExportedFileViaEmail() throws InterruptedException {
        manageNetworkPage.clickOnExportsTab();
        exportListPage.waitForPageToLoad();
        exportListPage.searchexport(exportName1);
        Assert.assertTrue(exportListPage.fetchExports().get(0).contains(exportName1));
        if(exportListPage.fetchExports().get(0).contains("Exported"))
            Assert.assertEquals(exportListPage.getExportStatus(), "Exported");
        else{
            Thread.sleep(40000);
            FunctionLibrary.refreshPage();
            exportListPage.searchexport(exportName2);
            Assert.assertEquals(exportListPage.getExportStatus(), "Exported");
        }

    }

    @Test(priority = 4)
    public void verifyTheExportedFileViaSFTP() throws InterruptedException {
        manageNetworkPage.clickOnExportsTab();
        exportListPage.waitForPageToLoad();
        exportListPage.searchexport(exportName2);
        Assert.assertTrue(exportListPage.fetchExports().get(0).contains(exportName2));
        if(exportListPage.fetchExports().get(0).contains("Exported"))
            Assert.assertEquals(exportListPage.getExportStatus(), "Exported");
        else{
            Thread.sleep(40000);
            FunctionLibrary.refreshPage();
            exportListPage.searchexport(exportName2);
            Assert.assertEquals(exportListPage.getExportStatus(), "Exported");
        }
    }
}
