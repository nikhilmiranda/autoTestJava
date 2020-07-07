package core.pages.pim.ManageNetwork.Partner;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ManagePartner_Page extends FluentPage {


    @FindBy(how = How.CSS, using = ".qa-partner-edit .primary-btn")
    FluentWebElement updatePartnerButton;

    @FindBy(how = How.CSS, using = ".qa-partner-edit .add-sftp-txt")
    FluentWebElement addSFTPAddressLink;

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement partnerNameInputBox;

    @FindBy(how = How.NAME, using = "emailId")
    FluentWebElement partnerEmailInputBox;

    @FindBy(how = How.CSS, using = ".back-to-list")
    FluentWebElement backToList;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement infoMsg;

    @FindBy(how = How.CSS, using = ".partner-sftp-list .tr")
    FluentWebElement sftpDetails;

    @FindBy(how = How.CSS, using = ".partner-sftp-list span.link-text")
    FluentWebElement makeDefaultLink;

    @FindBy(how = How.CSS, using = ".partner-sftp-list .link-text")
    FluentWebElement editSFTPLink;

    @FindBy(how = How.CSS, using = ".partner-sftp-list .delete-txt")
    FluentWebElement deleteSFTPLink;

    String updatePartnerButtonName = "Update Partner Button";
    String addSFTPAddressLinkName = "Add SFTP Address Link";
    String partnerNameInputBoxName = "Partner Name InputBox";
    String partnerEmailInputBoxName = "Partner Email InputBox";
    String backToListName = "Back to list";
    String infoMsgName = "Info Message";
    String sftpDetailsName = "SFTP Details";
    String makeDefaultLinkName = "Make Default Link";
    String editSFTPLinkName = "Edit SFTP Link";
    String deleteSFTPLinkName = "Delete SFTP Link";



    public void clickOnUpdate(){
        FunctionLibrary.click(updatePartnerButton, updatePartnerButtonName);

    }

    public void enterPartnerName(String partnerName) {
        FunctionLibrary.input(partnerNameInputBox, partnerNameInputBoxName, partnerName);
    }

    public void clickOnAddNewSFTP(){
        FunctionLibrary.click(addSFTPAddressLink, addSFTPAddressLinkName);

    }

    public void clickOnBackToList(){
        FunctionLibrary.click(backToList, backToListName);

    }

    public String getPartnerName(){
        return FunctionLibrary.retrieveAttributeValue(partnerNameInputBox, "value", partnerNameInputBoxName);
    }

    public String getPartnerEmail(){
        return FunctionLibrary.retrieveAttributeValue(partnerEmailInputBox,"value", partnerEmailInputBoxName);
    }

    public Boolean isPartnerEmailDisabled(){
        return FunctionLibrary.isEnabled(partnerEmailInputBox, partnerEmailInputBoxName);
    }

    public String getInfoMessage() {
        return  FunctionLibrary.retrieveText(infoMsg, infoMsgName);
    }

    public String getSFTPDetails() {
        return  FunctionLibrary.retrieveText(sftpDetails, sftpDetailsName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(addSFTPAddressLink, addSFTPAddressLinkName);
    }

    public void clickOnMakeDefaultLink(){
        FunctionLibrary.click(makeDefaultLink, makeDefaultLinkName);

    }

    public void clickOnEditSFTPLink(){
        FunctionLibrary.click(editSFTPLink, editSFTPLinkName);

    }

    public void clickOnDeleteSFTPLink(){
        FunctionLibrary.click(deleteSFTPLink, deleteSFTPLinkName);

    }


}
