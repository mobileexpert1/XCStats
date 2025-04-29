package com.xcstats.views.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.model.trackfield.Result;
import com.xcstats.views.Activites.GoalDetailActivity;
import com.xcstats.views.Dialogs.dialogs;

import java.util.List;

/**
 * Created by Mobile on 12/30/2016.
 */

public class TrackField_Adapter extends RecyclerView.Adapter<TrackField_Adapter.viewholder> {

    private int deleteGoals;

    public TrackField_Adapter(List<Result> results, Activity activity) {
        this.results = results;
        this.activity = activity;
    }

    List<Result> results;
    Activity activity;

    @Override
    public TrackField_Adapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.trackfield_view, parent, false);

        return new TrackField_Adapter.viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(final TrackField_Adapter.viewholder holder, @SuppressLint("RecyclerView") final int position) {

            holder.txt_date.setText(results.get(position).getShortDate());
            holder.txt_meet.setText(results.get(position).getMeetName());
            holder.txt_event.setText(results.get(position).getEventName());
            holder.txt_goal.setText(results.get(position).getGoal());
            holder.txt_goal.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     dialogs.viewUpcomingGoal(activity,results,position);

                 }
             });


        holder.edit_track.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(activity, GoalDetailActivity.class);
                    intent.putExtra("check",true);
                    intent.putExtra("meet_name",results.get(position).getMeetName());
                    intent.putExtra("event_name",results.get(position).getEventName());
                    intent.putExtra("notes_before",results.get(position).getNotesBefore());
                    intent.putExtra("track_id",results.get(position).getId());
                 //   SharedPref.setString(activity,"track_id",results.get(position).getId());
                    activity.startActivity(intent);
                    activity.overridePendingTransition(R.anim.pull_inright,R.anim.push_outleft);
                    activity.finish();
                }
            });


          holder.delete_track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteGoals=position;
                alertDeleteGoals(activity);

            }

          });

    }

    public void alertDeleteGoals(final Activity activity){

        AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setMessage(R.string.delete);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialogs.progressdialog(activity);
                WebserviceResult.deleteTrack(activity,results.get(deleteGoals).getId());


            }

        });

        notifyDataSetChanged();



        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.show();

    }





    @Override
    public int getItemCount() {
        return results.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {

        TextView txt_date,txt_meet,txt_event,txt_goal;
        ImageView edit_track,delete_track;


        public viewholder(View itemView) {
            super(itemView);
            txt_date=(TextView)itemView.findViewById(R.id.txt_date);
            txt_meet=(TextView)itemView.findViewById(R.id.txt_meet);
            txt_event=(TextView)itemView.findViewById(R.id.txt_event);
            txt_goal=(TextView)itemView.findViewById(R.id.txt_goal);
            edit_track=(ImageView) itemView.findViewById(R.id.edit_track);
            delete_track=(ImageView) itemView.findViewById(R.id.delete_track);

        }
    }
}
