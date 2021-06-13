package com.example.miniprojet.Entities;

public class Subject {
    private int id;
    private String title;
    private int id_reunion;
    int duree;
    public Subject(){

    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public Subject(int id, String title, int id_reunion) {
        this.id = id;
        this.title = title;
        this.id_reunion = id_reunion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId_reunion() {
        return id_reunion;
    }

    public void setId_reunion(int id_reunion) {
        this.id_reunion = id_reunion;
    }
}
