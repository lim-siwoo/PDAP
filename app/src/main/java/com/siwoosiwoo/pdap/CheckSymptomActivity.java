package com.siwoosiwoo.pdap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CheckSymptomActivity extends AppCompatActivity {

    LinearLayout linearMain;
    CheckBox checkBox;
    Button resultButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_symptom);
        linearMain = (LinearLayout) findViewById(R.id.checkbox_layout);
        resultButton = findViewById(R.id.resultButton);
        /**
         * create linked hash map for store item you can get value from database
         * or server also
         */
        LinkedHashMap<String, String> symptom = new LinkedHashMap<String, String>();//밑에처럼 동적추가 가능함
        symptom.put("1", "Apple");
        symptom.put("2", "Boy");
        symptom.put("3", "Cat");
        symptom.put("4", "Dog");
        symptom.put("5", "Eet");
        symptom.put("6", "Fat");
        symptom.put("7", "Goat");
        symptom.put("8", "Hen");
        symptom.put("9", "I am");
        symptom.put("10", "Jug");

        Set<?> set = symptom.entrySet();
        // Get an iterator
        Iterator<?> i = set.iterator();
        // Display elements
        while (i.hasNext()) {
            @SuppressWarnings("rawtypes")
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + ": ");
            System.out.println(me.getValue());

            checkBox = new CheckBox(this);
            checkBox.setId(Integer.parseInt(me.getKey().toString()));
            checkBox.setText(me.getValue().toString());
            checkBox.setOnClickListener(getOnClickDoSomething(checkBox));
            linearMain.addView(checkBox);
        }
        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckSymptomActivity.this,RecordActivitiy.class);
                startActivity(intent);
            }
        });
    }
    View.OnClickListener getOnClickDoSomething(final Button button) {
        return new View.OnClickListener() {
            public void onClick(View v) {
                System.out.println("*************id******" + button.getId());
                System.out.println("and text***" + button.getText().toString());
            }
        };
    }

}