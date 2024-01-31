package masterselenium.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import masterselenium.Objects.User;
import org.checkerframework.checker.units.qual.C;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import utils.ConfigLoader;

import java.util.HashMap;

import static io.restassured.RestAssured.*;

public class SignUpApi {

    private Cookies cookies; // assign cookies from response after POST api called

    public Cookies getCookies(){
        return cookies;
    }

    private String fetchRegisterNonceValueUsingGroovy(){
        Response response = getAccount();
        //groovy findAll "it." method
        return response.htmlPath().getString("**.findAll{ it.@name == 'woocommerce-register-nonce'}.@value");
    }
    private String fetchRegisterNonceValueUsingJSOUP(){
        Response response = getAccount();
        Document doc = Jsoup.parse(response.body().prettyPrint());
        Element element= doc.selectFirst("#woocommerce-register-nonce");
        return element.attr("value"); // fetch value from below response
        // <input type="hidden" id="woocommerce-register-nonce" name="woocommerce-register-nonce" value="b51565ae36"/>
    }
    private Response getAccount(){
        Cookies cookies = new Cookies(); // create obj locally
        Response response = given()
                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                .cookies(cookies)
                .log().all().
        when().
                get("/account").
        then().
                log().all()
                .extract()
                .response();
        if(response.getStatusCode() != 200){
            throw new RuntimeException("Failed to fetch the account, HTTP Status code: "+response.getStatusCode());
        }
        return response;
    }

    //register call return "cookies" in response
    //we need to fetch the cookies and assign to this class obj
    public Response register(User user){ //POSTAccount()
        Cookies cookies = new Cookies(); // create obj locally

        Header header = new Header("content-type","application/x-www-form-urlencoded");//POST - from request header once registered user
        Headers headers = new Headers(header);

        //pass user info for registration
        HashMap<String, String> formParams = new HashMap<>();
        formParams.put("username", user.getUserName());
        formParams.put("email", user.getEmail());
        formParams.put("password", user.getPassword());
        formParams.put("woocommerce-register-nonce",fetchRegisterNonceValueUsingJSOUP());
        formParams.put("register", "Register");

        Response response = given()
                .baseUri(ConfigLoader.getInstance().getBaseUrl())
                .headers(headers)
                .formParams(formParams)
                .cookies(cookies)
                .log().all().
                when().
        post("/account").
                then().
                log().all()
                .extract()
                .response();
        if(response.getStatusCode() != 302){
            throw new RuntimeException("Failed to register the account, HTTP Status code: "+response.getStatusCode());
        }
        this.cookies = response.getDetailedCookies();
        return response;
    }


}
