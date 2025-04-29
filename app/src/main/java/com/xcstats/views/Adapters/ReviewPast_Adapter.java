package com.xcstats.views.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.model.pastgoals.Result;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Textview_Light;

import java.util.List;

/**
 * Created by Mobile on 1/3/2017.
 */

public class ReviewPast_Adapter extends RecyclerView.Adapter<ReviewPast_Adapter.viewholder> {;
    List<Result> results;
    Activity activity;
    public static String[] goalid;

    public ReviewPast_Adapter(List<Result> results,Activity activity) {
        this.results = results;
        this.activity=activity;
        goalid=new String[results.size()];
    }


    @Override
    public ReviewPast_Adapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_past, parent, false);
        return new ReviewPast_Adapter.viewholder(itemView);

    }

    @Override
    public void onBindViewHolder(ReviewPast_Adapter.viewholder holder, @SuppressLint("RecyclerView") final int position) {
        holder.txt_date.setText(results.get(position).getMeetDate());
        holder.txt_meet.setText(results.get(position).getMeetName());
        holder.txt_event.setText(results.get(position).getName());
        holder.txt_goal.setText(results.get(position).getGoalFormat());
        holder.txt_result.setText(results.get(position).getResult());

        goalid[position] =results.get(position).getGoalId();

        holder.edit_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogs.showAllNotesdialog(activity,results,position);
            }
        });

    }

    @Override
    public int getItemCount(){
        return results.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        Textview_Light txt_date,txt_meet,txt_event,txt_goal,txt_result;
        ImageView edit_track;



        public viewholder(View itemView) {
            super(itemView);
            txt_date=(Textview_Light) itemView.findViewById(R.id.txt_date);
            txt_meet=(Textview_Light)itemView.findViewById(R.id.txt_meet);
            txt_event=(Textview_Light)itemView.findViewById(R.id.txt_event);
            txt_goal=(Textview_Light)itemView.findViewById(R.id.txt_goal);
            txt_result=(Textview_Light)itemView.findViewById(R.id.txt_result) ;
            edit_track=(ImageView)itemView.findViewById(R.id.edit_track);




        }
    }


}
