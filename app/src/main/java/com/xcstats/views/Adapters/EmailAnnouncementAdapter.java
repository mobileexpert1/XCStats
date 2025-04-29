package com.xcstats.views.Adapters;

import android.app.Activity;
import android.os.Build;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.model.emailcoach.CoachResult;
import com.xcstats.model.emailcoach.Result;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class EmailAnnouncementAdapter extends RecyclerView.Adapter<EmailAnnouncementAdapter.viewholder> {
    private List<Result> results;
    private boolean status = true;
    Activity activity;
    public static ArrayList<String> ids = new ArrayList<>();
    private String checked;


    public EmailAnnouncementAdapter(List<Result> results, Activity activity) {
        this.results = results;
        ids.clear();
        if (results.size() != 0)
            this.results.get(0).setCoachId("-1");
        this.activity = activity;


    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView textView;
        CheckBox checkBox;

        public viewholder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.txt_selectCoach);
            checkBox = (CheckBox) itemView.findViewById(R.id.checkbox);
        }
    }

    @Override
    public EmailAnnouncementAdapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        final View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.coach_select, parent, false);

        EmailAnnouncementAdapter.viewholder holder = new EmailAnnouncementAdapter.viewholder(itemView);
        itemView.setTag(holder);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EmailAnnouncementAdapter.viewholder holder = (EmailAnnouncementAdapter.viewholder) v.getTag();
                checkboxHandling(holder.getPosition());

            }
        });


        return holder;

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(EmailAnnouncementAdapter.viewholder holder, final int position) {
        holder.textView.setText(results.get(position).getCoachName());

        //  deleteItem();
        // results.remove(0);

     /*  else {
            holder.checkBox.setChecked(false);
        }*/

        if (results.get(position).isB()) {
            if (results.get(position).getChecked().equalsIgnoreCase("checked")) {
                holder.checkBox.setChecked(true);
            }
            //  holder.checkBox.setChecked(true);
        } else {
            holder.checkBox.setChecked(false);
        }
        holder.checkBox.setTag(position);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int pos = (int) view.getTag();
                checkboxHandling(pos);
            }
        });


    }


    private void checkboxHandling(int pos) {

        if (pos == 0) {

            handleState(results.get(pos).isB() ? false : true, pos);

        } else {
            handleState(results.get(pos).isB() ? false : true, pos);

        }

        notifyDataSetChanged();
    }

    private void handleState(boolean b, int pos) {
        if (pos == 0) {
            for (int i = 0; i < results.size(); i++) {
                results.get(i).setB(b);
                if (b) {
                    ids.add(results.get(i).getCoachId());
                } else {
                    if (ids.size() != 0)
                        ids.clear();
                }
            }
        } else {

            results.get(pos).setB(b);
            if (b) {
                if (!ids.contains(results.get(pos).getCoachId())) {
                    ids.add(results.get(pos).getCoachId());
                }
            } else {
                if (ids.contains(results.get(pos).getCoachId()))
                    ids.remove(results.get(pos).getCoachId());

            }
        }

        if (pos != 0) {
            if (ids.size() == (results.size() - 1) && !ids.contains("-1")) {
                if (results.size() != 0)
                    results.get(0).setB(true);
                ids.add(results.get(0).getCoachId());
            } else {
                results.get(0).setB(false);
                if (ids.contains(results.get(0).getCoachId()))
                    ids.remove(results.get(0).getCoachId());
            }
        }
    }


    @Override
    public int getItemCount() {
        return results.size();
    }

}

