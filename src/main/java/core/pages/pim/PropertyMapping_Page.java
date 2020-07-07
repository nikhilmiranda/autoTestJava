package core.pages.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class PropertyMapping_Page extends FluentPage {

    @FindBy(how = How.CSS, using = "span.title")
    FluentList<FluentWebElement> propertiesName;

    @FindBy(how = How.CSS, using = ".drop-down-text")
    FluentList<FluentWebElement> dropDownMenus;

    @FindBy(how = How.CSS, using = ".drop-down-input")
    FluentList<FluentWebElement> searchProperty;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> listItems;

    @FindBy(how = How.CSS, using = ".import-unique-selector fieldset")
    FluentList<FluentWebElement> propertyMappingList;

    @FindBy(how = How.NAME, using = "delimiter")
    FluentWebElement separator;


//    public void selectPropertyType(String name, Sting type, ){
//
//    }

    public void performMapping(String name){
        for(FluentWebElement propertyEle : propertyMappingList){
            if(propertyEle.getText().equalsIgnoreCase(name)) {
                propertyEle.getElement().findElement(By.tagName("input")).click();
            }
        }
    }

    public void searchProperty(String name){
        searchProperty.get(0).fill().with(name);
    }

    public void searchPropertyType(String type){
        searchProperty.get(0).fill().with(type);
    }

    public void selectProperty(String name){
        FunctionLibrary.selectDropDownValueByName(name,listItems, "nam3" );
    }

    public void clickOnPropertyDropdown(){
        dropDownMenus.get(0).click();
    }

    public void clickOnPropertyTypeDropdown(){
        dropDownMenus.get(1).click();
    }

    public void clickOnPropertyTypeDropdown(String sep){
        separator.fill().with(sep);
    }

    public List getPropertiesName(){
       return propertiesName.getTexts();
    }
}

