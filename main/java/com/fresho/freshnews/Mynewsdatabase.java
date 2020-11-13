package com.fresho.freshnews;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Mynewsdatabase extends SQLiteOpenHelper {

  private static final String databasename="storednews";

  private static final int databasev=1;
  private static final String idkey="id";

  private static final String dbtable="newstable";

  private static final String linke="link";

  private static final String date="date";


  public Mynewsdatabase(@Nullable Context context) {
        super(context, databasename, null, databasev);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

      String create = "Create table "+dbtable+"("+idkey+" integer primary key autoincrement"+","+ date+" text"+","+linke+" text)";
      sqLiteDatabase.execSQL(create);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

      sqLiteDatabase.execSQL("drop table if exists "+dbtable);
    onCreate(sqLiteDatabase);
    }

       public void insert(String date,String link){

          SQLiteDatabase sqLiteDatabase = getWritableDatabase();
          String inser = "insert into "+dbtable+" values(null,'"+date+"','"+link+"')";
    sqLiteDatabase.execSQL(inser);

       }

       public ArrayList<String> getall(){

             ArrayList<String> arrayList = new ArrayList<>();
SQLiteDatabase sqLiteDatabase = getWritableDatabase();
String query = "SELECT * FROM "+dbtable;
         Cursor cursor = sqLiteDatabase.rawQuery(query,null);
         while (cursor.moveToNext()){

          String lin =  cursor.getString(2);
          arrayList.add(lin);

         }

             return  arrayList;

       }


}
