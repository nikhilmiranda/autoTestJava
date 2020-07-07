package core.pages.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ActivityLog_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".parent-row")
    FluentList<FluentWebElement> activityLogsRow;

    @FindBy(how = How.CSS, using = ".parent-row td:nth-child(1)")
    FluentWebElement expandedLastLogIcon;

    @FindBy(how = How.CSS, using = ".expanded-row tbody tr")
    FluentWebElement expandedLogsRow;

    @FindBy(how = How.CSS, using = ".expanded-row thead th")
    FluentWebElement expandedLogsHeader;

    @FindBy(how = How.CSS, using = ".parent-row .expand-close-icon")
    FluentWebElement expandLastLogRow;

    @FindBy(how = How.CSS, using = ".modal-close-btn")
    FluentWebElement activityLogCloseIcon;

    String activityLogsRowName = "Activity Logs Row";
    String expandedLogsRowName = "Expanded Logs Row";
    String expandedLogsHeaderName = "Expanded Logs Header";
    String expandedLastLogIconName = "Expanded Last Log Icon Name";
    String activityLogCloseIconName = "Activity Log Close Icon";

    public String retrieveLatestActivityLog() {
       return FunctionLibrary.retrieveText(activityLogsRow.get(0), activityLogsRowName);
    }

    public String retrieveLatestDetailLog() {
        return FunctionLibrary.retrieveText(expandedLogsRow, expandedLogsHeaderName);
    }

    public String retrieveLatestDetailLogHeader() {
        return FunctionLibrary.retrieveText(expandedLogsHeader, expandedLogsHeaderName);
    }

    public void clickOnExpandIcon() {
        FunctionLibrary.click(expandedLastLogIcon, expandedLastLogIconName);
    }

    public void clickOnCloseIcon() {
        FunctionLibrary.click(activityLogCloseIcon, activityLogCloseIconName);
    }

}
