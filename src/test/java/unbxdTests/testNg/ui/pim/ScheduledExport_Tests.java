package unbxdTests.testNg.ui.pim;

import core.components.pim.NotificationMsg;
import core.components.pim.PrimaryButton;
import core.components.pim.ProductsTable;
import core.components.pim.backToList;
import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.ManageNetwork.Exports.ScheduledExports.ManageScheduledExports.ManageScheduledExports_Page;
import core.pages.pim.ManageNetwork.Exports.ScheduledExports.ManageScheduledExports.NameAndDescription_Page;
import core.pages.pim.ManageNetwork.Exports.ScheduledExports.ManageScheduledExports.Products_Page;
import core.pages.pim.ManageNetwork.Exports.ScheduledExports.ManageScheduledExports.Schedule_Page;
import core.pages.pim.ManageNetwork.Exports.ScheduledExports.ScheduledExportsListing_Page;
import core.pages.pim.ManageNetwork.ManageNetwork_Page;
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

public class ScheduledExport_Tests extends BaseTest {

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    String jsonPathScheduledExportData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/scheduledExport.json" ;
    String scheduledExportTypeData = "Scheduled Export Data";
    int totalScheduledExportDataRow = 1;
    int totalScheduledExportColumnEntry = 4;

    String jsonPathLabelData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/label.json" ;
    String labelTypeData = "Label Data";
    int totalLabelDataRow = 1;
    int totalLabelColumnEntry = 2;

    String jsonPathPartnerData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/partner.json" ;
    String partnerTypeData = "Partner Data";
    int totalPartnerDataRow = 2;
    int totalPartnerColumnEntry = 3;

    String jsonPathAdapterData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/adapter.json" ;
    String adapterTypeData = "Adapter Data";
    int totalAdapterDataRow = 1;
    int totalAdapterColumnEntry = 10;

    String emailId1, password1, schedulerName, description, parentProductSelected, adapterName,labelName,updatedPartnerName;

    {
        try {
            emailId1 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password1 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            schedulerName = JsonReader.getdata(jsonPathScheduledExportData, scheduledExportTypeData, totalScheduledExportDataRow, totalScheduledExportColumnEntry)[0][0].toString();
            labelName = JsonReader.getdata(jsonPathLabelData, labelTypeData, totalLabelDataRow, totalLabelColumnEntry )[0][0].toString();
            parentProductSelected = JsonReader.getdata(jsonPathScheduledExportData, scheduledExportTypeData, totalScheduledExportDataRow, totalScheduledExportColumnEntry)[0][2].toString();
            description = JsonReader.getdata(jsonPathScheduledExportData, scheduledExportTypeData, totalScheduledExportDataRow, totalScheduledExportColumnEntry)[0][3].toString();
            updatedPartnerName = JsonReader.getdata(jsonPathPartnerData, partnerTypeData, totalPartnerDataRow, totalPartnerColumnEntry)[0][2].toString();
            adapterName = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][0].toString();


        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }
    }

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    NameAndDescription_Page nameAndDescriptionPage;

    @Page
    ManageScheduledExports_Page manageScheduledExportsPage;

    @Page
    Products_Page productsPage;

    @Page
    Schedule_Page schedulePage;

    @Page
    ScheduledExportsListing_Page scheduledExportsListingPage;

    @Page
    ManageNetwork_Page manageNetworkPage;

    @Page
    backToList backToList;

    @Page
    PrimaryButton primaryButton;

    @Page
    NotificationMsg notificationMsg;

    @Page
    ProductsTable productsTable;

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId1, password1);
        homePage.clickOnNetworkTab();
        manageNetworkPage.clickOnExportsTab();
        scheduledExportsListingPage.clickOnSchedulerTab();
        scheduledExportsListingPage.waitForPageToLoad();
    }

    @AfterMethod
    public void returnToOverview() {
        scheduledExportsListingPage.clickOnSchedulerTab();
        scheduledExportsListingPage.waitForPageToLoad();
    }

    @Test(priority = 1)
    public void openPropertyPageFromManageTab() throws InterruptedException {
        scheduledExportsListingPage.clickOnSchedulerTab();
        scheduledExportsListingPage.waitForPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("tab=exports;subtab=schedulers;"));
    }

    @Test(priority = 2)
    public  void verifySchedulersPageContentWhenThereIsNoScheduler() throws InterruptedException {
        scheduledExportsListingPage.clickOnSchedulerTab();
        scheduledExportsListingPage.waitForPageToLoad();
        Assert.assertEquals(scheduledExportsListingPage.getPageContentOfBlankSchedulerListingPage(), "Schedule your exports to automatically send certified products to your partners\nJust set the SFTP location, email, and the export frequency");
    }

    @Test(priority = 3)
    public void verifyCreateScheduler() {
        scheduledExportsListingPage.createScheduler(schedulerName, updatedPartnerName);
        backToList.clickOnBackToListIcon();
        Assert.assertEquals(scheduledExportsListingPage.fetchSchedulers(), Arrays.asList(schedulerName +" Not Scheduled "+ updatedPartnerName+" - -"));
    }

    @Test(priority = 5)
    public void verifySearchProperty() throws InterruptedException {
        scheduledExportsListingPage.searchScheduler(schedulerName);
        Assert.assertEquals(scheduledExportsListingPage.fetchSchedulers(), Arrays.asList(schedulerName +" Not Scheduled "+ updatedPartnerName+" - -"));
    }

    @Test(priority = 6)
    public void verifyAddingLabels() throws InterruptedException {
        scheduledExportsListingPage.openScheduler(schedulerName);
        primaryButton.clickOnPrimaryButton();
        productsPage.selectLabel(labelName);
        productsPage.clickOnViewAgainstLabelSection();
        Assert.assertEquals(productsPage.fetchAllTheProducts().size(), 4);
        productsPage.clickOnRemoveIcon();
    }

    @Test(priority = 7)
    public void verifyAddingIndividualProducts() throws InterruptedException {
        scheduledExportsListingPage.openScheduler(schedulerName);
        productsPage.clickOnSelectIndividualProducts();
        productsTable.clickOnToggleGroupByParentSolo();
        productsPage.selectProduct(parentProductSelected);
        productsPage.clickOnViewAgainstLabelSection();
        Assert.assertEquals(productsPage.fetchAllTheProducts().size(), 1);;
    }

    @Test(priority = 8)
    public void verifyConfiguringScheduler() throws InterruptedException {
        scheduledExportsListingPage.openScheduler(schedulerName);
        manageScheduledExportsPage.clickOnSchedule();
        schedulePage.setScheduler(adapterName, true, true, true, true, true, false, "abhishek.sahu@unbxd.com", "");
        schedulePage.setScheduleFrequency("15", "Minutes", "After specific occurences", "2");
        primaryButton.clickOnPrimaryButton();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), "Changes updated successfully");
    }

    @Test(priority = 9)
    public void verifyStartingScheduler() throws InterruptedException {
        scheduledExportsListingPage.openScheduler(schedulerName);
        manageScheduledExportsPage.clickOnSchedulerStartBtn();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), "Changes updated successfully");
    }
}
