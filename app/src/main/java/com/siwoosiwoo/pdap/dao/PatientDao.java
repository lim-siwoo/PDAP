package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

import java.util.List;

@Dao
public interface PatientDao {
    @Query("SELECT * FROM patient")
    List<Patient> getAll();

    @Insert
    void insert(Patient patient);

    @Insert
    void insertAll(Patient... patients);

    @Delete
    void delete(Patient patient);
}
