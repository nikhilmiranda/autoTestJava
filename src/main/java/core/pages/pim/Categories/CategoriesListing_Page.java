package core.pages.pim.Categories;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class CategoriesListing_Page extends FluentPage {


    @FindBy(how = How.CSS, using = ".qa-category-list .new-cat-form .link-text")
    FluentWebElement addCategoryLink;

    @FindBy(how = How.NAME, using = "name")
    FluentWebElement categoryNameInputBox;

    @FindBy(how = How.CSS, using = ".new-child-btns .primary-btn")
    FluentWebElement addCategoryButton;

    @FindBy(how = How.CSS, using = ".cat-tree-item")
    FluentList<FluentWebElement> categoriesList;

    @FindBy(how = How.CSS, using = ".category-caret.right-caret")
    FluentWebElement categoryExpandIcon;


    String addCategoryLinkName = "add Category Link";
    String categoryNameInputBoxName = "Category Name InputBox";
    String addCategoryButtonName = "Add Category Button";
    String categoriesListName = "Categories List";
    String categoryExpandIconName = "Category Expand Icon";


    public void createCategory(String categoryName) {
        FunctionLibrary.click(addCategoryLink, addCategoryLinkName);
        FunctionLibrary.input(categoryNameInputBox, categoryNameInputBoxName, categoryName);
        FunctionLibrary.click(addCategoryButton, addCategoryButtonName);

    }

    public List<String> getAllCategoriesListed() {
//        while(FunctionLibrary.isElementDisplayed(categoryExpandIcon, categoryExpandIconName)){
//             FunctionLibrary.click(categoryExpandIcon, categoryExpandIconName);
//        }
        for(int i = 0; i < 20; i++){

           if(getDriver().findElement(By.cssSelector(".category-caret.right-caret")).isDisplayed()){
               getDriver().findElement(By.cssSelector(".category-caret.right-caret")).click();
           }
        }
        return FunctionLibrary.retrieveTexts(categoriesList, categoriesListName);
    }

    public void waitForPageToLoad() {
         FunctionLibrary.waitForElementToBeClickable(addCategoryLink, addCategoryLinkName);

    }

}