package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectProducts extends FluentPage {

    @FindBy(how = How.CSS, using = ".product-name-block")
    FluentList<FluentWebElement> productNamesList;

    @FindBy(how = How.CSS, using = ".step-1 table td input")
    FluentWebElement checkboxAgainstProductModal;

    @FindBy(how = How.CSS, using = "table td input")
    FluentWebElement checkboxAgainstProduct;

    @FindBy(how = How.CSS, using = ".product-name-block .product-name-text")
    FluentList<FluentWebElement> getTheProductNames;

    @FindBy(how = How.CSS, using = "table td tr")
    FluentList<FluentWebElement> productRowsList;

    String productNamesListName = "Product Names List";
    String checkboxAgainstProductName = "Checkbox Against Product";
    String checkboxAgainstProductModalName = "Checkbox Against Product Modal";
    String productRowsListName = "Product Rows List";

    public List<String> getAllTheProducts() {
        return FunctionLibrary.retrieveTexts(getTheProductNames, productNamesListName);
    }

    public Map<String, List<String>> getAllTheProductDetails() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(FluentWebElement productRow : productRowsList) {
            map.put(FunctionLibrary.retrieveText(productRow.findFirst("td:nth-child(1)"), productRowsListName), Arrays.asList(FunctionLibrary.retrieveText(productRow.findFirst("td:nth-child(2)"), productRowsListName), FunctionLibrary.retrieveText(productRow.findFirst("td:nth-child(3)"), productRowsListName)));
        }
        return map;
    }

    public void clickOnCheckboxAgainstProductInModal() {
        FunctionLibrary.click(checkboxAgainstProductModal, checkboxAgainstProductModalName);
    }

    public void clickOnCheckboxAgainstProduct() {
        FunctionLibrary.click(checkboxAgainstProduct, checkboxAgainstProductName);
    }



}
