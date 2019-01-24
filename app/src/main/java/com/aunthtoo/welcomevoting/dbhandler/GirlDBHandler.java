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

public class GirlDBHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "welcome.db";

    //girl table name
    private static final String TABLE_GIRL = "girl";

    // Girl Table Columns names
    private static final String GIRL_ID = "gid";
    private static final String GIRL_NAME = "gname";
    private static final String GIRL_BD = "gbd";
    private static final String GIRL_IMG="gimg";
    private static final String GIRL_QUEEN="gqueen";
    private static final String GIRL_GLORY="gglory";
    private static final String GIRL_ATTRACTION="gattraction";
    private static final String GIRL_INNOCENCE="ginnocence";
    private static final String GIRL_ALL="gall";

    public GirlDBHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_GIRL_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_GIRL + "("
                + GIRL_ID + " INTEGER PRIMARY KEY," + GIRL_NAME + " TEXT," + GIRL_BD + " TEXT,"+GIRL_IMG+" TEXT,"+GIRL_QUEEN+" INTEGER,"+GIRL_GLORY+" INTEGER,"+GIRL_ATTRACTION+" INTEGER,"+GIRL_INNOCENCE+" INTEGER,"+GIRL_ALL+" INTEGER)";

        db.execSQL(CREATE_GIRL_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GIRL);

        // Create tables again
        onCreate(db);
    }


    // Getting All girl
    public List<Selection> getAllGirls() {

        List<Selection> girlList = new ArrayList<Selection>();

        // Select All Query
        String selectQuery = "SELECT * FROM " + TABLE_GIRL;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);


        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Log.i("DATA : ", ""+cursor.getString(1));
                Selection girl = new Selection();
                girl.setId(Integer.parseInt(cursor.getString(0)));
                girl.setName(cursor.getString(1));
                girl.setBirthday(cursor.getString(2));
                girl.setImg(cursor.getString(3));


                // Adding girl to list
                girlList.add(girl);
            } while (cursor.moveToNext());
        }

        cursor.close();


        // return boy list
        return girlList;

    }

    //queen start
    public void setQueen(int idx,int num) {
        String query1 = "UPDATE " + TABLE_GIRL + " SET " + GIRL_QUEEN + "=" + num + " WHERE " + GIRL_ID + "==" + idx;


        String query2= "UPDATE " + TABLE_GIRL + " SET " + GIRL_QUEEN + "=0"+ " WHERE " + GIRL_ID + "!=" + idx;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query1);
        db.execSQL(query2);

    }

    public List<Selection> getQueen() {

        List<Selection> girlList = new ArrayList<>();

        //  +BOY_SMART+","

        String query="SELECT "+GIRL_ID+","+GIRL_NAME+","+GIRL_BD+","+GIRL_IMG+","+GIRL_QUEEN+" FROM "+TABLE_GIRL+" WHERE "+GIRL_GLORY+"==0 AND "+GIRL_ATTRACTION+"==0 AND "+GIRL_INNOCENCE+"==0 AND "+GIRL_ALL+"==0";



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
                girlList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return boy list
        return girlList;
    }

    public String getSelectedQueen()
    {
        String query="SELECT "+GIRL_NAME+" FROM "+TABLE_GIRL+" WHERE "+GIRL_QUEEN+"==1";
        String str="";

        SQLiteDatabase db = this.getWritableDatabase();


        try {

            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    str=cursor.getString(0);


                } while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (Exception e)
        {
//            str="Tap to choose";

        }

        if(str.equals(""))
            str="Tap to choose";


        return str;

    }


    //queen end

    //glory start
    public void setGlory(int idx,int num) {
        String query1 = "UPDATE " + TABLE_GIRL + " SET " + GIRL_GLORY + "=" + num + " WHERE " + GIRL_ID + "==" + idx;


        String query2= "UPDATE " + TABLE_GIRL + " SET " + GIRL_GLORY + "=0"+ " WHERE " + GIRL_ID + "!=" + idx;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query1);
        db.execSQL(query2);

    }

    public List<Selection> getGlory() {

        List<Selection> girlList = new ArrayList<>();

        //  +BOY_SMART+","

        String query="SELECT "+GIRL_ID+","+GIRL_NAME+","+GIRL_BD+","+GIRL_IMG+","+GIRL_GLORY+" FROM "+TABLE_GIRL+" WHERE "+GIRL_QUEEN+"==0 AND "+GIRL_ATTRACTION+"==0 AND "+GIRL_INNOCENCE+"==0 AND "+GIRL_ALL+"==0";



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
                girlList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return boy list
        return girlList;
    }


    public String getSelectedGlory()
    {
        String query="SELECT "+GIRL_NAME+" FROM "+TABLE_GIRL+" WHERE "+GIRL_GLORY+"==1";
        String str="";

        SQLiteDatabase db = this.getWritableDatabase();


        try {

            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    str=cursor.getString(0);


                } while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (Exception e)
        {
//            str="Tap to choose";

        }

        if(str.equals(""))
            str="Tap to choose";


        return str;

    }

    //glory end

    //attraction start
    public void setAttraction(int idx,int num) {
        String query1 = "UPDATE " + TABLE_GIRL + " SET " + GIRL_ATTRACTION + "=" + num + " WHERE " + GIRL_ID + "==" + idx;


        String query2= "UPDATE " + TABLE_GIRL + " SET " + GIRL_ATTRACTION + "=0"+ " WHERE " + GIRL_ID + "!=" + idx;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query1);
        db.execSQL(query2);

    }

    public List<Selection> getAttraction() {

        List<Selection> girlList = new ArrayList<>();

        //  +BOY_SMART+","

        String query="SELECT "+GIRL_ID+","+GIRL_NAME+","+GIRL_BD+","+GIRL_IMG+","+GIRL_ATTRACTION+" FROM "+TABLE_GIRL+" WHERE "+GIRL_QUEEN+"==0 AND "+GIRL_GLORY+"==0 AND "+GIRL_INNOCENCE+"==0 AND "+GIRL_ALL+"==0";



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
                girlList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return boy list
        return girlList;
    }


    public String getSelectedAttraction()
    {
        String query="SELECT "+GIRL_NAME+" FROM "+TABLE_GIRL+" WHERE "+GIRL_ATTRACTION+"==1";
        String str="";

        SQLiteDatabase db = this.getWritableDatabase();


        try {

            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    str=cursor.getString(0);


                } while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (Exception e)
        {
//            str="Tap to choose";

        }

        if(str.equals(""))
            str="Tap to choose";


        return str;

    }


    //attraction end

    //innocence start
    public void setInnocence(int idx,int num) {
        String query1 = "UPDATE " + TABLE_GIRL + " SET " + GIRL_INNOCENCE + "=" + num + " WHERE " + GIRL_ID + "==" + idx;


        String query2= "UPDATE " + TABLE_GIRL + " SET " + GIRL_INNOCENCE + "=0"+ " WHERE " + GIRL_ID + "!=" + idx;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query1);
        db.execSQL(query2);

    }

    public List<Selection> getInnocence() {

        List<Selection> girlList = new ArrayList<>();

        //  +BOY_SMART+","

        String query="SELECT "+GIRL_ID+","+GIRL_NAME+","+GIRL_BD+","+GIRL_IMG+","+GIRL_INNOCENCE+" FROM "+TABLE_GIRL+" WHERE "+GIRL_QUEEN+"==0 AND "+GIRL_GLORY+"==0 AND "+GIRL_ATTRACTION+"==0 AND "+GIRL_ALL+"==0";



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
                girlList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return boy list
        return girlList;
    }


    public String getSelectedInnocence()
    {
        String query="SELECT "+GIRL_NAME+" FROM "+TABLE_GIRL+" WHERE "+GIRL_INNOCENCE+"==1";
        String str="";

        SQLiteDatabase db = this.getWritableDatabase();


        try {

            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    str=cursor.getString(0);


                } while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (Exception e)
        {
//            str="Tap to choose";

        }

        if(str.equals(""))
            str="Tap to choose";


        return str;

    }

    //innocence end

    //all queen start
    public void setAllQueen(int idx,int num) {
        String query1 = "UPDATE " + TABLE_GIRL + " SET " + GIRL_ALL + "=" + num + " WHERE " + GIRL_ID + "==" + idx;


        String query2= "UPDATE " + TABLE_GIRL + " SET " + GIRL_ALL + "=0"+ " WHERE " + GIRL_ID + "!=" + idx;

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query1);
        db.execSQL(query2);

    }

    public List<Selection> getAllQueen() {

        List<Selection> girlList = new ArrayList<>();

        //  +BOY_SMART+","

        String query="SELECT "+GIRL_ID+","+GIRL_NAME+","+GIRL_BD+","+GIRL_IMG+","+GIRL_ALL+" FROM "+TABLE_GIRL+" WHERE "+GIRL_QUEEN+"==0 AND "+GIRL_GLORY+"==0 AND "+GIRL_ATTRACTION+"==0 AND "+GIRL_INNOCENCE+"==0";



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
                girlList.add(boy);
            } while (cursor.moveToNext());
        }

        cursor.close();

        //return boy list
        return girlList;
    }


    public String getSelectedAllQueen()
    {
        String query="SELECT "+GIRL_NAME+" FROM "+TABLE_GIRL+" WHERE "+GIRL_ALL+"==1";
        String str="";

        SQLiteDatabase db = this.getWritableDatabase();


        try {

            Cursor cursor = db.rawQuery(query, null);

            // looping through all rows and adding to list
            if (cursor.moveToFirst()) {
                do {
                    str=cursor.getString(0);


                } while (cursor.moveToNext());
            }

            cursor.close();
        }
        catch (Exception e)
        {
//            str="Tap to choose";

        }

        if(str.equals(""))
            str="Tap to choose";


        return str;

    }



    //all queen end

}
