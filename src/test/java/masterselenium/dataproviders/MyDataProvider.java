package masterselenium.dataproviders;

import masterselenium.Objects.Product;
import masterselenium.Objects.User;
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

    @DataProvider(name = "getUsers")
    public static Object[][]getUsers() throws IOException {
        User[] users = JacksonUtils.deserializeJson("userLogin.json", User[].class);

        Object[][] userobj = new Object[users.length][1];
        for (int i=0; i<1; i++) {
            userobj[i][0] = users[i];
        }
        return userobj;
    }
   /* @DataProvider(name="registerNewusers")
    public static Object[][]registerUsers(){
    }*/




}
