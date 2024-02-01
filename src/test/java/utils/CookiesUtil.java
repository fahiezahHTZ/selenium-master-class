package utils;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;

import java.util.ArrayList;
import java.util.List;

public class CookiesUtil {

//there is no cookies class in Selenium
//we need to return list of cookies

    //convert restassured cookies to list

    public List<Cookie> convertRestAssuredCookiesToSeleniumCookies(Cookies cookies){//as argument from rest assured
        List<io.restassured.http.Cookie> restAssuredCookies = new ArrayList<>();
        restAssuredCookies = cookies.asList();
        //list for selenium cookies
        List<Cookie> seleniumCookies = new ArrayList<>();
        for(io.restassured.http.Cookie cookie:restAssuredCookies){
            seleniumCookies.add(new Cookie(cookie.getName(),cookie.getValue(),cookie.getDomain(),
                    cookie.getPath(),cookie.getExpiryDate(), cookie.isSecured(),cookie.isHttpOnly(),cookie.getSameSite()));
        }
        return seleniumCookies;
    }

}
