package masterselenium.tests;

import io.qameta.allure.*;
import masterselenium.Objects.User;
import masterselenium.base.BaseTest;
import masterselenium.pages.AccountPage;
import masterselenium.pages.HomePage;
import org.testng.annotations.Test;
import utils.FakerUtils;

@Epic("Register User")
@Feature("Registration")
public class UserRegister extends BaseTest {


@Story("register user")
    @Link("https://example.org")
    @Link(name="allure", type="mylink")
    @TmsLink("12345")
    @Issue("123456")
    @Description("user registration")
    @Test(description = "user registration")
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
