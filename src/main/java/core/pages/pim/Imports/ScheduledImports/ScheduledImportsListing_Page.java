package core.pages.pim.Imports.ScheduledImports;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ScheduledImportsListing_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar li:nth-child(2) a")
    FluentWebElement scheduledImportsTab;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchSchedulerInputBox;

    @FindBy(how = How.CSS, using = ".qa-impJobs-list .secondary-btn")
    FluentWebElement createSchedulerButton;

    @FindBy(how = How.NAME, using = "importJobName")
    FluentWebElement schedulerNameInputBox;

    @FindBy(how = How.NAME, using = "importJobDesc")
    FluentWebElement schedulerDescInputBox;

    @FindBy(how = How.CSS, using = "form .drop-down-button .caret")
    FluentWebElement importDropDownIcon;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> partnerList;

    @FindBy(how = How.CSS, using = ".btn.primary-btn")
    FluentWebElement proceedButton;

    @FindBy(how = How.CSS, using = ".qa-impJobs-list .glyphicon-eye-open")
    FluentWebElement schedulerEditIcon;

    @FindBy(how = How.CSS, using = ".qa-impJobs-list .table tbody tr")
    FluentList<FluentWebElement> schedulerDetailsList;

    @FindBy(how = How.CSS, using = ".qa-impJobs-list .table tbody tr td:nth-child(1)")
    FluentList<FluentWebElement> schedulerNameList;

    @FindBy(how = How.CSS, using = ".first-time-import")
    FluentWebElement blankSchedulerPage;



    String scheduledImportsTabName = "Scheduled Imports Tab";
    String searchSchedulerInputBoxName = "Search scheduler InputBox";
    String createSchedulerButtonName = "create scheduler Button";
    String schedulerNameInputBoxName = "scheduler Name InputBox";
    String schedulerDescInputBoxName = "Scheduler Desc Input Box";
    String importDropDownIconName = "Import DropDown Icon Name";
    String partnerListName = "partnerListName";
    String proceedButtonName = "Proceed Button";
    String schedulerEditIconName = "scheduler Edit Icon";
    String schedulerDetailsListName = "scheduler Details List";
    String schedulerNameListName = "=scheduler Name List";
    String blankSchedulerPageName = "Blank Scheduler Page";



    public void clickOnSchedulerTab() {
        FunctionLibrary.click(scheduledImportsTab, scheduledImportsTabName);
    }

    public void createScheduler(String schedulerName, String inputName, String schedulerDesc) {
        FunctionLibrary.scrollToTop();
        FunctionLibrary.click(createSchedulerButton, createSchedulerButtonName);
        FunctionLibrary.input(schedulerNameInputBox, schedulerNameInputBoxName, schedulerName);
        FunctionLibrary.click(importDropDownIcon, importDropDownIconName);
        FunctionLibrary.selectDropDownValueByName(inputName, partnerList, partnerListName);
        FunctionLibrary.input(schedulerDescInputBox, schedulerDescInputBoxName, schedulerDesc);
        FunctionLibrary.click(proceedButton, proceedButtonName);
    }

    public void searchScheduler(String schedulerName) throws InterruptedException {
        FunctionLibrary.input(searchSchedulerInputBox, searchSchedulerInputBoxName, schedulerName);
        Thread.sleep(1000);
    }

    public List<String> fetchSchedulers() {
        return FunctionLibrary.retrieveTexts(schedulerDetailsList, schedulerDetailsListName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToLoad(createSchedulerButton, createSchedulerButtonName);
    }

    public void openScheduler(String schedulerName) throws InterruptedException {
        searchScheduler(schedulerName);
        FunctionLibrary.click(schedulerEditIcon, schedulerEditIconName);
    }

    public String getPageContentOfBlankSchedulerListingPage() {
        return FunctionLibrary.retrieveText(blankSchedulerPage, blankSchedulerPageName);
    }

}
