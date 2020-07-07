package core.pages.pim.WorkFlow.Nodes;

import core.components.pim.PrimaryButton;
import core.components.pim.SingleSelectModal;
import core.components.pim.StaticDropDown;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class TaskNode_Page extends FluentPage {

    @Page
    SingleSelectModal singleSelectModal;

    @Page
    PrimaryButton primaryButton;

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement taskNameInputBox;

    @FindBy(how = How.NAME, using = "instruction")
    FluentWebElement taskDesInputBox;

    String taskNameInputBoxName = "Task Name";
    String taskDesInputBoxName = "TaskDescription Description";

    public void selectTaskTemplate(String templateName, String taskName, String taskDesc) {
        singleSelectModal.clickOnDropDownIcon(2);
        singleSelectModal.selectStaticDropDownValue(templateName);
        primaryButton.clickOnPrimaryModalButton();
        FunctionLibrary.input(taskNameInputBox, taskNameInputBoxName, taskName);
        FunctionLibrary.input(taskDesInputBox, taskDesInputBoxName, taskDesc);
        primaryButton.clickOnPrimaryModalButton();
    }
}
