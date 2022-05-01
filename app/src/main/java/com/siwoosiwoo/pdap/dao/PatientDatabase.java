package com.siwoosiwoo.pdap.dao;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Patient.class, Record.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class PatientDatabase extends RoomDatabase {
    public abstract PatientDao patientDao();
    public abstract RecordDao recordDao();
}
