package com.siwoosiwoo.pdap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.siwoosiwoo.pdap.dao.MedicalDatabase;
import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;
import com.siwoosiwoo.pdap.dao.Record;
import com.siwoosiwoo.pdap.dao.RecordDao;
import com.siwoosiwoo.pdap.dao.Symptom;
import com.siwoosiwoo.pdap.dao.SymptomDao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CheckSymptomActivity extends AppCompatActivity {

    LinearLayout linearMain;
    CheckBox checkBox;
    private String patientId;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.save:
                Toast.makeText(this, "체크리스트를 저장합니다.", Toast.LENGTH_SHORT).show();

                //여기서 DB에 저장해야함
                MedicalDatabase mdb = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "Medical.db")
                        .createFromAsset("Medical.db")
                        .allowMainThreadQueries()
                        .build();

                SymptomDao symptomDao = mdb.symptomDao();

                List<Symptom> symptomsList = symptomDao.getAll();
                mdb.close();
                ArrayList<String> symptomIds = new ArrayList<>();

                for (int i =0; i<symptomsList.size();i++){
                    CheckBox tempCheckBox = findViewById(symptomsList.get(i).id);

                    if (tempCheckBox.isChecked()){
                        symptomIds.add(Integer.toString(symptomsList.get(i).id));
                    }
                }
                Record record = new Record();
                long now = System.currentTimeMillis();
                Date date = new Date(now);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                record.recordDate = sdf.format(date);
                record.symptomIds = symptomIds;

                PatientDatabase pdb = Room.databaseBuilder(getApplicationContext(), PatientDatabase.class, "Patient.db")
                        .allowMainThreadQueries()
                        .build();

                RecordDao recordDao = pdb.recordDao();
                recordDao.insertAll(record);
                String TAG = "logRecordList";
                List<Record> logRecordList = recordDao.getAll();    //밑에 로그는 삭제해도 되는데 얘는 안됨
                Log.d(TAG, "DB inserted record: "+logRecordList.get(0).recordDate);
                Log.d(TAG, "DB inserted record: "+logRecordList.get(0).symptomIds.get(0));


                PatientDao patientDao = pdb.patientDao();
                Patient patient = patientDao.findPatient(Integer.parseInt(patientId));
                ArrayList<String> recordsIds = patient.recordIds;

                recordsIds.add(Integer.toString(logRecordList.get(logRecordList.size()-1).id));

                patient.recordIds = recordsIds;
                patientDao.updateAll(patient);
//
//                Patient patient = patientDao.findPatient(patientIDInt); //현재 선택된 Patient의 정보를 저장했음.
//                ArrayList<String> records = patient.recordIds;//환자가 가지고있는 레코드 정보를 여기 저장함


                pdb.close();//DB닫아줌



                Intent intent = new Intent(CheckSymptomActivity.this, RecordActivitiy.class);
                intent.putExtra("patientId", Integer.toString(patient.id));
                startActivity(intent);

                //intent intent = new Intent(CheckSymptomActivity.this,RecordActivitiy.class);
                //startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_symptom);
        linearMain = (LinearLayout) findViewById(R.id.checkbox_layout);

        Intent receiveIntent = getIntent();
        patientId = receiveIntent.getStringExtra("patientId");




        /**
         * create linked hash map for store item you can get value from database
         * or server also
         */
//        LinkedHashMap<Integer, String> symptom = new LinkedHashMap<Integer, String>();//밑에처럼 동적추가 가능함

        MedicalDatabase mdb = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "Medical.db")
                .allowMainThreadQueries()
                .build();

        SymptomDao symptomDao = mdb.symptomDao();

        List<Symptom> symptomsList = symptomDao.getAll();

        mdb.close();

        for (int i =0; i<symptomsList.size();i++){

//            symptom.put(symptomsList.get(i).id,symptomsList.get(i).name);

            checkBox = new CheckBox(this);
            checkBox.setId(symptomsList.get(i).id);
            checkBox.setText(symptomsList.get(i).name);
            checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
            linearMain.addView(checkBox);
        }

/*        Set<?> set = symptom.entrySet();
        // Get an iterator
        Iterator<?> i = set.iterator();
        // Display elements
        while (i.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());
            checkBox = new CheckBox(this);
            checkBox.setId(Integer.parseInt(me.getKey().toString()));
            checkBox.setText(me.getValue().toString());
            checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
            linearMain.addView(checkBox);
        }*/
    }
    View.OnClickListener getOnClickDoSomething(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("*************id******" + button.getId());
                System.out.println("and text***" + button.getText().toString());
            }
        };
    }

}