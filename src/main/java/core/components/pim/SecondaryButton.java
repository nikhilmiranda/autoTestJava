package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class SecondaryButton extends FluentPage {

    @FindBy(how = How.CSS, using = ".secondary-btn")
    FluentWebElement secondaryButton;

    String secondaryButtonName = "Secondary Button";

    public void clickOnSecondaryButton() {
        FunctionLibrary.click(secondaryButton, secondaryButtonName);
    }

    public Boolean isSecondaryButtonPresent() {
       return FunctionLibrary.isElementPresent(secondaryButton, secondaryButtonName);
    }

}
