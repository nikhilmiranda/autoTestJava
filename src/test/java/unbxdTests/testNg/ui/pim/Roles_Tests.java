package unbxdTests.testNg.ui.pim;

import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.MyOrganisation.Members.MembersListing_Page;
import core.pages.pim.MyOrganisation.MyOrganisation_Page;
import core.pages.pim.MyOrganisation.Roles.ManageRole_Page;
import core.pages.pim.MyOrganisation.Roles.RolesListing_Page;
import lib.FunctionLibrary;
import lib.JsonReader;
import lib.constants.Notification;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;
import java.util.Arrays;

public class Roles_Tests extends BaseTest {

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    String jsonPathPropertyData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/property.json" ;
    String propertyTypeData = "Property Data";
    int totalPropertyDataRow = 1;
    int totalPropertyColumnEntry = 6;

    String emailId1, password1, roleName1, memberName1, updatedRoleName1, emailId2, password2, roleName2, updatedRoleName2, updatedPropertyName;


    {
        try {
            emailId1 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password1 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            roleName1 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][3].toString();
            updatedRoleName1 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][4].toString();
            memberName1 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][5].toString();

            emailId2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][0].toString();
            password2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][1].toString();
            roleName2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][3].toString();
            updatedRoleName2 = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[1][4].toString();

            updatedPropertyName = JsonReader.getdata(jsonPathPropertyData, propertyTypeData, totalPropertyDataRow, totalPropertyColumnEntry )[0][2].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    RolesListing_Page rolesPage;

    @Page
    ManageRole_Page manageRolePage;

    @Page
    MyOrganisation_Page myOrganizationPage;

    @Page
    MembersListing_Page membersPage;

    @AfterMethod
    public void navigateToRolesListingPage() throws InterruptedException {

        myOrganizationPage.clickOnRolesTab();
        rolesPage.waitForRoleListingPageToLoad();
    }

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId1, password1);
        homePage.clickOnUserMenuIcon();
        homePage.clickOnViewOrganization();
        myOrganizationPage.clickOnRolesTab();

    }

    @Test(priority = 1)
    public void verifyRolesPage() {
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("tab=roles"));

    }
    @Test(priority = 2)
    public void verifyRoleCreation() throws InterruptedException {
        getDriver().navigate().refresh();
        rolesPage.createRole(roleName2);
        manageRolePage.waitForManageRoleToAppear();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("roleId"));
    }

    @Test(priority = 3)
    public void verifyRoleName() throws InterruptedException {
        rolesPage.clickOnRoleEditIcon(roleName2);
        manageRolePage.clickOnInfoTab();
        Assert.assertEquals(manageRolePage.getRoleName(), roleName2);

    }

    @Test(priority = 4)
    public void verifyTheMembersDisplayedInMemberSection() throws InterruptedException {
        rolesPage.clickOnRoleEditIcon(roleName2);
        manageRolePage.waitForManageRoleToAppear();
        Assert.assertEquals(manageRolePage.getTheMembersDetails(), Arrays.asList(memberName1+" "+roleName1+" Active "+emailId1));

    }

    @Test(priority = 5, enabled = false)
    public void verifyThePropertyPermissions() throws InterruptedException {
        rolesPage.clickOnRoleEditIcon(roleName2);
        manageRolePage.waitForManageRoleToAppear();
        manageRolePage.clickOnPropertyPermissionsTab();
        Assert.assertEquals(manageRolePage.getPropertyPermissionDetails(), Arrays.asList(updatedPropertyName +"\nManage"));

    }

    @Test(priority = 6)
    public void verifyTheSystemPermissions() throws InterruptedException {
        rolesPage.clickOnRoleEditIcon(roleName2);
        manageRolePage.waitForManageRoleToAppear();
        manageRolePage.clickOnSystemPermissionsTab();
        Assert.assertEqualsNoOrder(manageRolePage.getSystemPermissionName().toArray(), Arrays.asList("Organization setup", "Import", "Property Definitions", "Product Management", "Network", "Task", "Catalog", "Category", "Assets", "Workflow").toArray());

    }

    @Test(priority = 7)
    public void addMemberToRole() throws InterruptedException {
        rolesPage.clickOnRoleEditIcon(roleName2);
        manageRolePage.waitForManageRoleToAppear();
        manageRolePage.assignMemberToRole(memberName1);
        Assert.assertEquals(manageRolePage.getSuccessMessage(), Notification.ROLE_DETAILS_UPDATE.getDesc());
        manageRolePage.search(memberName1);
        Assert.assertEquals(manageRolePage.getTheMembersDetails(), Arrays.asList(memberName1 +" "+ roleName1+", "+roleName2+ " Active " + emailId1));
        Assert.assertTrue(manageRolePage.getTheMembersDetails().get(0).contains(roleName1+", "+roleName2));
        manageRolePage.navigateToRolesListing();
        rolesPage.waitForRoleListingPageToLoad();
        Assert.assertEquals(rolesPage.getRoles(), Arrays.asList(roleName1+ " (1 Member )", roleName2 + " (1 Member )"));

    }

    @Test(priority = 8)
    public void verifyUpdatingRole() throws InterruptedException {
        rolesPage.clickOnRoleEditIcon(roleName2);
        manageRolePage.waitForManageRoleToAppear();
        FunctionLibrary.refreshPage();
        manageRolePage.clickOnInfoTab();
        manageRolePage.updateRoleName(updatedRoleName2);
        manageRolePage.clickOnUpdate();
        Assert.assertEquals(manageRolePage.getSuccessMessage(), Notification.ROLE_DETAILS_UPDATE.getDesc());
        Assert.assertEquals(manageRolePage.getRoleName(), updatedRoleName2);

    }

    @Test(priority = 9)
    public void verifyTheRoleDetailsInMembersPage() throws InterruptedException {
        myOrganizationPage.clickOnMemberTab();
        membersPage.searchMember(memberName1);
        Assert.assertTrue(membersPage.fetchMembersDetails().get(0).contains(roleName1+", "+updatedRoleName2));
        Assert.assertTrue(membersPage.fetchMembersDetails().get(0).contains(memberName1));
    }

    @Test(priority = 10)
    public void verifyRoleDeletion() throws InterruptedException {
        rolesPage.clickOnRoleEditIcon(roleName2);
        manageRolePage.waitForManageRoleToAppear();
        manageRolePage.deleteRole();
        rolesPage.waitForRoleListingPageToLoad();
        rolesPage.searchRole(updatedRoleName2);
        Assert.assertEquals(manageRolePage.noResultFoundPage(), "No results found");

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        FunctionLibrary.closeDriver();
    }
}
