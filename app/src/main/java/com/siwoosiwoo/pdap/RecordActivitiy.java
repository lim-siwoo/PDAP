package com.siwoosiwoo.pdap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.siwoosiwoo.pdap.ui.patientRecordFragment.List_Fragment;

public class RecordActivitiy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptom_layout);

        List_Fragment fragment1 = new List_Fragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment1,fragment1).commit();
        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.add(R.id.fragment1,fragment1).commit();
    }
}