package com.nemboru.nemboru.proto2;

import android.app.Activity;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ListView;
import android.widget.TextView;

public class landing extends Activity {

    protected ListView l;
    protected AlphabeticList a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);
        l = (ListView) findViewById(R.id.list);

        a = new AlphabeticList(this,l);
        a.addPair(new Pair("BTitulo3","Troll","12345"));
        a.addPair(new Pair("ATitulo1","Paypal","12345"));
        a.addPair(new Pair("ATitulo2","Github","12345"));

        a.addPair(new Pair("CTitulo4","Parente","12345"));

    }
}
