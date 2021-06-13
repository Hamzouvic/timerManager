package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miniprojet.Entities.Personne;
import com.example.miniprojet.Entities.Subject;

import java.util.ArrayList;

public class AjouterPersonne extends AppCompatActivity {

    public static ArrayList<Personne> personnes = new ArrayList<>();
    private EditText rolePersonne;
    private EditText name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_personne);
        getSupportActionBar().setTitle("Ajouter une personne");
        rolePersonne = findViewById(R.id.role);
        name = findViewById(R.id.nom);
    }

    public void ajouterPersonne(View view) {
        Personne personne = new Personne();
        String nom = name.getText().toString();
        String role = rolePersonne.getText().toString();
        if(nom.trim().compareTo("") == 0) return;
        if(role.trim().compareTo("") == 0) return;
        personne.setFullName(nom);
        personne.setRole(role);
        personnes.add(personne);
        Toast.makeText(this, "personne ajoutée", Toast.LENGTH_SHORT).show();
    }
}
