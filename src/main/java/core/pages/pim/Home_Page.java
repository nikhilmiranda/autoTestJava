package core.pages.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class Home_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".sprite.user-icon")
    FluentWebElement userMenuIcon;

    @FindBy(how = How.CSS, using = ".dd-item:nth-child(2) .inline div")
    FluentWebElement userNameLabel;

    @FindBy(how = How.CSS, using = ".dd-item:nth-child(2) .thumbnail-image")
    FluentWebElement profileImageDisplay;

    @FindBy(how = How.XPATH, using = "//div[contains(text(),'autoTEST')]")
    FluentWebElement organizationNameLabel;

    @FindBy(how = How.CSS, using = ".dd-item:nth-child(2) a")
    FluentWebElement editProfileLink;

    @FindBy(how = How.CSS, using = ".dd-item:nth-child(3) a")
    FluentWebElement viewOrganizationLink;

    @FindBy(how = How.CSS, using = ".dd-item:nth-child(4) a")
    FluentWebElement workflowLink;

    @FindBy(how = How.CSS, using = ".dd-item:nth-child(4) a")
    FluentWebElement userGuideLink;

    @FindBy(how = How.CSS, using = ".log-out-text")
    FluentWebElement logoutLink;

    @FindBy(how = How.CSS, using = ".nabar-tab-menu-item")
    FluentList<FluentWebElement> tabsList;

    @FindBy(how = How.CSS, using = ".nabar-tab-menu-item:nth-child(1) a")
    FluentWebElement overviewTab;

    @FindBy(how = How.CSS, using = ".nabar-tab-menu-item:nth-child(2) a")
    FluentWebElement productsTab;

    @FindBy(how = How.CSS, using = ".nabar-tab-menu-item:nth-child(3) a")
    FluentWebElement assetsTab;

    @FindBy(how = How.CSS, using = ".nabar-tab-menu-item:nth-child(4) .caret")
    FluentWebElement manageTab;

    @FindBy(how = How.CSS, using = ".nabar-tab-menu-item:nth-child(5) a")
    FluentWebElement networkTab;

    @FindBy(how = How.CSS, using = ".help-component:nth-child(2) .secondary-btn")
    FluentWebElement importProductsTab;

    @FindBy(how = How.CSS, using = ".help-component:nth-child(3) .secondary-btn")
    FluentWebElement setUpOrganizationTab;

    @FindBy(how = How.CSS, using = ".help-component:nth-child(4) .secondary-btn")
    FluentWebElement createPropertiesTab;

    @FindBy(how = How.CSS, using = ".list-item")
    FluentList<FluentWebElement> subMenuList;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(1) a")
    FluentWebElement propertiesSubMenu;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(2) a")
    FluentWebElement catalogsSubMenu;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(3) a")
    FluentWebElement importsSubMenu;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(4) a")
    FluentWebElement tasksSubMenu;

    @FindBy(how = How.CSS, using = ".list-item:nth-child(5) a")
    FluentWebElement categorySubMenu;



    String userMenuIconName = "User Menu Icon";
    String userNameLabelName = "User Name Label";
    String profileImageDisplayName = "Profile Image Display";
    String organizationNameLabelName = "Organization Name Label";
    String editProfileLinkName = "Edit Profile Link";
    String viewOrganizationLinkName = "View Organization Link";
    String workflowLinkName = "Workflow Link";
    String userGuideLinkName = "User Guide Link";
    String logoutLinkName = "Logout Link";
    String tabsListName = "Tabs List";
    String overviewTabName = "Overview Tab";
    String productsTabName = "Products Tab";
    String assetsTabName = "Assets Tab";
    String manageTabName = "Manage Tab";
    String networkTabName = "Network Tab";
    String importProductsTabName = "Import Products Tab";
    String setUpOrganizationTabName = "SetUp Organization Tab";
    String createPropertiesTabName = "Create Properties Tab";
    String subMenuListName = "Sub Menu List";
    String propertiesSubMenuName = "Properties SubMenu";
    String catalogsSubMenuName = "Catalogs SubMenu";
    String importsSubMenuName = "Imports SubMenu";
    String tasksSubMenuName = "Tasks SubMenu";
    String categorySubMenuName = "Tasks SubMenu";


    public void clickOnOverviewTab() {
        FunctionLibrary.click(overviewTab, overviewTabName);
    }

    public void clickOnProductsTab() {
        FunctionLibrary.click(productsTab, productsTabName);
    }

    public void clickOnAssetsTab() {
        FunctionLibrary.click(assetsTab, assetsTabName);
    }

    public void clickOnManageTab() {
        FunctionLibrary.click(manageTab, manageTabName);
    }

    public Boolean isManageTabDisplayed() {
        return FunctionLibrary.isElementPresent(manageTab, manageTabName);
    }

    public void clickOnNetworkTab() {
        FunctionLibrary.click(networkTab, networkTabName);
    }

    public void clickOnProperties() {
        FunctionLibrary.click(propertiesSubMenu, propertiesSubMenuName);
    }


    public Boolean isSubMenuDisplayed(String submenu) {
        for(FluentWebElement ele : tabsList){
            if(FunctionLibrary.retrieveText(ele, tabsListName).equalsIgnoreCase("Manage")){
                FunctionLibrary.click(ele, tabsListName);
                for(FluentWebElement subMenu : subMenuList){
                    if(FunctionLibrary.retrieveText(subMenu, subMenuListName).equalsIgnoreCase(submenu)){
                        FunctionLibrary.click(ele, tabsListName);
                        return true;
                    }
                }
                FunctionLibrary.click(ele, tabsListName);
                return false;
            }
        }
        return false;
    }

    public Boolean isMenuDisplayed(String menu) {
        for(FluentWebElement ele : tabsList){
            if(FunctionLibrary.retrieveText(ele, tabsListName).equalsIgnoreCase(menu))
                return true;
        }
        return false;
    }

//    public Boolean isPropertiesTabDisplayed() {
//       if (FunctionLibrary.isElementPresent(manageTab, manageTabName).booleanValue()){
//           FunctionLibrary.click(manageTab, manageTabName);
//           return FunctionLibrary.isElementPresent(propertiesSubMenu, propertiesSubMenuName);
//       }
//            return FunctionLibrary.isElementPresent(manageTab, manageTabName).booleanValue();
//    }


    public Boolean isNetworkTabDisplayed() {
        return FunctionLibrary.isElementPresent(networkTab, networkTabName);

    }


    public void clickOnCatalogs() {
        FunctionLibrary.click(catalogsSubMenu, catalogsSubMenuName);
    }

    public void clickOnImports() {
        FunctionLibrary.click(importsSubMenu, importsSubMenuName);
    }

    public void clickOnTasks() {
        FunctionLibrary.click(tasksSubMenu, tasksSubMenuName);
    }

    public void clickOnCategoy() {
        FunctionLibrary.click(categorySubMenu, categorySubMenuName);
    }

    public void clickOnUserMenuIcon() {
        FunctionLibrary.click(userMenuIcon, userMenuIconName);
    }
    public String getUserName() {
        return FunctionLibrary.retrieveText(userNameLabel, userNameLabelName);
    }

    public String getProfileImageURL() {
        return FunctionLibrary.retrieveAttributeValue(profileImageDisplay, "src", profileImageDisplayName);
    }

    public String getOrganisationName() {
        return FunctionLibrary.retrieveText(organizationNameLabel, organizationNameLabelName);
    }

    public void clickOnEditProfile() {
        FunctionLibrary.click(editProfileLink, editProfileLinkName);
    }

    public Boolean isViewOrganizationPresent() {
        return FunctionLibrary.isElementPresent(viewOrganizationLink, viewOrganizationLinkName);
    }

    public void clickOnViewOrganization() {
        FunctionLibrary.click(viewOrganizationLink, viewOrganizationLinkName);;
    }

    public void clickOnWorkflow() {
        FunctionLibrary.click(workflowLink, workflowLinkName);;
    }

    public void clickOnUserGuide() {
        FunctionLibrary.click(userGuideLink, userGuideLinkName);;
    }

    public void clickOnLogout() {
        FunctionLibrary.click(logoutLink, logoutLinkName);
    }

    public void clickOnImportProducts() {
        FunctionLibrary.click(importProductsTab, importProductsTabName);
    }

    public void clickOnSetUpOraganisations() {
        FunctionLibrary.click(setUpOrganizationTab, setUpOrganizationTabName);
    }

    public void clickOnCreateProperties() {
        FunctionLibrary.click(createPropertiesTab, createPropertiesTabName);
    }

}
