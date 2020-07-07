package core.pages.pim.ManageNetwork.Product.ProductLabels;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ManageLabel_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit table tbody tr td:nth-child(3)")
    FluentList<FluentWebElement> productNameList;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit table tbody tr")
    FluentList<FluentWebElement> productDetailsList;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit .push-right-10")
    FluentWebElement otherActionButton;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(1)")
    FluentWebElement sendAllProductsToPartnerLink;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit .list-item:nth-child(2)")
    FluentWebElement editLabelNameLink;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit .list-item:nth-child(3)")
    FluentWebElement deleteLabelLink;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit .primary-btn")
    FluentWebElement addPartnerReadinessButton;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit .toggle-btn")
    FluentWebElement groupByParentToggleSlider;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit .actions-dd .caret")
    FluentWebElement actionsIcon;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit .list-item:nth-child(1)")
    FluentWebElement addAnotherLabelForProduct;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit .list-item:nth-child(2)")
    FluentWebElement removeProductFromLabel;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit .list-item:nth-child(3)")
    FluentWebElement deleteProductFromLabel;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit .element-searchVal")
    FluentWebElement searchInputBox;

    @FindBy(how = How.NAME, using = "labelName")
    FluentWebElement labelNameInputBox;

    @FindBy(how = How.CSS, using = ".qa-nwlabel-edit .modal-buttons .primary-btn")
    FluentWebElement editLabelNameButton;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement infoMsg;

    String productNameListName = "Product Name List";
    String productDetailsListName = "Product Details List";
    String otherActionButtonName = "Other Action Button";
    String sendAllProductsToPartnerLinkName = "Send All Products To Partner Link";
    String editLabelNameLinkName = "Edit Label Name Link";
    String deleteLabelLinkName = "Delete Label Link";
    String addPartnerReadinessButtonName = "Add Partner Readiness Button";
    String groupByParentToggleSliderName = "Group By Parent Toggle Slider";
    String actionsIconName = "Actions Icon";
    String addAnotherLabelForProductName = "Add Another Label For Product";
    String removeProductFromLabelName = "Remove Product From Label";
    String deleteProductFromLabelName = "Delete Product From Label";
    String searchInputBoxName = "Search Input Box";
    String labelNameInputBoxName = "Label Name InputBox";
    String editLabelNameButtonName = "Edit Label Name Button";
    String infoMsgName = "Information Message";


    public List<String> getAllTheCertifiedProductNameInLabel() {
        return FunctionLibrary.retrieveTexts(productNameList, productNameListName);
    }

    public List<String> getAllTheCertifiedProductDetailsInLabel() {
        return FunctionLibrary.retrieveTexts(productDetailsList, productDetailsListName);
    }

    public void searchLabel(String productName) {
        FunctionLibrary.input(searchInputBox, searchInputBoxName, productName);
    }

    public void editLabel(String updatedLabelName) {
        FunctionLibrary.click(editLabelNameLink, editLabelNameLinkName);
        FunctionLibrary.input(labelNameInputBox, labelNameInputBoxName, updatedLabelName);
        FunctionLibrary.click(editLabelNameButton, editLabelNameButtonName);
    }

    public String getInfoMsg() {
        return FunctionLibrary.retrieveText(infoMsg, infoMsgName);
    }

    public void clickOnOtherActionsButton() {
        FunctionLibrary.click(otherActionButton, otherActionButtonName);
    }

    public void clickOnSendAllProductsToPartnerLink() {
        FunctionLibrary.click(sendAllProductsToPartnerLink, sendAllProductsToPartnerLinkName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(addPartnerReadinessButton, addPartnerReadinessButtonName);
    }
}
