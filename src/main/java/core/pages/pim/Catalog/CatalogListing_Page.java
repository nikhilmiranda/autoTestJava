package core.pages.pim.Catalog;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CatalogListing_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".qa-catalog-list .btn.secondary-btn")
    FluentWebElement createCatalogButton;

    @FindBy(how = How.NAME, using = "catalogName")
    FluentWebElement catalogNameInputBox;

    @FindBy(how = How.CSS, using = ".btn.primary-btn")
    FluentWebElement proceedButton;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchCatalogInputBox;

    @FindBy(how = How.CSS, using = ".qa-catalog-list table tbody tr td:nth-child(1)")
    FluentList<FluentWebElement>  catalogNameList;

    @FindBy(how = How.CSS, using = ".qa-catalog-list .glyphicon-eye-open")
    FluentWebElement catalogEditIcon;

    @FindBy(how = How.CSS, using = ".qa-catalog-list .glyphicon-new-window")
    FluentWebElement catalogOpenIcon;

    @FindBy(how = How.CSS, using = ".qa-catalog-list .glyphicon-log-in")
    FluentWebElement catalogCopyIcon;


    String createCatalogButtonName = "Create Catalog Button";
    String catalogNameInputBoxName = "Catalog Name InputBox";
    String proceedButtonName = "Proceed Button";
    String searchCatalogInputBoxName = "Search Catalog InputBox";
    String catalogNameListName = "Catalog Name List";
    String catalogEditIconName = "Catalog Edit Icon";
    String catalogOpenIconName = "Catalog Open Icon";
    String catalogCopyIconName = "Catalog Copy Icon";


    public void createCatalog(String catalogName) {
        FunctionLibrary.click(createCatalogButton, createCatalogButtonName);
        FunctionLibrary.input(catalogNameInputBox, catalogNameInputBoxName, catalogName);
        FunctionLibrary.click(proceedButton, proceedButtonName);

    }

    public Boolean isCreateCatalogButtonPresent() {
       return FunctionLibrary.isElementPresent(createCatalogButton, createCatalogButtonName);

    }

    public void searchCatalog(String catalogName) {
        FunctionLibrary.input(searchCatalogInputBox, searchCatalogInputBoxName, catalogName);

    }

    public List<String> getCatalogNames() {
       return FunctionLibrary.retrieveTexts(catalogNameList, catalogNameListName);
    }

    public void editCatalog(String catalogName) {
        searchCatalog(catalogName);
        FunctionLibrary.click(catalogEditIcon, catalogEditIconName);

    }

    public void openCatalog(String catalogName) {
        searchCatalog(catalogName);
        FunctionLibrary.click(catalogOpenIcon, catalogOpenIconName);

    }

    public void copyCatalog(String catalogName) {
        searchCatalog(catalogName);
        FunctionLibrary.click(catalogCopyIcon, catalogCopyIconName);

    }

    public void waitForElementToLoad() {
        FunctionLibrary.waitForElementToBeClickable(createCatalogButton, createCatalogButtonName);

    }

}
