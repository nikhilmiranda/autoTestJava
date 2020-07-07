package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class MultiSelectModal extends FluentPage {

    @FindBy(how = How.CSS, using = ".multi-select .drop-down-button .caret")
    FluentList<FluentWebElement> multiSelectDropDownIcons;

    @FindBy(how = How.CSS, using = ".drop-down-body li")
    FluentList<FluentWebElement> ddListValues;

    String multiSelectDropDownIconsName = "Dropdown Icons";
    String ddListValuesName = "Dropdown List Values";

    public void clickOnDropDownIcon(int index) {
        FunctionLibrary.click(multiSelectDropDownIcons.get(index), multiSelectDropDownIconsName);
    }

    public void selectStaticDropDownValue(String ddValue) {
        FunctionLibrary.selectDropDownValueByName(ddValue, ddListValues, ddListValuesName);
    }

    public List<String> fetchAllTheStaticDropDownValues() {
        return FunctionLibrary.retrieveTexts(ddListValues, ddListValuesName);
    }
}
