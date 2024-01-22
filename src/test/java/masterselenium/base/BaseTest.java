package masterselenium.base;

import masterselenium.factory.DriverManager;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;

    @Parameters("browser")
    @BeforeTest
    public void startDriver(String browser)
    {
        //System.setProperty("webdriver.chrome.driver", "/Users/fahiezah/Desktop/2024Projects/SeleniumJavaMasterClass/src/test/java/utils/chromedriver");
        driver = new DriverManager().initializeDriver(browser);

    }
    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }
}
