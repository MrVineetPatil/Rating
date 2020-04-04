package com.example.rating;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class SecondActivity extends AppCompatActivity {
    SeekBar seekBar;
    TextView t1, t3;
    public Button Submit;
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


        });
    }
}