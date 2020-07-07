package unbxdTests.testNg.ui.pim;

import core.pages.pim.AdvanceFilter_Page;

import core.pages.pim.Home_Page;
import core.pages.pim.Login_Page;

import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.TimeUnit;

public class AdvanceFilter_Tests extends BaseTest {


    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json" ;
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    String[] propertyImportedArray = {"Product Id", "Image", "Product Name", "Price", "Product Details", "Color", "Gender", "Size", "Pattern", "Sleeve", "Fit", "Fabric", "Fabric Care", "Suitable For", "Neck Type", "Payment Mode", "Return Policy","Parent Id","More Details","Date Of Manufaturing"};

    public String emailId, password, catalogName, property1, property2, facet1, facet2;

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    core.pages.pim.Catalog.ManageCatalog.Products_Page productsPage;

    @Page
    AdvanceFilter_Page advanceFilterPage;



    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnProductsTab();
        productsPage.waitForPageToLoad();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

    }

    @AfterMethod
    public void returnToCustomAdapterPage() {

    }

    @Test(priority = 1)
    public void verifyIfAdvanceFilterIsOpening() {
        productsPage.clickOnShowAdvanceFilterLink();
        Assert.assertTrue(advanceFilterPage.isAdvanceFilterOpened().equals(true));
    }

    @Test(priority = 2)
    public void verifyTheListOptionsInLabelDropdown() {
        advanceFilterPage.clickOnLabelDropdown();
        Assert.assertEquals(advanceFilterPage.getAllTheDropdownListValue(), Arrays.asList("Property", "System Property", "Task", "Product Group", "Category"));
        advanceFilterPage.clickOnLabelDropdown();
    }

    @Test(priority = 3)
    public void verifyTheValuesForProperty() {
        advanceFilterPage.clickOnLabelDropdown();
        advanceFilterPage.selectDropdownValue("Property");
        advanceFilterPage.clickOnValueDropdown();
        Collections.sort(Arrays.asList(propertyImportedArray), new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
        Assert.assertEquals(advanceFilterPage.getAllTheDropdownListValue(), Arrays.asList(propertyImportedArray));
        advanceFilterPage.clickOnValueDropdown();
    }


    @Test(priority = 4)
    public void verifyTheValuesForSystemProperty() {
        advanceFilterPage.clickOnLabelDropdown();
        advanceFilterPage.selectDropdownValue("System Property");
        advanceFilterPage.clickOnValueDropdown();
        Assert.assertEquals(advanceFilterPage.getAllTheDropdownListValue(), Arrays.asList("Date Created", "Date Updated"));
        advanceFilterPage.clickOnValueDropdown();
    }

    @Test(priority = 5)
    public void verifyTheValuesForTask() {
        advanceFilterPage.clickOnLabelDropdown();
        advanceFilterPage.selectDropdownValue("Task");
        advanceFilterPage.clickOnValueDropdown();
        Assert.assertEquals(advanceFilterPage.getAllTheDropdownListValue(), Arrays.asList("Task Name", "Task Status", "Task Assignee"));
        advanceFilterPage.clickOnValueDropdown();
    }

    @Test(priority = 6)
    public void verifyTheValuesForProductGroup() {
        advanceFilterPage.clickOnLabelDropdown();
        advanceFilterPage.selectDropdownValue("Product Group");
        advanceFilterPage.clickOnValueDropdown();
        Assert.assertEquals(advanceFilterPage.getAllTheDropdownListValue(), Arrays.asList("Product"));
        advanceFilterPage.clickOnValueDropdown();
    }

    @Test(priority = 7)
    public void verifyTheOperatorsForProductGroup() {
        advanceFilterPage.clickOnLabelDropdown();
        advanceFilterPage.selectDropdownValue("Product Group");
        advanceFilterPage.clickOnValueDropdown();
        advanceFilterPage.selectDropdownValue("Product");
        advanceFilterPage.clickOnOperatorDropdown();
        Assert.assertEquals(advanceFilterPage.getAllTheDropdownListValue(), Arrays.asList("is in list", "not in list"));
        advanceFilterPage.clickOnOperatorDropdown();
    }

    @Test(priority = 8)
    public void verifyTheOperatorsForSystemProperty() {
        advanceFilterPage.clickOnLabelDropdown();
        advanceFilterPage.selectDropdownValue("System Property");
        advanceFilterPage.clickOnValueDropdown();
        advanceFilterPage.selectDropdownValue("Date Created");
        advanceFilterPage.clickOnOperatorDropdown();
        Assert.assertEquals(advanceFilterPage.getAllTheDropdownListValue(), Arrays.asList("on", "not on", "between date range", "less than", "less than and equals to", "greater than", "greater than and equals to", "in the time frame of"));
        advanceFilterPage.clickOnOperatorDropdown();
    }

    @Test(priority = 9)
    public void verifyTheOperatorsForTask() {
        advanceFilterPage.clickOnLabelDropdown();
        advanceFilterPage.selectDropdownValue("Task");
        advanceFilterPage.clickOnValueDropdown();
        advanceFilterPage.selectDropdownValue("Task Name");
        advanceFilterPage.clickOnOperatorDropdown();
        Assert.assertEquals(advanceFilterPage.getAllTheDropdownListValue(), Arrays.asList("in", "not in"));
        advanceFilterPage.clickOnOperatorDropdown();
    }

    @Test(priority = 10)
    public void verifyTheOperatorsForProperty() {
        advanceFilterPage.clickOnLabelDropdown();
        advanceFilterPage.selectDropdownValue("Property");
        advanceFilterPage.clickOnValueDropdown();
        advanceFilterPage.selectDropdownValue("Color");
        advanceFilterPage.clickOnOperatorDropdown();
        Assert.assertEquals(advanceFilterPage.getAllTheDropdownListValue(), Arrays.asList("equals", "doesn't equal", "in", "not in", "has any value", "has no value", "contains word", "doesn't contain word"));
        advanceFilterPage.clickOnOperatorDropdown();
    }

    @Test(priority = 10)
    public void verifyTheProductsDisplayed() throws InterruptedException {
        advanceFilterPage.clickOnLabelDropdown();
        advanceFilterPage.selectDropdownValue("Property");
        advanceFilterPage.clickOnValueDropdown();
        advanceFilterPage.selectDropdownValue("Color");
        advanceFilterPage.clickOnOperatorDropdown();
        advanceFilterPage.selectDropdownValue("doesn't equal");
        advanceFilterPage.clickOnOptionDropdown();
        advanceFilterPage.searchOptionValueInDropdown("Red");
        advanceFilterPage.clickOnDropdownOption();
        Assert.assertEquals(productsPage.getAllTheProductNames(), Arrays.asList("snooky Men Vest", "snooky Men Vest", "snooky Men Vest"));
        advanceFilterPage.clickOnOptionDropdown();
    }
}
