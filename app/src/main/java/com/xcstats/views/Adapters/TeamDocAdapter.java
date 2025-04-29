package com.xcstats.views.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.controller.Items;
import com.xcstats.model.trackpr.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * Created by Mobile on 6/27/2018.
 */

public class TeamDocAdapter extends RecyclerView.Adapter<TeamDocAdapter.viewholder> {


    List<com.xcstats.model.teamdoc.Result> results;
    Activity activity;
    private String iconName;

    private ArrayList<Items> items;
    private String[] mDrawerIcons;
    private String des;


    public TeamDocAdapter(List<com.xcstats.model.teamdoc.Result> results, Activity activity) {
        this.results = results;
        this.activity = activity;
        mDrawerIcons = activity.getResources().getStringArray(R.array.img);
    }

    @Override
    public TeamDocAdapter.viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.docview, parent, false);

        return new viewholder(itemView);
    }

    @Override
    public void onBindViewHolder(TeamDocAdapter.viewholder holder, @SuppressLint("RecyclerView") final int position) {
        des = results.get(position).getDescription();
        if (!des.equals("")){
            holder.tvDoc.setText(results.get(position).getName()+" - "+ results.get(position).getDescription());
        }
        else {
            holder.tvDoc.setText(results.get(position).getName());

        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openBrowser(activity,results.get(position).getUrl());
            }
        });
        iconName = results.get(position).getIcon();
        Log.d("@@iconName",iconName);
        if (iconName.equals("image.png")){
            holder.imageView.setImageResource(R.drawable.image);
        }
        else if (iconName.equals("xls.png")){
            holder.imageView.setImageResource(R.drawable.xls);

        }
        else if (iconName.equals("doc.png")){
            holder.imageView.setImageResource(R.drawable.doc);

        }
        else if (iconName.equals("generic.png")){
            holder.imageView.setImageResource(R.drawable.generic);

        }
        else if (iconName.equals("html.png")){
            holder.imageView.setImageResource(R.drawable.html);

        }
        else if (iconName.equals("kmz.png")){
            holder.imageView.setImageResource(R.drawable.kmz);

        }
        else if (iconName.equals("odf.png")){
            holder.imageView.setImageResource(R.drawable.odf);

        }
        else if (iconName.equals("pdf.png")){
            holder.imageView.setImageResource(R.drawable.pdf);

        }
        else if (iconName.equals("ppt.png")){
            holder.imageView.setImageResource(R.drawable.ppt);

        }
        else if (iconName.equals("zip.png")){
            holder.imageView.setImageResource(R.drawable.zip);

        }

    }

    @Override
    public int getItemCount() {
        return results.size();
    }


    public class viewholder extends RecyclerView.ViewHolder {
        TextView tvDoc;
        ImageView imageView;


        public viewholder(View itemView) {
            super(itemView);
            tvDoc= (TextView)itemView.findViewById(R.id.tvDoc);
            imageView = (ImageView)itemView.findViewById(R.id.iv);

        }
    }



    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";

    public static void openBrowser(final Context context, String url) {

        if (!url.startsWith(HTTP) && !url.startsWith(HTTPS)) {
            url = HTTP + url;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(Intent.createChooser(intent, "Choose browser"));// Choose browser is arbitrary :)

    }

}
