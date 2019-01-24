package com.aunthtoo.welcomevoting.premanager;

import android.content.Context;
import android.content.SharedPreferences;

import com.aunthtoo.welcomevoting.R;
import com.aunthtoo.welcomevoting.choosepages.Innocence;

/**
 * Created by Aunt Htoo on 1/7/2017.
 */

public class PrefManager {

    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Context _context;

    // shared pref mode
    int PRIVATE_MODE = 0;

    // Shared preferences file name
    private static final String PREF_NAME = "fresher-welcome";
    private static final String JOKER = "joker";
    private static final String ALL_KING = "allking";
    private static final String ALL_QUEEN = "allqueen";
    private static final String TWO_KEY = "twokey";
    private static final String COUPLE = "couple";

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setJoker(String jok) {
        editor.putString(JOKER, jok);
        editor.commit();
    }

    public void setAllKing(String allKing) {
        editor.putString(ALL_KING, allKing);
        editor.commit();
    }

    public void setAllQueen(String allQueen) {
        editor.putString(ALL_QUEEN, allQueen);
        editor.commit();
    }

    public void setTwoKey(int key) {
        editor.putInt(TWO_KEY, key);
        editor.commit();
    }

    public void setCOUPLE(String couple) {
        editor.putString(COUPLE, couple);
        editor.commit();
    }

    public String getJoker() {
        return pref.getString(JOKER, _context.getResources().getString(R.string.tap_to_choose));
    }

    public String getCOUPLE() {
        return pref.getString(COUPLE, _context.getResources().getString(R.string.tap_to_choose));
    }


    public String getAllKing() {
        return pref.getString(ALL_KING, _context.getResources().getString(R.string.tap_to_choose));
    }

    public String getAllQueen() {
        return pref.getString(ALL_QUEEN, _context.getResources().getString(R.string.tap_to_choose));
    }

    public int getTwoKey() {
        return pref.getInt(TWO_KEY, 0);
    }
}
