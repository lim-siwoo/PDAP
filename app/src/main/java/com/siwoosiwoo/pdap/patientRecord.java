package com.siwoosiwoo.pdap;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class patientRecord extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patient_record_screen);
    }
}
