package com.example.miniprojet.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.miniprojet.Entities.Personne;
import com.example.miniprojet.Entities.Subject;


import java.util.ArrayList;

public class PersonneAdapter {
    private SQLiteDatabase myDB;
    private final String TABLE_NAME = "personne";
    private final String[] TABLE_COLUMNS = new String[]{"id","fullname","role","duree","id_reunion"};

    public PersonneAdapter(SQLiteDatabase dbConnection){
        this.myDB = dbConnection;
    }

    public long insertPersonne(Personne personne){
        ContentValues contentValues = new ContentValues();
        contentValues.put("fullname",personne.getFullName());
        contentValues.put("role",personne.getRole());
        contentValues.put("id_reunion",personne.getId_reunion());
        long returnn = myDB.insert(TABLE_NAME, null, contentValues);
        Log.d("return value " ,"---------"+returnn);
        return returnn;
    }

    public ArrayList<Personne> getPersonnes(int id_reunion){
        Cursor cursor = myDB.query(TABLE_NAME,TABLE_COLUMNS,"id_reunion="+id_reunion,null,null,null,null);
        return cursorToArray(cursor);
    }

    public Personne getById(int id){
        Cursor cursor = myDB.query(TABLE_NAME,null,"id="+id,null,null,null,null);
        return cursorToPersonne(cursor);
    }
    public void deleteById(int id){
        myDB.delete(TABLE_NAME,"id="+id,null);
    }

    private ArrayList<Personne> cursorToArray(Cursor cursor) {
        ArrayList<Personne> personnes = new ArrayList();
        if(cursor.getCount() == 0) return personnes;
        cursor.moveToFirst();
        do{
            personnes.add(new Personne(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3)));
        }while(cursor.moveToNext());
        return personnes;
    }
    private Personne cursorToPersonne(Cursor cursor){
        Personne personne = new Personne();
        if(cursor.getCount() == 0) return personne;
        cursor.moveToFirst();
        do{
            personne = new Personne(cursor.getInt(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3));
        }while(cursor.moveToNext());
        return personne;
    }
    private void update(Personne personne){
        ContentValues contentValues = new ContentValues();
        contentValues.put("duree",personne.getDuree());
        myDB.update(TABLE_NAME,contentValues,"id="+personne.getId(),null);
    }
}
