package unbxdTests.testNg.ui.pim;


import core.pages.pim.Login_Page;
import core.pages.pim.SignUp_Page;
import lib.EmailAutomation.EmailUtils;
import lib.EmailAutomation.Email_Library;
import lib.EmailAutomation.Read_Email_Config;
import lib.JsonReader;
import org.fluentlenium.core.annotation.Page;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import unbxdTests.testNg.ui.BaseTest;

import javax.mail.Folder;
import javax.mail.Message;
import java.io.FileNotFoundException;

public class SignUp_Tests extends BaseTest {

    String JSON_path = System.getProperty("user.dir") + "/src/test/resources/testData/pim/signup.json" ;
    String typeData = "SignUp Data";
    int totalDataRow = 2;
    int totalColumnEntry = 4;

    String emailId;
    String password;
    String organization;

    {
        try {
            emailId = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][0].toString();
            password = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][1].toString();
            organization = JsonReader.getdata(JSON_path, typeData, totalDataRow, totalColumnEntry)[0][2].toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    String successMessage = "An account activation email has been sent to " +emailId+ ".";
    String mailSubject = "Unbxd PIM. You’re Almost there!";
    String activationLink;
    String ereMsgForNotActivatedAcc = "Please check your mail to activate your account";

    @Page
    SignUp_Page signUpPage;

    @Page
    Login_Page loginPage;


    @BeforeClass
    public void init() throws InterruptedException, FileNotFoundException {
        super.setUp();
        initFluent(driver);
        initTest();
        goTo(signUpPage);
        getDriver().navigate().refresh();
    }

    public Folder connectToEmail() {
        try {
            String username = new Read_Email_Config().getEmailUsernameFromProperties();
            String password = new Read_Email_Config().getEmailPasswordFromProperties();
            String server = new Read_Email_Config().getEmailServerFromProperties();
            EmailUtils.EmailFolder emailFolder = EmailUtils.EmailFolder.INBOX;
            return new EmailUtils().setConnection(username, password, server, emailFolder);
        } catch (Exception e) {
            e.printStackTrace();
            org.junit.Assert.fail(e.getMessage());
        }
        return null;
    }
    @Test(priority = 1)
    public void verifySignUpOfOrganisation() throws InterruptedException, FileNotFoundException {
        signUpPage.signUp(emailId, password, organization);
        Assert.assertEquals(signUpPage.getSuccessMessage(), successMessage);

    }

    @Test(priority = 2)
    public void verifyVerificationMail() throws Exception {
        int count = 0;
        Message[] msg;
        Folder folder = connectToEmail();
        do{
            count++;
            Thread.sleep(5000);
            msg = new Email_Library().getMessagesBySubject("Unbxd PIM", true, 1, folder);
        }
        while(msg.length ==0 && count ==60);

        Assert.assertTrue(msg[0].getSubject().equalsIgnoreCase(mailSubject));
        activationLink = new Email_Library().getUrlsFromMessage(msg[0], "Activate Now", folder).get(0);

        Assert.assertTrue(activationLink.contains(emailId));
    }
    @Test(priority = 3)
    public void verifyErrorMessageForUnActivatedAccount() throws InterruptedException {
        signUpPage.clickOnSignInNow();
        loginPage.login(emailId,password);
        Assert.assertEquals(signUpPage.getErrorMessage(),ereMsgForNotActivatedAcc);
    }
    @Test(priority = 4)
    public void activateAccount() throws InterruptedException {
        System.out.println(activationLink);
        getDriver().get(activationLink);
        loginPage.login(emailId,password);
        Thread.sleep(40000);
        Assert.assertTrue(getDriver().getCurrentUrl().contains("overview/orgId="));
    }
}
