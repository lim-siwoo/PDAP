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
import com.siwoosiwoo.pdap.dao.Symptom;
import com.siwoosiwoo.pdap.dao.SymptomDao;

import org.w3c.dom.Text;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        SymptomDao symptomDao;
//        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Sample.db")
//                .createFromAsset("Symptom.db")
//                .allowMainThreadQueries()
//                //.addTypeConverter(Converters.class)
//                .build();
//
//        symptomDao = db.symptomDao();
//
//        List<Symptom> Symptoms = symptomDao.getAll();
//
//        for(int i=0;i<Symptoms.size();i++) {
//            String name = Symptoms.get(i).name;
//            Log.d("ㅎㅇㅎㅇ", name);
//        }

        Intent intent = new Intent(MainActivity.this , PatientsListActivity.class);
        startActivity(intent);
    }

}