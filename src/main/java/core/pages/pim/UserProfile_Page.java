package core.pages.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class UserProfile_Page extends FluentPage {

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement memberNameInputBox;

    @FindBy(how = How.NAME, using = "email")
    FluentWebElement emailIDInputBox;

    @FindBy(how = How.ID, using = "userProfileImg")
    FluentWebElement userProfileImageInput;

    @FindBy(how = How.CSS, using = ".profile-image")
    FluentWebElement userProfileImageDisplay;

    @FindBy(how = How.CSS, using = ".save-profile-btn .primary-btn")
    FluentWebElement saveChangesButton;

    String memberNameInputBoxName = "Member Name InputBox";
    String emailIDInputBoxName    = "EmailID InputBox";
    String userProfileImageName   = "User Profile Image";
    String saveChangesButtonName  = "Save Changes Button";
    String userProfileImageDisplayName = "User Profile Image Display";



    public String getMemberName(){
      return FunctionLibrary.retrieveAttributeValue(memberNameInputBox, "value", memberNameInputBoxName);

    }

    public String getMemberEmailId(){
        return FunctionLibrary.retrieveAttributeValue(emailIDInputBox, "value", emailIDInputBoxName);

    }

    public boolean isEmailEnabled(){
       return FunctionLibrary.isEnabled(emailIDInputBox, emailIDInputBoxName);

    }

    public void clickOnSaveChanges() {
        FunctionLibrary.click(saveChangesButton, saveChangesButtonName);
    }

    public void updateName(String updatedName) {
        FunctionLibrary.input(memberNameInputBox, memberNameInputBoxName, updatedName);
        clickOnSaveChanges();
    }



    public void uploadProfileImg(String filePath) throws InterruptedException {
        userProfileImageInput.fill().with(filePath);
        Thread.sleep(10000);
        clickOnSaveChanges();
    }

    public String getProfileImageUrl() {
        return FunctionLibrary.retrieveAttributeValue(userProfileImageDisplay, "src", userProfileImageDisplayName);
    }

    public void waitForUserProfilePageToLoad() throws InterruptedException {
        FunctionLibrary.waitForElementToBeClickable(saveChangesButton, saveChangesButtonName);
        FunctionLibrary.waitForPageToLoad();
    }

}
