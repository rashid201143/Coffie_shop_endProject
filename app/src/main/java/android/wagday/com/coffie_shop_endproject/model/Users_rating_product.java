package android.wagday.com.coffie_shop_endproject.model;
public class Users_rating_product
{
    private int id;
    private int users_id;
    private String date;
    private int count_star;
    private int love;
    private int good;
    private int bad;
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

    public int getCount_star() {
        return count_star;
    }

    public void setCount_star(int count_star) {
        this.count_star = count_star;
    }

    public int getLove() {
        return love;
    }

    public void setLove(int love) {
        this.love = love;
    }

    public int getGood() {
        return good;
    }

    public void setGood(int good) {
        this.good = good;
    }

    public int getBad() {
        return bad;
    }

    public void setBad(int bad) {
        this.bad = bad;
    }

    public Users_rating_product(int id, int users_id, String date, int count_star, int love, int good, int bad) {
        this.id = id;
        this.users_id = users_id;
        this.date = date;
        this.count_star = count_star;
        this.love = love;
        this.good = good;
        this.bad = bad;
    }

    public Users_rating_product() {
    }


}