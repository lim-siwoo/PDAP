package com.siwoosiwoo.pdap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class PatientsListActivity extends AppCompatActivity {


    private List<String> list;          // 데이터를 넣은 리스트변수
    private ListView listView;          // 검색을 보여줄 리스트변수
    private EditText editSearch;        // 검색어를 입력할 Input 창
    private SearchAdapter adapter;      // 리스트뷰에 연결할 아답터
    private ArrayList<String> arraylist;

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
                Intent intent = new Intent(PatientsListActivity.this, addNewPatientActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patients_list);

        editSearch = findViewById(R.id.editSearch);
        listView = findViewById(R.id.listView);

        // 리스트를 생성한다.
        list = new ArrayList<>();

        // 검색에 사용할 데이터을 미리 저장한다.
        settingList();

        // 리스트의 모든 데이터를 arraylist에 복사한다.// list 복사본을 만든다.
        arraylist = new ArrayList<>();
        arraylist.addAll(list);

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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent2 = new Intent(PatientsListActivity.this, RecordActivitiy.class);
                startActivity(intent2);
            }
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
        list.add("0001/채수빈/여/20000101");
        list.add("0002/박지현/여/20000101");
        list.add("0003/수지/여/20000101");
        list.add("0004/남태현/남/20000101");
        list.add("0005/하성운/여/20000101");
        list.add("0006/크리스탈/여/20000101");
        list.add("0007/강승윤/남/20000101");
        list.add("0008/손나은/여/20000101");
        list.add("0009/남주혁/남/20000101");
        list.add("0010/루이/남/20000101");
        list.add("0011/진영/남/20000101");
        list.add("0012/슬기/남/20000101");
        list.add("0013/이해인/남/20000101");
        list.add("0014/고원희/남/20000101");
        list.add("0015/설리/남/20000101");
        list.add("0016/공명/남/20000101");
        list.add("0017/김예림/남/20000101");
        list.add("0018/혜리/남/20000101");
        list.add("0019/웬디/남/20000101");
        list.add("박혜수/남/20000101");
        list.add("카이/남/20000101");
        list.add("진세연");
        list.add("동호");
        list.add("박세완");
        list.add("도희");
        list.add("창모");
        list.add("허영지");
    }
}