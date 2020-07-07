package core.components.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class NotificationMsg extends FluentPage {

    @FindBy(how = How.CSS, using = ".message-box")
    FluentWebElement notificationMessage;

    String notificationMessageName = "Notification Message";

    public String getTheNotificationMessage() {
       return FunctionLibrary.retrieveText(notificationMessage, notificationMessageName);
    }
}
