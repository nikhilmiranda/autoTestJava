package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class FilterListModal extends FluentPage {

    @FindBy(how = How.CSS, using = ".filter-panel .filters-list .inline-modal .drop-down-button .caret")
    FluentList<FluentWebElement> filterDropDown;

    @FindBy(how = How.CSS, using = ".drop-down-search-container input")
    FluentWebElement filterValSearchInputBox;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> filterList;

    String filterDropDownName = "Filter DropDown";
    String filterValSearchInputBoxName = "Filter Search InputBox";
    String filterListName = "Filter List";

    public void applyFilter(int index, String filterValue, Boolean searchBoxPresent) throws InterruptedException {
        FunctionLibrary.click(filterDropDown.get(index), filterDropDownName);
        if(searchBoxPresent) {
            FunctionLibrary.input(filterValSearchInputBox, filterValSearchInputBoxName, filterValue);
            Thread.sleep(1000);
            FunctionLibrary.click(filterList.get(0), filterListName);
        }
        else{
            FunctionLibrary.selectDropDownValueByName(filterValue, filterList, filterListName);
        }
    }

    public List<String> retrieveFilterValues() {
        return FunctionLibrary.retrieveTexts(filterList, filterListName);
    }
}