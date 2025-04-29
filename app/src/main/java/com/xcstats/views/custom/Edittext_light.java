package com.xcstats.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.xcstats.controller.Fonts;

/**
 * Created by Mobile on 1/3/2017.
 */

public class Edittext_light extends EditText {
    public Edittext_light(Context context) {
        super(context);
        init();
    }

    public Edittext_light(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Edittext_light(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        if (isInEditMode()) {
            return;
        }

        setTypeface(Fonts.setlight(getContext()));


    }
}

