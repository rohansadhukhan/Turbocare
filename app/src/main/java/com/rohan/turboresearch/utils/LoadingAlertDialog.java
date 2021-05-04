package com.rohan.turboresearch.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;

import com.rohan.turboresearch.R;

public class LoadingAlertDialog {

    private final Activity activity;
    private AlertDialog dialog;

    public LoadingAlertDialog(Activity activity) {
        this.activity = activity;
    }

    public void loadDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        builder.setView(inflater.inflate(R.layout.loading, null));
        builder.setCancelable(true);
        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog() {
        if(dialog != null)
            dialog.dismiss();
    }


}
