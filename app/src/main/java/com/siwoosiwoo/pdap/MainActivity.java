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

        AppDatabase db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "PDAP.db")
                //.createFromAsset("test.db")
                //.addTypeConverter(Converters.class)
                .allowMainThreadQueries()
                .build();

        SymptomDao symptomDao = db.symptomDao();//여기서부터 Symptom 넣음
        
        List<Symptom> symptoms = new ArrayList<>();
        Symptom symptom = new Symptom();
        symptom.id = 1;
        symptom.name = "Central neck pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 2;
        symptom.name = "Unilateral neck pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 3;
        symptom.name = "Associated shoulder girdle or upper extremity pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 4;
        symptom.name = "Limitation in neck motion";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 5;
        symptom.name = "Mechanism of onset related to trauma or whiplash";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 6;
        symptom.name = "Associated varied nonspecific concussive sign and symtoms(headache, concentration or memory difficults, confusion, hypersensitivity to mechanical, thermal, acoustic, odor or light stimuli)";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 7;
        symptom.name = "Dizziness/nausea";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 8;
        symptom.name = "Noncontinuous";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 9;
        symptom.name = "Associated headache";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 10;
        symptom.name = "Sustained positions/posture";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 11;
        symptom.name = "Intermitte pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 12;
        symptom.name = "Constant pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 13;
        symptom.name = "Insidious onset";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 14;
        symptom.name = "Trauma";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 15;
        symptom.name = "Arthrosclerosis";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 16;
        symptom.name = "Osteophytic encroachment to artery";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 17;
        symptom.name = "Spontaneous VAD";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 18;
        symptom.name = "Associated varied nonspecific concussive sign and symtoms";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 19;
        symptom.name = "positive 5 D’s and 3N’s";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 20;
        symptom.name = "Paresthesia of tongues and lips";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 21;
        symptom.name = "Over 70 years(90%)";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 22;
        symptom.name = "An insidious onset with reduced fine motor skills in the hands, urinary- urgency, hesitation";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 23;
        symptom.name = "Chest pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 24;
        symptom.name = "Chest pressure";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 25;
        symptom.name = "Shoulder pain(most common in Lt shoulder)";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 26;
        symptom.name = "Arm and hand pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 27;
        symptom.name = "Upper back pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 28;
        symptom.name = "Anterior neck pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 29;
        symptom.name = "Jaw pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 30;
        symptom.name = "Horner’s syndrome";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 31;
        symptom.name = "Thoracic outlet syndrome";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 32;
        symptom.name = "Pain in the shoulder(axillary)";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 33;
        symptom.name = "Weight loss(2~3개월 내 10%이상)";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 34;
        symptom.name = "Night pain";
        symptoms.add(symptom);

        symptom = new Symptom();
        symptom.id = 35;
        symptom.name = "Chest tightness";
        symptoms.add(symptom);

        for(int i = 0; i < symptoms.size(); i++) {
            symptomDao.insertAll(symptoms.get(i));
        }
        List<Symptom> getTest = symptomDao.getAll();

        for(int i = 0; i < getTest.size(); i++) {
            Log.d("test12", getTest.get(i).name);
        }


        DiseaseDao diseaseDao = db.diseaseDao();

        List<Disease> diseases = new ArrayList<>();
        Disease disease = new Disease();
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

        disease = new Disease();
        disease.name = "Neck pain with movement coordination impairments(muscle)Common Symptoms";
        disease.description = "Common Symptoms\n" +
                "\t- Mechanism of onset related to trauma or whiplash\n" +
                "\t- Associated shoulder girdle or upper extremity pain\n" +
                "\t- Associated varied nonspecific concussive sign and symtoms (headache, concentration or memory difficults, confusion, hypersensitivity to mechanical, thermal, acoustic, odor or light stimuli)\n" +
                "\t- Dizziness/nausea\n" +
                "\t- Positive cranial cervical flexion test\n" +
                "\t- Positive neck flexor muscle endurance teste\n" +
                "\t- Flexibility deficits of upper quarter muscles\n" +
                "\t- Sensorimotor impairment\n" +
                "\t\n" +
                "Muschle Strain/Ligamnet Sprain\n" +
                "\t- May be related to sudden trauma, chronic or sustained overload, or abnormal muscle biomechanics secondary to faulty function\n" +
                "\t\n" +
                "Intervention(management)\n" +
                "\n" +
                "Acute if prognosis is for a quick and early recovery\n" +
                "\t- Education: adivce to remain activem act as usual\n" +
                "\t- Home exercise: pain free cervical ROM and postural element\n" +
                "\t- Monitor for acceptable progress\n" +
                "\t- Minimize use of collar\n" +
                "\t\n" +
                "Subacute if prognosis is for a prolonged recovery\n" +
                "\t- Education: activation and counselling\n" +
                "\t- combined exercise\n" +
                "\t- Supervised exercise\n" +
                "\n" +
                "Chronic\n" +
                "\t- Education: prognosism encouragement, reassurance, pain management\n" +
                "\t- Cervical mobilization plus individualized progressive exercise\n" +
                "\t- TENS\n" +
                "\t* manipulation 언급 없음\n" +
                "\t* manipulation 언급 없음";
        symptomIds = new ArrayList<>();
        symptomIds.add("3");
        symptomIds.add("5");
        symptomIds.add("6");
        symptomIds.add("7");
        disease.symptomIds = symptomIds;
        diseases.add(disease);



        disease = new Disease();
        disease.name = "Neck pain with headache(Cervicogenic)";
        disease.description = "Common Symptoms\n" +
                "\t- Noncontinuous\n" +
                "\t- Unilater neck pain\n" +
                "\t- Associated headache\n" +
                "\t- Sustained positions/posture\n" +
                "\t- forward posture\n" +
                "\t- reduced AROM\n" +
                "\t- reduced PAIVM of C0-3\n" +
                "\t- Flexion rotation test\n" +
                "\n" +
                "Intervention(management)\n" +
                "\t- Coordination, strength, endurance exercise\n" +
                "\t- cervical mobilization/manipulation(upper cervical)\n" +
                "\t- stretching exercises\n" +
                "\t- self - SNAG(C1-2)\n" +
                "\n" +
                "Education: muscle control and support different from strength training";
        symptomIds = new ArrayList<>();
        symptomIds.add("2");
        symptomIds.add("8");
        symptomIds.add("9");
        symptomIds.add("10");
        disease.symptomIds = symptomIds;
        diseases.add(disease);



        disease = new Disease();
        disease.name = "Neck pain with Radiating pain(Disk condition, posterolateral Bulging or Herniation) Common Symptoms";
        disease.description = "Common Symptoms\n" +
                "\t- Radiating pain\n" +
                "\t- Upper extremtiy dermatomal paresthesia or numbness\n" +
                "\t- Muscle weakness\n" +
                "\t- Insidious onset\n" +
                "\t- neck and neck related radiating pain reproduced or relievd with radiculopathy testing: Wainner’s cluster tests Upper-limb tension test, Spurling’s test, Cervical distraction, Cervical ROM(Ipsilateral rotation < 60 deg)\n" +
                "\n" +
                " * TOS와 감별 필요 Cervical ROM test(TOS는 Cervical ROM과 상관없음)\n" +
                " \n" +
                "Intervention(management)\n" +
                "\t- Upper quarter and nerve mobilization procedures\n" +
                "\t- Traction(Intermittent, 60sec pull force: 20sec relaxation)\n" +
                "\t- Thoracic mobilization/manipulation\n" +
                "\t- Cervical non-thrust mobilization\n" +
                "\t- Cervical exercise(deep neck flexors)\n" +
                "\t- Scapulothoracic exercise(periscapular strenghening)";
        symptomIds = new ArrayList<>();
        symptomIds.add("13");

        disease.symptomIds = symptomIds;
        diseases.add(disease);



        for(int i = 0; i < diseases.size(); i++) {
            diseases.get(i).id = i;
            diseaseDao.insertAll(diseases.get(i));
        }


        List<Disease> getTest2 = diseaseDao.getAll();
        Log.d("test12", getTest2.get(0).name);

        Intent intent = new Intent(MainActivity.this , PatientsListActivity.class);
        startActivity(intent);
    }

}