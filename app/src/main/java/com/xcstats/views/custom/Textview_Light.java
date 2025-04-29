package com.xcstats.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xcstats.controller.Fonts;

/**
 * Created by Mobile on 1/3/2017.
 */

public class Textview_Light extends TextView {
    public Textview_Light(Context context) {
        super(context);
        init();
    }

    public Textview_Light(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Textview_Light(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    public void init(){

        if (isInEditMode()) {
            return;
        }

        setTypeface(Fonts.setlight(getContext()));


    }


}
