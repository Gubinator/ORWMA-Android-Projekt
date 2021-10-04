package com.example.dg_basicboxtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MainActivity extends AppCompatActivity {

    private Button BoxingRoundTimerButton;
    private Button CreatePresetButton;
    private Button SelectPresetButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BoxingRoundTimerButton=(Button)findViewById(R.id.ButtonMain1);
        BoxingRoundTimerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityBoxingRoundTimerActivity();
            }

        });

        CreatePresetButton=(Button)findViewById(R.id.ButtonMain2);
        CreatePresetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { openActivityCreatePresetActivity();
            }
        });

        SelectPresetButton=findViewById(R.id.ButtonMain3);
        SelectPresetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SelectPresetActivity.class);
                startActivity(intent);
            }
        });
    }


    private void openActivityCreatePresetActivity() {
        Intent intent = new Intent(this, CreatePresetActivity.class);
        startActivity(intent);
    }

    public void openActivityBoxingRoundTimerActivity() {
        Intent intent = new Intent(this, BoxingRoundTimerActivity.class);
        startActivity(intent);
    }

    public void ActivityBoxingRound(View view) {
    }

    public void ActivityEditPreset(View view) {
    }

    public void ActivityCreatePreset(View view) {
    }
}