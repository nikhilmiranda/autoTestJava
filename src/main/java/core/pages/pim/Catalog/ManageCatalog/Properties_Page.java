package core.pages.pim.Catalog.ManageCatalog;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Properties_Page extends FluentPage {


    @FindBy(how = How.CSS, using = ".selected-properties-container .drop-down-button .caret")
    FluentWebElement propertyVariantsDropDownLink;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchPropertyInputBox;

    @FindBy(how = How.CSS, using = ".list-item .property-add-btn")
    FluentWebElement addPropertyVariantLink;

    @FindBy(how = How.CSS, using = ".prop-row")
    FluentList<FluentWebElement> propertiesAddedToCatalog;

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement updateButton;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement infoMsgLabel;

    String propertyVariantsDropDownLinkName = "Property Variants DropDown Link";
    String searchPropertyInputBoxName = "SearchPropertyInputBox";
    String addPropertyVariantLinkName = "addPropertyVariantLink";
    String propertiesAddedToCatalogName = "Properties Added To Catalog";
    String updateButtonName = "Update Button";
    String infoMsgLabelName = "Info Msg Label";


    public void clickOnPropertyDropDown() {
        FunctionLibrary.click(propertyVariantsDropDownLink, propertyVariantsDropDownLinkName);

    }

    public void selectAPropertyVariant(String propertyName) throws InterruptedException {
        FunctionLibrary.input(searchPropertyInputBox, searchPropertyInputBoxName, propertyName);
        Thread.sleep(1000);
        FunctionLibrary.click(addPropertyVariantLink, addPropertyVariantLinkName);
    }

    public void clickOnUpdateButton() {
        FunctionLibrary.click(updateButton, updateButtonName);

    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(propertyVariantsDropDownLink, propertyVariantsDropDownLinkName);

    }

    public List<String> getAllThePropertiesAddedToCatalog() {
       return FunctionLibrary.retrieveTexts(propertiesAddedToCatalog, propertyVariantsDropDownLinkName);

    }

    public String fetchInfoMsg() {
        return FunctionLibrary.retrieveText(infoMsgLabel, infoMsgLabelName);
    }

}
