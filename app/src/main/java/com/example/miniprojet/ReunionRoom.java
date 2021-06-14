package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.miniprojet.Entities.Reunion;
import com.example.miniprojet.Entities.Subject;
import com.example.miniprojet.dal.DBConnection;
import com.example.miniprojet.dal.PersonneAdapter;
import com.example.miniprojet.dal.ReunionAdapter;
import com.example.miniprojet.dal.SubjectAdapter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ReunionRoom extends AppCompatActivity {
    private int id;
    private DBConnection dbConnection;
    private ArrayList<Personne> personnes;
    private ArrayList<Subject> subjects;

    private TextView chrono;
    private ListView subjects_id;
    private ListView personnes_id;

    private static boolean start = false;
    private static int active_member = -1;
    private static int active_subject = -1;
    private static int active_member_time = 0;
    private static int active_subject_time = 0;
    PersonneAdapter personneAdapter;
    SubjectAdapter subjectAdapter;
    private Timer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_room);
        id = getIntent().getExtras().getInt("id");
        dbConnection = new DBConnection(this);
        personneAdapter = new PersonneAdapter(dbConnection.getWritableDatabase());
        subjectAdapter = new SubjectAdapter(dbConnection.getWritableDatabase());
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
                if(active_subject == position) return;
                view.setBackgroundColor(Color.BLUE);
                String values[] = chrono.getText().toString().split(":");
                int now = Integer.parseInt(values[1]) + (Integer.parseInt(values[0]) * 60) + (Integer.parseInt(values[2])/ 60);

                view.setBackgroundColor(Color.GREEN);
                if(active_subject != -1){
                    View v = subjects_id.getChildAt(active_subject);
                    v.setBackgroundColor(Color.WHITE);
                    Subject s = subjects.get(active_subject);
                    s.setDuree(now - active_subject_time);
                    subjectAdapter.update(s);
                }
                active_subject = position;
                active_subject_time = now;
            }
        });
        personnes_id.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(active_member == position) return;
                String values[] = chrono.getText().toString().split(":");
                int now = Integer.parseInt(values[1]) + (Integer.parseInt(values[0]) * 60) + (Integer.parseInt(values[2])/ 60);

                view.setBackgroundColor(Color.GREEN);
                if(active_member != -1){
                    View v = personnes_id.getChildAt(active_member);
                    v.setBackgroundColor(Color.WHITE);
                    Personne p = personnes.get(active_member);
                    p.setDuree(now - active_member_time);
                    personneAdapter.update(p);
                }
                active_member = position;
                active_member_time = now;

                if(!start){
                    timer = new Timer(chrono);
                    timer.start();
                    start = true;
                }
            }
        });
        getSupportActionBar().hide();
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

    public void arreter(View v){
        String values[] = chrono.getText().toString().split(":");
        int now = Integer.parseInt(values[1]) + (Integer.parseInt(values[0]) * 60) + (Integer.parseInt(values[2])/ 60);
        Personne p = personnes.get(active_member);
        p.setDuree(now - active_member_time);
        personneAdapter.update(p);
        if(active_subject != -1) {
            Subject s = subjects.get(active_subject);
            s.setDuree(now - active_subject_time);
            subjectAdapter.update(s);
        }
        Reunion reunion = new Reunion();
        reunion.setId(id);
        reunion.setDuree(now);
        ReunionAdapter reunionAdapter = new ReunionAdapter(dbConnection.getWritableDatabase());
        reunionAdapter.update(reunion);
        start = false;
        timer.interrupt();
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
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
