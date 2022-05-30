package com.siwoosiwoo.pdap.ui.patientRecordFragment;

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
import android.widget.TextView;

import com.siwoosiwoo.pdap.R;
import com.siwoosiwoo.pdap.dao.Disease;
import com.siwoosiwoo.pdap.dao.MedicalDatabase;
import com.siwoosiwoo.pdap.dao.DiseaseDao;
import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;
import com.siwoosiwoo.pdap.dao.Record;
import com.siwoosiwoo.pdap.dao.RecordDao;
import com.siwoosiwoo.pdap.dao.SymptomDao;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Memo_Fragment extends Fragment {

    private PatientDatabase pdb;
    private PatientDao patientDao;//이 자바 파일에서는 patient 정보를 사용해야 하므로 선언
    private RecordDao recordDao;

    private EditText editText;
    private ScrollView scrollView;
    private int patientIDInt;

    private String record;
    private int recordInt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View fragment = inflater.inflate(R.layout.fragment_memo_record, container, false);
        editText = fragment.findViewById(R.id.memo_edit);
        scrollView = fragment.findViewById(R.id.scrollView);
        Bundle bundle = getArguments(); // RecordActivity에서 전달한 번들을 저장 patientID(ChartNum)이 저장되어있댜
        if(bundle != null){
            Log.d("patientId_Memo","번들에 값 있다.");
        }else{
            Log.d("patientId_Memo","번들에 값 없다.");
        }
        String patientID = bundle.getString("patientId_Memo");
        Log.d("patientId_Memo",patientID+"3");
        patientIDInt = Integer.parseInt(patientID);

        pdb = Room.databaseBuilder(getActivity(), PatientDatabase.class, "Patient.db")  //프래그먼트에서는 getApplicationContext()를 사용하면 오류가 떠서 getActivity()를 사용해야함
                //.createFromAsset("PDAP.db")
                .allowMainThreadQueries()
                .build();

        patientDao = pdb.patientDao();
        recordDao = pdb.recordDao();

        Patient patient = patientDao.findPatient(patientIDInt); //현재 선택된 Patient의 정보를 저장했음.
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



        return fragment;
//            Bundle bundle = getArguments();
//            Log.d("Id",bundle+"\t 번들 받았습니다.");
//        if(bundle==null){
//            Log.d("Id",bundle+"\t 번들에 값이 없습니다.");
//            View fragment = inflater.inflate(R.layout.fragment_memo_record, container, false);
//            textView = fragment.findViewById(R.id.memo_edit);
//            textView.setText("기록을 선택하시면 여기 뜹니다.");
//            return fragment;
//        }else {
//            Log.d("Id",bundle+"\t 번들에 값이 있습니다.");
//            int recordId = bundle.getInt("recordID");
//
//
//            mdb = Room.databaseBuilder(getActivity(), MedicalDatabase.class, "Medical.db")  //프래그먼트에서는 getApplicationContext()를 사용하면 오류가 떠서 getActivity()를 사용해야함
//                    //.createFromAsset("PDAP.db")
//                    .allowMainThreadQueries()
//                    .build();
//
//            pdb = Room.databaseBuilder(getActivity(), PatientDatabase.class, "Patient.db")  //프래그먼트에서는 getApplicationContext()를 사용하면 오류가 떠서 getActivity()를 사용해야함
//                    //.createFromAsset("PDAP.db")
//                    .allowMainThreadQueries()
//                    .build();
//
//            recordDao = pdb.recordDao();//다오 선언
//            diseaseDao = mdb.diseaseDao();
//
//            Record record = recordDao.findRecord(recordId);//레코드에 선택된 레코드 아이디값을 저장
//            ArrayList<String> symptomsIdsString_record = record.symptomIds;//레코드에서 symptomIds만 빼내서 저장
////        int[] symptomIds = new int[symptomsIdsString_record.size()];
////
////        for(int i=0;i<symptomsIdsString_record.size();i++){
////            symptomIds[i] = Integer.parseInt(symptomsIdsString_record.get(i));//symtompIds를 int값으로 변환해서 저장
////        }
//
//            List<Disease> diseases = diseaseDao.getAll();//모든 disease의 값들을 저장하는 리스트 생성
//
//            ArrayList<String> symptomsIdString_disease;//diseases에서 symtpmsIds만 저장할 변수 생성
////        ArrayList<Integer> symptomsIdInt = new ArrayList<Integer>();//symptomsIdString에서 Integer로 변환할 변수 생성
//
//            Map<Integer, Integer> resultDesease = new HashMap<Integer, Integer>();//결과적으로 출력할 질병 해쉬맵 생성
//            //왼쪽 인트는 해당하는 질병저장 오른쪽은 우선순위 저장
//            int count = 0;//몇개나 겹치는게 있는지 카운트
//
//
//            for (int i = 0; i < diseases.size(); i++) {
//                symptomsIdString_disease = diseases.get(i).symptomIds;//현재 disease저장
////            for(int j=0;j<symptomsIdString.size();j++){
////                symptomsIdInt.add(Integer.parseInt(symptomsIdString.get(j)));//int로 변환
////            }
//                for (int j = 0; j < symptomsIdsString_record.size(); j++) {
//                    for (int k = 0; k < symptomsIdString_disease.size(); k++) {
//                        symptomsIdsString_record.get(j).equals(symptomsIdString_disease.get(k));
//                        count++;
//                    }
//                }
//                if (count > 0) {
//                    resultDesease.put(i, count);
//                }
//            }
//
//            Disease disease;
//            View fragment = inflater.inflate(R.layout.fragment_memo_record, container, false);
//            textView = fragment.findViewById(R.id.memo_edit);
//
//            textView.setText(record.description);//텍스트뷰에 디스크립션 추가
//            textView.append("\n");
//            for (int key : resultDesease.keySet()) {
//                disease = diseaseDao.findDisease(key);
//                textView.append(disease.name);
//                textView.append("우선순위는 " + resultDesease.get(key) + " 입니다.");
//                textView.append("\n");
//                textView.append(disease.description);
//            }
//            return fragment;
//        }
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