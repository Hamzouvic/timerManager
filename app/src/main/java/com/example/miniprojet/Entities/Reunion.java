package com.example.miniprojet.Entities;

import java.io.Serializable;

public class Reunion implements Serializable {
    private int id;
    private String title;
    private String date;

    public Reunion(int id, String title,String date){
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Reunion(){

    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
