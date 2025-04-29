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
import com.xcstats.model.getparents.Parent;
import com.xcstats.views.Dialogs.dialogs;

import java.util.List;

/**
 * Created by Mobile on 12/28/2016.
 */

public class ParentsLogin_Adapter extends RecyclerView.Adapter<ParentsLogin_Adapter.parentsadd> {
    List<Parent> parents;
    Activity activity;
    private View view;

    public ParentsLogin_Adapter(List<Parent> parents, Activity activity) {
        this.parents= parents;
        this.activity=activity;
    }



    @Override
    public ParentsLogin_Adapter.parentsadd onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.parents_history, parent, false);

        return new ParentsLogin_Adapter.parentsadd(itemView);
    }

    @Override
    public void onBindViewHolder( ParentsLogin_Adapter.parentsadd holder,  int position) {

        final String firstname=parents.get(position).getName().substring(0,parents.get(position).getName().indexOf(" "));
        final String lastname=parents.get(position).getName().substring(parents.get(position).getName().indexOf(" "),parents.get(position).getName().length());
        holder.txt_name.setText(firstname+"\n"+lastname);

        String before=parents.get(position).getEmail().substring(0,parents.get(position).getEmail().indexOf("@"));
        String after=parents.get(position).getEmail().substring(parents.get(position).getEmail().indexOf("@"),parents.get(position).getEmail().length());
        holder.txt_email.setText(before+"\n"+after);

        holder.txt_w.setText(parents.get(position).getStatus());
        holder.delete_parents.setTag(holder);
        holder.delete_parents.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View view) {

                    AlertDialog.Builder alertdialog = new AlertDialog.Builder(activity);
                    alertdialog.setMessage("Are You Sure?");
                    alertdialog.setCancelable(false);
                    alertdialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            ParentsLogin_Adapter.parentsadd holder= (parentsadd) view.getTag();
                            SharedPref.setString(activity,"id", parents.get(holder.getPosition()).getId());
                            dialogs.progressdialog(activity);
                            WebserviceResult.deleteParents(activity,parents.get(holder.getPosition()));

                        }
                    });


                    alertdialog.setPositiveButton("NO", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            dialog.cancel();

                        }
                    });
                    alertdialog.show();


                }
            });

        holder.edit_parents.setTag(holder);
        holder.edit_parents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ParentsLogin_Adapter.parentsadd holder= (parentsadd) view.getTag();
                SharedPref.setString(activity,"id", parents.get(holder.getPosition()).getId());
                dialogs.showContactDialog(activity,firstname,lastname,parents.get(holder.getPosition()).getEmail(), Integer.parseInt(parents.get(holder.getPosition()).getId()));



            }
        });


    }

    @Override
    public int getItemCount() {
        return parents.size();
    }

public class parentsadd extends RecyclerView.ViewHolder {
    TextView txt_name,txt_email,txt_w;
    ImageView edit_parents,delete_parents;

    public parentsadd(View itemView) {
        super(itemView);
        txt_name=(TextView)itemView.findViewById(R.id.txt_name);
        txt_email=(TextView)itemView.findViewById(R.id.txt_email);
        txt_w=(TextView)itemView.findViewById(R.id.txt_w);
        edit_parents=(ImageView)itemView.findViewById(R.id.edit_parents);
        delete_parents=(ImageView)itemView.findViewById(R.id.delete_parents);
    }
}
}
