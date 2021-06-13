package com.example.miniprojet;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.miniprojet.Entities.Reunion;
import com.example.miniprojet.dal.DBConnection;
import com.example.miniprojet.dal.ReunionAdapter;

public class ReunionDetails extends AppCompatActivity {
    private DBConnection dbConnection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_details);
        TextView tv = findViewById(R.id.textView4);
        int id = (int)getIntent().getExtras().get("id");
        dbConnection = new DBConnection(this);
        ReunionAdapter reunionAdapter = new ReunionAdapter(dbConnection.getWritableDatabase());
        Reunion reunion = reunionAdapter.getById(id);
        tv.setText(reunion.toString());
    }

}
