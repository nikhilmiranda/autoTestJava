package core.pages.pim.WorkFlow.Nodes;

import core.components.pim.StaticDropDown;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.concurrent.TimeUnit;

public class FilterNode_Page extends FluentPage {

    @Page
    StaticDropDown staticDropDown;

    @FindBy(how = How.CSS, using = ".drop-down-button .caret")
    FluentList<FluentWebElement> staticDDIcons;

    String staticDDIconsName = "Static DD Icons";

    public void configureFilterNode() throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        FunctionLibrary.click(staticDDIcons.get(1), staticDDIconsName);
        staticDropDown.selectStaticDropDownValue("Property");
        FunctionLibrary.click(staticDDIcons.get(2), staticDDIconsName);
        staticDropDown.selectStaticDropDownValue("Color");
        FunctionLibrary.click(staticDDIcons.get(3), staticDDIconsName);
        staticDropDown.selectStaticDropDownValue("doesn't equal");
        FunctionLibrary.click(staticDDIcons.get(4), staticDDIconsName);
        staticDropDown.selectStaticDropDownValue("Red");
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
    }

    public void configureFilterNetworkNode() throws InterruptedException {
        getDriver().manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        FunctionLibrary.click(staticDDIcons.get(1), staticDDIconsName);
        staticDropDown.selectStaticDropDownValue("Property");
        FunctionLibrary.click(staticDDIcons.get(2), staticDDIconsName);
        staticDropDown.selectStaticDropDownValue("Color");
        FunctionLibrary.click(staticDDIcons.get(3), staticDDIconsName);
        staticDropDown.selectStaticDropDownValue("doesn't equal");
        FunctionLibrary.click(staticDDIcons.get(4), staticDDIconsName);
        staticDropDown.selectStaticDropDownValue("Red");
        getDriver().manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
    }
}
