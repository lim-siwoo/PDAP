package com.siwoosiwoo.pdap.ui.patientRecordFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.siwoosiwoo.pdap.R;
import com.siwoosiwoo.pdap.dao.MedicalDatabase;
import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;
import com.siwoosiwoo.pdap.dao.Record;
import com.siwoosiwoo.pdap.dao.RecordDao;

import java.util.ArrayList;
import java.util.List;


public class List_Fragment extends Fragment {

    private MedicalDatabase mdb; //룸db를 선언할 데이터 베이스 선언
    private PatientDatabase pdb;
    private PatientDao patientDao;//이 자바 파일에서는 patient 정보를 사용해야 하므로 선언
    private RecordDao recordDao;
    private int patientIDInt;


    View fragmentView;
    ListView listView;

    ArrayList<String> recordToScreen = new ArrayList<>();//화면에 띄울 리스트 선언
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_list_record, container, false);
        listView = fragmentView.findViewById(R.id.listView_item);

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

        pdb = Room.databaseBuilder(getActivity(), PatientDatabase.class, "Patient.db")  //프래그먼트에서는 getApplicationContext()를 사용하면 오류가 떠서 getActivity()를 사용해야함
                //.createFromAsset("PDAP.db")
                .allowMainThreadQueries()
                .build();

        patientDao = pdb.patientDao();

        Patient patient = patientDao.findPatient(patientIDInt); //현재 선택된 Patient의 정보를 저장했음.
        ArrayList<String> records = patient.recordIds;//환자가 가지고있는 레코드 정보를 여기 저장함
        ArrayList<Integer> recordsInt = new ArrayList<>();  //해당 환자의 기록들을 찾기 위해 recordID를 ArrayList를 저장함

        for(int i=0;i<records.size();i++){
            recordsInt.add(Integer.parseInt(records.get(i)));////해당 환자의 기록들을 찾기 위해 recordID를 ArrayList를 저장함
        }

        recordDao = pdb.recordDao();
        Record record;
        List<Record> records2 = new ArrayList<>();  //환자의 Record정보들을 저장할 Record List선언
        for(int i=0;i<recordsInt.size();i++){
            record = recordDao.findRecord(recordsInt.get(i));
            records2.add(record); //환자의 recordsIds기반으로 Record 정보를 List<Record> records2에 저장함.
        }

        for(int i=0;i<records2.size();i++){
            int id = records2.get(i).id;
            String recordDate = records2.get(i).recordDate;
            String description = records2.get(i).description;
            String toScreen = id +" / " + recordDate+" / " + description;
            Log.d("patientId",toScreen+"toScreen");
            recordToScreen.add(toScreen);
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,recordToScreen);

        listView.setAdapter(adapter);
        return fragmentView;
    }
}