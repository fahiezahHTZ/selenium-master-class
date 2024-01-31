package masterselenium.api.actions;


import masterselenium.Objects.User;
import utils.FakerUtils;

public class DummyTest {

    public static void main(String[] args){
        String username = "tester"+ new FakerUtils().generateRandomNumber();
        User user = new User()
                .setUserName(username)
                .setPassword("testerpwd")
                .setEmail(username+"@askomdch.com");
        SignUpApi signUpApi = new SignUpApi();

        System.out.println(signUpApi.register(user));
        System.out.println(signUpApi.getCookies());



   //  System.out.println(new SignUpApi().fetchRegisterNonceValueUsingJSOUP());
    }
}
