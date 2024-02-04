package masterselenium.tests;

import masterselenium.Objects.User;
import masterselenium.base.BaseTest;
import masterselenium.pages.AccountPage;
import masterselenium.pages.HomePage;
import org.testng.annotations.Test;
import utils.FakerUtils;

public class UserRegister extends BaseTest {


    @Test
    public void registerUser() throws InterruptedException {
        String username = "testdata"+new FakerUtils().generateRandomNumber();
        User user = new User()
                .setUserName(username)
                .setPassword("testerpwd")
                .setEmail(username + "@askomdch.com");
        AccountPage accPage = new HomePage(getDriver())
                .load().getMyHeader()
                .navigateToAccount();
        Thread.sleep(3000);
        accPage.registerNewUser(user.getUserName(), user.getPassword(),user.getEmail());

    }
}
