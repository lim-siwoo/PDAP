package com.siwoosiwoo.pdap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.siwoosiwoo.pdap.dao.MedicalDatabase;
import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;
import com.siwoosiwoo.pdap.dao.Record;
import com.siwoosiwoo.pdap.dao.RecordDao;
import com.siwoosiwoo.pdap.dao.Symptom;
import com.siwoosiwoo.pdap.dao.SymptomDao;
import com.siwoosiwoo.pdap.ui.AddNewRecordFragment.NewMemoRecord;
import com.siwoosiwoo.pdap.ui.AddNewRecordFragment.NewSymptomRecord;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EditRecordActivity extends AppCompatActivity {
//
//    String patientId;
//    NewSymptomRecord fragment_new_symptom_record;
//    private int patientIDInt;
//    private String recordInfo;
//    private int recordInt;
//    private Record trueRecord;
//
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.save_menu, menu);
//        return super.onCreateOptionsMenu(menu);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
//        int curId = item.getItemId();
//        switch (curId){
//            case R.id.save:
//                fragment_new_symptom_record = (NewSymptomRecord) getSupportFragmentManager().findFragmentById(R.id.fragment1);
//                //fragment_new_memo_record = (NewMemoRecord) getSupportFragmentManager().findFragmentById(R.id.fragment2);
//                //여기서 DB에 저장해야함
//                MedicalDatabase mdb = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "Medical.db")
//                        .createFromAsset("Medical.db")
//                        .allowMainThreadQueries()
//                        .build();
//
//                SymptomDao symptomDao = mdb.symptomDao();
//
//                List<Symptom> symptomsList = symptomDao.getAll();
//                mdb.close();
//
//                ArrayList<Integer> checkedIds = fragment_new_symptom_record.getCheckedIds();
//                ArrayList<String> symptomIds = new ArrayList<>();
//
//                for (int i =0; i < checkedIds.size();i++){
//                    symptomIds.add(Integer.toString(checkedIds.get(i)));
//                }
//                Record record = new Record();
//                long now = System.currentTimeMillis();
//                Date date = new Date(now);
//                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                record.recordDate = sdf.format(date);
//                record.symptomIds = symptomIds;
//
////                EditText memo = (EditText) fragment_new_memo_record.getView().findViewById(R.id.memo_record);
////                record.description = memo.getText().toString();
//
//                PatientDatabase pdb = Room.databaseBuilder(getApplicationContext(), PatientDatabase.class, "Patient.db")
//                        .allowMainThreadQueries()
//                        .build();
//
//                PatientDao patientDao = pdb.patientDao();
//                RecordDao recordDao = pdb.recordDao();
//
//
//
//                Patient patient = patientDao.findPatient(patientIDInt); //현재 선택된 Patient의 정보를 저장했음.
//                recordInfo = patient.recordIds.get(0);//환자가 가지고있는 레코드 정보를 여기 저장함
//                recordInt = Integer.parseInt(recordInfo);  //해당 환자의 기록들을 찾기 위해 recordID를 Int로 변환후 저장함
//
//                trueRecord = recordDao.findRecord(recordInt);    //해당 환자의 record 기록 가져옴
//
//                recordDao.insertAll(record);
//                String TAG = "logRecordList";
//                List<Record> logRecordList = recordDao.getAll();    //밑에 로그는 삭제해도 되는데 얘는 안됨
//                Log.d(TAG, "DB inserted record: "+logRecordList.get(0).recordDate);
//                //Log.d(TAG, "DB inserted record: "+logRecordList.get(0).symptomIds.get(0));
//
//
//
//                Patient patient = patientDao.findPatient(Integer.parseInt(patientId));
//                ArrayList<String> recordsIds = patient.recordIds;
//
//                recordsIds.add(Integer.toString(logRecordList.get(logRecordList.size()-1).id));
//
//                patient.recordIds = recordsIds;
//                patientDao.updateAll(patient);
//
//                pdb.close();//DB닫아줌
//
//                Intent intent = new Intent(EditRecordActivity.this, RecordActivitiy.class);
//                intent.putExtra("patientId", Integer.toString(patient.id));
//                startActivity(intent);
//                finish();
//                break;
//        }
//        return super.onOptionsItemSelected(item);
//    }
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_edit_record);
//
//        Intent intent = getIntent();
//        patientId = intent.getStringExtra("patientId");//PatientsListActivity에서 해당하는 환자정보의 차트번호를 가져옴
//        Log.d("patientId",patientId+"2");
//
//        patientIDInt = Integer.parseInt(patientId);
//
//        fragment_new_symptom_record = (NewSymptomRecord) getSupportFragmentManager().findFragmentById(R.id.fragment1);
//
//        Bundle bundle = new Bundle();
//        bundle.putString("patientId", patientId);
//
//        fragment_new_symptom_record.setArguments(bundle);
//    }
}