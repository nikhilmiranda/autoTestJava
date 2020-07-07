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

public class selectPropertyDropdown extends FluentPage {

    @FindBy(how = How.CSS, using = ".modal-body .element-property .inline-modal .drop-down-button .caret")
    FluentWebElement propertyDropDown;

    @FindBy(how = How.CSS, using = ".drop-down-search-container input")
    FluentWebElement propertySearchInputBox;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> propertyList;


    String propertyDropDownName = "Property DropDown";
    String propertySearchInputBoxName = "Property Search InputBox";
    String propertyListName = "Property List";


    public void selectAPropertyToEdit(String adapterProperty) throws InterruptedException {
        FunctionLibrary.click(propertyDropDown, propertyDropDownName);
        FunctionLibrary.input(propertySearchInputBox, propertySearchInputBoxName, adapterProperty);
        Thread.sleep(1000);
        FunctionLibrary.click(propertyList.get(0), propertyListName);

    }
}
