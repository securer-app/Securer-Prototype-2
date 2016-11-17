package com.nemboru.nemboru.proto2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Calendar;

public class producer extends AppCompatActivity {

    protected SeekBar seekbar;
    protected TextView seekbarInfo;
    protected EditText producerpass;
    protected EditText masterPass;
    protected ImageView icon;
    protected Button btnproducer;
    protected View content;
    protected int currentSize;
    protected int seed;
    protected boolean encrypted;
    protected View success;
    protected View container_pass;
    protected View container_user;
    protected View container_size;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        currentSize = 2;
        seed = 213;

        btnproducer = (Button) findViewById(R.id.button_producer);
        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbarInfo = (TextView) findViewById(R.id.seekbar_info);
        producerpass = (EditText) findViewById(R.id.producer_pass);
        icon = (ImageView) findViewById(R.id.producer_status);
        content = findViewById(R.id.content_producer);
        success = findViewById(R.id.success);

        container_pass = findViewById(R.id.pass_container);
        container_size = findViewById(R.id.size_container);
        container_user = findViewById(R.id.user_container);

        masterPass = (EditText) findViewById(R.id.master_pass);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentSize = (progress / 10)+2;
                seed += progress;
                seekbarInfo.setText(currentSize*2+" letters");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                producerpass.setText(roll.generate(currentSize,seed));
            }
        });

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seed *= 5;
                producerpass.setText(roll.generate(currentSize,seed));
            }
        });

        btnproducer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!encrypted) {
                    encrypted = true;
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
                            content.setBackgroundColor(Color.parseColor("#3e4c59"));
                            icon.setImageResource(R.drawable.ic_lock_black_24dp);
                            icon.setColorFilter(getResources().getColor(R.color.text));
                            success.setVisibility(View.VISIBLE);
                            container_user.setVisibility(View.GONE);
                            container_size.setVisibility(View.GONE);
                            seekbar.setVisibility(View.GONE);
                            container_pass.setVisibility(View.GONE);
                            masterPass.setVisibility(View.GONE);
                            btnproducer.setText("Back");
                        }
                    });

                    //content.setBackgroundColor(Color.BLACK);
                    //content.setVisibility(View.VISIBLE);
                    anim.setDuration(300);
                    anim.start();
                } else {
                    finish();
                    overridePendingTransition(R.anim.left_right_2,R.anim.right_left_2);
                }
            }
        });

        Snackbar.make(findViewById(android.R.id.content),"Touch the key icon to generate a password",Snackbar.LENGTH_SHORT).show();

    }

    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.left_right_2,R.anim.right_left_2);
    }

}
