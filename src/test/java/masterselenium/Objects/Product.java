package masterselenium.Objects;

import utils.JacksonUtils;

import java.io.IOException;

//create Product class, make sure**, here var name and type same with our Json file feilds
public class Product {
    private int id;
    private String name;
    //by dealing with json file, always better to create default constructor

    public Product(){
    }

    public Product(int id) throws IOException {
        Product[] products = JacksonUtils.deserializeJson("products.json",Product[].class);
        for (Product p:products) {
            if(p.getId() == id){
                this.id = id;
                this.name = p.getName();
            }
        }
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
