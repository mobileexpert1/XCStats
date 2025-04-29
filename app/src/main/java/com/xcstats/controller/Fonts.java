package com.xcstats.controller;

import android.content.Context;
import android.graphics.Typeface;

/**
 * Created by Mobile on 12/27/2016.
 */

public class Fonts {

    public static Typeface setbold(Context act){
            return Typeface.createFromAsset(act.getAssets(), "fonts/Roboto-Bold_5.ttf");
        }

        public static Typeface setlight(Context act){
            return Typeface.createFromAsset(act.getAssets(), "fonts/Roboto-Light_5.ttf");
        }

        public static Typeface setmedium(Context act){
            return Typeface.createFromAsset(act.getAssets(), "fonts/Roboto-Medium_5.ttf");
        }
    }


