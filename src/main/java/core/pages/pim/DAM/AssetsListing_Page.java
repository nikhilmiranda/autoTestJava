package core.pages.pim.DAM;

import core.components.pim.ActionsDDModal;
import core.components.pim.Pagination;
import core.components.pim.Search;
import core.components.pim.SelectProducts;
import core.components.pim.SelectProperties;
import core.components.pim.PrimaryButton;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import java.util.List;

public class AssetsListing_Page extends FluentPage {

    @Page
    PrimaryButton primaryButton;

    @Page
    Pagination pagination;

    @Page
    Search search;

    @Page
    ActionsDDModal actionsDDModal;

    @Page
    SelectProducts selectProducts;

    @Page
    SelectProperties selectProperties;


    @FindBy(how = How.CSS, using = ".asset-cards-cont .list-item .asset-details p:nth-child(1)")
    FluentList<FluentWebElement> fileNames;

    @FindBy(how = How.CSS, using = ".asset-cards-cont .list-item .asset-details .link-text")
    FluentWebElement editLinkAgainstImage;

    @FindBy(how = How.CSS, using = ".qa-asset-list")
    FluentWebElement assetUniqueLocator;

    @FindBy(how = How.CSS, using = ".select-checkbox input")
    FluentWebElement selectCheckBoxAgainstImage;

    String fileNamesName = "File Names";
    String editLinkAgainstImageName = "Edit Link Against Image";
    String assetUniqueLocatorName = "Asset Unique Locator";
    String selectCheckBoxAgainstImageName = "Select CheckBox Against Image";

    public void openImage(String imageName) throws InterruptedException {
        search.search(imageName);
        FunctionLibrary.click(editLinkAgainstImage, editLinkAgainstImageName);
    }

    public List<String> getAllTheImages() throws InterruptedException {
        return FunctionLibrary.retrieveTexts(fileNames, fileNamesName);
    }

    public void getAllTheImagesInNextPagination10() throws InterruptedException {
        pagination.clickOnPageNextButton();
        Assert.assertEquals(FunctionLibrary.retrieveTexts(fileNames, fileNamesName).size(), 9);
        Assert.assertEquals(pagination.isPageNextButtonEnabled(), "false");
        Assert.assertEquals(pagination.isPagePrevButtonEnabled(), "true");
    }

    public void getAllTheImagesInPrevPagination10() throws InterruptedException {
        pagination.clickOnPagePrevButton();
        Assert.assertEquals(FunctionLibrary.retrieveTexts(fileNames, fileNamesName).size(), 10);
        Assert.assertEquals(pagination.isPageNextButtonEnabled(), "true");
        Assert.assertEquals(pagination.isPagePrevButtonEnabled(), "false");
    }

    public List<String> getAllTheImagesForPagination20() throws InterruptedException {
        pagination.choosePaginationOf20Elements();
        return FunctionLibrary.retrieveTexts(fileNames, fileNamesName);
    }

    public void associateProducts(String imageName) throws InterruptedException {
        search.search(imageName);
        Thread.sleep(1000);
        FunctionLibrary.click(selectCheckBoxAgainstImage, selectCheckBoxAgainstImageName);
        actionsDDModal.clickOnActionsDropDownIcon();
        actionsDDModal.clickOnListDropDownValue("Associate Products");
        search.searchInModal("supera big lace sneakers");
        Thread.sleep(1000);
        selectProducts.clickOnCheckboxAgainstProductInModal();
        primaryButton.clickOnPrimaryModalButton();
        selectProperties.clickOnCheckboxAgainstPropertyInModal();
        primaryButton.clickOnPrimaryModalButton();
    }

    public void waitForAssetListingPageToLoad() throws InterruptedException {
        FunctionLibrary.waitForElementToLoad(assetUniqueLocator, assetUniqueLocatorName);
    }
}