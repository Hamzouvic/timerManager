package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.miniprojet.Entities.Reunion;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
    }

    public void creerReunion(View v){
        Intent i = new Intent(this, CreerReunion.class);
        startActivity(i);
    }

    public void historique(View v){
        Intent i = new Intent(this,Historique.class);
        startActivity(i);
    }
}
