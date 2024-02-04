package masterselenium.pages.Components;

import masterselenium.base.BasePage;
import masterselenium.pages.AccountPage;
import masterselenium.pages.StorePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class MyHeader extends BasePage {

    private final By accountMenu = By.linkText("Account");
    By storeMenuLink = By.cssSelector("li[id='menu-item-1227'] a[class='menu-link']");
    public MyHeader(WebDriver driver){
        super(driver);
    }

    public AccountPage navigateToAccount(){
        driver.findElement(accountMenu).click();
        return new AccountPage(driver);
    }

    //is called fluent interface-//we navigate to new page, so we return that page-
    public StorePage navigateToStoreUsingMenu() {
        driver.findElement(storeMenuLink).click();//after we perform this action:
        //if we still in same page, no need to return the page
        return new StorePage(driver);
    }


}
