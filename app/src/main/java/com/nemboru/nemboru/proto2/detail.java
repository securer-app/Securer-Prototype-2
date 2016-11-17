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

    protected TextView user;
    protected TextView pass;
    protected ImageView status;
    protected ImageView userimage;
    protected ImageView passimage;
    protected View content;
    protected Button b;

    protected boolean decrypted;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        user = (TextView) findViewById(R.id.user);
        pass = (TextView) findViewById(R.id.pass);
        Pair p = getIntent().getParcelableExtra("pair");

        decrypted = false;

        user.setText(p.user);
        pass.setText(p.pass);

        status = (ImageView) findViewById(R.id.status);
        userimage = (ImageView) findViewById(R.id.userimage);
        passimage = (ImageView) findViewById(R.id.passimage);
        content = findViewById(R.id.content);


        b = (Button) findViewById(R.id.decrypt);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!decrypted) {
                    decrypted = true;
                    // previously invisible view

                    //myView.setVisibility(View.INVISIBLE);
                    // get the center for the clipping circle
                    int cx = content.getWidth() / 2;
                    int cy = content.getHeight() / 2;

                    // get the final radius for the clipping circle
                    float finalRadius = (float) Math.hypot(cx, cy);

                    // create the animator for this view (the start radius is zero)
                    Animator anim =
                            ViewAnimationUtils.createCircularReveal(content, cx, cy, 0, finalRadius);

                    // make the view visible and start the animation

                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            content.setBackgroundColor(Color.parseColor("#FAFAFAFA"));
                            status.setImageResource(R.drawable.ic_lock_open_black_24dp);
                            status.setColorFilter(getResources().getColor(R.color.colorPrimary));
                            userimage.setColorFilter(getResources().getColor(R.color.colorAccent));
                            passimage.setColorFilter(getResources().getColor(R.color.colorAccent));
                            user.setTextColor(getResources().getColor(R.color.colorPrimary));
                            pass.setTextColor(getResources().getColor(R.color.colorPrimary));
                            b.setText("Back");
                        }
                    });

                    //content.setBackgroundColor(Color.BLACK);
                    content.setVisibility(View.VISIBLE);
                    anim.setDuration(300);
                    anim.start();
                } else {
                    finish();
                    overridePendingTransition(R.anim.left_right,R.anim.rigth_left);
                }
            }
        });
    }

}
