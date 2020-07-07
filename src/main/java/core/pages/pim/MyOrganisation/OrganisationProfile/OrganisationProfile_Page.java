package core.pages.pim.MyOrganisation.OrganisationProfile;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class OrganisationProfile_Page extends FluentPage {

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement organizationNameInputBox;

    @FindBy(how = How.CSS, using = ".save-profile-btn .primary-btn")
    FluentWebElement saveChangesButton;

    @FindBy(how = How.ID, using = "orgProfileImg")
    FluentWebElement orgImageInputField;

    @FindBy(how = How.NAME, using = "replace_with_organization_logo")
    FluentWebElement replaceWithOrgLogoCheckBox;

    @FindBy(how = How.CSS, using = ".profile-image")
    FluentWebElement orgImage;

    @FindBy(how = How.CSS, using = ".app-logo img")
    FluentWebElement appLogoUrl;

    String organizationNameInputBoxName = "Organization Name InputBox";
    String saveChangesButtonName = "Save Changes Button";
    String orgImageInputFieldName = "Org Image Input";
    String replaceWithOrgLogoCheckBoxName = "Replace With Org Logo CheckBox";
    String orgImageName = "Org Image URL";
    String appLogoUrlName = "Unbxd PIM logo";


    public void uploadOrgImage(String filePath) throws InterruptedException {
        orgImageInputField.fill().with(filePath);
        Thread.sleep(15000);
    }

    public void updateOrganisationName(String orgName) {
        FunctionLibrary.input(organizationNameInputBox, organizationNameInputBoxName, orgName);
        FunctionLibrary.click(saveChangesButton, saveChangesButtonName);
    }

    public void updatePimUiLogo() {
        FunctionLibrary.click(replaceWithOrgLogoCheckBox, replaceWithOrgLogoCheckBoxName);
        FunctionLibrary.click(saveChangesButton, saveChangesButtonName);
    }

    public String getOrganizationName() {
        return FunctionLibrary.retrieveAttributeValue(organizationNameInputBox, "value", organizationNameInputBoxName);
    }

    public String getOrgImageUrl() {
        return FunctionLibrary.retrieveAttributeValue(orgImage, "src", organizationNameInputBoxName);
    }

    public String getAppLogoImageUrl() {
        return FunctionLibrary.retrieveAttributeValue(appLogoUrl, "src", appLogoUrlName);
    }

    public void waitForOrganisationPageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(saveChangesButton, saveChangesButtonName);
    }
}
