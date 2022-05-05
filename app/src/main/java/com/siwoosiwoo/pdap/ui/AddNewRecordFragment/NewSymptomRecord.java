package com.siwoosiwoo.pdap.ui.AddNewRecordFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.siwoosiwoo.pdap.R;
import com.siwoosiwoo.pdap.dao.MedicalDatabase;
import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;
import com.siwoosiwoo.pdap.dao.Record;
import com.siwoosiwoo.pdap.dao.RecordDao;
import com.siwoosiwoo.pdap.dao.Symptom;
import com.siwoosiwoo.pdap.dao.SymptomDao;

import java.util.ArrayList;
import java.util.List;


public class NewSymptomRecord extends Fragment {
    LinearLayout linearMain;
    CheckBox checkBox;
    private MedicalDatabase mdb; //룸db를 선언할 데이터 베이스 선언
    private SymptomDao symptomDao;//이 자바 파일에서는 patient 정보를 사용해야 하므로 선언
    private int patientIDInt;


    View fragmentView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_new_symptom_record, container, false);
        Log.d("patientId", "onCreateView");
        linearMain = fragmentView.findViewById(R.id.checkbox_list);

        Bundle bundle = getArguments(); // RecordActivity에서 전달한 번들을 저장 patientID(ChartNum)이 저장되어있댜
        if(bundle != null){
            Log.d("patientId","번들에 값 있다.");
        }else{
            Log.d("patientId","번들에 값 없다.");
        }
        String patientID = bundle.getString("patientId");
        Log.d("patientId",patientID+"3");
        patientIDInt = Integer.parseInt(patientID);

        mdb = Room.databaseBuilder(getActivity(), MedicalDatabase.class, "Medical.db")  //프래그먼트에서는 getApplicationContext()를 사용하면 오류가 떠서 getActivity()를 사용해야함
                //.createFromAsset("PDAP.db")
                .allowMainThreadQueries()
                .build();

        symptomDao = mdb.symptomDao();
        mdb.close();

        List<Symptom> symptomsList = symptomDao.getAll();//환자가 가지고있는 레코드 정보를 여기 저장함

        for (int i =0; i<symptomsList.size();i++){

//            symptom.put(symptomsList.get(i).id,symptomsList.get(i).name);

            checkBox = new CheckBox(getActivity());
            checkBox.setId(symptomsList.get(i).id);
            checkBox.setText(symptomsList.get(i).name);
            linearMain.addView(checkBox);
        }
        return fragmentView;
    }

}