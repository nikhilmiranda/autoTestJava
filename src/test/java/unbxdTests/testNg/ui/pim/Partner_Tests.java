package unbxdTests.testNg.ui.pim;

import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.ManageNetwork.Adapter.CustomAdapter.CustomAdapter_Page;
import core.pages.pim.ManageNetwork.Adapter.CustomAdapter.PropertyMapping_Page;
import core.pages.pim.ManageNetwork.ManageNetwork_Page;
import core.pages.pim.ManageNetwork.Partner.AddExportSFTP_Page;
import core.pages.pim.ManageNetwork.Partner.ManagePartner_Page;
import core.pages.pim.ManageNetwork.Partner.PartnerListing_Page;
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

public class Partner_Tests extends BaseTest {

    String jsonPathPartnerData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/partner.json" ;
    String partnerTypeData = "Partner Data";
    int totalPartnerDataRow = 2;
    int totalPartnerColumnEntry = 3;

    String jsonPathSFTPData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/partnerSFTP.json" ;
    String sftpTypeData = "Partner SFTP Data";
    int totalSFTPDataRow = 2;
    int totalSFTPColumnEntry = 6;


    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    public String emailId, password, partnerName, partnerEmail, updatedPartnerName, host, port, username, sftpPassword, path, sftpName;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            partnerName = JsonReader.getdata(jsonPathPartnerData, partnerTypeData, totalPartnerDataRow, totalPartnerColumnEntry )[0][0].toString();
            partnerEmail = JsonReader.getdata(jsonPathPartnerData, partnerTypeData, totalPartnerDataRow, totalPartnerColumnEntry)[0][1].toString();
            updatedPartnerName = JsonReader.getdata(jsonPathPartnerData, partnerTypeData, totalPartnerDataRow, totalPartnerColumnEntry)[0][2].toString();
            host = JsonReader.getdata(jsonPathSFTPData, sftpTypeData, totalSFTPDataRow, totalSFTPColumnEntry)[0][0].toString();
            port = JsonReader.getdata(jsonPathSFTPData, sftpTypeData, totalSFTPDataRow, totalSFTPColumnEntry)[0][1].toString();
            username = JsonReader.getdata(jsonPathSFTPData, sftpTypeData, totalSFTPDataRow, totalSFTPColumnEntry)[0][2].toString();
            sftpPassword = JsonReader.getdata(jsonPathSFTPData, sftpTypeData, totalSFTPDataRow, totalSFTPColumnEntry)[0][3].toString();
            path = JsonReader.getdata(jsonPathSFTPData, sftpTypeData, totalSFTPDataRow, totalSFTPColumnEntry)[0][4].toString();
            sftpName = JsonReader.getdata(jsonPathSFTPData, sftpTypeData, totalSFTPDataRow, totalSFTPColumnEntry)[0][5].toString();

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
    PartnerListing_Page partnerListingPage;

    @Page
    ManagePartner_Page managePartnerPage;

    @Page
    AddExportSFTP_Page addSFTPPage;


    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnNetworkTab();
        productsListingPage.waitForPageToLoad();
        manageNetworkPage.clickOnPartnerTab();
        partnerListingPage.waitForPageToLoad();
    }

    @AfterMethod
    public void returnToPartnerPage() throws InterruptedException {
        manageNetworkPage.clickOnPartnerTab();
        partnerListingPage.waitForPageToLoad();

    }

    @Test(priority = 1)
    public void openPartnerPage() throws InterruptedException {
        manageNetworkPage.clickOnPartnerTab();
        partnerListingPage.waitForPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("tab=partners"));
    }

    @Test(priority = 2)
    public void verifyPartnerCreation() {
        partnerListingPage.createPartner(partnerName, partnerEmail);
        managePartnerPage.waitForPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("partnerId="));

    }

    @Test(priority = 3)
    public void verifyPartnerName() {
        partnerListingPage.searchPartnerName(partnerName);
        partnerListingPage.clickOnEditPartner();
       Assert.assertEquals(managePartnerPage.getPartnerName(), partnerName);
    }

    @Test(priority = 4)
    public void verifyPartnerEmail() {
        partnerListingPage.searchPartnerName(partnerName);
        partnerListingPage.clickOnEditPartner();
       Assert.assertEquals(managePartnerPage.getPartnerEmail(), partnerEmail);

    }

    @Test(priority = 5)
    public void verifyPartnerEmailIsDisabled() {
        partnerListingPage.searchPartnerName(partnerName);
        partnerListingPage.clickOnEditPartner();
        Assert.assertEquals(managePartnerPage.isPartnerEmailDisabled().toString(), "false");
    }

    @Test(priority = 6)
    public void verifyPartnerDetailsInListingPage() {
       Assert.assertTrue(partnerListingPage.getPartnerDetails().get(0).contains(partnerName));
       Assert.assertTrue(partnerListingPage.getPartnerDetails().get(0).contains(partnerEmail));
    }

    @Test(priority = 7)
    public void verifyPartnerUpdate() {
        partnerListingPage.searchPartnerName(partnerName);
        partnerListingPage.clickOnEditPartner();
        managePartnerPage.enterPartnerName(updatedPartnerName);
        managePartnerPage.clickOnUpdate();
        Assert.assertEquals(managePartnerPage.getInfoMessage(), "Partner details updated successfully.");
        Assert.assertTrue(managePartnerPage.getPartnerName().equalsIgnoreCase(updatedPartnerName));
        managePartnerPage.clickOnBackToList();
        Assert.assertTrue(partnerListingPage.getPartnerDetails().get(0).contains(updatedPartnerName));
    }

    @Test(priority = 8)
    public void verifyPartnerSFTPCreation() {
        partnerListingPage.searchPartnerName(updatedPartnerName);
        partnerListingPage.clickOnEditPartner();
        managePartnerPage.clickOnAddNewSFTP();
        addSFTPPage.createPartnerSFTP(sftpName, host, port, path, username,sftpPassword);
        Assert.assertEquals(addSFTPPage.fetchInfoMsg(), "Connection tested successfully");
        addSFTPPage.clickOnSaveAddress();
        Assert.assertEquals(addSFTPPage.fetchInfoMsg(), "SFTP address added successfully");

    }

}
