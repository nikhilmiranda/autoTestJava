package core.pages.pim.Imports;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ManageImports_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(5) a")
    FluentWebElement importSftpListTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(3) a")
    FluentWebElement importAdaptersTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(2) a")
    FluentWebElement scheduledImportsTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.primary li:nth-child(1) a")
    FluentWebElement importTab;

    String importSftpListTabName = "Import Sftp List Tab";
    String importAdapterTabName = "Import Adapter Tab";
    String scheduledImportTabName = "Scheduled Import";
    String importTabName = "Import";


    public void clickOnImportSftpListTab() {
        FunctionLibrary.click(importSftpListTab, importSftpListTabName);
    }

    public void clickOnImportAdapterTab() {
        FunctionLibrary.click(importAdaptersTab, importAdapterTabName);
    }

    public void clickOnScheduledImportTab() {
        FunctionLibrary.click(scheduledImportsTab, scheduledImportTabName);
    }

    public void clickOnImportTab() {
        FunctionLibrary.click(importTab, importTabName);
    }
}
