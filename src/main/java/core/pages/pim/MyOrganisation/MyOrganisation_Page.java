package core.pages.pim.MyOrganisation;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class MyOrganisation_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar li:nth-child(1) a")
    FluentWebElement  memberTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar li:nth-child(2) a")
    FluentWebElement  rolesTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar li:nth-child(3) a")
    FluentWebElement  organizationProfileTab;

    String memberTabName = "Member Tab";
    String rolesTabName = "Roles Tab";
    String organizationProfileTabName = "Organization Profile Tab";



    public void clickOnMemberTab()  {
        FunctionLibrary.click(memberTab, memberTabName);
    }

    public void clickOnRolesTab()  {
        FunctionLibrary.click(rolesTab, rolesTabName);
    }

    public void clickOnOrganisationProfileTab() {
        FunctionLibrary.click(organizationProfileTab, organizationProfileTabName);
    }

    public void waitForMyOrganizationPageToLoad() {
         FunctionLibrary.waitForElementToBeClickable(rolesTab, rolesTabName);
    }
}
