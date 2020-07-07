package core.pages.pim.Products.ProductsListing;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Actions_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".table-action-container .caret")
    FluentWebElement actionsIcon;

    @FindBy(how = How.NAME, using = ".static-dd-modal li:nth-child(1)")
    FluentWebElement addToProductGroupLink;

    @FindBy(how = How.CSS, using = ".static-dd-modal li:nth-child(2)")
    FluentWebElement bulkEditProductLink;

    @FindBy(how = How.NAME, using = ".static-dd-modal li:nth-child(3)")
    FluentWebElement assignTaskForProductLink;

    @FindBy(how = How.CSS, using = ".static-dd-modal li:nth-child(4)")
    FluentWebElement certifyForNetworkLink;

    @FindBy(how = How.NAME, using = ".static-dd-modal li:nth-child(5)")
    FluentWebElement deleteProductLink;

    @FindBy(how = How.NAME, using = ".static-dd-modal li")
    FluentList<FluentWebElement> actionsList;

    String actionsIconName = "Actions Icon";
    String addToProductGroupLinkName = "Add To Product Group Link";
    String bulkEditProductLinkName = "Bulk Edit Product Link";
    String assignTaskForProductLinkName = "Assign Task For Product Link";
    String certifyForNetworkLinkName = "Certify For Network Link";
    String deleteProductLinkName = "Delete Product Link";
    String actionsListName = "Actions List";


    public void clickOnActionsIcon() {
        FunctionLibrary.click(actionsIcon, actionsIconName);
    }

    public Boolean isActionsIconPresent() {
       return FunctionLibrary.isElementPresent(actionsIcon, actionsIconName);
    }

    public void clickOnAddToProductGroupLink() {
        FunctionLibrary.click(addToProductGroupLink, addToProductGroupLinkName);
    }

    public void clickOnBulkEditProductLink() {
        FunctionLibrary.click(bulkEditProductLink, bulkEditProductLinkName);
    }

    public void clickOnAssignTaskForProductLink() {
        FunctionLibrary.click(assignTaskForProductLink, assignTaskForProductLinkName);
    }

    public void clickOnCertifyForNetworkLink() {
        FunctionLibrary.click(certifyForNetworkLink, certifyForNetworkLinkName);
    }

    public void clickOnDeleteProductLink() {
        FunctionLibrary.click(deleteProductLink, deleteProductLinkName);
    }

    public List<String> getActionsListName() {
        return FunctionLibrary.retrieveTexts(actionsList, actionsListName);
    }


}
