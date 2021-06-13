package com.example.miniprojet.Entities;

public class Personne {
    private int id;
    private String fullName;
    private int id_reunion;
    private String role;
    private int duree;

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Personne(int id, String fullName, String role, int id_reunion) {
        this.id = id;
        this.fullName = fullName;
        this.id_reunion = id_reunion;
        this.role = role;
    }

    public Personne() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getId_reunion() {
        return id_reunion;
    }

    public void setId_reunion(int id_reunion) {
        this.id_reunion = id_reunion;
    }
}
