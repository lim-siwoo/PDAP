package com.siwoosiwoo.pdap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.siwoosiwoo.pdap.dao.MedicalDatabase;
import com.siwoosiwoo.pdap.dao.DiseaseDao;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.RecordDao;
import com.siwoosiwoo.pdap.dao.SymptomDao;
import com.siwoosiwoo.pdap.ui.patientRecordFragment.List_Fragment;

public class RecordActivitiy extends AppCompatActivity {

    String patientId;
    List_Fragment fragment1;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_new_record, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.new_record:
                Toast.makeText(this, "새로운 기록을 추가합니다.", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent( RecordActivitiy.this, CheckSymptomActivity.class);
                intent2.putExtra("patientId", patientId);
                startActivity(intent2);
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptom_layout);

        Intent intent = getIntent();
        patientId = intent.getStringExtra("patientId");//PatientsListActivity에서 해당하는 환자정보의 차트번호를 가져옴
        Log.d("patientId",patientId+"2");

        //fragment1 = new List_Fragment();  //fragment1이 List_Fragment이다
        fragment1 = (List_Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);

        //getSupportFragmentManager().beginTransaction().add(R.id.fragment1,fragment1).commit();//요걸로 선언하니까 번들값을 액티비티에서 프래그먼트로 넘겨주었을때 프래그먼트가 못받음 54번째 줄로 선언해주기
        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.add(R.id.fragment1,fragment1).commit();

        Bundle bundle = new Bundle();
        bundle.putString("patientId", patientId);//해당하는 환자정보의 차트번호를 리스트에 띄울 List_Fragment
        if(bundle != null){
            Log.d("patientId","번들에 값 있다.");
        }else{
            Log.d("patientId","번들에 값 없다.");
        }

        fragment1.setArguments(bundle);
    }
}