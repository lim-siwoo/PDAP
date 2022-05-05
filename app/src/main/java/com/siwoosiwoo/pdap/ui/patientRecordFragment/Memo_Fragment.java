package com.siwoosiwoo.pdap.ui.patientRecordFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.siwoosiwoo.pdap.R;
import com.siwoosiwoo.pdap.dao.MedicalDatabase;
import com.siwoosiwoo.pdap.dao.DiseaseDao;
import com.siwoosiwoo.pdap.dao.SymptomDao;

public class Memo_Fragment extends Fragment {

    private MedicalDatabase mdb; //룸db를 선언할 데이터 베이스 선언
    private SymptomDao symptomDao;
    private DiseaseDao diseaseDao;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_memo_record, container, false);
    }
}