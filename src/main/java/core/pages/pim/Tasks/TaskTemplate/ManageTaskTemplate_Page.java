package core.pages.pim.Tasks.TaskTemplate;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ManageTaskTemplate_Page extends FluentPage {


    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchInputBox;

    @FindBy(how = How.CSS, using = "table tbody tr td:nth-child(2)")
    FluentList<FluentWebElement> properties;

    @FindBy(how = How.CSS, using = "table tbody tr td:nth-child(1)")
    FluentList<FluentWebElement> propertiesInCreatedTask;

    @FindBy(how = How.CSS, using = "table tbody tr input")
    FluentWebElement selectCheckBox;

    @FindBy(how = How.CSS, using = ".task-sub-text")
    FluentWebElement countOfPropertiesSelected;

    @FindBy(how = How.CSS, using = ".pagination-dropdown .caret")
    FluentWebElement paginationDropdown;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(4)")
    FluentWebElement paginationDropdownValue;

    @FindBy(how = How.CSS, using = ".btn.primary-btn")
    FluentWebElement nextTab;

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement templateNameInputBox;

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

    @FindBy(how = How.CSS, using = ".secondary-btn.float-right")
    FluentWebElement addPropertiesButton;

    @FindBy(how = How.CSS, using = ".modal-body .element-searchVal input")
    FluentWebElement modalSearchInputBox;

    @FindBy(how = How.CSS, using = ".modal-body table tbody tr td:nth-child(2)")
    FluentList<FluentWebElement> modalProperties;

    @FindBy(how = How.CSS, using = ".modal-body table tbody tr input")
    FluentWebElement modalSelectCheckBox;

    @FindBy(how = How.CSS, using = ".modal-body .task-sub-text")
    FluentWebElement modalCountOfPropertiesSelected;

    @FindBy(how = How.CSS, using = ".modal-body .pagination-dropdown .caret")
    FluentWebElement modalPaginationDropdown;

    @FindBy(how = How.CSS, using = ".modal-body .list-item:nth-child(4)")
    FluentWebElement modalPaginationDropdownValue;

    @FindBy(how = How.CSS, using = ".modal-body .btn.primary-btn")
    FluentWebElement addSelectedPropertiesButton;

    public static By spinLoader = By.cssSelector(".ajax-loader");

    String searchInputBoxName = "Search";
    String propertiesName = "Properties";
    String propertiesInCreatedTaskName = "Properties In Created Task";
    String paginationDropdownName = "Pagination Dropdown";
    String paginationDropdownValueName = "Pagination Dropdown Value";
    String selectCheckBoxName = "Select Property CheckBox";
    String countOfPropertiesSelectedName= "Count Of Properties Selected";
    String nextTabName = "NextTab";
    String templateNameInputBoxName = "Template Name InputBox";
    String addTaskIconName = "Add Task Icon";
    String memberOrRoleSearchInputBoxName = "Member Or Role Search InputBox";
    String expandRolesName = "Expand Roles";
    String expandMembersName = "Expand Members";
    String selectRoleName = "Select Role";
    String selectMemberName = "Select Member";
    String createTemplateButtonName = "Create Template Button";
    String addPropertiesButtonName = "Add Properties Button";
    String modalSearchInputBoxName = "Modal Search";
    String modalPropertiesName = "Modal Properties";
    String modalPaginationDropdownName = "Modal Pagination Dropdown";
    String modalPaginationDropdownValueName = "Modal Pagination Dropdown Value";
    String modalSelectCheckBoxName = "Modal Select Property CheckBox";
    String modalCountOfPropertiesSelectedName= "Modal Count Of Properties Selected";
    String addSelectedPropertiesButtonName = "Modal Add Selected Properties Button";
    String spinLoaderName = "Spin Loader";


    public void addProductToTask(String productName) {
        FunctionLibrary.input(searchInputBox, searchInputBoxName, productName);
        FunctionLibrary.click(selectCheckBox, selectCheckBoxName);
    }

    public void selectPaginationWith100Values() {
        if(FunctionLibrary.isElementDisplayed(paginationDropdown, paginationDropdownName)){
            FunctionLibrary.click(paginationDropdown, paginationDropdownName);
            FunctionLibrary.click(paginationDropdownValue, paginationDropdownValueName);
        }
    }

    public void addPropertyToTask(String propertyName) throws InterruptedException {
        FunctionLibrary.input(searchInputBox, searchInputBoxName, propertyName);
        Thread.sleep(1000);
        FunctionLibrary.click(selectCheckBox, selectCheckBoxName);
    }

    public String getTheCountOfPropertiesAddedTotemplate() {
       return FunctionLibrary.retrieveText(countOfPropertiesSelected, countOfPropertiesSelectedName);
    }

    public void clickOnNext() {
        FunctionLibrary.scrollToTop();
        FunctionLibrary.click(nextTab, nextTabName);
    }

    public void enterTemplateName(String templateName) {
        FunctionLibrary.input(templateNameInputBox, templateNameInputBoxName, templateName);
    }

    public void assignTaskToMember(String memberName) throws InterruptedException {
        FunctionLibrary.click(addTaskIcon, addTaskIconName);
        FunctionLibrary.click(expandMembers, expandMembersName);
        FunctionLibrary.input(memberOrRoleSearchInputBox, memberOrRoleSearchInputBoxName, memberName);
        Thread.sleep(1000);
        FunctionLibrary.click(selectMember, selectMemberName);
        FunctionLibrary.click(addTaskIcon, addTaskIconName);
    }

    public void createTemplate() {
        FunctionLibrary.click(createTemplateButton, createTemplateButtonName);
    }

    public List<String> getThePropertiesAvailableInTaskListingPage() {
       return FunctionLibrary.retrieveTexts(properties, propertiesName);
    }

    public List<String> getThePropertiesAvailableInCreatedTaskListingPage() {
        return FunctionLibrary.retrieveTexts(propertiesInCreatedTask, propertiesInCreatedTaskName);
    }

    public void clickOnAddProperties() {
        FunctionLibrary.click(addPropertiesButton, addPropertiesButtonName);
    }

    public void addAdditionalProperties(String propertyName) throws InterruptedException {
        FunctionLibrary.input(modalSearchInputBox, modalSearchInputBoxName, propertyName);
        Thread.sleep(1000);
        FunctionLibrary.click(modalSelectCheckBox, selectCheckBoxName);
    }

    public List<String> getThePropertiesAvailableInTaskListingModalPage() {
        return FunctionLibrary.retrieveTexts(modalProperties, modalPropertiesName);
    }

    public String getTheCountOfAdditionalPropertiesAddedToTemplate() {
        return FunctionLibrary.retrieveText(countOfPropertiesSelected, countOfPropertiesSelectedName);
    }

    public void clickOnAddSelectedProperties() {
        FunctionLibrary.click(addSelectedPropertiesButton, addSelectedPropertiesButtonName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForLoaderToDissapear(spinLoader, spinLoaderName);
       // FunctionLibrary.waitForPageToLoad();
    }

}
