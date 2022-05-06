package com.siwoosiwoo.pdap;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;

import java.util.ArrayList;
import java.util.Calendar;

public class AddNewPatientActivity extends AppCompatActivity {

    private String date;
    private TextView mDisplayDate;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private RadioGroup sexRadioGroup;
    private RadioButton maleRadio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);

        getSupportActionBar().hide();
        mDisplayDate = (TextView) findViewById(R.id.BirthDate);
        Button confirmButton = findViewById(R.id.OKbutton);
        sexRadioGroup = findViewById(R.id.SexRadioGroup);
        maleRadio = findViewById(R.id.maleRadio);
        PatientDatabase pdb = Room.databaseBuilder(getApplicationContext(), PatientDatabase.class, "Patient.db")
                .allowMainThreadQueries()
                .build();

        PatientDao patientDao = pdb.patientDao();


        mDisplayDate.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH);
            int day = cal.get(Calendar.DAY_OF_MONTH);

            DatePickerDialog dialog = new DatePickerDialog(AddNewPatientActivity.this,
                    android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                    mDateSetListener,
                    year,month,day);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
        });
        mDateSetListener = (datePicker, year, month, day) -> {
//                Log.d(TAG, "onDateSet : YYYY-MM-DD: "+year+"-"+month+"-"+day);
            String strDate = year+"-"+month+"-"+day;
            date = strDate;
            mDisplayDate.setText(strDate);
        };

        confirmButton.setOnClickListener(view -> {
            EditText editPersonName = findViewById(R.id.editPersonName);
            EditText editChartNumber = findViewById(R.id.editChartNumber);

            //여기서 환자정보를 DB에 저장하면됨
            Patient newPatient = new Patient();

            newPatient.id = Integer.parseInt(editChartNumber.getText().toString());
            newPatient.name = editPersonName.getText().toString();
            newPatient.birthDate = date;

            int selectedId = sexRadioGroup.getCheckedRadioButtonId();
            if(maleRadio.getId() == selectedId) {
                newPatient.sex = "M";
            } else {
                newPatient.sex = "F";
            }

            newPatient.recordIds=new ArrayList<>();
            patientDao.insertAll(newPatient);


            pdb.close();
            Intent intent2 = new Intent(AddNewPatientActivity.this, AddNewRecordActivity.class);
            intent2.putExtra("patientId",Integer.toString(newPatient.id));
            setResult(200);
            startActivity(intent2);
        });

    }
    

    @Override
    protected void onStop() {
        setResult(200);
        finish();
        super.onStop();
    }
}