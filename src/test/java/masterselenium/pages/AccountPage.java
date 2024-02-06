package masterselenium.pages;

import com.beust.ah.A;
import io.qameta.allure.Step;
import masterselenium.Objects.User;
import masterselenium.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AccountPage extends BasePage {


    private final By regUsername = By.id("reg_username");
    private final By regEmail = By.id("reg_email");
    private final By regPassword = By.id("reg_password");
    private final By registerBtn = By.name("register");
    public AccountPage(WebDriver driver) {
        super(driver);
    }
    @Step
    public AccountPage load(){
        load("/account/");
        return this;
    }

    public void enterUsername(String username){
        driver.findElement(regUsername).sendKeys(username);
    }
    public void enterPassword(String pw){
        driver.findElement(regPassword).sendKeys(pw);
    }
    public void enterEmail(String email){
        driver.findElement(regEmail).sendKeys(email);
    }
    public void clickOnRegister(){
        driver.findElement(registerBtn).click();
    }

    @Step
    public void registerNewUser(String username,String pw, String email){
        enterUsername(username);
        enterPassword(pw);
        enterEmail(email);
        clickOnRegister();
    }



}
