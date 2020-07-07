package core.pages.pim.Products.ProductGroups;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

import javax.sql.rowset.spi.SyncResolver;
import java.util.List;

public class ManageProductGroup_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".qa-pgGrps-edit .horizontal-nav-bar.secondary li:nth-child(2) a")
    FluentWebElement grpInfoTab;

    @FindBy(how = How.CSS, using = ".qa-pgGrps-edit .primary-btn")
    FluentWebElement addProductsButton;

    @FindBys( {
            @FindBy(how = How.CSS, using = ".modal-content"),
            @FindBy(how = How.NAME, using = "searchVal")
    } )
    FluentWebElement searchProductsInModal;

    @FindBys( {
            @FindBy(how = How.CSS, using = ".qa-pgGrps-edit"),
            @FindBy(how = How.NAME, using = "searchVal")
    } )
    FluentWebElement searchProduct;

    @FindBy(how = How.CSS, using = ".modal-body tbody .row-selection-checkbox")
    FluentWebElement checkBoxAgainstProduct;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement addProductModalButton;

    @FindBy(how = How.CSS, using = "table tbody tr")
    FluentList<FluentWebElement> productAddedToGrp;

    @FindBy(how = How.CSS, using = ".product-name-text")
    FluentList<FluentWebElement> productNameAddedToGrp;

    @FindBy(how = How.NAME, using = "productGroupDesc")
    FluentWebElement productGroupDescInputBox;

    @FindBy(how = How.CSS, using = ".qa-pgGrps-edit .primary-btn")
    FluentWebElement addMoreProductsButton;

    @FindBy(how = How.CSS, using = ".qa-pgGrps-edit .primary-btn")
    FluentWebElement addDynamicFiltersButton;

    @FindBy(how = How.CSS, using = ".qa-pgGrps-edit .primary-btn")
    FluentWebElement updateDynamicFiltersButton;

    @FindBy(how = How.CSS, using = ".qa-pgGrps-edit .add-filter-block")
    FluentWebElement addAnotherFilterBlockLabel;

    @FindBy(how = How.CSS, using = ".qa-pgGrps-edit .primary-btn")
    FluentWebElement saveChangesToProductGroupButton;

    @FindBy(how = How.CSS, using = ".qa-pgGrps-edit .back-to-list")
    FluentWebElement backToProductGroupListingLink;

    @FindBy(how = How.CSS, using = ".delete-entity .hand-pointer")
    FluentWebElement deleteProductGrpLink;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement confirmDeleteProductGrpButton;

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement productGrpNameInputBox;

    @FindBy(how = How.NAME, using = "description")
    FluentWebElement productGrpDescInputBox;

    @FindBy(how = How.CSS, using = ".primary-btn ")
    FluentWebElement productGrpUpdateButton;

    @FindBy(how = How.CSS, using = ".actions-dd .caret")
    FluentWebElement actionIcon;

    @FindBy(how = How.CSS, using = "ul .list li:nth-child(1)")
    FluentWebElement bulkEditProductLink;

    @FindBy(how = How.CSS, using = "ul .list li:nth-child(2)")
    FluentWebElement removeProductLink;

    @FindBy(how = How.CSS, using = "ul .list li:nth-child(3)")
    FluentWebElement deleteProductLink;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement successNotification;


    String grpInfoTabName = "Group Info Tab";
    String addProductsButtonName = "Add Products Button";
    String searchProductsInModalName = "Search Products In Modal";
    String searchProductName = "Search Product";
    String checkBoxAgainstProductName = "Check Box Against Product";
    String addProductModalButtonName = "Add Product Modal Button";
    String productAddedToGrpName = "Product Added To Grp";
    String productGroupDescInputBoxName = "Product Group Desc InputBox";
    String addMoreProductsButtonName = "Add More Products Button";
    String addDynamicFiltersButtonName = "Add Dynamic Filters Button Name";
    String updateDynamicFiltersButtonName = "Update Dynamic Filters Button Name";
    String saveChangesToProductGroupButtonName = "Save Changes To Product Group Button";
    String addAnotherFilterBlockLabelName = "Add Another Filter Block Label";
    String backToProductGroupListingLinkName = "Back To Product Group Listing Link";
    String deleteProductGrpLinkName = "Delete Product Grp Link";
    String confirmDeleteProductGrpButtonName = "Confirm Delete Product Grp Button";
    String productGrpNameInputBoxName = "Product Grp Name Input Box";
    String productGrpDescInputBoxName = "Product Grp Desc Input Box";
    String productGrpUpdateButtonName = "Product Grp UpdateButton";
    String actionIconName = "Action Icon Name";
    String bulkEditProductLinkName = "Bulk Edit Product Link";
    String removeProductLinkName = "Remove Product Link";
    String deleteProductLinkName = "Delete Product Link";
    String successNotificationName = "Success Notification";

    public void clickOnGrpInfoTab() {
        FunctionLibrary.click(grpInfoTab, grpInfoTabName);
    }

    public void clickOnAddProductsButton() {
        FunctionLibrary.click(addProductsButton, addProductsButtonName);
    }

    public void searchProductInModal(String productName) throws InterruptedException {
        FunctionLibrary.input(searchProductsInModal, searchProductsInModalName, productName);
        Thread.sleep(1000);
    }

    public void searchProduct(String productName) throws InterruptedException {
        FunctionLibrary.input(searchProduct, searchProductName, productName);
        Thread.sleep(1000);
    }

    public void clickOnCheckBoxAgainstProduct() {
        FunctionLibrary.click(checkBoxAgainstProduct, checkBoxAgainstProductName);
    }

    public void clickOnAddProductToGroup() {
        FunctionLibrary.click(addProductModalButton, addProductModalButtonName);
    }

    public void clickOnAddMoreProduct() {
        FunctionLibrary.click(addMoreProductsButton, addMoreProductsButtonName);
    }

    public void clickOnAddDynamicFiltersButton() {
        FunctionLibrary.click(addDynamicFiltersButton, addDynamicFiltersButtonName);
    }

    public void clickOnUpdateDynamicFiltersButton() {
        FunctionLibrary.click(updateDynamicFiltersButton, addDynamicFiltersButtonName);
    }

    public void clickOnSaveChangesToProductGroupButton() {
        FunctionLibrary.click(saveChangesToProductGroupButton, saveChangesToProductGroupButtonName);
    }

    public List<String> getAllTheProducts() {
       return FunctionLibrary.retrieveTexts(productAddedToGrp, productAddedToGrpName);
    }

    public List<String> getAllTheProductsName() {
        return FunctionLibrary.retrieveTexts(productNameAddedToGrp, productAddedToGrpName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(backToProductGroupListingLink, backToProductGroupListingLinkName);
    }

    public void clickOnBackToProductGroupListingPage() {
        FunctionLibrary.click(backToProductGroupListingLink, backToProductGroupListingLinkName);
    }

    public  void deleteProductGrp() {
        FunctionLibrary.scrollToBottom();
        FunctionLibrary.click(deleteProductGrpLink, deleteProductGrpLinkName);
        if(!FunctionLibrary.isElementPresent(confirmDeleteProductGrpButton, confirmDeleteProductGrpButtonName)){
            FunctionLibrary.click(deleteProductGrpLink, deleteProductGrpLinkName);
        }
        FunctionLibrary.click(confirmDeleteProductGrpButton, confirmDeleteProductGrpButtonName);
        if(FunctionLibrary.isElementPresent(confirmDeleteProductGrpButton, confirmDeleteProductGrpButtonName)){
            FunctionLibrary.click(confirmDeleteProductGrpButton, confirmDeleteProductGrpButtonName);
        }
        if (FunctionLibrary.getPageURL().contains("action=edit")) {
            deleteProductGrp();
        }
    }

    public void setProductGrpName(String productGrpName) {
        FunctionLibrary.input(productGrpNameInputBox, productGrpNameInputBoxName, productGrpName);

    }

    public void setProductGrpDescription(String desc) {
        FunctionLibrary.input(productGrpDescInputBox, productGrpDescInputBoxName, desc);

    }

    public void updateProductGrp() {
        FunctionLibrary.click(productGrpUpdateButton, productGrpUpdateButtonName);

    }

    public void clickOnActionIcon() {
        FunctionLibrary.click(actionIcon, actionIconName);

    }

    public void clickOnBulkProductEdit() {
        FunctionLibrary.click(bulkEditProductLink, bulkEditProductLinkName);

    }

    public void clickOnRemoveProduct() {
        FunctionLibrary.click(removeProductLink, removeProductLinkName);
        FunctionLibrary.click(confirmDeleteProductGrpButton, confirmDeleteProductGrpButtonName);

    }

    public void clickOnDeleteProduct() {
        FunctionLibrary.click(deleteProductGrpLink, deleteProductGrpLinkName);
        FunctionLibrary.click(confirmDeleteProductGrpButton, confirmDeleteProductGrpButtonName);

    }

    public void clickOnAddAnotherFiterBlock() {
        FunctionLibrary.click(addAnotherFilterBlockLabel, addAnotherFilterBlockLabelName);

    }

    public String getSuccessMsg() {
        return FunctionLibrary.retrieveText(successNotification, successNotificationName);

    }




}
