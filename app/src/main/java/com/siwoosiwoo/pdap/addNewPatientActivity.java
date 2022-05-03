package com.siwoosiwoo.pdap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;

import java.sql.Date;

import java.util.ArrayList;
import java.util.Calendar;

public class addNewPatientActivity extends AppCompatActivity {

    private static final String TAG = "addNewPatientActivity";
    private String date;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button confirmButton;
    private RadioGroup sexRadioGroup;
    private RadioButton maleRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);
        mDisplayDate = (TextView) findViewById(R.id.BirthDate);
        confirmButton = findViewById(R.id.OKbutton);
        sexRadioGroup = findViewById(R.id.SexRadioGroup);
        maleRadio = findViewById(R.id.maleRadio);
        PatientDatabase pdb = Room.databaseBuilder(getApplicationContext(), PatientDatabase.class, "Patient.db")
                .allowMainThreadQueries()
                .build();

        PatientDao patientDao = pdb.patientDao();


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(addNewPatientActivity.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });
        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
//                Log.d(TAG, "onDateSet : YYYY-MM-DD: "+year+"-"+month+"-"+day);
                String strDate = year+"-"+month+"-"+day;
                date = strDate;
                mDisplayDate.setText(strDate);
            }
        };

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editPersonName = findViewById(R.id.editPersonName);
                EditText editChartNumber = findViewById(R.id.editChartNumber);

                //여기서 환자정보를 DB에 저장하면됨
                Patient newPatient = new Patient();

                newPatient.id = Integer.parseInt(editChartNumber.getText().toString());
                newPatient.name = editPersonName.getText().toString();
                newPatient.birthDate = date;

                int selectedId = sexRadioGroup.getCheckedRadioButtonId();
                if(maleRadio.getId() == selectedId) {
                    newPatient.sex = "m";
                } else {
                    newPatient.sex = "f";
                }

                newPatient.recordIds=new ArrayList<>();
                patientDao.insertAll(newPatient);


                pdb.close();
                Intent intent2 = new Intent(addNewPatientActivity.this, CheckSymptomActivity.class);
                intent2.putExtra("patientId",Integer.toString(newPatient.id));
                setResult(200);
                startActivity(intent2);
            }
        });

    }
    

    @Override
    protected void onStop() {
        setResult(200);
        finish();
        super.onStop();
    }
}