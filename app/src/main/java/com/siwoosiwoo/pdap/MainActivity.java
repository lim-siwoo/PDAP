package com.siwoosiwoo.pdap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.siwoosiwoo.pdap.dao.Converters;
import com.siwoosiwoo.pdap.dao.Disease;
import com.siwoosiwoo.pdap.dao.DiseaseDao;
import com.siwoosiwoo.pdap.dao.MedicalDatabase;
import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.Symptom;
import com.siwoosiwoo.pdap.dao.SymptomDao;

import org.w3c.dom.Text;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
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

        MedicalDatabase db = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "Medical.db")
                .createFromAsset("Medical.db")
                .allowMainThreadQueries()
                .build();


//        SymptomDao symptomDao = db.symptomDao();//여기서부터 Symptom 넣음
//
//        List<Symptom> getTest = symptomDao.getAll();
//
//        for(int i = 0; i < getTest.size(); i++) {
//            Log.d("test12", getTest.get(i).name);
//        }
//
//
//        DiseaseDao diseaseDao = db.diseaseDao();
//
//        List<Disease> getTest2 = diseaseDao.getAll();
//        Log.d("test12", getTest2.get(0).name);

        db.close();

        Intent intent = new Intent(getApplicationContext(), PatientsListActivity.class);
        startActivity(intent);
    }


}