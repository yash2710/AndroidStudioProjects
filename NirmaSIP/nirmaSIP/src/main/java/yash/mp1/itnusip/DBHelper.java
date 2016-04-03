package yash.mp1.itnusip;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import yash.mp1.itnusip.model.Contact;

/**
 * Created by Yash on 06-Apr-15.
 */
public class DBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "MyDB.db";
    public static final String TABLE_NAME = "users";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_SIP = "sip";
    public static final String COLUMN_BRANCH = "branch";


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
                        "(name text,id text,sip text,branch text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS contacts");
        onCreate(db);
    }

    public boolean insertNoDuplicate(String name, String id, String sip, String branch){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ TABLE_NAME +" WHERE sip=\""+sip+"\"", null );
        if(res.getCount() == 0){
            ContentValues contentValues = new ContentValues();

            contentValues.put(COLUMN_NAME, name);
            contentValues.put(COLUMN_ID, id);
            contentValues.put(COLUMN_SIP, sip);
            contentValues.put(COLUMN_BRANCH, branch);
            db.insert(TABLE_NAME, null, contentValues);
        }
        return true;
    }

    public boolean insert(String name, String id, String sip, String branch)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME, name);
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_SIP, sip);
        contentValues.put(COLUMN_BRANCH, branch);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
    }
    public Cursor getData(String name){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from "+ TABLE_NAME +" WHERE name=\""+name+"\"", null );
        return res;
    }
    public int numberOfRows(){
        SQLiteDatabase db = this.getReadableDatabase();
        int numRows = (int) DatabaseUtils.queryNumEntries(db, TABLE_NAME);
        return numRows;
    }
    public boolean update(String name, String id, String sip, String branch)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_ID, id);
        contentValues.put(COLUMN_SIP, sip);
        contentValues.put(COLUMN_BRANCH, branch);
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
    public List<Contact> getAll(String search)
    {
        List<Contact> list = new ArrayList<Contact>();
        //hp = new HashMap();
        SQLiteDatabase db = this.getReadableDatabase();
//        Cursor res =  db.rawQuery( "select * from "+TABLE_NAME, null );
        Cursor res =  db.query(TABLE_NAME,new String[] {COLUMN_NAME,COLUMN_ID,COLUMN_SIP,COLUMN_BRANCH},COLUMN_NAME+" LIKE ?",new String[] {"%"+search+"%"},null,null,COLUMN_NAME);
        res.moveToFirst();
        while(!res.isAfterLast()){
            list.add(new Contact(res.getString(0),res.getString(1),res.getString(2),res.getString(3)));
            res.moveToNext();
        }
        return list;
    }
}