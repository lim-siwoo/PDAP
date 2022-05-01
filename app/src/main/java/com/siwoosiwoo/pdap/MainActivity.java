package com.siwoosiwoo.pdap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.siwoosiwoo.pdap.dao.AppDatabase;
import com.siwoosiwoo.pdap.dao.Converters;
import com.siwoosiwoo.pdap.dao.Disease;
import com.siwoosiwoo.pdap.dao.DiseaseDao;
import com.siwoosiwoo.pdap.dao.Patient;
import com.siwoosiwoo.pdap.dao.PatientDao;
import com.siwoosiwoo.pdap.dao.Symptom;
import com.siwoosiwoo.pdap.dao.SymptomDao;

import org.w3c.dom.Text;

import java.io.File;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "Sample.db")
                //.createFromAsset("test.db")
                //.addTypeConverter(Converters.class)
                .allowMainThreadQueries()
                .build();

        SymptomDao symptomDao = db.symptomDao();
        
        List<Symptom> symptoms = new ArrayList<>();
        Symptom symptom = new Symptom();
        symptom.id = 1;
        symptom.name = "Central neck pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 2;
        symptom.name = "Unilateral neck pain";
        symptoms.add(symptom);

        for(int i = 0; i < symptoms.size(); i++) {
            symptomDao.insertAll(symptoms.get(i));
        }
        List<Symptom> getTest = symptomDao.getAll();
        Log.d("test12", getTest.get(0).name);

        DiseaseDao diseaseDao = db.diseaseDao();

        List<Disease> diseases = new ArrayList<>();
        Disease disease = new Disease();
        disease.id = 1;
        disease.name = "Neck pain with mobility (Neck stiffness, Spondylosis, Facet syndrome, Spinal stenosis) Common Symptoms";
        disease.description = "Common Symptoms\n" +
                "\t- Central and/or unilateral neck pain\n" +
                "\t- Associated shoulder girdle or upper extremity pain\n" +
                "\t- Limitation in neck motion\n" +
                "\t- Younger individaul(age < 50 years)\n" +
                "\t- Neck pain reproduced at end ranges of active and passive motions\n" +
                "\t- Restricted cervical and thoracic segmental mobility\n" +
                "\t- Neck and referred pain reproduced with provaction of the involved cervical or upper thoracic segments or cervical musculature\n" +
                "\t- Deficits in cervico-scapulo-thoracic strength and motor control may be present in individuals with subacute or chronic neck pain\n" +
                "\n" +
                "Facet joint syndrome feature\n" +
                "\t- Usually related to mechanical deformation\n" +
                "\t- Flexion is most comfortable for patient, and extension increase pain\n" +
                "\t- especially into ipsilateral rotation / SF – towards the end\n" +
                "\t- Extension and combined movement\n" +
                "\n" +
                "Intervention(management)\n" +
                "\n" +
                "Acute\n" +
                "\t- Thoracic manipulation\n" +
                "\t- Cervical mobilization and manipulation\n" +
                "\t- Cervical ROM, stretching, and isometric strengthening exercise\n" +
                "\t- supervised exercise, including cervicoscapulothoracic abd upper extremity stretching, strengthening, and endurance training\n" +
                "\t- general fitness training - stay active\n" +
                "\n" +
                "Subacute\n" +
                "\t- cervical mobilization or manipulation\n" +
                "\t- Thoracic manipulation\n" +
                "\t- advice to stay active plus home cervical ROM and isometric exercise\n" +
                "\t- Cervicoscapulothoracic endurance exercise\n" +
                "\n" +
                "Chronic\n" +
                "\t- Thoracic manipulation\n" +
                "\t- Cervical mobilization\n" +
                "\t- combined cervicoscapulothoracic exercise plus mobilization or manipulation\n" +
                "\t- Mixed exercise for cervicoscapulothoracic region-neuromuscular exercise\n" +
                "\t- Supervised individualized exercise\n" +
                "\t- \"stay active\" lifestyle approached";
        ArrayList<String> symptomIds = new ArrayList<>();
        symptomIds.add("1");
        symptomIds.add("2");
        symptomIds.add("3");
        symptomIds.add("4");
        disease.symptomIds = symptomIds;
        diseases.add(disease);

        for(int i = 0; i < diseases.size(); i++) {
            diseaseDao.insertAll(diseases.get(i));
        }


        List<Disease> getTest2 = diseaseDao.getAll();
        Log.d("test12", getTest2.get(0).name);

        Intent intent = new Intent(MainActivity.this , PatientsListActivity.class);
        startActivity(intent);
    }

}