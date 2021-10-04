package com.example.dg_basicboxtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class SelectPresetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_preset);

        ListView mListView=(ListView)findViewById(R.id.ListView);

        ArrayList<PresetObjectClass> PresetList = new ArrayList<>();
        PresetObjectClass[] presets = Utilities.getFromGson(getApplicationContext());

        // Ovo radimo jer preset list u adapteru mora biti tipa ArrayList<PresetObjectClass>, a ne tipa array[] (npr. PresetObjectClass[] )
        for(int i=0; i<presets.length;i++){
            PresetList.add(presets[i]);
        }

        PresetSelectListAdapter adapter = new PresetSelectListAdapter(this,R.layout.adapter_select_view_layout,PresetList);
        mListView.setAdapter(adapter);
    }
}