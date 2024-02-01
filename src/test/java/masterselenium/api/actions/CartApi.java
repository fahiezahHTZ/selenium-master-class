package masterselenium.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import utils.ConfigLoader;

import java.util.HashMap;
import java.util.Objects;

import static io.restassured.RestAssured.given;

public class CartApi {

    private Cookies cookies;

    public CartApi(){}//if customer is not login, we use this constructor while creating obj of this class
    //basically we won't take any cookie
    // then will call addToCart(), will set empty cookie


    public CartApi(Cookies cookies) {//when customer logged in,
        this.cookies = cookies;//set cookies//will get cookies from register/login api
    }

    public Cookies getCookies() {
        return cookies;
    }

    public Response addToCart(int productId, int quantity){

        Header header = new Header("content-type","application/x-www-form-urlencoded");//POST - from request header once registered user
        Headers headers = new Headers(header);

        //pass user info for registration
        HashMap<String, Object> formParams = new HashMap<>();
        formParams.put("product_sku","");
        formParams.put("product_id", productId);
        formParams.put("quantity",quantity);

        //for customer login situation, cookie will not be null
        if(cookies == null) { // set empty if it is null
            cookies = new Cookies();
        }
        Response response = given()
                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                .headers(headers)
                .formParams(formParams)
                .cookies(cookies)
                .log().all().
        when().
                post("/?wc-ajax=add_to_cart").
        then().
                log().all()
                .extract()
                .response();
        if(response.getStatusCode() != 200){
            throw new RuntimeException("Failed to add product"+productId+ "to the cart "+"HTTP Status code: "+response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }


}
