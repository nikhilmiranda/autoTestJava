package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class SingleSelectModal extends FluentPage {

    @FindBy(how = How.CSS, using = ".single-select .drop-down-button .caret")
    FluentList<FluentWebElement> singleSelectDropDownIcons;

    @FindBy(how = How.CSS, using = ".drop-down-body li")
    FluentList<FluentWebElement> ddListValues;

    @FindBy(how = How.CSS, using = ".drop-down-search-container input")
    FluentList<FluentWebElement> listSearchInputBox;

    String singleSelectDropDownIconsName = "Dropdown Icons";
    String ddListValuesName = "Dropdown List Values";
    String listSearchInputBoxName = "List Search Input Box";

    public void clickOnDropDownIcon(int index) {
        FunctionLibrary.click(singleSelectDropDownIcons.get(index), singleSelectDropDownIconsName);
    }

    public void searchList(int index) {
        FunctionLibrary.click(singleSelectDropDownIcons.get(index), singleSelectDropDownIconsName);
    }

    public void selectStaticDropDownValue(String ddValue) {
        FunctionLibrary.selectDropDownValueByName(ddValue, ddListValues, ddListValuesName);
    }

    public List<String> fetchAllTheStaticDropDownValues() {
        return FunctionLibrary.retrieveTexts(ddListValues, ddListValuesName);
    }
}
