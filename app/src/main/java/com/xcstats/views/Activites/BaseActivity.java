package com.xcstats.views.Activites;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.xcstats.api.PermissionStatus;

/**
 * Created by Mobile on 8/17/2018.
 */

public class BaseActivity extends AppCompatActivity {

    PermissionStatus listener;
    private String permission;


    public void setPermissionListener(PermissionStatus listener) {
        this.listener = listener;
    }

    public boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.permission = permission;
            int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) this, new String[]{permission}, 111);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }




    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 111: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    listener.PermissionStatus(permission, 1);
                } else {
                    listener.PermissionStatus(permission, 0);
                }
                return;
            }
        }
    }
}


