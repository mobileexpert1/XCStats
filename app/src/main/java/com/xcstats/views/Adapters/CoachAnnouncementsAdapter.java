package com.xcstats.views.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.announcementsProp.Result;
import com.xcstats.views.Activites.CoachAnnouncementsActivity;
import com.xcstats.views.Activites.CrossDetailActivity;
import com.xcstats.views.Activites.EmailCoachActivity;
import com.xcstats.views.Activites.HomeActivity;
import com.xcstats.views.Activites.WebViewActivity;
import com.xcstats.views.custom.Button_simple;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CoachAnnouncementsAdapter extends RecyclerView.Adapter<CoachAnnouncementsAdapter.viewholder> {

    private Activity activity;
    private List<Result> announcementList;
    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";

    public CoachAnnouncementsAdapter(Activity activity, List<Result> announcementList) {
        this.activity = activity;
        this.announcementList = announcementList;
    }


    @Override
    public CoachAnnouncementsAdapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.announcements, parent, false);
        return new CoachAnnouncementsAdapter.viewholder(itemView);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(final CoachAnnouncementsAdapter.viewholder holder, @SuppressLint("RecyclerView") final int position) {
        if (announcementList.get(position).getFontWeight().equals("normal")){
           holder.txt_dte.setTextAppearance(R.style.fontNormal);
           holder.coachNameTV.setTextAppearance(R.style.fontNormal);
           holder.subjectTV.setTextAppearance(R.style.fontNormal);

        }
        else {
            holder.txt_dte.setTextAppearance(R.style.fontBold);
            holder.coachNameTV.setTextAppearance(R.style.fontBold);
            holder.subjectTV.setTextAppearance(R.style.fontBold);
        }

        holder.txt_dte.setText(announcementList.get(position).getPostDay());
        holder.coachNameTV.setText(announcementList.get(position).getCoachName());
        holder.subjectTV.setText(announcementList.get(position).getSubjectText());
        holder.subjectTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(activity, WebViewActivity.class);
                intent.putExtra("url",announcementList.get(position).getSubjectUrl());

                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);

//                openBrowser(activity,announcementList.get(position).getSubjectUrl());

            }
        });

        holder.replyBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, EmailCoachActivity.class);
                intent.putExtra("type", 2);
                intent.putExtra("coachId", announcementList.get(position).getCoachId());
                intent.putExtra("subjectText", announcementList.get(position).getSubjectText());
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
            }
        });

    }

    @Override
    public int getItemCount() {
        return announcementList.size();
    }


    public static void openBrowser(final Context context, String url) {

        if (!url.startsWith(HTTP) && !url.startsWith(HTTPS)) {
            url = HTTP + url;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(Intent.createChooser(intent, "Choose browser"));// Choose browser is arbitrary :)

    }




    public class viewholder extends RecyclerView.ViewHolder {
        TextView txt_dte, coachNameTV, subjectTV;
        Button_simple replyBT;

        public viewholder(View itemView) {
            super(itemView);
            txt_dte = (TextView) itemView.findViewById(R.id.txt_dte);
            coachNameTV = (TextView) itemView.findViewById(R.id.coachNameTV);
            subjectTV = (TextView) itemView.findViewById(R.id.subjectTV);
            replyBT = (Button_simple) itemView.findViewById(R.id.replyBT);


        }
    }
}
