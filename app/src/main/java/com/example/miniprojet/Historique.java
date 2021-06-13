package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.miniprojet.Entities.Reunion;
import com.example.miniprojet.dal.DBConnection;
import com.example.miniprojet.dal.ReunionAdapter;

import java.util.ArrayList;

public class Historique extends AppCompatActivity {
    private DBConnection dbConnection;

    private ArrayAdapter<Reunion> reunionArrayAdapter;
    private ListView listView;
    private ArrayList<Reunion> reunionArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historique);
        listView = findViewById(R.id.listview);
        dbConnection = new DBConnection(this);
        ReunionAdapter reunionAdapter = new ReunionAdapter(dbConnection.getWritableDatabase());
        reunionArrayList = reunionAdapter.getReunions();
        reunionArrayAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,objectToTitle(reunionArrayList));
        listView.setAdapter(reunionArrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Reunion reunion = reunionArrayList.get(position);
                Intent i;
                i = new Intent(getBaseContext(), ReunionDetails.class);
                i.putExtra("id",reunion.getId());
                startActivity(i);
            }
        });
    }

    private ArrayList<String> objectToTitle(ArrayList<Reunion> reunionArrayList) {
        ArrayList<String> titles = new ArrayList<>();
        if (reunionArrayList.isEmpty()) return titles;
        for (Reunion r :
                reunionArrayList) {
            titles.add(r.getTitle()+"\nDate :"+r.getDate());
        }
        return titles;
    }
}
