package com.siwoosiwoo.pdap;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ExpandableListViewAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> exapndableListDetail;

    public ExpandableListViewAdapter(
            Context context,
            List<String> expandableListTitle,
            HashMap<String, List<String>> exapndableListDetail
        ) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.exapndableListDetail = exapndableListDetail;
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
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);
        return view;
    }

    @Override
    public View getChildView(int listPosition, final int expandableListPosition, boolean isLastChild, View view, ViewGroup viewGroup) {
        final String expandedListText = (String) getChild(listPosition, expandableListPosition);
        if(view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_symptom, null);
        }
        TextView expandedListTextView = (TextView) view.findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);
        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }
}
