package com.binary.giphy.utils;

import android.util.Log;

import com.binary.giphy.BuildConfig;

/**
 * Created by duong on 9/20/2017.
 */

public class RLog {
    private static final String TAG = "Nha5s";

    public static void v(Object message){
        if(BuildConfig.DEBUG){
            Log.v(TAG, String.valueOf(message));
        }
    }

    public static void d(Object message){
        if(BuildConfig.DEBUG){
            Log.d(TAG, String.valueOf(message));
        }
    }

    public static void i(Object message){
        if(BuildConfig.DEBUG){
            Log.i(TAG, String.valueOf(message));
        }
    }

    public static void w(Object message){
        if(BuildConfig.DEBUG){
            Log.w(TAG, String.valueOf(message));
        }
    }

    public static void e(Object message){
        if(BuildConfig.DEBUG){
            Log.e(TAG, String.valueOf(message));
        }
    }
}
