package android.wagday.com.coffie_shop_endproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.wagday.com.coffie_shop_endproject.model.MySQLiteOpenHelper;

import android.wagday.com.coffie_shop_endproject.model.Users;

public class DataBes {
    MySQLiteOpenHelper sql;
    private SQLiteDatabase db;
    Context context;

    public DataBes(MySQLiteOpenHelper sql, Context context) {
        this.db = sql.getWritableDatabase();;
        this.context = context;
    }
    public DataBes(MySQLiteOpenHelper sql) {
        this.db = sql.getWritableDatabase();;

    }
    public Users p(String str, String str2) {
        Users users = null;
        try {
            // ArrayList<Users> list= new ArrayList<Users>();
            SQLiteDatabase sQLiteDatabase = this.db;
            Cursor rawQuery = sQLiteDatabase.rawQuery("SELECT id,Firs_name,Last_name,Phone,Email from" +
                    " Users  where Password=? and userName=?  ", new String[]{"" + str + "", "" + str2 + ""});
            rawQuery.moveToFirst();
            int i2 = 0;
            while (!rawQuery.isAfterLast()) {
                i2 = rawQuery.getInt(0);
                users=new Users(i2,rawQuery.getString(1),rawQuery.getString(2),rawQuery.getString(3),rawQuery.getString(4),str,str2);
                //list.add(users);
                rawQuery.moveToNext();
            }
            rawQuery.close();
            return users;
        } catch (Exception unused) {
            return users;
        }
    }
    public long add(Users users){
        ContentValues values =new ContentValues();
        values.put("Firs_name",users.getFirst_name());
        values.put("Last_name",users.getLast_name());
        values.put("Phone",users.getPhone());
        values.put("Email",users.getEmail());
        values.put("Password",users.getPassword());
        values.put("userName",users.getNameLog());
        long id=  db.insert("Users",null,values);
        return id;
//        sql.add("INSERT INTO Users(Firs_name,Last_name,Phone,Email,Password,userName) VALUES ("+users.getFirst_name()+"" +
//                " , "+users.getLast_name()+"," +
//                " "+users.getPhone()+", "+users.getEmail()+", " +
//                ""+users.getPassword()+","+users.getNameLog()+")");

    }
}

