package core.pages.pim.ManageNetwork.Product;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import java.util.List;

public class NetworkProducts_Page extends FluentPage {

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(1)")
    FluentWebElement productsTab;

    @FindBy(how = How.CSS, using = ".horizontal-nav-bar.secondary li:nth-child(2)")
    FluentWebElement  productLabelTab;



    String productsTabName = "Products Tab";
    String productLabelTabName = "Product Label Tab";


    public void clickOnProductTab() {
        FunctionLibrary.click(productsTab, productsTabName);
    }

    public void clickOnProductLabelTab() {

        FunctionLibrary.click(productLabelTab, productLabelTabName);
    }



}
