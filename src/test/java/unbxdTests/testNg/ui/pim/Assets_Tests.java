package unbxdTests.testNg.ui.pim;

import core.components.pim.*;
import core.pages.pim.DAM.AssetsListing_Page;
import core.pages.pim.DAM.ManageAsset_Page;
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
import java.util.List;

public class Assets_Tests extends BaseTest {

    String jsonPathCatalogData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/catalog.json";
    String catalogTypeData = "Catalog Data";
    int totalCatalogDataRow = 1;
    int totalCatalogColumnEntry = 5;

    String jsonPathLoginData = System.getProperty("user.dir") + "/src/test/resources/testData/pim/login.json";
    String loginTypeData = "Login Data";
    int totalLoginDataRow = 2;
    int totalLoginColumnEntry = 7;

    public String emailId, password, catalogName, property1, property2, facet1, facet2;
    String[] actualImagesArray = {"1529575667845_JockeyBlue.jpeg", "1529575692626_JockeyWhite.jpeg", "1529575641675_JockeyBlack.jpeg", "1529575591945_JockeyRed.jpeg", "1529518918964_fileName", "1529518915822_fileName", "1529516935990_fileName", "1529516933399_fileName", "1529516934757_fileName", "1529518921833_fileName", "1529518920856_fileName", "1529518918044_fileName", "1529518914660_fileName", "1529518911085_fileName", "1529518912112_fileName", "1529518913561_fileName", "1529518919924_fileName", "1529518910153_fileName", "1529518916831_fileName"};
    List<String> actualImagesList = Arrays.asList(actualImagesArray);

    String assetFilePath = System.getProperty("user.dir") + "/src/test/resources/testData/pim/asset.jpeg";

    {
        try {
            emailId = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][0].toString();
            password = JsonReader.getdata(jsonPathLoginData, loginTypeData, totalLoginDataRow, totalLoginColumnEntry)[0][1].toString();
            catalogName = JsonReader.getdata(jsonPathCatalogData, catalogTypeData, totalCatalogDataRow, totalCatalogColumnEntry)[0][0].toString();
            property1 = JsonReader.getdata(jsonPathCatalogData, catalogTypeData, totalCatalogDataRow, totalCatalogColumnEntry)[0][1].toString();
            property2 = JsonReader.getdata(jsonPathCatalogData, catalogTypeData, totalCatalogDataRow, totalCatalogColumnEntry)[0][2].toString();
            facet1 = JsonReader.getdata(jsonPathCatalogData, catalogTypeData, totalCatalogDataRow, totalCatalogColumnEntry)[0][3].toString();
            facet2 = JsonReader.getdata(jsonPathCatalogData, catalogTypeData, totalCatalogDataRow, totalCatalogColumnEntry)[0][4].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Page
    Pagination pagination;

    @Page
    SecondaryButton secondaryButton;

    @Page
    PrimaryButton primaryButton;

    @Page
    DragAndDropModal dragAndDropModal;

    @Page
    Search search;

    @Page
    SelectProducts selectProducts;

    @Page
    Login_Page loginPage;

    @Page
    Home_Page homePage;

    @Page
    AssetsListing_Page assetsListingPage;

    @Page
    ManageAsset_Page manageAssetPage;

    @Page
    SelectProperties selectProperties;

    @Page
    NotificationMsg notificationMsg;


    @BeforeClass
    public void init() throws InterruptedException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(loginPage);
        loginPage.login(emailId, password);
        homePage.clickOnAssetsTab();
        assetsListingPage.waitForAssetListingPageToLoad();

    }

    @AfterMethod
    public void returnToCustomAdapterPage() {

//            homePage.clickOnManageTab();
        //  homePage.clickOnCatalogs();
        // catalogListingPage.waitForElementToLoad();

    }

    @Test(priority = 1)
    public void getAllTheAssetsListedForPagination10() throws InterruptedException {
        Assert.assertTrue(assetsListingPage.getAllTheImages().size() == 10);
        Assert.assertTrue(actualImagesList.containsAll(assetsListingPage.getAllTheImages()));

    }

    @Test(priority = 2)
    public void getAllTheAssetsListedForNextPagination() throws InterruptedException {
        pagination.clickOnPageNextButton();
        Assert.assertEquals(assetsListingPage.getAllTheImages().size(), 9);
        Assert.assertTrue(actualImagesList.containsAll(assetsListingPage.getAllTheImages()));
//        Assert.assertEquals(pagination.isPageNextButtonEnabled().booleanValue(), false);
//        Assert.assertEquals(pagination.isPagePrevButtonEnabled().booleanValue(), true);

    }

    @Test(priority = 3)
    public void getAllTheAssetsListedForPrevPagination() throws InterruptedException {
        pagination.clickOnPagePrevButton();
        Assert.assertEquals(assetsListingPage.getAllTheImages().size() , 10);
//        Assert.assertEquals(pagination.isPageNextButtonEnabled().booleanValue(), true);
//        Assert.assertEquals(pagination.isPagePrevButtonEnabled().booleanValue(), false);

    }

    @Test(priority = 4)
    public void getAllTheAssetsListedForPagination20() throws InterruptedException {
        pagination.choosePaginationOf20Elements();
        Assert.assertEquals(assetsListingPage.getAllTheImages().size() , 19);
//        Assert.assertEquals(pagination.isPageNextButtonEnabled().booleanValue(), false);
//        Assert.assertEquals(pagination.isPagePrevButtonEnabled().booleanValue(), true);

    }

    @Test(priority = 5)
    public void verifyAssetCreation() throws InterruptedException {
        secondaryButton.clickOnSecondaryButton();
        dragAndDropModal.uploadFile(assetFilePath);
        search.search("asset.jpeg");
        Assert.assertTrue(assetsListingPage.getAllTheImages().size() == 1);


    }

    @Test(priority = 6)
    public void verifyAssetAssociation() throws InterruptedException {
        // search.search("asset.jpeg");
        assetsListingPage.associateProducts("asset.jpeg");
        assetsListingPage.openImage("asset.jpeg");
        Assert.assertEquals(selectProducts.getAllTheProducts().size(), 1);


    }

    @Test(priority = 7)
    public void verifyAssetAssociationFromManageAssetPage() throws InterruptedException {
        // search.search("asset.jpeg");
        secondaryButton.clickOnSecondaryButton();
        search.searchInModal("Stelve Mad cactus sandals");
        Thread.sleep(1000);
        selectProducts.clickOnCheckboxAgainstProductInModal();
        primaryButton.clickOnPrimaryModalButton();
        selectProperties.clickOnCheckboxAgainstPropertyInModal();
        primaryButton.clickOnPrimaryModalButton();
        Assert.assertEquals(selectProducts.getAllTheProducts().size(), 2);

    }

    @Test(priority = 8)
    public void verifyImageNameUpdate() throws InterruptedException {
        Assert.assertEquals(manageAssetPage.getTheImageName(), "asset.jpeg");
        manageAssetPage.updateName("assetUpdated.jpeg");
        Assert.assertEquals(notificationMsg.getTheNotificationMessage(), "Image name updated successfully");
        Assert.assertEquals(manageAssetPage.getTheImageName(), "assetUpdated.jpeg");


    }


}
