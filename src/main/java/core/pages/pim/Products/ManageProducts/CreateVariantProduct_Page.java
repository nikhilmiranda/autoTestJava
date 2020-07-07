package core.pages.pim.Products.ManageProducts;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateVariantProduct_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".modal-content .glyphicon-plus")
    FluentWebElement propertyVariantsDropDownLink;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchPropertyInputBox;

    @FindBy(how = How.CSS, using = ".property-add-btn")
    FluentWebElement addPropertyVariantLink;

    @FindBy(how = How.CSS, using = ".modal-body .property-form-element:nth-child(4) input")
    FluentWebElement productIdInputBox;

    @FindBy(how = How.CSS, using = ".modal-body .property-form-element:nth-child(5) input")
    FluentWebElement productNameInputBox;

    @FindBy(how = How.CSS, using = ".modal-body .property-form-element:nth-child(6) input")
    FluentWebElement fabricPropertyInputBox;

    @FindBy(how = How.CSS, using = ".modal-content .primary-btn")
    FluentWebElement createProductVariantButton;

    @FindBy(how = How.CSS, using = ".selected-pills")
    FluentList<FluentWebElement> getThePropertyVariantsAdded;

    @FindBy(how = How.CSS, using = ".expandable-item")
    FluentList<FluentWebElement> getThePropertyGroupNameAndValues;



    String propertyVariantsDropDownLinkName = "propertyVariantsDropDownLink";
    String searchPropertyInputBoxName = "searchPropertyInputBox";
    String addPropertyVariantLinkName = "addPropertyVariantLink";
    String getThePropertyVariantsAddedName = "getThePropertyVariantsAdded";
   // String propertyVariantsDropDownLinkName = "propertyVariantsDropDownLink";
    String createProductVariantButtonName = "Create Product Variant Button";
    String productIdInputBoxName = "Product Id InputBox";
    String productNameInputBoxName = "Product Name InputBox";
    String fabricPropertyInputBoxName = "Fabric Property InputBox";




    public void clickOnPropertyVariantDropDown() {
        FunctionLibrary.click(propertyVariantsDropDownLink, propertyVariantsDropDownLinkName);

    }

    public void selectAPropertyVariant(String propertyName) throws InterruptedException {
        FunctionLibrary.input(searchPropertyInputBox, searchPropertyInputBoxName, propertyName);
        Thread.sleep(1000);
        FunctionLibrary.click(addPropertyVariantLink, addPropertyVariantLinkName);
    }

    public Map<String, List<String>> getTheContentOfPropertyVariantDropDown() {
        Map<String, List<String>> mp = new HashMap<String, List<String>>();
         for(FluentWebElement fl : getThePropertyGroupNameAndValues){
             mp.put(fl.findFirst(".expand-toggle").getText(), fl.find(".float-left").getTexts());
         }
         return mp;
    }

    public List<String> getAllThePropertieselected() {
        return FunctionLibrary.retrieveTexts(getThePropertyVariantsAdded, getThePropertyVariantsAddedName);
    }


    public void createPropertyVariant(String productId, String productName, String fabricName) {
        FunctionLibrary.input(productIdInputBox, productIdInputBoxName, productId);
        FunctionLibrary.input(productNameInputBox, productNameInputBoxName, productName);
        FunctionLibrary.input(fabricPropertyInputBox, fabricPropertyInputBoxName, fabricName);
        FunctionLibrary.click(createProductVariantButton, createProductVariantButtonName);
    }




}
