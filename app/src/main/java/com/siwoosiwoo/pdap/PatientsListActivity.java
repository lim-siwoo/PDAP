package com.siwoosiwoo.pdap;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;

import java.util.ArrayList;
import java.util.List;


public class PatientsListActivity extends AppCompatActivity {

    private List<String> list;          // 데이터를 넣은 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;
    private AlertDialog.Builder alert;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_patient_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.addPatient:
                Intent intent = new Intent(PatientsListActivity.this, AddNewPatientActivity.class);
                startActivity(intent);
                break;
            case R.id.deletePatient:
                alert.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        settingList();
        adapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        settingList();
        adapter.notifyDataSetChanged();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);
        setAlert();

        editSearch = findViewById(R.id.editSearch);


        // 검색을 보여줄 리스트변수
        ListView listView = findViewById(R.id.listView);

        // 리스트를 생성한다.
        list = new ArrayList<>();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();


        // 리스트에 연동될 아답터를 생성한다.
        adapter = new SearchAdapter(list, this);

        // 리스트뷰에 아답터를 연결한다.
        listView.setAdapter(adapter);

        // input창에 검색어를 입력시 "addTextChangedListener" 이벤트 리스너를 정의한다.
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                // input창에 문자를 입력할때마다 호출된다.
                // search 메소드를 호출한다.
                String text = editSearch.getText().toString();
                search(text);
            }
        });
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent2 = new Intent(PatientsListActivity.this, RecordActivitiy.class);
            String chartNumber = list.get(i);
            String[] split = chartNumber.split(" ");

            intent2.putExtra("patientId", split[0]);

            startActivity(intent2);
        });

    }


    // 검색을 수행하는 메소드
    public void search(String charText) {

        // 문자 입력시마다 리스트를 지우고 새로 뿌려준다.
        list.clear();

        // 문자 입력이 없을때는 모든 데이터를 보여준다.
        if (charText.length() == 0) {
            list.addAll(arraylist);
        }
        // 문자 입력을 할때..
        else {
            // 리스트의 모든 데이터를 검색한다.
            for (int i = 0; i < arraylist.size(); i++) {
                // arraylist의 모든 데이터에 입력받은 단어(charText)가 포함되어 있으면 true를 반환한다.
                if (arraylist.get(i).toLowerCase().contains(charText)) {
                    // 검색된 데이터를 리스트에 추가한다.
                    list.add(arraylist.get(i));
                }
            }
        }
        // 리스트 데이터가 변경되었으므로 아답터를 갱신하여 검색된 데이터를 화면에 보여준다.
        adapter.notifyDataSetChanged();
    }

    // 검색에 사용될 데이터를 리스트에 추가한다.
    private void settingList() {
        //룸pdb를 선언할 데이터 베이스 선언
        PatientDatabase pdb = Room.databaseBuilder(getApplicationContext(), PatientDatabase.class, "Patient.db")
                .allowMainThreadQueries()
                .build();
        //이 자바 파일에서는 patient 정보를 사용해야 하므로 선언
        PatientDao patientDao = pdb.patientDao();
        List<Patient> patients= patientDao.getAll();
        pdb.close();

//        Log.d("test12", "settingList called");

        list.clear();
        for(int i=0;i<patients.size();i++){
            Patient patient = patients.get(i);
            int id = patient.id;
            String name = patient.name;
            String birthDate = patient.birthDate;
            String sex = patient.sex;

            String addList = id + " / " + name+ " / " + birthDate+ " / " + sex;

//            Log.d("text12", addList);
            list.add(addList);
        }

        arraylist.clear();
        arraylist.addAll(list);
    }

    private void setAlert(){
        alert = new AlertDialog.Builder(this);

        alert.setTitle("환자 삭제");
        alert.setMessage("삭제할 환자의 차트번호를 입력해주세요.");

        final EditText name = new EditText(this);
        name.setHint("ChartNumber");
        alert.setView(name);

        alert.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String deleteChartNumber = name.getText().toString();
                //삭제
                deletePatient(deleteChartNumber);
                setAlert();
            }
        });
        alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                setAlert();
            }
        });
    }

    private void deletePatient(String patientId) {
        PatientDatabase pdb = Room.databaseBuilder(getApplicationContext(), PatientDatabase.class, "Patient.db")
                .allowMainThreadQueries()
                .build();
        PatientDao patientDao = pdb.patientDao();
        Patient patient = patientDao.findPatient(Integer.parseInt(patientId));
        //있는지 확인을 해야함.
        if (patient != null){
            patientDao.delete(patient);
            settingList();
            adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(getApplicationContext(),
                    "차트번호가 잘못됬습니다.",
                    Toast.LENGTH_SHORT).show();
        }
        pdb.close();
    }
}