package android.wagday.com.coffie_shop_endproject.model;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="eer_db";
    public static final int DB_VERSION=1;
    SQLiteDatabase db;
    public MySQLiteOpenHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("CREATE TABLE if not EXISTS  Users (Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Firs_name	TEXT NOT NULL,"+
                "Last_name TEXT NOT NULL," +
                "Phone TEXT NOT NULL," +
                "Email TEXT," +
                "Password TEXT NOT NULL," +
                "userName TEXT NOT NULL unique );");

        db.execSQL("CREATE TABLE if not EXISTS Comments (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Id_users INTEGER NOT NULL," +
                "Id_prodact INTEGER NOT NULL," +
                "Comments TEXT," +
                "Date TEXT," +
                "Cuont_like INTEGER NOT NULL DEFAULT 0," +
                "FOREIGN KEY(Id_users) REFERENCES Users(Id)" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Size_prodact (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT UNIQUE" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Department (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name INTEGER UNIQUE\n" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Orders(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Users_id INTEGER," +
                "Time TEXT," +
                "Date TEXT," +
                "FOREIGN KEY(Users_id) REFERENCES Users (Id)" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Product (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name NUMERIC NOT NULL," +
                "price INTEGER NOT NULL DEFAULT 0," +
                "size_id INTEGER NOT NULL," +
                "Dep_id INTEGER NOT NULL," +
                "Image BLOB," +
                "Other_detilis TEXT," +
                "FOREIGN KEY(size_id) REFERENCES Department(Id)," +
                "FOREIGN KEY(price) REFERENCES Size_prodact(ID)" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Users_rating_prodact (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Id_users INTEGER NOT NULL," +
                "Id_prodact INTEGER NOT NULL," +
                "Count_star INTEGER," +
                "Date TEXT NOT NULL," +
                "FOREIGN KEY(Id_users) REFERENCES Users(Id)," +
                "FOREIGN KEY(Id_prodact) REFERENCES Product(Id)" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Details_Orders (" +
                "Id_order INTEGER NOT NULL," +
                "Id_prodact INTEGER NOT NULL," +
                "Total_price INTEGER," +
                "quantity INTEGER," +
                "FOREIGN KEY(Id_prodact) REFERENCES Product(Id)," +
                "FOREIGN KEY(Id_order) REFERENCES Orders(Id)" +
                ");");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // db.execSQL("Drop table if EXISTS ");
     //   onCreate(db);
        db.execSQL("CREATE TABLE if not EXISTS  Users (Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Firs_name	TEXT NOT NULL,"+
                "Last_name TEXT NOT NULL," +
                "Phone TEXT NOT NULL," +
                "Email TEXT," +
                "Password TEXT NOT NULL," +
                "userName TEXT NOT NULL unique );");
        db.execSQL("CREATE TABLE if not EXISTS Comments (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Id_users INTEGER NOT NULL," +
                "Id_prodact INTEGER NOT NULL," +
                "Comments TEXT," +
                "Date TEXT," +
                "Cuont_like INTEGER NOT NULL DEFAULT 0," +
                "FOREIGN KEY(Id_users) REFERENCES Users(Id)" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Size_prodact (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name TEXT UNIQUE" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Department (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Name INTEGER UNIQUE\n" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Orders(" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Users_id INTEGER," +
                "Time TEXT," +
                "Date TEXT," +
                "FOREIGN KEY(Users_id) REFERENCES Users (Id)" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Product (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name NUMERIC NOT NULL," +
                "price INTEGER NOT NULL DEFAULT 0," +
                "size_id INTEGER NOT NULL," +
                "Dep_id INTEGER NOT NULL," +
                "Image BLOB," +
                "Other_detilis TEXT," +
                "FOREIGN KEY(size_id) REFERENCES Department(Id)," +
                "FOREIGN KEY(price) REFERENCES Size_prodact(ID)" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Users_rating_prodact (" +
                "Id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "Id_users INTEGER NOT NULL," +
                "Id_prodact INTEGER NOT NULL," +
                "Count_star INTEGER," +
                "Date TEXT NOT NULL," +
                "FOREIGN KEY(Id_users) REFERENCES Users(Id)," +
                "FOREIGN KEY(Id_prodact) REFERENCES Product(Id)" +
                ");");
        db.execSQL("CREATE TABLE if not EXISTS Details_Orders (" +
                "Id_order INTEGER NOT NULL," +
                "Id_prodact INTEGER NOT NULL," +
                "Total_price INTEGER," +
                "quantity INTEGER," +
                "FOREIGN KEY(Id_prodact) REFERENCES Product(Id)," +
                "FOREIGN KEY(Id_order) REFERENCES Orders(Id)" +
                ");");
    }



}
