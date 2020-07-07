package core.pages.pim.ManageNetwork;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ProductReadinessReport_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".readiness-report .percent-col")
    FluentWebElement readinessPercentageLabel;

    @FindBy(how = How.CSS, using = ".readiness-report .export-task-summary .missing-text")
    FluentWebElement missingPropertiesCountLabel;

    @FindBy(how = How.CSS, using = ".readiness-report .export-tag-item:nth-child(1) .tag-value")
    FluentWebElement partnerTagValueLabel;

    @FindBy(how = How.CSS, using = ".readiness-report .export-tag-item:nth-child(2) .tag-value")
    FluentWebElement adapterTagValueLabel;

    @FindBy(how = How.CSS, using = ".readiness-report .secondary-btn")
    FluentWebElement assignTaskButton;

    @FindBy(how = How.NAME, using = "taskName")
    FluentWebElement taskNameInputBox;

    @FindBy(how = How.CSS, using = ".pills-add-icon")
    FluentWebElement addTaskIcon;

    @FindBy(how = How.CSS, using = ".drop-down-search-container input")
    FluentWebElement memberOrRoleSearchInputBox;

    @FindBy(how = How.CSS, using = ".expandable-item:nth-child(1) .expand-toggle.closed")
    FluentWebElement expandRoles;

    @FindBy(how = How.CSS, using = ".expandable-item:nth-child(2) .expand-toggle.closed")
    FluentWebElement expandMembers;

    @FindBy(how = How.CSS, using = ".expandable-item:nth-child(1) li")
    FluentWebElement selectRole;

    @FindBy(how = How.CSS, using = ".expandable-item:nth-child(2) li")
    FluentWebElement selectMember;





    @FindBy(how = How.CSS, using = ".qa-nwexport-view .export-tag-item:nth-child(3) .tag-value")
    FluentWebElement formatsTagValueLabel;

    @FindBy(how = How.CSS, using = ".qa-nwexport-view .secondary-btn .caret")
    FluentList<FluentWebElement> actionsButton;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(1)")
    FluentList<FluentWebElement> reExportLink;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(2)")
    FluentList<FluentWebElement> emailLink;




    String addTaskIconName = "addTaskIconName";
    String expandMembersName = "expandMembersName";
    String memberOrRoleSearchInputBoxName = "memberOrRoleSearchInputBoxName";
    String selectMemberName = "selectMemberName";






    public void assignTaskToMember(String memberName) throws InterruptedException {
        FunctionLibrary.click(addTaskIcon, addTaskIconName);
        FunctionLibrary.click(expandMembers, expandMembersName);
        FunctionLibrary.input(memberOrRoleSearchInputBox, memberOrRoleSearchInputBoxName, memberName);
        Thread.sleep(1000);
        FunctionLibrary.click(selectMember, selectMemberName);
        FunctionLibrary.click(addTaskIcon, addTaskIconName);
    }
}
