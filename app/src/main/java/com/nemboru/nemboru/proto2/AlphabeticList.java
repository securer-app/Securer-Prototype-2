package com.nemboru.nemboru.proto2;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by nemboru on 14/11/16.
 */

@SuppressWarnings("Since15")
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
        this.arrayData.add(p);
        this.adapter.notifyDataSetChanged();
    }
}
