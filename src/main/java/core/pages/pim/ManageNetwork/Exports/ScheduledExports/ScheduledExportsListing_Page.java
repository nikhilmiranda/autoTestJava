package core.pages.pim.ManageNetwork.Exports.ScheduledExports;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ScheduledExportsListing_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(5) a")
    FluentWebElement exportsTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(2) a")
    FluentWebElement scheduledExportsTab;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchSchedulerInputBox;

    @FindBy(how = How.CSS, using = ".qa-nwschedulers-list .secondary-btn")
    FluentWebElement createSchedulerButton;

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement schedulerNameInputBox;

    @FindBy(how = How.CSS, using = "form .drop-down-button .caret")
    FluentWebElement partnerDropDownIcon;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> partnerList;

    @FindBy(how = How.CSS, using = ".btn.primary-btn")
    FluentWebElement proceedButton;

    @FindBy(how = How.CSS, using = ".qa-nwschedulers-list .glyphicon-eye-open")
    FluentWebElement schedulerEditIcon;

    @FindBy(how = How.CSS, using = ".qa-nwschedulers-list .table tbody tr")
    FluentList<FluentWebElement> schedulerDetailsList;

    @FindBy(how = How.CSS, using = ".qa-nwschedulers-list .table tbody tr td:nth-child(1)")
    FluentList<FluentWebElement> schedulerNameList;

    @FindBy(how = How.CSS, using = ".first-time-import")
    FluentWebElement blankSchedulerPage;


    String exportsTabName = "Exports Tab";
    String scheduledExportsTabName = "Scheduled Exports Tab";
    String searchSchedulerInputBoxName = "Search scheduler InputBox";
    String createSchedulerButtonName = "create scheduler Button";
    String schedulerNameInputBoxName = "scheduler Name InputBox";
    String partnerDropDownIconName = "Partner DropDown Icon Name";
    String partnerListName = "partnerListName";
    String proceedButtonName = "Proceed Button";
    String schedulerEditIconName = "scheduler Edit Icon";
    String schedulerDetailsListName = "scheduler Details List";
    String schedulerNameListName = "=scheduler Name List";
    String blankSchedulerPageName = "Blank Scheduler Page";



    public void clickOnSchedulerTab() {
        FunctionLibrary.click(exportsTab, exportsTabName);
        FunctionLibrary.click(scheduledExportsTab, scheduledExportsTabName);
    }

    public void createScheduler(String schedulerName, String partnerName) {
        FunctionLibrary.click(createSchedulerButton, createSchedulerButtonName);
        FunctionLibrary.input(schedulerNameInputBox, schedulerNameInputBoxName, schedulerName);
        FunctionLibrary.click(partnerDropDownIcon, partnerDropDownIconName);
        FunctionLibrary.selectDropDownValueByName(partnerName, partnerList, partnerListName);
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
