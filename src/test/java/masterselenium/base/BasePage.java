package masterselenium.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
   // protected WebDriverWait waitshort;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    //how it works? *****
    public void load(String endPoint){
        driver.get(ConfigLoader.getInstance().getBaseUrl()+ endPoint);//url is not hardcoded
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

    public WebElement waitForElementToBeVisible(By element){
       return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }
    public WebElement waitForElementToBeClickable(By element){
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }




}
