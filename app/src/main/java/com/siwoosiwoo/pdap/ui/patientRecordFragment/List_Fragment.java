package com.siwoosiwoo.pdap.ui.patientRecordFragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.siwoosiwoo.pdap.R;


public class List_Fragment extends Fragment {

    View fragmentView;
    ListView listView;

    String[] symptomName = new String[]{"Chest Pain","Unilateral Neck Pain","Limitation in Neck Motion"}; //나중에 동적으로 DB로 받아와서 아이템 만듬
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        fragmentView = inflater.inflate(R.layout.fragment_list_symptom, container, false);

        listView = fragmentView.findViewById(R.id.listView_item);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1,symptomName);

        listView.setAdapter(adapter);
        return fragmentView;
    }
}