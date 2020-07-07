package core.pages.pim.WorkFlow;

import core.components.pim.*;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class WorkflowListing_Page extends FluentPage {

    @Page
    PrimaryButton primaryButton;

    @Page
    SecondaryButton secondaryButton;

    @Page
    Pagination pagination;

    @Page
    Search search;

    @Page
    ActionsDDModal actionsDDModal;

    @Page
    SelectProducts selectProducts;

    @Page
    SelectProperties selectProperties;

    @FindBy(how = How.CSS, using = ".qa-workflow-list .secondary-btn")
    FluentWebElement createWfButton;

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement wfNameInputBox;

    @FindBy(how = How.NAME, using = "description")
    FluentWebElement wfDescInputBox;

    @FindBy(how = How.CSS, using = ".qa-workflow-list .table tbody tr")
    FluentList<FluentWebElement> wfDetailsList;

    @FindBy(how = How.CSS, using = ".qa-workflow-list .table tbody tr td:nth-child(2)")
    FluentList<FluentWebElement> wfNameList;

    @FindBy(how = How.CSS, using = ".table tbody tr td:nth-child(2)")
    FluentList<FluentWebElement> wfTypeList;

    @FindBy(how = How.CSS, using = ".table tbody tr td:nth-child(3)")
    FluentList<FluentWebElement> wfGroupList;

    @FindBy(how = How.CSS, using = ".qa-workflow-list .table tbody tr td:nth-child(6) .glyphicon-eye-open")
    FluentList<FluentWebElement> wfEditIcon;

    @FindBy(how = How.CSS, using = ".no-data h2")
    FluentWebElement noResultFound;

    @FindBy(how = How.CSS, using = ".first-time-import")
    FluentWebElement blankPropertiesPage;

    @FindBy(how = How.CSS, using = ".first-time-import")
    FluentWebElement wfListingHeaderClass;

    @FindBy(how = How.CSS, using = ".wf-summary-item:nth-child(1) .wf-summary-count")
    FluentWebElement activeWfCount;

    @FindBy(how = How.CSS, using = ".wf-summary-item:nth-child(2) .wf-summary-count")
    FluentWebElement pausedWfCount;

    @FindBy(how = How.CSS, using = ".wf-summary-item:nth-child(3) .wf-summary-count")
    FluentWebElement completedWfCount;

    @FindBy(how = How.CSS, using = ".wf-summary-item:nth-child(4) .wf-summary-count")
    FluentWebElement errorsWfCount;

    String createWfButtonName = "Create WorkFlow Button";
    String wfNameInputBoxName = "WorkFlow Name InputBox";
    String wfDescInputBoxName = "Workflow Description Name InputBox";
    String wfDataTypeListName = "Property DataType List";
    String proceedButtonName = "Proceed Button";
    String searchWfInputBoxName = "Search WorkFLow InputBox";
    String wfNameListName = "WorkFLow Name List";
    String wfDetailsListName = "WorkFLow Details List";
    String wfEditIconName = "WorkFLow Edit Icon";
    String noResultFoundName = "No Result Found";
    String activeWfCountName = "Active Workflow Count";
    String pausedWfCountName = "Paused Workflow Count";
    String completedWfCountName = "Completed Workflow Count";
    String errorsWfCountName = "Errors Workflow Count";

    public void createWorkFlow(String wfName, String wfDesc) {
        secondaryButton.clickOnSecondaryButton();
        FunctionLibrary.input(wfNameInputBox, wfNameInputBoxName, wfName);
        FunctionLibrary.input(wfDescInputBox, wfDescInputBoxName, wfDesc);
        primaryButton.clickOnPrimaryButton();
    }

    public Boolean isCreateWorkflowButtonPresent() {
        return secondaryButton.isSecondaryButtonPresent();
    }

    public void searchPropertyName(String wfName) throws InterruptedException {
        search.search(wfName);
    }

    public List<String> getWorkFlowNames(){
        return FunctionLibrary.retrieveTexts(wfNameList, wfNameListName);
    }

    public List<String> getWorkFlowDetails(){
        return FunctionLibrary.retrieveTexts(wfDetailsList, wfDetailsListName);
    }

    public void clickOnEditWorkFlow(String workflowName) throws InterruptedException {
        search.search(workflowName);
        FunctionLibrary.click(wfEditIcon.get(0), wfEditIconName);
    }

    public String getPageContentForPropertyNotFound() {
        return FunctionLibrary.retrieveText(noResultFound, noResultFoundName);
    }

    public void waitForWorkFlowListingPageToLoad() throws InterruptedException {
        FunctionLibrary.waitForElementToLoad(createWfButton, createWfButtonName);
        FunctionLibrary.waitForPageToLoad();

    }

    public String getActiveWfCount() {
        return FunctionLibrary.retrieveText(activeWfCount, activeWfCountName);
    }

    public String getPausedWfCount() {
        return FunctionLibrary.retrieveText(pausedWfCount, pausedWfCountName);
    }

    public String getCompletedWfCount() {
        return FunctionLibrary.retrieveText(completedWfCount, completedWfCountName);
    }

    public String getErrorWfCount() {
        return FunctionLibrary.retrieveText(errorsWfCount, errorsWfCountName);
    }


}
