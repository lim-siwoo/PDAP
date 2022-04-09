package com.siwoosiwoo.pdap;

import androidx.room.*;
import java.util.List;

@Dao
public interface DiseaseDao {
    @Query("SELECT * FROM disease")
    List<Disease> getAll();

    @Query("SELECT * FROM disease WHERE id IN (:diseaseIds)")
    List<Disease> loadAllByIds(int[] diseaseIds);

    @Insert
    void insertAll(Disease... diseases);

    @Delete
    void delete(Disease disease);
}