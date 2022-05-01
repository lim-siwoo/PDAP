package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

import java.util.ArrayList;
import java.util.Date;

@Entity(tableName = "patient")
public class Patient {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "birth_date")
    public String birthDate;

    @ColumnInfo(name = "sex")
    public String sex;

    @ColumnInfo(name = "record_ids")
    public ArrayList<String> recordIds;
}