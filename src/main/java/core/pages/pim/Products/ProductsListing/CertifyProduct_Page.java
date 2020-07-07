package core.pages.pim.Products.ProductsListing;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CertifyProduct_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".modal-body .caret")
    FluentWebElement labelSelectionDropDownIcon;

    @FindBy(how = How.CSS, using = ".drop-down-body li")
    FluentList<FluentWebElement> labelsInDropDownList;

    @FindBy(how = How.NAME, using = "tagName")
    FluentWebElement labelNameInputBox;

    @FindBy(how = How.CSS, using = ".btn.primary-btn")
    FluentWebElement sendToNetWorkButton;

    @FindBy(how = How.CSS, using = ".modal-content .float-left")
    FluentWebElement countOfProductToBeCertified;

    @FindBy(how = How.CSS, using = ".success")
    FluentWebElement successInfoMsgLabel;


    String labelSelectionDropDownIconName = "Label Selection DropDown Icon";
    String labelsInDropDownListName = "Labels In Drop Down List";
    String labelNameInputBoxName = "Label Name InputBox";
    String sendToNetWorkButtonName = "Send To NetWork Button";
    String countOfProductToBeCertifiedName = "Count Of Product To Be Certified";
    String successInfoMsgLabelName = "Success Info Msg Label";



    public void clickOnLabelSelectionDropDownIcon() {
        FunctionLibrary.click(labelSelectionDropDownIcon, labelSelectionDropDownIconName);
    }

    public void createANewLabel(String labelName) {
        FunctionLibrary.selectDropDownValueByName("Create a new label", labelsInDropDownList, labelsInDropDownListName);
        FunctionLibrary.input(labelNameInputBox, labelNameInputBoxName, labelName);
        FunctionLibrary.click(sendToNetWorkButton, sendToNetWorkButtonName);
    }

    public String fetchTheCountOfProductToBeCertified() {
       return FunctionLibrary.retrieveText(countOfProductToBeCertified, countOfProductToBeCertifiedName);
    }

    public String fetchCertificationSuccessMsg() {
        return FunctionLibrary.retrieveText(successInfoMsgLabel, successInfoMsgLabelName);
    }

}
