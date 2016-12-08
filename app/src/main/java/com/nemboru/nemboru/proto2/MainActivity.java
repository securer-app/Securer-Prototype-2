package com.nemboru.nemboru.proto2;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    protected ListView l;
    protected AlphabeticList a;
    protected FireIO fireio;

    public static int PRODUCER_CODE = 2;
    public static int SIGIN = 324;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(new Intent(MainActivity.this,producer.class),MainActivity.PRODUCER_CODE);
                overridePendingTransition(R.anim.left_right,R.anim.rigth_left);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        l = (ListView) findViewById(R.id.list);

        a = new AlphabeticList(this,l);

        fireio = new FireIO(getIntent().getStringExtra("user"),a);

        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(MainActivity.this,detail.class);
                i.putExtra("pair",a.arrayData.get(position));
                startActivity(i);
                overridePendingTransition(R.anim.left_right_2,R.anim.right_left_2);
            }
        });

        l.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
                b.setMessage("Do you want to delete this item?");
                b.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                });

                b.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        fireio.remove(position);
                    }
                });

                b.show();
                return true;
            }
        });

        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(item.getItemId() == R.id.Logout){
            FirebaseAuth.getInstance().signOut();
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.new_navbar){
            startActivityForResult(new Intent(MainActivity.this,producer.class),MainActivity.PRODUCER_CODE);
            overridePendingTransition(R.anim.left_right,R.anim.rigth_left);
        }else if(id == R.id.faq){
            Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://securer-app.github.io/faq.html"));
            MainActivity.this.startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PRODUCER_CODE) {
            if (resultCode == RESULT_OK) {
                Pair p = data.getParcelableExtra("pair");
                fireio.add(p);
            }
        }

        if (requestCode == SIGIN) {
            if (resultCode == RESULT_OK) {
                // user is signed in!
                Snackbar.make(findViewById(android.R.id.content),"Signed",Snackbar.LENGTH_SHORT);
            }
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
    }

    protected void showEmptyDialog(){
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setMessage("Seems that you don't have any passwords saved");
        d.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //
            }
        });
        d.setPositiveButton("Add one", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                startActivityForResult(new Intent(MainActivity.this,producer.class),MainActivity.PRODUCER_CODE);
                overridePendingTransition(R.anim.left_right,R.anim.rigth_left);
            }
        });
        d.show();
    }

    @Override
    protected void onResume(){
        super.onResume();
    }

}
