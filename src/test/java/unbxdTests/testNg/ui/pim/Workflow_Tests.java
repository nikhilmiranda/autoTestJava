package unbxdTests.testNg.ui.pim;

import core.components.pim.*;
import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.ManageNetwork.Product.ProductLabels.ExportProduct_Page;
import core.pages.pim.ProductsListing.CertifyProduct_Page;
import core.pages.pim.WorkFlow.Nodes.BulkEditNode_Page;
import core.pages.pim.WorkFlow.Nodes.FilterNode_Page;
import core.pages.pim.WorkFlow.Nodes.ImportNode_Page;
import core.pages.pim.WorkFlow.WorkFlowManage_Page;
import core.pages.pim.WorkFlow.WorkflowListing_Page;
import lib.FunctionLibrary;
import lib.JsonReader;
import lib.constants.Constants;
import lib.constants.Notification;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;
import java.io.FileNotFoundException;
import java.util.Arrays;

public class Workflow_Tests extends BaseTest {

    String JSON_path = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json";
    String typeData = "Login Data";
    int totalDataRow = 3;
    int totalColumnEntry = 7;

    String jsonPathWorkflowData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/workflow.json" ;
    String workflowTypeData = "Workflow Data";
    int totalWorkflowDataRow = 1;
    int totalWorkflowColumnEntry = 2;

    String jsonPathImportSFTPData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/importSFTP.json" ;
    String sftpImportTypeData = "Import SFTP Data";
    int totalSFTPImportDataRow = 2;
    int totalSFTPImportColumnEntry = 7;

    String jsonPathAdapterData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/adapter.json" ;
    String adapterTypeData = "Adapter Data";
    int totalAdapterDataRow = 1;
    int totalAdapterColumnEntry = 10;

    String jsonPathLabelData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/label.json" ;
    String labelTypeData = "Label Data";
    int totalLabelDataRow = 1;
    int totalLabelColumnEntry = 2;

    String jsonPathPartnerData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/partner.json" ;
    String partnerTypeData = "Partner Data";
    int totalPartnerDataRow = 2;
    int totalPartnerColumnEntry = 3;

    String jsonPathSFTPData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/partnerSFTP.json" ;
    String sftpTypeData = "Partner SFTP Data";
    int totalSFTPDataRow = 2;
    int totalSFTPColumnEntry = 6;

    String emailId, password, orgName, workflowName, workflowDescription, importSFTPName,host, port, username, sftpPassword, path, adapterName, labelName, updatedPartnerName, sftpName;

    {
        try {
            emailId = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][0].toString();
            password = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][1].toString();
            orgName = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][2].toString();
            workflowName = JsonReader.getdata(jsonPathWorkflowData, workflowTypeData, totalWorkflowDataRow, totalWorkflowColumnEntry)[0][0].toString();
            workflowDescription = JsonReader.getdata(jsonPathWorkflowData, workflowTypeData, totalWorkflowDataRow, totalWorkflowColumnEntry)[0][1].toString();
            importSFTPName = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][5].toString();
            adapterName = JsonReader.getdata(jsonPathAdapterData, adapterTypeData, totalAdapterDataRow, totalAdapterColumnEntry )[0][0].toString();
            host = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][0].toString();
            port = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][1].toString();
            username = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][2].toString();
            sftpPassword = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][3].toString();
            path = JsonReader.getdata(jsonPathImportSFTPData, sftpImportTypeData, totalSFTPImportDataRow, totalSFTPImportColumnEntry)[0][4].toString();
            labelName = JsonReader.getdata(jsonPathLabelData, labelTypeData, totalLabelDataRow, totalLabelColumnEntry )[0][1].toString();
            updatedPartnerName = JsonReader.getdata(jsonPathPartnerData, partnerTypeData, totalPartnerDataRow, totalPartnerColumnEntry)[0][2].toString();
            sftpName = JsonReader.getdata(jsonPathSFTPData, sftpTypeData, totalSFTPDataRow, totalSFTPColumnEntry)[0][5].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Search search;

    @Page
    backToList backToList;

    @Page
    ProductsTable productsTable;

    @Page
    PrimaryButton primaryButton;

    @Page
    SingleSelectModal singleSelectModal;

    @Page
    Login_Page loginPage;

    @Page
    WorkFlowManage_Page workFlowManagePage;

    @Page
    Home_Page homePage;

    @Page
    WorkflowListing_Page workflowListingPage;

    @Page
    ImportNode_Page importNodePage;

    @Page
    FilterNode_Page filterNodePage;

    @Page
    BulkEditNode_Page bulkEditNodePage;

    @Page
    CertifyProduct_Page certifyProductPage;

    @Page
    NotificationMsg notificationMsg;

    @Page
    ExportProduct_Page exportProductPage;

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
    }

    @AfterMethod
    public void returnToWorkFLowListing() throws InterruptedException {
        homePage.clickOnUserMenuIcon();
        homePage.clickOnWorkflow();
        workflowListingPage.waitForWorkFlowListingPageToLoad();
    }

    @Test(priority = 1)
    public void verifyWorkflowPage() throws InterruptedException {
        homePage.clickOnUserMenuIcon();
        homePage.clickOnWorkflow();
        workflowListingPage.waitForWorkFlowListingPageToLoad();
        Assert.assertTrue(driver.getCurrentUrl().contains("#/workflow/"));
    }

    @Test(priority = 2)
    public void verifyWorkflowCreation() {
        workflowListingPage.createWorkFlow(workflowName, workflowDescription);
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        Assert.assertTrue(driver.getCurrentUrl().contains(";workflowId"));
    }

    @Test(priority = 3)
    public void verifyWorkflowInTheListingPage() throws InterruptedException {
        search.search(workflowName);
        Assert.assertEquals(productsTable.fetchAllTheRowItemsDetail(), Arrays.asList(workflowName +
                "\nNot Set\n" +
                "Draft\n" +
                "-"));
    }

    @Test(priority = 4)
    public void verifyAddingImportNode() throws InterruptedException {
        search.search(workflowName);
        productsTable.clickOnEditIcon();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        workFlowManagePage.clickOnNewDagPlusIcon();
        workFlowManagePage.selectAndAddNode("Import");
        FunctionLibrary.ThreadWait();
        importNodePage.selectSFTPFiles("SFTP",importSFTPName, adapterName, true, true, true, false, "Final");
        importNodePage.clickOnAddNode();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        primaryButton.clickOnPrimaryButton();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), Notification.WF_UPDATE.getDesc());
        backToList.clickOnBackToListIcon();
        Assert.assertEquals(productsTable.fetchAllTheRowItemsDetail(), Arrays.asList(workflowName +
                "\nNot Set\n" +
                "Draft\n" +
                "-"));
    }

    @Test(priority = 5)
    public void verifyAddingFiltersPIMNode() throws InterruptedException {
        search.search(workflowName);
        productsTable.clickOnEditIcon();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        workFlowManagePage.clickOnDagPlusIcon();
        workFlowManagePage.selectAndAddNode("Filter PIM Products");
        FunctionLibrary.ThreadWait();
        filterNodePage.configureFilterNode();
        primaryButton.clickOnPrimaryModalButton();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        FunctionLibrary.ThreadWait();
        FunctionLibrary.scrollToTop();
        primaryButton.clickOnPrimaryButton();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), Notification.WF_UPDATE.getDesc());
    }

    @Test(priority = 6)
    public void verifyAddingBulkEditNode() throws InterruptedException {
        search.search(workflowName);
        productsTable.clickOnEditIcon();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        workFlowManagePage.clickOnDagPlusIcon();
        workFlowManagePage.selectAndAddNode("Bulk Edit");
        FunctionLibrary.ThreadWait();
        bulkEditNodePage.configureBulkEditNode("Price", "122.0");
        primaryButton.clickOnPrimaryModalButton();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        FunctionLibrary.ThreadWait();
        FunctionLibrary.scrollToTop();
        primaryButton.clickOnPrimaryButton();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), Notification.WF_UPDATE.getDesc());
    }

    @Test(priority = 7)
    public void verifyCertifyToNetworkNode() throws InterruptedException {
        search.search(workflowName);
        productsTable.clickOnEditIcon();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        workFlowManagePage.clickOnDagPlusIcon();
        workFlowManagePage.selectAndAddNode("Certify to Network");
        FunctionLibrary.ThreadWait();
        certifyProductPage.clickOnLabelSelectionDropDownIcon();
        certifyProductPage.createANewLabel(labelName);
        primaryButton.clickOnPrimaryModalButton();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        FunctionLibrary.ThreadWait();
        FunctionLibrary.scrollToTop();
        primaryButton.clickOnPrimaryButton();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), Notification.WF_UPDATE.getDesc());
    }

    @Test(priority = 8)
    public void verifyAddingFiltersNetworkProductNode() throws InterruptedException {
        search.search(workflowName);
        productsTable.clickOnEditIcon();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        workFlowManagePage.clickOnDagPlusIcon();
        workFlowManagePage.selectAndAddNode("Filter Network Products");
        FunctionLibrary.ThreadWait();
        filterNodePage.configureFilterNode();
        primaryButton.clickOnPrimaryModalButton();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        FunctionLibrary.ThreadWait();
        FunctionLibrary.scrollToTop();
        primaryButton.clickOnPrimaryButton();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), Notification.WF_UPDATE.getDesc());
    }

    @Test(priority = 9)
    public void verifyConfigureExportNode() throws InterruptedException {
        search.search(workflowName);
        productsTable.clickOnEditIcon();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        workFlowManagePage.clickOnDagPlusIcon();
        workFlowManagePage.selectAndAddNode("Export");
        FunctionLibrary.ThreadWait();
        exportProductPage.configureExportNode(updatedPartnerName, adapterName, true, true, true,true, true, false, "abhishek.sahu@unbxd.com", sftpName);
        primaryButton.clickOnPrimaryModalButton();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        FunctionLibrary.ThreadWait();
        FunctionLibrary.scrollToTop();
        primaryButton.clickOnPrimaryButton();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), Notification.WF_UPDATE.getDesc());
    }

    @Test(priority = 10)
    public void verifySchedulingWorkFlow() throws InterruptedException {
        search.search(workflowName);
        productsTable.clickOnEditIcon();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        workFlowManagePage.clickOnRunTheWFNow();
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), Notification.WF_STARTED.getDesc());
    }

    @Test(priority = 11)
    public void verifyWFStatus() throws InterruptedException {
        search.search(workflowName);
        productsTable.clickOnEditIcon();
        workFlowManagePage.waitForManageWorkFLowPageToLoad();
        workFlowManagePage.clickOnLogsIcon();
        Thread.sleep(120000);
        FunctionLibrary.refreshPage();
        Thread.sleep(4000);
        singleSelectModal.clickOnDropDownIcon(0);
        singleSelectModal.selectStaticDropDownValue("Running");
        Assert.assertEquals(workFlowManagePage.blackScreenOfActivityLog(), Constants.NO_RESULTS_FOUND.getDesc());
        singleSelectModal.clickOnDropDownIcon(0);
        singleSelectModal.selectStaticDropDownValue("Failed");
        Assert.assertFalse(productsTable.fetchAllTheRowItemsDetail().toString().contains("Some error occurred"));
        Assert.assertEquals(workFlowManagePage.blackScreenOfActivityLog(), Constants.NO_RESULTS_FOUND.getDesc());
        singleSelectModal.clickOnDropDownIcon(0);
        singleSelectModal.selectStaticDropDownValue("Completed");
        Assert.assertFalse(productsTable.fetchAllTheRowItemsDetail().toString().equalsIgnoreCase("Success"));
    }
}
