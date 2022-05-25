package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

import java.util.List;

@Dao
public interface RecordDao {
    @Query("SELECT * FROM record")
    List<Record> getAll();

    @Query("SELECT * FROM record WHERE id = :recordId")
    Record findRecord(int recordId);

    @Query("Update record SET description = :appendDescription")
    void appendDescription(String appendDescription);

    @Insert
    void insertAll(Record... records);

    @Delete
    void delete(Record record);
}
