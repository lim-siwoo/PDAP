package com.siwoosiwoo.pdap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.siwoosiwoo.pdap.dao.AppDatabase;
import com.siwoosiwoo.pdap.dao.Converters;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Sample.db")
                //.createFromAsset("PDAP.db")
                //.addTypeConverter(Converters.class)
                .allowMainThreadQueries()
                .build();

        PatientDao patientDao = db.patientDao();

        Patient test = new Patient();
        test.id = 19;
        test.name = "hello";
        test.birthDate = "1999-05-15";
        test.sex = "m";

        ArrayList<String> str = new ArrayList<>();
        str.add("123");
        str.add("23425");
        test.recordIds = str;
        patientDao.insertAll(test);

        List<Patient> darr = patientDao.getAll();

        Log.d("test12", darr.get(0).birthDate);

        Intent intent = new Intent(MainActivity.this , PatientsListActivity.class);
        startActivity(intent);
    }

}