package masterselenium.pages.Components;

import masterselenium.base.BasePage;
import masterselenium.pages.CartPage;
import masterselenium.pages.HomePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductThumbnail extends BasePage {
    private final By viewCartLink = By.cssSelector("a[title='View cart']");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getAddToCartBtnElement(String productName){

        return By.cssSelector("a[aria-label='Add “"+ productName +"” to your cart']");
    }
    public ProductThumbnail clickAddToCartBtn(String productName){
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
