package core.pages.pim.ManageNetwork.Exports.ExportList;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ExportList_Page extends FluentPage {


        @FindBy(how = How.NAME, using = "searchVal")
        FluentWebElement searchExportInputBox;

        @FindBy(how = How.CSS, using = ".qa-nwexports-list .glyphicon-eye-open")
        FluentWebElement exportEditIcon;

        @FindBy(how = How.CSS, using = ".qa-nwexports-list .table tbody tr")
        FluentList<FluentWebElement> exportDetailsList;

        @FindBy(how = How.CSS, using = ".qa-nwexports-list .table tbody tr td:nth-child(5)")
        FluentWebElement exportStatus;

        @FindBy(how = How.CSS, using = ".qa-nwexports-list .table tbody tr td:nth-child(1)")
        FluentList<FluentWebElement> exportNameList;


        String searchExportInputBoxName = "Search Export InputBox";
        String exportEditIconName = "export Edit Icon";
        String exportDetailsListName = "export Details List";
        String exportNameListName = "export Name List";
        String exportStatusName = "Export Status";


        public void searchexport(String exportName) throws InterruptedException {
            FunctionLibrary.input(searchExportInputBox, searchExportInputBoxName, exportName);
            Thread.sleep(1000);
        }

        public String getExportStatus() {
            FunctionLibrary.waitForTextToBePresent(exportStatus, exportStatusName, "EXPORTED");
            return FunctionLibrary.retrieveText(exportStatus, exportStatusName);
        }

        public List<String> fetchExports() {
            return FunctionLibrary.retrieveTexts(exportDetailsList, exportDetailsListName);
        }

        public void waitForPageToLoad() {
            FunctionLibrary.waitForElementToLoad(exportEditIcon, exportEditIconName);
        }

        public void openExport(String exportName) throws InterruptedException {
            searchexport(exportName);
            FunctionLibrary.click(exportEditIcon, exportEditIconName);
        }

    }




