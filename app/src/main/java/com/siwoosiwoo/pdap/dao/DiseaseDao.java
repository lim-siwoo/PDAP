package com.siwoosiwoo.pdap.dao;

import androidx.room.*;
import java.util.List;

@Dao
public interface DiseaseDao {
    @Query("SELECT * FROM disease")
    List<Disease> getAll();

    @Query("SELECT * FROM disease WHERE id = :diseaseId")
    Disease findDisease(int diseaseId);

    @Insert
    void insertAll(Disease... diseases);

    @Delete
    void delete(Disease disease);
}