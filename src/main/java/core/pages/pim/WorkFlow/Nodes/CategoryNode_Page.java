package core.pages.pim.WorkFlow.Nodes;

import core.components.pim.MultiSelectModal;
import core.components.pim.PrimaryButton;
import core.components.pim.Search;
import core.components.pim.StaticDropDown;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CategoryNode_Page extends FluentPage {

    @Page
    StaticDropDown staticDropDown;

    @Page
    MultiSelectModal multiSelectModal;

    @Page
    Search search;

    @Page
    PrimaryButton primaryButton;

    @FindBy(how = How.CSS, using = ".view-children-link")
    FluentWebElement seeChildrenLink;

    @FindBy(how = How.CSS, using = ".tree-item-name")
    FluentWebElement treeItemLabel;

    String seeChildrenLinkName = "See Children Link";
    String treeItemLabelName = "Tree Item Label Name";

    public void assignParentCategory(String categoryName) throws InterruptedException {
        search.searchInDropDown(categoryName);
        staticDropDown.selectStaticDropDownValue(categoryName);
        primaryButton.clickOnPrimaryModalButton();
    }

    public void assignCategory(String categoryName) throws InterruptedException {
        multiSelectModal.clickOnDropDownIcon(0);
        String[] category = categoryName.split(">");
        for(int j=0; j< category.length; j++){
            search.searchInDropDown(category[j].trim());
            if(j!= category.length - 1)
            FunctionLibrary.click(seeChildrenLink, seeChildrenLinkName);
        }
        FunctionLibrary.click(treeItemLabel, treeItemLabelName);
        primaryButton.clickOnPrimaryModalButton();
    }
}
