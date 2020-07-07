package core.pages.pim.Tasks.Tasks_Page;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ManageTask_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".step-2 .element-searchVal input")
    FluentWebElement propertySearch;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement productSearch;

    @FindBy(how = How.CSS, using = ".pim-default-filter-table table tbody tr input")
    FluentWebElement selectPropertyCheckBox;

    @FindBy(how = How.CSS, using = "table tbody tr input")
    FluentWebElement selectCheckBox;

    @FindBy(how = How.CSS, using = ".margin-top-10 table tbody tr")
    FluentList<FluentWebElement> propertiesSelected;

    @FindBy(how = How.CSS, using = ".drop-down-text .caret")
    FluentWebElement selectTaskTemplateIcon;


    @FindBy(how = How.CSS, using = ".selected-assignees div")
    FluentWebElement selectedTaskTemplateAssignee;

    @FindBy(how = How.CSS, using = "table tbody tr")
    FluentList<FluentWebElement> selectedTaskTemplateProperties;

    @FindBy(how = How.CSS, using = "table tbody tr td:nth-child(1)")
    FluentList<FluentWebElement>  taskTemplateList;

    @FindBy(how = How.CSS, using = "table tbody tr input")
    FluentWebElement  checkBoxAgainstProduct;

    @FindBy(how = How.CSS, using = ".btn.primary-btn")
    FluentWebElement nextTab;

    @FindBy(how = How.CSS, using = ".btn.primary-btn")
    FluentWebElement saveAndAssignTask;

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement templateNameInputBox;

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement taskNameInputBox;

    @FindBy(how = How.CSS, using = ".pills-add-icon")
    FluentWebElement addTaskIcon;

    @FindBy(how = How.CSS, using = ".drop-down-search-container input")
    FluentWebElement memberOrRoleSearchInputBox;

    @FindBy(how = How.CSS, using = ".expandable-item:nth-child(1) .expand-toggle")
    FluentWebElement expandRoles;

    @FindBy(how = How.CSS, using = ".expandable-item:nth-child(2) .expand-toggle")
    FluentWebElement expandMembers;

    @FindBy(how = How.CSS, using = ".expandable-item:nth-child(1) li")
    FluentWebElement selectRole;

    @FindBy(how = How.CSS, using = ".expandable-item:nth-child(2) li")
    FluentWebElement selectMember;

    @FindBy(how = How.CSS, using = ".btn.primary-btn")
    FluentWebElement createTemplateButton;

    @FindBy(how = How.CSS, using = ".list-item ")
    FluentList<FluentWebElement> taskTemplateDropDownList;

    @FindBy(how = How.CSS, using = ".drop-down-button .caret")
    FluentWebElement taskTemplateDropDown;

    @FindBy(how = How.CSS, using = ".message-box.success")
    FluentWebElement taskCreationSuccessMsg;

    public static By spinLoader = By.cssSelector(".ajax-loader");


    String propertySearchName = "Property Search";
    String selectPropertyCheckBoxName = "Select Property CheckBox";
    String countOfPropertiesSelectedName= "Properties Selected";
    String selectTaskTemplateIconName = "Select Task Template Icon";
    String taskTemplateListName = "taskTemplateList";
    String checkBoxAgainstProductName = "CheckBox Against Product";
    String selectedTaskTemplateAssigneeName = "Selected TaskTemplate Assignee";
    String selectedTaskTemplatePropertiesName = "Selected TaskTemplate Properties";
    String nextTabName = "NextTab";
    String saveAndAssignTaskName = "Save And Assign Task";
    String templateNameInputBoxName = "Template Name InputBox";
    String taskNameInputBoxName = "Task Name InputBox";
    String addTaskIconName = "Add Task Icon";
    String memberOrRoleSearchInputBoxName = "Member Or Role Search InputBox";
    String expandRolesName = "Expand Roles";
    String expandMembersName = "Expand Members";
    String selectRoleName = "Select Role";
    String selectMemberName = "Select Member";
    String createTemplateButtonName = "Create Template Button";
    String taskTemplateDropDownListName = "Task Template DropDown List";
    String taskTemplateDropDownName = "Task Template DropDown";
    String taskCreationSuccessMsgName = "Task Creation Success Msg";
    String spinLoaderName = "Spin Loader";




    public void addPropertyToTemplate(String propertyName) {
        FunctionLibrary.input(propertySearch, propertySearchName, propertyName);
        FunctionLibrary.click(selectPropertyCheckBox, selectPropertyCheckBoxName);
    }

    public void addProductToTask(String productName) throws InterruptedException {
        FunctionLibrary.input(productSearch, propertySearchName, productName);
        Thread.sleep(1000);
        FunctionLibrary.click(checkBoxAgainstProduct, checkBoxAgainstProductName);
    }

    public void addPropertyToTask(String propertyName) throws InterruptedException {
        FunctionLibrary.input(propertySearch, propertySearchName, propertyName);
        Thread.sleep(1000);
        FunctionLibrary.click(selectPropertyCheckBox, selectPropertyCheckBoxName);
    }

    public void selectTaskTemplate(String templateName){
        FunctionLibrary.click(taskTemplateDropDown, taskTemplateDropDownName);
        FunctionLibrary.selectDropDownValueByName(templateName,taskTemplateDropDownList, taskTemplateDropDownListName);
    }

    public String getTheAssigneesLinkedWithTaskTemplate() {
        return FunctionLibrary.retrieveText(selectedTaskTemplateAssignee, selectedTaskTemplateAssigneeName);
    }

    public List<String> getThePropertiesLinkedWithTaskTemplate() {
        return FunctionLibrary.retrieveTexts(selectedTaskTemplateProperties, selectedTaskTemplatePropertiesName);
    }

    public List<String> getTheCountOfPropertiesAddedTotemplate() {
       return FunctionLibrary.retrieveTexts(propertiesSelected, countOfPropertiesSelectedName);
    }

    public void clickOnNext() {
        FunctionLibrary.scrollToTop();
        FunctionLibrary.click(nextTab, nextTabName);
    }

    public void enterTemplateName(String templateName) {
        FunctionLibrary.input(templateNameInputBox, templateNameInputBoxName, templateName);
    }

    public void assignTaskToMember(String memberName) {
        FunctionLibrary.click(addTaskIcon, addTaskIconName);
        FunctionLibrary.input(memberOrRoleSearchInputBox, memberOrRoleSearchInputBoxName, memberName);
        FunctionLibrary.click(expandMembers, expandMembersName);
        FunctionLibrary.click(selectMember, selectMemberName);
    }

    public void createTemplate() {
        FunctionLibrary.click(createTemplateButton, createTemplateButtonName);
    }

    public void setSelectTaskTemplate(String taskTemplate) {
        for(FluentWebElement tt : taskTemplateList){
            if(FunctionLibrary.retrieveText(tt, taskTemplateListName).equalsIgnoreCase(taskTemplate)){
                FunctionLibrary.click(tt, taskTemplateListName );
            }
        }
    }

    public void enterTaskName(String taskName) {
        FunctionLibrary.input(taskNameInputBox, taskNameInputBoxName, taskName);
    }

    public void clickOnSaveAndAssignTask() {
        FunctionLibrary.click(saveAndAssignTask, saveAndAssignTaskName);
    }

    public void waitForManageTaskUsingTemplatePageToLoad() throws InterruptedException {
        FunctionLibrary.waitForLoaderToDissapear(spinLoader, spinLoaderName);
        FunctionLibrary.waitForElementToBeClickable(taskTemplateDropDown, taskTemplateDropDownName);
        FunctionLibrary.waitForPageToLoad();
    }

}
