package core.pages.pim.ManageNetwork.Adapter.CustomAdapter;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PropertyMapping_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".qa-adapter-edit .drop-down-button .caret")
    FluentWebElement adapterPropertyDropDown;

    @FindBy(how = How.CSS, using = ".static-dd-modal .search-container input")
    FluentWebElement addPropertySearchInputBox;

    @FindBy(how = How.CSS, using = ".drop-down-input")
    FluentWebElement propertySearchInputBox;

    @FindBy(how = How.CSS, using = ".list-item .property-add-btn")
    FluentWebElement propertyAddIcon;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> propertyList;

    @FindBy(how = How.CSS, using = ".qa-adapter-edit table tbody tr")
    FluentList<FluentWebElement> adapterPropertiesMappingDetails;

    @FindBy(how = How.CSS, using = ".qa-adapter-edit .prop-label-view")
    FluentList<FluentWebElement> adapterPropertiesList;

    @FindBy(how = How.CSS, using = ".qa-adapter-edit .primary-btn")
    FluentWebElement saveMappingsButton;

    @FindBy(how = How.CSS, using = ".qa-adapter-edit .add-adapter-prop button")
    FluentWebElement addEmptyPropertyButton;

    @FindBy(how = How.CSS, using = ".qa-adapter-edit .glyphicon-pencil")
    FluentWebElement adapterPropertyEditIcon;

    @FindBy(how = How.NAME, using = "adapter_property_name")
    FluentWebElement adapterPropertyNameInputBox;

    @FindBy(how = How.CSS, using = ".success")
    FluentWebElement successInfoMsgLabel;

    @FindBy(how = How.CSS, using = ".back-to-list .glyphicon-arrow-left")
    FluentWebElement backToListIcon;



    String propertySearchInputBoxName = "Property Search InputBox";
    String propertyListName = "Property List";
    String propertyAddIconName = "Property Add Icon";
    String adapterPropertyDropDownName = "Adapter Property DropDown";
    String adapterPropertiesListName = "Adapter Properties List";
    String saveMappingsButtonName = "Save Mappings Button";
    String addEmptyPropertyButtonName = "Add Empty Property Button";
    String adapterPropertyEditIconName = "Adapter Property Edit Icon";
    String adapterPropertyNameInputBoxName = "Adapter Property Name InputBox";
    String successInfoMsgLabelName = "success InfoMsgLabel";
    String backToListIconName = "Back To List Icon";

    public void addAnPropertyToAdapter(String adapterProperty) throws InterruptedException {
        FunctionLibrary.click(adapterPropertyDropDown, adapterPropertyDropDownName);
        FunctionLibrary.input(addPropertySearchInputBox, propertySearchInputBoxName, adapterProperty);
        Thread.sleep(1000);
        FunctionLibrary.click(propertyAddIcon, propertyAddIconName);
    }

    public void createAdapterMappings(String adapterProperty, String orgProperty) throws InterruptedException {
        for(FluentWebElement adapterPro : adapterPropertiesMappingDetails){
            if(adapterPro.findFirst(".prop-label-view").getText().equalsIgnoreCase(adapterProperty)){
                adapterPro.findFirst(".drop-down-button .caret").click();
                FunctionLibrary.input(propertySearchInputBox, propertySearchInputBoxName, orgProperty);
                Thread.sleep(1000);
                FunctionLibrary.click(propertyList.get(0), propertyListName);
                break;
            }
        }
    }

    public void createAdapterMappingsForEmptyProperty(String adapterProperty, String orgProperty) throws InterruptedException {
        for(FluentWebElement adapterPro : adapterPropertiesMappingDetails) {
            if(adapterPro.findFirst(".prop-label-view").getText().equalsIgnoreCase("New Property")) {
                FunctionLibrary.click(adapterPro.findFirst(".glyphicon-pencil"), adapterPropertyEditIconName);
                FunctionLibrary.input(adapterPropertyNameInputBox, adapterPropertyNameInputBoxName, adapterProperty);
                adapterPro.findFirst(".drop-down-button .caret").click();
                FunctionLibrary.input(propertySearchInputBox, propertySearchInputBoxName, orgProperty);
                Thread.sleep(1000);
                FunctionLibrary.click(propertyList.get(0), propertyListName);
                break;
            }
        }
    }

    public List<String> fetchAdapterProperties() {
        return FunctionLibrary.retrieveTexts(adapterPropertiesList, adapterPropertiesListName);
    }

    public void clickOnSaveMappings() {
        FunctionLibrary.scrollToTop();
        FunctionLibrary.click(saveMappingsButton, saveMappingsButtonName);
    }

    public void clickOnAddEmptyProperty() {
        FunctionLibrary.click(addEmptyPropertyButton, addEmptyPropertyButtonName);
    }

    public String getSuccessInfoMsg() {
        return FunctionLibrary.retrieveText(successInfoMsgLabel, successInfoMsgLabelName);
    }

    public void clickOnBackToListIcon() {
        FunctionLibrary.click(backToListIcon, backToListIconName);
    }

    public void waitForPageToLoad() {
       // FunctionLibrary.waitForSpinLoaderToDissapear();
        FunctionLibrary.waitForElementToLoad(backToListIcon, backToListIconName);
    }

}
