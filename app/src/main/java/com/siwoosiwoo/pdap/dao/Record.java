package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

@Entity(tableName = "record")
public class Record {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "record_year")
    public int recordYear;

    @ColumnInfo(name = "record_month")
    public int recordMonth;

    @ColumnInfo(name = "record_date")
    public int recordDate;

    @ColumnInfo(name = "symptom")
    public int symptom;
}
