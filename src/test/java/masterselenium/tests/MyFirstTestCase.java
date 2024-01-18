package masterselenium.tests;

import masterselenium.Objects.BillingAddress;
import masterselenium.Objects.Product;
import masterselenium.Objects.User;
import masterselenium.base.BaseTest;
import masterselenium.pages.CartPage;
import masterselenium.pages.CheckOutPage;
import masterselenium.pages.HomePage;
import masterselenium.pages.StorePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JacksonUtils;

import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {
String searchfor = "Blue";
    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {

        //pass json file into java object=billingAddress, use it in our code below)
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);

/*
        BillingAddress billingAddress = new BillingAddress();
        //read Json file -from Meaven support class
        InputStream is = getClass().getClassLoader().getResourceAsStream("myBillingAddress.json");
       billingAddress = JacksonUtils.deserializeJson(is,billingAddress);//pass file path=is, billing-obj
*/

        /*
        BillingAddress billingAddress = new BillingAddress().
                setFirstName("tester").
                setLastName("tst").
                setAddressLineOne("San Francisco").
                setCity("San Francisco").
                setPostalCode("94188").
                setEmail("tester1@gmail.com");
                */


        /*used Constructor -this is not readable, but it only 2 lines
        BillingAddress billingAddress = new BillingAddress("tester","tst",
         "San Francisco","San Francisco", "94188", "tester1@gmail.com");
        */
        StorePage storePage = new HomePage(driver)
                .load()
                .navigateToStoreUsingMenu();
        storePage.isLoaded();
        storePage.search(searchfor);

//        StorePage storePage = homePage.navigateToStoreUsingMenu();//return storepage-it is called interface
//        storePage.search("Blue"); //STRUCTURAL PAGE OBJECT
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchfor+"”");
        storePage.clickAddToCartBtn(product.getName());

        CartPage cartPage =  storePage.clickViewCart();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        CheckOutPage checkOutpage = cartPage.
                clickCheckOutBtn().
                setBillingAddress(billingAddress).
                placeOrder();
        /*checkOutpage.
                    enterFirstName("tester1").
                    enterLastName("tester2").
                    enterBillingAddress("San Francisco").
                    enterBillingCity("San Francisco").
                    enterBillingPostCode("94188").
                    enterEmail("tester1@gmail.com").
                */


        Assert.assertEquals(
                checkOutpage.getTextSuccessNotice(),
                "Thank you. Your order has been received.");

    }

    @Test
    public void LoginAndCheckOUtUsingDirectBankTransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);
        StorePage storePage = new HomePage(driver)
                .load()
                .navigateToStoreUsingMenu();
        storePage.isLoaded();
        storePage.search(searchfor);
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchfor+"”");

        storePage.clickAddToCartBtn(product.getName());
        CartPage cartPage =  storePage.clickViewCart();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        CheckOutPage checkOutpage = cartPage.clickCheckOutBtn();
        checkOutpage.clickHereToLogin();
        User user = JacksonUtils.deserializeJson("user.json",User.class);

        checkOutpage.
                login(user.getUserName(), user.getPassword());
        checkOutpage.
                     setBillingAddress(billingAddress).
                     placeOrder();

        Assert.assertEquals(checkOutpage.getTextSuccessNotice(),
                "Thank you. Your order has been received.");

    }


}
