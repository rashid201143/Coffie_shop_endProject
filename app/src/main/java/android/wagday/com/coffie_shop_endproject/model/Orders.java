package android.wagday.com.coffie_shop_endproject.model;

public class Orders
{
    private int id;
    private int users_id;
    private String date;
    private String time;

    public Orders()
    {
    }

    public Orders(int id, int users_id, String date, String time) {
        this.id = id;
        this.users_id = users_id;
        this.date = date;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUsers_id() {
        return users_id;
    }

    public void setUsers_id(int users_id) {
        this.users_id = users_id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}