package com.example.rating;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class SecondActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView t1, t3;
    public Button sb;
    ArrayList<exampleItem> mExampleList;
    int step = 1;
    int max = 9;
    int min = 0;
    int t;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        t1 = findViewById(R.id.t1);
        t3 = findViewById(R.id.t3);
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        //Toast.makeText(this, ""+pref.getInt("min_rate", -1)+" "+pref.getInt("max_rate", -1), Toast.LENGTH_SHORT).show();

        min=pref.getInt("min_rate", -1);
        max=pref.getInt("max_rate", -1);
        t1.setText(""+min);
        t3.setText("Min Rate: "+min+", Max Rate: "+max);

        sb = (Button) findViewById(R.id.Submit);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                seekBar.setMax( (max - min) / step );
                t= min+progress;
                t1.setText(""+t);
                Toast.makeText(getApplicationContext(),"seekbar progress: "+progress+"/"+seekBar.getMax(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(),"seekbar touch started!", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //Toast.makeText(getApplicationContext(),"seekbar touch stopped!", Toast.LENGTH_SHORT).show();
            }



        });

        sb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadData();
                String mmin = "MIN: "+min;
                String mmax = "MAX: "+max;
                String mrate = "RATING: "+t;
                String currentDate = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                String currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
                insertItem(mmin, mmax, mrate, currentDate, currentTime);
                insertItem(String.valueOf(min), String.valueOf(max), String.valueOf(t), currentDate, currentTime);
                saveData();
                Intent intent = new Intent(view.getContext(), HistoryActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<exampleItem>>() {}.getType();
        mExampleList = gson.fromJson(json, type);

        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }


    private void insertItem(String line1, String line2, String line3, String line4, String line5) {
        mExampleList.add(new exampleItem(line4, line5,line1, line2, line3));
    }
}