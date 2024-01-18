package masterselenium.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
   // protected WebDriverWait waitshort;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        //can create global var as per below wait long, medium or short time for specific elements
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
      //  waitshort = new WebDriverWait(driver,Duration.ofSeconds(5));
    }

    //how it works? *****
    public void load(String endPoint){

        driver.get("https://askomdch.com"+ endPoint);
    }

    //write method for reusable for everypage
    public void waitForOverlaysToDisappear(By overlay){
        List<WebElement> overlays = driver.findElements(overlay);
        System.out.println("Overlay size"+ overlays.size());
        if(overlays.size()>0){
//            new WebDriverWait(driver, Duration.ofSeconds(10)).until(
//                    ExpectedConditions.invisibilityOfAllElements(overlays)
//            );
            wait.until(ExpectedConditions.invisibilityOfAllElements(overlays));
            System.out.println("Overlays invisible");
        } else {
            System.out.println("Overlay not found");
        }

    }




}
