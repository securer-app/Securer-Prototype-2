package com.nemboru.nemboru.proto2;

import android.content.Context;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Created by nemboru on 14/11/16.
 */

public class AlphabeticList {
    ListView list;
    ArrayList<Pair> arrayData;
    alphaAdaptor adapter;

    public AlphabeticList(Context c, ListView l){
        this.list = l;
        this.arrayData = new ArrayList<>();
        this.adapter = new alphaAdaptor(c,arrayData);
        l.setAdapter(this.adapter);
        l.setDivider(null);
    }

    public void addPair(Pair p){
        arrayData.add(p);
        adapter.notifyDataSetChanged();
    }


    public void remove(int position){
        this.arrayData.remove(position);
        this.adapter.notifyDataSetChanged();
    }
}
