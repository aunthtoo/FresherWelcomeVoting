package com.aunthtoo.welcomevoting.dbhandler;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import com.aunthtoo.welcomevoting.model.Selection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Aunt Htoo on 12/25/2016.
 */

public class BoyDBHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "welcome.db";

    // boy table name
    private static final String TABLE_BOY = "boy";


    // Boy Table Columns names
    private static final String BOY_ID = "bid";
    private static final String BOY_NAME = "bname";
    private static final String BOY_BD = "bbd";
    private static final String BOY_IMG = "bimg";
    private static final String BOY_KING = "bking";
    private static final String BOY_SMART = "bsmart";
    private static final String BOY_POPULAR = "bpopular";
    private static final String BOY_JOKER = "bjoker";
    private static final String BOY_ALL = "ball";
    private static final String BOY_COUPlE = "bcouple";


    public BoyDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_BOY_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_BOY + "("
                + BOY_ID + " INTEGER PRIMARY KEY," + BOY_NAME + " TEXT," + BOY_BD + " TEXT, " + BOY_IMG + " TEXT, " + BOY_KING + "INTEGER, " + BOY_SMART + "INTEGER, " + BOY_POPULAR + " INTEGER, " + BOY_JOKER + "INTEGER, " + BOY_ALL + "INTEGER, " + BOY_COUPlE + "INTEGER)";

        db.execSQL(CREATE_BOY_TABLE);

    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BOY);

        // Create tables again
        onCreate(db);
    }

    // Getting All boy
    public List<Selection> getAllBoys() {

        List<Selection> boyList = new ArrayList<Selection>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_BOY;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("DATA : ", "" + cursor.getString(1));
                Selection boy = new Selection();
                boy.setId(Integer.parseInt(cursor.getString(0)));
                boy.setName(cursor.getString(1));
                boy.setBirthday(cursor.getString(2));


                // Adding boy to list
                boyList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();


        // return boy list
        return boyList;

    }

    //king start

    public void setKing(int idx, int num) {
        String query1 = "UPDATE " + TABLE_BOY + " SET " + BOY_KING + "=" + num + " WHERE " + BOY_ID + "==" + idx;


        String query2 = "UPDATE " + TABLE_BOY + " SET " + BOY_KING + "=0" + " WHERE " + BOY_ID + "!=" + idx;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query1);
        db.execSQL(query2);

    }


    public List<Selection> getKing() {

        List<Selection> boyList = new ArrayList<>();

        //  +BOY_SMART+","

        String query = "SELECT " + BOY_ID + "," + BOY_NAME + "," + BOY_BD + "," + BOY_IMG + "," + BOY_KING + " FROM " + TABLE_BOY + " WHERE " + BOY_SMART + "==0 AND " + BOY_POPULAR + "==0 AND " + BOY_JOKER + "==0 AND " + BOY_ALL + "==0";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("DATA : ", "" + cursor.getString(1));
                Selection boy = new Selection();
                boy.setId(Integer.parseInt(cursor.getString(0)));
                boy.setName(cursor.getString(1));
                boy.setBirthday(cursor.getString(2));
                boy.setImg(cursor.getString(3));
                boy.setChoose(cursor.getInt(4));


                // Adding boy to list
                boyList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return boy list
        return boyList;
    }

    public String getSelectedKing() {
        String query = "SELECT " + BOY_NAME + " FROM " + TABLE_BOY + " WHERE " + BOY_KING + "==1";
        String str = "";

        SQLiteDatabase db = this.getWritableDatabase();


        try {

            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    str = cursor.getString(0);


                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
//            str="Tap to choose";

        }

        if (str.equals(""))
            str = "Tap to choose";


        return str;

    }

    //king end===

    //Smart Start
    public void setSmart(int idx, int num) {
        String query1 = "UPDATE " + TABLE_BOY + " SET " + BOY_SMART + "=" + num + " WHERE " + BOY_ID + "==" + idx;


        String query2 = "UPDATE " + TABLE_BOY + " SET " + BOY_SMART + "=0" + " WHERE " + BOY_ID + "!=" + idx;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query1);
        db.execSQL(query2);

    }

    public List<Selection> getSmart() {

        List<Selection> boyList = new ArrayList<>();

        //  +BOY_SMART+","

        String query = "SELECT " + BOY_ID + "," + BOY_NAME + "," + BOY_BD + "," + BOY_IMG + "," + BOY_SMART + " FROM " + TABLE_BOY + " WHERE " + BOY_KING + "==0 AND " + BOY_POPULAR + "==0 AND " + BOY_JOKER + "==0 AND " + BOY_ALL + "==0";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("DATA : ", "" + cursor.getString(1));
                Selection boy = new Selection();
                boy.setId(Integer.parseInt(cursor.getString(0)));
                boy.setName(cursor.getString(1));
                boy.setBirthday(cursor.getString(2));
                boy.setImg(cursor.getString(3));
                boy.setChoose(cursor.getInt(4));


                // Adding boy to list
                boyList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return boy list
        return boyList;
    }

    public String getSelectedSmart() {
        String query = "SELECT " + BOY_NAME + " FROM " + TABLE_BOY + " WHERE " + BOY_SMART + "==1";
        String str = "";

        SQLiteDatabase db = this.getWritableDatabase();


        try {

            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    str = cursor.getString(0);


                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
//            str="Tap to choose";

        }

        if (str.equals(""))
            str = "Tap to choose";


        return str;

    }

    //smart end

    //popular start

    public void setPopular(int idx, int num) {
        String query1 = "UPDATE " + TABLE_BOY + " SET " + BOY_POPULAR + "=" + num + " WHERE " + BOY_ID + "==" + idx;


        String query2 = "UPDATE " + TABLE_BOY + " SET " + BOY_POPULAR + "=0" + " WHERE " + BOY_ID + "!=" + idx;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query1);
        db.execSQL(query2);

    }

    public List<Selection> getPopular() {

        List<Selection> boyList = new ArrayList<>();

        //  +BOY_SMART+","

        String query = "SELECT " + BOY_ID + "," + BOY_NAME + "," + BOY_BD + "," + BOY_IMG + "," + BOY_POPULAR + " FROM " + TABLE_BOY + " WHERE " + BOY_KING + "==0 AND " + BOY_SMART + "==0 AND " + BOY_JOKER + "==0 AND " + BOY_ALL + "==0";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("DATA : ", "" + cursor.getString(1));
                Selection boy = new Selection();
                boy.setId(Integer.parseInt(cursor.getString(0)));
                boy.setName(cursor.getString(1));
                boy.setBirthday(cursor.getString(2));
                boy.setImg(cursor.getString(3));
                boy.setChoose(cursor.getInt(4));


                // Adding boy to list
                boyList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return boy list
        return boyList;
    }


    public String getSelectedPopular() {
        String query = "SELECT " + BOY_NAME + " FROM " + TABLE_BOY + " WHERE " + BOY_POPULAR + "==1";
        String str = "";

        SQLiteDatabase db = this.getWritableDatabase();


        try {

            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    str = cursor.getString(0);


                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
//            str="Tap to choose";

        }

        if (str.equals(""))
            str = "Tap to choose";


        return str;

    }


    //popular end

    //joker start
    public void setJoker(int idx, int num) {
        String query1 = "UPDATE " + TABLE_BOY + " SET " + BOY_JOKER + "=" + num + " WHERE " + BOY_ID + "==" + idx;


        String query2 = "UPDATE " + TABLE_BOY + " SET " + BOY_JOKER + "=0" + " WHERE " + BOY_ID + "!=" + idx;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query1);
        db.execSQL(query2);

    }


    public List<Selection> getJoker() {

        List<Selection> boyList = new ArrayList<>();

        //  +BOY_SMART+","

        String query = "SELECT " + BOY_ID + "," + BOY_NAME + "," + BOY_BD + "," + BOY_IMG + "," + BOY_JOKER + " FROM " + TABLE_BOY + " WHERE " + BOY_KING + "==0 AND " + BOY_SMART + "==0 AND " + BOY_POPULAR + "==0 AND " + BOY_ALL + "==0";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("DATA : ", "" + cursor.getString(1));
                Selection boy = new Selection();
                boy.setId(Integer.parseInt(cursor.getString(0)));
                boy.setName(cursor.getString(1));
                boy.setBirthday(cursor.getString(2));
                boy.setImg(cursor.getString(3));
                boy.setChoose(cursor.getInt(4));


                // Adding boy to list
                boyList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return boy list
        return boyList;
    }


    public String getSelectedJoker() {
        String query = "SELECT " + BOY_NAME + " FROM " + TABLE_BOY + " WHERE " + BOY_JOKER + "==1";
        String str = "";

        SQLiteDatabase db = this.getWritableDatabase();


        try {

            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    str = cursor.getString(0);


                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
//            str="Tap to choose";

        }

        if (str.equals(""))
            str = "Tap to choose";


        return str;

    }


    //joker end

    //all king start
    public void setAllKing(int idx, int num) {
        String query1 = "UPDATE " + TABLE_BOY + " SET " + BOY_ALL + "=" + num + " WHERE " + BOY_ID + "==" + idx;


        String query2 = "UPDATE " + TABLE_BOY + " SET " + BOY_ALL + "=0" + " WHERE " + BOY_ID + "!=" + idx;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query1);
        db.execSQL(query2);

    }


    public List<Selection> getAllKing() {

        List<Selection> boyList = new ArrayList<>();

        //  +BOY_SMART+","

        String query = "SELECT " + BOY_ID + "," + BOY_NAME + "," + BOY_BD + "," + BOY_IMG + "," + BOY_ALL + " FROM " + TABLE_BOY + " WHERE " + BOY_KING + "==0 AND " + BOY_SMART + "==0 AND " + BOY_POPULAR + "==0 AND " + BOY_JOKER + "==0";


        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("DATA : ", "" + cursor.getString(1));
                Selection boy = new Selection();
                boy.setId(Integer.parseInt(cursor.getString(0)));
                boy.setName(cursor.getString(1));
                boy.setBirthday(cursor.getString(2));
                boy.setImg(cursor.getString(3));
                boy.setChoose(cursor.getInt(4));


                // Adding boy to list
                boyList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return boy list
        return boyList;
    }

    public String getSelectedAllKing() {
        String query = "SELECT " + BOY_NAME + " FROM " + TABLE_BOY + " WHERE " + BOY_ALL + "==1";
        String str = "";

        SQLiteDatabase db = this.getWritableDatabase();


        try {

            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    str = cursor.getString(0);


                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
//            str="Tap to choose";

        }

        if (str.equals(""))
            str = "Tap to choose";


        return str;

    }


    //best couple


    public void setCouple(int idx, int num) {
        String query1 = "UPDATE " + TABLE_BOY + " SET " + BOY_COUPlE + "=" + num + " WHERE " + BOY_ID + "==" + idx;

        String query2 = "UPDATE " + TABLE_BOY + " SET " + BOY_COUPlE + "=0" + " WHERE " + BOY_ID + "!=" + idx;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query1);
        db.execSQL(query2);

    }


    public List<Selection> getCouple() {

        List<Selection> boyList = new ArrayList<>();

        //  +BOY_SMART+","

        String query = "SELECT " + BOY_ID + "," + BOY_NAME + "," + BOY_BD + "," + BOY_IMG + "," + BOY_COUPlE + " FROM " + TABLE_BOY;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("DATA : ", "" + cursor.getString(1));
                Selection boy = new Selection();
                boy.setId(Integer.parseInt(cursor.getString(0)));
                boy.setName(cursor.getString(1));
                boy.setBirthday(cursor.getString(2));
                boy.setImg(cursor.getString(3));
                boy.setChoose(cursor.getInt(4));


                // Adding boy to list
                boyList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return boy list
        return boyList;
    }

    public String getSelectedCouple() {
        String query = "SELECT " + BOY_ID + " FROM " + TABLE_BOY + " WHERE " + BOY_COUPlE + "==1";
        String str = "";

        SQLiteDatabase db = this.getWritableDatabase();


        try {

            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    str = cursor.getString(0);


                } while (cursor.moveToNext());
            }

            cursor.close();
        } catch (Exception e) {
//            str="Tap to choose";

        }

        if (str.equals(""))
            str = "Tap to choose";


        return str;

    }

    //king end===

}
