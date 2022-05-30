package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

import java.util.List;

@Dao
public interface RecordDao {
    @Query("SELECT * FROM record")
    List<Record> getAll();

    @Query("SELECT * FROM record WHERE id = :recordId")
    Record findRecord(int recordId);

    @Query("Update record SET description = :appendDescription WHERE id = :recordId")
    void appendDescription(String appendDescription, int recordId);

    @Update
    void updateAll(Record... records);

    @Insert
    void insertAll(Record... records);

    @Delete
    void delete(Record record);
}
