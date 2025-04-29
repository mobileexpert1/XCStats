package com.xcstats.views.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

import com.xcstats.controller.Fonts;

/**
 * Created by Mobile on 12/14/2016.
 */

public class Edittext_Simple extends EditText {
    public Edittext_Simple(Context context) {
        super(context);
        init();
    }

    public Edittext_Simple(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Edittext_Simple(Context context, AttributeSet attrs, int defStyleAttr) {
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
