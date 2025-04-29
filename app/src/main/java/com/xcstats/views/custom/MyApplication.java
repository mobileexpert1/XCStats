package com.xcstats.views.custom;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.StrictMode;

import androidx.annotation.RequiresApi;
import androidx.multidex.MultiDex;

public class MyApplication extends Application {
 

 
    @RequiresApi(api = Build.VERSION_CODES.TIRAMISU)
    @Override
    public void onCreate() {
        super.onCreate();
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
//        Fabric.with(this, new Crashlytics());
        IntentFilter intentFilter=new IntentFilter();
        intentFilter.addAction("checkinternet");
        CheckInternetConnection checkInternetConnection=new CheckInternetConnection();
//        registerReceiver(checkInternetConnection,intentFilter);
        registerReceiver(checkInternetConnection,intentFilter, Context.RECEIVER_NOT_EXPORTED);
        sendBroadcast(new Intent("checkinternet"));
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext (base);
        MultiDex.install (this);
    }

}