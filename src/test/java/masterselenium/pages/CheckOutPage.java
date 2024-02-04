package masterselenium.pages;

import masterselenium.Objects.BillingAddress;
import masterselenium.Objects.User;
import masterselenium.base.BasePage;
import org.openqa.selenium.*;
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
    private final By usernamefield = By.id("username");
    private final By passwordfield = By.id("password");
    private final By loginBtn = By.name("login");
    private final By overlay = By.cssSelector(".blockUI.blockOverlay");
    private final By contryDropDown = By.id("billing_country");
    private final By stateDropdown = By.id("billing_state");
    private final By productName = By.cssSelector("td[class='product-name']");
    By alternateCountryDropDown = By.id("select2-billing_country-container");
    By alternateStateDropDown = By.id("select2-billing_state-container");
    private final By cashOnDeliveryTransferRadioBtn = By.id("payment_method_cod");
    private final By directBankTransferRadioBtn = By.id("payment_method_bacs");
    public CheckOutPage(WebDriver driver) {
        super(driver);
    }
    public CheckOutPage load(){
        load("/checkout/");
        return this;
    }


    public CheckOutPage enterFirstName(String fstName){
       WebElement e = waitForElementToBeVisible(firstNameFld);
        e.clear();
        e.sendKeys(fstName);
        return this;
    }
    public CheckOutPage enterLastName(String LstName){
        WebElement e = waitForElementToBeVisible(lastNameFld);
        e.clear();
        e.sendKeys(LstName);
        return this;
    }
    public CheckOutPage enterBillingAddress(String addr){
        WebElement e = waitForElementToBeVisible(addressLineOneFld);
        e.clear();
        e.sendKeys(addr);
        return this;
    }
    public CheckOutPage enterBillingCity(String city){
        WebElement e = waitForElementToBeVisible(billingCityFld);
        e.clear();
        e.sendKeys(city);
        return this;
    }
    public CheckOutPage selectCountry(String countryName){
        //this has issue in firefox, as ele could not be scrolled into view
       /* WebElement e = waitForElementToBeClickable(contryDropDown);
        Select select = new Select(e);
        select.selectByVisibleText(countryName);*/


        waitForElementToBeClickable(alternateCountryDropDown).click();
        WebElement e =waitForElementToBeClickable (By.xpath("//li[text()='"+countryName+"']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
        return this;
    }
    public CheckOutPage selectState(String stateName){
        /*WebElement e = waitForElementToBeClickable(stateDropdown);
        Select select = new Select(e);
        select.selectByVisibleText(stateName);
        */
        waitForElementToBeClickable(alternateStateDropDown).click();
        WebElement e =waitForElementToBeClickable (By.xpath("//li[text()='"+stateName+"']"));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",e);
        e.click();
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
    public CheckOutPage enterUsername(String username){
        waitForElementToBeVisible(usernamefield).sendKeys(username);
        return this;
    }
    public CheckOutPage enterPassword(String pwd){
        waitForElementToBeVisible(passwordfield).sendKeys(pwd);
        return this;
    }
    public CheckOutPage clickLoginBtn(){
        waitForElementToBeClickable(loginBtn).click();
        return this;
    }
    private CheckOutPage waitForLoginBtnToDisappeared(){
        wait.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }

    public CheckOutPage login(User user){
      return enterUsername(user.getUserName())
              .enterPassword(user.getPassword())
              .clickLoginBtn()
              .waitForLoginBtnToDisappeared();
    }

    public CheckOutPage selectDirectBankTransfer(){
        WebElement e = waitForElementToBeClickable(directBankTransferRadioBtn);
       if(!e.isSelected()){
           e.click();
       }
       return this;
    }
    public CheckOutPage selctCashOnDeliveryTransfer(){
        WebElement e =waitForElementToBeClickable(cashOnDeliveryTransferRadioBtn);
        if(!e.isSelected()){
            e.click();
        }
        return this;
    }

    public String getTextSuccessNotice() throws InterruptedException {
        WebElement e = waitForElementToBeVisible(successNotice);
        return e.getText();
    }
    public String getProductName() throws Exception {
        int i=5;
        while(i>0){
            try{
                return waitForElementToBeVisible(productName).getText();
            }catch (StaleElementReferenceException e){
                System.out.println("Not Found, trying again"+e);
            }
            Thread.sleep(5000);
            i--;
            }
            throw new Exception("Element not found");
        }

}
