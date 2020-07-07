package core.components.pim;

import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DragAndDropModal extends FluentPage {

    @FindBy(how = How.ID, using = "newAsset")
    FluentWebElement uploadButton;

    public void uploadFile(String filePath) throws InterruptedException {
        uploadButton.fill().with(filePath);
        Thread.sleep(10000);
    }
}
