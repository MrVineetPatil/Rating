package com.example.rating;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements
        OnItemSelectedListener{
    Spinner s1,s2;
    private Button btnSubmit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        s1 = (Spinner)findViewById(R.id.spinner1);
        s2 = (Spinner)findViewById(R.id.spinner2);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        s1.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
                               long arg3) {
        // TODO Auto-generated method stub
        String sp11=String.valueOf(s1.getSelectedItem());
        Toast.makeText(this, sp11, Toast.LENGTH_SHORT).show();
        int sp1 = Integer.parseInt(sp11);
        List<Integer> list = new ArrayList<Integer>();
        for (;sp1<=9;sp1++){
            list.add(sp1+1);
            ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, list);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            dataAdapter.notifyDataSetChanged();
            s2.setAdapter(dataAdapter);
        }

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "OnClickListener : " +
                                "\nSpinner 1 : "+ String.valueOf(s1.getSelectedItem()) +
                                "\nSpinner 2 : "+ String.valueOf(s2.getSelectedItem()),
                        Toast.LENGTH_SHORT).show();

            }
        } );
    }
    
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }
}