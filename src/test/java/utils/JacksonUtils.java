package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import masterselenium.Objects.BillingAddress;

import java.io.IOException;
import java.io.InputStream;

//create Jackson class to deserialize the json
public class JacksonUtils {
/*

    //create method to return java obj
    public static BillingAddress deserializeJson(InputStream is, BillingAddress billingAddress) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is,billingAddress.getClass());//return billing address obj
    }
*/

    //To read for every as a generic
    public static <T> T deserializeJson(String fileName, Class<T>T) throws IOException {
        // read the file
        InputStream is = JacksonUtils.class.getClassLoader().getResourceAsStream(fileName);
        // map the file to desired object
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is,T);
    }


}
