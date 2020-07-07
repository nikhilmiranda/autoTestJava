package core.pages.pim.Products.ProductsListing;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Products_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(1) a")
    FluentWebElement productsTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(2) a")
    FluentWebElement productGroupsTab;

    @FindBy(how = How.CSS, using = ".btn.primary-btn")
    FluentWebElement proceedButton;

    @FindBy(how = How.CSS, using = ".qa-product-list.product-listing-page .secondary-btn")
    FluentWebElement addProductButton;

    @FindBy(how = How.NAME, using = "uniqueId")
    FluentWebElement productIdInputBox;

    @FindBy(how = How.NAME, using = "productName")
    FluentWebElement productNameInputBox;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchProductInputBox;

    @FindBy(how = How.CSS, using = ".qa-product-list .glyphicon-eye-open")
    FluentWebElement productEditIcon;

    @FindBy(how = How.CSS, using = "table tbody tr")
    FluentList<FluentWebElement> productList;

    @FindBy(how = How.CSS, using = "table tbody tr input")
    FluentWebElement checkBoxAgainstProduct;

    @FindBy(how = How.CSS, using = ".qa-product-list .glyphicon-cog")
    FluentWebElement productHeadersSettingCog;

    @FindBy(how = How.CSS, using = ".toggle-btn.inactive")
    FluentWebElement toggleGroupByParentSolo;

    @FindBy(how = How.CSS, using = ".toggle-btn.active")
    FluentWebElement toggleGroupByVariantSolo;

    @FindBy(how = How.CSS, using = ".product-message-banner .product-link")
    FluentWebElement addAVariantProductLink;

    @FindBy(how = How.CSS, using = ".no-data")
    FluentWebElement noResultFoundLabel;


    public static By spinLoader = By.cssSelector(".ajax-loader");


    String productsTabName = "Product Name Tab";
    String productGroupsTabName = "Product Groups Tab";
    String addProductButtonName = "Add Product Button";
    String productIdInputBoxName = "ProductId InputBox";
    String productNameInputBoxName = "Product Name InputBox";
    String proceedButtonName = "Proceed Button";
    String searchProductInputBoxName = "Search Product InputBox";
    String productEditIconName = "Product Edit Icon";
    String productListName = "Product List";
    String productHeadersSettingCogName = "Product Headers Setting Cog";
    String spinLoaderName = "Spin Loader";
    String checkBoxAgainstProductName = "CheckBox Against Product";
    String toggleGroupByParentSoloName = "Toggle Group By Parent Solo";
    String toggleGroupByVariantSoloName = "Toggle Group By Variant Solo";
    String addAVariantProductLinkName = "Add A Variant Product Link";
    String noResultFoundLabelName = "No Result Found Label";


    public void clickOnProductTab() {
        FunctionLibrary.click(productsTab, productsTabName);
    }

    public void clickOnProductGrpTab() {
        FunctionLibrary.click(productGroupsTab, productGroupsTabName);
    }

    public Boolean isCreateProductPresent(){
       return FunctionLibrary.isElementPresent(addProductButton, addProductButtonName);
    }

    public void createProduct(String productID, String productName) {
        FunctionLibrary.click(addProductButton, addProductButtonName);
        FunctionLibrary.input(productIdInputBox, productIdInputBoxName, productID);
        FunctionLibrary.input(productNameInputBox, productNameInputBoxName, productName);
        FunctionLibrary.click(proceedButton, proceedButtonName);
    }

    public void searchProduct(String productName) throws InterruptedException {
        FunctionLibrary.input(searchProductInputBox, searchProductInputBoxName, productName);
        Thread.sleep(1000);
    }

    public void clickOnProductEditIcon() {
        FunctionLibrary.click(productEditIcon, productEditIconName);
    }

    public List<String> getProductsList() {
        return FunctionLibrary.retrieveTexts(productList, productListName);
    }

    public void waitForProductListingPageToLoad() {
       // FunctionLibrary.ThreadWait();
       // FunctionLibrary.waitForLoaderToDisappear(spinLoader, spinLoaderName);
        FunctionLibrary.waitForElementToLoad(productHeadersSettingCog, productHeadersSettingCogName);
        FunctionLibrary.waitForElementToLoad(productEditIcon, productEditIconName);
    }

    public void clickOnCheckBoxAgainstProduct() {
        FunctionLibrary.click(checkBoxAgainstProduct, checkBoxAgainstProductName);
    }

    public void clickOnToggleGroupByParentSolo() {
        FunctionLibrary.click(toggleGroupByParentSolo, toggleGroupByParentSoloName);
    }
    public void clickOnToggleGroupByParentSoloName() {
        FunctionLibrary.click(toggleGroupByVariantSolo, toggleGroupByVariantSoloName);
    }

    public void clickOnAddProductVariantLink() {
        FunctionLibrary.click(addAVariantProductLink, addAVariantProductLinkName);
    }


    public String getNoResultFoundLabel() {
        return  FunctionLibrary.retrieveText(noResultFoundLabel, noResultFoundLabelName);
    }



 }
