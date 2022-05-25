package com.siwoosiwoo.pdap.ui.patientRecordFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Toast;

import com.siwoosiwoo.pdap.PatientsListActivity;
import com.siwoosiwoo.pdap.R;
import com.siwoosiwoo.pdap.RecordActivitiy;
import com.siwoosiwoo.pdap.ResultExpandableListViewAdapter;
import com.siwoosiwoo.pdap.SymptomExpandableListViewAdapter;
import com.siwoosiwoo.pdap.dao.Disease;
import com.siwoosiwoo.pdap.dao.DiseaseDao;
import com.siwoosiwoo.pdap.dao.MedicalDatabase;
import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.PatientDatabase;
import com.siwoosiwoo.pdap.dao.Record;
import com.siwoosiwoo.pdap.dao.RecordDao;
import com.siwoosiwoo.pdap.dao.Symptom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class List_Fragment extends Fragment {

    private PatientDatabase pdb;
    private MedicalDatabase mdb;
    private PatientDao patientDao;//이 자바 파일에서는 patient 정보를 사용해야 하므로 선언
    private RecordDao recordDao;
    private DiseaseDao diseaseDao;
    private int patientIDInt;

    OnList_FragmentSelectedListner callback;//인터페이스 자료형 선언

    ExpandableListView expandableListView;
    ResultExpandableListViewAdapter expandableListAdapter;
    List<String> expandableListTitle;
    LinkedHashMap<String, List<String>> expandableListDetail;

    View fragmentView;
    ArrayList<String> recordToScreen = new ArrayList<>();//화면에 띄울 리스트 선언

    public void setOnList_FragmentSelectedListner(OnList_FragmentSelectedListner callback){//인터페이스가 호출되면 인터페이스 자료형에 현재 값을 넣어준다
        this.callback = callback;
    }

    public interface OnList_FragmentSelectedListner{//액티비티와 통신하기 위한 인터페이스 선언
        public void onArticleSelected(int position);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_list_record, container, false);

        Bundle bundle = getArguments(); // RecordActivity에서 전달한 번들을 저장 patientID(ChartNum)이 저장되어있댜
        if(bundle != null){
            Log.d("patientId","번들에 값 있다.");
        }else{
            Log.d("patientId","번들에 값 없다.");
        }
        String patientID = bundle.getString("patientId");
        Log.d("patientId",patientID+"3");
        patientIDInt = Integer.parseInt(patientID);

        pdb = Room.databaseBuilder(getActivity(), PatientDatabase.class, "Patient.db")  //프래그먼트에서는 getApplicationContext()를 사용하면 오류가 떠서 getActivity()를 사용해야함
                //.createFromAsset("PDAP.db")
                .allowMainThreadQueries()
                .build();

        patientDao = pdb.patientDao();

        Patient patient = patientDao.findPatient(patientIDInt); //현재 선택된 Patient의 정보를 저장했음.
        ArrayList<String> recordIds = patient.recordIds;//환자가 가지고있는 레코드 정보를 여기 저장함
        ArrayList<Integer> recordsInt = new ArrayList<>();  //해당 환자의 기록들을 찾기 위해 recordID를 ArrayList를 저장함

        for(int i=0;i<recordIds.size();i++){
            recordsInt.add(Integer.parseInt(recordIds.get(i)));////해당 환자의 기록들을 찾기 위해 recordID를 ArrayList를 저장함
        }

        recordDao = pdb.recordDao();
        List<Record> records = new ArrayList<>();  //환자의 Record정보들을 저장할 Record List선언
        for(int i=0;i<recordsInt.size();i++){
            records.add(recordDao.findRecord(recordsInt.get(i))); //환자의 recordsIds기반으로 Record 정보를 List<Record> records2에 저장함.
        }





        expandableListView = fragmentView.findViewById(R.id.expandableListView);


        expandableListDetail = getDiseaseResult(records.get(0));

        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());


        expandableListAdapter = new ResultExpandableListViewAdapter(getActivity(), expandableListTitle, expandableListDetail);

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
                                childPosition), Toast.LENGTH_SHORT
                ).show();
                return false;
            }
        });

        return fragmentView;
    }

    public LinkedHashMap<String, List<String>> getDiseaseResult(Record record) {
        ArrayList<Integer> trueWeight = new ArrayList<Integer>();
        ArrayList<String> symptomsIdsString_record = record.symptomIds;//레코드의 증상들의 값을 저장
//        int[] symptomIds = new int[symptomsIdsString_record.size()];
//
//        for(int i=0;i<symptomsIdsString_record.size();i++){
//            symptomIds[i] = Integer.parseInt(symptomsIdsString_record.get(i));//symtompIds를 int값으로 변환해서 저장
//        }
        mdb = Room.databaseBuilder(getActivity(), MedicalDatabase.class, "Medical.db")  //프래그먼트에서는 getApplicationContext()를 사용하면 오류가 떠서 getActivity()를 사용해야함
                .allowMainThreadQueries()
                .build();

        diseaseDao = mdb.diseaseDao();

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
                resultDesease.put(i+1, count);
            }
            count=0;
        }

        for(int key : resultDesease.keySet()){
//            trueSymptom.add(key);
            trueWeight.add(resultDesease.get(key));
        }
        Collections.sort(trueWeight, Collections.reverseOrder());



        LinkedHashMap<String, List<String>> resultDetail = new LinkedHashMap<String, List<String>>();
        for (int key : resultDesease.keySet()) {
            Disease disease = diseaseDao.findDisease(key);
            List<String> description = new ArrayList<String>();
            description.add(disease.description);
            resultDetail.put(disease.name, description);

        }

        mdb.close();

        return resultDetail;
    }

}