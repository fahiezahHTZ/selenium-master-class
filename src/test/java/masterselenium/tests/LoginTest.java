package masterselenium.tests;

import masterselenium.Objects.Product;
import masterselenium.Objects.User;
import masterselenium.api.actions.CartApi;
import masterselenium.api.actions.SignUpApi;
import masterselenium.base.BaseTest;
import masterselenium.dataproviders.MyDataProvider;
import masterselenium.pages.CheckOutPage;
import org.junit.Assert;
import org.testng.annotations.Test;
import utils.FakerUtils;

import java.io.IOException;

public class LoginTest extends BaseTest {

    @Test
    public void loginDuringCheckOut() throws Exception {
        String username = "tester"+new FakerUtils().generateRandomNumber();
        User user = new User()
                .setUserName(username)
                .setPassword("testerpwd")
                .setEmail(username + "@askomdch.com");

        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        CartApi cartApi = new CartApi();
        Product product = new Product(1215);
        cartApi.addToCart(product.getId(),1);

        CheckOutPage checkOutPage = new CheckOutPage(getDriver()).load();
        Thread.sleep(5000);
        injectCookiesToBrowser(cartApi.getCookies());
        checkOutPage.load();
        Thread.sleep(5000);
        checkOutPage
                .clickHereToLogin()
                        .login(user);
        Thread.sleep(5000);
        Assert.assertTrue(checkOutPage.getProductName().contains(product.getName()));

    }
}
