package core.pages.pim.Imports;

import core.components.pim.PrimaryButton;
import core.components.pim.SecondaryButton;
// import jdk.internal.dynalink.linker.LinkerServices;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

import java.util.ArrayList;
import java.util.List;

public class AddImportSFTP_Page extends FluentPage {

    @Page
    SecondaryButton secondaryButton;

    @Page
    PrimaryButton primaryButton;


    @FindBy(how = How.CSS, using = ".qa-impSftp-list")
    FluentWebElement importSFTPListPage;

    @FindBy(how = How.CSS, using = ".sftp-node .sftp-header")
    FluentList<FluentWebElement> sftpList;

    @FindBys( {
            @FindBy(how = How.CSS, using = ".modal-content"),
            @FindBy(how = How.NAME, using = "name")
    } )
    FluentWebElement sftpNameInputBox;

    @FindBy(how = How.NAME, using = "host")
    FluentWebElement sftpHostInputBox;

    @FindBy(how = How.NAME, using = "port")
    FluentWebElement sftpPortInputBox;

    @FindBy(how = How.NAME, using = "path")
    FluentWebElement sftpPathInputBox;

    @FindBy(how = How.NAME, using = "username")
    FluentWebElement sftpUsernameInputBox;

    @FindBy(how = How.NAME, using = "password")
    FluentWebElement sftpPasswordInputBox;

    @FindBy(how = How.CSS, using = ".modal-body .secondary-btn")
    FluentWebElement testConnectionButton;

    @FindBy(how = How.CSS, using = ".modal-body .primary-btn")
    FluentWebElement saveAddressButton;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement infoMsgLabel;

    @FindBy(how = How.CSS, using = ".first-time-import")
    FluentWebElement noResultFound;

    @FindBy(how = How.CSS, using = ".delete-link")
    FluentWebElement deleteSFTPLink;

    @FindBy(how = How.CSS, using = ".edit-link")
    FluentWebElement editSFTPLink;

    String importSFTPListPageName = "Import SFTP List Page";
    String sftpListName = "Sftp List";
    String sftpNameInputBoxName = "Sftp Name InputBox";
    String sftpHostInputBoxName = "Sftp Host InputBox";
    String sftpPortInputBoxName = "Sftp Port InputBox";
    String sftpPathInputBoxName = "Sftp Path InputBox";
    String sftpUsernameInputBoxName = "Sftp UserName InputBox";
    String sftpPasswordInputBoxName = "Sftp Password InputBox";
    String testConnectionButtonName = "Test Connection Button";
    String saveAddressButtonName = "Save Address Button";
    String infoMsgLabelName = "Info Msg Label";
    String noResultFoundName = "No Result Found";
    String deleteSFTPLinkName = "Delete SFTP Link";
    String editSFTPLinkName = "Edit SFTP Link";

    public void createImportSFTP(String sftpName, String host, String port, String path, String username, String password ) {
        secondaryButton.clickOnSecondaryButton();
        FunctionLibrary.input(sftpNameInputBox, sftpNameInputBoxName, sftpName);
        FunctionLibrary.input(sftpHostInputBox, sftpHostInputBoxName, host);
        FunctionLibrary.input(sftpPortInputBox, sftpPortInputBoxName, port);
        FunctionLibrary.input(sftpPathInputBox, sftpPathInputBoxName, path);
        FunctionLibrary.input(sftpUsernameInputBox, sftpUsernameInputBoxName, username);
        FunctionLibrary.input(sftpPasswordInputBox, sftpPasswordInputBoxName, password);
        FunctionLibrary.click(testConnectionButton, testConnectionButtonName);
        primaryButton.clickOnPrimaryModalButton();

    }

    public List<String> fetchSFTPValues() {
        List<String> sftpValues = new ArrayList<>();
        sftpValues.add(FunctionLibrary.retrieveText(sftpNameInputBox, sftpNameInputBoxName));
        sftpValues.add(FunctionLibrary.retrieveText(sftpHostInputBox, sftpHostInputBoxName));
        sftpValues.add(FunctionLibrary.retrieveText(sftpPortInputBox, sftpPortInputBoxName));
        sftpValues.add(FunctionLibrary.retrieveText(sftpPathInputBox, sftpPathInputBoxName));
        sftpValues.add(FunctionLibrary.retrieveText(sftpUsernameInputBox, sftpUsernameInputBoxName));
        sftpValues.add(FunctionLibrary.retrieveText(sftpPasswordInputBox, sftpPasswordInputBoxName));
        return sftpValues;

    }

    public String fetchInfoMsg() {
        return FunctionLibrary.retrieveText(infoMsgLabel, infoMsgLabelName);
    }

    public void clickOnSaveAddress() {
        FunctionLibrary.click(saveAddressButton, saveAddressButtonName);
    }

    public void waitForPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(importSFTPListPage, importSFTPListPageName);

    }

    public String fetchBlankPageContent() {
       return FunctionLibrary.retrieveText(noResultFound, noResultFoundName);

    }

    public List<String> fetchSFTPList() {
       return FunctionLibrary.retrieveTexts(sftpList, sftpListName);

    }

    public void clickOnEditSFTP() {
        FunctionLibrary.click(editSFTPLink, editSFTPLinkName);
    }

    public void clickOnDeleteSFTP() {
        FunctionLibrary.click(deleteSFTPLink, deleteSFTPLinkName);
        primaryButton.clickOnPrimaryModalButton();

    }

}
