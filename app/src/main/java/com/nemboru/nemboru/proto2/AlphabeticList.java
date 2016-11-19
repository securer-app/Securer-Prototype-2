package com.nemboru.nemboru.proto2;

import android.content.Context;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by nemboru on 14/11/16.
 */

@SuppressWarnings("Since15")
public class AlphabeticList {
    ListView list;
    ArrayList<Pair> arrayData;
    alphaAdaptor adapter;
    Gson g;

    public AlphabeticList(Context c, ListView l){
        this.list = l;
        this.arrayData = new ArrayList<>();
        this.adapter = new alphaAdaptor(c,arrayData);
        l.setAdapter(this.adapter);
        l.setDivider(null);
        g = new Gson();
    }

    public void addPair(Pair p){
        this.arrayData.add(p);
        this.adapter.notifyDataSetChanged();
    }

    public String dump(){
        Log.d("writed",g.toJson(arrayData));
        return g.toJson(arrayData);
    }

    public void load(String s){
        Log.d("readed",s);
        if(arrayData.isEmpty()) {
            Pair[] t = g.fromJson(s, Pair[].class);
            for (Pair p : t) {
                this.addPair(p);
            }
            this.adapter.notifyDataSetChanged();
        }
    }

    public void remove(int position){
        this.arrayData.remove(position);
        this.adapter.notifyDataSetChanged();
    }
}
