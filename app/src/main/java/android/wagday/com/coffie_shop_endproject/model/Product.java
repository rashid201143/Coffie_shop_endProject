package android.wagday.com.coffie_shop_endproject.model;

import java.sql.Blob;

public class Product
{
    private int id;
    private int name;
    private int price;
    private int size_id;
    private int dep_id;
    private Blob image;
    private String other_details;
    public Product() {}

    public Product(int id, int name, int price, int size_id, int dep_id, Blob image, String other_details) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.size_id = size_id;
        this.dep_id = dep_id;
        this.image = image;
        this.other_details = other_details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSize_id() {
        return size_id;
    }

    public void setSize_id(int size_id) {
        this.size_id = size_id;
    }

    public int getDep_id() {
        return dep_id;
    }

    public void setDep_id(int dep_id) {
        this.dep_id = dep_id;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public String getOther_details() {
        return other_details;
    }

    public void setOther_details(String other_details) {
        this.other_details = other_details;
    }


}
