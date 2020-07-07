package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class backToList extends FluentPage {


    @FindBy(how = How.CSS, using = ".back-section .back-to-list .glyphicon-arrow-left")
    FluentWebElement backToListIcon;

    @FindBy(how = How.CSS, using = ".back-section .manage-title")
    FluentWebElement sectionTitleLabel;

    String backToListIconName = "Back To List Icon";
    String sectionTitleLabelName = "Section Title Label";

    public void clickOnBackToListIcon() {
        FunctionLibrary.scrollToTop();
        FunctionLibrary.click(backToListIcon, backToListIconName);
    }

    public void waitForLinkToBeClickable() {
        FunctionLibrary.waitForElementToBeClickable(backToListIcon, backToListIconName);
    }


    public String fetchSectionTitleLabel() {
        return FunctionLibrary.retrieveText(sectionTitleLabel, sectionTitleLabelName);
    }

    public void moveTo() {
        FunctionLibrary.scrollToElement(backToListIcon, backToListIconName);
    }
}


