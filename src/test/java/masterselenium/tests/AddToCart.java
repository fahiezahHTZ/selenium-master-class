package masterselenium.tests;

import masterselenium.Objects.Product;
import masterselenium.base.BaseTest;
import masterselenium.pages.CartPage;
import masterselenium.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AddToCart extends BaseTest {

    @Test
    public void addToCartFromStorePage() throws IOException {
        Product product = new Product(1215);
        CartPage cartPage  = new StorePage(getDriver())
                .load()
                .clickAddToCartBtn(product.getName())
                .clickViewCart();
        Assert.assertEquals(cartPage.getProductName(),product.getName());

    }

}