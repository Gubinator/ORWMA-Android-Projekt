package com.example.dg_basicboxtimer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Utilities {
    // GSON pretvara java objekte u JSON string; taj string kasnije zapisujemo u memoriju mobitela
    // Uzimamo-dohvacamo preset listu iz memorije mobitela
    // Shared preferences je usnimljavanje u memoriju
    public static PresetObjectClass[]  getFromGson(Context mContext){
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        SharedPreferences pref = mContext.getApplicationContext().getSharedPreferences("PresetListSP", 0);
        String presets = pref.getString("PresetListSP", "");
        return gson.fromJson(presets,PresetObjectClass[].class);

    }
    // Spremamo preset listu na memoriju mobitela
    // Editor samo za snimanje sluzi (nepotreban za povlacenje podataka)
    public static void commitGson(PresetObjectClass [] presets, Context mContext){
        GsonBuilder gb = new GsonBuilder();
        Gson gson = gb.create();
        String save = gson.toJson(presets);
        SharedPreferences pref = mContext.getApplicationContext().getSharedPreferences("PresetListSP", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("PresetListSP",save);
        editor.commit();

    }

    public static String addZero(String time){
        if(Long.parseLong(time)<10){
            return "0"+time;
        }
        else {
            return time;
        }
    }

}
