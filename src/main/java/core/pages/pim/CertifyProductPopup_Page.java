package core.pages.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class CertifyProductPopup_Page {

    @FindBy(how = How.CSS, using = ".element-tag .drop-down-text")
    FluentWebElement labelWhenNolabelIsAssigned;

    @FindBy(how = How.CSS, using = ".element-tag .drop-down-button .caret")
    FluentWebElement labelDropdown;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> labelsList;

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement sendToNetworkButton;

    @FindBy(how = How.CSS, using = ".modal-content h4")
    FluentWebElement certifyLabel;

    @FindBy(how = How.NAME, using = "tagName")
    FluentWebElement labelNameInputBox;


    String labelWhenNolabelIsAssignedName = "Label When No Roles Is Assigned";
    String labelDropdownName = "Label Dropdown";
    String labelsListName = "Labels List";
    String sendToNetworkButtonName = "Send To Network Button";
    String certifyLabelName = "Certify Label";
    String labelNameInputBoxName = "Label Name InputBox";


    public String getTheCountOfProductToBeCertified() {
        return FunctionLibrary.retrieveText(certifyLabel, certifyLabelName);
    }

    public void clickOnLabelDropDown() {
         FunctionLibrary.retrieveText(labelDropdown, labelDropdownName);
    }

    public String getTheDropDownLabel() {
       return FunctionLibrary.retrieveText(labelWhenNolabelIsAssigned, labelWhenNolabelIsAssignedName);
    }

    public void createANewLabel(String labelName) {
        FunctionLibrary.selectDropDownValueByName("Create a new label", labelsList, labelsListName);
        FunctionLibrary.input(labelNameInputBox, labelNameInputBoxName, labelName);
    }

    public void clickOnSendToNetwork() {
        FunctionLibrary.click(sendToNetworkButton, sendToNetworkButtonName);

    }


}
