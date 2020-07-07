package core.pages.pim.DAM;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

import java.util.List;

public class ManageAsset_Page extends FluentPage {


    @FindBy(how = How.NAME, using = "imgName")
    FluentWebElement imageNameInputBox;

    @FindBy(how = How.CSS, using = ".qa-asset-edit .section:nth-child(1) .primary-btn")
    FluentWebElement updateButton;

    @FindBy(how = How.CSS, using = ".section:nth-child(2) .primary-btn")
    FluentWebElement editImageButton;


    String imageNameInputBoxName = "Image Name InputBox";
    String updateButtonName = "Update Button";
    String editImageButtonName = "Edit Image Button";



    public String getTheImageName() throws InterruptedException {
        return FunctionLibrary.retrieveAttributeValue(imageNameInputBox, "value", imageNameInputBoxName);

    }

    public void updateName(String imageName) throws InterruptedException {
        FunctionLibrary.input(imageNameInputBox, imageNameInputBoxName, imageName);
        FunctionLibrary.click(updateButton, updateButtonName);

    }

    public void clickOnUpdate() throws InterruptedException {
        FunctionLibrary.click(updateButton, updateButtonName);

    }

    public void clickOnEditImage() throws InterruptedException {
        FunctionLibrary.click(editImageButton, editImageButtonName);

    }

    public void waitForThePageToLoad() {
        FunctionLibrary.waitForElementToBeClickable(updateButton, updateButtonName);
    }


}
