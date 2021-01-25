package android.wagday.com.coffie_shop_endproject.model;

public class Product_size
{
    private int id;
    private String name;
    public Product_size(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public Product_size() {
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