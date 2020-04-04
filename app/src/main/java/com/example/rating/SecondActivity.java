package com.example.rating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class SecondActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView t1, t3;
    public Button Submit;
    ArrayList<exampleItem> mExampleList;
    int step = 1;
    int max = 9;
    int min = 0;

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

        Submit = (Button) findViewById(R.id.Submit);
        seekBar=(SeekBar)findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                seekBar.setMax( (max - min) / step );
                int t= min+progress;
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


            private void saveData() {
                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(mExampleList);
                editor.putString("task list", json);
                editor.apply();
            }

            private void setInsertButton() {
                Button Submit = findViewById(R.id.Submit);
                Submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DateFormat df = new SimpleDateFormat("h:mm a");
                        String time = df.format(Calendar.getInstance().getTime());

                        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
                        insertItem(date.getText().toString(), time.getText().toString());
                    }
                });
            }




        });
    }
}