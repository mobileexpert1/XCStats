package com.xcstats.views.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.crossgoals.Result;
import com.xcstats.views.Activites.CrossDetailActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Mobile on 1/7/2017.
 */

public class CrossGoal_Adapter extends RecyclerView.Adapter<CrossGoal_Adapter.viewholder> {

    private Activity activity;
    private List<Result> results;

    public CrossGoal_Adapter(Activity activity, List<Result> results) {
        this.activity = activity;
        this.results = results;
    }


    @Override
    public CrossGoal_Adapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.crosscountry, parent, false);
        return new CrossGoal_Adapter.viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(final CrossGoal_Adapter.viewholder holder, final int position) {
        holder.txt_dte.setText(results.get(position).getDisplayDate());
        holder.txt_evnt.setText(results.get(position).getEvent());
        holder.txt_course.setText(results.get(position).getCourse());

        int editableValue = results.get(position).getEditable();
        Log.d("editableValue",""+editableValue);

        Calendar now = Calendar.getInstance();
        final SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        final String curDate = formatter.format(now.getTimeInMillis());

        try {
            Date date = formatter.parse(results.get(position).getMeetDate());

        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        if (results.get(position).getDisplayPace() == null) {
            holder.txt_goalpace.setText("-");
        }
        else {
            holder.txt_goalpace.setText(results.get(position).getDisplayPace() + "");
        }
        if (results.get(position).getDisplayTime() == null) {
            holder.txt_time.setText("-");
        }
        else {
            holder.txt_time.setText(results.get(position).getDisplayTime() + "");
        }


        /////  New editvalue logic implementation Here

        if (results.get(position).getDisplayGoal() == null){
               if (editableValue == 1) {
                  holder.txt_goal.setText("Add");
                  holder.txt_goal.setTextColor(Color.parseColor("#FF9933"));
                  holder.txt_goal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(activity, CrossDetailActivity.class);
                        if (results.get(position).getDisplayGoal() == null) {
                            intent.putExtra("show_delBT", false);
                        }
                        else {
                            intent.putExtra("show_delBT", true);
                        }
                        activity.startActivity(intent);
                        SharedPref.setString(activity,"id",results.get(position).getEventNum());
                        activity.overridePendingTransition(R.anim.pull_inright,R.anim.push_outleft);
                        activity.finish();
                    }
                  });
            }
            else {
                holder.txt_goal.setText("-");
            }
      }
      else {
            if (editableValue == 0 && results.get(position).getDisplayGoal() != null){
                holder.txt_goal.setText(results.get(position).getDisplayGoal() + "");
            }
            else {
                holder.txt_goal.setText(results.get(position).getDisplayGoal() + "");
                holder.txt_goal.setTextColor(Color.parseColor("#FF9933"));
                holder.txt_goal.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(activity, CrossDetailActivity.class);
                        intent.putExtra("show_delBT",results.get(position).getDisplayGoal() == null ? false : true);
                        activity.startActivity(intent);
                        SharedPref.setString(activity,"id",results.get(position).getEventNum());
                        activity.overridePendingTransition(R.anim.pull_inright,R.anim.push_outleft);
                        activity.finish();
                    }
                });
            }
        }
    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txt_dte,txt_evnt,txt_course,txt_goal,txt_goalpace,txt_time;


        public viewholder(View itemView) {
            super(itemView);
            txt_dte = (TextView) itemView.findViewById(R.id.txt_dte);
            txt_evnt = (TextView) itemView.findViewById(R.id.txt_evnt);
            txt_course = (TextView) itemView.findViewById(R.id.txt_course);
            txt_goal = (TextView) itemView.findViewById(R.id.txt_goal);
            txt_goalpace = (TextView) itemView.findViewById(R.id.txt_goalpace);
            txt_time = (TextView) itemView.findViewById(R.id.txt_time);


        }
    }
}
