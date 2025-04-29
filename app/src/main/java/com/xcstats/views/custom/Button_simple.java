package com.xcstats.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Button;

import com.xcstats.controller.Fonts;

/**
 * Created by Mobile on 1/3/2017.
 */

public class Button_simple extends Button {
    public Button_simple(Context context) {
        super(context);
        init();
    }

    public Button_simple(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Button_simple(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        if (isInEditMode()) {
            return;
        }

        setTypeface(Fonts.setmedium(getContext()));


    }
}

