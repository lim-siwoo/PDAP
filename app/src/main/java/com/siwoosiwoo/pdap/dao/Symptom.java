package com.siwoosiwoo.pdap.dao;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.w3c.dom.Text;

@Entity(tableName = "symptom")
public class Symptom {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "name")
    public String name;
}
