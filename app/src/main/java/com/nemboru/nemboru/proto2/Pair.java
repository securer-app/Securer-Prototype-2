package com.nemboru.nemboru.proto2;

/**
 * Created by nemboru on 14/11/16.
 */

public class Pair {
    public String header;
    public String title;
    public String user;
    public String pass;

    public Pair(){
        title = new String();
        user = new String();
        pass = new String();
    }

    public Pair(String title, String user, String pass){
        this.title = title;
        this.pass = pass;
        this.user = user;
    }
}
