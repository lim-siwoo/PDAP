package com.siwoosiwoo.pdap;

import android.app.ActionBar;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
    private RadioButton femaleRadio;
    private PatientDao patientDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);

        getSupportActionBar().hide();
        mDisplayDate = (TextView) findViewById(R.id.BirthDate);
        Button confirmButton = findViewById(R.id.OKbutton);
        sexRadioGroup = findViewById(R.id.SexRadioGroup);
        maleRadio = findViewById(R.id.maleRadio);
        femaleRadio = findViewById(R.id.femaleRadio);

        PatientDatabase pdb = Room.databaseBuilder(getApplicationContext(), PatientDatabase.class, "Patient.db")
                .allowMainThreadQueries()
                .build();

        patientDao = pdb.patientDao();


        mDisplayDate.setOnClickListener(view -> {
            Calendar cal = Calendar.getInstance();
            int year = cal.get(Calendar.YEAR);
            int month = cal.get(Calendar.MONTH) + 1;
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
            String strDate = year+"-"+(month + 1 )+"-"+day;
            date = strDate;
            mDisplayDate.setText(strDate);
        };

        confirmButton.setOnClickListener(view -> {
            EditText editPersonName = findViewById(R.id.editPersonName);
            EditText editChartNumber = findViewById(R.id.editChartNumber);

            //여기서 환자정보를 DB에 저장하면됨
            Patient newPatient = new Patient();

            if(editChartNumber.getText().toString().isEmpty()) {
                newPatient.id = -1;
            } else {
                newPatient.id = Integer.parseInt(editChartNumber.getText().toString());
            }
            newPatient.name = editPersonName.getText().toString();
            newPatient.birthDate = date;
            int selectedId = sexRadioGroup.getCheckedRadioButtonId();
            if(maleRadio.getId() == selectedId) {
                newPatient.sex = "M";
            } else if(femaleRadio.getId() == selectedId) {
                newPatient.sex = "F";
            } else {
                newPatient.sex = "N";
            }

            newPatient.recordIds=new ArrayList<>();

            if(patientDao.findPatient(newPatient.id) != null) {
                Toast.makeText(getApplicationContext(),
                        "이미 중복된 차트번호가 있습니다.",
                        Toast.LENGTH_SHORT).show();
            } else if(newPatient.id == -1 || newPatient.name.isEmpty() || newPatient.sex.equals("N")) {
                Toast.makeText(getApplicationContext(),
                        "환자 정보를 모두 입력해주세요.",
                        Toast.LENGTH_SHORT).show();
            } else {
                patientDao.insertAll(newPatient);
                Intent intent2 = new Intent(AddNewPatientActivity.this, AddNewRecordActivity.class);
                intent2.putExtra("patientId",Integer.toString(newPatient.id));
                startActivity(intent2);
                finish();
            }
            pdb.close();
        });

    }

}