package com.example.miniprojet.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.miniprojet.Entities.Reunion;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReunionAdapter {
    private SQLiteDatabase myDB;
    private final String TABLE_NAME = "reunion";
    private final String[] TABLE_COLUMNS = new String[]{"id","title","date","duree"};

    public ReunionAdapter(SQLiteDatabase dbConnection){
        this.myDB = dbConnection;
    }

    public long insertReunion(Reunion reunion){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",reunion.getTitle());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        contentValues.put("date",now.toString());
        long returnn = myDB.insert(TABLE_NAME, null, contentValues);
        Log.d("return value " ,"---------"+returnn);
        return returnn;
    }

    public ArrayList<Reunion> getReunions(){
        Cursor cursor = myDB.query(TABLE_NAME,TABLE_COLUMNS,null,null,null,null,null);
        return cursorToArray(cursor);
    }

    public Reunion getById(int id){
        Cursor cursor = myDB.query(TABLE_NAME,null,"id="+id,null,null,null,null);
        return cursorToReunion(cursor);
    }
    public void deleteById(int id){
        myDB.delete(TABLE_NAME,"id="+id,null);
    }

    private ArrayList<Reunion> cursorToArray(Cursor cursor) {
        ArrayList<Reunion> reunions = new ArrayList();
        if(cursor.getCount() == 0) return reunions;
        cursor.moveToFirst();
        do{
            reunions.add(new Reunion(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3)));
        }while(cursor.moveToNext());
        return reunions;
    }
    private Reunion cursorToReunion(Cursor cursor){
        Reunion reunion = new Reunion();
        if(cursor.getCount() == 0) return reunion;
        cursor.moveToFirst();
        do{
            reunion = new Reunion(cursor.getInt(0),cursor.getString(1),cursor.getString(2));
        }while(cursor.moveToNext());
        return reunion;
    }
    public void update(Reunion reunion){
        ContentValues contentValues = new ContentValues();
        contentValues.put("duree",reunion.getDuree());
        myDB.update(TABLE_NAME,contentValues,"id="+reunion.getId(),null);
    }
}
