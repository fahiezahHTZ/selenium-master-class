package masterselenium.pages;

import masterselenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class StorePage extends BasePage {
    private final By searchFld =  By.id("woocommerce-product-search-field-0");
    private final By searchBtn = By.cssSelector("button[value='Search']");
    private final By title = By.cssSelector(".woocommerce-products-header__title.page-title");
    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage search(String txt) //Functional PAGE OBJECT
    {
        enterTextInSearchFld(txt).
                clickSearchBtn();
        return this;
    }
    public Boolean isLoaded(){
      return   wait.until(ExpectedConditions.urlContains("/store"));
    }


    private StorePage enterTextInSearchFld(String txt) {
        driver.findElement(searchFld).sendKeys(txt);
        return this;
    }
    private StorePage clickSearchBtn(){
        driver.findElement(searchBtn).click();
        return this;
    }


    public String getTitle(){
        return driver.findElement(title).getText();
    }

    //trying to make flexible any product can find
    private By getAddToCartBtnElement(String productName){

        return By.cssSelector("a[aria-label='Add “"+ productName +"” to your cart']");
    }
    public StorePage clickAddToCartBtn(String productName){
        By addToCartBtn = getAddToCartBtnElement(productName);
        driver.findElement(addToCartBtn).click();
        return this;
    }

    public CartPage clickViewCart(){
        WebElement e = waitForElementToBeClickable(viewCartLink);
        e.click();
        return new CartPage(driver);
    }

    
    
}

