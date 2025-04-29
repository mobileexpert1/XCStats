package com.xcstats.views.custom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

public class CheckInternetConnection extends BroadcastReceiver {


    static Context context;

    public CheckInternetConnection() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent arg1) {

        this.context = context;


        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        if (!isConnected) {

            Toast.makeText(context, "Internet Connection Lost", Toast.LENGTH_LONG).show();
            return;
        }
        context.sendBroadcast(arg1);
    }
    }


