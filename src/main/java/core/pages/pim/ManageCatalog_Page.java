package core.pages.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import java.security.PublicKey;

public class ManageCatalog_Page extends FluentPage {


    @FindBy(how = How.CSS, using = "horizontal-nav-item secondary:nth-child(1)")
    FluentWebElement productsTab;

    @FindBy(how = How.CSS, using = "horizontal-nav-item secondary:nth-child(2)")
    FluentWebElement propertiesTab;

    @FindBy(how = How.CSS, using = "horizontal-nav-item secondary:nth-child(3)")
    FluentWebElement facetsTab;

    @FindBy(how = How.CSS, using = "horizontal-nav-item secondary:nth-child(4)")
    FluentWebElement settingsTab;

    @FindBy(how = How.CSS, using = ".add-products-link")
    FluentWebElement addIndividualProductLink;

    @FindBy(how = How.CSS, using = ".toggle-btn")
    FluentWebElement groupByParentToggle;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchProductsInputBox;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchFacetsInputBox;

    @FindBy(how = How.NAME, using = "table tbody tr input")
    FluentWebElement checkboxAgainstProduct;

    @FindBy(how = How.CSS, using = ".add-products")
    FluentWebElement addProductsToCatalog;

    @FindBy(how = How.CSS, using = ".source-container .card-text")
    FluentWebElement facetsSource;

    @FindBy(how = How.CSS, using = ".dest-container")
    FluentWebElement facetsDestination;

    @FindBy(how = How.CSS, using = ".catalog-publish button")
    FluentWebElement publishORUnpublishCatalog;

    @FindBy(how = How.CSS, using = ".btn.primary-btn")
    FluentWebElement updateCatalogButton;

    @FindBy(how = How.NAME, using = "nameValue")
    FluentWebElement catalogNameInputBox;




    String productsTabName = "Products Tab";
    String propertiesTabName = "Properties Tab";
    String facetsTabName = "Facets Tab";
    String settingsTabName = "Settings Tab";
    String addIndividualProductLinkName = "Add Individual Product Link";
    String groupByParentToggleName = "Group By Parent Toggle";
    String searchProductsInputBoxName = "Search Products InputBox";
    String searchFacetsInputBoxName = "Search Facets InputBox";
    String checkboxAgainstProductName = "Checkbox Against Product";
    String addProductsToCatalogName = "Add Products To Catalog";

    String facetsSourceName = "Facets Source";
    String facetsDestinationName = "Facets Destination";
    String publishORUnpublishCatalogName = "Publish OR Unpublish Catalog";
    String updateCatalogButtonName = "Update Catalog Button";
    String catalogNameInputBoxName = "Catalog Name InputBox";

    public void clickOnProductsTab() {
        FunctionLibrary.click(productsTab, productsTabName);
    }

    public void clickOnPropertiesTab() {
        FunctionLibrary.click(propertiesTab, propertiesTabName);
    }

    public void clickOnFacetsTab() {
        FunctionLibrary.click(facetsTab, facetsTabName);
    }

    public void clickOnSettingsTab() {
        FunctionLibrary.click(settingsTab, settingsTabName);
    }

    public void clickOnAddIndividualProductLink() {
        FunctionLibrary.click(addIndividualProductLink, addIndividualProductLinkName);
    }

    public void addProductToCatalog(String productName) {
        FunctionLibrary.input(searchProductsInputBox, searchProductsInputBoxName, productName);
        FunctionLibrary.checkCheckBox(checkboxAgainstProduct, checkboxAgainstProductName);
        Assert.assertTrue(FunctionLibrary.retrieveText(addProductsToCatalog, addProductsToCatalogName).equalsIgnoreCase("Proceed with (1) products"));
        FunctionLibrary.click(addProductsToCatalog, addProductsToCatalogName);
    }

    public void addFacetsToCatalog(String propertyName) {
        FunctionLibrary.input(searchFacetsInputBox, searchFacetsInputBoxName, propertyName);
        FunctionLibrary.dragAndDrop(facetsSource, facetsDestination, facetsSourceName, facetsDestinationName);

    }

    public void publishCatalog() {
        FunctionLibrary.click(publishORUnpublishCatalog, publishORUnpublishCatalogName);
    }

    public void updateCatalog() {
        FunctionLibrary.click(updateCatalogButton, updateCatalogButtonName);
    }

    public String getCatalogNameFromSettingsPage() {
       return FunctionLibrary.retrieveText(catalogNameInputBox, catalogNameInputBoxName);
    }


}
