package core.pages.pim.Catalog;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CatalogView_Page extends FluentPage {


    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchCatalogProductInputBox;

    @FindBy(how = How.CSS, using = ".ub-product-title")
    FluentList<FluentWebElement> catalogProductNameList;

    @FindBy(how = How.CSS, using = ".ub-product-image")
    FluentWebElement seeDetailsLink;

    @FindBy(how = How.CSS, using = ".well-sm")
    FluentList<FluentWebElement> productPropertyDetailsLabel;

    @FindBy(how = How.CSS, using = ".ub-suggest-list li")
    FluentList<FluentWebElement> searchSuggesstionList;


    String searchCatalogProductInputBoxName = "searchCatalogProductInputBox";
    String catalogProductNameListName = "catalogProductNameList";
    String seeDetailsLinkName = "seeDetailsLink";
    String productPropertyDetailsLabelName = "productPropertyDetailsLabel";
    String searchSuggesstionListName = "searchSuggesstionList";



    public List<String> getAlltheProductNames() {
       return FunctionLibrary.retrieveTexts(catalogProductNameList, catalogProductNameListName);

    }

    public void searchCatalogProduct(String productName) throws InterruptedException {
        FunctionLibrary.input(searchCatalogProductInputBox, searchCatalogProductInputBoxName, productName);
        Thread.sleep(1000);
    }

    public List<String> getAllTheSearchSuggesstions() {
        return FunctionLibrary.retrieveTexts(searchSuggesstionList, searchSuggesstionListName);
    }

    public void openProduct(String productName) throws InterruptedException {
        searchCatalogProduct(productName);
        FunctionLibrary.click(seeDetailsLink, seeDetailsLinkName);

    }

    public void clickOnSearch() throws InterruptedException {
        FunctionLibrary.click(searchCatalogProductInputBox, searchCatalogProductInputBoxName);

    }

    public List<String> getAllTheProductPropertiesDetails() {
        return FunctionLibrary.retrieveTexts(productPropertyDetailsLabel, productPropertyDetailsLabelName);
    }
}
