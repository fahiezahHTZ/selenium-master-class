package masterselenium.tests;

import masterselenium.base.BaseTest;
import masterselenium.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SearchTest extends BaseTest {

    @Test
    public void searchWithPartialMatch(){
        String searchFor = "Blue";
        StorePage storePage = new StorePage(getDriver())
                .load()
                .search(searchFor);
        //here sometime search product might getting delay,
        //to ensure that check the url is loaded -use wait until the url loaded
        //https://askomdch.com/?s=+blue&post_type=product -check endpoint url to be loaded
        Assert.assertEquals(storePage.getTitle(),"Search results: “"+searchFor+"”");
    }
}
