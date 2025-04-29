package com.xcstats.views.Adapters;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.absenceplanned.Result;
import com.xcstats.views.Dialogs.dialogs;

import java.util.List;

/**
 * Created by Mobile on 12/29/2016.
 */

public class PlannedAbsence_Adapter extends RecyclerView.Adapter<PlannedAbsence_Adapter.showabsence> {
    private int pos;

    public PlannedAbsence_Adapter(List<Result> results, Activity activity) {
        this.results = results;
        this.activity = activity;
    }
    List<Result> results;
    Activity activity;

    @Override
    public PlannedAbsence_Adapter.showabsence onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.planed_recyleview, parent, false);

        return new PlannedAbsence_Adapter.showabsence(itemView);

    }

    @Override
    public void onBindViewHolder(PlannedAbsence_Adapter.showabsence holder, final int position) {

        holder.txt_date.setText(results.get(position).getAbsentDate());
        holder.txt_vaction.setText(results.get(position).getEaReason());
        holder.txt_time.setText(results.get(position).getEntryDate());
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pos=position;
                alertDelete(activity);



            }

        });
        dialogs.removedialog();


    }

    @Override
    public int getItemCount() {
        return results.size();
    }
    public class showabsence extends RecyclerView.ViewHolder {
        TextView txt_date,txt_vaction,txt_time;
        ImageView btn_delete;

        public showabsence(View itemView) {
            super(itemView);
            txt_date=(TextView)itemView.findViewById(R.id.txt_date);
            txt_vaction=(TextView)itemView.findViewById(R.id.txt_vaction);
            txt_time=(TextView)itemView.findViewById(R.id.txt_time);
            btn_delete=(ImageView)itemView.findViewById(R.id.btn_delete);

        }
    }

    public void alertDelete(final Activity activity){
        final AlertDialog.Builder builder=new AlertDialog.Builder(activity);
        builder.setMessage(R.string.delete);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialogs.progressdialog(activity);
                SharedPref.setString(activity,"idd",results.get(pos).getId());
                WebserviceResult.removeAbsence(activity,SharedPref.getString(activity,"idd"));




            }
        });
          builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
              @Override
              public void onClick(DialogInterface dialog, int which) {

                  dialog.dismiss();
              }
          });
        builder.show();

    }



}
