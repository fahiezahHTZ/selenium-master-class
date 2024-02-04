package masterselenium.tests;

import masterselenium.Objects.BillingAddress;
import masterselenium.Objects.Product;
import masterselenium.Objects.User;
import masterselenium.base.BaseTest;
import masterselenium.dataproviders.MyDataProvider;
import masterselenium.pages.CartPage;
import masterselenium.pages.CheckOutPage;
import masterselenium.pages.HomePage;
import masterselenium.pages.StorePage;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.ConfigLoader;
import utils.JacksonUtils;

import java.io.IOException;
import java.io.InputStream;

public class MyFirstTestCase extends BaseTest {
String searchfor = "Blue";


   @Test
    public void guestCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {

        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);

        StorePage storePage = new HomePage(getDriver())
                .load().getMyHeader()
                .navigateToStoreUsingMenu();
        storePage.isLoaded();
        storePage.search(searchfor);

        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchfor+"”");
        storePage.getProductThumbnail()
                .clickAddToCartBtn(product.getName());

        CartPage cartPage =  storePage.getProductThumbnail()
                .clickViewCart();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        CheckOutPage checkOutpage = cartPage.
                clickCheckOutBtn().
                setBillingAddress(billingAddress).
                placeOrder();

        Assert.assertEquals(
                checkOutpage.getTextSuccessNotice(),
                "Thank you. Your order has been received.");
    }

   @Test(dataProvider = "getUsers", dataProviderClass = MyDataProvider.class)
    public void LoginAndCheckOUtUsingDirectBankTransfer(User user) throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        Product product = new Product(1215);
       // User user = new User(ConfigLoader.getInstance().getUsername(), ConfigLoader.getInstance().getPassword());
        StorePage storePage = new HomePage(getDriver())
                .load()
                .getMyHeader()
                .navigateToStoreUsingMenu();
        storePage.isLoaded();
        storePage.search(searchfor);
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchfor+"”");

        storePage.getProductThumbnail().clickAddToCartBtn(product.getName());
        CartPage cartPage =  storePage.getProductThumbnail()
                .clickViewCart();
        cartPage.isLoaded();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

        CheckOutPage checkOutpage = cartPage.clickCheckOutBtn();
        checkOutpage.clickHereToLogin();

        //User user = JacksonUtils.deserializeJson("userLogin.json",User.class);


        checkOutpage.
                login(user);
        Thread.sleep(5000);
        checkOutpage.
                     setBillingAddress(billingAddress).
                     placeOrder();

        Assert.assertEquals(checkOutpage.getTextSuccessNotice(),
                "Thank you. Your order has been received.");

    }


}
