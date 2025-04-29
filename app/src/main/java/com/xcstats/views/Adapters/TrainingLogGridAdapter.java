package com.xcstats.views.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xcstats.R;
import com.xcstats.model.plannedabsence.Workoutdata;
import com.xcstats.views.Activites.TrainingLogEntryActivity;

import java.util.List;

/**
 * Created by Chawla on 1/22/2017.
 */

public class TrainingLogGridAdapter extends BaseAdapter {
    private List<Workoutdata> list;
    Activity activity;

    public TrainingLogGridAdapter(Activity activity, List<Workoutdata> list) {
        this.activity = activity;
        this.list = list;
    }

    @Override
    public int getCount() {
        return 14;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.training_grid, null);
            holder = new Holder();
            holder.text1TV = (TextView) convertView.findViewById(R.id.text1TV);
            holder.text2TV = (TextView) convertView.findViewById(R.id.text2TV);
            holder.addTV=(TextView)convertView.findViewById(R.id.addTV);
            holder.textLL=(LinearLayout)convertView.findViewById(R.id.textLL);
            holder.txtCN= (TextView)convertView.findViewById(R.id.txtCN);
            holder.text3TV = (TextView) convertView.findViewById(R.id.text3TV);
            holder.txtLog = (TextView)convertView.findViewById(R.id.tvlog);
            holder.imageIV = (ImageView)convertView.findViewById(R.id.imageIV);
            convertView.setTag(holder);
        }
        else {

            holder = (Holder) convertView.getTag();
        }

        if (list.get(position).getDistanceDisplay().contains(".")){
            holder.text1TV.setText(list.get(position).getDistanceDisplay()+""+list.get(position).getDistanceTypeDisplay());
        }
        else {
            holder.text1TV.setText(list.get(position).getDistanceDisplay()+".00"+list.get(position).getDistanceTypeDisplay());
        }

        holder.text2TV.setText(list.get(position).getTimeF());
        holder.text3TV.setText(list.get(position).getPace());
        if(list.get(position).getDisplay_image_icon()==1)
        {
            holder.imageIV.setVisibility(View.VISIBLE);
        }else
        {
            holder.imageIV.setVisibility(View.GONE);
        }
        if (!list.get(position).getLogName().equals("null")){
            holder.txtLog.setText(list.get(position).getLogName());

        }

        if (!list.get(position).getCoachNoteFlag().equals("null")){
            holder.txtCN.setText(list.get(position).getCoachNoteFlag());

        }

        holder.textLL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (list.get(position).getRunnerFeel().equals("null")) {
                    list.get(position).getEveryday();
                    Intent intent = new Intent(activity, TrainingLogEntryActivity.class);
                    intent.putExtra("id",list.get(position).getEveryday());
                    intent.putExtra("show_deleteBT", false);
                    intent.putExtra("hidenull","editValue");
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
                }
                else {
                    Intent intent = new Intent(activity, TrainingLogEntryActivity.class);
                    intent.putExtra("show_deleteBT", true);
                    intent.putExtra("editID", list.get(position).getId());
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
                }
            }
        });

        if (list.get(position).getRunnerFeel().equals("null")) {


            holder.text1TV.setVisibility(View.INVISIBLE);
            holder.text2TV.setVisibility(View.GONE);
            holder.text3TV.setVisibility(View.INVISIBLE);
            holder.addTV.setVisibility(View.VISIBLE);
        }
        else
        {

            holder.text1TV.setVisibility(View.VISIBLE);
            holder.text2TV.setVisibility(View.VISIBLE);
            holder.text3TV.setVisibility(View.VISIBLE);
            holder.addTV.setVisibility(View.GONE);

        }

        if (list.get(position).getRunnerFeel().equals("1")){
            holder.textLL.setBackgroundColor(Color.parseColor("#FF1A00"));
        }
        else if (list.get(position).getRunnerFeel().equals("2")){
            holder.textLL.setBackgroundColor(Color.parseColor("#FF9933"));

        }
        else if (list.get(position).getRunnerFeel().equals("3")){
            holder.textLL.setBackgroundColor(Color.parseColor("#FFFF00"));

        }
        else if (list.get(position).getRunnerFeel().equals("4")){
            holder.textLL.setBackgroundColor(Color.parseColor("#00A9EB"));

        }
        else if (list.get(position).getRunnerFeel().equals("5")){
            holder.textLL.setBackgroundColor(Color.parseColor("#66CC33"));

        }

        return convertView;

    }



    class Holder {
        TextView text1TV, text3TV, text2TV,addTV,txtCN,txtLog;
        LinearLayout textLL;
        ImageView imageIV;
    }
}