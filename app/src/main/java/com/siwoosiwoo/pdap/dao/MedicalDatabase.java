package com.siwoosiwoo.pdap.dao;

import androidx.room.*;

@Database(entities = {Disease.class, Symptom.class}, version = 1)
@TypeConverters({Converters.class})
public abstract class MedicalDatabase extends RoomDatabase {
    public abstract DiseaseDao diseaseDao();
    public abstract SymptomDao symptomDao();
}
