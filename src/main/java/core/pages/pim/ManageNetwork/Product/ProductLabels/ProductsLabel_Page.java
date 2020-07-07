package core.pages.pim.ManageNetwork.Product.ProductLabels;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ProductsLabel_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".qa-nwlabels-list table tbody tr td:nth-child(2)")
    FluentList<FluentWebElement> productLabelList;

    @FindBy(how = How.CSS, using = ".qa-nwlabels-list table tbody tr")
    FluentList<FluentWebElement> productLabelDetailsList;

    @FindBy(how = How.CSS, using = ".qa-nwlabels-list .glyphicon-eye-open")
    FluentWebElement  productLabelEditIcon;

    @FindBy(how = How.CSS, using = ".qa-nwlabels-list .actions-dd .caret")
    FluentWebElement actionMenuIcon;

    @FindBy(how = How.NAME, using = ".qa-nwlabels-list .element-searchVal")
    FluentWebElement searchInputBox;

    String productLabelListName = "Product Label List";
    String productLabelDetailsListName = "Product Label Details List";
    String productLabelEditIconName = "Product Label Edit Icon";
    String actionMenuIconName = "Action Menu Icon";
    String searchInputBoxName = "Search Input Box";


    public List<String> getAllTheProductLabels() {
        return FunctionLibrary.retrieveTexts(productLabelList, productLabelListName);
    }

    public void searchLabel(String labelName) {
         FunctionLibrary.input(searchInputBox, searchInputBoxName, labelName);
    }

    public void clickOnLabelEditIcon() {
        FunctionLibrary.click(productLabelEditIcon, productLabelEditIconName);
    }

    public List<String> getAllTheProductLabelDetails() {
        FunctionLibrary.waitForTextToBePresent(productLabelDetailsList.get(0), productLabelDetailsListName, "4");
        return FunctionLibrary.retrieveTexts(productLabelDetailsList, productLabelDetailsListName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(productLabelEditIcon, productLabelEditIconName);
    }

}
