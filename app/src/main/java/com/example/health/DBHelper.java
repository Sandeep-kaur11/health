package com.example.health;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DBNAME = "healthplus1.db";
    public static final String tab = "appointments";

    public DBHelper(Context context) {
        super(context, "healthplus1.db", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase MyDB) {
        MyDB.execSQL("create Table patients(username TEXT primary key, password TEXT)");
        MyDB.execSQL("create Table appointments(clinic TEXT, date_time TEXT, user TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase MyDB, int i, int i1) {
        MyDB.execSQL("drop Table if exists patients");
        MyDB.execSQL("drop Table if exists appointments");
    }

    public Boolean insertData(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();
        contentValues.put("username", username);
        contentValues.put("password", password);

        long result = MyDB.insert("patients", null, contentValues);
        if(result==-1) return false;
        else
            return true;
    }
    public Boolean insertData2(String selected_clinic, String d_t, String user){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        ContentValues contentValues2= new ContentValues();
        contentValues2.put("clinic", selected_clinic);
        contentValues2.put("date_time", d_t);
        contentValues2.put("user", user);


        long result = MyDB.insert("appointments", null, contentValues2);
        if(result==-1) return false;
        else
            return true;
    }

    public Boolean alreadybooked(String user, String d_t) {
        SQLiteDatabase MyDB = this.getReadableDatabase();

        Cursor res = MyDB.rawQuery("select * from appointments where user= ? and password = ?", new String[]{user, d_t});
        if(res.getCount()>0)
            return true;
        else
            return false;
    }


    public Boolean checkusername(String username) {
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from patients where username = ?", new String[]{username});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }

    public Boolean checkusernamepassword(String username, String password){
        SQLiteDatabase MyDB = this.getWritableDatabase();
        Cursor cursor = MyDB.rawQuery("Select * from patients where username = ? and password = ?", new String[] {username,password});
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
