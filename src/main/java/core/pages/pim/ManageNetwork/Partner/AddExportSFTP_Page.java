package core.pages.pim.ManageNetwork.Partner;

import org.fluentlenium.core.FluentPage;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.How;

public class AddExportSFTP_Page extends FluentPage {


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

    @FindBy(how = How.CSS, using = ".secondary-btn")
    FluentWebElement testConnectionButton;

    @FindBy(how = How.CSS, using = ".modal-body .primary-btn")
    FluentWebElement saveAddressButton;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement infoMsgLabel;

    String sftpNameInputBoxName = "Sftp Name InputBox";
    String sftpHostInputBoxName = "Sftp Host InputBox";
    String sftpPortInputBoxName = "Sftp Port InputBox";
    String sftpPathInputBoxName = "Sftp Path InputBox";
    String sftpUsernameInputBoxName = "Sftp UserName InputBox";
    String sftpPasswordInputBoxName = "Sftp Password InputBox";
    String testConnectionButtonName = "Test Connection Button";
    String saveAddressButtonName = "Save Address Button";
    String infoMsgLabelName = "Info Msg Label";

    public void createPartnerSFTP(String sftpName, String host, String port, String path, String username, String password ) {
        FunctionLibrary.input(sftpNameInputBox, sftpNameInputBoxName, sftpName);
        FunctionLibrary.input(sftpHostInputBox, sftpHostInputBoxName, host);
        FunctionLibrary.input(sftpPortInputBox, sftpPortInputBoxName, port);
        FunctionLibrary.input(sftpPathInputBox, sftpPathInputBoxName, path);
        FunctionLibrary.input(sftpUsernameInputBox, sftpUsernameInputBoxName, username);
        FunctionLibrary.input(sftpPasswordInputBox, sftpPasswordInputBoxName, password);
        FunctionLibrary.click(testConnectionButton, testConnectionButtonName);
        FunctionLibrary.waitForTextToBePresent(testConnectionButton, testConnectionButtonName, "Save Address");
    }

    public String fetchInfoMsg() {
        return FunctionLibrary.retrieveText(infoMsgLabel, infoMsgLabelName);
    }

    public void clickOnSaveAddress() {
        FunctionLibrary.click(saveAddressButton, saveAddressButtonName);
    }
}
