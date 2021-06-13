package com.example.miniprojet.dal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBConnection extends SQLiteOpenHelper {
    private final static String DB_NAME = "myDB";
    private final static int VERSION = 1;

    private final static String REQUETE ="create table reunion(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title text," +
            "date text);" +
            "create table subject(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "title text," +
            "id_reunion INTEGER NOT NULL," +
            "FOREIGN KEY(id_reunion) references reunion(id));" +
            "create table personne(" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT," +
            "fullname text," +
            "id_reunion INTEGER NOT NULL," +
            "FOREIGN KEY(id_reunion) references reunion(id));";
    public DBConnection(@Nullable Context context) {
        super(context, DB_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(REQUETE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table reunion; " +
                "drop table subject;" +
                "drop table personne;");
        onCreate(db);
    }
}
