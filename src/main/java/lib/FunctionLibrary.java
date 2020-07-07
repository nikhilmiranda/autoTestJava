package lib;

import org.apache.log4j.Logger;
import org.fluentlenium.core.Fluent;
import org.fluentlenium.core.domain.FluentList;
import org.fluentlenium.core.domain.FluentWebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import org.openqa.selenium.support.ui.Select;

public class FunctionLibrary extends Fluent {

    public static String defaultWindow = null;
    public static Logger APPLICATION_LOGS = Logger.getLogger("Abhishek");


    /**
     * public static void uncheckCheckBox(FluentWebElement locator, String elemName) method
     * specification :-
     *
     * 1) Checks a check-box if not checked already 2) locator -> to locate the
     * element by id,x-path,name,etc. 3) elemName -> the name/type of the
     * check-box which we intend to check 4)
     * locator.getAttribute("value") == "on" -> is used to
     * verify whether the intended checkbox is earlier checked or not 5)
     * locator.click() -> checks the check-box
     *
     * @param : Locator for the Check-box, name of the web element
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static  void uncheckCheckBox(FluentWebElement locator, String elemName) {

        APPLICATION_LOGS.debug("Unchecking the checkbox : " + elemName);
        System.out.println("Unchecking the checkbox : " + elemName);

        try {

            // Highlight check-box
          //  highlightElement(locator);

            // Wait for check-box to appear on the page
            waitForElementToLoad(locator, elemName);

            // UnCheck check-box if already checked
            if (locator.isSelected()) {
                locator.click();
            }

            // Log the result
            System.out.println("Unchecked '" + elemName + "'");
            APPLICATION_LOGS.debug("Unchecked '" + elemName + "'");


        }

        catch (Throwable uncheckCheckBoxException) {

            // Log the exception
            System.err.println("Error came while unchecking '" + elemName
                    + "' : " + uncheckCheckBoxException.getMessage());
            APPLICATION_LOGS.debug("Error came while unchecking '" + elemName
                    + "' : " + uncheckCheckBoxException.getMessage());



        }

    }

    /**
     * public static String verifyTextPresent(String expText, String elemName)
     * method specification :-
     *
     * 1) Verifies text present in the page source 2) expText -> Expected text
     * to be verified from page source 3) elemName -> the name/type of test we
     * are expecting 4)
     * Assert.assertTrue(BrowserInitializer.getDriver().getPageSource().contains(expText)) -> verifies
     * whether the expected text exist int the page source or not
     *
     * @param : Expected text to verify
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String verifyTextPresent(String expText) {

        APPLICATION_LOGS.debug("Verifying Text : '" + expText + "' "
                + "present in the Page Source");
        System.out.println("Verifying Text : '" + expText + "' "
                + "present in the Page Source");

        try {

            // Verify page source contains expected text
            Assert.assertTrue(BrowserInitializer.getDriver().getPageSource().contains(expText));

            // Log result
            APPLICATION_LOGS.debug("'" + expText
                    + "' present in the Page Source");
            System.out.println("'" + expText + "' present in the Page Source");

            return "Pass : '" + expText + "' present in the Page Source";

        }

        catch (Throwable verifyTextPresentError) {

            // report error
            System.err.println("Error while Verifying Text from Page Source : "
                    + verifyTextPresentError.getMessage());
            APPLICATION_LOGS
                    .debug("Error while Verifying Text from Page Source : "
                            + verifyTextPresentError.getMessage());

            return "Fail : Error while Verifying Text from Page Source : "
                    + verifyTextPresentError.getMessage();

        }

    }

    /**
     * public static String click(FluentWebElement locator,String elemName) method
     * specification :-
     *
     * 1) Clicks on a web element 2) locator -> to locate the element by id,
     * x-path, name,etc. 3) elemName -> the name of the element which we intend
     * to click 4) waitForElementToLoad(locator) -> waits for element to load 5)
     * locator.click() -> clicks on the intended element
     *
     * @param : Locator for the link, name of the web element
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static void click(FluentWebElement locator, String elemName) {

        APPLICATION_LOGS.debug("Clicking on : " + elemName);
        System.out.println("Clicking on : " + elemName);

        try {
            // Wait for link to appear on the page
            waitForElementToLoad(locator, elemName);

            // Click on the link
            locator.click();

            // Log result
            System.out.println("Clicked on : " + elemName);
            APPLICATION_LOGS.debug("Clicked on : " + elemName);


        } catch (StaleElementReferenceException clickStaleException) {

            System.err.println("Stale Element Exception is handled for '" + elemName
                    + "' : " + clickStaleException.getMessage());
            ThreadWait();

            // Wait for link to appear on the page
            waitForElementToLoad(locator, elemName);

            // Click on the link
            locator.click();

            // Log result
            System.out.println("Retrying...Clicked on : " + elemName);
            APPLICATION_LOGS.debug("Retrying...Clicked on : " + elemName);

        }
        catch (Throwable clickException) {

            APPLICATION_LOGS.debug("Clicking for 2nd time on : " + elemName);
            System.out.println("Clicking for 2nd time on : " + elemName);

            try {
                // Wait for link to appear on the page
                waitForElementToLoad(locator, elemName);

                // Click on the link
                locator.click();

                // Log result
                System.out.println("Clicked for 2nd time on : " + elemName);
                APPLICATION_LOGS.debug("Clicked for 2nd time on : " + elemName);


            }catch (Throwable clickException1) {

                System.err.println("Error while clicking on - '" + elemName
                        + "' : " + clickException.getMessage());
                APPLICATION_LOGS.debug("Error while clicking on - '" + elemName
                        + "' : " + clickException.getMessage());
            }
        }
    }


    /**
     * public static String click(FluentWebElement locator,String elemName) method
     * specification :-
     *
     * 1) Clicks on a web element 2) locator -> to locate the element by id,
     * x-path, name,etc. 3) elemName -> the name of the element which we intend
     * to click 4) waitForElementToLoad(locator) -> waits for element to load 5)
     * locator.click() -> clicks on the intended element
     *
     * @param : Locator for the link, name of the web element
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static void click(WebElement locator, String elemName) {

        APPLICATION_LOGS.debug("Clicking on : " + elemName);
        System.out.println("Clicking on : " + elemName);

        try {
            // Wait for link to appear on the page
            waitForElementToLoad(locator, elemName);

            // Click on the link
            locator.click();

            // Log result
            System.out.println("Clicked on : " + elemName);
            APPLICATION_LOGS.debug("Clicked on : " + elemName);


        } catch (StaleElementReferenceException clickStaleException) {

            System.err.println("Stale Element Exception is handled for '" + elemName
                    + "' : " + clickStaleException.getMessage());
            ThreadWait();

            // Wait for link to appear on the page
            waitForElementToLoad(locator, elemName);

            // Click on the link
            locator.click();

            // Log result
            System.out.println("Retrying...Clicked on : " + elemName);
            APPLICATION_LOGS.debug("Retrying...Clicked on : " + elemName);

        }
        catch (Throwable clickException) {

            APPLICATION_LOGS.debug("Clicking for 2nd time on : " + elemName);
            System.out.println("Clicking for 2nd time on : " + elemName);

            try {
                // Wait for link to appear on the page
                waitForElementToLoad(locator, elemName);

                // Click on the link
                locator.click();

                // Log result
                System.out.println("Clicked for 2nd time on : " + elemName);
                APPLICATION_LOGS.debug("Clicked for 2nd time on : " + elemName);


            }catch (Throwable clickException1) {

                System.err.println("Error while clicking on - '" + elemName
                        + "' : " + clickException.getMessage());
                APPLICATION_LOGS.debug("Error while clicking on - '" + elemName
                        + "' : " + clickException.getMessage());
            }
        }
    }

    /**
     * public static String click(FluentWebElement locator,String elemName) method
     * specification :-
     *
     * 1) Clicks on a web element 2) locator -> to locate the element by id,
     * x-path, name,etc. 3) elemName -> the name of the element which we intend
     * to click 4) waitForElementToLoad(locator) -> waits for element to load 5)
     * locator.click() -> clicks on the intended element
     *
     * @param : Locator for the link, name of the web element
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static void click(By locator, String elemName) {

        APPLICATION_LOGS.debug("Clicking on : " + elemName);
        System.out.println("Clicking on : " + elemName);

        try {
            // Wait for link to appear on the page
            waitForElementToLoad(BrowserInitializer.getDriver().findElement(locator), elemName);

            // Click on the link
            BrowserInitializer.getDriver().findElement(locator).click();

            // Log result
            System.out.println("Clicked on : " + elemName);
            APPLICATION_LOGS.debug("Clicked on : " + elemName);


        } catch (StaleElementReferenceException clickStaleException) {

            System.err.println("Stale Element Exception is handled for '" + elemName
                    + "' : " + clickStaleException.getMessage());
            ThreadWait();

            // Wait for link to appear on the page
            waitForElementToLoad(BrowserInitializer.getDriver().findElement(locator), elemName);

            // Click on the link
            BrowserInitializer.getDriver().findElement(locator).click();

            // Log result
            System.out.println("Retrying...Clicked on : " + elemName);
            APPLICATION_LOGS.debug("Retrying...Clicked on : " + elemName);

        }
        catch (Throwable clickException) {

            APPLICATION_LOGS.debug("Clicking for 2nd time on : " + elemName);
            System.out.println("Clicking for 2nd time on : " + elemName);

            try {
                // Wait for link to appear on the page
                waitForElementToLoad(BrowserInitializer.getDriver().findElement(locator), elemName);

                // Click on the link
                BrowserInitializer.getDriver().findElement(locator).click();

                // Log result
                System.out.println("Clicked for 2nd time on : " + elemName);
                APPLICATION_LOGS.debug("Clicked for 2nd time on : " + elemName);


            }catch (Throwable clickException1) {

                System.err.println("Error while clicking on - '" + elemName
                        + "' : " + clickException.getMessage());
                APPLICATION_LOGS.debug("Error while clicking on - '" + elemName
                        + "' : " + clickException.getMessage());
            }
        }
    }


    public static void dragAndDrop(FluentWebElement sourceLocator, FluentWebElement destLocator, String sourceName, String destName) {

        APPLICATION_LOGS.debug("Dragging : " + sourceName);
        System.out.println("Dragging  : " + sourceName);

        try{

            // Wait for link to appear on the page
            waitForElementToLoad(sourceLocator, sourceName);

            Actions action=new Actions(BrowserInitializer.getDriver());
            action.dragAndDrop(sourceLocator.getElement(), destLocator.getElement()).build().perform();

            APPLICATION_LOGS.debug("Dragging : " + sourceName);
            System.out.println("Dragging  : " + sourceName);

        }
        catch (Throwable dragException) {
            // Log error
            System.err.println("Error while dragging  - '" + sourceName
                    + "' : " + dragException.getMessage());
            APPLICATION_LOGS.debug("Error while dragging - '" + sourceName
                    + "' : " + dragException.getMessage());
        }
    }


    /**
     * public static String clickLink(FluentWebElement locator,String elemName) method
     * specification :-
     *
     * 1) Clicks on a web element 2) locator -> to locate the element by id,
     * x-path, name,etc. 3) elemName -> the name of the element which we intend
     * to click 4) waitForElementToLoad(locator) -> waits for element to load 5)
     * locator.click() -> clicks on the intended element
     *
     * @param : Locator for the link, name of the web element
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String clickLinkIE(FluentWebElement locator, String elemName) {

        APPLICATION_LOGS.debug("Clicking on : " + elemName);
        System.out.println("Clicking on : " + elemName);

        try {

            // Wait for link to appear on the page
            waitForElementToLoad(locator, elemName);

            // Highlight link
            highlightElement(locator);

            // Click on the link
            locator.getElement().sendKeys(Keys.ENTER);


            // Log result
            System.out.println("Clicked on : " + elemName);
            APPLICATION_LOGS.debug("Clicked on : " + elemName);

            return "Pass : Clicked on : " + elemName;

        }

        catch (Throwable clickLinkException) {

            // Log error
            System.err.println("Error while clicking on - '" + elemName
                    + "' : " + clickLinkException.getMessage());
            APPLICATION_LOGS.debug("Error while clicking on - '" + elemName
                    + "' : " + clickLinkException.getMessage());

            return "Fail : Error while clicking on - '" + elemName + "' : "
                    + clickLinkException.getMessage();

        }

    }

    public static void scrollToElement(FluentWebElement locator, String name) {

        APPLICATION_LOGS.debug("Scrolling to "+name);
        System.out.println("Scrolling to "+name);

        // Initialize Javascript executor
        JavascriptExecutor js = (JavascriptExecutor) BrowserInitializer.getDriver();

        try {

            js.executeScript("arguments[0].scrollIntoView(true);", locator.getElement());
            System.out.println("Scrolled to "+name);
            APPLICATION_LOGS.debug("Scrolled to "+name);

        }

        catch (Throwable scrollException) {

            // Log error
            System.err.println("Error while scrolling "+name);
            APPLICATION_LOGS.debug("Error while scrolling "+name);

        }
    }

    public static void scrollToBottom() {

        APPLICATION_LOGS.debug("Scrolling to bottom of page");
        System.out.println("Scrolling to bottom of page");

        try {
            ((JavascriptExecutor) BrowserInitializer.getDriver())
                    .executeScript("window.scrollTo(0, document.body.scrollHeight)");

            System.out.println("Scrolled to bottom of page");
            APPLICATION_LOGS.debug("Scrolled to bottom of page");

        }

        catch (Throwable scrollException) {

            // Log error
            System.err.println("Error while scrolling down to bottom of the page");
            APPLICATION_LOGS.debug("Error while scrolling down to bottom of the page");

        }
    }

    public static void scrollToTop() {

        APPLICATION_LOGS.debug("Scrolling to top of page");
        System.out.println("Scrolling to top of page");

        try {
            ((JavascriptExecutor) BrowserInitializer.getDriver())
                    .executeScript("window.scrollTo(0, 0)");

            System.out.println("Scrolled to top of page");
            APPLICATION_LOGS.debug("Scrolled to top of page");

        }

        catch (Throwable scrollException) {

            // Log error
            System.err.println("Error while scrolling to top of the page");
            APPLICATION_LOGS.debug("Error while scrolling to top of the page");

        }
    }



    /**
     * public static String clearField(FluentWebElement locator,String elemName) method
     * specification :-
     *
     * 1) Clears a text field 2) locator -> identify the text field by
     * id,x-path,name,etc. 3) elemName -> the name of the text-field which we
     * intend to clear 4) waitForElementToLoad(locator) -> waits for text-field
     * to load 5) locator.clear() -> clears the intended
     * text-field
     *
     * @param : Locator for the input-box, name of the web element
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String clearField(FluentWebElement locator, String elemName) {

        APPLICATION_LOGS.debug("Clearing field : " + elemName);
        System.out.println("Clearing field : " + elemName);

        try {

            // Wait for the input-box to load on the page
            waitForElementToLoad(locator, elemName);

            // Clear the input-box
            locator.clear();

            //second check
            if(!locator.getValue().isEmpty())
                locator.clear();

            // Log result
            System.out.println("Cleared : " + elemName);
            APPLICATION_LOGS.debug("Cleared : " + elemName);

            return "Pass : Cleared : " + elemName;

        }

        catch (Throwable clearFieldException) {

            // Log error
            System.err.println("Error while clearing - " + elemName + " : "
                    + clearFieldException.getMessage());
            APPLICATION_LOGS.debug("Error while clearing - " + elemName + " : "
                    + clearFieldException.getMessage());

            return "Fail : Error while clearing - " + elemName + " : "
                    + clearFieldException.getMessage();

        }

    }

    /**
     * public static String input(FluentWebElement locator,String elemName,String Value)
     * method specification :-
     *
     * 1) Inputs/sends value 2) locator -> identify the web element by
     * id,x-path,name,etc. 3) elemName -> the name of the web element where we
     * intend to input/send values 4) Value -> the string value which we intend
     * to input/send 5) waitForElementToLoad(locator) -> waits for web element
     * to load 6) locator.sendKeys(Value) -> inputs/sends
     * the value to the intended web element
     *
     * @param : Locator for the input-box, name of the web element, value to be
     *        inputted
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static void input(FluentWebElement locator, String elemName, String Value) {

        APPLICATION_LOGS.debug("Sending Values in : " + elemName);
        System.out.println("Sending Values in : " + elemName);

        try {

            // Wait for the input box to appear on the page
            waitForElementToLoad(locator, elemName);

            // Send values to the input box
            if(locator.getAttribute("value").isEmpty() && locator.getText().isEmpty() && locator.getValue().isEmpty()){
                locator.fill().with(Value);
                locator.getElement().sendKeys(Keys.TAB);
            }else{
                locator.clear();
                locator.click();
                locator.fill().with(Keys.chord(Keys.CONTROL + "a" + Keys.DELETE));
                while(!locator.getAttribute("value").isEmpty() && !locator.getText().isEmpty() && !locator.getValue().isEmpty()){
                    locator.getElement().sendKeys(Keys.chord(Keys.BACK_SPACE));
                }

                System.out.println("Cleared : " + elemName);
                APPLICATION_LOGS.debug("Cleared : " + elemName);
                locator.fill().with(Value);
                System.out.println("Inputted '" + Value + "' text into : "
                        + elemName);
                APPLICATION_LOGS.debug("Inputted '" + Value + "' text into : "
                        + elemName);
                locator.getElement().sendKeys(Keys.TAB);
            }

            System.out.println("Inputted '" + Value + "' text into : "
                    + elemName);
            APPLICATION_LOGS.debug("Inputted '" + Value + "' text into : "
                    + elemName);

        }
        catch (StaleElementReferenceException inputStaleException) {

            System.err.println("Stale Element Exception is handled for '" + elemName
                    + "' : " + inputStaleException.getMessage());

           ThreadWait();

            // Wait for link to appear on the page
            waitForElementToLoad(locator, elemName);

            // Send values to the input box
            if(locator.getAttribute("value").isEmpty()){
                locator.fill().with(Value);
                locator.getElement().sendKeys(Keys.TAB);
            }else{
                locator.clear();
                locator.fill().with(Value);
                locator.getElement().sendKeys(Keys.TAB);
            }

            // Log result
            System.out.println("Retrying...Inputted '" + Value + "' text into : "
                    + elemName);
            APPLICATION_LOGS.debug("Retrying...Inputted '" + Value + "' text into : "
                    + elemName);

        }
        catch (Throwable inputException) {

            // Log error
            System.err.println("Error while inputting into - " + elemName
                    + " : " + inputException.getMessage());
            APPLICATION_LOGS.debug("Error while inputting into - " + elemName
                    + " : " + inputException.getMessage());

        }

    }


    /*
     * public static String getPageTitle() method specification :-
     *
     * 1) BrowserInitializer.getDriver().getTitle() : Get current page title
     *
     * @param : none
     *
     * @return : Current webpage title
     */

    public static String getPageURL() {

        APPLICATION_LOGS.debug("Getting current webpage url ...");
        String pageURL = null;

        try {

            // Get page title
            pageURL = BrowserInitializer.getDriver().getCurrentUrl();

        } catch (Throwable getPageTitleError) {

            APPLICATION_LOGS.debug("Error came while fetching page url : "
                    + getPageTitleError.getMessage());

        }

        APPLICATION_LOGS.debug("Got current webpage title : " + pageURL);
        return pageURL;

    }


    /**
     * public static String inputChord(FluentWebElement locator,String elemName,String Value)
     * method specification :-
     *
     * 1) Inputs/sends value in chord 2) locator -> identify the web element by
     * id,x-path,name,etc. 3) elemName -> the name of the web element where we
     * intend to input/send values 4) Value -> the string value which we intend
     * to input/send 5) waitForElementToLoad(locator) -> waits for web element
     * to load 6) locator.sendKeys(Keys.chord(Value)) ->
     * inputs/sends the value in chord to the intended web element
     *
     * @param : Locator for the input-box, name of the web element, value to be
     *        inputed
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String inputChord(FluentWebElement locator, String elemName, String Value) {

        APPLICATION_LOGS.debug("Sending Values in : " + elemName);
        System.out.println("Sending Values in : " + elemName);

        try {

            // Wait for input box to appear on the page
            waitForElementToLoad(locator, elemName);

            // Highlight input box
            highlightElement(locator);

            // Send values in chord to the input box
            locator.getElement().sendKeys(Keys.chord(Value));

            // Log result
            System.out.println("Inputted '" + Value + "' text into : '"
                    + elemName + "'");
            APPLICATION_LOGS.debug("Inputted '" + Value + "' text into : '"
                    + elemName + "'");

            return "Pass : Inputted '" + Value + "' text into : '" + elemName
                    + "'";

        }

        catch (Throwable inputException) {

            // Log error
            System.err.println("Error while inputting into - '" + elemName
                    + "' : " + inputException.getMessage());
            APPLICATION_LOGS.debug("Error while inputting into - '" + elemName
                    + "' : " + inputException.getMessage());

            return "Fail : Error while inputting into - '" + elemName + "' : "
                    + inputException.getMessage();

        }

    }

    /**
     * public static String assertText(String elemName,String actValue, String
     * expValue) method specification :-
     *
     * 1) Verifies and returns TRUE if expected and actual text match 2)
     * elemName -> the name/type of text we intend to compare 3) actValue -> the
     * actual string value which is shown in the application 4) expValue -> the
     * expected string value which should be shown in the application 5)
     * Assert.assertEquals(expValue.trim(), actValue.trim())) -> trims and
     * compares the actual and expected string value
     *
     * @param : Name of the web element, Actual text and expected text
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String assertText(String elemName, String actValue,
                                    String expValue) {

        APPLICATION_LOGS.debug("Asserting  Text  where : ExpectedText = "
                + expValue + "  and ActualText = " + actValue);

        System.out.println("Asserting  Text  where : ExpectedText = "
                + expValue + "  and ActualText = " + actValue);

        try {

            // Assert that expected value matches with actual value
            Assert.assertEquals(expValue.trim(), actValue.trim());

            // Log result
            System.out.println("Successfully asserted text for : " + elemName
                    + " where Expected text is '" + expValue
                    + "' and Actual text is '" + actValue + "'");
            APPLICATION_LOGS.debug("Successfully asserted text for : "
                    + elemName + " where Expected text is '" + expValue
                    + "' and Actual text is '" + actValue + "'");

            return "Pass : Expected text matches with actual text";

        }

        catch (Throwable assertTextException) {

            // Log error
            System.err.println("Error while Asserting Text for - " + elemName
                    + " : " + assertTextException.getMessage());
            APPLICATION_LOGS.debug("Error while Asserting Text for - "
                    + elemName + " : " + assertTextException.getMessage());

            return "Fail : Error while Asserting Text for - " + elemName
                    + " : " + assertTextException.getMessage();

        }

    }

    /**
     * public static String assertCondition(String elemName,Boolean condition)
     * method specification :-
     *
     * 1) Verifies and returns TRUE if condition mentioned is true 2) elemName
     * -> Name of the web element for which we are asserting the condition 3)
     * condition -> Condition to be checked 5) Assert.assertTrue(condition) ->
     * Asserts whether the condition mentioned is TRUE or not
     *
     * @param : Name of the web element, condition to assert
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String assertCondition(String elemName, Boolean condition) {

        APPLICATION_LOGS.debug("Asserting Condition for : " + elemName);
        System.out.println("Asserting Condition for : " + elemName);

        try {

            // Assert that condition is true
            Assert.assertTrue(condition);

            // Log result
            APPLICATION_LOGS.debug("Successfully asserted condition for : "
                    + elemName);
            System.out.println("Successfully asserted condition for : "
                    + elemName);

            return "Pass : Successfully asserted condition for : " + elemName;

        }

        catch (Throwable assertConditionException) {

            // Log error
            System.err
                    .println("Error while asserting Condition for - '"
                            + elemName + "' : "
                            + assertConditionException.getMessage());
            APPLICATION_LOGS
                    .debug("Error while asserting Condition for - '" + elemName
                            + "' : " + assertConditionException.getMessage());

            return "Fail : Error while asserting Condition for - '" + elemName
                    + "' : " + assertConditionException.getMessage();

        }

    }

    /**
     * public static String closePopupWindow() method specification :-
     *
     * 1) Closes the popup window 2) BrowserInitializer.getDriver().close() -> closes the popup window
     * which has the current window handle 3)
     * BrowserInitializer.getDriver().switchTo().window(mainWindow) -> switches back to main window by
     * granting the current window handle to main window
     *
     * @param : no parameters
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String closePopupWindow() throws InterruptedException {

        APPLICATION_LOGS.debug("Closing pop-up window");
        System.out.println("Closing pop-up window");

        try {

            // Close current window pointed by webBrowserInitializer.getDriver()
            BrowserInitializer.getDriver().close();

            // Switch back to the main window
            BrowserInitializer.getDriver().switchTo().window(defaultWindow);

            // Log result
            APPLICATION_LOGS.debug("Closed pop-up window");
            System.out.println("Closed pop-up window");

            return "Pass : Closed pop-up window";

        }

        catch (Throwable closePopUpException) {

            // Log error
            System.err.println("Error while closing pop-up window : "
                    + closePopUpException.getMessage());
            APPLICATION_LOGS.debug("Error while closing pop-up window : "
                    + closePopUpException.getMessage());

            return "Fail : Error while closing pop-up window : "
                    + closePopUpException.getMessage();

        }

    }

    /**
     * public static String closePopupWindow() method specification :-
     *
     * 1) Closes the popup window 2) BrowserInitializer.getDriver().close() -> closes the popup window
     * which has the current window handle 3)
     * BrowserInitializer.getDriver().switchTo().window(mainWindow) -> switches back to main window by
     * granting the current window handle to main window
     *
     * @param : no parameters
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String closeDriver() throws InterruptedException {

        APPLICATION_LOGS.debug("Closing Window");
        System.out.println("Closing Window");

        try {

            if(BrowserInitializer.getDriver() != null)
            // Close current window pointed by webBrowserInitializer.getDriver()
            BrowserInitializer.getDriver().close();

            // Log result
            APPLICATION_LOGS.debug("Closed Window");
            System.out.println("Closed Window");

            return "Pass : Closed Window";

        }

        catch (Throwable closePopUpException) {

            // Log error
            System.err.println("Error while closing Window : "
                    + closePopUpException.getMessage());
            APPLICATION_LOGS.debug("Error while closing Window : "
                    + closePopUpException.getMessage());

            return "Fail : Error while closing Window : "
                    + closePopUpException.getMessage();

        }

    }

    /**
     * public static String switchToDefaultWindow() method specification :-
     *
     * 1) Switches back to the main window 2)
     * BrowserInitializer.getDriver().switchTo().window(mainWindow) -> switches back to main window by
     * granting the current window handle to main window
     *
     * @param : no parameters
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String switchToDefaultWindow() throws InterruptedException {

        APPLICATION_LOGS.debug("Switching to Default Window");
        System.out.println("Switching to Default Window");

        try {

            // Switch to main window
            BrowserInitializer.getDriver().switchTo().window(defaultWindow);

            // Log result
            System.out.println("Switched to default window");
            APPLICATION_LOGS.debug("Switched to default window");

            return "Pass : Switched to default window";

        }

        catch (Throwable switchToDefaultWindowException) {

            // Log error
            System.err.println("Error while switching to default window : "
                    + switchToDefaultWindowException.getMessage());
            APPLICATION_LOGS.debug("Error while switching to default window : "
                    + switchToDefaultWindowException.getMessage());

            return "Fail : Error while switching to default window : "
                    + switchToDefaultWindowException.getMessage();

        }

    }

    /**
     * public static String switchToPopupWindow() method specification :-
     *
     * 1) Switches to pop-up window 2) BrowserInitializer.getDriver().getWindowHandle() -> Returns
     * current window handle 3) BrowserInitializer.getDriver().getWindowHandles() -> Returns all the
     * available window handles 4) BrowserInitializer.getDriver().switchTo().window(popUpWindowHandle)
     * -> Switches to pop-up window
     *
     * @param : no parameters
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String switchToPopupWindow() throws InterruptedException {

        APPLICATION_LOGS.debug("Executing switchToPopupWindow");
        System.out.println("Switching to Pop Window : ");

        String popUpWindowHandle = null;

        try {

            // Save current window handle for future reference
           String defaultWindow = BrowserInitializer.getDriver().getWindowHandle();

            // Get all the window handles one by one
            for (String windowHandle : BrowserInitializer.getDriver().getWindowHandles()) {

                // Save new window handle
                if (!windowHandle.equals(defaultWindow)) {

                    popUpWindowHandle = windowHandle;

                }

            }

            // Switches to pop-up window
            BrowserInitializer.getDriver().switchTo().window(popUpWindowHandle);

            // Log result
            System.out.println("Switched to pop-up window");
            APPLICATION_LOGS.debug("Switched to pop-up window");

            return "Pass : Switched to pop-up window";

        }

        catch (Throwable switchToPopupWindowException) {

            // Log error
            System.err.println("Error while Switching to Pop Window : "
                    + switchToPopupWindowException.getMessage());
            APPLICATION_LOGS.debug("Error while Switching to Pop Window : "
                    + switchToPopupWindowException.getMessage());

            return "Fail : Error while Switching to Pop Window : "
                    + switchToPopupWindowException.getMessage();

        }

    }



    /**
     * public static String selectValueByVisibleText(By Locator, String Option,
     * String elemName) method specification :-
     *
     * 1) Select value from drop-down by visible text 2) Select -> This is a
     * in-built class in Selenium which is used to represent a drop-down 3)
     * select.selectByVisibleText(Value) -> Select by visible text
     *
     * @param : Locator for the drop-down field, Option to be selected, Name of
     *        the web element
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String selectValueByVisibleText(FluentWebElement Locator, String Option,
                                                  String elemName) {

        APPLICATION_LOGS.debug("Selecting '" + Option + "' from : " + elemName);
        System.out.println("Selecting '" + Option + "' from : " + elemName);

        try {

            // Wait for drop-down element to load on the page
            waitForElementToLoad(Locator, elemName);

            // Highlight the drop-down
            highlightElement(Locator);

            // Locate drop-down field
            Select select = new Select(Locator.getElement());

            // Select value from drop-down
            select.selectByVisibleText(Option);

            // Log result
            System.out.println("Selected '" + Option + "' from : " + elemName);
            APPLICATION_LOGS.debug("Selected '" + Option + "' from : "
                    + elemName);

            return "Pass : Selected '" + Option + "' from : " + elemName;

        }

        catch (Throwable selectValueException) {

            // Log error
            System.err.println("Error while Selecting Value from - '"
                    + elemName + "' : " + selectValueException.getMessage());
            APPLICATION_LOGS.debug("Error while Selecting Value from - '"
                    + elemName + "' : " + selectValueException.getMessage());

            return "Fail : Error while Selecting Value from - '" + elemName
                    + "' : " + selectValueException.getMessage();

        }

    }

    /**
     * public static String selectValueByIndex(By Locator, int index, String
     * elemName) method specification :-
     *
     * 1) Select value from drop-down by index 2) Select -> This is a in-built
     * class in Selenium which is used to represent a drop-down 3)
     * select.selectByIndex(index) -> Select by index
     *
     * @param : Locator for the drop-down field, Index for the option to be
     *        selected, Name of the web element
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String selectValueByIndex(FluentWebElement Locator, int index,
                                            String elemName) {

        APPLICATION_LOGS.debug("Selecting value from : " + elemName);
        System.out.println("Selecting value from : " + elemName);

        try {

            // Wait for drop-down element to load on the page
            waitForElementToBeClickable(Locator, elemName);

            // Locate drop-down field
            Select select = new Select(Locator.getElement());

            // Select value from drop-down
            select.selectByIndex(index);

            // Log result
            System.out.println("Selected value from : " + elemName);
            APPLICATION_LOGS.debug("Selected value from : " + elemName);

            return "Pass : Selected value from : " + elemName;

        }

        catch (Throwable selectValueException) {

            // Log error
            System.err.println("Error while Selecting Value from - '"
                    + elemName + "' : " + selectValueException.getMessage());
            APPLICATION_LOGS.debug("Error while Selecting Value from - '"
                    + elemName + "' : " + selectValueException.getMessage());

            return "Fail : Error while Selecting Value from - '" + elemName
                    + "' : " + selectValueException.getMessage();

        }

    }

    /**
     * public static Boolean isElementPresent(By Locator,String elemName) method
     * specification :-
     *
     * 1) Check whether an element present or not on the webpage 2)
     * BrowserInitializer.getDriver().findElement(Locator).isDisplayed() -> Return true/false based on
     * whether element is displayed or not on the page
     *
     * @param : Locator for the web element, Name of the web element
     * @return : True/false based on whether element is displayed on the page or
     *         not
     */

    public static Boolean isElementPresent(FluentWebElement Locator, String elemName) {

        APPLICATION_LOGS.debug("Verifying whether Element : " + elemName
                + " is present");
        System.out.println("Verifying whether Element : " + elemName
                + " is present");

        Boolean present = null;

        try {

            // Wait for web element to load
             waitForElementToBeClickable(Locator, elemName);

            // Verify whether element is displayed on the page or not
            present = Locator.isDisplayed();

            // Log result
            if (present) {

                System.out.println("Element : " + elemName + " is present");
                APPLICATION_LOGS.debug("Element : " + elemName + " is present");

            }
            else {

                System.out.println("Element : " + elemName + " is not present");
                APPLICATION_LOGS.debug("Element : " + elemName
                        + " is not present");
            }

            return present;

        }

        catch (Throwable isElementPresentException) {

            // Log error
            System.err.println("Error while verifying - " + elemName
                    + " element Present : "
                    + isElementPresentException.getMessage());
            APPLICATION_LOGS.debug("Error while verifying - " + elemName
                    + " element Present : "
                    + isElementPresentException.getMessage());

            return false;

        }

    }

    public static Boolean isElementPresentWithoutWait(By Locator, String elemName) {

        APPLICATION_LOGS.debug("Verifying whether Element : " + elemName
                + " is present");
        System.out.println("Verifying whether Element : " + elemName
                + " is present");

        Boolean present ;

        try {

            // Wait for web element to load
            //waitForElementToBeClickable(locator, elemName);

            // Highlight the web element
            //FunctionLibrary.highlightElement(BrowserInitializer.getDriver(), Locator);

            // Verify whether element is displayed on the page or not
            present = BrowserInitializer.getDriver().findElement(Locator).isDisplayed();

            // Log result
            if (present) {

                System.out.println("Element : " + elemName + " is present");
                APPLICATION_LOGS.debug("Element : " + elemName + " is present");

            }

            else {

                System.out.println("Element : " + elemName + " is not present");
                APPLICATION_LOGS.debug("Element : " + elemName
                        + " is not present");

            }

            return present;

        }

        catch (Throwable isElementPresentException) {



            return false;

        }

    }
    /*
     * public static Boolean isElementPresent(FluentWebElement locator, String elemName)
     * method specification :-
     *
     * locator : Checking whether element present or not
     *
     * @param : web element locator, web element name
     *
     * @return : (true) - If element is present (false) - If element not present
     */
    public static Boolean isElementPresent1(FluentWebElement locator, String elemName) {

        APPLICATION_LOGS.debug("Checking whether " + elemName
                + " is present on the page or not ...");

        try {

            // Check whether web element is displayed or not
            locator.isDisplayed();

            APPLICATION_LOGS.debug(elemName + " is present on the page");
            return true;

        }

        catch (NoSuchElementException elementPresentError) {

            APPLICATION_LOGS.debug(elemName + " not present on the page");
            return false;

        }

    }



    /*
     * public static Boolean isElementDisplayed(FluentWebElement locator, String elemName)
     * method specification :-
     *
     * locator.isDisplayed() : Verifying whether element
     * displayed or not
     *
     * @param : web element locator, web element name
     *
     * @return : (true) - If element is displayed (false) - If element not
     * displayed
     */
    public static Boolean isElementDisplayed(FluentWebElement locator, String elemName) {

        APPLICATION_LOGS.debug("Checking whether " + elemName
                + " is displayed on the page or not ...");
        System.out.println("Checking whether " + elemName
                + " is displayed on the page or not ...");

        Boolean isDisplayed;

        try {

            // Check whether web element is displayed or not
            isDisplayed = locator.isDisplayed();

            if (isDisplayed) {
                APPLICATION_LOGS.debug(elemName + " is displayed on the page");
                System.out.println(elemName + " is displayed on the page");
            } else {
                APPLICATION_LOGS.debug(elemName + " not displayed on the page");
                System.out.println(elemName + " not displayed on the page");
            }

            return isDisplayed;

        }

        catch (NoSuchElementException elementPresentError) {

            APPLICATION_LOGS.debug(elemName + " not present on the page");
            System.out.println(elemName + " not present on the page");
            return false;

        }

    }

    /**
     * public static String retrieveText(FluentWebElement locator,String elemName) method
     * specification :-
     *
     * 1) Return retrieved text from webpage 2)
     * locator.getText() -> Retrieves text from the web
     * element targeted by specified locator
     *
     * @param : Locator for the web element, Name of the web element
     * @return : Text retrieved from the webpage
     */

    public static String retrieveText(FluentWebElement locator, String elemName) {

        String retrievedText = null;

        APPLICATION_LOGS.debug("Retrieving Text from : " + elemName);
        System.out.println("Retrieving Text from : " + elemName);

        try {
            // Wait for web element to load on the page
            waitForElementToBeClickable(locator, elemName);

            // Highlight the web element
            highlightElement(locator);

            if(locator.isEnabled() && locator.isDisplayed()){
                // Retrieve text from web element
                retrievedText = locator.getText().trim();
            }
            else {
                ThreadWait();
                retrievedText = locator.getText().trim();
            }

            // Log result
            APPLICATION_LOGS.debug("Retrieved text : " + retrievedText);
            System.out.println("Retrieved text : " + retrievedText);

        }
        catch (StaleElementReferenceException getTextStaleException) {

            System.err.println("Stale Element Exception is handled for '" + elemName
                    + "' : " + getTextStaleException.getMessage());
            ThreadWait();

            // Wait for link to appear on the page
            waitForElementToLoad(locator, elemName);

            // Click on the link
            retrievedText = locator.getText().trim();

            // Log result
            System.out.println("Retrying...Clicked on : " + elemName);
            APPLICATION_LOGS.debug("Retrying...Clicked on : " + elemName);

        }
        catch (Throwable retrieveTextException) {

            // Log error
            System.err.println("Error while Getting Text from '" + elemName
                    + "' : " + retrieveTextException.getMessage());
            APPLICATION_LOGS.debug("Error while Getting Text from '" + elemName
                    + "' : " + retrieveTextException.getMessage());

        }
        return retrievedText;
    }

    /**
     * public static String retrieveText(FluentWebElement locator,String elemName) method
     * specification :-
     *
     * 1) Return retrieved text from webpage 2)
     * locator.getText() -> Retrieves text from the web
     * element targeted by specified locator
     *
     * @param : Locator for the web element, Name of the web element
     * @return : Text retrieved from the webpage
     */

    public static String retrieveText(WebElement locator, String elemName) {

        String retrievedText = null;

        APPLICATION_LOGS.debug("Retrieving Text from : " + elemName);
        System.out.println("Retrieving Text from : " + elemName);

        try {

            // Wait for web element to load on the page
            waitForElementToBeClickable(locator, elemName);

            if(locator.isEnabled() && locator.isDisplayed()){
                // Retrieve text from web element
                retrievedText = locator.getText().trim();
            }
            else {
                ThreadWait();
                retrievedText = locator.getText().trim();
            }


            // Log result
            APPLICATION_LOGS.debug("Retrieved text : " + retrievedText);
            System.out.println("Retrieved text : " + retrievedText);

        }

        catch (Throwable retrieveTextException) {

            // Log error
            System.err.println("Error while Getting Text from '" + elemName
                    + "' : " + retrieveTextException.getMessage());
            APPLICATION_LOGS.debug("Error while Getting Text from '" + elemName
                    + "' : " + retrieveTextException.getMessage());

        }
        return retrievedText;
    }

    /**
     * public static String retrieveTexts(FluentList<FluentWebElement> locator,String elemName) method
     * specification :-
     *
     * 1) Return retrieved text from webpage 2)
     * locator.getText() -> Retrieves text from the web
     * element targeted by specified locator
     *
     * @param : Locator for the web element, Name of the web element
     * @return : Text retrieved from the webpage
     */

    public static List<String> retrieveTexts(FluentList<FluentWebElement> locator, String elemName) {

        List<String> retrievedText = null;

        APPLICATION_LOGS.debug("Retrieving Texts from : " + elemName);
        System.out.println("Retrieving Texts from : " + elemName);

        try {

            // Wait for web element to load on the page
            waitForElementToLoad(locator.get(0), elemName);

            // Retrieve text from web element
            retrievedText = locator.getTexts();

            // Log result
            APPLICATION_LOGS.debug("Retrieved texts : " + retrievedText);
            System.out.println("Retrieved texts : " + retrievedText);

        }catch (StaleElementReferenceException retrieveTextStaleException) {

            System.err.println("Stale Element Exception is handled while retrieving texts for '" + elemName
                    + "' : " + retrieveTextStaleException.getMessage());

            ThreadWait();

            // Wait for link to appear on the page
            waitForElementToLoad(locator.get(0), elemName);

            // Retrieve text from web element
            retrievedText = locator.getTexts();

            // Log result
            System.out.println("Retrying...Retrieved text from : " + elemName);
            APPLICATION_LOGS.debug("Retrying...Retrieved text from  : " + elemName);

        }
        catch (Throwable retrieveTextException) {

            // Log error
            System.err.println("Error while Getting Text from '" + elemName
                    + "' : " + retrieveTextException.getMessage());
            APPLICATION_LOGS.debug("Error while Getting Text from '" + elemName
                    + "' : " + retrieveTextException.getMessage());

        }
        return retrievedText;
    }



    /**
     * public static String retrieveAttributeValue(FluentWebElement locator,String
     * value,String elemName) method specification :-
     *
     * 1) Return retrieved HTML attribute value from webpage 2)
     * locator.getAttribute(value) -> Retrieves attribute
     * (present under a web element) value
     *
     * @param : Locator for the web element, Attribute name, Name of the web
     *        element
     * @return : Attribute value retrieved
     */

    public static String retrieveAttributeValue(FluentWebElement locator, String value,
                                                String elemName) {

        String attributeValue = null;

        APPLICATION_LOGS.debug("Getting Attribute '" + value
                + "'  Value from : " + elemName);
        System.out.println("Getting Attribute '" + value + "'  Value from : "
                + elemName);

        try {

            // Wait for web element to load
            waitForElementToBeClickable(locator, elemName);

            // Get attribute value for the web element
            attributeValue = locator.getAttribute(value);

            // Log result
            APPLICATION_LOGS.debug("Got Attribute '" + value
                    + "'  Value from : " + elemName);
            System.out.println("Got Attribute '" + value + "'  Value from : "
                    + elemName);

        }

        catch (Throwable retrieveAttributeValueException) {

            // report error
            System.err.println("Error while Getting Attribute '" + value
                    + "' value from '" + elemName + "' : "
                    + retrieveAttributeValueException.getMessage());

            APPLICATION_LOGS.debug("Error while Getting Attribute '" + value
                    + "' value from '" + elemName + "' : "
                    + retrieveAttributeValueException.getMessage());

            return "Fail : Error while Getting Attribute '" + value
                    + "' value from '" + elemName + "' : "
                    + retrieveAttributeValueException.getMessage();

        }

        return attributeValue;

    }




    /**
     * public static void waitForNewWindow(int prevWndCount) method
     * specification :-
     *
     * 1) Waits for a new window to appear 2) new WebDriverWait(BrowserInitializer.getDriver(), 60) ->
     * Waits for 60 seconds 3) wait.until((ExpectedCondition<Boolean>) -> Wait
     * until expected condition (Window count increases) met 4)
     * d.getWindowHandles().size() -> Returns number of window handles present
     *
     * @param : Previous window count
     * @return : void
     */

    public static void waitForNewWindow(int prevWndCount) {

        final int  currWndCount = prevWndCount;

        try {

            // Waits for 60 seconds
            WebDriverWait wait = new WebDriverWait(BrowserInitializer.getDriver(), 60);

            // Wait until expected condition (Window count increases) met
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver d) {

                    // Return true if window count increases, else return false
                    return d.getWindowHandles().size() > currWndCount;

                }

            });

        }

        catch (Throwable waitForNewWindowException) {

            System.err
                    .println("Exception while waiting for new window to appear : "
                            + waitForNewWindowException.getMessage());

            APPLICATION_LOGS
                    .debug("Exception while waiting for new window to appear : "
                            + waitForNewWindowException.getMessage());

        }

    }

    /**
     * public static void waitForPageToLoad() method specification :-
     *
     * 1) Waits for a new page to load completely 2) new WebDriverWait(BrowserInitializer.getDriver(),
     * 60) -> Waits for 60 seconds 3) wait.until((ExpectedCondition<Boolean>) ->
     * Wait until expected condition (All documents present on the page get
     * ready) met
     *
     * @param : no parameters passed
     * @return : void
     */

    public static void waitForPageToLoad() throws InterruptedException {

        try {

            // Waits for 60 seconds
            WebDriverWait wait = new WebDriverWait(BrowserInitializer.getDriver(), 60);
            Thread.sleep(2000);
            // Wait until expected condition (All documents present on the page
            // get ready) met
            wait.until((ExpectedCondition<Boolean>) new ExpectedCondition<Boolean>() {

                public Boolean apply(WebDriver d) {

                    if (!(d instanceof JavascriptExecutor))
                        return true;

                    Object result = ((JavascriptExecutor) d)
                            .executeScript("return document['readyState'] ? 'complete' == document.readyState : true");

                    if (result != null && result instanceof Boolean
                            && (Boolean) result)
                        return true;

                    return false;

                }

            });

        }

        catch (Throwable waitForPageToLoadException) {

            System.err.println("Error came while waiting for page to load : "
                    + waitForPageToLoadException.getMessage());
            APPLICATION_LOGS
                    .debug("Error came while waiting for page to load : "
                            + waitForPageToLoadException.getMessage());

        }

    }

    /**
     * public static void waitForElementToLoad(FluentWebElement locator) method specification
     * :-
     *
     * 1) Waits for the web element to appear on the page 2) new
     * WebDriverWait(BrowserInitializer.getDriver(), 60) -> Waits for 60 seconds 3)
     * wait.until((ExpectedCondition<Boolean>) -> Wait until expected condition
     * (All documents present on the page get ready) met
     *
     * @param : no parameters passed
     * @return : void
     */

    public static void waitForElementToLoad(final FluentWebElement locator, String eleName) {

        APPLICATION_LOGS.debug("Waiting for "+eleName+" to load on the page");
        System.out.println("Waiting for "+eleName+" to load on the page");

        try {
            if(locator.isEnabled()){
                // Waits for 60 seconds
                Wait<WebDriver> wait = new WebDriverWait(BrowserInitializer.getDriver(), 60);
                // Wait until the element is located on the page
                @SuppressWarnings("unused")

                FluentWebElement element = wait
                        .until(visibilityOfElementLocated(locator));
            }

            // Log result
            APPLICATION_LOGS
                    .debug("Waiting ends ... "+eleName +" loaded on the page");
            System.out
                    .println("Waiting ends ... "+eleName +" loaded on the page");

        }
        catch (StaleElementReferenceException staleException) {
            System.out.println("Caught Stale element exception for " +eleName + "+...Retrying...");
            Wait<WebDriver> wait = new WebDriverWait(BrowserInitializer.getDriver(), 30);
            wait.until(visibilityOfElementLocated(locator));
        }
        catch (Throwable waitForElementException) {

            // Log error
            APPLICATION_LOGS
                    .debug("Error came while waiting for "+eleName + " to appear : "
                            + waitForElementException.getMessage());
            System.err
                    .println("Error came while waiting for " +eleName + " to appear : "
                            + waitForElementException.getMessage());

        }

    }

    /**
     * public static void waitForElementToLoad(FluentWebElement locator) method specification
     * :-
     *
     * 1) Waits for the web element to appear on the page 2) new
     * WebDriverWait(BrowserInitializer.getDriver(), 60) -> Waits for 60 seconds 3)
     * wait.until((ExpectedCondition<Boolean>) -> Wait until expected condition
     * (All documents present on the page get ready) met
     *
     * @param : no parameters passed
     * @return : void
     */

    public static void waitForElementToLoad(final WebElement locator, String eleName) {

        APPLICATION_LOGS.debug("Waiting for "+eleName+" to load on the page");
        System.out.println("Waiting for "+eleName+" to load on the page");

        try {
            if(locator.isEnabled()){
                // Waits for 60 seconds
                Wait<WebDriver> wait = new WebDriverWait(BrowserInitializer.getDriver(), 60);
                // Wait until the element is located on the page
                @SuppressWarnings("unused")

                WebElement element = wait
                        .until(visibilityOfElementLocated(locator));
            }

            // Log result
            APPLICATION_LOGS
                    .debug("Waiting ends ... "+eleName +" loaded on the page");
            System.out
                    .println("Waiting ends ... "+eleName +" loaded on the page");

        }
        catch (StaleElementReferenceException staleException) {
            System.out.println("Caught Stale element exception for " +eleName + "+...Retrying...");
            Wait<WebDriver> wait = new WebDriverWait(BrowserInitializer.getDriver(), 30);
            wait.until(visibilityOfElementLocated(locator));
        }
        catch (Throwable waitForElementException) {

            // Log error
            APPLICATION_LOGS
                    .debug("Error came while waiting for "+eleName + " to appear : "
                            + waitForElementException.getMessage());
            System.err
                    .println("Error came while waiting for " +eleName + " to appear : "
                            + waitForElementException.getMessage());

        }

    }

    /**
     * public static ExpectedCondition<FluentWebElement>
     * visibilityOfElementLocated(final FluentWebElement locator) method specification :-
     *
     * 1) Waits for the web element to appear on the page 2) FluentWebElement
     * toReturn.isDisplayed() -> Returns true if displayed on the page, else
     * returns false
     *
     * @param : Locator to locate the web element
     * @return : ExpectedCondition about the web element
     */

    public static ExpectedCondition<FluentWebElement> visibilityOfElementLocated(final FluentWebElement locator) {

        return new ExpectedCondition<FluentWebElement>() {

            public FluentWebElement apply(WebDriver driver) {

                // Store the web element
                FluentWebElement toReturn = locator;

                // Check whether the web element is displayed on the page
                if (toReturn.isDisplayed() && toReturn.isEnabled())
                    return toReturn;

                return null;

            }

        };

    }

    /**
     * public static ExpectedCondition<FluentWebElement>
     * visibilityOfElementLocated(final FluentWebElement locator) method specification :-
     *
     * 1) Waits for the web element to appear on the page 2) FluentWebElement
     * toReturn.isDisplayed() -> Returns true if displayed on the page, else
     * returns false
     *
     * @param : Locator to locate the web element
     * @return : ExpectedCondition about the web element
     */

    public static ExpectedCondition<WebElement> visibilityOfElementLocated(final WebElement locator) {

        return new ExpectedCondition<WebElement>() {

            public WebElement apply(WebDriver driver) {

                // Store the web element
                WebElement toReturn = locator;

                // Check whether the web element is displayed on the page
                if (toReturn.isDisplayed() && toReturn.isEnabled())
                    return toReturn;

                return null;

            }

        };

    }

    /**
     * public static boolean verifyNumber(String elemName,int actValue, int
     * expValue) method specification :-
     *
     * 1) Verify number on the page 2)
     *
     * @param : Name of the web element, Actual value in integer, Expected value
     *        in integer
     * @return : Boolean - true if verified successfully else false
     */

    public static boolean verifyNumber(String elemName, int actValue,
                                       int expValue) {

        APPLICATION_LOGS.debug("Verifying Number for : " + elemName);
        System.out.println("Verifying Number for : " + elemName);

        try {

            // Assert whether actual value matches with expected
            Assert.assertEquals(expValue, actValue);

            // Log result
            APPLICATION_LOGS.debug("Actual value '" + actValue
                    + "' matches with Expected value '" + expValue + "' for : "
                    + elemName);
            System.out.println("Actual value '" + actValue
                    + "' matches with Expected value '" + expValue + "' for : "
                    + elemName);

            return true;

        }

        catch (Throwable verifyNumberException) {

            // Log error
            System.err.println("Error while Verifying Number for '" + elemName
                    + "' : " + verifyNumberException.getMessage());

            APPLICATION_LOGS.debug("Error while Verifying Number for '"
                    + elemName + "' : " + verifyNumberException.getMessage());

            return false;

        }

    }

    /**
     * public static String acceptAlert(String elemName) method specification :-
     *
     * 1) Accepts an alert 2) BrowserInitializer.getDriver().switchTo().alert() -> Switch to the desired
     * alert 3) alert.accept() -> Accepts the alert
     *
     * @param : Name of the web element
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String acceptAlert(String elemName) {

        APPLICATION_LOGS.debug("Accepting alert : " + elemName);
        System.out.println("Accepting alert : " + elemName);

        try {

            // Create a new alert object
            Alert alert = BrowserInitializer.getDriver().switchTo().alert();

            // Accept the alert
            alert.accept();

            // Log result
            System.out.println("Accepted alert : " + elemName);
            APPLICATION_LOGS.debug("Accepted alert : " + elemName);

            return "Pass : Accepted the alert '" + elemName + "'";

        }

        catch (Throwable acceptAlertException) {

            // Log error
            System.err.println("Error came while accepting alert : "
                    + acceptAlertException.getMessage());
            APPLICATION_LOGS.debug("Error came while accepting alert : "
                    + acceptAlertException.getMessage());

            return "Fail : Error came while accepting alert : "
                    + acceptAlertException.getMessage();

        }

    }


    /**
     * public static String assertTitle(String expectedTitle) method
     * specification :-
     *
     * 1) Asserts page title 2) BrowserInitializer.getDriver().getTitle() -> Retrieves page title 3)
     * Assert.assertEquals() -> Asserts for equality
     *
     * @param : Expected title to assert
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String assertTitle(String expectedTitle) {

        String actualTitle = null;

        APPLICATION_LOGS.debug("Asserting  title  where : Expected title = "
                + expectedTitle);
        System.out.println("Asserting  title  where : Expected title = "
                + expectedTitle);

        try {

            // Fetch actual title of the webpage
            actualTitle = BrowserInitializer.getDriver().getTitle();

            // Asserts whether actual title matches with expected one
            Assert.assertEquals(expectedTitle.trim(), actualTitle.trim());

            // Log result
            APPLICATION_LOGS.debug("Actual title = " + actualTitle
                    + " and matches with Expected title = " + expectedTitle);

            System.out.println("Actual title = " + actualTitle
                    + " and matches with Expected title = " + expectedTitle);

            return "Pass : Actual title = " + actualTitle
                    + " and matches with Expected title = " + expectedTitle;

        }

        catch (Throwable assertTitleException) {

            // Log error
            System.err.println("Error while asserting title : "
                    + assertTitleException.getMessage());

            APPLICATION_LOGS.debug("Error while asserting title : "
                    + assertTitleException.getMessage());

            return "Fail : Error while asserting title : "
                    + assertTitleException.getMessage();

        }

    }

    /**
     * public static String assertAlertAndAccept(String expectedAlertText)
     * method specification :-
     *
     * 1) Assert alert text and accept 2) Alert alert =
     * BrowserInitializer.getDriver().switchTo().alert() -> Switch to the alert appeared on the page 3)
     * Assert.assertEquals() -> Asserts for equality 4) alert.accept() ->
     * Accepts the alert
     *
     * @param : Expected alert text to assert
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static String assertAlertAndAccept(String expectedAlertText) {

        APPLICATION_LOGS.debug("Asserting alert text : " + expectedAlertText);
        System.out.println("Asserting alert text : " + expectedAlertText);

        String actualAlertText = null;
        Alert alert = null;

        try {

            // Switch control to alert
            alert = BrowserInitializer.getDriver().switchTo().alert();

            // Get the actual alert message
            actualAlertText = alert.getText();

            // Assert alert message
            Assert.assertEquals(expectedAlertText.trim(),
                    actualAlertText.trim());
            Thread.sleep(3000L);

            // Accept alert message
            alert.accept();
            Thread.sleep(3000L);

            // log result
            APPLICATION_LOGS.debug("Success : got the alert message saying : "
                    + actualAlertText);
            System.out.println("Success : got the alert message saying : "
                    + actualAlertText);

            return "Pass : got the alert message saying : '" + actualAlertText;

        }

        catch (Throwable alertExcpetion) {

            APPLICATION_LOGS
                    .debug("Error came while asserting alert and accepting : "
                            + alertExcpetion.getMessage());
            System.err
                    .println("Error came while asserting alert and accepting : "
                            + alertExcpetion.getMessage());
            return "Fail : Error came while asserting alert and accepting : "
                    + alertExcpetion.getMessage();

        }

    }

    /**
     * public static Boolean isEnabled(By Locator, String elemName) method
     * specification
     *
     * @param : (Locator) locator of the element
     * @param : (elemName) name of the element
     * @return : True/false based on whether element is enabled on the page or
     *         not
     */

    public static Boolean isEnabled(FluentWebElement Locator, String elemName) {
        APPLICATION_LOGS.debug("Is Element enabled : " + elemName);
        System.out.println("Is Element enabled : " + elemName);
        try {
            waitForElementToLoad(Locator, elemName);
            Boolean present = Locator.isEnabled();
            return present;
        } catch (Throwable e) {
            System.out.println("Error while verifying Is Element Enabled :   -"
                    + elemName);
            APPLICATION_LOGS
                    .debug("Error while verifying Is Element Enabled  :   -"
                            + elemName + e.getMessage());
            return false;
        }

    }

    /**
     * public static void highlightElement(WebDriver BrowserInitializer.getDriver(), By Locator) method
     * specification
     *
     * This method is used to highlight the element of choice
     *
     * @param : BrowserInitializer.getDriver()
     * @param : (Locator) locator of the element to highlight
     */
    public static void highlightElement(FluentWebElement Locator) {

        try {

                for (int i = 0; i < 3; i++) {
                    JavascriptExecutor js = (JavascriptExecutor) BrowserInitializer.getDriver();
                    // js.executeScript("arguments[0].setAttribute('style', arguments[1]);",BrowserInitializer.getDriver().findElement(Locator),
                    // "color: red; border: 2px solid red;");
                    js.executeScript(
                            "arguments[0].setAttribute('style', arguments[1]);",
                            Locator.getElement(),
                            "background-color: yellow; outline: 1px solid rgb(136, 255, 136);");

                }
        }
        catch (Throwable t) {
            System.out.println("Error came : " + t.getMessage());
            APPLICATION_LOGS.debug("Error came : " + t.getMessage());
        }

    }

    /**
     * public static void checkCheckBox(FluentWebElement locator, String elemName) method
     * specification :
     *
     * 1) Checks a check-box if it is not checked already 2) if
     * (!locator.isSelected()) {
     * locator.click() : Checks the checkbox if it is not
     * checked already 3) String elemName : Passed as a parameter to name the
     * element
     */
    public static void checkCheckBox(FluentWebElement locator, String elemName) {

        APPLICATION_LOGS.debug("Clicking on: " + elemName);
        System.out.println("Clicking on: " + elemName);

        try {

            waitForElementToBeClickable(locator, elemName);

            if (!locator.isSelected()) {
                locator.click();
            }

        }

        catch (Throwable t) {

            System.out.println("Error while clicking on link -" + elemName);
            APPLICATION_LOGS.debug("Error while clicking on link -" + elemName
                    + t.getMessage());
        }

    }

    /**
     * public static Boolean verifyPartialText(String elemName, String
     * expValue,String actValue) method specification :
     *
     * This method is for verifying presence of a sub-string in between a larger
     * string
     *
     * @param elemName
     *            : Passed as a parameter for naming the element
     * @param expValue
     *            : Passed as a parameter for storing the expected value
     * @param actValue
     *            : Passed as a parameter for storing the actual value 5)
     *
     *            Boolean check = actValue.trim().contains(expValue.trim()) :
     *            Checks if actual text contains the expected text
     *
     * @return : True/false based on whether text is partially matching or not
     */

    public static Boolean verifyPartialText(String elemName, String expValue,
                                            String actValue) {

        APPLICATION_LOGS.debug("Verifying Partial Text - '" + expValue
                + "' for : " + elemName);
        System.out.println("Verifying Partial Text - '" + expValue + "' for : "
                + elemName);

        try {

            Boolean check = actValue.trim().contains(expValue.trim());

            if (check) {

                APPLICATION_LOGS.debug("Success : Partial text - '" + expValue
                        + "' is present within Full text - '" + actValue + "'");
                System.out.println("Success : Partial text - '" + expValue
                        + "' is present within Full text - '" + actValue + "'");

                return true;

            }

            else {

                APPLICATION_LOGS.debug("Partial text - '" + expValue
                        + "' is not present within Full text - '" + actValue
                        + "'");
                System.err.println("Partial text - '" + expValue
                        + "' is not present within Full text - '" + actValue
                        + "'");

                return false;

            }

        }

        catch (Throwable verifyPartialTextException) {

            System.err.println("Error while Verifying Partial Text for - "
                    + elemName + " : "
                    + verifyPartialTextException.getMessage());
            APPLICATION_LOGS.debug("Error while Verifying Partial Text for : "
                    + elemName + " : "
                    + verifyPartialTextException.getMessage());

            return false;

        }

    }

    /**
     * public static Boolean assertPartialTitle(String expectedTitle) method
     * specification :
     *
     * This method is for asserting partial text
     *
     * @param : (elemName) : Passed as a parameter for naming the element
     * @return : Result of execution whether the title partially matches or not.
     */

    public static Boolean assertPartialTitle(String expectedTitle) {

        String actualTitle = null;

        APPLICATION_LOGS
                .debug("Asserting partial title  where : Expected title = "
                        + expectedTitle);
        System.out
                .println("Asserting  partial title  where : Expected title = "
                        + expectedTitle);

        try {

            // Fetch actual title of the webpage
            actualTitle = BrowserInitializer.getDriver().getTitle();

            // Asserts whether actual title matches with expected one
            Boolean check = actualTitle.trim().contains(expectedTitle.trim());

            if (check) {

                // Log result
                APPLICATION_LOGS.debug("Actual title = " + actualTitle
                        + "  partially matches with Expected title = "
                        + expectedTitle);

                System.out.println("Actual title = " + actualTitle
                        + "  partially matches with Expected title = "
                        + expectedTitle);

                return true;
            }

            else {

                // Log result
                APPLICATION_LOGS.debug("Actual title = " + actualTitle
                        + "  not matching with Expected title = "
                        + expectedTitle);

                System.out.println("Actual title = " + actualTitle
                        + " not matching with Expected title = "
                        + expectedTitle);

                return false;
            }

        }

        catch (Throwable assertPartialTitleException) {

            // Log error
            System.err.println("Error while asserting title : "
                    + assertPartialTitleException.getMessage());

            APPLICATION_LOGS.debug("Error while asserting title : "
                    + assertPartialTitleException.getMessage());

            return false;

        }
    }

    /**
     * public static void inputInteger(FluentWebElement locator, String elemName, int Value)
     * method specification
     *
     * This method converts integer to string and inputs to text box
     *
     * @param locator
     *            : locator of the textbox
     * @param elemName
     *            : A parameter passed to take the element name
     * @param Value
     *            : Another parameter passed to take an integer value and input
     *            it
     */
    public static void inputInteger(FluentWebElement locator, String elemName, int Value) {
        APPLICATION_LOGS.debug("Sending Values in: " + elemName);
        System.out.println("Sending Values in: " + elemName);
        try {
            waitForElementToBeClickable(locator, elemName);
            String Value1 = Integer.toString(Value);
            locator.getElement().sendKeys(Value1);
        } catch (Throwable t) {
            System.out.println("Error while Sending Values in:  -" + elemName);
            APPLICATION_LOGS.debug("Error while Sending Values in:  -"
                    + elemName + t.getMessage());
        }
    }

    /**
     * public static String getDateAndTimeOfSpecificTimeZone( String
     * dateAndTimeFormat, String timeZone) method specification :
     *
     * 1) This function gets date and time of a specific time zone 2) String
     * dateAndTimeFormat : A parameter passed in the function which takes the
     * Date/Time format we want 3) String timeZone : Another parameter passed
     * mentioning the timezone for which we want the date/time 4) Date date =
     * new Date() : Locale date and time 5)
     * formatter.setTimeZone(TimeZone.getTimeZone(timeZone)) : Get US/Eastern
     * time 6) return dateAndTime : Prints the date in the US timezone
     */

    public static String getDateAndTimeOfSpecificTimeZone(
            String dateAndTimeFormat, String timeZone) {

        APPLICATION_LOGS
                .debug("Executing : getDateAndTimeOfSpecificTimeZone() method");

        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat(dateAndTimeFormat);
        formatter.setTimeZone(TimeZone.getTimeZone(timeZone));
        String dateAndTime = formatter.format(date);

        APPLICATION_LOGS
                .debug("Got date and time specific to timezone. Timezone = "
                        + timeZone + " and DateTime = " + dateAndTime);
        System.out
                .println("Got date and time specific to timezone. Timezone = "
                        + timeZone + " and DateTime = " + dateAndTime);
        return dateAndTime;

    }


    /**
     * This method returns time in Pacific timezone in M/d/yyyy format.
     * @param dateOffSet : This int parameter is added or subtracted from the current date. If current date is required
     * this parameter should be 0.
     * @param timeZone
     * @return Returns time in string format.
     */
    public static String getDateOfPacificTimezone(int dateOffSet, String dateAndTimeFormat, String timeZone) {
        Date date = new Date();
        DateFormat df = new SimpleDateFormat(dateAndTimeFormat);
        Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone(timeZone));
        calendar.add(Calendar.DATE, dateOffSet);
        String dateAndTime = df.format(date);
        APPLICATION_LOGS
                .debug("Got date and time specific to timezone. Timezone = "
                        + timeZone + " and DateTime = " + dateAndTime);
        System.out
                .println("Got date and time specific to timezone. Timezone = "
                        + timeZone + " and DateTime = " + dateAndTime);
        return df.format(calendar.getTime());

    }

    /**
     * public static void refreshPage() method specification : -
     *
     * 1) Refresh the page 2) BrowserInitializer.getDriver().navigate().refresh : This is used to
     * refresh the current page
     */
    public static void refreshPage() {

        APPLICATION_LOGS.debug("Executing : refreshPage() method");

        try {

            APPLICATION_LOGS.debug("Refreshing page");
            System.out.println("Refreshing page");
            BrowserInitializer.getDriver().navigate().refresh();
            APPLICATION_LOGS.debug("Page successfully refreshed");
            System.out.println("Page successfully refreshed");
        } catch (Throwable pageRefreshException) {

            APPLICATION_LOGS.debug("Exception came while refreshing page : "
                    + pageRefreshException.getMessage());
            System.err.println("Exception came while refreshing page : "
                    + pageRefreshException.getMessage());
        }
    }

    /**
     * public static void selectValue(By Locator,String Value,String elemName)
     * method specification : -
     *
     * Selects value from drop down using value
     *
     * @param(Locator) : drop down locator
     * @param(Value) : value to be selected
     * @param(elemName) : drop down name
     */
    public static void selectValue(FluentWebElement Locator, String Value, String elemName) {

        APPLICATION_LOGS.debug("Selecting Value from : " + elemName);
        System.out.println("Selecting Value from : " + elemName);

        try {

            waitForElementToBeClickable(Locator, elemName);
            highlightElement(Locator);
            Select select = new Select(Locator.getElement());
            select.selectByVisibleText(Value);
        }

        catch (Exception e) {

            System.out.println("Error while Selecting Value from :   -"
                    + elemName);
            APPLICATION_LOGS.debug("Error while Selecting Value from :   -"
                    + elemName + e.getMessage());

        }

    }

    /**
     * public static String assertText(String expectedString,String
     * actualString) method specification
     *
     * @param(expectedString) : String to be asserted against.
     * @param(actualString) : Actual string to be asserted.
     *
     * @return Result of execution - Pass or fail (with cause)
     */

    public static String assertText(String expectedString, String actualString) {

        APPLICATION_LOGS.debug("Asserting  Text  where : ExpectedText = "
                + expectedString + "  and ActualText = " + actualString);
        System.out.println("Asserting  Text  where : ExpectedText = "
                + expectedString + "  and ActualText = " + actualString);

        try {

            Assert.assertEquals(expectedString.trim(), actualString.trim());

            APPLICATION_LOGS.debug("Success : ExpectedText = " + expectedString
                    + "  and ActualText = " + actualString
                    + " and both are same");
            System.out.println("Success : ExpectedText = " + expectedString
                    + "  and ActualText = " + actualString
                    + " and both are same");

        }

        catch (Throwable t) {

            // report error
            System.out.println("Error while asserting text :- "
                    + t.getMessage());
            APPLICATION_LOGS.debug("Error while asserting text :- "
                    + t.getMessage());
            return "Fail : Error while asserting text :- " + t.getMessage();

        }

        return "Pass";

    }

    /**
     * Generate random numbers
     *
     * @param : none
     * @return : Random number generated
     */
    public static String randomnGenerator() {

        int PASSWORD_LENGTH = 8;
        String randomnCharacters;
        StringBuffer sb = new StringBuffer();

        for (int x = 0; x < PASSWORD_LENGTH; x++) {

            sb.append((char) ((int) (Math.random() * 26) + 97));

        }

        randomnCharacters = sb.toString();
        return randomnCharacters;

    }

    /**
     * Generate random numbers within the range provided inside the method
     * argument
     *
     * @param : Min Value in the range, Max Value in the range
     * @return : Random number generated
     */

    public static String randomnNumberGenerator(int minValue, int maxValue) {

        int randomnNumber;
        String numbers;
        randomnNumber = (int) (minValue + ((new Random()).nextDouble() * (maxValue - minValue)));
        numbers = Integer.toString(randomnNumber);
        return numbers;

    }

    /**
     * public static void isChecked(FluentWebElement locator, String elemName) method
     * specification :-
     *
     * 1) Verifies whether a Checkbox is checked or not 2) locator -> to locate
     * the element by id,x-path,name,etc. 3) elemName -> the name/type of the
     * check-box which we intend to check 4)
     * locator.isSelected() -> is to verify whether the
     * intended checkbox is checked or not
     *
     * @param : Locator for the Check-box, name of the web element
     * @return : Result of execution - Pass or fail (with cause)
     */

    public static Boolean isChecked(FluentWebElement locator, String elemName) {

        APPLICATION_LOGS.debug("Verifying is the checkbox checked : "
                + elemName);
        System.out.println("Verifying is the checkbox checked : " + elemName);

        Boolean result = null;

        try {

            // Wait for check-box to appear on the page
            waitForElementToBeClickable(locator, elemName);

            // Verify whether check-box if already checked
            if (locator.isSelected()) {

                // Log the result
                System.out.println("Is checked '" + elemName + "'");
                APPLICATION_LOGS.debug("Is checked '" + elemName + "'");
                result = true;
            } else {

                // Log the result
                System.out.println("Is not checked '" + elemName + "'");
                APPLICATION_LOGS.debug("Is not checked '" + elemName + "'");
                result = false;
            }

        }

        catch (Throwable ischeckCheckBoxException) {

            // Log the exception
            System.err
                    .println("Error while verifying checkbox is checked '"
                            + elemName + "' : "
                            + ischeckCheckBoxException.getMessage());
            APPLICATION_LOGS
                    .debug("Error while verifying checkbox is checked '"
                            + elemName + "' : "
                            + ischeckCheckBoxException.getMessage());

            result = false;

        }
        System.out.println("Result: " + result);
        return result;

    }


    /**
     * public static String waitForElementToBeClickable(FluentWebElement locator, String
     * elemName) method specification
     *
     * 1) This method waits explicitly wait until a given element is clickable
     * 2) String elemName : A parameter passed to take the element name 3) By
     * locator : To locate the element by id, xpath etc..
     *
     * @return : Result of execution either Element is present or not.
     */
    public static void waitForElementToBeClickable(FluentWebElement locator, String elemName) {

        String result = null;
        WebDriverWait wait = new WebDriverWait(BrowserInitializer.getDriver(), 15);
        // FluentWebElement element;
        try {
            APPLICATION_LOGS.debug("waiting for " + elemName + "to appear");
            System.out.println("waiting for " + elemName + "to appear");

            if(locator.isEnabled())
                wait.until(ExpectedConditions.elementToBeClickable(locator.getElement()));

            APPLICATION_LOGS.debug("waited for " + elemName + "to appear");
            System.out.println("waited for " + elemName + "to appear");

        } catch (Throwable ElementNotFoundException) {

            APPLICATION_LOGS
                    .debug("Exception came while waiting for element to load");
            System.err
                    .println("Exception came while waiting for element to load");


        }
    }

    /**
     * public static String waitForTextToBePresent(FluentWebElement locator, String
     * elemName, String text) method specification
     *
     * 1) This method waits explicitly wait until a given text is present in the element
     * 2) String elemName : A parameter passed to take the element name 3) By
     * locator : To locate the element by id, xpath etc..
     *
     * @return : Result of execution either test is present or not.
     */
    public static void waitForTextToBePresent(FluentWebElement locator, String elemName, String text) {


        String result = null;
        WebDriverWait wait = new WebDriverWait(BrowserInitializer.getDriver(), 40);
        // FluentWebElement element;
        try {
            APPLICATION_LOGS.debug("waiting for " + elemName + "to appear");
            System.out.println("waiting for " + elemName + "to appear");

            if(locator.isEnabled())
                wait.until(ExpectedConditions.textToBePresentInElement(locator.getElement(), text));

            APPLICATION_LOGS.debug("waited for " + elemName + "to appear");
            System.out.println("waited for " + elemName + "to appear");

        } catch (Throwable ElementNotFoundException) {

            APPLICATION_LOGS
                    .debug("Exception came while waiting for element to load");
            System.err
                    .println("Exception came while waiting for element to load");


        }
    }

    /**
     * public static String waitForElementToBeClickable(FluentWebElement locator, String
     * elemName) method specification
     *
     * 1) This method waits explicitly wait until a given element is clickable
     * 2) String elemName : A parameter passed to take the element name 3) By
     * locator : To locate the element by id, xpath etc..
     *
     * @return : Result of execution either Element is present or not.
     */
    public static void waitForElementToBeClickable(WebElement locator, String elemName) {
        WebDriverWait wait = new WebDriverWait(BrowserInitializer.getDriver(), 40);
        try {
            APPLICATION_LOGS.debug("waiting for " + elemName + "to appear");
            System.out.println("waiting for " + elemName + "to appear");

            if(locator.isEnabled())
                wait.until(ExpectedConditions.elementToBeClickable(locator));

            APPLICATION_LOGS.debug("waited for " + elemName + "to appear");
            System.out.println("waited for " + elemName + "to appear");

        } catch (Throwable ElementNotFoundException) {

            APPLICATION_LOGS
                    .debug("Exception came while waiting for element to load");
            System.err
                    .println("Exception came while waiting for element to load");
        }
    }

    /**
     * public static String waitForElementToBeClickable(FluentWebElement locator, String
     * elemName) method specification
     *
     * 1) This method waits explicitly wait until a given element is clickable
     * 2) String elemName : A parameter passed to take the element name 3) By
     * locator : To locate the element by id, xpath etc..
     *
     * @return : Result of execution either Element is present or not.
     */
    public static void waitForLoaderToDissapear(By locator, String name) {


        String result = null;
        WebDriverWait wait = new WebDriverWait(BrowserInitializer.getDriver(), 180);
        // FluentWebElement element;
        try {
            APPLICATION_LOGS.debug("waiting for "+name+ " to disappear");
            System.out.println("waiting for "+name+ " to disappear");

            wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));

            APPLICATION_LOGS.debug("waited for "+name+ " to disappear");
            System.out.println("waited for"+name+ " to disappear");

        } catch (Throwable ElementNotFoundException) {

            APPLICATION_LOGS
                    .debug("Exception came while waiting for "+name+ " to load");
            System.err
                    .println("Exception came while waiting for "+name+ " to load");


        }
    }

    /**
     * public static String waitForElementToBeClickable(FluentWebElement locator, String
     * elemName) method specification
     *
     * 1) This method waits explicitly wait until a given element is clickable
     * 2) String elemName : A parameter passed to take the element name 3) By
     * locator : To locate the element by id, xpath etc..
     *
     * @return : Result of execution either Element is present or not.
     */
    public static void waitForSpinLoaderToDissapear() {


        String result = null;
        WebDriverWait wait = new WebDriverWait(BrowserInitializer.getDriver(), 60);
        // FluentWebElement element;
        try {
            APPLICATION_LOGS.debug("waiting for ajax loader to disappear");
            System.out.println("waiting for ajax loader to disappear");

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ajax-loader")));

            APPLICATION_LOGS.debug("waited for ajax loader to disappear");
            System.out.println("waited for ajax loader to disappear");

        } catch (Throwable ElementNotFoundException) {

            APPLICATION_LOGS
                    .debug("Exception came while waiting for ajax loader to load");
            System.err
                    .println("Exception came while waiting for ajax loader to load");


        }
    }

    public static void selectDropDownValueByName(String searchValue, FluentList<FluentWebElement> dropDownValues, String eleName){

        APPLICATION_LOGS.debug("Selecting '" + searchValue + "' from : " + dropDownValues.getTexts());
        System.out.println("Selecting '" + searchValue + "' from : " + dropDownValues.getTexts());

        try {
            for (FluentWebElement value : dropDownValues) {
                if (value.getText().trim().equalsIgnoreCase(searchValue)) {
                    value.click();
                    APPLICATION_LOGS.debug("Selected '" + searchValue + "' from : " + dropDownValues.getTexts());
                    System.out.println("Selected '" + searchValue + "' from : " + dropDownValues.getTexts());
                    break;
                }
            }
        }
        catch (Throwable selectValueException) {

            // Log error
            System.err.println("Error while Selecting Value from - '"
                    + searchValue + "' : " + selectValueException.getMessage());
            APPLICATION_LOGS.debug("Error while Selecting Value from - '"
                    + searchValue + "' : " + selectValueException.getMessage());


        }
    }

    public static void ThreadWait() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



}