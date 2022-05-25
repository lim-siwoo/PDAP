package com.siwoosiwoo.pdap.ui.AddNewRecordFragment;

import android.content.Context;
import android.graphics.PathEffect;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.siwoosiwoo.pdap.SymptomExpandableListViewAdapter;
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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;


public class NewSymptomRecord extends Fragment {
    ExpandableListView expandableListView;
    SymptomExpandableListViewAdapter expandableListAdapter;
    List<String> expandableListTitle;
    LinkedHashMap<String, List<Symptom>> expandableListDetail;

    CheckBox checkBox;
    private MedicalDatabase mdb; //룸db를 선언할 데이터 베이스 선언
    private PatientDatabase pdb;
    private PatientDao patientDao;
    private RecordDao recordDao;
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

        List<Symptom> symptomsList = symptomDao.getAll();//환자가 가지고있는 레코드 정보를 여기 저장함
//        List<String> symptomsName = new ArrayList<String>();

//        for (int i = 0; i < symptomsList.size(); i++) {
//            symptomsName.add(symptomsList.get(i).name);
//            Log.d("test12", "test12" + symptomsName.get(i));
//        }

//        ExpandableListDataPump test = new ExpandableListDataPump();

        mdb.close();
        expandableListView = fragmentView.findViewById(R.id.expandableListView);
//        expandableListDetail = test.getData();

        expandableListDetail = new LinkedHashMap<String, List<Symptom>>();

//        Log.d("test12", "testtest: " + symptomsName.subList(0, 3).get(0));

        expandableListDetail.put("Cervical arterial dysfunction & Upper cervical ligamentous insufficiency", symptomsList.subList(0, 3));
        expandableListDetail.put("Cervical Myelopathy", symptomsList.subList(3, 6));
        expandableListDetail.put("Heart attack", symptomsList.subList(6, 9));
        expandableListDetail.put("Pancoast Tumor", symptomsList.subList(9, 12));
        expandableListDetail.put("MOI(Mechanism of injury)", symptomsList.subList(12, 16));
        expandableListDetail.put("24 hours patterns", symptomsList.subList(16, 20));
        expandableListDetail.put("Aggravating factor", symptomsList.subList(20, 24));
        expandableListDetail.put("Easing factor", symptomsList.subList(24, 25));
        expandableListDetail.put("Others", symptomsList.subList(25, 29));


        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

        pdb = Room.databaseBuilder(getActivity(), PatientDatabase.class, "Patient.db")  //프래그먼트에서는 getApplicationContext()를 사용하면 오류가 떠서 getActivity()를 사용해야함
                //.createFromAsset("PDAP.db")
                .allowMainThreadQueries()
                .build();

        patientDao = pdb.patientDao();
        recordDao = pdb.recordDao();
        Patient patient = patientDao.findPatient(patientIDInt);
        Record record = null;
        if(patient.recordIds.size() > 0) {
            record = recordDao.findRecord(Integer.parseInt(patient.recordIds.get(0)));
        }

        ArrayList<Integer> recordIdsInt = new ArrayList<Integer>();

        if(record != null) {
            for (String id : record.symptomIds) {
                recordIdsInt.add(Integer.parseInt(id));
            }
        }

        expandableListAdapter = new SymptomExpandableListViewAdapter(getActivity(), expandableListTitle, expandableListDetail, recordIdsInt);

        expandableListView.setAdapter(expandableListAdapter);

        for(int i=0; i < expandableListAdapter.getGroupCount(); i++) {
            expandableListView.expandGroup(i);
        }
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Expanded.",
                        Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(getActivity(),
                        expandableListTitle.get(groupPosition) + " List Collapsed.",
                        Toast.LENGTH_SHORT).show();

            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                Toast.makeText(
                        getActivity(),
                        expandableListTitle.get(groupPosition)
                                + " -> "
                                + expandableListDetail.get(
                                expandableListTitle.get(groupPosition)).get(
                                childPosition).name, Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

//        for (int i =0; i<symptomsList.size();i++){
//            checkBox = new CheckBox(getActivity());
//            checkBox.setId(symptomsList.get(i).id);
//            checkBox.setText(symptomsList.get(i).name);
//            expandableListView.addView(checkBox);
//        }
        return fragmentView;
    }



    public ArrayList<Integer> getCheckedIds() {
        return expandableListAdapter.getCheckedIds();
    }

    class ExpandableListDataPump {
        public HashMap<String, List<String>> getData() {
            HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

            List<String> cricket = new ArrayList<String>();
            cricket.add("India");
            cricket.add("Pakistan");
            cricket.add("Australia");
            cricket.add("England");
            cricket.add("South Africa");

            List<String> football = new ArrayList<String>();
            football.add("Brazil");
            football.add("Spain");
            football.add("Germany");
            football.add("Netherlands");
            football.add("Italy");

            List<String> basketball = new ArrayList<String>();
            basketball.add("United States");
            basketball.add("Spain");
            basketball.add("Argentina");
            basketball.add("France");
            basketball.add("Russia");

            expandableListDetail.put("CRICKET TEAMS", cricket);
            expandableListDetail.put("FOOTBALL TEAMS", football);
            expandableListDetail.put("BASKETBALL TEAMS", basketball);

            expandableListDetail.put("Soccer TEAMS", basketball);

            expandableListDetail.put("Baseball TEAMS", basketball);

            expandableListDetail.put("Ski TEAMS", basketball);
            return expandableListDetail;
        }
    }
}