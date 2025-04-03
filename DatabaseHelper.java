package com.example.mobileapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String databaseName ="Signup.db";

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Signup.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase dt) {
        dt.execSQL("create Table allusers(email TEXT primary key, password TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase dt, int oldVersion, int newVersion) {
        dt.execSQL("drop Table if exists allusers");

    }

   public boolean insertData(String email, String password ){
        SQLiteDatabase dt = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put("email", email);
       contentValues.put("password", password);
      long result =  dt.insert("allusers", null, contentValues);

      if(result == -1){
          return false;

      } else {
          return true;
      }

   }
   public Boolean checkEmail(String email){
       SQLiteDatabase db = this.getWritableDatabase();
       Cursor cursor = db.rawQuery("Select * from allusers where email = ?", new String[]{email});

       if(cursor.getCount()>0){
           return true;

       }else {
           return false;

       }
   }
   public Boolean checkEmailPassword(String email, String password){
       SQLiteDatabase dt = this.getWritableDatabase();
       Cursor cursor = dt.rawQuery("Select * from allusers where email = ? and password = ?", new String[]{email, password});
       if (cursor.getCount()>0){
           return true;
       }else {
           return false;
       }
   }
}
