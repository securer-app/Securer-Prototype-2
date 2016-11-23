package com.nemboru.nemboru.proto2;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nemboru on 23/11/16.
 */

public class emptyGuard {
    protected List<EditText> editTexts;

    public emptyGuard(){
        editTexts = new ArrayList<>();
    }


    public void addGuard(EditText t){
        editTexts.add(t);
    }

    public boolean check(){
        boolean ok = true;
        for(EditText t: editTexts){
            if(t.getText().toString().compareTo("") == 0){ // if is empty
                t.setError("This field is required");
                ok = false;
            }
        }
        return ok;
    }
}
