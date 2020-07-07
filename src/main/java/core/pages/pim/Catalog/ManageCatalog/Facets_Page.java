package core.pages.pim.Catalog.ManageCatalog;

import lib.BrowserInitializer;
import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Facets_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".source-container .DnD-card")
    FluentWebElement facetsSource;


    By facetsSource1 = By.cssSelector(".source-container .DnD-card");



    @FindBy(how = How.CSS, using = ".dest-container")
    FluentWebElement facetsDestination;

    @FindBy(how = How.CSS, using = ".destination-container.DnD-container")
    WebElement facetsDestination12;

    By facetsDestination1 = By.cssSelector(".destination-container.DnD-container");

    @FindBy(how = How.CSS, using = ".dest-container .DnD-card")
    FluentList<FluentWebElement> facetsAddedToDestination;

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchFacetsInputBox;

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement updateButton;

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement infoMsgLabel;


    String facetsSourceName = "Facets Source";
    String facetsDestinationName = "Facets Destination";
    String searchFacetsInputBoxName = "Search Facets InputBox";
    String facetsAddedToDestinationName = "Facets Added To Destination";
    String updateButtonName = "Update Button";
    String infoMsgLabelName = "Info Msg Label";


    public void addFacetsToCatalog(String propertyName) throws InterruptedException {
        FunctionLibrary.input(searchFacetsInputBox, searchFacetsInputBoxName, propertyName);
        Thread.sleep(1000);
       // FunctionLibrary.dragAndDrop(facetsSource1, facetsDestination1, facetsSourceName, facetsDestinationName);

    }

    public List<String> getAllTheFacetsAdded() {
        return FunctionLibrary.retrieveTexts(facetsAddedToDestination, facetsAddedToDestinationName);
    }

    public void clickOnUpdateButton() {
        FunctionLibrary.click(updateButton, updateButtonName);

    }

    public void waitForPageToLoad() {
        FunctionLibrary.click(facetsSource, facetsSourceName);

    }

    public String fetchInfoMsg() {

        return FunctionLibrary.retrieveText(infoMsgLabel, infoMsgLabelName);
    }

}
