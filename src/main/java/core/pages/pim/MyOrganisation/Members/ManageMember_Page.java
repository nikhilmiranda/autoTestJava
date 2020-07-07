package core.pages.pim.MyOrganisation.Members;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class ManageMember_Page extends FluentPage {

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement memberNameInputBox;

    @FindBy(how = How.NAME, using = "email")
    FluentWebElement emailInputBox;

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement updateButton;

    @FindBy(how = How.CSS, using = ".pill-item")
    FluentList<FluentWebElement> getRolesAssigned;

    @FindBy(how = How.CSS, using = ".drop-down-text")
    FluentWebElement labelWhenNoRolesIsAssigned;

    @FindBy(how = How.CSS, using = ".drop-down-input")
    FluentWebElement roleNameSearch;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentWebElement rolesPresentInRoleDropdown;

    @FindBy(how = How.CSS, using = ".profile-myorganisation .delete-entity .hand-pointer")
    FluentWebElement deleteMemberLink;

    @FindBy(how = How.CSS, using = ".btn.primary-btn:nth-child(2)")
    FluentWebElement confirmDeleteMemberButton;

    @FindBy(how = How.CSS, using = ".back-to-list")
    FluentWebElement backToMemberListingLink;

    String memberNameInputBoxName = "MemberNameInputBox";
    String emailInputBoxName = "Email InputBox";
    String updateButtonName = "Update Button";
    String getRolesAssignedName = "Roles Assigned";
    String labelWhenNoRolesIsAssignedName = "Label When No Roles Is Assigned";
    String roleNameSearchName = "Role Name Search";
    String rolesPresentInRoleDropdownName = "Select Role Label";
    String deleteMemberLinkName = "Delete Member Link";
    String confirmDeleteMemberButtonName = "Confirm Delete Member Button";
    String backToMemberListingLinkName = "Back To Member Listing Link";



    public String getMemberName() {
        return FunctionLibrary.retrieveAttributeValue(memberNameInputBox, "value", memberNameInputBoxName);

    }

    public List<String> getRolesAssigned() {
        return FunctionLibrary.retrieveTexts(getRolesAssigned, getRolesAssignedName);

    }

    public String getDefaultLabelWhenNoRoleIsAssigned() {
        return FunctionLibrary.retrieveText(labelWhenNoRolesIsAssigned, labelWhenNoRolesIsAssignedName);

    }

    public void assignRoleToMember(String roleName) throws InterruptedException {
        FunctionLibrary.click(labelWhenNoRolesIsAssigned, labelWhenNoRolesIsAssignedName);
        FunctionLibrary.input(roleNameSearch,roleNameSearchName, roleName);
        Thread.sleep(1000);
        FunctionLibrary.click(rolesPresentInRoleDropdown, rolesPresentInRoleDropdownName);

    }
    public void updateName(String memberName) {
        FunctionLibrary.clearField(memberNameInputBox, memberNameInputBoxName);
        FunctionLibrary.input(memberNameInputBox, memberNameInputBoxName, memberName);

    }

    public String getMemberEmail() {
      return FunctionLibrary.retrieveAttributeValue(emailInputBox, "value", emailInputBoxName);
    }

    public boolean isEmailEnabled() {
       return FunctionLibrary.isEnabled(emailInputBox, emailInputBoxName);
    }

    public void clickOnUpdate() {
        FunctionLibrary.click(updateButton, updateButtonName);
    }

    public void deleteMember() {
        ((JavascriptExecutor) getDriver())
                .executeScript("window.scrollTo(0, document.body.scrollHeight)");
        FunctionLibrary.waitForElementToBeClickable(deleteMemberLink, deleteMemberLinkName);
        FunctionLibrary.click(deleteMemberLink, deleteMemberLinkName);
        FunctionLibrary.click(confirmDeleteMemberButton, confirmDeleteMemberButtonName);
    }

    public void clickOnBackToMemberListingLink() {
        FunctionLibrary.click(backToMemberListingLink, backToMemberListingLinkName);
    }

    public void waitForManageMemberPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(deleteMemberLink, deleteMemberLinkName);
    }


}
