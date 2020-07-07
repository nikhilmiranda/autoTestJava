package core.pages.pim.ManageNetwork;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ManageNetwork_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(1) a")
    FluentWebElement productsTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(2) a")
    FluentWebElement partnerTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(3) a")
    FluentWebElement adaptersTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(5) a")
    FluentWebElement exportsTab;

    String productsTabName = "Products Tab";
    String partnerTabName = "Partner Tab";
    String adaptersTabName = "Adapters Tab";
    String exportsTabName = "Exports Tab";

    public void clickOnProductsTab() {
        FunctionLibrary.click(productsTab, productsTabName);
    }

    public void clickOnAdapterTab() {
        FunctionLibrary.click(adaptersTab, adaptersTabName);
    }

    public void clickOnPartnerTab() {
        FunctionLibrary.click(partnerTab, partnerTabName);
    }

    public void clickOnExportsTab() {
        FunctionLibrary.click(exportsTab, exportsTabName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToLoad(productsTab, productsTabName);
    }
}
