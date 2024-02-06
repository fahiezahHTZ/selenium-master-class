package utils;

import com.github.javafaker.Faker;
import io.qameta.allure.Step;

public class FakerUtils {


    public Long generateRandomNumber(){
        Faker faker = new Faker();
        return faker.number().randomNumber(10,true);
    }
}
