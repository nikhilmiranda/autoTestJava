package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class Pagination extends FluentPage {

    @FindBy(how = How.CSS, using = ".element-pageSize .caret")
    FluentWebElement paginationDropdownIcon;

    @FindBy(how = How.CSS, using = ".page-count-dd li")
    FluentList<FluentWebElement> paginationDropdownModalListValues;

    @FindBy(how = How.CSS, using = ".page-count-dd li:nth-child(1)")
    FluentWebElement paginationListValue10;

    @FindBy(how = How.CSS, using = ".page-count-dd li:nth-child(2)")
    FluentWebElement paginationListValue20;

    @FindBy(how = How.CSS, using = ".page-count-dd li:nth-child(3)")
    FluentWebElement paginationListValue50;

    @FindBy(how = How.CSS, using = ".page-count-dd li:nth-child(4)")
    FluentWebElement paginationListValue100;

    @FindBy(how = How.CSS, using = ".page-prev-btn")
    FluentWebElement pagePrevButton;

    @FindBy(how = How.CSS, using = ".page-next-btn")
    FluentWebElement pageNxtButton;

    @FindBy(how = How.CSS, using = ".actions-dd-body .list-item.disabled")
    FluentWebElement actionsDropdownModalListDisabledValues;

    String paginationDropdownIconName = "Pagination Dropdown Icon";
    String paginationDropdownModalListValuesName = "Pagination Dropdown Modal List Values";
    String paginationListValue10Name = "Pagination List Value 10";
    String paginationListValue20Name = "Pagination List Value 20";
    String paginationListValue50Name = "Pagination List Value 30";
    String paginationListValue100Name = "Pagination List Value 40";
    String pagePrevButtonName = "Page Previous Button";
    String pageNxtButtonName = "Page Next Button";


    public void clickOnActionsDropdownIcon() {
        FunctionLibrary.click(paginationDropdownIcon, paginationDropdownIconName);
    }

    public List<String> getAllTheListValues() {
        return  FunctionLibrary.retrieveTexts(paginationDropdownModalListValues, paginationDropdownModalListValuesName);
    }

    public void choosePaginationOf10Elements() {
        FunctionLibrary.click(paginationDropdownIcon, paginationDropdownIconName);
        FunctionLibrary.click(paginationListValue10, paginationListValue10Name);
    }

    public void choosePaginationOf20Elements() {
        FunctionLibrary.click(paginationDropdownIcon, paginationDropdownIconName);
        FunctionLibrary.click(paginationListValue20, paginationListValue20Name);
    }

    public void choosePaginationOf50Elements() {
        FunctionLibrary.click(paginationDropdownIcon, paginationDropdownIconName);
        FunctionLibrary.click(paginationListValue50, paginationListValue50Name);
    }

    public void choosePaginationOf100Elements() {
        FunctionLibrary.click(paginationDropdownIcon, paginationDropdownIconName);
        FunctionLibrary.click(paginationListValue100, paginationListValue100Name);
    }

    public void clickOnPageNextButton() {
        FunctionLibrary.click(pageNxtButton, pageNxtButtonName);
    }

    public void clickOnPagePrevButton() {
        FunctionLibrary.click(pagePrevButton, pagePrevButtonName);
    }

    public Boolean isPageNextButtonEnabled() {
       return FunctionLibrary.isEnabled(pageNxtButton, pageNxtButtonName);
    }

    public Boolean isPagePrevButtonEnabled() {
       return FunctionLibrary.isElementDisplayed(pagePrevButton, pagePrevButtonName);
    }

}
