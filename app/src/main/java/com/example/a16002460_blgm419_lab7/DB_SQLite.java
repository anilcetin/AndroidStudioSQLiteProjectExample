package com.example.a16002460_blgm419_lab7;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DB_SQLite extends SQLiteOpenHelper {

    public static final String DB_Filename="Person.db";
    int version=1;



    public DB_SQLite(@Nullable Context context) {
        super(context,DB_Filename,null,1 );
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        boolean dbcheck=checkDB();

        if (!dbcheck){

            db.execSQL("create table Persons"+"(Name text,surname text, phone integer);");

        }else{
            SQLiteDatabase.openDatabase(DB_Filename,null,SQLiteDatabase.OPEN_READWRITE);
        }

    }

    private boolean checkDB(){
        SQLiteDatabase check=null;

        try {
            check=SQLiteDatabase.openDatabase(DB_Filename,null,SQLiteDatabase.OPEN_READONLY);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return check!=null;
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Male_Workers");
        db.execSQL("DROP TABLE IF EXISTS Female_Workers");
        onCreate(db);
    }
}
