package com.example.health;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

public class Clinics extends AppCompatActivity {
    ListView listView;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinics);
        btn= (Button) findViewById(R.id.buttonback);
        listView= (ListView) findViewById(R.id.list);
        ArrayList<Clinic> arrayList= new ArrayList<>();
        Clinic c1 = new Clinic("Rexdale","Dr. John","Heart Specialist");
        Clinic c2 = new Clinic("Appletree","Dr. Emma smith","Skin Specialist");
        Clinic c3 = new Clinic("Smile Makers","Dr. Rohit Sharma","Dentist");
        Clinic c4 = new Clinic("Vital Urgent Care","Dr. Shszia Malik","Accidental care");
        Clinic c5 = new Clinic("Oakdale","Dr. Harwinder","Heart Specialist");
        Clinic c6 = new Clinic("A+ Medical Inc.","Dr. Kemi","All services");
        arrayList.add(c1);
        arrayList.add(c2);
        arrayList.add(c3);
        arrayList.add(c4);
        arrayList.add(c5);
        arrayList.add(c6);

        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), homeActivity.class);
                startActivity(intent);
            }
        });
    }
}