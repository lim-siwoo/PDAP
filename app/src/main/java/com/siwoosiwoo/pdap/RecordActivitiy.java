package com.siwoosiwoo.pdap;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.siwoosiwoo.pdap.ui.patientRecordFragment.List_Fragment;

public class RecordActivitiy extends AppCompatActivity {

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.save_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int curId = item.getItemId();
        switch (curId){
            case R.id.save:
                Toast.makeText(this, "저장되었습니다.", Toast.LENGTH_SHORT).show();
                //여기서 DB에 노트적은거 저장하면됨

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.symptom_layout);

        List_Fragment fragment1 = new List_Fragment();

        getSupportFragmentManager().beginTransaction().add(R.id.fragment1,fragment1).commit();
        //FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        //transaction.add(R.id.fragment1,fragment1).commit();
    }
}