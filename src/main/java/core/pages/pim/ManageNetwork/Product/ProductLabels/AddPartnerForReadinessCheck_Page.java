package core.pages.pim.ManageNetwork.Product.ProductLabels;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class AddPartnerForReadinessCheck_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".modal-body .element-partner .drop-down-button")
    FluentWebElement partnerDropdownIcon;

    @FindBy(how = How.CSS, using = ".modal-body .element-adapter .drop-down-button")
    FluentWebElement adapterDropdownIcon;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> dropdownValuesList;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement checkForReadinessButton;

    @FindBy(how = How.CSS, using = ".modal-body .glyphicon-plus-sign")
    FluentWebElement plusIcon;

    @FindBy(how = How.CSS, using = ".modal-body .glyphicon-minus-sign")
    FluentWebElement minusIcon;

    String partnerDropdownIconName = "Partner Dropdown Icon";
    String adapterDropdownIconName = "Adapter Dropdown Icon";
    String dropdownValuesListName = "Dropdown Values List";
    String checkForReadinessButtonName = "Check For Readiness Button";
    String plusIconName = "Plus Icon";
    String minusIconName = "Minus Icon";


    public void checkPartnerreadiness(String partnerName, String adapterName) {

            FunctionLibrary.click(partnerDropdownIcon, partnerDropdownIconName);
            FunctionLibrary.selectDropDownValueByName(partnerName, dropdownValuesList, dropdownValuesListName);
            FunctionLibrary.click(adapterDropdownIcon, adapterDropdownIconName);
            FunctionLibrary.selectDropDownValueByName(adapterName, dropdownValuesList, dropdownValuesListName);
            FunctionLibrary.click(checkForReadinessButton, checkForReadinessButtonName);
    }
}
