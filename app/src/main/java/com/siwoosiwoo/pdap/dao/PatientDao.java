package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

import java.util.List;

@Dao
public interface PatientDao {
    @Query("SELECT * FROM patient")
    List<Patient> getAll();

    @Query("SELECT * FROM patient WHERE id = :patientId")
    Patient findPatient(int patientId);

    @Insert
    void insertAll(Patient... patients);

    @Update
    void updateAll(Patient... patients);

    @Delete
    void delete(Patient patient);
}
