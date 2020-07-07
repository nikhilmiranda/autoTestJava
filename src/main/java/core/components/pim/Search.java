package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class Search extends FluentPage {

    @FindBy(how = How.NAME, using = "searchVal")
    FluentWebElement searchInputBox;

    @FindBy(how = How.CSS, using = ".search-container input")
    FluentWebElement searchContainerInputBox;

    @FindBy(how = How.CSS, using = ".modal-body .search-container input")
    FluentWebElement searchModalInputBox;

    @FindBy(how = How.CSS, using = ".drop-down-search-container input")
    FluentWebElement searchDropDownInputBox;

    String searchInputBoxName = "Search Input Box";

    public void search(String searchVal) throws InterruptedException {
        FunctionLibrary.input(searchInputBox, searchInputBoxName, searchVal);
        Thread.sleep(1000);
    }

    public void searchInContainer(String searchVal) throws InterruptedException {
        FunctionLibrary.input(searchContainerInputBox, searchInputBoxName, searchVal);
        Thread.sleep(1000);
    }

    public void searchInModal(String searchVal) throws InterruptedException {
        FunctionLibrary.input(searchModalInputBox, searchInputBoxName, searchVal);
        Thread.sleep(1000);
    }

    public void searchInDropDown(String searchVal) throws InterruptedException {
        FunctionLibrary.input(searchDropDownInputBox, searchInputBoxName, searchVal);
        Thread.sleep(1000);
    }
}
