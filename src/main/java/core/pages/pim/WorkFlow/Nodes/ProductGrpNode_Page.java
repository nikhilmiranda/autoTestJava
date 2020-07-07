package core.pages.pim.WorkFlow.Nodes;

import core.components.pim.PrimaryButton;
import core.components.pim.SingleSelectModal;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class ProductGrpNode_Page extends FluentPage {

    @Page
    SingleSelectModal singleSelectModal;

    @Page
    PrimaryButton primaryButton;

    @FindBy(how = How.NAME, using = "groupName")
    FluentWebElement pgNameInputBox;

    @FindBy(how = How.NAME, using = "groupDesc")
    FluentWebElement pgDescInputBox;

    String pgNameInputBoxName = "Product Group Name";
    String pgDescInputBoxName = "Product Group Description";

    public List<String> fetchTheListValues() {
        return singleSelectModal.fetchAllTheStaticDropDownValues();
    }

    public void createNewPG(String productGrpName, String productGrpDesc) {
        singleSelectModal.clickOnDropDownIcon(2);
        singleSelectModal.selectStaticDropDownValue("Create a new group");
        FunctionLibrary.input(pgNameInputBox, pgNameInputBoxName, productGrpName);
        FunctionLibrary.input(pgDescInputBox, pgDescInputBoxName, productGrpDesc);
        primaryButton.clickOnPrimaryModalButton();
    }

    public void addToExistingPG(String productGrpName) {
        singleSelectModal.clickOnDropDownIcon(2);
        singleSelectModal.selectStaticDropDownValue(productGrpName);
        primaryButton.clickOnPrimaryModalButton();
    }
}
