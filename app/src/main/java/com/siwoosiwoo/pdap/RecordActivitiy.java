package com.siwoosiwoo.pdap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.siwoosiwoo.pdap.dao.Disease;
import com.siwoosiwoo.pdap.dao.MedicalDatabase;
import com.siwoosiwoo.pdap.dao.DiseaseDao;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;
import com.siwoosiwoo.pdap.dao.Record;
import com.siwoosiwoo.pdap.dao.RecordDao;
import com.siwoosiwoo.pdap.dao.SymptomDao;
import com.siwoosiwoo.pdap.ui.patientRecordFragment.List_Fragment;
import com.siwoosiwoo.pdap.ui.patientRecordFragment.Memo_Fragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordActivitiy extends AppCompatActivity implements List_Fragment.OnList_FragmentSelectedListner{

    String patientId;
    List_Fragment fragment1;
    Memo_Fragment fragment2;

    private PatientDatabase pdb;
    private MedicalDatabase mdb; //룸db를 선언할 데이터 베이스 선언
    private RecordDao recordDao;
    private DiseaseDao diseaseDao;

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
                Intent intent2 = new Intent( RecordActivitiy.this, AddNewRecordActivity.class);
                intent2.putExtra("patientId", patientId);
                startActivity(intent2);
                finish();
                break;
            case android.R.id.home:
                //뒤로가기버튼 눌렀을때
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptom_layout);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        patientId = intent.getStringExtra("patientId");//PatientsListActivity에서 해당하는 환자정보의 차트번호를 가져옴
        Log.d("patientId",patientId+"2");

        //fragment1 = new List_Fragment();  //fragment1이 List_Fragment이다
        fragment1 = (List_Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment1);
        fragment2 = (Memo_Fragment) getSupportFragmentManager().findFragmentById(R.id.fragment2);
        //getSupportFragmentManager().beginTransaction().add(R.id.fragment1,fragment1).commit();//요걸로 선언하니까 번들값을 액티비티에서 프래그먼트로 넘겨주었을때 프래그먼트가 못받음 54번째 줄로 선언해주기
        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.add(R.id.fragment1,fragment1).commit();

        mdb = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "Medical.db")  //프래그먼트에서는 getApplicationContext()를 사용하면 오류가 떠서 getActivity()를 사용해야함
                //.createFromAsset("PDAP.db")
                .allowMainThreadQueries()
                .build();

        pdb = Room.databaseBuilder(getApplicationContext(), PatientDatabase.class, "Patient.db")  //프래그먼트에서는 getApplicationContext()를 사용하면 오류가 떠서 getActivity()를 사용해야함
                //.createFromAsset("PDAP.db")
                .allowMainThreadQueries()
                .build();

        recordDao = pdb.recordDao();//다오 선언
        diseaseDao = mdb.diseaseDao();


        Bundle bundle = new Bundle();
        bundle.putString("patientId", patientId);//해당하는 환자정보의 차트번호를 리스트에 띄울 List_Fragment
        if(bundle != null){
            Log.d("patientId","번들에 값 있다.");
        }else{
            Log.d("patientId","번들에 값 없다.");
        }

        fragment1.setArguments(bundle);
    }

    @Override
    public void onAttachFragment(Fragment fragment){
        if(fragment instanceof List_Fragment){
            List_Fragment list_fragment = (List_Fragment) fragment;
            list_fragment.setOnList_FragmentSelectedListner(this);
        }
    }

    @Override
    public void onArticleSelected(int position) {
        Log.d("Id",position+"\t 잘 받았습니다.");

        //int recordId = bundle.getInt("recordID");
        Record record = recordDao.findRecord(position);//레코드에 선택된 레코드 아이디값을 저장
        ArrayList<String> symptomsIdsString_record = record.symptomIds;//레코드의 증상들의 값을 저장
//        int[] symptomIds = new int[symptomsIdsString_record.size()];
//
//        for(int i=0;i<symptomsIdsString_record.size();i++){
//            symptomIds[i] = Integer.parseInt(symptomsIdsString_record.get(i));//symtompIds를 int값으로 변환해서 저장
//        }

        List<Disease> diseases = diseaseDao.getAll();//모든 disease의 값들을 저장하는 리스트 생성

        ArrayList<String> symptomsIdString_disease;//diseases에서 symtpmsIds만 저장할 변수 생성
//        ArrayList<Integer> symptomsIdInt = new ArrayList<Integer>();//symptomsIdString에서 Integer로 변환할 변수 생성

        Map<Integer, Integer> resultDesease = new HashMap<Integer, Integer>();//결과적으로 출력할 질병 해쉬맵 생성
        //왼쪽 인트는 해당하는 질병저장 오른쪽은 우선순위 저장
        int count = 0;//몇개나 겹치는게 있는지 카운트


        for (int i = 0; i < diseases.size(); i++) {
            symptomsIdString_disease = diseases.get(i).symptomIds;//현재 disease가 가지고 있는 증상들의 값을 저장
//            for(int j=0;j<symptomsIdString.size();j++){
//                symptomsIdInt.add(Integer.parseInt(symptomsIdString.get(j)));//int로 변환
//            }
            for (int j = 0; j < symptomsIdsString_record.size(); j++) {//disease가 가지고 있는 증상들의 값만큼
                for (int k = 0; k < symptomsIdString_disease.size(); k++) {//
                    if(symptomsIdsString_record.get(j).equals(symptomsIdString_disease.get(k))) {
                        count++;
                    }
                }
            }
            if (count > 0) {
                resultDesease.put(i, count);
            }
            count=0;
        }

        Disease disease;
        //View fragment = inflater.inflate(R.layout.fragment_memo_record, container, false);
        //fragment2.textView = fragment.findViewById(R.id.memo_edit);
        fragment2.initScrollView();
        fragment2.setText("");
        //Log.d("Id",)
        if(record.description!=null) {
            fragment2.setText(record.description);//텍스트뷰에 디스크립션 추가
            fragment2.appendText("\n\n");
        }
        for (int key : resultDesease.keySet()) {
            disease = diseaseDao.findDisease(key);
            fragment2.appendText(disease.name);
            fragment2.appendText("\n");
            fragment2.appendText("가중치 " + resultDesease.get(key) + " 입니다.");
            fragment2.appendText("\n");
            fragment2.appendText(disease.description);
            fragment2.appendText("\n\n");
        }
        Log.d("Id", position + "\t 보냈습니다.");
    }
}