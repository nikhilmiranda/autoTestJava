package core.pages.pim.ManageNetwork.Product.ProductLabels;

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

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ExportProduct_Page extends FluentPage {


    @FindBys( {
            @FindBy(how = How.CSS, using = ".modal-content"),
            @FindBy(how = How.NAME, using = "exportName")
    } )
    FluentWebElement exportNameInputBox;

    @FindBy(how = How.CSS, using = ".modal-body .drop-down-button:nth-child(1)")
    FluentWebElement partnerDropdownIcon;

    @FindBy(how = How.CSS, using = ".modal-body .element-adapter .drop-down-button")
    FluentWebElement adapterDropdownIcon;

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

    @FindBy(how = How.ID, using = "radio-medium-EMAIL")
    FluentWebElement emailOptionButton;

    @FindBy(how = How.ID, using = "radio-medium-SFTP")
    FluentWebElement sftpOptionButton;

    @FindBy(how = How.NAME, using = "email")
    FluentWebElement emailInputBox;

    @FindBy(how = How.CSS, using = ".modal-buttons .secondary-btn")
    FluentWebElement exportWithoutReadinessCheckButton;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement exportWithReadinessCheckButton;




    String exportNameInputBoxName = "Export Name InputBox";
    String partnerDropdownIconName = "Partner Dropdown Icon";
    String adapterDropdownIconName = "Adapter Dropdon Icon";
    String selectSFTPDropdownIconName = "Select SFTP Dropdown Icon";
    String dropdownSearchInputBoxName = "Dropdown Search InputBox";
    String expandSystemAdapterIconName = "Expand System Adapter Icon";
    String expandCustomAdapterIconName = "Expand CustomA dapter Icon";
    String dropdownValuesListName = "Dropdown Values List";
    String csvCheckboxName = "CSV Checkbox";
    String xlsCheckboxName = "XLS Checkbox";
    String xmlCheckboxName = "XML Checkbox";
    String jsonCheckboxName = "JSON Checkbox";
    String emailOptionButtonName = "Email Option Button";
    String sftpOptionButtonName = "Sftp Option Button";
    String emailInputBoxName = "Email InputBox";
    String exportWithoutReadinessCheckButtonName = "Export Without Readiness Check Button";
    String exportWithReadinessCheckButtonName = "Export With Readiness Check Button";




    public void createExport(String exportName, String partnerName, String adapterName, Boolean csvValue, Boolean xlsValue,Boolean xmlValue,Boolean jsonValue, Boolean allowEmail, Boolean allowSFTP, Boolean exportWithoutReadinessCheck, Boolean exportWithReadinessCheck,String emailIds, String sftpName) throws InterruptedException {
        if(exportName != null) {
            FunctionLibrary.input(exportNameInputBox, exportNameInputBoxName, exportName);
        }
        if(partnerName != null) {
            FunctionLibrary.click(partnerDropdownIcon, partnerDropdownIconName);
            searchInDropDown(partnerName);
            FunctionLibrary.selectDropDownValueByName(partnerName, dropdownValuesList, dropdownValuesListName);
        }
        if(adapterName != null) {
            FunctionLibrary.click(adapterDropdownIcon, adapterDropdownIconName);
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
        ((JavascriptExecutor) BrowserInitializer.getDriver()).executeScript("arguments[0].scrollIntoViewIfNeeded(true);", BrowserInitializer.getDriver().findElement(By.id("radio-medium-EMAIL")));
        if(allowEmail == true) {
            FunctionLibrary.click(emailOptionButton, emailOptionButtonName);
            FunctionLibrary.input(emailInputBox, emailInputBoxName, emailIds);
        }
        if(allowSFTP == true) {
            FunctionLibrary.click(sftpOptionButton, sftpOptionButtonName);
            FunctionLibrary.click(selectSFTPDropdownIcon, selectSFTPDropdownIconName);
            FunctionLibrary.selectDropDownValueByName(sftpName, dropdownValuesList, dropdownValuesListName);
        }
        if(exportWithoutReadinessCheck == true) {
            FunctionLibrary.click(exportWithoutReadinessCheckButton, exportWithoutReadinessCheckButtonName);
        }
        if(exportWithReadinessCheck == true) {
            FunctionLibrary.click(exportWithReadinessCheckButton, exportWithReadinessCheckButtonName);
        }
    }

    public void configureExportNode(String partnerName, String adapterName, Boolean csvValue, Boolean xlsValue,Boolean xmlValue,Boolean jsonValue, Boolean allowEmail, Boolean allowSFTP,String emailIds, String sftpName) throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        if(partnerName != null) {
            FunctionLibrary.click(partnerDropdownIcon, partnerDropdownIconName);
            searchInDropDown(partnerName);
            FunctionLibrary.selectDropDownValueByName(partnerName, dropdownValuesList, dropdownValuesListName);
        }
        if(adapterName != null) {
            FunctionLibrary.click(adapterDropdownIcon, adapterDropdownIconName);
            searchInDropDown(adapterName);
            FunctionLibrary.click(expandCustomAdapterIcon, expandCustomAdapterIconName);
            FunctionLibrary.selectDropDownValueByName(adapterName, dropdownValuesList, dropdownValuesListName);
        }
        getDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);

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
        ((JavascriptExecutor) BrowserInitializer.getDriver()).executeScript("arguments[0].scrollIntoViewIfNeeded(true);", BrowserInitializer.getDriver().findElement(By.id("radio-medium-EMAIL")));
        if(allowEmail == true) {
            FunctionLibrary.click(emailOptionButton, emailOptionButtonName);
            FunctionLibrary.input(emailInputBox, emailInputBoxName, emailIds);
        }
        if(allowSFTP == true) {
            FunctionLibrary.click(sftpOptionButton, sftpOptionButtonName);
            FunctionLibrary.click(selectSFTPDropdownIcon, selectSFTPDropdownIconName);
            FunctionLibrary.selectDropDownValueByName(sftpName, dropdownValuesList, dropdownValuesListName);
        }
    }

    public void searchInDropDown(String name) throws InterruptedException {
        FunctionLibrary.input(dropdownSearchInputBox, dropdownSearchInputBoxName, name);
        Thread.sleep(1000);
    }

}
