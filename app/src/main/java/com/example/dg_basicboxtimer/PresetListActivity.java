package com.example.dg_basicboxtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import static com.example.dg_basicboxtimer.PresetListAdapter.*;

public class PresetListActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preset_list);
        ListView mListView=(ListView)findViewById(R.id.ListView);

        ArrayList<PresetObjectClass> PresetList = new ArrayList<>();
        PresetObjectClass[] presets = Utilities.getFromGson(getApplicationContext());

        // Ovo radimo jer preset list u adapteru mora biti tipa ArrayList<PresetObjectClass>, a ne tipa array[] (npr. PresetObjectClass[] )
        for(int i=0; i<presets.length;i++){
            PresetList.add(presets[i]);
        }

        // ID od novonastalog elementa
        int id = Integer.parseInt(getIntent().getExtras().getString("VarijablaID"));
        PresetListAdapter adapter = new PresetListAdapter(this,R.layout.adapter_view_layout,PresetList,id);
        mListView.setAdapter(adapter);



    }
}