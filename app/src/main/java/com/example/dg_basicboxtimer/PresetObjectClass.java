package com.example.dg_basicboxtimer;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Random;

public class PresetObjectClass implements Parcelable {
    private int id;
    private String name;
    private String rest_duration_min;
    private String rest_duration_sec;
    private String work_duration_min;
    private String work_duration_sec;
    private String round_number;

    public PresetObjectClass(String name, String rest_duration_min, String rest_duration_sec, String work_duration_min, String work_duration_sec, String round_number) {
        this.name = name;
        this.rest_duration_min = rest_duration_min;
        this.rest_duration_sec = rest_duration_sec;
        this.work_duration_min = work_duration_min;
        this.work_duration_sec = work_duration_sec;
        this.round_number = round_number;
        Random r=new Random();
        id=r.nextInt(100000);
    }

    protected PresetObjectClass(Parcel in) {
        name = in.readString();
        rest_duration_min = in.readString();
        rest_duration_sec = in.readString();
        work_duration_min = in.readString();
        work_duration_sec = in.readString();
        round_number = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(rest_duration_min);
        dest.writeString(rest_duration_sec);
        dest.writeString(work_duration_min);
        dest.writeString(work_duration_sec);
        dest.writeString(round_number);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PresetObjectClass> CREATOR = new Creator<PresetObjectClass>() {
        @Override
        public PresetObjectClass createFromParcel(Parcel in) {
            return new PresetObjectClass(in);
        }

        @Override
        public PresetObjectClass[] newArray(int size) {
            return new PresetObjectClass[size];
        }
    };

    public int getIdId(){return  id;}
    public void setIdId(int nebitno){this.id=nebitno;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRest_duration_min() {
        return rest_duration_min;
    }

    public void setRest_duration_min(String rest_duration_min) {
        this.rest_duration_min = rest_duration_min;
    }

    public String getRest_duration_sec() {
        return rest_duration_sec;
    }

    public void setRest_duration_sec(String rest_duration_sec) {
        this.rest_duration_sec = rest_duration_sec;
    }

    public String getWork_duration_min() {
        return work_duration_min;
    }

    public void setWork_duration_min(String work_duration_min) {
        this.work_duration_min = work_duration_min;
    }

    public String getWork_duration_sec() {
        return work_duration_sec;
    }

    public void setWork_duration_sec(String work_duration_sec) {
        this.work_duration_sec = work_duration_sec;
    }

    public String getRound_number() {
        return round_number;
    }

    public void setRound_number(String round_number) {
        this.round_number = round_number;
    }
}
