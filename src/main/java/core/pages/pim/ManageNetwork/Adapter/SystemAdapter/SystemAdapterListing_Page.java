package core.pages.pim.ManageNetwork.Adapter.SystemAdapter;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SystemAdapterListing_Page extends FluentPage {

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchAdapterInputBox;

    @FindBy(how = How.CSS, using = ".qa-sysadapters-list .view-adapter")
    FluentWebElement viewMappingLink;

    @FindBy(how = How.CSS, using = ".qa-sysadapters-list .adapter-name")
    FluentWebElement adapterNameLabel;

    @FindBy(how = How.CSS, using = ".qa-sysadapters-list .adapter-mapping-status")
    FluentWebElement adapterMappingPercentageLabel;

    @FindBy(how = How.CSS, using = ".qa-sysadapters-list .remaining-count")
    FluentWebElement adapterRemainingMappingCountLabel;

    @FindBy(how = How.CSS, using = ".qa-sysadapters-list .adapter-name")
    FluentList<FluentWebElement> adapterList;


    String viewMappingLinkName = "View Mapping Link";


    public void clickOnViewMappingLink() {
        FunctionLibrary.click(viewMappingLink, viewMappingLinkName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(viewMappingLink, viewMappingLinkName);
    }
}
