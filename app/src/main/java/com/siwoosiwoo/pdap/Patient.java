package com.siwoosiwoo.pdap;

import androidx.room.*;

@Entity(tableName = "patient")
public class Patient {
    @PrimaryKey
    public int id;

    @ColumnInfo(name = "first_name")
    public String firstName;

    @ColumnInfo(name = "last_name")
    public String lastName;

    @ColumnInfo(name = "birth_year")
    public int birthYear;

    @ColumnInfo(name = "birth_month")
    public int birthMonth;

    @ColumnInfo(name = "bitth_date")
    public int birthDate;

    @ColumnInfo(name = "sex")
    public char sex;
}