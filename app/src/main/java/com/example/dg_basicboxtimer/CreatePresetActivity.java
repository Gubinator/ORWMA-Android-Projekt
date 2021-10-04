package com.example.dg_basicboxtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class CreatePresetActivity extends AppCompatActivity {

    private Button home_button;
    private Button save_button;

    private EditText presetName;
    private EditText numberRounds;
    private EditText roundDurationMin;
    private EditText roundDurationSec;
    private EditText restDurationMin;
    private EditText restDurationSec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_preset);

        save_button=(Button)findViewById(R.id.save_button);
        presetName=findViewById(R.id.preset_name);
        numberRounds=findViewById(R.id.NumberOfRounds);
        roundDurationMin=findViewById(R.id.duration_minutes);
        roundDurationSec=findViewById(R.id.duration_seconds);
        restDurationMin=findViewById(R.id.rest_minutes);
        restDurationSec=findViewById(R.id.rest_seconds);

/*
        String value1=roundDurationMin.getText().toString();
        int roundDurationMinINT=Integer.parseInt(value1);

        String value2=roundDurationSec.getText().toString();
        int roundDurationSecINT=Integer.parseInt(value2);

        String value3=restDurationMin.getText().toString();
        int restDurationMinINT=Integer.parseInt(value3);

        String value4=restDurationSec.getText().toString();
        int restDurationSecINT=Integer.parseInt(value4);

 */


        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (roundDurationMin.getText().toString().length()==0 || roundDurationSec.getText().toString().length()==0 || restDurationMin.getText().toString().length()==0 || restDurationSec.getText().toString().length()==0 || numberRounds.getText().toString().length()==0 || presetName.getText().toString().length()==0 ){
                    openNullDialog();
                }
                else if(Integer.parseInt(roundDurationMin.getText().toString())>60 || Integer.parseInt(roundDurationSec.getText().toString())>60 || Integer.parseInt(restDurationMin.getText().toString())>60 || Integer.parseInt(restDurationSec.getText().toString())>60){
                    openLess60Dialog();
                }
                else if((Integer.parseInt(roundDurationSec.getText().toString())==0 && (Integer.parseInt(roundDurationMin.getText().toString())<=0)) || (Integer.parseInt(restDurationSec.getText().toString())==0 && Integer.parseInt(restDurationMin.getText().toString())<=0)  || (Integer.parseInt(restDurationMin.getText().toString())==0 && Integer.parseInt(restDurationSec.getText().toString())==0) || (Integer.parseInt(roundDurationMin.getText().toString())==0 && Integer.parseInt(roundDurationSec.getText().toString())==0)  || Integer.parseInt(numberRounds.getText().toString())==0){
                    openEqual0Dialog();
                }
                else {
                    openActivityPresetList();

                }
            }
        });

        home_button=(Button)findViewById(R.id.home_button);
        home_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivityMain();
            }
        });

    }

    private void openActivityPresetList() {
        Intent intent = new Intent(this, PresetListActivity.class);//json sam sluzi da objekte pretvorim u string
        GsonBuilder gb = new GsonBuilder();
        Gson g = gb.create();
        SharedPreferences pref = getApplicationContext().getSharedPreferences("PresetListSP", 0);
        SharedPreferences.Editor e = pref.edit();
        String c = pref.getString("PresetListSP", "");
        System.out.println(c);
        PresetObjectClass[] staraLista = g.fromJson(c,PresetObjectClass[].class); //kad on to cita iz stringa automatski pretvori u PresetObjectClass (navedeni class)
        int num=0;
        if(!c.equals("")){
            num=staraLista.length;
        }
        PresetObjectClass[] novaLista = new PresetObjectClass[num+1];
        // DODAJEMO SVE PRESETE IZ STARE LISTE U NOVU PA DODAJEMO NOVI PRESET(LINIJA 107)
        for(int i=0; staraLista!=null && i <staraLista.length;i++){
            novaLista[i]=staraLista[i];
        }
        PresetObjectClass FF12=new PresetObjectClass(presetName
                .getText().toString(),restDurationMin.getText().toString(),restDurationSec.getText().toString(),roundDurationMin.getText().toString(),roundDurationSec.getText().toString(),numberRounds.getText().toString());

        novaLista[num]=FF12;
        int JKK = FF12.getIdId();
        Integer JK = new Integer(JKK);
        intent.putExtra("VarijablaID",JK.toString());
        System.out.println("Prvi"+FF12.getIdId());
        c = g.toJson(novaLista);
        e.putString("PresetListSP",c);
        e.commit();
        startActivity(intent);
    }

    private void openEqual0Dialog() {
        Equal0Dialog dialog1 = new Equal0Dialog();
        dialog1.show(getSupportFragmentManager(), "equal 0 dialog");
    }

    private void openLess60Dialog() {
        LessThan60Dialog dialog1 = new LessThan60Dialog();
        dialog1.show(getSupportFragmentManager(), "60 dialog");
    }

    private void openNullDialog() {
        NullLengthDialog dialog1 = new NullLengthDialog();
        dialog1.show(getSupportFragmentManager(), "null dialog");
    }

    private void openActivityMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}