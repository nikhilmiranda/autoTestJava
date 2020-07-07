package core.pages.pim.Tasks.TaskTemplate;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class TaskTemplateListing_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".secondary-btn.float-right")
    FluentWebElement createTaskTemplateButton;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement templateSearch;

    @FindBy(how = How.CSS, using = "table tbody tr td:nth-child(1)")
    FluentList<FluentWebElement> templatesName;

    @FindBy(how = How.CSS, using = "table tbody tr td:nth-child(3)")
    FluentWebElement templateEditIcon;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement taskCreationSuccessMsg;

    String createTaskTemplateButtonName = "Create Task Template Button";
    String templateSearchName = "Template Search";
    String templatesNameName = "Templates Name";
    String templateEditIconName = "Template Edit Icon";
    String taskCreationSuccessMsgName = "Task Creation Success Msg";

    public void searchTemplate(String templateName) {
        FunctionLibrary.input(templateSearch, templateSearchName, templateName);
    }

    public List<String> getTemplateNames() {
       return FunctionLibrary.retrieveTexts(templatesName, templatesNameName);
    }

    public void clickOnTemplateEditIcon() {
        FunctionLibrary.click(templateEditIcon, templateEditIconName);
    }

    public void clickOnCreateTaskTemplate() {
        FunctionLibrary.click(createTaskTemplateButton, createTaskTemplateButtonName);
    }

    public String retrieveTaskCreationSuccessMsg() {
        return FunctionLibrary.retrieveText(taskCreationSuccessMsg, taskCreationSuccessMsgName);
    }


}
