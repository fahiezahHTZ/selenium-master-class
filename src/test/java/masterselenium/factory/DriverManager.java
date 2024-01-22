package masterselenium.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import masterselenium.constants.DriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.Locale;


public class DriverManager {

    public WebDriver initializeDriver(String brwser)  {
        WebDriver driver;

        //to run from Maven command line/JVM argument with System.get property)

       brwser = System.getProperty("browser",brwser);//can run by both maven command/testng Param
        //DriverType.valueOf -> converted to Enum when receive browsername
        //localBrowser = brwser;
        switch (DriverType.valueOf(brwser)){
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
