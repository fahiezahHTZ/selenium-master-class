package masterselenium.tests;

import masterselenium.base.BaseTest;
import masterselenium.pages.StorePage;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {


    @Test
    public void navigationToStore() throws InterruptedException {
        driver.get("https://askomdch.com/");
        StorePage sp = new StorePage(driver);
        //sp.clickSearchBtn();

    }
}
