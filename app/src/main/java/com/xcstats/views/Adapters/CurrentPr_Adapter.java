package com.xcstats.views.Adapters;

import android.app.Activity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.model.trackpr.Result;

import java.util.List;

/**
 * Created by Mobile on 12/30/2016.
 */

public class CurrentPr_Adapter extends RecyclerView.Adapter<CurrentPr_Adapter.viewholder> {
    List<Result> results;
    Activity activity;

    public CurrentPr_Adapter(List<Result> results, Activity activity) {
        this.results = results;
        this.activity = activity;
    }

    @Override
    public CurrentPr_Adapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trackpr_view, parent, false);

        return new CurrentPr_Adapter.viewholder(itemView);
    }


    @Override
    public void onBindViewHolder(CurrentPr_Adapter.viewholder holder, int position) {
        holder.txt_event.setText(results.get(position).getEvent());
        holder.txt_meter.setText(results.get(position).getSr());
        String eventrecord = "" + Html.fromHtml(results.get(position).getSr());
        int a = eventrecord.length();
        if(a>0){
            String time = eventrecord.substring(0, eventrecord.indexOf("("));
            String date = eventrecord.substring(eventrecord.indexOf("("), a);
            holder.txt_meter.setText(time);
            holder.txt_countdates.setText(date);
           // holder.txt_meter.setText(time+"\n"+ date);
            System.out.println(a);
        }else{
            holder.txt_meter.setText("");
        }



        String seasonrecord="" +Html.fromHtml(results.get(position).getPr());
        int b = seasonrecord.length();
        String time1 = seasonrecord.substring(0, seasonrecord.indexOf("("));
        String date1 = seasonrecord.substring(seasonrecord.indexOf("("), b);
        holder.txt_count.setText(time1);
        holder.txt_countdate.setText(date1);
      //  holder.txt_count.setText(time1+"\n"+ date1);
        System.out.println(b);


    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    public class viewholder extends RecyclerView.ViewHolder {
        TextView txt_event, txt_count, txt_meter,txt_countdate,txt_countdates;

        public viewholder(View itemView) {
            super(itemView);
            txt_event = (TextView) itemView.findViewById(R.id.txt_event);
            txt_count = (TextView) itemView.findViewById(R.id.txt_count);
            txt_meter = (TextView) itemView.findViewById(R.id.txt_meter);
            txt_countdate = (TextView)itemView.findViewById(R.id.txt_countdate);
            txt_countdates=(TextView)itemView.findViewById(R.id.txt_countdates);


        }
    }
}
