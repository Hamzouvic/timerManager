package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.miniprojet.Entities.Subject;
import com.example.miniprojet.dal.DBConnection;
import com.example.miniprojet.dal.SubjectAdapter;

import java.util.ArrayList;

public class AjouterSujets extends AppCompatActivity {
    public static ArrayList<Subject> subjects = new ArrayList<>();
    private EditText title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_sujets);
        title = findViewById(R.id.title_subject);
    }

    public void ajouterSujet(View v){
        Subject subject = new Subject();
        String titre = title.getText().toString();
        if(titre.trim().compareTo("") == 0) return;
        subject.setTitle(titre);
        subjects.add(subject);
        Toast.makeText(this, "Sujet ajouté", Toast.LENGTH_SHORT).show();
    }
}
