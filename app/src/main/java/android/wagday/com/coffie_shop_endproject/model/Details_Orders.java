package android.wagday.com.coffie_shop_endproject.model;

public class Details_Orders
{    private int id_order;
    private int id_product;
    private int total_price;
    private int quantity;
    public Details_Orders() {}

    public Details_Orders(int id_order, int id_product, int total_price, int quantity)
    {
        this.id_order = id_order;
        this.id_product = id_product;
        this.total_price = total_price;
        this.quantity = quantity;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }


}