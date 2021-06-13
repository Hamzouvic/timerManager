package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.miniprojet.Entities.Personne;
import com.example.miniprojet.Entities.Subject;
import com.example.miniprojet.dal.DBConnection;
import com.example.miniprojet.dal.PersonneAdapter;
import com.example.miniprojet.dal.SubjectAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class ReunionRoom extends AppCompatActivity {
    private int id;
    private DBConnection dbConnection;
    private ArrayList<Personne> personnes;
    private ArrayList<Subject> subjects;

    private TextView chrono;
    private ListView subjects_id;
    private ListView personnes_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_room);
        id = getIntent().getExtras().getInt("id");
        dbConnection = new DBConnection(this);
        PersonneAdapter personneAdapter = new PersonneAdapter(dbConnection.getWritableDatabase());
        SubjectAdapter subjectAdapter = new SubjectAdapter(dbConnection.getWritableDatabase());
        personnes = personneAdapter.getPersonnes(id);
        subjects = subjectAdapter.getSubjects(id);
        chrono = findViewById(R.id.chrono);
        subjects_id = findViewById(R.id.sujets_id);
        personnes_id = findViewById(R.id.personne_id);
        ArrayAdapter<String> sujets = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,sujetToTitle(subjects));
        ArrayAdapter<String>  participants = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,personneToTitle(personnes));
        subjects_id.setAdapter(sujets);
        personnes_id.setAdapter(participants);
        subjects_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.BLUE);
                Toast.makeText(ReunionRoom.this, "hello", Toast.LENGTH_SHORT).show();
            }
        });
        personnes_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                view.setBackgroundColor(Color.BLUE);
                Toast.makeText(ReunionRoom.this, "hello too", Toast.LENGTH_SHORT).show();
                new Timer(chrono).start();
            }
        });
    }

    private ArrayList<String> personneToTitle(ArrayList<Personne> personnesArrayList) {
        ArrayList<String> titles = new ArrayList<>();
        if (personnesArrayList.isEmpty()) return titles;
        for (Personne r :
                personnesArrayList) {
            titles.add(r.getFullName());
        }
        return titles;
    }
    private ArrayList<String> sujetToTitle(ArrayList<Subject> subjectsArrayList) {
        ArrayList<String> titles = new ArrayList<>();
        if (subjectsArrayList.isEmpty()) return titles;
        for (Subject r :
                subjectsArrayList) {
            titles.add(r.getTitle());
        }
        return titles;
    }
}

class Timer extends Thread{
    private TextView chrono;

    public Timer(TextView chrono){
        this.chrono = chrono;
    }
    @Override
    public void run(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:MM:SS");
        int heure =0;
        int minute = 0;
        int second= 0;
        String currentTime = "";
        while(true){
            while(minute <60){
                while (second < 60) {
                    second++;
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    currentTime = String.format("%02d:%02d:%02d",heure, minute,second);
                    chrono.setText(currentTime);
                }
                minute++;
                second=0;
            }
            minute =0;
            heure++;
        }
    }
}
