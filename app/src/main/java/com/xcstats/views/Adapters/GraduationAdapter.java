package com.xcstats.views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.xcstats.R;

import java.util.ArrayList;

/**
 * Created by Mobile on 2/2/2017.
 */

public class GraduationAdapter extends ArrayAdapter<String> {

    Context context;
    ArrayList<String> objects;
    String firstElement;
    boolean isFirstTime;
    String textviewid;


    public GraduationAdapter(Context context, int resource, String txt, ArrayList<String> objects) {
        super(context, resource, 0, objects);
        this.context = context;
        this.objects = objects;
        this.textviewid = txt;
        this.isFirstTime = true;

    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        return getCustomView(position, convertView, parent);

    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        return getCustomView(position, convertView, parent);
    }





    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        TextView label = (TextView) row.findViewById(R.id.spinner_text);
        label.setText(objects.get(position));
        return row;
    }

}


