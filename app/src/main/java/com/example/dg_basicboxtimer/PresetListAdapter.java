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

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;



public class PresetListAdapter extends ArrayAdapter<PresetObjectClass> {

    private Context mContext;
    private int mResource;
    private int VarId;


    //Defaultni konstruktor za PresetListAdapter prima parametre iz PresetListActivity context,Layout,Objekt (PresetObjectClass)
    public PresetListAdapter(Context context, int resource, ArrayList<PresetObjectClass> objects,int id) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
        // VarId je ID novo-nastalog elementa
        VarId=id;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Dohvacamo podatke iz XML-a za preset listu
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


        TextViewName.setText(name);
        TextViewRest.setText(Utilities.addZero(rest_duration_min) + ":" + Utilities.addZero(rest_duration_sec));
        TextViewWork.setText(Utilities.addZero(work_duration_min) + ":" + Utilities.addZero(work_duration_sec));
        TextViewRounds.setText(round_number);
        Integer variableID = new Integer(VarId);
        Button_remove.setTag(variableID.toString());


        Button_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Kad se brise element iz liste to je -1 element u listi (i kada je manji od 1 ne moze biti manji length od 0)
                PresetObjectClass[] staraLista = Utilities.getFromGson(mContext);
                int num = (staraLista.length-1);
                //U slucaju da je num manji od nule, num se postavlja na nulu
                if(num<0){num=0;}
                PresetObjectClass[] novaLista = new PresetObjectClass[num];

                for(int i = 0,j=0; i < staraLista.length; i++) {
                    if(i != position) {
                        novaLista[j] = staraLista[i];
                        j++;
                    }
                }

                Utilities.commitGson(novaLista,mContext);

                Intent intent=new Intent(mContext.getApplicationContext(),PresetListActivity.class);
                intent.putExtra("VarijablaID",String.valueOf(VarId));
                String extras = intent.getExtras().getString("VarijablaID");
                mContext.startActivity(intent);
            }
        });

        return convertView;

    }

}