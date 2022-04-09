package com.siwoosiwoo.pdap;

import androidx.room.*;

import java.util.List;

@Dao
public interface PatientDao {
    @Query("SELECT * FROM patient")
    List<Patient> getAll();

    @Query("SELECT * FROM patient WHERE id IN (:patientIds)")
    List<Patient> loadAllByIds(int[] patientIds);

    @Query("SELECT * FROM patient WHERE first_name LIKE :first AND " +
            "last_name LIKE :last LIMIT 1")
    Patient findByName(String first, String last);

    @Insert
    void insertAll(Patient... patients);

    @Delete
    void delete(Patient patient);
}
