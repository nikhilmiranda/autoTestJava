package core.pages.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class AdvanceFilter_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".advanced-filter-item fieldset:nth-child(1) .caret")
    FluentWebElement labelDropdown;

    @FindBy(how = How.CSS, using = ".advanced-filter-item fieldset:nth-child(2) .caret")
    FluentWebElement valueDropdown;

    @FindBy(how = How.CSS, using = ".advanced-filter-item fieldset:nth-child(3) .caret")
    FluentWebElement operatorDropdown;

    @FindBy(how = How.CSS, using = ".adv-filters-values .caret")
    FluentWebElement optionDropdown;

    @FindBy(how = How.CSS, using = ".advanced-filter-item:nth-child(2) fieldset:nth-child(1) .caret")
    FluentWebElement label1Dropdown;

    @FindBy(how = How.CSS, using = ".advanced-filter-item:nth-child(2) fieldset:nth-child(2) .caret")
    FluentWebElement value1Dropdown;

    @FindBy(how = How.CSS, using = ".advanced-filter-item:nth-child(2) fieldset:nth-child(3) .caret")
    FluentWebElement operator1Dropdown;

    @FindBy(how = How.CSS, using = ".adv-filters-values:nth-child(2) .caret")
    FluentWebElement option1Dropdown;

    @FindBy(how = How.CSS, using = ".adv-filters-values input")
    FluentWebElement filterValueInputBox;

    @FindBy(how = How.CSS, using = ".drop-down-search-container:nth-child(2) input")
    FluentWebElement dropdownSearch1InputBox;

    @FindBy(how = How.CSS, using = ".drop-down-search-container input")
    FluentWebElement dropdownSearchInputBox;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> dropdownList;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentWebElement dropdownItem;

    @FindBy(how = How.CSS, using = ".glyphicon-plus-sign")
    FluentWebElement filterAddButton;

    @FindBy(how = How.CSS, using = ".glyphicon-minus-sign:nth-child(1)")
    FluentWebElement filterRemoveButton;

    @FindBy(how = How.CSS, using = ".glyphicon-minus-sign:nth-child(2)")
    FluentWebElement filterRemoveButton2;


    String labelDropdownName = "Label Dropdown";
    String valueDropdownName = "Value Dropdown";
    String operatorDropdownName = "Operator Dropdown";
    String optionDropdownName = "Option Dropdown";
    String filterValueInputBoxName = "Filter Value InputBox";
    String dropdownSearchInputBoxName = "Dropdown Search InputBox";
    String dropdownListName = "Dropdown List";
    String dropdownItemName = "Dropdown Item";
    String filterAddButtonName = "Filter Add Button";
    String filterRemoveButtonName = "Filter Remove Button";
    String filterRemoveButton2Name = "Filter Remove Button2";


    public void clickOnLabelDropdown() {
        FunctionLibrary.click(labelDropdown,labelDropdownName);
    }

    public void clickOnValueDropdown() {
        FunctionLibrary.click(valueDropdown,valueDropdownName);
    }

    public void clickOnOperatorDropdown() {
        FunctionLibrary.click(operatorDropdown,operatorDropdownName);
    }

    public void clickOnOptionDropdown() {
        FunctionLibrary.click(optionDropdown,optionDropdownName);
    }

    public void clickOnDropdownOption() {
        FunctionLibrary.click(dropdownItem,dropdownItemName);
    }

    public void clickOnLabel1Dropdown() {
        FunctionLibrary.click(label1Dropdown,labelDropdownName);
    }

    public void clickOnValue1Dropdown() {
        FunctionLibrary.click(value1Dropdown,valueDropdownName);
    }

    public void clickOnOperator1Dropdown() {
        FunctionLibrary.click(operator1Dropdown,operatorDropdownName);
    }

    public void clickOnOption1Dropdown() {
        FunctionLibrary.click(option1Dropdown,optionDropdownName);
    }

    public void clickOnDropdown1Option() {
        FunctionLibrary.click(dropdownItem,dropdownItemName);
    }

    public void enterFilterValue(String value) {
        FunctionLibrary.input(filterValueInputBox,filterValueInputBoxName, value);
    }

    public void searchOptionValueInDropdown(String optionValue) throws InterruptedException {
        FunctionLibrary.input(dropdownSearchInputBox,dropdownSearchInputBoxName, optionValue);
        Thread.sleep(1000);
    }

    public void addNewFilterRow() {
        FunctionLibrary.click(filterAddButton,filterAddButtonName);
    }

    public void removeFilterRow() {
        FunctionLibrary.click(filterRemoveButton,filterRemoveButtonName);
    }

    public void remove2FilterRow() {
        FunctionLibrary.click(filterRemoveButton2,filterRemoveButton2Name);
    }

    public void selectDropdownValue(String optionValue) {
        FunctionLibrary.selectDropDownValueByName(optionValue, dropdownList, dropdownListName);
    }

    public List<String> getAllTheDropdownListValue() {
        return FunctionLibrary.retrieveTexts(dropdownList,dropdownListName);
    }

    public Boolean isAdvanceFilterOpened() {
        return (FunctionLibrary.isElementPresent(filterAddButton, filterAddButtonName) && FunctionLibrary.isElementPresent(labelDropdown, labelDropdownName));
    }

    public void waitForPageToLoad() {
        FunctionLibrary.isElementPresent(filterAddButton, filterAddButtonName);
        FunctionLibrary.isElementPresent(labelDropdown, labelDropdownName);
    }

}
