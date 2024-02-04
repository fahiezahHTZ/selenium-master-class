package masterselenium.tests;

import masterselenium.base.BaseTest;
import masterselenium.pages.HomePage;
import masterselenium.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NavigationTest extends BaseTest {

    //all navigation test can write here
    @Test
    public void NavigateFromHomeToStoreUsingMainMenu(){

        StorePage storePage = new HomePage(getDriver())
                .load().getMyHeader()
                .navigateToStoreUsingMenu();
        Assert.assertEquals(storePage.getTitle(),"Store");

    }
}
