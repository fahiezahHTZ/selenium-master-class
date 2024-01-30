package masterselenium.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import masterselenium.constants.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Locale;


public class DriverManager {
    WebDriver driver;
    public WebDriver initializeDriver(String browser)  {


        //to run from Maven command line/JVM argument with System.get property)

        //DriverType.valueOf -> converted to Enum when receive browsername
        //localBrowser = brwser;
        switch (DriverType.valueOf(browser)){
            case CHROME:
                WebDriverManager.chromedriver().cachePath("Drivers").setup();
                driver = new ChromeDriver();
                break;

            case FIREFOX:
                WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
                driver = new FirefoxDriver();
                break;

            default: throw new IllegalStateException();
        }
        driver.manage().window().maximize();

       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return driver;
    }
}
