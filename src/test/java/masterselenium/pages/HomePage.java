package masterselenium.pages;

import masterselenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {
    By storeMenuLink = By.cssSelector("li[id='menu-item-1227'] a[class='menu-link']");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public HomePage load(){
        load("/"); //url is base URL
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }

    //is called fluent interface-//we navigate to new page, so we return that page-
    public StorePage navigateToStoreUsingMenu() {
        driver.findElement(storeMenuLink).click();//after we perform this action:
        //if we still in same page, no need to return the page
        return new StorePage(driver);
    }
}
