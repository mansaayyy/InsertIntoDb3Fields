package com.example.manasi.insertintodb3fields;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Manasi on 15-10-2017.
 */

public class DB_Controller extends SQLiteOpenHelper {

    public DB_Controller(Context c) {
        super(c,"tablebla.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE FOOD(ITEM TEXT, COST TEXT, QTY TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldv, int newv) {
        db.execSQL("DROP TABLE IF EXISTS FOOD");
        onCreate(db);
    }

    public void insertitem(String item, String cost, String qty) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ITEM",item);
        cv.put("COST",cost);
        cv.put("QTY",qty);
        db.insert("FOOD",null,cv);
    }

    public Cursor listitem() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM FOOD",null);
        return c;
    }

    public String get_cost(String item) {
        String cost="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("FOOD",null,"ITEM=?",new String[]{item}, null,null,null);
        if(c.getCount()<1) {
            c.close();
            return "NOT EXIST";
        }
        c.moveToFirst();
        cost = c.getString(c.getColumnIndex("COST"));
        return cost;
    }

    public String get_qty(String item) {
        String qty="";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query("FOOD",null,"ITEM=?",new String[]{item}, null,null,null);
        if(c.getCount()<1) {
            c.close();
            return "NOT EXIST";
        }
        c.moveToFirst();
        qty = c.getString(c.getColumnIndex("QTY"));
        return qty;
    }

   public void update_qty(String item, String cost, String qty) {
       SQLiteDatabase db = this.getWritableDatabase();
       ContentValues cv = new ContentValues();
       int q = Integer.valueOf(qty).intValue();
       if(q==0)
           return;

       else {
           q = q-1;
           qty = Integer.toString(q);
           cv.put("ITEM", item);
           cv.put("COST",cost);
           cv.put("QTY",qty);

           db.update("FOOD",cv,"ITEM=?",new String[]{item});
       }
   }


}
