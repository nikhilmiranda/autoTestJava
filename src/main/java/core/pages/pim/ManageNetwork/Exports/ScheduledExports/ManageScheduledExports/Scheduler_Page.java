package core.pages.pim.ManageNetwork.Exports.ScheduledExports.ManageScheduledExports;

import core.components.pim.StaticDropDown;
import core.components.pim.backToList;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Scheduler_Page extends FluentPage {


    @Page
    core.components.pim.backToList backToList;

    @Page
    StaticDropDown staticDropDown;


    @FindBy(how = How.CSS, using = ".secondary-btn .start-icon")
    FluentWebElement startScheduleButton;

    @FindBy(how = How.CSS, using = ".schedule-import-mapping-adapter .drop-down-button .caret")
    FluentWebElement adapterDropDownIcon;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> dropDownList;

    @FindBy(how = How.CSS, using = ".element-repeat_every_minutes .caret")
    FluentWebElement repeatFrequencyDropDownIcon;

    @FindBy(how = How.CSS, using = ".element-repeat_unit .caret")
    FluentWebElement repeatUnitDropDownIcon;

    @FindBy(how = How.CSS, using = ".element-end_type .caret")
    FluentWebElement repeatEndDropDownIcon;

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

    public void selectAdapter(String adapterName)  {
        staticDropDown.clickOnDDListIcon(2);
        staticDropDown.selectStaticDropDownValue(adapterName);
    }

    public void selectSFTPFiles(Boolean csvValue, Boolean xlsValue, Boolean xmlValue, Boolean jsonValue, String fileName) {

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
        FunctionLibrary.click(repeatUnitDropDownIcon, startScheduleButtonName);
        staticDropDown.selectStaticDropDownValue(unit);
        FunctionLibrary.click(repeatFrequencyDropDownIcon, startScheduleButtonName);
        staticDropDown.selectStaticDropDownValue(frequency);
        FunctionLibrary.click(repeatEndDropDownIcon, startScheduleButtonName);
        staticDropDown.selectStaticDropDownValue(end);
        FunctionLibrary.input(endAfterIterationInputBox, endAfterIterationInputBoxName, count);
    }
}
