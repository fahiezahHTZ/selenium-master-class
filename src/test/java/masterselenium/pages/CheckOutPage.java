package masterselenium.pages;

import masterselenium.Objects.BillingAddress;
import masterselenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

public class CheckOutPage extends BasePage {
    private final By firstNameFld = By.id("billing_first_name");
    private final By lastNameFld = By.id("billing_last_name");
    private final By addressLineOneFld = By.id("billing_address_1");
    private final By billingCityFld = By.id("billing_city");
    private final By billingPostCodeFld = By.id("billing_postcode");
    private final By billingEmailFld = By.id("billing_email");
    private final By placeOrderBtn = By.id("place_order");
    private final By successNotice = By.cssSelector(".woocommerce-notice");
    private final By clickHeretoLogin = By.className("showlogin");
    private final By username = By.id("username");
    private final By password = By.id("password");
    private final By loginBtn = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By contryDropDown = By.id("billing_country");
    private final By stateDropdown = By.id("billing_state");


    public CheckOutPage(WebDriver driver) {
        super(driver);
    }

    public CheckOutPage enterFirstName(String fstName){
        driver.findElement(firstNameFld).clear();
        driver.findElement(firstNameFld).sendKeys(fstName);
        return this;
    }
    public CheckOutPage enterLastName(String LstName){
        driver.findElement(lastNameFld).clear();
        driver.findElement(lastNameFld).sendKeys(LstName);
        return this;
    }
    public CheckOutPage enterBillingAddress(String addr){
        driver.findElement(addressLineOneFld).clear();
        driver.findElement(addressLineOneFld).sendKeys(addr);
        return this;
    }
    public CheckOutPage enterBillingCity(String city){
        driver.findElement(billingCityFld).clear();
        driver.findElement(billingCityFld).sendKeys(city);
        return this;
    }
    public CheckOutPage selectCountry(String countryName){
        Select select = new Select(driver.findElement(contryDropDown));
        select.selectByVisibleText(countryName);
        return this;
    }
    public CheckOutPage selectState(String stateName){
        Select select = new Select(driver.findElement(stateDropdown));
        select.selectByVisibleText(stateName);
        return this;
    }
    public CheckOutPage enterBillingPostCode(String postCode){
        driver.findElement(billingPostCodeFld).clear();
        driver.findElement(billingPostCodeFld).sendKeys(postCode);
        return this;
    }
    public CheckOutPage enterEmail(String email){
        driver.findElement(billingEmailFld).clear();
        driver.findElement(billingEmailFld).sendKeys(email);
        return this;
    }

    public CheckOutPage setBillingAddress(BillingAddress billingAddress){
        return enterFirstName(billingAddress.getFirstName()).
                enterLastName(billingAddress.getLastName()).
                selectCountry(billingAddress.getCountry()).
                enterBillingAddress(billingAddress.getAddressLineOne()).
                enterBillingCity(billingAddress.getCity()).
                selectState(billingAddress.getState()).
                enterBillingPostCode(billingAddress.getPostalCode())
                .enterEmail(billingAddress.getEmail());

    }

    public CheckOutPage placeOrder()
    {
        waitForOverlaysToDisappear(overlay);
        driver.findElement(placeOrderBtn).click();
        return this;
    }

    public CheckOutPage clickHereToLogin(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(clickHeretoLogin)).click();
        return this;


    }
    public CheckOutPage login(String usname, String pwd){
        wait.until(ExpectedConditions.visibilityOfElementLocated(username)).sendKeys(usname);
        driver.findElement(password).sendKeys(pwd);
        driver.findElement(loginBtn).click();
        return this;
    }
    public String getTextSuccessNotice() throws InterruptedException {
        Thread.sleep(3000);
        return driver.findElement(successNotice).getText();
    }


}
