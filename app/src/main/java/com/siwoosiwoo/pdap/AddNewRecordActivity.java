package com.siwoosiwoo.pdap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;
import com.siwoosiwoo.pdap.dao.Record;
import com.siwoosiwoo.pdap.dao.RecordDao;
import com.siwoosiwoo.pdap.ui.AddNewRecordFragment.NewSymptomRecord;
import com.siwoosiwoo.pdap.ui.patientRecordFragment.Memo_Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AddNewRecordActivity extends AppCompatActivity {

    String patientId;
    NewSymptomRecord fragment_new_symptom_record;
    Memo_Fragment fragment_new_memo_record;
    private String recordInfo;
    private int recordInt;
    private Record record;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

/*뒤로가기눌렀을때 환자 삭제해버림*/
    @Override
    public void onBackPressed() {
        PatientDatabase pdb = Room.databaseBuilder(getApplicationContext(), PatientDatabase.class, "Patient.db")
                .allowMainThreadQueries()
                .build();
        PatientDao patientDao = pdb.patientDao();
        Patient patient = patientDao.findPatient(Integer.parseInt(patientId));
        if (patient.recordIds.isEmpty()){ // 환자 레코드가 있을떄랑 없을떄로 구분
            patientDao.delete(patient);
            pdb.close();
            super.onBackPressed();
        } else { // 레코드가 있으면 삭제하지 않고 환자 레코드 엑티비티로 전환
            Intent intent2 = new Intent(AddNewRecordActivity.this, RecordActivitiy.class);

            intent2.putExtra("patientId", patientId);

            startActivity(intent2);
            finish();
        }
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.save:
                //여기서 DB에 저장해야함

                PatientDatabase pdb = Room.databaseBuilder(getApplicationContext(), PatientDatabase.class, "Patient.db")
                        .allowMainThreadQueries()
                        .build();

                RecordDao recordDao = pdb.recordDao();
                PatientDao patientDao = pdb.patientDao();

                Patient patient = patientDao.findPatient(Integer.parseInt(patientId));


                // 체크된 체크리스트 호출
                ArrayList<Integer> checkedIds = fragment_new_symptom_record.getCheckedIds();
                ArrayList<String> symptomIds = new ArrayList<>();

                for (int i =0; i < checkedIds.size();i++){
                    symptomIds.add(Integer.toString(checkedIds.get(i)));
                }
                Log.d("test12", "patien.recordIds.size() : " + patient.recordIds.size());
                // 저장된 레코드가 없을때
                if (patient.recordIds.isEmpty()){
                    record = new Record();
                    long now = System.currentTimeMillis();
                    Date date = new Date(now);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    record.recordDate = sdf.format(date);
                    record.symptomIds = symptomIds;

                    EditText memo = (EditText) fragment_new_memo_record.getView().findViewById(R.id.memo_edit);

                    record.description = memo.getText().toString();
                    Long logRecord = recordDao.insert(record);
                    Log.d("test12", "new record inserted");

                    ArrayList<String> recordsIds = new ArrayList<String>();
                    recordsIds.add(String.valueOf(logRecord));
                    Log.d("test12", recordsIds.get(0));
                    patient.recordIds = recordsIds;
                    patientDao.updateAll(patient);
                }else{ // 저장된 레코드가 있을때
                    recordInfo = patient.recordIds.get(0);//환자가 가지고있는 레코드 정보를 여기 저장함
                    recordInt = Integer.parseInt(recordInfo);  //해당 환자의 기록들을 찾기 위해 recordID를 Int로 변환후 저장함

                    record = recordDao.findRecord(recordInt);    //해당 환자의 record 기록 가져옴
                    record.symptomIds = symptomIds;

                    recordDao.updateAll(record);
                }



                pdb.close();//DB닫아줌

                Intent intent = new Intent(AddNewRecordActivity.this, RecordActivitiy.class);
                intent.putExtra("patientId", Integer.toString(patient.id));
                startActivity(intent);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_record);

        Intent intent = getIntent();
        patientId = intent.getStringExtra("patientId");//PatientsListActivity에서 해당하는 환자정보의 차트번호를 가져옴
        Log.d("patientId",patientId+"2");

        fragment_new_symptom_record = (NewSymptomRecord) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        fragment_new_memo_record = (Memo_Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);

        Bundle bundle = new Bundle();
        bundle.putString("patientId", patientId);

        fragment_new_symptom_record.setArguments(bundle);
        fragment_new_memo_record.setArguments(bundle);
    }
}