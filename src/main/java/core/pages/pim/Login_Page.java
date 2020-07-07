package core.pages.pim;

import lib.FunctionLibrary;
import org.fluentlenium.core.FluentPage;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.support.FindBy;

public class Login_Page extends FluentPage {

    @FindBy(css = ".login-hotspot")
    FluentWebElement loginButton;

    @FindBy(name = "userEmail")
    FluentWebElement emailInputBox;

    @FindBy(name = "password")
    FluentWebElement passwordInputBox;

    @FindBy(css = ".front .login-btn")
    FluentWebElement signInButton;

    String loginButtonName = "Login Button";
    String emailInputBoxName = "Email Input Box";
    String passwordInputBoxName = "Password Input Box";
    String signInButtonName = "SignIn Button";

    public  void clickOnLogIn() {
        FunctionLibrary.click(loginButton, loginButtonName);
        FunctionLibrary.refreshPage();
    }

    public void login(String emailID, String pass) throws InterruptedException {

        FunctionLibrary.input(emailInputBox, emailInputBoxName, emailID);
        FunctionLibrary.input(passwordInputBox, passwordInputBoxName, pass);
        FunctionLibrary.click(signInButton, signInButtonName);
        FunctionLibrary.waitForPageToLoad();
    }

    public Boolean isThePageAtLoginScreen() {
        return  ( FunctionLibrary.isElementPresent(emailInputBox, emailInputBoxName) &&
        FunctionLibrary.isElementPresent(passwordInputBox, passwordInputBoxName) &&
        FunctionLibrary.isElementPresent(signInButton, signInButtonName));
    }

    public String getUrl() {
        // String url=System.getProperty("baseUrl");
        // return url;
        String url="https://pim.unbxd.io/";
        return url;
    }
}
