package core.pages.pim.Imports;

import com.sun.jna.platform.win32.OaIdl;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import java.util.List;

public class ManageImport_Page extends FluentPage {


    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(5) a")
    FluentWebElement importSftpListTab;

    @FindBy(how = How.CSS, using = ".qa-new-import .drag-n-drop-uploader-area .secondary-btn")
    FluentWebElement fetchFromSftpButton;

    @FindBy(how = How.CSS, using = ".qa-import-list .primary-btn")
    FluentWebElement proceedButton;

    @FindBy(how = How.CSS, using = ".qa-import-list .secondary-btn")
    FluentWebElement newImportButton;

    @FindBy(how = How.ID, using = "importFileUpload")
    FluentWebElement uploadButton;

    @FindBy(how = How.CSS, using = ".import-summary-block")
    FluentWebElement summaryBlock;

    @FindBy(how = How.CSS, using = ".show-new-prop")
    FluentWebElement showNewProperties;

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement startImportButton;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement dialogStartImportButton;

    @FindBy(how = How.CSS, using = ".import-th span")
    FluentList<FluentWebElement> propertiesHeader;

    @FindBy(how = How.CSS, using = ".import-unique-selector fieldset:nth-child(1) input")
    FluentWebElement productIDSelector;

    @FindBy(how = How.CSS, using = ".import-unique-selector fieldset:nth-child(2) input")
    FluentWebElement productNameSelector;

    @FindBy(how = How.CSS, using = ".import-unique-selector fieldset:nth-child(3) input")
    FluentWebElement productImageSelector;

    @FindBy(how = How.CSS, using = ".import-unique-selector fieldset:nth-child(4) input")
    FluentWebElement parentIDSelector;

    @FindBy(how = How.CSS, using = ".import-unique-selector fieldset:nth-child(5) input")
    FluentWebElement isSearchableSelector;

    @FindBy(how = How.NAME, using = "delimiter")
    FluentWebElement multivaluedSeparatorInputBox;

    @FindBy(how = How.CSS, using = ".import-prop-type .drop-down-text")
    FluentWebElement propertyDataTypeLabel;

    @FindBy(how = How.CSS, using = ".drop-down-input")
    FluentWebElement propertyDataTypeSearchInputBox;

    @FindBy(how = How.CSS, using = ".list-item ")
    FluentWebElement dataTypeList;

    @FindBy(how = How.CSS, using = ".import-completed-msg")
    FluentWebElement importCompletedMsg;

    @FindBy(how = How.CSS, using = ".import-processed .count")
    FluentWebElement processedProductsCount;

    @FindBy(how = How.CSS, using = ".import-failed .count")
    FluentWebElement failedProductsCount;

    @FindBy(how = How.CSS, using = ".import-total .count")
    FluentWebElement totalProductsCount;


    @FindBy(how = How.CSS, using = ".import-new-property-name")
    FluentWebElement newPropertyName;

    @FindBy(how = How.CSS, using = ".import-new-property-type")
    FluentWebElement newPropertyType;

    @FindBy(how = How.CSS, using = ".import-new-props")
    FluentWebElement newPropertyCount;

    @FindBy(how = How.CSS, using = ".import-mapping-type label:nth-child(2) input")
    FluentWebElement scriptCheckbox;

    @FindBy(how = How.CSS, using = ".import-mapping-type label:nth-child(3) input")
    FluentWebElement doNotMapCheckbox;

    @FindBy(how = How.CSS, using = ".editor-action")
    FluentWebElement editScriptLink;

//    @FindBy(how = How.CSS, using = ".drop-down-input")
//    FluentWebElement propertyDataTypeSearchInputBox;

//    @FindBy(how = How.CSS, using = ".list-item ")
//    FluentWebElement dataTypeList;

    public static By importLoader = By.cssSelector(".progress-bar-cont");

    String importSftpListTabName = "Import Sftp List Tab";
    String startImportButtonName = "Start Import Button";
    String dialogStartImportButtonName = "Dialog Start Import Button";
    String filePathName = "File Path";
    String uploadButtonName = "Upload Button";
    String proceedButtonName = "Proceed Button";
    String newImportButtonName = "New Import Button";
    String propertiesHeaderName = "Properties Header";
    String productIDSelectorName = "productID Selector";
    String productNameSelectorName = "productName Selector";
    String productImageSelectorName = "productImage Selector";
    String parentIDSelectorName = "parentID Selector";
    String isSearchableSelectorName = "isSearchable Selector";
    String propertyDataTypeLabelName = "Property DataType Label";
    String multivaluedSeparatorInputBoxName = "Multivalued Separator InputBox";
    String propertyDataTypeSearchInputBoxName = "Property Data Type";
    String dataTypeListName = "Data Type List";
    String importCompletedMsgName = "importCompletedMsg";
    String processedProductsCountName = "Processed Products Count";
    String failedProductsCountName = "Failed Products Count";
    String totalProductsCountName = "Total Products Count";
    String importLoaderName = "Import Loader";



    @FindBy(how = How.CSS, using = ".mandatory-prop-block:nth-child(1) .drop-down-button .caret")
    FluentWebElement productIdDropDownIcon;

    @FindBy(how = How.CSS, using = ".mandatory-prop-block:nth-child(2) .drop-down-button .caret")
    FluentWebElement productNameDropDownIcon;

    @FindBy(how = How.CSS, using = ".mandatory-prop-block:nth-child(3) .drop-down-button .caret")
    FluentWebElement productImageDropDownIcon;

    @FindBy(how = How.CSS, using = ".mandatory-prop-block:nth-child(4) .drop-down-button .caret")
    FluentWebElement parentIdDropDownIcon;

    @FindBy(how = How.CSS, using = ".mandatory-prop-block:nth-child(5) .drop-down-button .caret")
    FluentWebElement categoryDropDownIcon;

    @FindBy(how = How.CSS, using = ".drop-down-input")
    FluentWebElement mandatoryPropBlockDropdownInputBox;

    @FindBy(how = How.CSS, using = ".list-item ")
    FluentWebElement propertyList;

    @FindBy(how = How.CSS, using = ".multi-valued:nth-child(1) input")
    FluentWebElement isSearchableCheckBox;

    @FindBy(how = How.CSS, using = ".multi-valued:nth-child(2) input")
    FluentWebElement multivaluedCheckBox;

    @FindBy(how = How.CSS, using = ".multi-valued:nth-child(3) input")
    FluentWebElement treeListCheckBox;

    @FindBy(how = How.XPATH, using = "//input[starts-with(@name,'delimiter')]")
    FluentWebElement multiValuedSeparatorInputBox;

    @FindBy(how = How.XPATH, using = "//input[starts-with(@name,'tree_delimiter')]")
    FluentWebElement treeListSepratorInputBox;

    @FindBy(how = How.NAME, using = "adapterName")
    FluentWebElement adapterNameInputBox;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement initializeImportButton;


    String productIdDropDownIconName = "Product Id DropDown Icon";
    String productNameDropDownIconName = "Product Name DropDown Icon";
    String productImageDropDownIconName = "Product Image DropDown Icon";
    String parentIdDropDownIconName = "Parent Id DropDown Icon";
    String categoryDropDownIconName = "Category DropDown Icon";
    String mandatoryPropBlockDropdownInputBoxName = "Mandatory Prop Block Dropdown InputBox";
    String propertyListName = "Property List";
    String isSearchableCheckBoxName = "Is Searchable CheckBox";
    String multivaluedCheckBoxName = "Multivalued CheckBox";
    String treeListCheckBoxName = "Tree List CheckBox";
    String multiValuedSeparatorInputBoxName = "Multivalued Separator InputBoxx";
    String treeListSepratorInputBoxName = "Tree/List Seprator InputBox";
    String adapterNameInputBoxName = "Adapter Name InputBox";
    String initializeImportButtonName = "Initialize Import Button";



    public void clickOnImportSftpListTab() {
        FunctionLibrary.click(importSftpListTab, importSftpListTabName);
    }

    public void mapProductID(String productId) throws InterruptedException {
        FunctionLibrary.click(productIdDropDownIcon, productIdDropDownIconName);
        FunctionLibrary.input(mandatoryPropBlockDropdownInputBox, mandatoryPropBlockDropdownInputBoxName, productId);
        Thread.sleep(1000);
        FunctionLibrary.click(propertyList, propertyListName);
    }

    public void mapProductName(String productName) throws InterruptedException {
        FunctionLibrary.click(productNameDropDownIcon, productNameDropDownIconName);
        FunctionLibrary.input(mandatoryPropBlockDropdownInputBox, mandatoryPropBlockDropdownInputBoxName, productName);
        Thread.sleep(1000);
        FunctionLibrary.click(propertyList, propertyListName);
    }

    public void mapProductImage(String productImage) throws InterruptedException {
        FunctionLibrary.click(productImageDropDownIcon, productImageDropDownIconName);
        FunctionLibrary.input(mandatoryPropBlockDropdownInputBox, mandatoryPropBlockDropdownInputBoxName, productImage);
        Thread.sleep(1000);
        FunctionLibrary.click(propertyList, propertyListName);
    }

    public void mapParentId(String parentId) throws InterruptedException {
        FunctionLibrary.click(parentIdDropDownIcon, parentIdDropDownIconName);
        FunctionLibrary.input(mandatoryPropBlockDropdownInputBox, mandatoryPropBlockDropdownInputBoxName, parentId);
        Thread.sleep(1000);
        FunctionLibrary.click(propertyList, propertyListName);
    }

    public void mapCategory(String category) throws InterruptedException {
        FunctionLibrary.click(categoryDropDownIcon, categoryDropDownIconName);
        FunctionLibrary.input(mandatoryPropBlockDropdownInputBox, mandatoryPropBlockDropdownInputBoxName, category);
        Thread.sleep(1000);
        FunctionLibrary.click(propertyList, propertyListName);
    }

    public void clickOnProceedAfter1stImport() throws InterruptedException {
        FunctionLibrary.click(newImportButton, newImportButtonName);
        FunctionLibrary.click(proceedButton, proceedButtonName);
    }

    public void clickOnProceed() throws InterruptedException {
//        if(FunctionLibrary.isElementDisplayed(newImportButton, newImportButtonName)){
//            FunctionLibrary.click(newImportButton, newImportButtonName);
//        }
        FunctionLibrary.scrollToTop();
        FunctionLibrary.click(proceedButton, proceedButtonName);
    }

    public void uploadFile(String filePath){
        uploadButton.fill().with(filePath);
    }

    public String getSummaryBlockText(){
      return summaryBlock.getText();
    }

    public void clickOnShowNewProperties(){
         showNewProperties.click();
    }

    public List<String> getAllThePropertiesImported() {
        return FunctionLibrary.retrieveTexts(propertiesHeader, propertiesHeaderName);
    }

    public List<String> getAllTheNewProperties() {
        return FunctionLibrary.retrieveTexts(propertiesHeader, propertiesHeaderName);
    }

    public void selectDataType(String dataType) throws InterruptedException {
        FunctionLibrary.click(propertyDataTypeLabel, propertyDataTypeLabelName);
        FunctionLibrary.input(propertyDataTypeSearchInputBox, propertyDataTypeSearchInputBoxName, dataType);
        Thread.sleep(1000);
        FunctionLibrary.click(dataTypeList, dataTypeListName);

    }

    public void mapProperties() {
        for(FluentWebElement propertyHeader : propertiesHeader) {
            if (FunctionLibrary.retrieveText(propertyHeader, propertiesHeaderName).equalsIgnoreCase("Product Id")) {
                FunctionLibrary.click(propertyHeader, propertiesHeaderName);
                Assert.assertTrue(FunctionLibrary.isChecked(isSearchableCheckBox, isSearchableCheckBoxName));
                Assert.assertFalse(FunctionLibrary.isElementDisplayed(multiValuedSeparatorInputBox, multiValuedSeparatorInputBoxName));
            } else if (FunctionLibrary.retrieveText(propertyHeader, propertiesHeaderName).equalsIgnoreCase("Image")) {
                FunctionLibrary.scrollToTop();
                FunctionLibrary.click(propertyHeader, propertiesHeaderName);
                Assert.assertFalse(FunctionLibrary.isChecked(isSearchableCheckBox, isSearchableCheckBoxName));
                Assert.assertFalse(FunctionLibrary.isElementDisplayed(multiValuedSeparatorInputBox, multiValuedSeparatorInputBoxName));

            } else if (FunctionLibrary.retrieveText(propertyHeader, propertiesHeaderName).equalsIgnoreCase("Product Name")) {
                FunctionLibrary.scrollToTop();
                FunctionLibrary.click(propertyHeader, propertiesHeaderName);
                Assert.assertTrue(FunctionLibrary.isChecked(isSearchableCheckBox, isSearchableCheckBoxName));
                Assert.assertFalse(FunctionLibrary.isElementDisplayed(multiValuedSeparatorInputBox, multiValuedSeparatorInputBoxName));

            } else if (FunctionLibrary.retrieveText(propertyHeader, propertiesHeaderName).equalsIgnoreCase("Color")) {
                FunctionLibrary.scrollToTop();
                FunctionLibrary.click(propertyHeader, propertiesHeaderName);
                FunctionLibrary.click(multivaluedCheckBox, multivaluedCheckBoxName);
                FunctionLibrary.input(multiValuedSeparatorInputBox, multiValuedSeparatorInputBoxName, ",");

            } else if (FunctionLibrary.retrieveText(propertyHeader, propertiesHeaderName).equalsIgnoreCase("Size")) {
                FunctionLibrary.scrollToTop();
                FunctionLibrary.click(propertyHeader, propertiesHeaderName);
                FunctionLibrary.click(multivaluedCheckBox, multivaluedCheckBoxName);
                FunctionLibrary.input(multiValuedSeparatorInputBox, multiValuedSeparatorInputBoxName, ",");
                FunctionLibrary.scrollToTop();

            }
            else if (FunctionLibrary.retrieveText(propertyHeader, propertiesHeaderName).equalsIgnoreCase("Category")) {
                FunctionLibrary.scrollToTop();
                FunctionLibrary.click(propertyHeader, propertiesHeaderName);
                FunctionLibrary.click(treeListCheckBox, treeListCheckBoxName);
                FunctionLibrary.input(treeListSepratorInputBox, treeListSepratorInputBoxName, ">");
                FunctionLibrary.scrollToTop();

            } else if (FunctionLibrary.retrieveText(propertyHeader, propertiesHeaderName).equalsIgnoreCase("Parent Id")) {
                FunctionLibrary.scrollToTop();
                FunctionLibrary.click(propertyHeader, propertiesHeaderName);
                Assert.assertTrue(FunctionLibrary.isChecked(isSearchableCheckBox, isSearchableCheckBoxName));
                Assert.assertFalse(FunctionLibrary.isElementDisplayed(multiValuedSeparatorInputBox, multiValuedSeparatorInputBoxName));
            }
        }
    }

    public void associateAnAdapter(String adapterName){
        FunctionLibrary.input(adapterNameInputBox, adapterNameInputBoxName, adapterName);
    }

    public void initializeImport(){
        FunctionLibrary.click(initializeImportButton, initializeImportButtonName);
    }

    public void startImport() {
        ((JavascriptExecutor) getDriver())
                .executeScript("window.scrollTo(0, 0)");
        FunctionLibrary.click(startImportButton, startImportButtonName);
        FunctionLibrary.click(dialogStartImportButton, dialogStartImportButtonName);
    }

    public void waitForProductPreImportToGetCompleted() {
        FunctionLibrary.waitForLoaderToDissapear(importLoader, importLoaderName);
        FunctionLibrary.waitForElementToBeClickable(startImportButton, startImportButtonName);
    }

    public void waitForProductImportToComplete() {
        FunctionLibrary.waitForLoaderToDissapear(importLoader, importLoaderName);
        FunctionLibrary.waitForElementToLoad(importCompletedMsg, importCompletedMsgName);
        FunctionLibrary.waitForElementToLoad(totalProductsCount, totalProductsCountName);
    }

    public String getImportSuccessMsg() {
       return FunctionLibrary.retrieveText(importCompletedMsg, importCompletedMsgName);

    }

    public String getProcessedProductCount() {
        return FunctionLibrary.retrieveText(processedProductsCount, processedProductsCountName);
    }

    public String getTotalProductCount() {
        return FunctionLibrary.retrieveText(totalProductsCount, totalProductsCountName);
    }

    public String getFailedProductCount() {
        return FunctionLibrary.retrieveText(failedProductsCount, failedProductsCountName);
    }

    public void waitForPreImport() {
        FunctionLibrary.waitForLoaderToDissapear(importLoader, importLoaderName);
    }

    public void waitForPostImport() {
        FunctionLibrary.waitForLoaderToDissapear(importLoader, importLoaderName);
    }

}
