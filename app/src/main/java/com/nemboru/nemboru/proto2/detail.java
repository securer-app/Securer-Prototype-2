package com.nemboru.nemboru.proto2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextView user = (TextView) findViewById(R.id.user);
        final TextView pass = (TextView) findViewById(R.id.pass);
        Pair p = getIntent().getParcelableExtra("pair");

        user.setText(p.user);
        pass.setText(p.pass);

        final ImageView status = (ImageView) findViewById(R.id.status);
        final ImageView userimage = (ImageView) findViewById(R.id.userimage);
        final ImageView passimage = (ImageView) findViewById(R.id.passimage);


        Button b = (Button) findViewById(R.id.decrypt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // previously invisible view
                final View myView = findViewById(R.id.content);
                //myView.setVisibility(View.INVISIBLE);
// get the center for the clipping circle
                int cx = myView.getWidth() / 2;
                int cy = myView.getHeight() / 2;

// get the final radius for the clipping circle
                float finalRadius = (float) Math.hypot(cx, cy);

// create the animator for this view (the start radius is zero)
                Animator anim =
                        ViewAnimationUtils.createCircularReveal(myView, cx, cy, 0, finalRadius);

// make the view visible and start the animation

                anim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationStart(Animator animation) {
                        myView.setBackgroundColor(Color.WHITE);
                        status.setImageResource(R.drawable.ic_lock_open_black_24dp);
                        status.setColorFilter(getResources().getColor(R.color.colorPrimary));
                        userimage.setColorFilter(getResources().getColor(R.color.colorAccent));
                        passimage.setColorFilter(getResources().getColor(R.color.colorAccent));
                        user.setTextColor(getResources().getColor(R.color.colorPrimary));
                        pass.setTextColor(getResources().getColor(R.color.colorPrimary));
                    }
                });

                myView.setBackgroundColor(Color.BLACK);
                myView.setVisibility(View.VISIBLE);
                anim.setDuration(300);
                anim.start();
            }
        });
    }

}
