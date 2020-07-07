package core.pages.pim.WorkFlow.Nodes;

import core.components.pim.PrimaryButton;
import core.components.pim.StaticDropDown;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

public class ImportNode_Page extends FluentPage {

    @Page
    StaticDropDown staticDropDown;

    @Page
    PrimaryButton primaryButton;

    @FindBy(how = How.CSS, using = ".drop-down-button .caret")
    FluentList<FluentWebElement> staticDDIcon;

    @FindBy(how = How.NAME, using = "contains")
    FluentWebElement containsInputBox;

    @FindBy(how = How.NAME, using = "type_csv")
    FluentWebElement csvCheckbox;

    @FindBy(how = How.NAME, using = "type_xls")
    FluentWebElement xlsCheckbox;

    @FindBy(how = How.NAME, using = "type_xml")
    FluentWebElement xmlCheckbox;

    @FindBy(how = How.NAME, using = "type_json")
    FluentWebElement jsonCheckbox;

    @FindBy(how = How.ID, using = "radio-selectAll-like")
    FluentWebElement selectSpecificFilesRadio;

    String staticDDIconName = "Adapter DropDown Icon";
    String containsInputBoxName = "Contains Input Box";
    String csvCheckboxName = "CSV Checkbox";
    String xlsCheckboxName = "XLS Checkbox";
    String xmlCheckboxName = "XML Checkbox";
    String jsonCheckboxName = "JSON Checkbox";
    String selectSpecificFilesRadioName = "Select Specific Files Radio Button";

    public void selectSFTPFiles(String source, String sftpName, String importAdapterName, Boolean csvValue, Boolean xlsValue, Boolean xmlValue, Boolean jsonValue, String fileName) {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        FunctionLibrary.click(staticDDIcon.get(1), staticDDIconName);
        staticDropDown.selectStaticDropDownValue(source);
        FunctionLibrary.click(staticDDIcon.get(2), staticDDIconName);
        staticDropDown.selectStaticDropDownValue(sftpName);
        FunctionLibrary.click(staticDDIcon.get(3), staticDDIconName);
        staticDropDown.selectStaticDropDownValue(importAdapterName);
        getDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
        FunctionLibrary.click(selectSpecificFilesRadio, selectSpecificFilesRadioName);
        FunctionLibrary.input(containsInputBox, containsInputBoxName, fileName);

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
    }

    public void clickOnAddNode() {
        primaryButton.clickOnPrimaryModalButton();
    }

}
