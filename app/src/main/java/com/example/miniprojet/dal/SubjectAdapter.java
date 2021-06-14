package com.example.miniprojet.dal;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.miniprojet.Entities.Subject;

import java.util.ArrayList;

public class SubjectAdapter {
    private SQLiteDatabase myDB;
    private final String TABLE_NAME = "subject";
    private final String[] TABLE_COLUMNS = new String[]{"id","title","duree","id_reunion"};

    public SubjectAdapter(SQLiteDatabase dbConnection){
        this.myDB = dbConnection;
    }

    public void insertSubject(Subject subject){
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",subject.getTitle());
        contentValues.put("id_reunion",subject.getId_reunion());
        long returnn = myDB.insert(TABLE_NAME, null, contentValues);
        Log.d("return value " ,"---------"+returnn);
    }

    public ArrayList<Subject> getSubjects(int id_reunion){
        Cursor cursor = myDB.query(TABLE_NAME,TABLE_COLUMNS,"id_reunion ="+id_reunion,null,null,null,null);
        return cursorToArray(cursor);
    }

    public Subject getById(int id){
        Cursor cursor = myDB.query(TABLE_NAME,null,"id="+id,null,null,null,null);
        return cursorToSubject(cursor);
    }
    public void deleteById(int id){
        myDB.delete(TABLE_NAME,"id="+id,null);
    }

    private ArrayList<Subject> cursorToArray(Cursor cursor) {
        ArrayList<Subject> subjects = new ArrayList();
        if(cursor.getCount() == 0) return subjects;
        cursor.moveToFirst();
        do{
            subjects.add(new Subject(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3)));
        }while(cursor.moveToNext());
        return subjects;
    }
    private Subject cursorToSubject(Cursor cursor){
        Subject subject = new Subject();
        if(cursor.getCount() == 0) return subject;
        cursor.moveToFirst();
        do{
            subject = new Subject(cursor.getInt(0),cursor.getString(1),cursor.getInt(2),cursor.getInt(3));
        }while(cursor.moveToNext());
        return subject;
    }

    public void update(Subject subject){
        ContentValues contentValues = new ContentValues();
        contentValues.put("duree", subject.getDuree());
        myDB.update(TABLE_NAME,contentValues,"id="+subject.getId(),null);
    }
}
