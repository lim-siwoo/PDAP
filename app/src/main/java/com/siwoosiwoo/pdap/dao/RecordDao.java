package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

import java.util.List;

@Dao
public interface RecordDao {
    @Query("SELECT * FROM record")
    List<Record> getAll();

    @Insert
    void insertAll(Record... records);

    @Delete
    void delete(Record record);
}
