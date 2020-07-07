package core.pages.pim.ManageNetwork.Adapter.CustomAdapter;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CustomAdapter_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(2)")
    FluentWebElement customAdaptersTab;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchAdapterInputBox;

    @FindBy(how = How.CSS, using = ".qa-custadapters-list .secondary-btn")
    FluentWebElement createAdapterButton;

    @FindBy(how = How.NAME, using = "adapterName")
    FluentWebElement adapterNameInputBox;

    @FindBy(how = How.NAME, using = "adapterDesc")
    FluentWebElement adapterDescInputBox;

    @FindBy(how = How.CSS, using = ".btn.primary-btn")
    FluentWebElement proceedButton;

    @FindBy(how = How.CSS, using = ".qa-custadapters-list .glyphicon-eye-open")
    FluentWebElement adapterEditIcon;

    @FindBy(how = How.CSS, using = ".qa-custadapters-list .table tbody tr")
    FluentList<FluentWebElement> adapterDetailsList;

    @FindBy(how = How.CSS, using = ".qa-custadapters-list .table tbody tr td:nth-child(1)")
    FluentList<FluentWebElement> adapterNameList;

    String customAdaptersTabName = "Custom Adapters Tab";
    String searchAdapterInputBoxName = "Search Adapter InputBox";
    String createAdapterButtonName = "create Adapter Button";
    String adapterNameInputBoxName = "Adapter Name InputBox";
    String adapterDescInputBoxName = "Adapter Desc InputBox";
    String proceedButtonName = "Proceed Button";
    String adapterEditIconName = "Adapter Edit Icon";
    String adapterDetailsListName = "Adapter Details List";
    String adapterNameListName = "=Adapter Name List";


    public void clickOnCustomAdaptersTab() {
        FunctionLibrary.click(customAdaptersTab, customAdaptersTabName);
    }

    public void createAdapter(String adapterName, String adapterDesc) {
        FunctionLibrary.click(createAdapterButton, createAdapterButtonName);
        FunctionLibrary.input(adapterNameInputBox, adapterNameInputBoxName, adapterName);
        FunctionLibrary.input(adapterDescInputBox, adapterDescInputBoxName, adapterDesc);
        FunctionLibrary.click(proceedButton, proceedButtonName);
    }

    public void searchAdapter(String adapterName) throws InterruptedException {
        FunctionLibrary.input(searchAdapterInputBox, searchAdapterInputBoxName, adapterName);
        Thread.sleep(1000);
    }

    public List<String> fetchAdapters() {
       return FunctionLibrary.retrieveTexts(adapterDetailsList, adapterDetailsListName);
    }


    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToLoad(createAdapterButton, createAdapterButtonName);
    }

    public void openAdapter(String adapterName) throws InterruptedException {
        searchAdapter(adapterName);
        FunctionLibrary.click(adapterEditIcon, adapterEditIconName);
    }



}



