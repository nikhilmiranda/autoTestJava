package unbxdTests.testNg.ui.pim;

import core.pages.pim.Home_Page;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import core.pages.pim.Login_Page;
import lib.Helper;
import org.testng.asserts.SoftAssert;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;

public class LoginTest extends BaseTest {

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
    Home_Page homePage;


    @BeforeClass
    public void init() {
        super.setUp();
        initFluent(driver);
        initTest();
    }

    @Test(priority = 1)
    public void LoginValidation(ITestContext context) throws InterruptedException {
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnUserMenuIcon();
        Assert.assertEquals(homePage.getOrganisationName(), "autoTEST");
    }

    @Test(priority = 2)
    public void LogOutValidation() {
        homePage.clickOnLogout();
        Assert.assertTrue(loginPage.isThePageAtLoginScreen());

    }

}
