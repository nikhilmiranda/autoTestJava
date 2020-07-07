package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ProductsTable extends FluentPage {

    @FindBy(how = How.CSS, using = "table tbody tr")
    FluentList<FluentWebElement> itemsRowListDetails;

    @FindBy(how = How.CSS, using = ".modal-body table tbody tr td:nth-child(2)")
    FluentList<FluentWebElement> itemsRowListName;

    @FindBy(how = How.CSS, using = ".modal-body table tbody tr td input")
    FluentWebElement checkboxAgainstItemRow;

    @FindBy(how = How.CSS, using = ".toggle-btn.inactive")
    FluentWebElement toggleGroupByParentSolo;

    @FindBy(how = How.CSS, using = ".glyphicon-eye-open")
    FluentWebElement editIconAgainstRow;

    String itemsRowListDetailsName = "Items row Names";
    String itemsRowListNameName = "Items row Names";
    String checkboxAgainstItemRowName = "Checkbox Against Item row";
    String toggleGroupByParentSoloName = "Toggle Group By Parent Solo";
    String editIconAgainstRowName = "Edit Icon Against Row";


    public void clickOnToggleGroupByParentSolo() {
        FunctionLibrary.click(toggleGroupByParentSolo, toggleGroupByParentSoloName);
    }

    public List<String> fetchAllTheRowItemsDetailFromModal() {
        return FunctionLibrary.retrieveTexts(itemsRowListDetails, itemsRowListDetailsName);
    }

    public void selectRowItemFromModal() {
         FunctionLibrary.click(checkboxAgainstItemRow, checkboxAgainstItemRowName);
    }

    public List<String> fetchAllTheRowItemsNameFromModal() {
        return FunctionLibrary.retrieveTexts(itemsRowListName, itemsRowListNameName);
    }

    public List<String> fetchAllTheRowItemsDetail() {
        return FunctionLibrary.retrieveTexts(itemsRowListDetails, itemsRowListDetailsName);
    }

    public void selectRowItem() {
        FunctionLibrary.click(checkboxAgainstItemRow, checkboxAgainstItemRowName);
    }

    public List<String> fetchAllTheRowItemsName() {
        return FunctionLibrary.retrieveTexts(itemsRowListName, itemsRowListNameName);
    }

    public void clickOnEditIcon() {
        FunctionLibrary.click(editIconAgainstRow, editIconAgainstRowName);
    }

}
