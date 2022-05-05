package com.siwoosiwoo.pdap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.siwoosiwoo.pdap.dao.Disease;
import com.siwoosiwoo.pdap.dao.DiseaseDao;
import com.siwoosiwoo.pdap.dao.MedicalDatabase;

import com.siwoosiwoo.pdap.dao.Symptom;
import com.siwoosiwoo.pdap.dao.SymptomDao;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.goToPatientList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PatientsListActivity.class);
                startActivity(intent);
            }
        });

        MedicalDatabase mdb = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "Medical.db")
                .createFromAsset("Medical.db")
                .allowMainThreadQueries()
                .build();


        SymptomDao symptomDao = mdb.symptomDao();//여기서부터 Symptom 넣음

        List<Symptom> getTest = symptomDao.getAll();

        for(int i = 0; i < getTest.size(); i++) {
            Log.d("test12", getTest.get(i).name);
        }


        DiseaseDao diseaseDao = mdb.diseaseDao();

        List<Disease> getTest2 = diseaseDao.getAll();
        Log.d("test12", getTest2.get(0).name);

        mdb.close();

        Intent intent = new Intent(getApplicationContext(), PatientsListActivity.class);
        startActivity(intent);
    }


}