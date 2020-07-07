package core.pages.pim.MyOrganisation.Roles;

import com.sun.jna.platform.win32.OaIdl;
import core.components.pim.Search;
import lib.FunctionLibrary;
import lib.constants.SystemPermissions;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ManageRole_Page extends FluentPage {

    @Page
    Search search;

    @FindBy(how = How.NAME, using = "roleName")
    FluentWebElement roleNameInputBox;

    @FindBy(how = How.CSS, using = ".primary-btn.push-left-20")
    FluentWebElement updateButton;

    @FindBy(how = How.CSS, using = "table tbody tr")
    FluentList<FluentWebElement> tableRows;

    @FindBy(how = How.CSS, using = "table tbody tr td:nth-child(5) input")
   FluentWebElement clickOnAddMemberToRoleCheckbox;

    @FindBy(how = How.CSS, using = "table tbody tr td:nth-child(1)")
    FluentList<FluentWebElement> systemOperationsLabel;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement  searchInputBox;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(1) a")
    FluentWebElement membersTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(2) a")
    FluentWebElement propertyPermissionsTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(3) a")
    FluentWebElement systemPermissionsTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(4) a")
    FluentWebElement infoTab;

    @FindBy(how = How.CSS, using = ".back-to-list")
    FluentWebElement backToRolesListingLink;

    @FindBy(how = How.CSS, using = ".delete-entity .hand-pointer")
    FluentWebElement deleteRoleLink;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement confirmDeleteRoleButton;

    @FindBy(how = How.CSS, using = ".no-data")
    FluentWebElement noResultFoundLabel;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement successNotification;

    @FindBy(how = How.CSS, using = "td ul li:nth-child(1)")
    FluentWebElement clickOnManageBar;

    @FindBy(how = How.CSS, using = "td ul li:nth-child(2)")
    FluentWebElement clickOnReadOnlyBar;

    @FindBy(how = How.CSS, using = "td ul li:nth-child(3)")
    FluentWebElement clickOnHideBar;

    String roleNameInputBoxName = "Role Name InputBox";
    String updateButtonName = "Update Button";
    String tableRowsName = "Table Rows";
    String searchInputBoxName = "Member Name Search InputBox";
    String membersTabName = "Members Tab";
    String propertyPermissionsTabName = "Property Permissions Tab";
    String systemPermissionsTabName = "System Permissions Tab";
    String infoTabName = "Info Tab";
    String backToRolesListingLinkName = "Back To Roles Listing Link";
    String deleteRoleLinkName = "Delete Role Link";
    String confirmDeleteRoleButtonName = "Confirm Delete Role Button";
    String noResultFoundLabelName = "No Result Found Label";
    String successNotificationName = "Success Notification";
    String systemOperationsLabelName = "System Operations Label";
    String clickOnAddMemberToRoleCheckboxName = "Click On AddMember To Role Checkbox";
    String clickOnManageBarName = "click On Manage Bar";
    String clickOnReadOnlyBarName = "click On ReadOnly Bar";
    String clickOnHideBarName = "click On Hide Bar";

    public void waitForManageRoleToAppear() throws InterruptedException {
        FunctionLibrary.waitForElementToLoad(tableRows.get(0), tableRowsName);
        FunctionLibrary.waitForElementToBeClickable(infoTab, infoTabName);
        FunctionLibrary.waitForPageToLoad();
    }

    public void clickOnInfoTab() {
        FunctionLibrary.click(infoTab, infoTabName);
    }

    public void clickOnMembersTab() {
        FunctionLibrary.click(membersTab, membersTabName);
    }

    public void clickOnPropertyPermissionsTab() {
        FunctionLibrary.click(propertyPermissionsTab, propertyPermissionsTabName);
    }

    public void clickOnSystemPermissionsTab() throws InterruptedException {
        FunctionLibrary.click(systemPermissionsTab, systemPermissionsTabName);
        FunctionLibrary.waitForPageToLoad();
    }

    public void navigateToRolesListing() {
        FunctionLibrary.click(backToRolesListingLink, backToRolesListingLinkName);
    }

    public String getRoleName() {
        return FunctionLibrary.retrieveAttributeValue(roleNameInputBox, "value", roleNameInputBoxName);
    }

    public void search(String name) throws InterruptedException {
        FunctionLibrary.input(searchInputBox, searchInputBoxName, name);
        Thread.sleep(1000);
    }

    public void clickOnUpdate(){
        FunctionLibrary.scrollToTop();
        FunctionLibrary.click(updateButton, updateButtonName);
    }

    public void updateRoleName(String updatedRoleName) {
        FunctionLibrary.clearField(roleNameInputBox, roleNameInputBoxName);
        FunctionLibrary.input(roleNameInputBox, roleNameInputBoxName, updatedRoleName);
    }

    public List<String> getTheMembersDetails() {
        clickOnMembersTab();
        return FunctionLibrary.retrieveTexts(tableRows, tableRowsName);
    }

    public List getPropertyPermissionDetails() {
        clickOnPropertyPermissionsTab();
        return FunctionLibrary.retrieveTexts(tableRows, tableRowsName);
    }

    public List getSystemPermissionName() {
        return FunctionLibrary.retrieveTexts(systemOperationsLabel, systemOperationsLabelName);
    }

    public void setSystemPermission(String systemPermissionName, String permission) {
        for(FluentWebElement ele : tableRows){
            if(ele.getElement().findElements(By.tagName("td")).get(0).getText().equalsIgnoreCase(systemPermissionName)){
                if(permission.equalsIgnoreCase("read only")) {
                    ele.getElement().findElements(By.tagName("td")).get(2).findElement(By.cssSelector("div ul li:nth-child(3)")).click();
                    ele.getElement().findElements(By.tagName("td")).get(2).findElement(By.cssSelector("div ul li:nth-child(2)")).click();
                }
                else if(permission.equalsIgnoreCase("hide")) {
                    ele.getElement().findElements(By.tagName("td")).get(2).findElement(By.cssSelector("div ul li:nth-child(1)")).click();
                    ele.getElement().findElements(By.tagName("td")).get(2).findElement(By.cssSelector("div ul li:nth-child(3)")).click();
                }
                else
                    ele.getElement().findElements(By.tagName("td")).get(2).findElement(By.cssSelector("div ul li:nth-child(1)"));
            }
        }
    }

    public void setSystemPermission1(List systemPermissions, String permission) throws InterruptedException {
        Iterator systemPermission = systemPermissions.listIterator();
        while (systemPermission.hasNext()){
            search.searchInContainer((String) systemPermission.next());
            if(permission.equalsIgnoreCase("read only")) {
                FunctionLibrary.click(clickOnHideBar, clickOnHideBarName);
                FunctionLibrary.click(clickOnReadOnlyBar, clickOnReadOnlyBarName);
            }
            else if(permission.equalsIgnoreCase("hide")) {
                FunctionLibrary.click(clickOnManageBar, clickOnManageBarName);
                FunctionLibrary.click(clickOnHideBar, clickOnHideBarName);
            }
            else
                FunctionLibrary.click(clickOnManageBar, clickOnManageBarName);
        }

    }

    public void setPropertyPermission(String propertyName, String permission) {
        for(FluentWebElement ele : tableRows) {
            if(ele.getElement().findElements(By.tagName("td")).get(0).getText().equalsIgnoreCase(propertyName)){
                if(permission.equalsIgnoreCase("read only"))
                    ele.getElement().findElements(By.tagName("td")).get(1).findElement(By.cssSelector("div ul li:nth-child(2)"));
                else if(permission.equalsIgnoreCase("hide"))
                    ele.getElement().findElements(By.tagName("td")).get(1).findElement(By.cssSelector("div ul li:nth-child(3)"));
                else
                    ele.getElement().findElements(By.tagName("td")).get(1).findElement(By.cssSelector("div ul li:nth-child(1)"));
                }
        }
    }

    public void  setPropertyPermission1(String propertyName, String permission) throws InterruptedException {
         FunctionLibrary.input(searchInputBox, searchInputBoxName, propertyName);
         Thread.sleep(1000);
        if(permission.equalsIgnoreCase("read only"))
           FunctionLibrary.click(clickOnReadOnlyBar, clickOnReadOnlyBarName);
        else if(permission.equalsIgnoreCase("hide"))
            FunctionLibrary.click(clickOnHideBar, clickOnHideBarName);
        else
            FunctionLibrary.click(clickOnManageBar, clickOnManageBarName);
    }

    public void assignMemberToRole1(String memberName) {
        for(FluentWebElement ele: tableRows) {
            if(ele.getElement().findElements(By.tagName("td")).get(0).getText().equalsIgnoreCase(memberName)){
                ele.getElement().findElement(By.tagName("input")).click();
            }
        }
    }

    public void assignMemberToRole(String memberName) throws InterruptedException {
        search(memberName);
        FunctionLibrary.click(clickOnAddMemberToRoleCheckbox, clickOnAddMemberToRoleCheckboxName);
        clickOnUpdate();

    }

    public void deleteRole() {
        FunctionLibrary.scrollToBottom();
        FunctionLibrary.click(deleteRoleLink, deleteRoleLinkName);
        FunctionLibrary.click(confirmDeleteRoleButton, confirmDeleteRoleButtonName);
    }

    public String noResultFoundPage() {
       return FunctionLibrary.retrieveText(noResultFoundLabel, noResultFoundLabelName);
    }

    public String getSuccessMessage() {
        return FunctionLibrary.retrieveText(successNotification, successNotificationName);
    }

}
