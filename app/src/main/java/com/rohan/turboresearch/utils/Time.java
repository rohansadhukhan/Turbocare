package com.rohan.turboresearch.utils;

import android.util.Log;

import java.util.concurrent.TimeUnit;

public class Time {

    public static String convertMillis(long millis) {
        String time = null;

        long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(millis);
        long milliSecond = TimeUnit.MILLISECONDS.toMillis(millis);

        if(minutes > 0) {
            time = Long.toString(minutes) + "min";
        } else if(seconds > 0) {
            time = Long.toString(seconds) + "sec";
        } else if(milliSecond > 0) {
            time = Long.toString(milliSecond) + "ms";
        }

        Log.d(Constants.TAG, "Request time: " + time);

        if(time == null) return "NIL";
        return time;
    }

}
