package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class AssociateProperties extends FluentPage {

    @FindBy(how = How.CSS, using = ".static-dropdown .inline-modal .drop-down-button .caret")
    FluentWebElement propertyDropDown;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement dropDownSearchInputBox;

    @FindBy(how = How.CSS, using = ".property-add-btn")
    FluentWebElement addButtonAgainstProperty;

    @FindBy(how = How.CSS, using = ".expand-toggle")
    FluentList<FluentWebElement> propertyGroupsInDropdownList;

    String propertyDropDownName = "propertyDropDown";
    String dropDownSearchInputBoxName = "dropDownSearchInputBox";
    String addButtonAgainstPropertyName = "addButtonAgainstProperty";
    String propertyGroupsInDropdownListName = "propertyGroupsInDropdownList";


    public void associatePropertyToCategory(String propertyName) {
        FunctionLibrary.click(propertyDropDown, propertyDropDownName);
        FunctionLibrary.input(dropDownSearchInputBox, dropDownSearchInputBoxName, propertyName);
        FunctionLibrary.click(addButtonAgainstProperty, addButtonAgainstPropertyName);

    }

    public List<String> getAllTheProductGroupListValues() {
        return  FunctionLibrary.retrieveTexts(propertyGroupsInDropdownList, propertyGroupsInDropdownListName);
    }

    public List<String> getAllThePropertyListValues() {
        return  FunctionLibrary.retrieveTexts(propertyGroupsInDropdownList, propertyGroupsInDropdownListName);
    }
}
