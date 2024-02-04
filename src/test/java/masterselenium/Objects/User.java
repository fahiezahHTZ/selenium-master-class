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
    private String email;

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }
    public User(String username,String pwd) throws IOException {
        this.username = username;
        this.password = pwd;

    }

    public User(){ }
    public User(String username,String pwd,String email) throws IOException {
        this.username = username;
        this.password = pwd;
        this.email = email;
    }

    @JsonGetter("username")
    public String getUserName() {
        return username;
    }

    @JsonSetter("username")
    public User setUserName(String username) {

        this.username = username;
        return this;
    }

    @JsonGetter("password")
    public String getPassword() {
        return password;
    }

    @JsonSetter("password")
    public User setPassword(String password) {

        this.password = password;
        return this;
    }


}
