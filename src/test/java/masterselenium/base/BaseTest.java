package masterselenium.base;

import masterselenium.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected WebDriver driver;

    @BeforeTest
    public void startDriver()
    {
        //System.setProperty("webdriver.chrome.driver", "/Users/fahiezah/Desktop/2024Projects/SeleniumJavaMasterClass/src/test/java/utils/chromedriver");
        driver = new DriverManager().initializeDriver();

    }
    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
