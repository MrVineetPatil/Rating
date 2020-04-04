package com.example.rating;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        OnItemSelectedListener {
    Spinner s1, s2;
    TextView t1;
    String minr, maxr;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        t1 = findViewById(R.id.text2);
        s1 = findViewById(R.id.spinner1);
        s2 = findViewById(R.id.spinner2);
        btn = (Button) findViewById(R.id.btnSubmit);

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        final SharedPreferences.Editor editor = pref.edit();

        ArrayList<String> l = new ArrayList<>();
        l.add("Choose between 0-8");
        for (int i = 0; i < 9; i++) l.add("" + i);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, l);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(arrayAdapter);

        s1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0) {
                    ArrayList<String> l1 = new ArrayList<>();
                    l1.add("Choose between "+position+"-9");
                    for (int i = position; i <= 9; i++) l1.add("" + i);
                    ArrayAdapter<String> arrayAdapter1 = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, l1);
                    arrayAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    s2.setAdapter(arrayAdapter1);
                    s2.setVisibility(View.VISIBLE);
                    t1.setVisibility(View.VISIBLE);
                    int pos = position-1;
                    minr=""+pos;
                    editor.putInt("min_rate", pos);
                    editor.putInt("max_rate", -1);
                    editor.apply();
                }
                else {
                    s2.setVisibility(View.GONE);
                    t1.setVisibility(View.GONE);
                }
                btn.setText("Rated");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position>0) {
                    int pos = Integer.parseInt(minr)+position;
                    maxr=""+pos;
                    editor.putInt("max_rate", pos);
                    editor.apply();
                    btn.setText("Rated <"+minr+"-"+maxr+">");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pref.getInt("max_rate", -1) != -1) {
                    Intent intent = new Intent(view.getContext(), SecondActivity.class);
                    view.getContext().startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Please ensure that all the details are filled.", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}