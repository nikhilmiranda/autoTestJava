package core.pages.pim.ManageNetwork.Exports.ScheduledExports.ManageScheduledExports;

import lib.BrowserInitializer;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

public class Schedule_Page extends FluentPage {



    @FindBys( {
            @FindBy(how = How.CSS, using = ".modal-content"),
            @FindBy(how = How.NAME, using = "exportName")
    } )
    FluentWebElement exportNameInputBox;

    @FindBy(how = How.CSS, using = ".modal-body .drop-down-button:nth-child(1)")
    FluentWebElement partnerDropdownIcon;

    @FindBy(how = How.CSS, using = ".element-adapter .drop-down-button")
    FluentWebElement adapterDropDownIcon;

    @FindBy(how = How.CSS, using = ".modal-body .element-sftp .drop-down-button")
    FluentWebElement selectSFTPDropdownIcon;

    @FindBy(how = How.CSS, using = ".drop-down-search-container input")
    FluentWebElement dropdownSearchInputBox;

    @FindBy(how = How.CSS, using = ".expandable-item:nth-child(1) .expand-toggle")
    FluentWebElement expandSystemAdapterIcon;

    @FindBy(how = How.CSS, using = ".expandable-item:nth-child(2) .expand-toggle")
    FluentWebElement expandCustomAdapterIcon;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> dropdownValuesList;

    @FindBy(how = How.NAME, using = "format-CSV")
    FluentWebElement csvCheckbox;

    @FindBy(how = How.NAME, using = "format-XLSX")
    FluentWebElement xlsCheckbox;

    @FindBy(how = How.NAME, using = "format-XML")
    FluentWebElement xmlCheckbox;

    @FindBy(how = How.NAME, using = "format-JSON")
    FluentWebElement jsonCheckbox;

    @FindBy(how = How.NAME, using = "medium-EMAIL")
    FluentWebElement emailCheckBoxButton;

    @FindBy(how = How.NAME, using = "medium-SFTP")
    FluentWebElement sftpCheckBoxButton;

    @FindBy(how = How.NAME, using = "emailIds")
    FluentWebElement emailInputBox;

    @FindBy(how = How.CSS, using = ".modal-buttons .secondary-btn")
    FluentWebElement exportWithoutReadinessCheckButton;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement exportWithReadinessCheckButton;

    @FindBy(how = How.CSS, using = ".element-repeat_every_minutes .caret")
    FluentWebElement repeatFrequencyDropDownIcon;

    @FindBy(how = How.CSS, using = ".element-repeat_unit .caret")
    FluentWebElement repeatUnitDropDownIcon;

    @FindBy(how = How.CSS, using = ".element-end_type .caret")
    FluentWebElement repeatEndDropDownIcon;

    @FindBy(how = How.NAME, using = "ends_after_iteration")
    FluentWebElement endAfterIteration;


    String adapterDropdownIconName = "Adapter Dropdon Icon";
    String selectSFTPDropdownIconName = "Select SFTP Dropdown Icon";
    String dropdownSearchInputBoxName = "Dropdown Search InputBox";
    String expandCustomAdapterIconName = "Expand CustomA dapter Icon";
    String dropdownValuesListName = "Dropdown Values List";
    String csvCheckboxName = "CSV Checkbox";
    String xlsCheckboxName = "XLS Checkbox";
    String xmlCheckboxName = "XML Checkbox";
    String jsonCheckboxName = "JSON Checkbox";
    String emailOptionButtonName = "Email Option Button";
    String sftpOptionButtonName = "Sftp Option Button";
    String emailInputBoxName = "Email InputBox";
    String repeatFrequencyDropdownIconName = "Repeat Frequency Dropdown Icon";
    String repeatUnitDropdownIconName = "Repeat Unit Dropdown Icon";
    String repeatEndDropdownIconName = "Repeat End Dropdown Icon";
    String endAfterIterationName = "End After Iteration";

    public void setScheduler(String adapterName, Boolean csvValue, Boolean xlsValue,Boolean xmlValue,Boolean jsonValue, Boolean allowEmail, Boolean allowSFTP, String emailIds, String sftpName) throws InterruptedException {
        if(adapterName != null) {
            FunctionLibrary.click(adapterDropDownIcon, adapterDropdownIconName);
            searchInDropDown(adapterName);
            FunctionLibrary.click(expandCustomAdapterIcon, expandCustomAdapterIconName);
            FunctionLibrary.selectDropDownValueByName(adapterName, dropdownValuesList, dropdownValuesListName);
        }
        if(csvValue == true) {
            FunctionLibrary.click(csvCheckbox, csvCheckboxName);
        }
        if(xlsValue == true) {
            FunctionLibrary.click(xlsCheckbox, xlsCheckboxName);
        }
        if(xmlValue == true) {
            FunctionLibrary.click(xmlCheckbox, xmlCheckboxName);
        }
        if(jsonValue == true) {
            FunctionLibrary.click(jsonCheckbox, jsonCheckboxName);
        }
        ((JavascriptExecutor) BrowserInitializer.getDriver()).executeScript("arguments[0].scrollIntoViewIfNeeded(true);", BrowserInitializer.getDriver().findElement(By.name("medium-EMAIL")));
        if(allowEmail == true) {
            FunctionLibrary.click(emailCheckBoxButton, emailOptionButtonName);
            FunctionLibrary.input(emailInputBox, emailInputBoxName, emailIds);
        }
        if(allowSFTP == true) {
            FunctionLibrary.click(sftpCheckBoxButton, sftpOptionButtonName);
            FunctionLibrary.click(selectSFTPDropdownIcon, selectSFTPDropdownIconName);
            FunctionLibrary.selectDropDownValueByName(sftpName, dropdownValuesList, dropdownValuesListName);
        }
    }

    public void setScheduleFrequency(String frequency, String unit, String end, String endAfterIterationVal) {
        FunctionLibrary.click(repeatUnitDropDownIcon, repeatUnitDropdownIconName);
        FunctionLibrary.selectDropDownValueByName(unit, dropdownValuesList, dropdownValuesListName);
        FunctionLibrary.click(repeatFrequencyDropDownIcon, repeatFrequencyDropdownIconName);
        FunctionLibrary.selectDropDownValueByName(frequency, dropdownValuesList, dropdownValuesListName);
        FunctionLibrary.click(repeatEndDropDownIcon, repeatEndDropdownIconName);
        FunctionLibrary.selectDropDownValueByName(end, dropdownValuesList, dropdownValuesListName);
        if(end == "After specific occurences") {
            FunctionLibrary.input(endAfterIteration, endAfterIterationName, endAfterIterationVal);
        }
    }

    public void searchInDropDown(String name) throws InterruptedException {
        FunctionLibrary.input(dropdownSearchInputBox, dropdownSearchInputBoxName, name);
        Thread.sleep(1000);
    }

}
