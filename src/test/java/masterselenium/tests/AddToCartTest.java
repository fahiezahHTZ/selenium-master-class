package masterselenium.tests;

import masterselenium.Objects.Product;
import masterselenium.base.BaseTest;
import masterselenium.dataproviders.MyDataProvider;
import masterselenium.pages.CartPage;
import masterselenium.pages.HomePage;
import masterselenium.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.JacksonUtils;

import java.io.IOException;

public class AddToCartTest extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage  = new StorePage(getDriver()).load()
                .getProductThumbnail().clickAddToCartBtn(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
    }

    @Test(dataProvider = "getFeaturedProducts", dataProviderClass = MyDataProvider.class)
    public void addToCartFeaturedProducts(Product product){
        CartPage cartPage = new HomePage(getDriver()).load()
                .getProductThumbnail().clickAddToCartBtn(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());
    }



}
