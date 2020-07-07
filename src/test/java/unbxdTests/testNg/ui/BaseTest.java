package unbxdTests.testNg.ui;

import lib.BrowserInitializer;
import org.fluentlenium.core.FluentAdapter;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class BaseTest extends FluentAdapter {

    public  WebDriver driver=null;

    public void setUp() {
        try {
            BrowserInitializer initializer = new BrowserInitializer();
            initializer.init();
            driver = initializer.getDriver();

        }
        catch(Exception e)
        {
            System.out.println("Browser Initialization failed with Exception "+e.getMessage());
        }
    }

}
