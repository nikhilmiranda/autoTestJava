package core.pages.pim.WorkFlow.Nodes;

import core.components.pim.Search;
import core.components.pim.StaticDropDown;
import core.pages.pim.Products.ProductGroups.BulkEditProducts_Page;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BulkEditNode_Page extends FluentPage {

    @Page
    StaticDropDown staticDropDown;

    @Page
    BulkEditProducts_Page bulkEditProductsPage;

    @Page
    Search search;

    @FindBy(how = How.CSS, using = ".drop-down-button .caret")
    FluentList<FluentWebElement> staticDDIcons;

    @FindBy(how = How.CSS, using = ".modal-body input")
    FluentWebElement propValInputBox;

    String staticDDIconsName = "Static DD Icons";
    String propValInputBoxName = "Property Value Input Box";

    public void configureBulkEditNode(String propertyName, String propertyValue) throws InterruptedException {
        FunctionLibrary.click(staticDDIcons.get(1), staticDDIconsName);
        search.search(propertyName);
        bulkEditProductsPage.clickOnPropAddBtn();
        FunctionLibrary.click(staticDDIcons.get(1), staticDDIconsName);
        FunctionLibrary.input(propValInputBox, propValInputBoxName, propertyValue);
    }
}
