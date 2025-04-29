package com.xcstats.views.Adapters;

import android.app.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.model.GetLeader.Results;
import com.xcstats.model.GetLeader.Row;

import java.util.List;

/**
 * Created by Mobile on 1/9/2019.
 */

public class LeadBoardAdapter extends RecyclerView.Adapter<LeadBoardAdapter.viewhold>  {

    public LeadBoardAdapter(Activity activity, List<Row> results) {
        this.activity = activity;
        this.results = results;
    }

    Activity activity;
    List<Row> results;

    @Override
    public LeadBoardAdapter.viewhold onCreateViewHolder(ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.leadview, viewGroup, false);
        return new LeadBoardAdapter.viewhold(itemView);
    }

    @Override
    public void onBindViewHolder( LeadBoardAdapter.viewhold viewhold, int i) {
        viewhold.txt_athlete.setText(""+results.get(i).getAthlete());
        viewhold.txt_rank.setText(""+results.get(i).getRank());
        viewhold.txt_gr.setText(""+results.get(i).getGr());
        viewhold.txt_metric.setText(""+results.get(i).getVal());

    }

    @Override
    public int getItemCount() {
        return results.size();
    }



    public  class viewhold extends RecyclerView.ViewHolder {
        TextView txt_rank,txt_athlete,txt_gr,txt_metric;

        public viewhold(@NonNull View itemView) {
            super(itemView);
            txt_rank = (TextView)itemView.findViewById(R.id.txt_ranks);
            txt_athlete = (TextView)itemView.findViewById(R.id.txt_athletes);
            txt_gr = (TextView)itemView.findViewById(R.id.txt_grs);
            txt_metric = (TextView)itemView.findViewById(R.id.txt_metrics);

        }
    }
}
