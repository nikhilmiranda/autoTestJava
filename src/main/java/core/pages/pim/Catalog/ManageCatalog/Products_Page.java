package core.pages.pim.Catalog.ManageCatalog;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import java.util.List;

public class Products_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".qa-catalog-edit .add-products-link")
    FluentWebElement addIndividualProductLink;

    @FindBy(how = How.CSS, using = ".catalog-add-products table tbody tr input")
    FluentWebElement checkBoxAgainstProduct;

    @FindBy(how = How.CSS, using = ".catalog-add-products .add-products")
    FluentWebElement proceedWithProductsButton;

    @FindBy(how = How.CSS, using = ".toggle-btn.inactive")
    FluentWebElement toggleGroupByParentSolo;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchProductsInputBox;

    @FindBy(how = How.CSS, using = ".filter-panel-btn")
    FluentWebElement showAdvanceFilterLink;

    @FindBy(how = How.CSS, using = ".product-name-text")
    FluentList<FluentWebElement> productNameList;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement notificationMsg;



    String addIndividualProductLinkName = "Add Individual Product Link";
    String checkBoxAgainstProductName = "CheckBox Against Product";
    String toggleGroupByParentSoloName = "Toggle Group By Parent Solo";
    String searchProductsInputBoxName = "Search Products InputBox";
    String proceedWithProductsButtonName = "Proceed With Products Button";
    String showAdvanceFilterLinkName = "Show Advance Filter Link";
    String productNameListName = "Product Name List";
    String notificationMsgName = "Notification Msg";


    public void clickOnAddIndividualProductLink() {
        FunctionLibrary.click(addIndividualProductLink, addIndividualProductLinkName);
    }

    public void clickOnCheckBoxAgainstProduct() {
        FunctionLibrary.click(checkBoxAgainstProduct, checkBoxAgainstProductName);
    }

    public void clickOnToggleGroupByParentSolo() {
        FunctionLibrary.click(toggleGroupByParentSolo, toggleGroupByParentSoloName);
    }

    public void addProductToCatalog(String productName) throws InterruptedException {
        clickOnAddIndividualProductLink();
        clickOnToggleGroupByParentSolo();
        FunctionLibrary.input(searchProductsInputBox, searchProductsInputBoxName, productName);
        Thread.sleep(1000);
        FunctionLibrary.click(checkBoxAgainstProduct, checkBoxAgainstProductName);
        Assert.assertTrue(FunctionLibrary.retrieveText(proceedWithProductsButton, proceedWithProductsButtonName).equalsIgnoreCase("Proceed with (1) products"));
        FunctionLibrary.click(proceedWithProductsButton, proceedWithProductsButtonName);
    }

    public void clickOnShowAdvanceFilterLink() {
        FunctionLibrary.click(showAdvanceFilterLink, showAdvanceFilterLinkName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(addIndividualProductLink, addIndividualProductLinkName);
    }

    public List<String> getAllTheProductNames() {
       return FunctionLibrary.retrieveTexts(productNameList, productNameListName);
    }

    public String getSuccessMessage() {
        return FunctionLibrary.retrieveText(notificationMsg, notificationMsgName);
    }

}
