package android.wagday.com.coffie_shop_endproject.model;

public class Comments {
    private int id;
    private int id_users;
    private int id_product;
    private String comments;
    private String Date;
    public Comments(int id, int id_users, int id_product, String comments, String date) {
        this.id = id;
        this.id_users = id_users;
        this.id_product = id_product;
        this.comments = comments;
        Date = date;
    }
    public Comments()
    {

    }
    public int getId()
    {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_users() {
        return id_users;
    }

    public void setId_users(int id_users) {
        this.id_users = id_users;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}