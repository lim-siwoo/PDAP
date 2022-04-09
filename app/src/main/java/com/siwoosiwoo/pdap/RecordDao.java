package com.siwoosiwoo.pdap;

import androidx.room.*;

import java.util.List;

@Dao
public interface RecordDao {
    @Query("SELECT * FROM record")
    List<Record> getAll();

    @Query("SELECT * FROM record WHERE id IN (:recordIds)")
    List<Record> loadAllByIds(int[] recordIds);

    @Insert
    void insertAll(Record... records);

    @Delete
    void delete(Record record);
}
