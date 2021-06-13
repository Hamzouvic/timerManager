package com.example.miniprojet.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {
    private final static String DB_NAME = "myDB";
    private final static int VERSION = 4;

    private final static String REQUETE1 ="create table reunion(id INTEGER PRIMARY KEY AUTOINCREMENT,title text,date text,duree INTEGER);";
    private final static String REQUETE2 = "create table subject(id INTEGER PRIMARY KEY AUTOINCREMENT, title text,duree INTEGER, id_reunion INTEGER NOT NULL, FOREIGN KEY(id_reunion) references reunion(id));";
    private final static String REQUETE3 = "create table personne(id INTEGER PRIMARY KEY AUTOINCREMENT,fullname text,role text,duree INTEGER,id_reunion INTEGER NOT NULL,FOREIGN KEY(id_reunion) references reunion(id));";
    public DBConnection(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE1);
        db.execSQL(REQUETE2);
        db.execSQL(REQUETE3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table reunion; ");
        db.execSQL("drop table subject; ");
        db.execSQL("drop table personne; ");
        onCreate(db);
    }
}
