package core.pages.pim.MyOrganisation.Roles;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;
import core.pages.pim.MyOrganisation.Roles.ManageRole_Page;

public class RolesListing_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".secondary-btn")
    FluentWebElement createRoleButton;

    @FindBy(how = How.NAME, using = "roleName")
    FluentWebElement roleNameInputBox;

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement proceedButton;

    @FindBy(how = How.CSS, using = ".cell-container")
    FluentList<FluentWebElement> tableRows;

    @FindBy(how = How.CSS, using = ".cell-heading")
    FluentList<FluentWebElement> rolesName;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement roleSearchInputBox;

    @FindBy(how = How.CSS, using = "table tbody tr .glyphicon-eye-open")
    FluentWebElement roleEditIcon;


    String createRoleButtonName = "Create Role Button";
    String roleNameInputBoxName = "Role Name InputBox";
    String proceedButtonName = "Proceed Button";
    String tableRowsName = "Table Rows Name";
    String rolesNameName = "Roles Name";
    String roleSearchInputBoxName = "Role Name Search InputBox";
    String roleEditIconName = "Role Edit Icon";

    public List getUsersAssignedToARole(String Role) {
        List users = new ArrayList();
        for(FluentWebElement ele : tableRows){
            if(ele.getElement().findElement(By.cssSelector(".cell-heading")).getText().contains(Role)){
                users.add(ele.find(".cell-content ul li").getTexts());
            }
        }
        return users;
    }

    public Boolean isCreateRoleButtonDisabled() {
        return FunctionLibrary.isElementPresent(createRoleButton, createRoleButtonName);
    }

    public List<String> getRoles() {
        return FunctionLibrary.retrieveTexts(rolesName, rolesNameName);
    }

    public void createRole(String roleName) {
        FunctionLibrary.click(createRoleButton, createRoleButtonName);
        FunctionLibrary.input(roleNameInputBox, roleNameInputBoxName, roleName);
        FunctionLibrary.click(proceedButton, proceedButtonName);
    }

    public void searchRole(String roleName) {
        FunctionLibrary.input(roleSearchInputBox, roleNameInputBoxName, roleName);
    }

    public void clickOnRoleEditIcon(String roleName) throws InterruptedException {
        searchRole(roleName);
        Thread.sleep(3000);
        FunctionLibrary.click(roleEditIcon, roleEditIconName);
    }

    public void waitForRoleListingPageToLoad() throws InterruptedException {
        FunctionLibrary.waitForElementToBeClickable(roleEditIcon, roleEditIconName);
        FunctionLibrary.waitForElementToLoad(roleSearchInputBox, roleNameInputBoxName);
        FunctionLibrary.waitForPageToLoad();
    }

    public Boolean isCreateRoleButtonVisible() {
        return FunctionLibrary.isElementDisplayed(createRoleButton, createRoleButtonName);
    }
}
