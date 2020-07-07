package core.pages.pim.Imports.ScheduledImports.ManageScheduledImports;

import core.components.pim.PrimaryButton;
import core.components.pim.StaticDropDown;
import core.components.pim.backToList;
import lib.FunctionLibrary;
import org.apache.bcel.generic.FNEG;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SchedulerConfiguration_Page extends FluentPage {


    @Page
    backToList backToList;

    @Page
    StaticDropDown staticDropDown;


    @FindBy(how = How.CSS, using = ".secondary-btn .start-icon")
    FluentWebElement startScheduleButton;

    @FindBy(how = How.CSS, using = ".schedule-import-mapping-adapter .drop-down-button .caret")
    FluentWebElement adapterDropDownIcon;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> dropdownList;

    @FindBy(how = How.CSS, using = ".element-repeat_every_minutes .caret")
    FluentWebElement repeatFrequencyDropdownIcon;

    @FindBy(how = How.CSS, using = ".element-repeat_unit .caret")
    FluentWebElement repeatUnitDropdownIcon;

    @FindBy(how = How.CSS, using = ".element-end_type .caret")
    FluentWebElement repeatEndDropdownIcon;

    @FindBy(how = How.NAME, using = "ends_after_iteration")
    FluentWebElement endAfterIterationInputBox;

    @FindBy(how = How.NAME, using = "contains")
    FluentWebElement containsInputBox;

    @FindBy(how = How.NAME, using = "import_csv")
    FluentWebElement csvCheckbox;

    @FindBy(how = How.NAME, using = "import_xls")
    FluentWebElement xlsCheckbox;

    @FindBy(how = How.NAME, using = "import_xml")
    FluentWebElement xmlCheckbox;

    @FindBy(how = How.NAME, using = "import_json")
    FluentWebElement jsonCheckbox;

    @FindBy(how = How.ID, using = "radio-select_all-like")
    FluentWebElement selectSpecificFilesRadio;

    String startScheduleButtonName = "Start Schedule Button";
    String selectIndividualProductsButtonName = "Select Individual Products Button";
    String productsSectionName = "Products Section";
    String viewLinkAgainstProductSectionName = "View Link Against Product Section";
    String endAfterIterationInputBoxName = "End After Iteration InputBox";
    String adapterDropDownIconName = "Adapter DropDown Icon";
    String containsInputBoxName = "Contains Input Box";
    String csvCheckboxName = "CSV Checkbox";
    String xlsCheckboxName = "XLS Checkbox";
    String xmlCheckboxName = "XML Checkbox";
    String jsonCheckboxName = "JSON Checkbox";
    String selectSpecificFilesRadioName = "Select Specific Files Radio Button";


    public void waitForPageToLoad()  {
        backToList.waitForLinkToBeClickable();
    }

    public void selectSFTP(String sftpName)  {
        staticDropDown.clickOnDDListIcon(1);
        staticDropDown.selectStaticDropDownValue(sftpName);
    }

    public void selectAdapter(String adapterName)  {
        FunctionLibrary.click(adapterDropDownIcon, adapterDropDownIconName);
        staticDropDown.selectStaticDropDownValue(adapterName);

    }

    public void selectSFTPFiles(Boolean csvValue, Boolean xlsValue, Boolean xmlValue, Boolean jsonValue, String fileName) {
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

    public void createScheduler(String frequency, String unit, String end, String count) {
        FunctionLibrary.click(repeatUnitDropdownIcon, startScheduleButtonName);
        staticDropDown.selectStaticDropDownValue(frequency);
        FunctionLibrary.click(repeatFrequencyDropdownIcon, startScheduleButtonName);
        staticDropDown.selectStaticDropDownValue(unit);
        FunctionLibrary.click(repeatEndDropdownIcon, startScheduleButtonName);
        staticDropDown.selectStaticDropDownValue(end);
        FunctionLibrary.input(endAfterIterationInputBox, endAfterIterationInputBoxName, count);
        //FunctionLibrary.input(containsInputBox, containsInputBoxName, count);
    }


}
