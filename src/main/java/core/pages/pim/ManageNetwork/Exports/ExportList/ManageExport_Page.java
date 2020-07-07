package core.pages.pim.ManageNetwork.Exports.ExportList;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ManageExport_Page extends FluentPage {



    @FindBy(how = How.CSS, using = ".qa-nwexport-view .export-tag-item:nth-child(1) .tag-value")
    FluentWebElement partnerTagValueLabel;

    @FindBy(how = How.CSS, using = ".qa-nwexport-view .export-tag-item:nth-child(2) .tag-value")
    FluentWebElement adapterTagValueLabel;

    @FindBy(how = How.CSS, using = ".qa-nwexport-view .export-tag-item:nth-child(3) .tag-value")
    FluentWebElement formatsTagValueLabel;

    @FindBy(how = How.CSS, using = ".qa-nwexport-view .secondary-btn .caret")
    FluentList<FluentWebElement> actionsButton;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(1)")
    FluentList<FluentWebElement> reExportLink;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(2)")
    FluentList<FluentWebElement> emailLink;

    @FindBy(how = How.CSS, using = ".export-missing-summary")
    FluentWebElement infoMsgLabel;

    @FindBy(how = How.CSS, using = ".export-missing-summary div")
    FluentWebElement viewReportToAssignTaskLink;

   // ---------------------------------------------------------------




//    @FindBy(how = How.CSS, using = ".list-item:nth-child(3)")
//    FluentList<FluentWebElement> actionsButton;
//
//    @FindBy(how = How.CSS, using = ".list-item:nth-child(4)")
//    FluentList<FluentWebElement> actionsButton;
//
//    @FindBy(how = How.CSS, using = ".list-item:nth-child(5)")
//    FluentList<FluentWebElement> actionsButton;

    String partnerTagValueLabelName = "Partner Tag Value Label";
    String adapterTagValueLabelName = "Adapter Tag Value Label";
    String formatsTagValueLabelName = "Formats Tag Value Label";
    String actionsButtonName = "Actions Button";
    String reExportLinkName = "Re Export Link";
    String emailLinkName = "Email Link";

    public String getPartnerValue() {
        return FunctionLibrary.retrieveText(partnerTagValueLabel, partnerTagValueLabelName);
    }

    public String getAdapterValue() {
        return FunctionLibrary.retrieveText(adapterTagValueLabel, adapterTagValueLabelName);
    }

    public String getFormatsValue() {
        return FunctionLibrary.retrieveText(formatsTagValueLabel, formatsTagValueLabelName);
    }




}
