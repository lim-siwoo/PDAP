package com.siwoosiwoo.pdap;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.siwoosiwoo.pdap.dao.Symptom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class SymptomExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private LinkedHashMap<String, List<Symptom>> exapndableListDetail;
    private ArrayList<Integer> checkedIds;

    public SymptomExpandableListViewAdapter(
            Context context,
            List<String> expandableListTitle,
            LinkedHashMap<String, List<Symptom>> exapndableListDetail, ArrayList<Integer> checkedIds
        ) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.exapndableListDetail = exapndableListDetail;
        this.checkedIds = checkedIds;
    }

    @Override
    public int getGroupCount() {
        return this.expandableListTitle.size();
    }

    @Override
    public int getChildrenCount(int listPosition) {
        return this.exapndableListDetail.get(this.expandableListTitle.get(listPosition)).size();
    }

    @Override
    public Object getGroup(int listPosition) {
        return this.expandableListTitle.get(listPosition);
    }

    @Override
    public Object getChild(int listPosition, int expandableListPosition) {
        return this.exapndableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandableListPosition);
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public long getChildId(int listPosition, int expandableListPosition) {
        return expandableListPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int listPosition, boolean isExpanded,
                             View view, ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_symptom_group, null);
        }
        TextView listTitleTextView = (TextView) view
                .findViewById(R.id.listTitle);
        if(
            listTitle.equals("Cervical arterial dysfunction & Upper cervical ligamentous insufficiency") ||
            listTitle.equals("Cervical Myelopathy") ||
            listTitle.equals("Heart attack") ||
            listTitle.equals("Pancoast Tumor")
            ) {
                listTitleTextView.setTextColor(Color.RED);
        } else {
            listTitleTextView.setTextColor(Color.BLACK);
        }
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return view;
    }

    @Override
    public View getChildView(int listPosition, final int expandableListPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        final Symptom expandedListSymptom = (Symptom) getChild(listPosition, expandableListPosition);
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_symptom, null);
        }

        CheckBox expandedListCheckBox = (CheckBox) view.findViewById(R.id.expandedListItem);
        expandedListCheckBox.setChecked(false);

        if(checkedIds.contains(expandedListSymptom.id)) {
            expandedListCheckBox.setChecked(true);
        }
        expandedListCheckBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(expandedListCheckBox.isChecked() && !checkedIds.contains(expandedListSymptom.id)) {
                    checkedIds.add(expandedListSymptom.id);
                } else if(!expandedListCheckBox.isChecked() && checkedIds.contains(expandedListSymptom.id)) {
                    checkedIds.remove(Integer.valueOf(expandedListSymptom.id));
                }
            }
        });

        expandedListCheckBox.setText(expandedListSymptom.name);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    public ArrayList<Integer> getCheckedIds() {
        return checkedIds;
    }
}
