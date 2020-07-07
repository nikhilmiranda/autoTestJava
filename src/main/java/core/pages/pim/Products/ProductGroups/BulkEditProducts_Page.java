package core.pages.pim.Products.ProductGroups;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BulkEditProducts_Page extends FluentPage {

    @FindBy(how = How.CSS, using = "ul .list li:nth-child(2)")
    FluentWebElement removeProductLink;

    @FindBy(how = How.CSS, using = "ul .list li:nth-child(3)")
    FluentWebElement deleteProductLink;

    @FindBy(how = How.CSS, using = ".modal-body .drop-down-button .caret")
    FluentWebElement propertyDropDown;

    @FindBy(how = How.CSS, using = ".inline-modal-body .element-searchVal input")
    FluentWebElement propertySearchInputBox;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> propertyList;

    @FindBy(how = How.CSS, using = ".property-form-element .element-label")
    FluentWebElement propertyToBeEditedLabel;

    @FindBy(how = How.CSS, using = ".property-add-btn")
    FluentWebElement propAddBtn;

    @FindBy(how = How.CSS, using = ".property-form-element input")
    FluentWebElement propertyToBeEditedInputBox;

    @FindBy(how = How.CSS, using = ".property-form-element label:nth-child(2) input")
    FluentWebElement booleanPropertyToBeEditedCheckBox;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement updateProductsButton;


    String propertyDropDownName = "Property Drop Down Icon";
    String propertySearchInputBoxName = "Property Search Input Box Name";
    String propertyListName = "Property List";
    String propertyToBeEditedLabelName = "Property To Be Edited Label";
    String propertyToBeEditedInputBoxName = "Property To Be Edited InputBox";
    String booleanPropertyToBeEditedCheckBoxName = "Boolen Property To Be Edited CheckBox";
    String updateProductsButtonName = "Update Products Button";
    String propAddBtnName = "Property Add Button";


    public void selectAPropertyToEdit(String adapterProperty) throws InterruptedException {
        FunctionLibrary.click(propertyDropDown, propertyDropDownName);
        FunctionLibrary.input(propertySearchInputBox, propertySearchInputBoxName, adapterProperty);
        Thread.sleep(1000);
        clickOnPropAddBtn();
        FunctionLibrary.click(propertyDropDown, propertyDropDownName);
    }

    public void updateTextTypeProperty(String propertyValue) throws InterruptedException {
        FunctionLibrary.input(propertyToBeEditedInputBox, propertyToBeEditedInputBoxName, propertyValue);
        FunctionLibrary.click(updateProductsButton, updateProductsButtonName);
        Thread.sleep(2000);
    }

    public void updateBooleanTypeProperty() {
        FunctionLibrary.click(booleanPropertyToBeEditedCheckBox, booleanPropertyToBeEditedCheckBoxName);
        FunctionLibrary.click(updateProductsButton, updateProductsButtonName);

    }

    public String getThePropertyToBeEdited() {
        return FunctionLibrary.retrieveText(propertyToBeEditedLabel, propertyToBeEditedLabelName);
    }

    public void clickOnPropAddBtn() {
        FunctionLibrary.click(propAddBtn, propAddBtnName);
    }
}