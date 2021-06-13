package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miniprojet.Entities.Personne;
import com.example.miniprojet.Entities.Reunion;
import com.example.miniprojet.Entities.Subject;
import com.example.miniprojet.dal.DBConnection;
import com.example.miniprojet.dal.PersonneAdapter;
import com.example.miniprojet.dal.ReunionAdapter;
import com.example.miniprojet.dal.SubjectAdapter;

public class CreerReunion extends AppCompatActivity {
    DBConnection dbConnection;
    Reunion reunion;
    private EditText title;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creer_reunion);
        getSupportActionBar().setTitle("Créer une reunion:");
        dbConnection = new DBConnection(this);
        title = findViewById(R.id.title);
    }

    public void ajouterPersonne(View view) {
        Intent i = new Intent(this, AjouterPersonne.class);
        startActivity(i);
    }

    public void ajouterSujet(View view) {
        Toast.makeText(this, "open", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this, AjouterSujets.class);
        startActivity(i);
    }

    public void startReunion(View v){
        reunion = new Reunion();
        String titre = title.getText().toString();
        if(titre.trim().compareTo("") == 0) {
            Toast.makeText(this, "Entrer un titre stp", Toast.LENGTH_SHORT).show();
            return;
        }
        reunion.setTitle(titre);
        insertIntoDB();
        Toast.makeText(this, "ajouter", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(this,ReunionRoom.class);
        i.putExtra("id",id);
        startActivity(i);
    }
    private void insertIntoDB(){
        ReunionAdapter reunionAdapter = new ReunionAdapter(dbConnection.getWritableDatabase());
        SubjectAdapter subjectAdapter = new SubjectAdapter(dbConnection.getWritableDatabase());
        PersonneAdapter personneAdapter = new PersonneAdapter(dbConnection.getWritableDatabase());
        id= Integer.parseInt(String.valueOf(reunionAdapter.insertReunion(reunion)));
        for (Subject subject:
             AjouterSujets.subjects) {
            subject.setId_reunion(id);
            subjectAdapter.insertSubject(subject);
        }
        Log.d("static - -- ",AjouterPersonne.personnes.toString());
        for (Personne personne : AjouterPersonne.personnes){
            Log.d("personne ::: ",personne+"");
            personne.setId_reunion(id);
            personneAdapter.insertPersonne(personne);
        }
        AjouterPersonne.personnes.clear();
        AjouterSujets.subjects.clear();
    }
}
