package core.pages.pim.Catalog.ManageCatalog;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;
import java.security.PublicKey;

public class ManageCatalog_Page extends FluentPage {


    @FindBy(how = How.CSS, using = ".horizontal-nav-item:nth-child(1) a")
    FluentWebElement productsTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-item:nth-child(2) a")
    FluentWebElement propertiesTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-item:nth-child(3) a")
    FluentWebElement facetsTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-item:nth-child(4) a")
    FluentWebElement settingsTab;


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
    String groupByParentToggleName = "Group By Parent Toggle";
    String searchProductsInputBoxName = "Search Products InputBox";
    String checkboxAgainstProductName = "Checkbox Against Product";
    String addProductsToCatalogName = "Add Products To Catalog";


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


    public void publishCatalog() {
        FunctionLibrary.scrollToTop();
        FunctionLibrary.click(publishORUnpublishCatalog, publishORUnpublishCatalogName);
    }

    public void updateCatalog() {
        FunctionLibrary.click(updateCatalogButton, updateCatalogButtonName);
    }

    public String getCatalogNameFromSettingsPage() {
       return FunctionLibrary.retrieveText(catalogNameInputBox, catalogNameInputBoxName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(publishORUnpublishCatalog, publishORUnpublishCatalogName);
    }


}
