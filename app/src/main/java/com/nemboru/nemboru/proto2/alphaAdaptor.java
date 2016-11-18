package com.nemboru.nemboru.proto2;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by nemboru on 14/11/16.
 */

@SuppressWarnings("ALL")
public class alphaAdaptor extends ArrayAdapter<Pair> {
    private Context c;
    private ArrayList<Pair> list;
    private String lastLetter;

    public alphaAdaptor(Context c, ArrayList<Pair> a){
        super(c,-1,a);
        this.c = c;
        this.list = a;
        this.lastLetter = new String(" ");

    }

    public String checkLast(String title){
        Log.d("letra",title.substring(0,1));
        if(lastLetter.compareTo(title.substring(0,1)) == 0){

            return " ";
        }else{
            lastLetter = title.substring(0,1);
            return lastLetter;
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Pair p = list.get(position);
        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list, parent, false);
        TextView header = (TextView) rowView.findViewById(R.id.headerlist);
        TextView title = (TextView) rowView.findViewById(R.id.headerText);

        header.setText(checkLast(p.title));
        if(position == 0 || header.getText().toString().compareTo(" ") == 0){
            rowView.findViewById(R.id.divider).setVisibility(View.GONE);
        }
        title.setText(p.title);

        return rowView;
    }

    @Override
    public void notifyDataSetChanged(){
        setNotifyOnChange(false);
        Collections.sort(this.list, new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.title.compareTo(o2.title);
            }
        });
        setNotifyOnChange(true);
        super.notifyDataSetChanged();
    }
}
