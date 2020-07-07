package core.pages.pim.Properties.Properties_Page;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class ManageProperty_Page extends FluentPage {

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement propertyNameInputBox;

    @FindBy(how = How.CSS, using = ".qa-prop-edit .primary-btn")
    FluentWebElement updateButton;

    @FindBy(how = How.CSS, using = ".element-property_group_id .drop-down-text")
    FluentWebElement propertyGroupDropDown;

    @FindBy(how = How.CSS, using = ".element-data_type .drop-down-text")
    FluentWebElement propertyDataTypeInputBox;

    @FindBy(how = How.NAME, using = "searchable")
    FluentWebElement propertySearchableCheckBox;

    @FindBy(how = How.CSS, using = ".delete-entity .hand-pointer")
    FluentWebElement deletePropertyLink;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement confirmDeleteButton;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(1)")
    FluentWebElement propertyDetailsTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(2)")
    FluentWebElement permissionForRolesTab;

    @FindBy(how = How.CSS, using = ".table tbody tr")
    FluentList<FluentWebElement> tableRows;

    @FindBy(how = How.CSS, using = ".table tbody tr td:nth-child(1)")
    FluentList<FluentWebElement> rolesList;

    @FindBy(how = How.CSS, using = ".list-item.active")
    FluentList<FluentWebElement> permissionList;


    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement roleSearchInputBox;

    @FindBy(how = How.CSS, using = ".no-data h2")
    FluentWebElement noResultFound;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement successNotification;

    @FindBy(how = How.CSS, using = ".back-to-list")
    FluentWebElement backToPropertiesListingLink;




    String propertyNameInputBoxName = "Property Name InputBox";
    String updateButtonName = "Update Button";
    String propertyGroupDropDownName = "Property Group DropDown";
    String propertyDataTypeInputBoxName = "Property DataType InputBox";
    String propertySearchableCheckBoxName = "Property Searchable CheckBox";
    String deletePropertyLinkName = "Delete Property Link";
    String confirmDeleteButtonName = "Confirm Delete Button";
    String permissionForRolesTabName = "Permission For Roles Tab";
    String rolesListName = "Roles List";

    String roleSearchInputBoxName = "Role Search InputBox";
    String permissionListName = "Permission List";
    String successNotificationName = "Success Notificationtn";
    String backToPropertiesListingLinkName = "Back To Properties Listing Link";
    String propertyDetailsTabName = "Property Details Tab";

    public void updatePropertyName(String updatedProeprtyName) throws InterruptedException {
        FunctionLibrary.clearField(propertyNameInputBox, propertyNameInputBoxName);
        FunctionLibrary.input(propertyNameInputBox, propertyNameInputBoxName, updatedProeprtyName);
        FunctionLibrary.click(updateButton, updateButtonName);
    }

    public void searchRole(String role) throws InterruptedException {
        FunctionLibrary.input(roleSearchInputBox, roleSearchInputBoxName, role);
        Thread.sleep(1000);
    }

    public String getRoles(){
        List roles = new ArrayList<String>();
        for(FluentWebElement ele : tableRows){
            roles.add(ele.getElement().findElements(By.tagName("td")).get(0).getText());
        }
        return roles.toString();
    }

    public List<String> fetchRoles(){
        return FunctionLibrary.retrieveTexts(rolesList, rolesListName);
    }

    public List<String> getPermissions(){
       return FunctionLibrary.retrieveTexts(permissionList, permissionListName);

    }

    public String getPropertyName(){
       return FunctionLibrary.retrieveAttributeValue(propertyNameInputBox, "value", propertyNameInputBoxName);

    }

    public String getPropertyDataType(){
       return FunctionLibrary.retrieveText(propertyDataTypeInputBox, propertyDataTypeInputBoxName);

    }

    public String getProductGroup(){
        return FunctionLibrary.retrieveText(propertyGroupDropDown, propertyGroupDropDownName);

    }

    public void clickOnPermissionForRoles() {
        FunctionLibrary.click(permissionForRolesTab, permissionForRolesTabName);
    }

    public void changePermissionForRole(String roleName, String permission) {
        for(FluentWebElement ele : tableRows){
            if(ele.getElement().findElements(By.tagName("td")).get(0).getText().equalsIgnoreCase(roleName)){
                if(permission.equalsIgnoreCase("read only"))
                    ele.getElement().findElement(By.cssSelector(".list-item:nth-child(2)"));
                else if(permission.equalsIgnoreCase("hide"))
                    ele.getElement().findElement(By.cssSelector(".list-item:nth-child(3)"));
                else
                    ele.getElement().findElement(By.cssSelector(".list-item:nth-child(1)"));
            }
        }
    }

    public String getNoResultFoundMessage(){
        return noResultFound.getTextContent();
    }

    public  void deleteProperty() {
        FunctionLibrary.click(deletePropertyLink, deletePropertyLinkName);
        FunctionLibrary.click(confirmDeleteButton, confirmDeleteButtonName);

    }

    public String getSuccessMessage() {
        return FunctionLibrary.retrieveText(successNotification, successNotificationName);
    }

    public void clickOnBackToPropertyListing() {
        FunctionLibrary.click(backToPropertiesListingLink, backToPropertiesListingLinkName);
    }

    public void saveManagePropertyDetails() {
        FunctionLibrary.click(updateButton, updateButtonName);
    }

    public void waitForManagePropertyToLoad() {
       // FunctionLibrary.waitForElementToLoad(backToPropertiesListingLink, backToPropertiesListingLinkName);
        FunctionLibrary.waitForElementToLoad(propertyDetailsTab, propertyDetailsTabName);
    }
}
