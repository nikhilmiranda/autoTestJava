package core.pages.pim.ManageNetwork.Exports.ScheduledExports.ManageScheduledExports;

import core.components.pim.PrimaryButton;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NameAndDescription_Page extends FluentPage {

    @Page
    PrimaryButton primaryButton;

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement schedulerNameInputBox;

    @FindBy(how = How.NAME, using = "partner")
    FluentWebElement partnerNameInputBox;

    @FindBy(how = How.NAME, using = "desc")
    FluentWebElement schedulerDescInputBox;


    String schedulerNameInputBoxName = "schedulerNameInputBox";
    String partnerNameInputBoxName = "partnerNameInputBox";
    String schedulerDescInputBoxName = "schedulerDescInputBox";

    public void updatePropertyName(String updatedSchedulerName) {
        FunctionLibrary.clearField(schedulerNameInputBox, schedulerNameInputBoxName);
        FunctionLibrary.input(schedulerNameInputBox, schedulerNameInputBoxName, updatedSchedulerName);
        primaryButton.clickOnPrimaryButton();
    }


    public String getSchedulerName(){
        return FunctionLibrary.retrieveAttributeValue(schedulerNameInputBox, "value", schedulerNameInputBoxName);
    }

    public String getSchedulerDescription(){
        return FunctionLibrary.retrieveText(schedulerDescInputBox, schedulerDescInputBoxName);
    }

    public String getPartnerName(){
        return FunctionLibrary.retrieveText(partnerNameInputBox, partnerNameInputBoxName);
    }

    public boolean isPartnerNameEnabled(){
        return FunctionLibrary.isEnabled(partnerNameInputBox, partnerNameInputBoxName);
    }

}
