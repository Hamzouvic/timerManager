package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojet.Entities.Reunion;
import com.example.miniprojet.dal.DBConnection;
import com.example.miniprojet.dal.ReunionAdapter;

public class CreerReunion extends AppCompatActivity {
    DBConnection dbConnection;
    private EditText title;
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
        Reunion reunion = new Reunion();
        String titre = title.getText().toString();
        if(titre.trim().compareTo("") == 0) {
            Toast.makeText(this, "Entrer un titre stp", Toast.LENGTH_SHORT).show();
            return;
        }
        reunion.setTitle(titre);
        ReunionAdapter reunionAdapter = new ReunionAdapter(dbConnection.getWritableDatabase());
        reunionAdapter.insertReunion(reunion);
    }
}
