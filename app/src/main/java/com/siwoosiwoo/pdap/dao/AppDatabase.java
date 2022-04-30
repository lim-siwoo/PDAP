package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

@Database(entities = {Patient.class, Record.class, Disease.class, Symptom.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class AppDatabase extends RoomDatabase {
    public abstract PatientDao patientDao();
    public abstract RecordDao recordDao();
    public abstract DiseaseDao diseaseDao();
    public abstract SymptomDao symptomDao();
}
