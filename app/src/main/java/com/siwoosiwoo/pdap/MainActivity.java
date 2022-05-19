package com.siwoosiwoo.pdap;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.siwoosiwoo.pdap.dao.Disease;
import com.siwoosiwoo.pdap.dao.DiseaseDao;
import com.siwoosiwoo.pdap.dao.MedicalDatabase;

import com.siwoosiwoo.pdap.dao.Symptom;
import com.siwoosiwoo.pdap.dao.SymptomDao;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView text;
    int time_out = 1500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = (TextView) findViewById(R.id.text1);

        //Action Bar 제거
        ActionBar bar = getSupportActionBar();
        bar.hide();

        //animation 추가
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.intro_anim);
        text.startAnimation(anim);



/*        button = findViewById(R.id.goToPatientList);
        button.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), PatientsListActivity.class);
            startActivity(intent);
        });*/

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

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), PatientsListActivity.class);
                startActivity(intent);
                finish();
            }
        }, time_out);


/*        Intent intent = new Intent(getApplicationContext(), PatientsListActivity.class);
        startActivity(intent);*/
    }


}