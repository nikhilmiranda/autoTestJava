package core.pages.pim.WorkFlow;

import core.components.pim.PrimaryButton;
import core.components.pim.Search;
import core.components.pim.backToList;
import jdk.nashorn.internal.objects.annotations.Function;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class WorkFlowManage_Page extends FluentPage {

    @Page
    Search search;

    @Page
    PrimaryButton primaryButton;

    @Page
    backToList backToList;

    @FindBy(how = How.CSS, using = ".drop-down-button:nth-child(1) .caret")
    FluentWebElement labelDropDown;

    @FindBy(how = How.CSS, using = ".drop-down-button:nth-child(1) .caret")
    FluentWebElement valueDropDown;

    @FindBy(how = How.CSS, using = ".drop-down-button:nth-child(1) .caret")
    FluentWebElement operatorDropDown;

    @FindBy(how = How.CSS, using = ".add-new-icon")
    FluentWebElement nodeAddNewIcon;

    @FindBy(how = How.CSS, using = ".add-icon")
    FluentWebElement nodeAddIcon;

    @FindBy(how = How.CSS, using = ".node-list-item")
    FluentList<FluentWebElement> nodeList;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> nodeListDag;

    @FindBy(how = How.CSS, using = ".wf-save-button")
    FluentWebElement wfSaveBtn;

    @FindBy(how = How.CSS, using = ".sb-run-now")
    FluentWebElement runTheWFNowLink;

    @FindBy(how = How.CSS, using = ".timer-icon")
    FluentWebElement logsIcon;

    @FindBy(how = How.CSS, using = ".no-data")
    FluentWebElement noResultFoundLabel;

    String nodeListName = "Node List";
    String nodeAddNewIconName = "New Node Add Icon";
    String nodeAddIconName = "Node Add Icon";
    String wfSaveBtnName = "Workflow Save Button";
    String runTheWFNowLinkName = "Run The WF Now Link";
    String logsIconName = "Logs Icon";
    String noResultFoundLabelName = "No Result Found";


    public void extendBranch(String node) {
        for(FluentWebElement ele : nodeList){
            if (FunctionLibrary.retrieveText(ele.getElement().findElement(By.cssSelector(".node")), "rf") == node){
                FunctionLibrary.click(ele.getElement().findElement(By.cssSelector(".node-extend")), "frf");
            }
        }
    }

    public void selectAndAddNode(String nodeName) throws InterruptedException {
        search.search(nodeName);
        FunctionLibrary.click(nodeList.get(0), nodeListName);
    }

    public void clickOnDagPlusIcon(){
        FunctionLibrary.click(nodeAddIcon, nodeAddIconName);
    }

    public void clickOnNewDagPlusIcon(){
        FunctionLibrary.click(nodeAddNewIcon, nodeAddNewIconName);
    }

    public void waitForManageWorkFLowPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(nodeAddIcon, nodeAddIconName);
        backToList.waitForLinkToBeClickable();
    }

    public void clickOnWfSaveBtn() {
        FunctionLibrary.click(wfSaveBtn, wfSaveBtnName);
    }

    public void clickOnRunTheWFNow() {
        FunctionLibrary.click(runTheWFNowLink, runTheWFNowLinkName);
        primaryButton.clickOnPrimaryModalButton();
    }

    public void clickOnLogsIcon() {
        FunctionLibrary.click(logsIcon, logsIconName);
    }

    public String blackScreenOfActivityLog() {
        return FunctionLibrary.retrieveText(noResultFoundLabel, noResultFoundLabelName);
    }


}
