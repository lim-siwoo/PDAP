package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "record")
public class Record {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "record_date")
    public String recordDate;

    @ColumnInfo(name = "symptom_ids")
    public ArrayList<String> symptomIds;

    @ColumnInfo(name = "description")
    public String description;
}
