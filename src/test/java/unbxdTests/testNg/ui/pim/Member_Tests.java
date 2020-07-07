package unbxdTests.testNg.ui.pim;

import core.pages.pim.*;
import core.pages.pim.MyOrganisation.Members.ManageMember_Page;
import core.pages.pim.MyOrganisation.Members.MembersListing_Page;
import core.pages.pim.MyOrganisation.MyOrganisation_Page;
import core.pages.pim.MyOrganisation.Roles.ManageRole_Page;
import core.pages.pim.MyOrganisation.Roles.RolesListing_Page;
import lib.FunctionLibrary;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Member_Tests extends BaseTest {

    String JSON_path = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String typeData = "Login Data";
    int totalDataRow = 2;
    int totalColumnEntry = 7;

    String memberName, memberName2;
    String emailId, emailId2;
    String password, password2;
    String role, roleName2;
    String updatedName, updatedName2;

    {
        try {
            memberName = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][5].toString();
            emailId = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][0].toString();
            password = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][1].toString();
            role = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][3].toString();
            updatedName = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][6].toString();

            memberName2 = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[1][5].toString();
            emailId2 = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[1][0].toString();
            password2 = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[1][1].toString();
            roleName2 = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[1][3].toString();
            updatedName2 = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[1][6].toString();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Login_Page loginPage;

    @Page
    MembersListing_Page membersListingPage;

    @Page
    Home_Page homePage;

    @Page
    ManageMember_Page manageMemberPage;

    @Page
    RolesListing_Page rolesPage;

    @Page
    ManageRole_Page manageRolePage;

    @Page
    MyOrganisation_Page myOrganizationPage;

    public List<String> removeLastCharacter(List<String> strList) {
        List<String> modifiedList = new ArrayList<String>();
        for(String str : strList)
        if (str != null && str.length() > 0 && str.charAt(str.length() - 1) == 'x') {
            modifiedList.add(str.substring(0, str.length() - 1));
        }
        return modifiedList;
    }

    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnUserMenuIcon();
        homePage.clickOnViewOrganization();
        myOrganizationPage.clickOnRolesTab();
        rolesPage.waitForRoleListingPageToLoad();
        rolesPage.createRole(roleName2);
        manageRolePage.waitForManageRoleToAppear();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("roleId"));

    }

    @Test(priority = 1)
    public void verifyOrganisationPage() throws InterruptedException {
        homePage.clickOnUserMenuIcon();
        homePage.clickOnViewOrganization();
        membersListingPage.waitForMemberListingPageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("#/organisation/"));
    }

    @Test(priority = 2)
    public void verifyMemberCreationForEmptyEmail() {
        membersListingPage.createMember("");
        Assert.assertEquals(membersListingPage.getEmailErrorValidationMessage(), "Email ID is required");
        membersListingPage.closeCreateMemberModal();
    }

    @Test(priority = 3)
    public void verifyMemberCreationForInvalidEmail() {
        membersListingPage.createMember("invalidEmail");
        Assert.assertEquals(membersListingPage.getEmailErrorValidationMessage(), "Email ID is invalid");
        membersListingPage.closeCreateMemberModal();
    }

    @Test(priority = 4)
    public void verifyMemberCreation() throws Exception {
        membersListingPage.createMember(emailId2, roleName2);
        manageMemberPage.waitForManageMemberPageToLoad();
        Assert.assertEquals(manageMemberPage.getMemberEmail(), emailId2);
    }

    @Test(priority = 5)
    public void verifyEmailIsDisabled() {
        Assert.assertEquals(manageMemberPage.isEmailEnabled(), false);
    }

    @Test(priority = 6)
    public void verifyMemberName() {
        // Currently, the user only gets deleted for that org. Thus, the member's data remain intact
      //  Assert.assertEquals(manageMemberPage.getMemberName(), emailId2.split("@")[0]);
    }

    @Test(priority = 7)
    public void verifyRoleAssigned() {
        Assert.assertEquals(removeLastCharacter(manageMemberPage.getRolesAssigned()), Arrays.asList(roleName2));
    }

    // As the member is not getting deleted from the system(It just get deleted from the org). So, if I test update
    // member data, it will preserve and hence hamper other test cases.
    @Test(priority = 8, enabled = false)
    public void verifyMemberNameUpdate() {
        manageMemberPage.updateName(updatedName2);
        manageMemberPage.clickOnUpdate();
        Assert.assertEquals(manageMemberPage.getMemberName(), updatedName2);

    }

    @Test(priority = 9)
    public void verifyMemberNameInMemberListingPage() {
        manageMemberPage.clickOnBackToMemberListingLink();
        System.out.println(membersListingPage.fetchMembersDetails());
        Assert.assertEquals(membersListingPage.fetchMembersDetails(), Arrays.asList(
                memberName+" "+role+" Active "+ emailId,memberName2+" "+roleName2+" Active "+emailId2));

    }

    @Test(priority = 10)
    public void verifyDuplicateMemberCreation() throws Exception {
        membersListingPage.createMember(emailId2, roleName2);
        Assert.assertEquals(membersListingPage.getErrorMsgOnReCreatingAnExistingMember(), "Member already exists for this organization");
        membersListingPage.closeCreateMemberModal();
    }

    @Test(priority = 11)
    public void verifyMemberEditPage() throws InterruptedException {
        membersListingPage.searchMember(memberName2);
        membersListingPage.clickOnMemberEditIcon();
        manageMemberPage.waitForManageMemberPageToLoad();
        System.out.println(FunctionLibrary.getPageURL());
        Assert.assertTrue(FunctionLibrary.getPageURL().contains(";action=edit;tab=members;"));
    }

    @Test(priority = 13)
    public void verifyMemberDeletion() {
       // verifyMemberEditPage();
        manageMemberPage.deleteMember();
        Assert.assertEquals(membersListingPage.fetchMembersDetails(), Arrays.asList(
                memberName+" "+role+" Active "+ emailId));
        System.out.println(FunctionLibrary.getPageURL());
        Assert.assertTrue(FunctionLibrary.getPageURL().contains(";action=list;tab=members"));
        // Fix for 262
       // FunctionLibrary.refreshPage();

    }
    @Test(priority = 14)
    public void verifyMemberCreationWithoutRole() {
        FunctionLibrary.scrollToTop();
        membersListingPage.createMember(emailId2);
        manageMemberPage.waitForManageMemberPageToLoad();
        Assert.assertEquals(manageMemberPage.getMemberEmail(), emailId2);
    }



    @Test(priority = 15)
    public void verifyTheLabelWhenNoRoleIsAssigned() {
        Assert.assertEquals(manageMemberPage.getDefaultLabelWhenNoRoleIsAssigned(), "Select");
    }

    @Test(priority = 16)
    public void verifyTheRoleAssignmentToMember() throws InterruptedException {
        manageMemberPage.assignRoleToMember(roleName2);
        manageMemberPage.clickOnUpdate();
        Assert.assertEquals(removeLastCharacter(manageMemberPage.getRolesAssigned()), Arrays.asList(roleName2));

    }

    @Test(priority = 17)
    public void verifyAgainMemberNameInMemberListingPage() throws InterruptedException {
        FunctionLibrary.scrollToTop();
        manageMemberPage.clickOnBackToMemberListingLink();
        FunctionLibrary.scrollToTop();
        FunctionLibrary.refreshPage();
        System.out.println(membersListingPage.fetchMembersDetails());
        Assert.assertEquals(membersListingPage.fetchMembersDetails(), Arrays.asList(
                memberName+" "+role+" Active "+ emailId, memberName2+" "+roleName2+" Active "+emailId2));

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        FunctionLibrary.closeDriver();
    }


}
