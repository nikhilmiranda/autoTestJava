package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class StaticDropDown extends FluentPage {

    private By ddListIcon(int index) {
        return By.cssSelector(
                String.format
                        (".single-select:nth-child(%d) .drop-down-button .caret", index));
    }

    @FindBy(how = How.CSS, using = ".drop-down-button .caret")
    FluentList<FluentWebElement> ddListIcons;

    @FindBy(how = How.CSS, using = ".drop-down-body .list-item")
    FluentList<FluentWebElement> ddListValues;

    String ddListIconName = "Dropdown Icon";
    String ddListValuesName = "Dropdown List Values";


    public void clickOnDDListIcon(int index) {
        FunctionLibrary.click(ddListIcon(index), ddListIconName);
    }

    public void clickOnDDListIcon1(int index) {
        FunctionLibrary.click(ddListIcons.get(index), ddListIconName);
    }

    public void selectStaticDropDownValue(String ddValue) {
        FunctionLibrary.selectDropDownValueByName(ddValue, ddListValues, ddListValuesName);
    }

    public List<String> fetchAllTheStaticDropdownValues() {
       return FunctionLibrary.retrieveTexts(ddListValues, ddListValuesName);
    }
}
