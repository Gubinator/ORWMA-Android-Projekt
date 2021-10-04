package com.example.dg_basicboxtimer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Objects;


public class PresetSelectListAdapter extends ArrayAdapter<PresetObjectClass> {

    private Context mContext;
    private int mResource;
    private ArrayList<PresetObjectClass> presets;


    //Defaultni konstruktor za PresetListAdapter prima parametre iz PresetListActivity context,Layout,Objekt (PresetObjectClass)
    public PresetSelectListAdapter(Context context, int resource, ArrayList<PresetObjectClass> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        presets= objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getName();
        String rest_duration_min = getItem(position).getRest_duration_min();
        String rest_duration_sec = getItem(position).getRest_duration_sec();
        String work_duration_min = getItem(position).getWork_duration_min();
        String work_duration_sec = getItem(position).getWork_duration_sec();
        String round_number = getItem(position).getRound_number();

        LayoutInflater inflater = LayoutInflater.from(mContext);
        // iz get Viewa definiran
        convertView = inflater.inflate(mResource, parent, false);


        TextView TextViewName = (TextView) convertView.findViewById(R.id.TextView1);
        TextView TextViewRest = (TextView) convertView.findViewById(R.id.TextView2);
        TextView TextViewWork = (TextView) convertView.findViewById(R.id.TextView3);
        TextView TextViewRounds = (TextView) convertView.findViewById(R.id.TextView4);
        Button Button_remove = (Button)convertView.findViewById(R.id.remove_button1);

        for(PresetObjectClass x : presets){
            // Mora se podudarati u SVIM parametrima; pronalazimo ID tako da se elementi na preset listi poklapaju s elementima pohranjenjim u memoriju uredaja
            if(x.getName().equals(name) &&
                    x.getRest_duration_min().equals(String.valueOf(rest_duration_min)) &&
                    x.getRest_duration_sec().equals(String.valueOf(rest_duration_sec)) &&
                    x.getWork_duration_min().equals(String.valueOf(work_duration_min)) &&
                    x.getWork_duration_sec().equals(String.valueOf(work_duration_sec)) &&
                    x.getRound_number().equals(String.valueOf(round_number))){
                // Ovo radimo da mozemo poslati ispravni element odnosno preset -> TimerActivity  kad se klikne button
                Button_remove.setTag(String.valueOf(x.getIdId()));
            }
        }
        TextViewName.setText(name);
        TextViewRest.setText(Utilities.addZero(rest_duration_min) + ":" + Utilities.addZero(rest_duration_sec));
        TextViewWork.setText(Utilities.addZero(work_duration_min) + ":" + Utilities.addZero(work_duration_sec));
        TextViewRounds.setText(round_number);

        Button_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PresetObjectClass preset = null;
                for(PresetObjectClass x : presets){
                    //Trazimo preset sa ID-em navedenim u buttonu (linija koda - 69)
                    if(String.valueOf(x.getIdId()).equals(Button_remove.getTag())){
                        preset=x;
                    }
                }

                Integer restMinutes = Integer.parseInt(preset.getRest_duration_min())*60;
                Integer restSeconds = Integer.parseInt(preset.getRest_duration_sec());
                // varijabla rest - minute i sekunde zapisane u milisekundama
                long rest = (restMinutes+restSeconds)*1000;


                Integer workMinutes = Integer.parseInt(preset.getWork_duration_min())*60;
                Integer workSeconds = Integer.parseInt(preset.getWork_duration_sec());
                // varijabla rest - minute i sekunde zapisane u milisekundama
                long work = (workMinutes+workSeconds)*1000;

                Intent intent=new Intent(mContext.getApplicationContext(),TimerActivity.class);
                intent.putExtra("Value",preset.getRound_number());

                // 102. i 103. linija koda - trajanje u string formatu AKTIVNOG i vremena ODMORA
                intent.putExtra("Rest_time",String.valueOf(rest));
                intent.putExtra("Active_time",String.valueOf(work));
                mContext.startActivity(intent);
            }
        });



        return convertView;

    }

}