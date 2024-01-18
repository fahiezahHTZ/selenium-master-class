package masterselenium.pages;

import masterselenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends BasePage {
    private final By productName = By.cssSelector("td[class='product-name'] a");
    public final By checkOutBtn = By.xpath("//a[normalize-space()='Proceed to checkout']");
    private final By cartHeading = By.cssSelector(".has-text-align-center");
    public CartPage(WebDriver driver) {
        super(driver);
    }
    public Boolean isLoaded(){

        return wait.until(ExpectedConditions.textToBe(cartHeading,"Cart"));
    }
    public String getProductName(){
     return wait.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();

    }
    public CheckOutPage clickCheckOutBtn(){
        wait.until(ExpectedConditions.elementToBeClickable(checkOutBtn)).click();
        return new CheckOutPage(driver);
    }
}
