package com.example.miniprojet.Entities;

import java.io.Serializable;
import java.util.ArrayList;

public class Reunion{
    private int id;
    private String title;
    private String date;
    private int duree;

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Reunion(int id, String title, String date){
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public Reunion(int id, String title, String date, int duree) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.duree = duree;
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
