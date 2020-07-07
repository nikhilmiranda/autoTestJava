package core.pages.pim.ManageNetwork.Product.CertifyProducts;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ProductsListing_Page extends FluentPage {


    @FindBy(how = How.CSS, using = ".qa-nwproduct-list table tbody tr td:nth-child(3) .product-name-text")
    FluentList<FluentWebElement>  productListing;

    @FindBy(how = How.CSS, using = ".qa-nwproduct-list table thead tr")
    FluentWebElement  productProperties;

    @FindBy(how = How.CSS, using = ".qa-nwproduct-list .toggle-btn")
    FluentWebElement groupByParentToggle;

    @FindBy(how = How.CSS, using = ".qa-nwproduct-list .actions-dd .caret")
    FluentWebElement actionMenuIcon;

    @FindBy(how = How.CSS, using = ".qa-nwproduct-list .list-item")
    FluentWebElement exportProductsLink;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement infoMsgLabel;

    @FindBy(how = How.CSS, using = ".message-box a")
    FluentWebElement infoMsgLabelLinkToExportList;



    String productListingName = "Product Listing";
    String productPropertiesName = "Product Properties";
    String groupByParentToggleName = "GroupBy Parent Toggle";
    String actionMenuIconName = "Action Menu Icon";
    String exportProductsLinkName = "Export Products Link";



    public List<String> getAllTheProducts() {
        return FunctionLibrary.retrieveTexts(productListing, productListingName);
    }

    public void clickOngroupByParent() {
        FunctionLibrary.click(groupByParentToggle, groupByParentToggleName);
    }

    public void exportProducts() {
        FunctionLibrary.click(actionMenuIcon, actionMenuIconName);
        FunctionLibrary.click(exportProductsLink, exportProductsLinkName);
    }

    public void waitForPageToLoad() throws InterruptedException {
        FunctionLibrary.waitForElementToBeClickable(groupByParentToggle, groupByParentToggleName);
    }

}
