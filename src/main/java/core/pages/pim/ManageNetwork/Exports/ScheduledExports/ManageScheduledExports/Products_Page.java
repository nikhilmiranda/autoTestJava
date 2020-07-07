package core.pages.pim.ManageNetwork.Exports.ScheduledExports.ManageScheduledExports;

import core.components.pim.PrimaryButton;
import core.components.pim.ProductsTable;
import core.components.pim.Search;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.annotation.Page;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import java.util.List;

public class Products_Page extends FluentPage {

    @Page
    Search search;

    @Page
    PrimaryButton primaryButton;

    @Page
    ProductsTable productsTable;


    @FindBy(how = How.CSS, using = ".secondary-btn .start-icon")
    FluentWebElement startScheduleButton;

    @FindBy(how = How.CSS, using = ".qa-nwscheduler-products .secondary-btn")
    FluentWebElement selectIndividualProductsButton;

    @FindBy(how = How.CSS, using = ".scheduler-prod-item")
    FluentList<FluentWebElement> productsSection;

    @FindBy(how = How.CSS, using = ".group-expand-link")
    FluentList<FluentWebElement> viewLinkAgainstSections;

    @FindBy(how = How.CSS, using = ".sprite.remove-icon")
    FluentWebElement removeIcon;


    String startScheduleButtonName = "Start Schedule Button";
    String selectIndividualProductsButtonName = "Select Individual Products Button";
    String productsSectionName = "Products Section";
    String viewLinkAgainstProductSectionName = "View Link Against Product Section";
    String removeIconName = "Remove Icon";

    public void selectLabel(String labelName) throws InterruptedException {
        search.searchInModal(labelName);
        productsTable.selectRowItemFromModal();
        primaryButton.clickOnPrimaryModalButton();

    }

    public void selectProduct(String productName) throws InterruptedException {
        search.searchInModal(productName);
        productsTable.selectRowItemFromModal();
        primaryButton.clickOnPrimaryModalButton();

    }

    public List<String> fetchAllTheProducts() throws InterruptedException {
        return productsTable.fetchAllTheRowItemsDetail();

    }

    public void clickOnSelectIndividualProducts() throws InterruptedException {
        FunctionLibrary.click(selectIndividualProductsButton, selectIndividualProductsButtonName);

    }

    public List<String> clickOnStartSchedule() throws InterruptedException {
       return FunctionLibrary.retrieveTexts(productsSection, productsSectionName);

    }

    public void clickOnViewAgainstLabelSection() throws InterruptedException {
        FunctionLibrary.click(viewLinkAgainstSections.get(0), viewLinkAgainstProductSectionName);
    }

    public void clickOnViewAgainstProductSection() throws InterruptedException {
        FunctionLibrary.click(viewLinkAgainstSections.get(1), viewLinkAgainstProductSectionName);
    }

    public void clickOnRemoveIcon() throws InterruptedException {
        FunctionLibrary.click(removeIcon, removeIconName);
    }










}
