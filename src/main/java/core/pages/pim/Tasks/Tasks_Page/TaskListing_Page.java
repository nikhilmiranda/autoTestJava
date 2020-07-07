package core.pages.pim.Tasks.Tasks_Page;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class TaskListing_Page extends FluentPage {

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchTaskTemplate;

    @FindBy(how = How.CSS, using = ".secondary-btn .push-right-10")
    FluentWebElement assignNewTaskButton;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(1)")
    FluentWebElement assignTaskFromScratchLink;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(2)")
    FluentWebElement assignTaskUsingATaskTemplateLink;

    @FindBy(how = How.CSS, using = ".glyphicon-eye-open")
    FluentWebElement taskTemplateEditIcon;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(1)")
    FluentWebElement tasksTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(2)")
    FluentWebElement tasksTemplateTab;

    @FindBy(how = How.CSS, using = ".first-time-import")
    FluentWebElement tasksBlankPageLabel;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement taskCreationSuccessMsg;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentList<FluentWebElement> taskList;

    public static By spinLoader = By.cssSelector(".ajax-loader");


    String taskTemplateEditIconName = "Task Template Edit Icon";
    String searchTaskTemplateName = "Search Task Template";
    String assignNewTaskButtonName = "Assign New Task Button";
    String assignTaskFromScratchLinkName = "Assign Task From Scratch Link";
    String assignTaskUsingATaskTemplateLinkName = "Assign Task Using A Task Template Link";
    String tasksTabName = "Tasks Tab";
    String tasksTemplateTabName = "Tasks Template Tab";
    String tasksBlankPageLabelName = "Tasks Blank Page Label";
    String taskCreationSuccessMsgName = "Task Creation Success Msg";
    String spinLoaderName = "Spin Loader";
    String taskListName = "Task List";


    public void searchTaskTemplate(String taskTemplateName) throws InterruptedException {
        FunctionLibrary.input(searchTaskTemplate,searchTaskTemplateName, taskTemplateName);
        Thread.sleep(1000);
    }

    public void clickOnEditIcon() {
        FunctionLibrary.click(taskTemplateEditIcon,taskTemplateEditIconName);
    }

    public void clickOnAssignTaskFromScratch() {
        FunctionLibrary.click(assignNewTaskButton, assignNewTaskButtonName);
        FunctionLibrary.click(assignTaskFromScratchLink, assignTaskFromScratchLinkName);
    }

    public Boolean isAssignNewTaskButtonPresent() {
        return FunctionLibrary.isElementPresent(assignNewTaskButton, assignNewTaskButtonName);
    }

    public void clickOnAssignTaskUsingATaskTemplate() {
        FunctionLibrary.click(assignNewTaskButton, assignNewTaskButtonName);
        FunctionLibrary.click(assignTaskUsingATaskTemplateLink, assignTaskUsingATaskTemplateLinkName);
    }

    public void clickOnTasksTab() {
        FunctionLibrary.click(tasksTab, tasksTabName);
    }

    public void clickOnTaskTemplateTab() {
        FunctionLibrary.click(tasksTemplateTab, tasksTemplateTabName);
    }


    public String getPageContentWhenThereIsNoTask() {
        return  FunctionLibrary.retrieveText(tasksBlankPageLabel, tasksBlankPageLabelName);
    }

    public String retrieveTaskCreationSuccessMsg() {
        return FunctionLibrary.retrieveText(taskCreationSuccessMsg, taskCreationSuccessMsgName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForLoaderToDissapear(spinLoader, spinLoaderName);
    }

    public List<String> getTheTasksListed() {
        return FunctionLibrary.retrieveTexts(taskList, taskListName);
    }
}
