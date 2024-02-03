package masterselenium.base;

import io.restassured.http.Cookies;
import masterselenium.factory.DriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.CookiesUtil;

import java.util.List;

public class BaseTest {
    //protected WebDriver driver; <-- need to create threadlocal to run parallel
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    private void setDriver(WebDriver driver){
        this.driver.set(driver);
    }
    protected WebDriver getDriver(){
        return this.driver.get();
    }

    @Parameters("browser")
    @BeforeMethod
    public void startDriver(@Optional String browser) // CRHOME
    {
       // browser = System.getProperty("browser", browser);//can run by both maven command/testng Param
       if(browser == null) browser = "CHROME";
        //System.setProperty("webdriver.chrome.driver", "/Users/fahiezah/Desktop/2024Projects/SeleniumJavaMasterClass/src/test/java/utils/chromedriver");
        //driver = new DriverManager().initializeDriver(browser);
        setDriver(new DriverManager().initializeDriver(browser));
        //only this class able to set driver,
        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId()+ ","+
                "Driver = " +getDriver());

    }
    @AfterMethod
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
//        System.out.println("CURRENT THREAD: " + Thread.currentThread().getId()+ ","+
//                "Driver = " +getDriver());


        getDriver().quit();

    }

    public void injectCookiesToBrowser(Cookies cookies){

        List<Cookie> selenimCookies = new CookiesUtil().convertRestAssuredCookiesToSeleniumCookies(cookies);
        for(Cookie cookie: selenimCookies){
            getDriver().manage().addCookie(cookie);
        }
    }
}
