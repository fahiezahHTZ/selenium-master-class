package masterselenium.tests;

import masterselenium.Objects.BillingAddress;
import masterselenium.Objects.Product;
import masterselenium.Objects.User;
import masterselenium.api.actions.CartApi;
import masterselenium.api.actions.SignUpApi;
import masterselenium.base.BaseTest;
import masterselenium.pages.CheckOutPage;
import net.bytebuddy.build.Plugin;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.FakerUtils;
import utils.JacksonUtils;

import java.io.IOException;

public class CheckoutTest extends BaseTest {

    @Test
    public void guestGheckOutUsingDirectBankTransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();

        CartApi cartApi = new CartApi();
        cartApi.addToCart(1215,1);
        injectCookiesToBrowser(cartApi.getCookies());

        checkOutPage.load()
                .setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();
        Assert.assertEquals(checkOutPage.getTextSuccessNotice(),
                "Thank you. Your order has been received.");

    }


    @Test
    public void loginAndCheckoutUsingDirectBankTransfer() throws IOException, InterruptedException {
        BillingAddress billingAddress = JacksonUtils.deserializeJson("myBillingAddress.json",BillingAddress.class);
        String username = "tester"+new FakerUtils().generateRandomNumber();
        User user = new User()
                .setUserName(username)
                .setPassword("testerpwd")
                .setEmail(username + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi(signUpApi.getCookies());
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(), 1);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();

        injectCookiesToBrowser(signUpApi.getCookies());
        checkOutPage.load();
        checkOutPage.setBillingAddress(billingAddress)
                .selectDirectBankTransfer()
                .placeOrder();
        Assert.assertEquals(checkOutPage.getTextSuccessNotice(),
                "Thank you. Your order has been received.");


    }

}
