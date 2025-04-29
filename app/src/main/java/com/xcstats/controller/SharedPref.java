package com.xcstats.controller;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Mobile on 12/28/2016.
 */

public class SharedPref {

    public static void setString(Context ctx, String var_name, String var_value) {
        SharedPreferences pref = ctx.getSharedPreferences(SharedConstants.PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString(var_name,var_value);
        edit.commit();
    }

    public static String getString(Activity act,String var_name) {
        SharedPreferences pref = act.getSharedPreferences(SharedConstants.PREF_NAME,Context.MODE_PRIVATE);
        return pref.getString(var_name,"");
    }

    public static void clear(Activity act) {
        SharedPreferences pref = act.getSharedPreferences(SharedConstants.PREF_NAME,Context.MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.clear();
        edit.commit();
    }


}