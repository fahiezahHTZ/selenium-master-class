package masterselenium.Objects;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonSetter;

import java.io.IOException;
import java.util.Properties;

public class User {
    private String username;
    private String password;

    private int age;

    public User(){ }

    public User(String username,String pwd) throws IOException {
        this.username = username;
        this.password = pwd;
    }

    @JsonGetter("username")
    public String getUserName() {
        return username;
    }

    @JsonSetter("username")
    public void setUserName(String username) {

        this.username = username;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    @JsonSetter("password")
    public void setPassword(String password) {

        this.password = password;
    }

}
