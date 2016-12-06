package com.nemboru.nemboru.proto2;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nemboru on 6/12/16.
 * It syncs things with firebase
 */

public class FireIO {

    String user;
    FirebaseDatabase db;
    DatabaseReference dbRef;
    AlphabeticList list;

    public FireIO(String user, AlphabeticList l){
        this.user = user;
        db = FirebaseDatabase.getInstance();
        dbRef = db.getReference(user);
        list = l;

        ChildEventListener listen = new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Pair p = dataSnapshot.getValue(Pair.class);
                list.addPair(p);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        dbRef.addChildEventListener(listen);
    }

    public void add(Pair it){
        dbRef.push().setValue(it);
    }

}
