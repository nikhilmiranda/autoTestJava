package unbxdTests.testNg.ui.pim;

import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;
import core.pages.pim.UserProfile_Page;
import lib.FunctionLibrary;
import lib.Helper;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;

public class UserProfile_Tests extends BaseTest {

    @Page
    Home_Page homePage;

    @Page
    Login_Page loginPage;

    @Page
    UserProfile_Page userProfilePage;

    String JSON_path = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String typeData = "Login Data";
    int totalDataRow = 2;
    int totalColumnEntry = 7;

    String profileImage = System.getProperty("user.dir") + "/src/test/resources/testData/pim/lucy.jpeg";


    String emailId;
    String password;
    String memberName, updatedName;

    {
        try {
            emailId = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][0].toString();
            password = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][1].toString();
            memberName = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][5].toString();
            updatedName = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][6].toString();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);

    }

    @Test(priority = 1)
    public void verifyOrganisationProfilePage() throws InterruptedException {
        homePage.clickOnUserMenuIcon();
        homePage.clickOnEditProfile();
        userProfilePage.waitForUserProfilePageToLoad();
        Assert.assertTrue(FunctionLibrary.getPageURL().contains("profile"));
    }

    @Test(priority = 2)
    public void verifyUserName() {
        Assert.assertEquals(userProfilePage.getMemberName(), memberName);
    }

    @Test(priority = 3)
    public void verifyEmailID() {
        Assert.assertEquals(userProfilePage.getMemberEmailId(), emailId);
    }

    @Test(priority = 4)
    public void verifyEmailFieldIsDisabled() {

        Assert.assertEquals(userProfilePage.isEmailEnabled(), false);
    }

    @Test(priority = 5)
    public void verifyImageUpload() throws InterruptedException {
        userProfilePage.uploadProfileImg(profileImage);
        Assert.assertTrue(userProfilePage.getProfileImageUrl().contains("lucy.jpeg"));
    }

    @Test(priority = 6)
    public void verifyNameUpdate() throws InterruptedException {
        userProfilePage.updateName(updatedName);
        FunctionLibrary.refreshPage();
        Assert.assertEquals(userProfilePage.getMemberName(), updatedName);
    }

    @Test(priority = 7)
    public void verifyNameUpdateInMenuPage() {
        homePage.clickOnUserMenuIcon();
        Assert.assertEquals(homePage.getUserName(), updatedName);
    }

    @Test(priority = 8)
    public void verifyProfileImageInMenuPage() throws InterruptedException {
        Assert.assertTrue(homePage.getProfileImageURL().contains("lucy.jpeg"));
        homePage.clickOnUserMenuIcon();
    }


    @AfterClass(alwaysRun = true)
    public void tearDown() throws InterruptedException {
        userProfilePage.updateName(memberName);
        FunctionLibrary.closeDriver();
    }
}
