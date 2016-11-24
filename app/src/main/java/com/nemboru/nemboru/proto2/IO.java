package com.nemboru.nemboru.proto2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Created by nemboru on 21/11/16.
 */

public class IO {
    public static final int PICKER_CODE = 23;
    public static final int CREATOR_CODE = 24;

    public static final void PickTextFile(Activity a){
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("application/json");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        try {
            a.startActivityForResult(Intent.createChooser(intent, "Select a File to Import"),PICKER_CODE);
        } catch (Exception ex) {
            Log.d("error reading file",ex.toString());
        }
    }

    public static final void NewTextFile(Activity a){
        Intent intent = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        intent.setType("application/json");
        intent.putExtra(Intent.EXTRA_TITLE, "dump-"+System.currentTimeMillis()/1000); //epoch
        a.startActivityForResult(intent, CREATOR_CODE);
    }

    public static final void WriteStringToFile(Activity a, String content, Intent resultData) {

        Uri uri = null;
        if (resultData != null) {
            uri = resultData.getData();
            Log.d("IOWRITED :", "Uri: " + uri.toString());
            PrintWriter out = null;
            try {
                out = new PrintWriter(a.getContentResolver().openOutputStream(uri));
                out.write(content);
                Log.d("IOPrinted: ","d "+content);

            } catch (FileNotFoundException e) {
                Log.d("IOError to file ",e.toString());
            }finally {
                out.close();
            }

        }

    }

    public static final String ReadStringFromFile(Activity a, Intent resultData){

        Uri uri = null;
        if (resultData != null) {
            uri = resultData.getData();
            Log.d("IOREADED :", "Uri: " + uri.toString());
            InputStream in = null;
            try {
                in = a.getContentResolver().openInputStream(uri);
            } catch (FileNotFoundException e) {
                Log.d("IOreading from file",e.toString());
            }
            Scanner s = new Scanner(in).useDelimiter("\\A");
            String result = s.hasNext() ? s.next() : "";
            Log.d("IOR :" , "d  " +result);
            return result;
        }

        return null;
    }


}
