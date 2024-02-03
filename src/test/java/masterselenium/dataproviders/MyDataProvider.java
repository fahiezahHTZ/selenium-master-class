package masterselenium.dataproviders;

import masterselenium.Objects.Product;
import org.testng.annotations.DataProvider;
import utils.JacksonUtils;

import java.io.IOException;

public class MyDataProvider {
    @DataProvider(name = "getFeaturedProducts")
    public static Object[][] getFeaturedProducts() throws IOException {
        Product[] products = JacksonUtils.deserializeJson("products.json", Product[].class);

        Object[][] output = new Object[products.length][1];
        for (int i = 0; i < products.length; i++) {
            output[i][0] = products[i];
        }
        return output;
    }


}
