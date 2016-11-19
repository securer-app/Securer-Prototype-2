package com.nemboru.nemboru.proto2;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewStub;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Calendar;

// TODO refactor
public class producer extends AppCompatActivity {

    protected SeekBar seekbar;
    protected TextView seekbarInfo;
    protected EditText produceruser;
    protected EditText producerpass;
    protected EditText masterPass;
    protected EditText masterPass2;
    protected EditText producerLabel;
    protected ImageView icon;
    protected Button btnproducer;
    protected View content;
    protected int currentSize;
    protected int seed;
    protected View container_user;
    protected View label_container;
    protected Pair p;

    protected int state;
    protected Animator anim;

    protected boolean checkPasswords(){
        masterPass = (EditText) findViewById(R.id.producer_master);
        masterPass2 = (EditText) findViewById(R.id.producer_master_2);
        return masterPass.getText().toString().compareTo(masterPass2.getText().toString()) == 0;
    }

    protected void setSeekbarAndIcon(){

        seekbar = (SeekBar) findViewById(R.id.seekBar);
        seekbarInfo = (TextView) findViewById(R.id.seekbar_info);
        producerpass = (EditText) findViewById(R.id.producer_pass);

        icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                seed *= 5;
                producerpass.setText(roll.generate(currentSize,seed));
            }
        });
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
    }

    protected Pair doCipher(){
        Pair toret = new Pair();
        String pass = masterPass.getText().toString();
        toret.user = AESWrapper.Encrypt(pass,produceruser.getText().toString());
        toret.pass = AESWrapper.Encrypt(pass,producerpass.getText().toString());
        toret.title = producerLabel.getText().toString();
        toret.title = toret.title.substring(0,1).toUpperCase() + p.title.substring(1,p.title.length());
        return toret;
    }

    protected void newAnim(View c){
        int cx = content.getWidth() / 2;
        int cy = content.getHeight() / 2;

        // get the final radius for the clipping circle
        float finalRadius = (float) Math.hypot(cx, cy);

        // create the animator for this view (the start radius is zero)
        anim = ViewAnimationUtils.createCircularReveal(content, cx, cy, 0, finalRadius);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_producer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        currentSize = 2;
        seed = 213;
        state = 0;
        p = new Pair();

        btnproducer = (Button) findViewById(R.id.button_producer);
        icon = (ImageView) findViewById(R.id.producer_status);
        content = findViewById(R.id.content_producer);

        container_user = findViewById(R.id.user_container);
        label_container = findViewById(R.id.label_container);

        producerLabel = (EditText) findViewById(R.id.producer_label);
        produceruser = (EditText) findViewById(R.id.producer_user);


        /**/

        /**/

        btnproducer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (state == 0){

                    newAnim(content);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            icon.setImageResource(R.drawable.ic_vpn_key_black_24dp);
                            label_container.setVisibility(View.GONE);
                            container_user.setVisibility(View.GONE);
                            ViewStub t = (ViewStub) findViewById(R.id.userkey_producer);
                            t.inflate();
                            setSeekbarAndIcon();
                        }
                    });

                    anim.setDuration(300);
                    anim.start();
                    state++;
                }else if (state == 1){
                    newAnim(content);
                    anim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationStart(Animator animation) {
                            icon.setImageResource(R.drawable.ic_memory_black_24dp);
                            findViewById(R.id.keyproducer_producer).setVisibility(View.GONE);

                            ViewStub t = (ViewStub) findViewById(R.id.key_producer);
                            t.inflate();
                        }
                    });
                    anim.setDuration(300);
                    anim.start();
                    state++;
                }else if (state == 2) {
                    if(checkPasswords()) {
                        newAnim(content);
                        anim.addListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationStart(Animator animation) {
                                content.setBackgroundColor(Color.parseColor("#3e4c59"));
                                icon.setImageResource(R.drawable.ic_lock_black_24dp);
                                icon.setColorFilter(getResources().getColor(R.color.text));
                                findViewById(R.id.key_producer).setVisibility(View.GONE);
                                ViewStub t = (ViewStub) findViewById(R.id.success_producer);
                                t.inflate();
                                btnproducer.setText("Back");
                            }
                        });

                        anim.setDuration(300);
                        anim.start();
                        state++;
                    }
                }else if(state == 3){
                    setResult(RESULT_OK,new Intent().putExtra("pair",doCipher()));
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
