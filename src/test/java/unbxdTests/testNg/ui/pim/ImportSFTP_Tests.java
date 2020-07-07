package unbxdTests.testNg.ui.pim;

import core.components.pim.Search;
import core.pages.pim.Home_Page;
import core.pages.pim.Imports.AddImportSFTP_Page;
import core.pages.pim.Imports.ManageImport_Page;
import core.pages.pim.Login_Page;
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

public class ImportSFTP_Tests extends BaseTest {

    String jsonPathPartnerData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/partner.json" ;
    String partnerTypeData = "Partner Data";
    int totalPartnerDataRow = 2;
    int totalPartnerColumnEntry = 3;

    String jsonPathImportSFTPData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/importSFTP.json" ;
    String sftpImportTypeData = "Import SFTP Data";
    int totalSFTPImportDataRow = 2;
    int totalSFTPImportColumnEntry = 7;


    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    public String emailId, password, host, port, username, sftpPassword, path, sftpName,sftpNameUpdated;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            host = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][0].toString();
            port = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][1].toString();
            username = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][2].toString();
            sftpPassword = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][3].toString();
            path = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][4].toString();
            sftpName = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][5].toString();
            sftpNameUpdated = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][6].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Search search;

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    ManageImport_Page manageImportsPage;

    @Page
    AddImportSFTP_Page addImportSFTPPage;

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnManageTab();
        homePage.clickOnImports();
    }

    @AfterMethod
    public void returnToPartnerPage() {
        manageImportsPage.clickOnImportSftpListTab();
        addImportSFTPPage.waitForPageToLoad();
    }

    @Test(priority = 1)
    public void openImportSftpPage() {
        manageImportsPage.clickOnImportSftpListTab();
        addImportSFTPPage.waitForPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("tab=importSFTPList"));
    }

    @Test(priority = 2)
    public void verifyImportSftpCreation() throws InterruptedException {
        addImportSFTPPage.createImportSFTP(sftpName, host, port, path, username,sftpPassword);
        search.search(sftpName);
        Assert.assertEquals(addImportSFTPPage.fetchSFTPList(), Arrays.asList(sftpName));
    }

    @Test(priority = 3)
    public void verifyImportSftpUpdate() throws InterruptedException {
        search.search(sftpName);
        addImportSFTPPage.clickOnEditSFTP();
        addImportSFTPPage.createImportSFTP(sftpNameUpdated, host, port, path, username,sftpPassword);
        search.search(sftpNameUpdated);
        Thread.sleep(2000);
        Assert.assertEquals(addImportSFTPPage.fetchSFTPList(), Arrays.asList(sftpNameUpdated));
    }

    @Test(priority = 4)
    public void deleteSftp() throws InterruptedException {
        search.search(sftpNameUpdated);
        addImportSFTPPage.clickOnDeleteSFTP();
        search.search(sftpNameUpdated);
        Assert.assertEquals(addImportSFTPPage.fetchBlankPageContent(), "Could not find any Import SFTP details in your system");
    }
    @Test(priority = 5)
    public void verifyCreation() throws InterruptedException {
        addImportSFTPPage.createImportSFTP(sftpName, host, port, path, username,sftpPassword);
        search.search(sftpName);
        Assert.assertEquals(addImportSFTPPage.fetchSFTPList(), Arrays.asList(sftpName));

    }

}
