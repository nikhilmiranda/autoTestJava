package core.pages.pim.Properties.PropertyGroup_Page;

import lib.FunctionLibrary;
import lib.Helper;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.lang.*;

import java.util.List;

public class ManagePropertyGroup_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(1)")
    FluentWebElement propertyGroupPropertiesTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(2)")
    FluentWebElement propertyGroupDetailsTab;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchPropertyInputBox;

    @FindBy(how = How.CSS, using = ".hand-pointer")
    FluentWebElement deletePropertyLink;

    @FindBy(how = How.CSS, using = "table tbody tr")
    FluentList<FluentWebElement> tableRows;

    @FindBy(how = How.CSS, using = "table tbody tr input")
    FluentWebElement selectionCheckboxAgainstProperty;

    @FindBy(how = How.NAME, using = "propertyGroupName")
    FluentWebElement propertyGroupNameInputBox;

    @FindBy(how = How.CSS, using = ".primary-btn.push-left-20")
    FluentWebElement updateButton;

    @FindBy(how = How.CSS, using = ".back-to-list")
    FluentWebElement backToPropertyGroupListingLink;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement successNotification;

    @FindBy(how = How.CSS, using = ".delete-entity .hand-pointer")
    FluentWebElement deletePropertyGroupLink;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement confirmDeleteButton;


    String propertyGroupPropertiesTabName = "Property Group Properties Tab";
    String propertyGroupDetailsTabName = "Property Group Details Tab";
    String tableRowsName = "Property List";
    String selectionCheckboxAgainstPropertyName = "Selection Checkbox Against Property";
    String searchPropertyInputBoxName = "Search Property InputBox";
    String propertyGroupNameInputBoxName = "Property Group Name InputBox";
    String updateButtonName = "Update Button";
    String backToPropertyGroupListingLinkName = "Back To Property Group Listing Link";
    String successNotificationName = "Success Notificationt";
    String deletePropertyGroupLinkName = "Delete Property Group Link";
    String confirmDeleteButtonName = "Confirm Delete Button";


    public void searchProperty(String propertyName) {
        FunctionLibrary.input(searchPropertyInputBox, searchPropertyInputBoxName, propertyName);
    }

    public void addPropertyToProductGroup(String propertyName) throws InterruptedException {
        searchProperty(propertyName);
        FunctionLibrary.click(selectionCheckboxAgainstProperty, selectionCheckboxAgainstPropertyName);
    }

    public void clickOnUpdate() {
        FunctionLibrary.click(updateButton, updateButtonName);
    }

    public void clickOnPropertyGroupPropertiesTab() {
        FunctionLibrary.click(propertyGroupPropertiesTab, propertyGroupPropertiesTabName);
    }

    public void clickOnPropertyGroupDetailsTab() {
        FunctionLibrary.click(propertyGroupDetailsTab, propertyGroupDetailsTabName);
    }

    public void clickOnBackToPropertyGroupListingLink() {
        Helper.scrollIntotop();
        FunctionLibrary.click(backToPropertyGroupListingLink, backToPropertyGroupListingLinkName);
    }

    public String getPropertyGroupName() {
       return FunctionLibrary.retrieveAttributeValue(propertyGroupNameInputBox,"value", propertyGroupNameInputBoxName);
    }

    public List<String> getPropertiesName() {
        return FunctionLibrary.retrieveTexts(tableRows, tableRowsName);
    }

    public String getSuccessMessage() {
        return FunctionLibrary.retrieveText(successNotification, successNotificationName);
    }

    public void waitForManagePropertyGroupToLoad() {
        FunctionLibrary.waitForElementToLoad(propertyGroupPropertiesTab, propertyGroupPropertiesTabName);
    }

    public  void deletePropertyGroup() {
        Helper.scrollDown();
        System.out.println(deletePropertyGroupLink.getText());
        FunctionLibrary.click(deletePropertyGroupLink, deletePropertyGroupLinkName);
        FunctionLibrary.click(confirmDeleteButton, confirmDeleteButtonName);

    }
}
