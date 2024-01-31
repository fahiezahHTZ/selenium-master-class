package utils;

import masterselenium.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){ //other class can't create instance of this class
      // String env = System.getProperty("env");
       String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
        //added default value as Stage incase maven command don't provide env var

        switch (EnvType.valueOf(env)){//support multiple env
            case STAGE:
                properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
                break;
            case PRODUCTION:
                properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
                break;
            default: throw new IllegalStateException("Invalid env type" + env);
        }



    }

    //************Singleton Design pattern-only 1 instance create through the program****
    //if there is no instance of this class, we create new instance
    //sending that new instance to the configloader var

    public static ConfigLoader getInstance(){
        if(configLoader == null){
            configLoader = new ConfigLoader();
        }
        return configLoader; //if next call this, then it will not be null, bcoz of static, it will return value
        // thatwhy it will create 1 instance, this is called singleton method
    }

    // we can create properties object instead of creating each method as below

    public String getBaseUrl(){
        String prop = properties.getProperty("baseUrl");
        if(prop != null) return prop;
        else throw new RuntimeException("property base url is not specified in the properties file");
    }
    public String getUsername(){
        String prop = properties.getProperty("username");
        if(prop != null) return prop;
        else throw new RuntimeException("property username is not specified in the properties file");
    }
    public String getPassword(){
        String prop = properties.getProperty("password");
        if(prop != null) return prop;
        else throw new RuntimeException("property password is not specified in the properties file");
    }
    public String getEmail(){
        String prop = properties.getProperty("email");
        if(prop != null) return prop;
        else throw new RuntimeException("property email is not specified in the properties file");
    }



}
