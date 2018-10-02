
/**
 * This Class for handling database operations like open , insert
 **/

package com.example.a7medassem.raye7task.SQL;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;
import com.example.a7medassem.raye7task.Activity.Favorite;

public class favoriteDB {

    private static SQLiteDatabase newsSQLiteDB;
    private static Cursor dbCursor;


    public static void creatTable(Context context)
    {
        try {
        newsSQLiteDB = context.openOrCreateDatabase("newsDB", Context.MODE_PRIVATE, null);

        newsSQLiteDB.execSQL("CREATE TABLE IF NOT EXISTS favorite(name VARCHAR ,date VARCHAR,image VARCHAR,url VARCHAR PRIMARY KEY)");

    } catch (Exception e) {
        e.printStackTrace();
    }
    }

    public static void insertData(String Name ,String Date ,String Image,String Url,Context context)
    {
        try {
        newsSQLiteDB.execSQL("insert into favorite values('"+Name+"','"+Date+"','"+Image+"','"+Url+"')");
        Toast.makeText(context,"Added to favorites",Toast.LENGTH_LONG).show();
        } catch (SQLException e) {
            Toast.makeText(context,"New has been Added",Toast.LENGTH_LONG).show();
        }
    }

    public static void showData()
    {
        dbCursor = newsSQLiteDB.rawQuery("SELECT DISTINCT name , date , image , url" + " FROM favorite ",null);
        try {
            if (dbCursor != null && dbCursor.moveToFirst()) {
                do {
                    String Name = dbCursor.getString(0);
                    String Date = dbCursor.getString(1);
                    String Image = dbCursor.getString(2);
                    String Url = dbCursor.getString(3);
                    Favorite.showData(Name,Date,Image,Url);
                } while (dbCursor.moveToNext());}

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
