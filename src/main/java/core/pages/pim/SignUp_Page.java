package core.pages.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;


public class SignUp_Page extends FluentPage {

    @FindBy(how = How.NAME, using = "userEmail")
    FluentWebElement email;

    @FindBy(how = How.NAME, using = "password")
    FluentWebElement password;

    @FindBy(how = How.NAME, using = "orgName")
    FluentWebElement orgName;

    @FindBy(how = How.CSS, using = ".login-btn")
    FluentWebElement createMyAccount;

    @FindBy(how = How.CSS, using = ".success-msg")
    FluentWebElement successMessage;

    @FindBy(how = How.CSS, using = ".error.message-show")
    FluentWebElement errMessage;

    @FindBy(how = How.CSS, using = ".login-subtext-action")
    FluentWebElement signIn;

    public void signUp(String emailID, String pass, String org) throws InterruptedException {
        email.fill().with(emailID);
        password.fill().with(pass);
        orgName.fill().with(org);
        createMyAccount.click();
    }

    public String getSuccessMessage() {
       return successMessage.getText();
    }

    public String getErrorMessage() {
        return errMessage.getText();
    }

    public void clickOnSignInNow() throws InterruptedException {
        signIn.click();
        FunctionLibrary.waitForPageToLoad();
        getDriver().navigate().refresh();
    }

    public String getUrl() {
        return "http://pim-demo.unbxd.com/#/signup";
    }


}
