package com.nemboru.nemboru.proto2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by nemboru on 14/11/16.
 */

public class Pair implements Parcelable {
    public String key;
    public String title;
    public String user;
    public String pass;

    @Override
    public int describeContents(){
        return 0;
    }

    @Override
    public void  writeToParcel(Parcel p, int flags){
        p.writeString(this.title);
        p.writeString(this.user);
        p.writeString(this.pass);
    }

    public static final Parcelable.Creator<Pair> CREATOR
            = new Parcelable.Creator<Pair>() {
        public Pair createFromParcel(Parcel in) {
            return new Pair(in);
        }

        public Pair[] newArray(int size) {
            return new Pair[size];
        }
    };


    public Pair(){
        title = new String();
        user = new String();
        pass = new String();
        key = new String();
    }

    public Pair(String title, String user, String pass){
        this.title = title;
        this.pass = pass;
        this.user = user;
    }

    public Pair(Parcel p){
        this.title = p.readString();
        this.user = p.readString();
        this.pass = p.readString();
    }
}
