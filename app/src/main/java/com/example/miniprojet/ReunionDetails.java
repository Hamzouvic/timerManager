package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.miniprojet.Entities.Personne;
import com.example.miniprojet.Entities.Reunion;
import com.example.miniprojet.Entities.Subject;
import com.example.miniprojet.dal.DBConnection;
import com.example.miniprojet.dal.PersonneAdapter;
import com.example.miniprojet.dal.ReunionAdapter;
import com.example.miniprojet.dal.SubjectAdapter;

import java.util.ArrayList;

public class ReunionDetails extends AppCompatActivity {
    private DBConnection dbConnection;
    private TextView title;
    private TextView duree;
    private ListView sujets;
    private ListView participants;
    int id ;
    ReunionAdapter reunionAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_details);
        title = findViewById(R.id.reuniontitle);
        duree = findViewById(R.id.reuniondate);
        sujets = findViewById(R.id.reunionsujets);
        participants = findViewById(R.id.reunionparticipant);
        id =(int)getIntent().getExtras().get("id");
        dbConnection = new DBConnection(this);
        reunionAdapter = new ReunionAdapter(dbConnection.getWritableDatabase());
        Reunion reunion = reunionAdapter.getById(id);
        title.setText(reunion.getTitle()+"");
        duree.setText(reunion.getDuree()+"");
        /*
        after creating adapter for both subjects and participants
         */
        PersonneAdapter personneAdapter = new PersonneAdapter(dbConnection.getWritableDatabase());
        ArrayList<Personne> personnes = personneAdapter.getPersonnes(id);
        ArrayAdapter<String> participantsAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,personneToTitle(personnes));
        participants.setAdapter(participantsAdapter);
        SubjectAdapter subjectAdapter = new SubjectAdapter(dbConnection.getWritableDatabase());
        ArrayList<Subject> subjects = subjectAdapter.getSubjects(id);
        ArrayAdapter<String> sujetListAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,sujetToTitle(subjects));
        sujets.setAdapter(sujetListAdapter);

    }
    private ArrayList<String> personneToTitle(ArrayList<Personne> personnesArrayList) {
        ArrayList<String> titles = new ArrayList<>();
        if (personnesArrayList.isEmpty()) return titles;
        for (Personne r :
                personnesArrayList) {
            titles.add(String.format(r.getFullName()+"\ntime:%d min",r.getDuree()));
        }
        return titles;
    }
    private ArrayList<String> sujetToTitle(ArrayList<Subject> subjectsArrayList) {
        ArrayList<String> titles = new ArrayList<>();
        if (subjectsArrayList.isEmpty()) return titles;
        for (Subject r :
                subjectsArrayList) {
            titles.add(String.format(r.getTitle()+"\ntime: %d min",r.getDuree()));
        }
        return titles;
    }
}
