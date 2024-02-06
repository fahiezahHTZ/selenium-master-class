package masterselenium.api.actions;


import masterselenium.Objects.User;
import utils.FakerUtils;

public class DummyTest {

    public static void main(String[] args){

        //when customer login, we will set the user, signup the user
        String username = "tester"+ new FakerUtils().generateRandomNumber();
        User user = new User()
                .setUserName(username)
                .setPassword("testerpwd")
                .setEmail(username+"@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();
        signUpApi.register(user);
        System.out.println("REGISTER COOKIES: "+signUpApi.getCookies());
        CartApi cartapi = new CartApi();
        cartapi.addToCart(1215,1);
        System.out.println("CART COOKIES: "+cartapi.getCookies());
        System.out.println("added dummy test");


   //  System.out.println(new SignUpApi().fetchRegisterNonceValueUsingJSOUP());

            /*user not login and add to cart
            CartApi cartapi = new CartApi();
            cartapi.addToCart(1215,1);
            cartapi.getCookies();
            */



    }
}
