package warehouse.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Product {
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName_photo_file() {
        return name_photo_file;
    }

    public void setName_photo_file(String name_photo_file) {
        this.name_photo_file = name_photo_file;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    String name_product;
    String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    double price;
    String name_photo_file;
}

