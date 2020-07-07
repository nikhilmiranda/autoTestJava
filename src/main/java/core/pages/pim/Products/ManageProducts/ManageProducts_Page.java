package core.pages.pim.Products.ManageProducts;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageProducts_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement saveProductButton;

    @FindBy(how = How.CSS, using = ".product-category .edit-on-hover div div")
    FluentList<FluentWebElement> categoryAssociated;

    @FindBy(how = How.CSS, using = ".product-card")
    FluentList<FluentWebElement> productCards;

    @FindBy(how = How.NAME, using = ".property-form-element")
    FluentList<FluentWebElement> propertyList;

    @FindBy(how = How.CSS, using = ".delete-entity .hand-pointer")
    FluentWebElement deletePropertyLink;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement confirmDeleteButton;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement successNotification;

    @FindBy(how = How.CSS, using = ".no-data h2")
    FluentWebElement noResultFoundPage;

    @FindBy(how = How.CSS, using = ".product-link")
    FluentWebElement addVariantProduct;

    @FindBy(how = How.CSS, using = ".drop-down-button.caret")
    FluentWebElement addPropertiesDropDownIcon;

    @FindBy(how = How.CSS, using = ".expandable-item .expand-toggle")
    FluentList<FluentWebElement> propertyGroupHeaderInAddPropertiesDropDown;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> propertyListInAddPropertiesDropDown;

    @FindBy(how = How.CSS, using = ".product-accordian:nth-child(4)  .summary")
    FluentList<FluentWebElement> variantListed;

    @FindBy(how = How.CSS, using = ".sprite.dd-trigger")
    FluentList<FluentWebElement> variantEditIcon;

    @FindBy(how = How.CSS, using = ".activity-log-btn")
    FluentWebElement activityLogBtn;

    @FindBy(how = How.CSS, using = ".save-icon-btn")
    FluentWebElement saveCommentIcon;

    @FindBy(how = How.CSS, using = ".element-comment textarea")
    FluentWebElement commentInputBox;

    public static By spinLoader = By.cssSelector(".ajax-loader");

    String saveProductButtonName = "Save Product Button";
    String categoryAssociatedName = "Name of associated categories";
    String productIdInputBoxName = "ProductId InputBox";
    String productNameInputBoxName = "Product Name InputBox";
    String proceedButtonName = "Proceed Button";
    String deletePropertyLinkName = "Delete Property Link";
    String confirmDeleteButtonName = "Confirm Delete Button";
    String successNotificationName = "Success Notification";
    String noResultFoundPageName = "No Result Found Page";
    String addVariantProductName = "Add Variant Product";
    String addPropertiesDropDownIconName = "Add Properties DropDown Icon";
    String propertyGroupHeaderInAddPropertiesDropDownName = "Property Group HeaderIn Add Properties DropDown";
    String propertyListInAddPropertiesDropDownName = "Property List In Add Properties DropDown";
    String spinLoaderName = "Spin Loader";
    String activityLogBtnName = "Activity Log Button";
    String saveCommentIconName = "Save Icon";
    String commentInputBoxName = "Comment Input Box";

    public void clickOnSaveProductButton() {
        FunctionLibrary.click(saveProductButton, saveProductButtonName);
    }

    public void clickOnSaveCommentIcon() {
        FunctionLibrary.scrollToTop();
        FunctionLibrary.click(saveCommentIcon, saveCommentIconName);
    }

    public List<String> getTheAssociatedCategories() {
        return FunctionLibrary.retrieveTexts(categoryAssociated, categoryAssociatedName);
    }

    public void enterComment(String comment) {
        FunctionLibrary.input(commentInputBox, commentInputBoxName, comment);
    }

    public Boolean isSaveProductButtonPresent() {
       return FunctionLibrary.isElementPresent(saveProductButton, saveProductButtonName);
    }

    public Map getTheProductPropertiesWithAssignedValueInEditMode() {
        Map<String, String> mp = new HashMap<String, String>();
            for(FluentWebElement productCard : productCards){
                for(FluentWebElement fb: productCard.find(".property-form-element")){
                    mp.put(fb.findFirst(".float-left").getText(), fb.findFirst(".property-value").getText());
                }
        }
        return mp;
    }

    public Map getTheProductPropertiesWithAssignedValue() {
        Map<String, String> mp = new HashMap<String, String>();
        for(FluentWebElement productCard : productCards){
            for(FluentWebElement fb: productCard.find(".property-form-view")){
                mp.put(fb.find(".float-left").getText(), fb.findFirst(".property-value").getText());
            }
        }
        return mp;
    }

    public String updateProperty(String propertyName, String value) {
        FunctionLibrary.scrollToBottom();
        for(FluentWebElement productCard : productCards){
            for(FluentWebElement fb: productCard.find(".property-form-element")){
                if (fb.findFirst(".float-left").getText().equalsIgnoreCase(propertyName)){
                    try {
                        fb.findFirst(".float-left").click();
                        fb.findFirst(".edit-trigger").click();
                    }catch(NoSuchElementException exp) {
                        return "Property is in read only mode";
                    }
                    fb.findFirst("input").fill().with(value);
                    break;
                }
            }
        }
        return "updated property";
    }

    public String getPropertyValue(String propertyName) {
        for(FluentWebElement productCard : productCards){
            for(FluentWebElement fb: productCard.find(".property-form-element")){
                if (fb.findFirst(".element-label span").getText().equalsIgnoreCase(propertyName)){
                   return fb.findFirst(".property-value").getText();
                }
            }
        }
        return "No Property Found";
    }

    public void deleteProperty() {
        FunctionLibrary.click(deletePropertyLink, deletePropertyLinkName);
        FunctionLibrary.click(confirmDeleteButton, confirmDeleteButtonName);
    }

    public String getSuccessMessage() {
        return FunctionLibrary.retrieveText(successNotification, successNotificationName);
    }

    public String getNoResultFoundMessage() {
        return FunctionLibrary.retrieveText(noResultFoundPage, noResultFoundPageName);
    }

    public void waitForManageProductPageToLoad() {
        //FunctionLibrary.ThreadWait();
        FunctionLibrary.waitForElementToLoad(saveCommentIcon, saveCommentIconName);
    }

    public List<String> fetchPropertyGroupInAddPropertiesDropDown() {
       return FunctionLibrary.retrieveTexts(propertyGroupHeaderInAddPropertiesDropDown, propertyGroupHeaderInAddPropertiesDropDownName);
    }

    public List<String> fetchPropertyAssignedToPropertyGroupInAddPropertiesDropDown(String propertyGroupName) {
        for( FluentWebElement propertyGroup : propertyGroupHeaderInAddPropertiesDropDown){
            if(FunctionLibrary.retrieveText(propertyGroup, propertyGroupHeaderInAddPropertiesDropDownName).equalsIgnoreCase(propertyGroupName)){

            }
        }
        return FunctionLibrary.retrieveTexts(propertyGroupHeaderInAddPropertiesDropDown, propertyGroupHeaderInAddPropertiesDropDownName);
    }

    public void clickOnActivityLogBtn() {
        FunctionLibrary.click(activityLogBtn, activityLogBtnName);
    }
}
