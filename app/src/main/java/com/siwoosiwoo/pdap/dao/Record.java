package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

import java.util.Date;

@Entity(tableName = "record")
public class Record {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "record_date")
    public Date recordDate;

    @ColumnInfo(name = "symptom_ids")
    public int[] symptomIds;
}
