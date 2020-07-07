package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SelectProperties extends FluentPage {

    @FindBy(how = How.CSS, using = "table tbody tr")
    FluentList<FluentWebElement> propRowsList;

    @FindBy(how = How.CSS, using = "td:nth-child(2)")
    FluentWebElement propertyNamesList;

    @FindBy(how = How.CSS, using = ".step-2 table td input")
    FluentWebElement checkboxAgainstPropertyModal;

    @FindBy(how = How.CSS, using = "table td input")
    FluentWebElement checkboxAgainstProperty;

    @FindBy(how = How.CSS, using = ".product-name-block .product-name-text")
    FluentList<FluentWebElement> getTheProductNames;

    String productNamesListName = "Product Names List";
    String checkboxAgainstPropertyName = "Checkbox Against Property";
    String checkboxAgainstPropertyModalName = "Checkbox Against Property Modal";
    String actionsDropdownModalListDisabledValuesName = "Actions Dropdown Modal List Values Disabled";
    String propRowsListName = "propRowsListName";


    public  Map<String, List<String>> getAllThePropertiesDetails() {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for(FluentWebElement propRow : propRowsList) {
            map.put(FunctionLibrary.retrieveText(propRow.findFirst("td:nth-child(1)"), propRowsListName), Arrays.asList(FunctionLibrary.retrieveText(propRow.findFirst("td:nth-child(2)"), propRowsListName), FunctionLibrary.retrieveText(propRow.findFirst("td:nth-child(3)"), propRowsListName)));
        }
        return map;

    }

    public void clickOnCheckboxAgainstProperty() {
          FunctionLibrary.click(checkboxAgainstProperty, checkboxAgainstPropertyName);

    }

    public void clickOnCheckboxAgainstPropertyInModal() {
        FunctionLibrary.click(checkboxAgainstPropertyModal, checkboxAgainstPropertyModalName);

    }

}
