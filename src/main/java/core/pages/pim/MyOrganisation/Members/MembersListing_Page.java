package core.pages.pim.MyOrganisation.Members;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;


public class MembersListing_Page extends FluentPage {


    @FindBy(how = How.CSS, using = ".inline-modal-header .secondary-btn")
    FluentWebElement createMemberButton;

    @FindBy(how = How.CSS, using = ".modal-btn-close-btn")
    FluentWebElement createMemberModalCloseButton;

    @FindBy(how = How.NAME, using = "memberEmail")
    FluentWebElement memberEmailInputBox;

    @FindBy(how = How.CSS, using = ".text-danger")
    FluentWebElement emailValidationMessageLabel;

    @FindBy(how = How.CSS, using = ".message-box.error")
    FluentWebElement errorMessage;

    @FindBy(how = How.CSS, using = ".element-memberRole .drop-down-button .caret")
    FluentWebElement roleDropDownIcon;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> rolesList;

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement proceedButton;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement memberSearchInputBox;

    @FindBy(how = How.CSS, using = ".table tbody tr")
    FluentList<FluentWebElement> memberDetailsList;

    @FindBy(how = How.CSS, using = ".table tbody tr .glyphicon-eye-open")
    FluentWebElement memberEditIcon;



    String createMemberButtonName = "Create Member";
    String createMemberModalCloseButtonName = "Create Member Modal Close Button";
    String memberEmailInputBoxName = "Member Email";
    String emailValidationMessageLabelName = "Email Validation Message Label";
    String errorMessageName = "Error Message";
    String roleDropDownIconName = "Drop Down";
    String rolesListName = "Roles List";
    String proceedButtonName = "Proceed";
    String memberSearchInputBoxName = "Search";
    String memberDetailsListName = "Member Details List";
    String memberEditIconName = "memberEditIcon";



    public List<String> fetchMembersDetails() {
        return FunctionLibrary.retrieveTexts(memberDetailsList, memberDetailsListName);

    }

    public void editMember(String memberName) {
        for(FluentWebElement ele : memberDetailsList){
            if(ele.find("td").get(0).equals(memberName)){
                ele.find(".glyphicon-eye-open").click();
            }
        }
    }

    public void createMember(String emailID, String role) throws InterruptedException {
        FunctionLibrary.click(createMemberButton, createMemberButtonName);
        FunctionLibrary.input(memberEmailInputBox, memberDetailsListName, emailID);
        FunctionLibrary.click(roleDropDownIcon, roleDropDownIconName);
        FunctionLibrary.selectDropDownValueByName(role, rolesList, rolesListName);
        FunctionLibrary.click(memberEmailInputBox, memberEmailInputBoxName);
        FunctionLibrary.click(proceedButton, proceedButtonName);

    }

    public Boolean isCreateMemberButtonDisabled() {
        return FunctionLibrary.isElementPresent(createMemberButton, createMemberButtonName);

    }

    public void closeCreateMemberModal() {
        FunctionLibrary.click(createMemberModalCloseButton, createMemberModalCloseButtonName);
    }

    public void createMember(String emailID) {
        FunctionLibrary.click(createMemberButton, createMemberButtonName);
        FunctionLibrary.input(memberEmailInputBox, memberDetailsListName, emailID);
        FunctionLibrary.click(proceedButton, proceedButtonName);
    }

    public void searchMember(String memberName) throws InterruptedException {
        FunctionLibrary.input(memberSearchInputBox, memberSearchInputBoxName, memberName);
        Thread.sleep(1000);
    }

    public void waitForMemberListingPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(createMemberButton, createMemberButtonName);
    }

    public String getEmailErrorValidationMessage() {
        return FunctionLibrary.retrieveText(emailValidationMessageLabel, emailValidationMessageLabelName);
    }

    public String getErrorMsgOnReCreatingAnExistingMember() {
        return FunctionLibrary.retrieveText(errorMessage, errorMessageName);
    }

    public void clickOnMemberEditIcon() {
        FunctionLibrary.click(memberEditIcon, memberEditIconName);
    }

    public Boolean isCreateMemberButtonVisible() {
        return FunctionLibrary.isElementDisplayed(createMemberButton, createMemberButtonName);

    }
}
