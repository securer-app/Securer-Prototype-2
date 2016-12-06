package com.nemboru.nemboru.proto2;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Iterator;

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

    public void remove(String key){
        for (Iterator<Pair> iter = arrayData.iterator(); iter.hasNext(); ) {
            Pair p = iter.next();
            if (p.key.equals(key)) {
                iter.remove();
            }
        }
        this.adapter.notifyDataSetChanged();
    }
}
