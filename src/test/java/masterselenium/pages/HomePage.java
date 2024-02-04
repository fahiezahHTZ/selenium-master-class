package masterselenium.pages;

import masterselenium.base.BasePage;
import masterselenium.pages.Components.MyHeader;
import masterselenium.pages.Components.ProductThumbnail;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class HomePage extends BasePage {

    //create objs for the components,
    // create new instances of these components in the constructor of the homepage
    private MyHeader myHeader;
    private ProductThumbnail productThumbnail;

    //here both way ok either change private to public of this 2 components
    //or create getter methods for them to use from outside of this class
    public MyHeader getMyHeader() {
        return myHeader;
    }

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public HomePage(WebDriver driver) {
        super(driver);
        // whenever we create new obj of homepage
        // we will get new instance of components
       myHeader = new MyHeader(driver);
       productThumbnail = new ProductThumbnail(driver);
    }

    public HomePage load(){
        super.load("/"); //url is base URL
        wait.until(ExpectedConditions.titleContains("AskOmDch"));
        return this;
    }








}
