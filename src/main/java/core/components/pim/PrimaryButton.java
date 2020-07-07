package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class PrimaryButton extends FluentPage {

    @FindBy(how = How.CSS, using = ".primary-btn")
    FluentWebElement primaryButton;

    @FindBy(how = How.CSS, using = ".modal-buttons .primary-btn")
    FluentWebElement primaryModalButton;

    String primaryButtonName = "Primary Button";

    public void clickOnPrimaryButton() {
        FunctionLibrary.click(primaryButton, primaryButtonName);
    }

    public void clickOnPrimaryModalButton() {
        FunctionLibrary.click(primaryModalButton, primaryButtonName);
    }
}
