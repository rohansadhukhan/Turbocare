package com.rohan.turboresearch.utils;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.rohan.turboresearch.R;

public class LoadingAlertDialog {

    private final Activity activity;
    private AlertDialog dialog;

    public LoadingAlertDialog(Activity activity) {
        this.activity = activity;
    }

    public void loadDialog(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();

        View v = inflater.inflate(R.layout.loading, null);
        TextView msg = v.findViewById(R.id.message);
        msg.setText(message);
        builder.setView(v);
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();
    }

    public void dismissDialog() {
        if(dialog != null)
            dialog.dismiss();
    }


}
