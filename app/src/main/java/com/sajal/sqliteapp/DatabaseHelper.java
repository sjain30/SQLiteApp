package com.sajal.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String Database_name= "Student.db";
    public static final String Table_name= "Student_Table";
    public static final String Col1= "ID";
    public static final String Col2= "Name";
    public static final String Col3= "Surname";
    public static final String Col4= "Marks";

    public DatabaseHelper(@Nullable Context context) {
        super(context, Database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+Table_name+" (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, SURNAME TEXT, MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+Table_name);
        onCreate(sqLiteDatabase);
    }

    public boolean insetData(String name, String surName,String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col2,name);
        contentValues.put(Col3,surName);
        contentValues.put(Col4,marks);
        long result=db.insert(Table_name,null,contentValues);
        if (result==-1)
            return false;
        return true;
    }

    public Cursor viewAllData() {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+Table_name,null);
        return res;
    }

    public boolean updateData(String ID, String name, String surname, String marks){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Col1,ID);
        contentValues.put(Col2,name);
        contentValues.put(Col3,surname);
        contentValues.put(Col4,marks);
        db.update(Table_name,contentValues,"ID = ?",new String[] {ID});
        return true;
    }
}
