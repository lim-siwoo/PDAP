package com.siwoosiwoo.pdap.ui.AddNewRecordFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ScrollView;

import com.siwoosiwoo.pdap.R;
import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;
import com.siwoosiwoo.pdap.dao.Record;
import com.siwoosiwoo.pdap.dao.RecordDao;

public class NewMemoRecord extends Fragment {
    private PatientDatabase pdb;
    private PatientDao patientDao;//이 자바 파일에서는 patient 정보를 사용해야 하므로 선언
    private RecordDao recordDao;

    private EditText editText;
    private ScrollView scrollView;
    private int patientIDInt;

    private String record;
    private int recordInt;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_new_memo_record, container, false);

        editText = fragment.findViewById(R.id.memo_record);
        scrollView = fragment.findViewById(R.id.scrollView);
        Bundle bundle = getArguments(); // RecordActivity에서 전달한 번들을 저장 patientID(ChartNum)이 저장되어있댜
        if(bundle != null){
            Log.d("patientId_Memo","번들에 값 있다.");
        }else{
            Log.d("patientId_Memo","번들에 값 없다.");
        }
        String patientID = bundle.getString("patientId");
        Log.d("patientId_Memo",patientID+"3");
        patientIDInt = Integer.parseInt(patientID);

        pdb = Room.databaseBuilder(getActivity(), PatientDatabase.class, "Patient.db")  //프래그먼트에서는 getApplicationContext()를 사용하면 오류가 떠서 getActivity()를 사용해야함
                //.createFromAsset("PDAP.db")
                .allowMainThreadQueries()
                .build();

        patientDao = pdb.patientDao();
        recordDao = pdb.recordDao();

        Patient patient = patientDao.findPatient(patientIDInt); //현재 선택된 Patient의 정보를 저장했음.

        if(!patient.recordIds.isEmpty()) { // 환자 레코드가 있을때만 editText 세팅하도록 if문 추가
            record = patient.recordIds.get(0);//환자가 가지고있는 레코드 정보를 여기 저장함
            recordInt = Integer.parseInt(record);  //해당 환자의 기록들을 찾기 위해 recordID를 Int로 변환후 저장함

            Record record = recordDao.findRecord(recordInt);
            setText(record.description);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    recordDao.appendDescription(editText.getText().toString(), recordInt);
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }


        return fragment;
    }

    public void setText(String s) {
        if(editText!=null){
            editText.setText(s);
        }else{
            Log.d("Id","textView가 널인데요??");
        }
    }

    public void appendText(String s){
        editText.append(s);
    }

    public void initScrollView(){
        scrollView.scrollTo(0,0);
    }
}