package core.pages.pim.Properties.PropertyGroup_Page;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PropertyGroup_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".qa-propGroup-list button.secondary-btn")
    FluentWebElement createPropertyGroupButton;

    @FindBy(how = How.NAME, using = "propertyGroupName")
    FluentWebElement propertyGroupNameInputBox;

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement proceedButton;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchPropertyGroup;

    @FindBy(how = How.CSS, using = ".qa-propGroup-list table tbody tr")
    FluentList<FluentWebElement> tableRows;

    @FindBy(how = How.CSS, using = ".qa-propGroup-list table tbody tr li")
    FluentList<FluentWebElement> propertiesAssignedToGroup;

    @FindBy(how = How.CSS, using = ".qa-propGroup-list table tbody tr .glyphicon-eye-open")
    FluentWebElement propertyGroupEditIcon;

    @FindBy(how = How.CSS, using = ".first-time-import")
    FluentWebElement blankPropertyGroupPage;


    String createPropertyGroupButtonName = "Create Property Group Button";
    String propertyGroupNameInputBoxName = "Property Group Name InputBox";
    String proceedButtonName = "Proceed Button";
    String searchPropertyGroupName = "Search Property Group";
    String tableRowsName = "Table Rows";
    String propertyGroupEditIconName = "Property Group Edit Icon";
    String propertiesAssignedToGroupName = "Properties Assigned To Product Group";
    String blankPropertyGroupPageName = "Blank Property Group Page";

    public void createPropertyGroup(String propertyGroupName) throws InterruptedException {
        Thread.sleep(3000);
        FunctionLibrary.click(createPropertyGroupButton, createPropertyGroupButtonName);
        if(!FunctionLibrary.isElementDisplayed(propertyGroupNameInputBox, propertyGroupNameInputBoxName)){
            FunctionLibrary.click(createPropertyGroupButton, createPropertyGroupButtonName);
        }
        Thread.sleep(3000);
        FunctionLibrary.input(propertyGroupNameInputBox, propertyGroupNameInputBoxName, propertyGroupName);
        FunctionLibrary.click(proceedButton, proceedButtonName);
    }

    public Boolean isPropertyGroupButtonPresent() {
       return FunctionLibrary.isElementPresent(createPropertyGroupButton, createPropertyGroupButtonName);
    }


    public void searchPropertyGroup(String propertyGroupName) throws InterruptedException {
        FunctionLibrary.input(searchPropertyGroup, searchPropertyGroupName, propertyGroupName);
        Thread.sleep(1000);
    }

    public void clickOnPropertyGroupEditIcon() {
        FunctionLibrary.click(propertyGroupEditIcon, propertyGroupEditIconName);
    }

    public List<String> getPropertyGroupName() {
       return FunctionLibrary.retrieveTexts(tableRows, tableRowsName);
    }

    public List<String> getPropertiesAssignedToPropertyGroup() {
        return FunctionLibrary.retrieveTexts(propertiesAssignedToGroup, propertiesAssignedToGroupName);
    }

    public void waitForPropertyGroupToLoad() throws InterruptedException {
        FunctionLibrary.waitForElementToLoad(createPropertyGroupButton, createPropertyGroupButtonName);
        FunctionLibrary.waitForPageToLoad();
    }

    public String getPageContentOfBlankPropertyGroupPage() {
        return FunctionLibrary.retrieveText(blankPropertyGroupPage, blankPropertyGroupPageName);
    }

}
