package com.nemboru.nemboru.proto2;

import java.util.Random;

public class roll {
    public static final String x;
    public static final String y;
    public static final String special;

    public static final int xsize;
    public static final int ysize;
    public static final int specialsize;

    static {
        x = "bcdfghjklmnpqrstvwxyz";
        y = "aeiou";
        special = "!@#$%&--==/?;;[[]]";
        xsize = x.length();
        ysize = y.length();
        specialsize = special.length();
    }

    public static void addSyl(StringBuffer buff, Random r){
        int t = r.nextInt(xsize);
        int q = r.nextInt(ysize);

        if(r.nextInt() % 2 == 0){
            buff.append(x.substring(t,t+1));
            buff.append(y.substring(q,q+1));

        }else{
            buff.append(y.substring(q,q+1));
            buff.append(x.substring(t,t+1));
        }
    }

    public static void addSpecial(StringBuffer buff, Random r){
        int t = r.nextInt(specialsize);
        int q = r.nextInt(ysize);

        if(r.nextInt()% 2 == 0){
            buff.append(special.substring(t,t+1));
            buff.append(y.substring(q,q+1));
        }else{
            buff.append(y.substring(q,q+1));
            buff.append(special.substring(t,t+1));
        }
    }

    public static String generate(int n, int seed){
        Random r  = new Random(seed);
        StringBuffer t = new StringBuffer();
        for(int i=0;i<n;i++){
            if(r.nextInt() % 3 == 0){
                addSpecial(t, r);
            }else{
                addSyl(t, r);
            }
        }
        return t.toString();
    }
}
