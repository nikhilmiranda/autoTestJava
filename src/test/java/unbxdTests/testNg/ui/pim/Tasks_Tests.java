package unbxdTests.testNg.ui.pim;

import core.pages.pim.*;
import core.pages.pim.Tasks.TaskTemplate.ManageTaskTemplate_Page;
import core.pages.pim.Tasks.Tasks_Page.ManageTask_Page;
import core.pages.pim.Tasks.Tasks_Page.TaskListing_Page;
import core.pages.pim.Tasks.TaskTemplate.TaskTemplateListing_Page;
import lib.FunctionLibrary;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Tasks_Tests extends BaseTest {

    String jsonPathPropertyData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/property.json" ;
    String propertyTypeData = "Property Data";
    int totalPropertyDataRow = 2;
    int totalPropertyColumnEntry = 6;

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    String jsonPathTaskData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/task.json" ;
    String taskTypeData = "Task Data";
    int totalTaskDataRow = 2;
    int totalTaskColumnEntry = 13;

    String emailId, password, emailId2, memberName2, propertyName1, propertyDataType1, taskTemplateNameUpdated, taskName, taskName2, propertyName2, propertyDataType2, taskTemplateName, taskNameUpdated, propertyImportedString;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            emailId2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][0].toString();
            memberName2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][5].toString();
            propertyName1 = JsonReader.getdata(jsonPathTaskData, taskTypeData, totalTaskDataRow, totalTaskColumnEntry)[0][5].toString();
            propertyDataType1 = JsonReader.getdata(jsonPathTaskData, taskTypeData, totalTaskDataRow, totalTaskColumnEntry)[0][4].toString();
            propertyName2 = JsonReader.getdata(jsonPathTaskData, taskTypeData, totalTaskDataRow, totalTaskColumnEntry)[0][7].toString();
            propertyDataType2 = JsonReader.getdata(jsonPathTaskData, taskTypeData, totalTaskDataRow, totalTaskColumnEntry)[0][6].toString();
            taskTemplateName = JsonReader.getdata(jsonPathTaskData, taskTypeData, totalTaskDataRow, totalTaskColumnEntry)[0][0].toString();
            taskTemplateNameUpdated = JsonReader.getdata(jsonPathTaskData, taskTypeData, totalTaskDataRow, totalTaskColumnEntry)[0][1].toString();
            taskName = JsonReader.getdata(jsonPathTaskData, taskTypeData, totalTaskDataRow, totalTaskColumnEntry)[0][2].toString();
            taskNameUpdated = JsonReader.getdata(jsonPathTaskData, taskTypeData, totalTaskDataRow, totalTaskColumnEntry)[0][3].toString();
            taskName2 = JsonReader.getdata(jsonPathTaskData, taskTypeData, totalTaskDataRow, totalTaskColumnEntry)[0][9].toString();
            propertyImportedString = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry)[0][4].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    String[] propertyImportedArray = {"Product Id","Image","Product Name","Price","Product Details","Color","Gender","Size","Pattern","Sleeve","Fit","Fabric","Fabric Care","Suitable For","Neck Type","Payment Mode","Return Policy","Category","More Details","Date Of Manufaturing","Parent Id"};

    String taskTemplateCreationErrorMsg = "TaskTemplate Creation failed due to duplicate name";
    String taskCreationUsingTemplateErrorMsg  = "Creating task Failed due to duplicate unique name";
    String taskCreationErrorMsg  = "Creating task Failed due to duplicate unique name";

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    ManageTask_Page manageTaskPage;

    @Page
    TaskListing_Page taskListingPage;

    @Page
    TaskTemplateListing_Page taskTemplateListingPage;

    @Page
    ManageTaskTemplate_Page manageTaskTemplatePage;

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);

    }

    @AfterMethod
    public void returnToOverview() {
        homePage.clickOnOverviewTab();
    }

    @Test(priority = 1)
    public void openTaskPageFromManageTab() {
        homePage.clickOnManageTab();
        homePage.clickOnTasks();
        Assert.assertTrue(getDriver().getCurrentUrl().contains("/manageTasks/"));
    }

   @Test(priority = 2)
    public void verifyTaskTemplateCreation() throws InterruptedException {
       openTaskPageFromManageTab();
       taskListingPage.clickOnTaskTemplateTab();
       taskTemplateListingPage.clickOnCreateTaskTemplate();
       System.out.println(FunctionLibrary.getPageURL());
       Assert.assertTrue(FunctionLibrary.getPageURL().contains("tab=taskTemplates") && FunctionLibrary.getPageURL().contains("action=create"));
       manageTaskTemplatePage.waitForPageToLoad();
       manageTaskTemplatePage.selectPaginationWith100Values();System.out.println(Arrays.asList(propertyImportedArray));
       Assert.assertTrue(Arrays.asList(propertyImportedArray).containsAll(manageTaskTemplatePage.getThePropertiesAvailableInTaskListingPage()));
       manageTaskTemplatePage.addPropertyToTask(propertyName1);
       Assert.assertEquals(manageTaskTemplatePage.getTheCountOfPropertiesAddedTotemplate(), "1 Properties selected");
       manageTaskTemplatePage.clickOnNext();
       manageTaskTemplatePage.enterTemplateName(taskTemplateName);
       manageTaskTemplatePage.assignTaskToMember(memberName2);
       FunctionLibrary.scrollToTop();
       manageTaskTemplatePage.createTemplate();
       Assert.assertEquals(taskTemplateListingPage.retrieveTaskCreationSuccessMsg(), "Template created successfully");



   }
   @Test(priority = 3)
    public void verifyTaskCreationUsingTaskTemplate() throws InterruptedException {
       openTaskPageFromManageTab();
       taskListingPage.clickOnAssignTaskUsingATaskTemplate();
       manageTaskPage.waitForManageTaskUsingTemplatePageToLoad();
       manageTaskPage.selectTaskTemplate(taskTemplateName);
       Assert.assertEquals(manageTaskPage.getTheAssigneesLinkedWithTaskTemplate(), memberName2);
       Assert.assertEquals(manageTaskPage.getTheCountOfPropertiesAddedTotemplate(), Arrays.asList(propertyName1+" "+propertyDataType1+" Unassigned"));
       manageTaskPage.clickOnNext();
       manageTaskPage.addProductToTask("snooky");
       manageTaskPage.clickOnNext();
       manageTaskPage.enterTaskName(taskName);
       manageTaskPage.clickOnSaveAndAssignTask();
       Assert.assertEquals(taskListingPage.retrieveTaskCreationSuccessMsg(), "Task created successfully");



   }

    @Test(priority = 4)
    public void verifyTaskCreationFromScratch() throws InterruptedException {
        openTaskPageFromManageTab();
        taskListingPage.clickOnAssignTaskFromScratch();
        manageTaskPage.addProductToTask("zaux suede");
        manageTaskPage.clickOnNext();
        manageTaskPage.addPropertyToTask(propertyName1);
        manageTaskPage.clickOnNext();
        manageTaskPage.enterTaskName(taskName2);
        manageTaskTemplatePage.assignTaskToMember(memberName2);
        FunctionLibrary.scrollToTop();
        manageTaskPage.clickOnSaveAndAssignTask();
        Assert.assertEquals(taskListingPage.retrieveTaskCreationSuccessMsg(), "Task created successfully");

    }

    @Test(priority = 5)
    public void verifyTaskTemplateEdit() throws InterruptedException {
        openTaskPageFromManageTab();
        taskListingPage.clickOnTaskTemplateTab();
        taskListingPage.searchTaskTemplate(taskTemplateName);
        taskListingPage.clickOnEditIcon();
        manageTaskTemplatePage.waitForPageToLoad();
        manageTaskTemplatePage.clickOnAddProperties();
        manageTaskTemplatePage.waitForPageToLoad();
        manageTaskTemplatePage.addAdditionalProperties(propertyName2);
        Assert.assertEquals(manageTaskTemplatePage.getTheCountOfAdditionalPropertiesAddedToTemplate(), "1 Properties selected");
        manageTaskTemplatePage.clickOnAddSelectedProperties();
        Assert.assertEquals(manageTaskTemplatePage.getThePropertiesAvailableInCreatedTaskListingPage(), Arrays.asList(propertyName2, propertyName1));
        manageTaskTemplatePage.clickOnNext();
        manageTaskTemplatePage.enterTemplateName(taskTemplateNameUpdated);
        manageTaskTemplatePage.createTemplate();
        Assert.assertEquals(taskTemplateListingPage.retrieveTaskCreationSuccessMsg(), "Template updated successfully");
        homePage.clickOnUserMenuIcon();
        homePage.clickOnLogout();
    }

//    @Test
//    public void verifyIfTheAssigneeIsAbleToViewTask() throws InterruptedException {
//        loginPage.login(emailId2, password);
//        openTaskPageFromManageTab();
//        Assert.assertEquals(taskListingPage.getTheTasksListed(), Arrays.asList(taskName));
//    }
//
//    public void verifyIfTheAssigneeIsAbleToViewTask() throws InterruptedException {
//        loginPage.login(emailId2, password);
//        openTaskPageFromManageTab();
//        Assert.assertEquals(taskListingPage.getTheTasksListed(), Arrays.asList(taskName));
//    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        FunctionLibrary.closeDriver();
    }



}
