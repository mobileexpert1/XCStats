package com.xcstats.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import com.xcstats.controller.Fonts;

/**
 * Created by Mobile on 12/27/2016.
 */

public class Textview_Simple extends TextView {


    public Textview_Simple(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Textview_Simple(Context context) {
        super(context);
        init();
    }

    public Textview_Simple(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init(){

        if (isInEditMode()) {
                return;
            }

            setTypeface(Fonts.setmedium(getContext()));


        }


}
