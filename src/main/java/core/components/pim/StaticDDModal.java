package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class StaticDDModal extends FluentPage {

    private By ddListValues(int index) {
        return By.cssSelector(
                String.format
                        (".static-dd-modal:nth-child(%d) li", index));
    }


    String ddListValuesName = "Dropdown List Values";

    public void selectStaticDropdownValue(String ddValue, int index) {
        for( WebElement ddListValue : getDriver().findElements(ddListValues(index))){
            if (FunctionLibrary.retrieveText(ddListValue, ddListValuesName) == ddValue){
                FunctionLibrary.click(ddListValue, ddListValuesName);
                break;
            }
        }
    }

    public List<String> fetchAllTheStaticDropdownValues(int index) {
        List<String> ddValuesText = new ArrayList<String>();
        List<WebElement> ddValueWebElements = getDriver().findElements(ddListValues(index));
        for(WebElement ddValueWebElement : ddValueWebElements)
             ddValuesText.add(FunctionLibrary.retrieveText(ddValueWebElement, ddListValuesName));

        return ddValuesText;

    }
}
