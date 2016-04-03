package com.example.app4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Yash on 06-Apr-15.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDB.db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASSWORD = "pass";


    private HashMap hp;

    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME , null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        Log.d("Created", "DB");
        db.execSQL(
                "create table " + TABLE_NAME +
                        "(name TEXT,pass TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS users");
        onCreate(db);
    }

    public boolean insert(String name, String pass) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from `" + TABLE_NAME + "` WHERE name=\"" + name + "\"", null);
        res.moveToFirst();
        if (res.getCount() == 0) {
            ContentValues contentValues = new ContentValues();

            contentValues.put(COLUMN_NAME, name);
            contentValues.put(COLUMN_PASSWORD, pass);
            db.insert(TABLE_NAME, null, contentValues);
        }
        res.close();
        return true;
    }
    public Cursor getData(String name, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from `"+ TABLE_NAME +"` WHERE name=\""+name+"\" and pass=\""+pass+"\"", null );
        res.moveToFirst();
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }
    public boolean update(String name, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_PASSWORD, pass);
        db.update(TABLE_NAME,contentValues,"name = ?", new String[] {name});
        return true;
    }

    public Integer deleteContact (Integer id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
                "id = ? ",
                new String[] { Integer.toString(id) });
    }
    public ArrayList getAll()
    {
        ArrayList array_list = new ArrayList();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        res.moveToFirst();
        while(!res.isAfterLast()){
            array_list.add(res.getString(0));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }
}