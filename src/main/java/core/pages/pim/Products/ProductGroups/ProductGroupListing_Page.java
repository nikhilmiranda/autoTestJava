package core.pages.pim.Products.ProductGroups;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ProductGroupListing_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".qa-pgGrps-list .secondary-btn")
    FluentWebElement createProductGroupButton;

    @FindBy(how = How.CSS, using = ".element-productGroupType label:nth-child(1) input")
    FluentWebElement staticProductGroupCheckBox;

    @FindBy(how = How.CSS, using = ".element-productGroupType label:nth-child(2) input")
    FluentWebElement dynamicProductGroupTypeCheckBox;

    @FindBy(how = How.NAME, using = "productGroupName")
    FluentWebElement productGroupNameInputBox;

    @FindBy(how = How.NAME, using = "productGroupDesc")
    FluentWebElement productGroupDescInputBox;

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement proceedButton;

    @FindBy(how = How.CSS, using = ".qa-pgGrps-list table tbody tr")
    FluentList<FluentWebElement> tableRows;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchProductGroup;

    @FindBy(how = How.CSS, using = ".qa-pgGrps-list table tbody tr .glyphicon-eye-open")
    FluentWebElement productGroupEditIcon;

    @FindBy(how = How.CSS, using = ".qa-pgGrps-list .first-time-import")
    FluentWebElement blankProductGroupPage;

    @FindBy(how = How.CSS, using = ".no-data h2")
    FluentWebElement noResultFound;

    @FindBy(how = How.CSS, using = ".drop-down-button:nth-child(1) .caret")
    FluentWebElement actionIcon;

    @FindBy(how = How.CSS, using = ".row-selection-checkbox")
    FluentWebElement checkBoxAgainstProduct;

    @FindBy(how = How.CSS, using = ".list-item.delete-txt")
    FluentWebElement deleteProductGroupLink;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement confirmDeleteProductGrpButton;


    String createProductGroupButtonName = "Create Product Group Button";
    String staticProductGroupCheckBoxName = "Static Product Group Type CheckBox";
    String dynamicProductGroupCheckBoxName = "Dynamic Product Group Type CheckBox";
    String productGroupNameInputBoxName = "Product Group Name InputBox";
    String productGroupDescInputBoxName = "Product Group Desc InputBox";
    String proceedButtonName = "Proceed Button";
    String searchProductGroupName = "Search Product Group";
    String tableRowsName = "Table Rows";
    String productGroupEditIconName = "Product Group Edit Icon";
    String blankProductGroupPageName = "Blank Property Group Page";
    String noResultFoundName = "No Result Found";
    String actionIconName = "Action Icon";
    String checkBoxAgainstProductName = "CheckBox Against Product";
    String deleteProductGroupLinkName = "Delete Product Group Link";
    String confirmDeleteProductGrpButtonName = "Confirm Delete Product Grp Button";




    public void createProductGroup(String productGroupName, String productGroupDesc) {
        FunctionLibrary.click(createProductGroupButton, createProductGroupButtonName);
        FunctionLibrary.click(staticProductGroupCheckBox, staticProductGroupCheckBoxName);
        FunctionLibrary.input(productGroupNameInputBox, productGroupDescInputBoxName, productGroupName);
        FunctionLibrary.input(productGroupDescInputBox, productGroupDescInputBoxName, productGroupDesc);
        FunctionLibrary.click(proceedButton, proceedButtonName);

    }

    public void createDynamicProductGroup(String productGroupName, String productGroupDesc) {
        FunctionLibrary.click(createProductGroupButton, createProductGroupButtonName);
        FunctionLibrary.click(dynamicProductGroupTypeCheckBox, dynamicProductGroupCheckBoxName);
        FunctionLibrary.input(productGroupNameInputBox, productGroupDescInputBoxName, productGroupName);
        FunctionLibrary.input(productGroupDescInputBox, productGroupDescInputBoxName, productGroupDesc);
        FunctionLibrary.click(proceedButton, proceedButtonName);

    }

    public void searchProductGroup(String productGroupName) throws InterruptedException {
        FunctionLibrary.input(searchProductGroup, searchProductGroupName, productGroupName);
        Thread.sleep(1000);
    }

    public void clickOnPropertyGroupEditIcon() {
        FunctionLibrary.click(productGroupEditIcon, productGroupEditIconName);
    }

    public void openProductGroup(String productGrpName) throws InterruptedException {
        searchProductGroup(productGrpName);
        clickOnPropertyGroupEditIcon();

    }

    public List<String> getPropertyGroupName() {
        return FunctionLibrary.retrieveTexts(tableRows, tableRowsName);
    }



    public void waitForPropertyGroupToLoad() {
        FunctionLibrary.waitForElementToLoad(createProductGroupButton, createProductGroupButtonName);
    }

    public String getPageContentOfBlankPropertyGroupPage() {
        return FunctionLibrary.retrieveText(blankProductGroupPage, blankProductGroupPageName);
    }

    public List<String> getProductGroupDetails() {
        return FunctionLibrary.retrieveTexts(tableRows, tableRowsName);
    }

    public String getPageContentForProductGrpNotFound() {
        return FunctionLibrary.retrieveText(noResultFound, noResultFoundName);
    }

    public void deleteBulkProductGroup(String productGrpName) throws InterruptedException {
        FunctionLibrary.refreshPage();
        searchProductGroup(productGrpName);
        FunctionLibrary.click(checkBoxAgainstProduct, checkBoxAgainstProductName);
        FunctionLibrary.click(actionIcon, actionIconName);
        FunctionLibrary.click(deleteProductGroupLink, deleteProductGroupLinkName);
        FunctionLibrary.click(confirmDeleteProductGrpButton, confirmDeleteProductGrpButtonName);
    }

}
