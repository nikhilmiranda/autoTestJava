package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class ActionsDDModal extends FluentPage {

    @FindBy(how = How.CSS, using = ".actions-dd .caret")
    FluentWebElement actionsDDIcon;

    @FindBy(how = How.CSS, using = ".actions-dd-body li")
    FluentList<FluentWebElement> actionsDDListValues;

    @FindBy(how = How.CSS, using = ".actions-dd-body .list-item.disabled")
    FluentList<FluentWebElement> actionsDDListDisabledValues;

    String actionsDDIconName = "Actions DropDown Icon Name";
    String actionsDDListValuesName = "Actions DropDown Modal List Values Name";
    String actionsDDListDisabledValuesName = "Actions DropDown Modal List Values Disabled";

    public void clickOnActionsDropDownIcon() {
        FunctionLibrary.click(actionsDDIcon, actionsDDIconName);
    }

    public List<String> getAllTheListValues() {
        return  FunctionLibrary.retrieveTexts(actionsDDListValues, actionsDDListValuesName);
    }

    public List<String> getAllTheListDisabledValues() {
        return  FunctionLibrary.retrieveTexts(actionsDDListDisabledValues, actionsDDListDisabledValuesName);
    }

    public void selectDDListValue(String listValueName) {
        FunctionLibrary.selectDropDownValueByName(listValueName, actionsDDListValues, actionsDDListValuesName);
    }

    public void clickOnListDropDownValue(String listValueName) {
        for(FluentWebElement listValue : actionsDDListValues){ 
            if(FunctionLibrary.retrieveText(listValue, actionsDDListValuesName).contains(listValueName)){ 
                FunctionLibrary.click(listValue,actionsDDListValuesName);
                break;
            }
        }
    }



}
