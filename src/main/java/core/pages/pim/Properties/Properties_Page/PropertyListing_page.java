package core.pages.pim.Properties.Properties_Page;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.apache.commons.collections.MultiMap;
import org.apache.commons.collections.map.MultiValueMap;

import java.util.List;

public class PropertyListing_page extends FluentPage {

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(1) a")
    FluentWebElement propertiesTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(2) a")
    FluentWebElement productGroupsTab;

    @FindBy(how = How.CSS, using = ".qa-prop-list .inline-modal-header .secondary-btn")
    FluentWebElement createPropertyButton;

    @FindBy(how = How.NAME, using = "propertyName")
    FluentWebElement propertyNameInputBox;

    @FindBy(how = How.CSS, using = "form .drop-down-button .caret")
    FluentWebElement propertyDataTypeDropDownIcon;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> propertyDataTypeList;

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement proceedButton;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchPropertyInputBox;

    @FindBy(how = How.CSS, using = ".enabled .drop-down-text")
    FluentWebElement propertyDataType;

    @FindBy(how = How.CSS, using = ".qa-prop-list .table tbody tr")
    FluentList<FluentWebElement> propertyDetailsList;

    @FindBy(how = How.CSS, using = ".qa-prop-list .table tbody tr td:nth-child(1)")
    FluentList<FluentWebElement> propertyNameList;

    @FindBy(how = How.CSS, using = ".table tbody tr td:nth-child(2)")
    FluentList<FluentWebElement> propertyTypeList;

    @FindBy(how = How.CSS, using = ".table tbody tr td:nth-child(3)")
    FluentList<FluentWebElement> propertyGroupList;

    @FindBy(how = How.CSS, using = ".qa-prop-list .table tbody tr td:nth-child(4) .glyphicon-eye-open")
    FluentList<FluentWebElement> propertyEditIcon;

    @FindBy(how = How.CSS, using = ".no-data h2")
    FluentWebElement noResultFound;

    @FindBy(how = How.CSS, using = ".first-time-import")
    FluentWebElement blankPropertiesPage;

    @FindBy(how = How.CSS, using = ".first-time-import")
    FluentWebElement propertyListingHeaderClass;



    String createPropertyButtonName = "Create Property Button";
    String propertyNameInputBoxName = "Property Name InputBox";
    String propertyDataTypeDropDownIconName = "Property DataType DropDown Icon";
    String propertyDataTypeListName = "Property DataType List";
    String proceedButtonName = "Proceed Button";
    String searchPropertyInputBoxName = "Search Property InputBox";
    String propertyNameListName = "Property Name List";
    String propertyDetailsListName = "Property Details List";
    String propertyEditIconName = "Property Edit Icon";
    String noResultFoundName = "No Result Found";
    String blankPropertiesPageName = "Blank Properties Page";
    String propertiesTabName = "Properties Tab";
    String productGroupsTabName = "Product Groups Tab";
    String propertyListingHeaderClassName = "propertyListingHeaderClass";


    public void createProperty(String propertyName, String propertyDataType) {
        FunctionLibrary.click(createPropertyButton, createPropertyButtonName);
        FunctionLibrary.input(propertyNameInputBox, propertyNameInputBoxName, propertyName);
        FunctionLibrary.click(propertyDataTypeDropDownIcon, propertyDataTypeDropDownIconName);
        FunctionLibrary.selectDropDownValueByName(propertyDataType, propertyDataTypeList, propertyDataTypeListName);
        FunctionLibrary.click(proceedButton, proceedButtonName);
    }

    public Boolean isCreatePropertyButtonPresent() {
        return FunctionLibrary.isElementPresent(createPropertyButton, createPropertyButtonName);

    }

    public void searchPropertyName(String propertyName){
        FunctionLibrary.input(searchPropertyInputBox, searchPropertyInputBoxName, propertyName);

    }

    public List<String> getPropertyNames(){
       return FunctionLibrary.retrieveTexts(propertyNameList, propertyNameListName);
    }

    public List<String> getPropertyDetails(){
        return FunctionLibrary.retrieveTexts(propertyDetailsList, propertyDetailsListName);
    }

    public MultiMap propertiesMappedToProductGroup() {
        MultiMap map = new MultiValueMap();
        for(FluentWebElement ele : propertyDetailsList){
            map.put(ele.getElement().findElement(By.cssSelector("td:nth-child(3)")).getText(), ele.getElement().findElement(By.cssSelector("td:nth-child(1)")).getText());

        }
        return map;
    }

    public void clickOnEditProperty() {
        FunctionLibrary.click(propertyEditIcon.get(0), propertyEditIconName);
    }

    public String getPageContentForPropertyNotFound() {
        return FunctionLibrary.retrieveText(noResultFound, noResultFoundName);
    }

    public String getPageContentOfBlankPropertiesListingPage() {
        return FunctionLibrary.retrieveText(blankPropertiesPage, blankPropertiesPageName);
    }

    public void clickOnProductGroupstab() {
        FunctionLibrary.click(productGroupsTab, productGroupsTabName);
    }

    public void clickOnPropertiesTab() {
        FunctionLibrary.click(propertiesTab, propertiesTabName);
    }

    public void waitForPropertyListingPageToLoad() throws InterruptedException {
       // FunctionLibrary.waitForElementToLoad(propertyListingHeaderClass, propertyListingHeaderClassName);
        FunctionLibrary.waitForElementToLoad(createPropertyButton, createPropertyButtonName);
        FunctionLibrary.waitForPageToLoad();
    }



}
