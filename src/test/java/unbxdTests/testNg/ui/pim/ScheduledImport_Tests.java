package unbxdTests.testNg.ui.pim;

import core.components.pim.*;
import core.pages.pim.Home_Page;
import core.pages.pim.Imports.AddImportSFTP_Page;
import core.pages.pim.Imports.ManageImports_Page;
import core.pages.pim.Imports.ScheduledImports.ManageScheduledImports.SchedulerConfiguration_Page;
import core.pages.pim.Imports.ScheduledImports.ScheduledImportsListing_Page;
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

public class ScheduledImport_Tests extends BaseTest {

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    String jsonPathScheduledImportData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/scheduledImport.json" ;
    String scheduledImportTypeData = "Scheduled Import Data";
    int totalScheduledImportDataRow = 1;
    int totalScheduledImportColumnEntry = 3;

    String jsonPathImportSFTPData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/importSFTP.json" ;
    String sftpImportTypeData = "Import SFTP Data";
    int totalSFTPImportDataRow = 2;
    int totalSFTPImportColumnEntry = 7;

    String jsonPathAdapterData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/adapter.json" ;
    String adapterTypeData = "Adapter Data";
    int totalAdapterDataRow = 1;
    int totalAdapterColumnEntry = 10;

    String emailId1, password1, schedulerName, importTypeName, schedulerDesc, importSFTPName, host, port, username, sftpPassword, path, adapterName;

    {
        try {
            emailId1 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password1 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            schedulerName = JsonReader.getdata(jsonPathScheduledImportData, scheduledImportTypeData, totalScheduledImportDataRow, totalScheduledImportColumnEntry)[0][0].toString();
            schedulerDesc = JsonReader.getdata(jsonPathScheduledImportData, scheduledImportTypeData, totalScheduledImportDataRow, totalScheduledImportColumnEntry)[0][2].toString();
            importTypeName = JsonReader.getdata(jsonPathScheduledImportData, scheduledImportTypeData, totalScheduledImportDataRow, totalScheduledImportColumnEntry)[0][1].toString();
            importSFTPName = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][5].toString();
            adapterName = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][0].toString();
            host = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][0].toString();
            port = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][1].toString();
            username = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][2].toString();
            sftpPassword = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][3].toString();
            path = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][4].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    @Page
    Search search;

    @Page
    PrimaryButton primaryButton;

    @Page
    SecondaryButton secondaryButton;

    @Page
    NotificationMsg notificationMsg;

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    ScheduledImportsListing_Page scheduledImportsListingPage;

    @Page
    SchedulerConfiguration_Page schedulerConfigurationPage;

    @Page
    backToList backToList;

    @Page
    AddImportSFTP_Page addImportSFTPPage;

    @Page
    ManageImports_Page manageImportsPage;

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId1, password1);
        verifyImportSftpCreation();
        manageImportsPage.clickOnScheduledImportTab();
        scheduledImportsListingPage.waitForPageToLoad();
    }

    @AfterMethod
    public void returnToOverview() {
        manageImportsPage.clickOnScheduledImportTab();
        scheduledImportsListingPage.waitForPageToLoad();
    }

    public void verifyImportSftpCreation() throws InterruptedException {
        homePage.clickOnManageTab();
        homePage.clickOnImports();
        manageImportsPage.clickOnImportSftpListTab();
        addImportSFTPPage.waitForPageToLoad();
        addImportSFTPPage.createImportSFTP(importSFTPName, host, port, path, username,sftpPassword);
        search.search(importSFTPName);
        Assert.assertEquals(addImportSFTPPage.fetchSFTPList(), Arrays.asList(importSFTPName));
    }


    @Test(priority = 1)
    public void verifyOpeningScheduledImportPage() {
        scheduledImportsListingPage.clickOnSchedulerTab();
        scheduledImportsListingPage.waitForPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("manageImports/tab=importJobs;"));
    }

    @Test(priority = 2)
    public  void verifySchedulersPageContentWhenThereIsNoScheduler() {
        scheduledImportsListingPage.clickOnSchedulerTab();
        scheduledImportsListingPage.waitForPageToLoad();
        Assert.assertEquals(scheduledImportsListingPage.getPageContentOfBlankSchedulerListingPage(), "Schedule your imports to automate the processing of the import files.\n" +
                "Perform your first import, and schedule it hence after.");
    }

    @Test(priority = 3)
    public void createScheduler() {
        scheduledImportsListingPage.createScheduler(schedulerName, importTypeName,schedulerDesc );
        backToList.clickOnBackToListIcon();
        Assert.assertEquals(scheduledImportsListingPage.fetchSchedulers(), Arrays.asList(schedulerName +" "+ schedulerDesc+" Products" +" Stopped"+ " No jobs scheduled"));
    }

    @Test(priority = 4)
    public void searchProperty() throws InterruptedException {
        scheduledImportsListingPage.searchScheduler(schedulerName);
        Assert.assertEquals(scheduledImportsListingPage.fetchSchedulers(), Arrays.asList(schedulerName +" "+ schedulerDesc+" Products" +" Stopped"+ " No jobs scheduled"));
    }

    @Test(priority = 5)
    public void configureScheduler() throws InterruptedException {
        scheduledImportsListingPage.openScheduler(schedulerName);
        schedulerConfigurationPage.waitForPageToLoad();
        schedulerConfigurationPage.selectSFTP(importSFTPName);
        schedulerConfigurationPage.selectAdapter(adapterName);
        FunctionLibrary.scrollToBottom();
        schedulerConfigurationPage.selectSFTPFiles(true, true, true, true, "suite");
        schedulerConfigurationPage.createScheduler("Minutes", "15", "After specific occurences", "2");
        FunctionLibrary.scrollToTop();
        primaryButton.clickOnPrimaryButton();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), "Scheduled import settings successfully updated");
    }

    @Test(priority = 6)
    public void startScheduler() throws InterruptedException {
        scheduledImportsListingPage.openScheduler(schedulerName);
        schedulerConfigurationPage.waitForPageToLoad();
        secondaryButton.clickOnSecondaryButton();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), "Scheduled Import is running now!");
    }

    @Test(priority = 7)
    public void verifySchedulerStatus() throws InterruptedException {
        scheduledImportsListingPage.searchScheduler(schedulerName);
        Assert.assertTrue(scheduledImportsListingPage.fetchSchedulers().get(0).contains("Ongoing"));
    }

}
