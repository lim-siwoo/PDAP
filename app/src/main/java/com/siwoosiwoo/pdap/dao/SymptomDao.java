package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

import java.util.List;

@Dao
public interface SymptomDao {
    @Query("SELECT * FROM symptom")
    List<Symptom> getAll();

    @Query("SELECT * FROM symptom WHERE id IN (:symptomIds)")
    List<Symptom> loadAllByIds(int[] symptomIds);

    @Insert
    void insert(Symptom symptom);

    @Insert
    void insertAll(Symptom... symptoms);

    @Delete
    void delete(Symptom symptom);
}
