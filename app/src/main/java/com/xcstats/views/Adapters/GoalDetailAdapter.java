package com.xcstats.views.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.xcstats.R;

/**
 * Created by Mobile on 2/3/2017.
 */

public class GoalDetailAdapter extends ArrayAdapter<String>{

    Context context;
    String[] objects;
    String firstElement;
    boolean isFirstTime;
    String textviewid;


    public GoalDetailAdapter(Context context, int resource, String txt, String[] objects) {
        super(context, resource, 0, objects);
        this.context = context;
        this.objects = objects;
        this.textviewid = txt;
        this.isFirstTime = true;
        setDefaultText("Select Event");
    }



    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {

        if (isFirstTime) {
            objects[0] = firstElement;
            isFirstTime = false;
        }
        return getCustomView(position, convertView, parent);

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        notifyDataSetChanged();
        return getCustomView(position, convertView, parent);
    }


    public void setDefaultText(String defaultText) {
        this.firstElement = objects[0];
        objects[0] = defaultText;
    }


    public View getCustomView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row = inflater.inflate(R.layout.spinner_row, parent, false);
        TextView label = (TextView) row.findViewById(R.id.spinner_text);
        label.setText(objects[position]);
        return row;
    }



}
