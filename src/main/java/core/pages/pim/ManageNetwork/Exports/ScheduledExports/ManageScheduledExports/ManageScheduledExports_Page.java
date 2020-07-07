package core.pages.pim.ManageNetwork.Exports.ScheduledExports.ManageScheduledExports;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ManageScheduledExports_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.tertiary li")
    FluentList<FluentWebElement> tertiaryBar;

    @FindBy(how = How.CSS, using = ".secondary-btn .start-icon")
    FluentWebElement schedulerStartBtn;

    @FindBy(how = How.CSS, using = ".secondary-btn .stop-icon")
    FluentWebElement schedulerStopBtn;

    String tertiaryBarName = "";
    String schedulerStartBtnName = "";
    String schedulerStopBtnName = "";

    public void clickOnSchedule(){
        FunctionLibrary.click(tertiaryBar.get(1), tertiaryBarName);
    }

    public void clickOnNameDesc(){
        FunctionLibrary.click(tertiaryBar.get(2), tertiaryBarName);
    }

    public void clickOnSchedulerStartBtn(){
        FunctionLibrary.click(schedulerStartBtn, schedulerStartBtnName);
    }
}
