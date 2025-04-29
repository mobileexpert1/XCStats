package com.xcstats.views.Adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.text.Html;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.xcstats.R;
import com.xcstats.ViewTrack_WebViewActivity;
import com.xcstats.model.viewtrackschdule.Data;
import com.xcstats.views.Activites.WebViewActivity;

import java.util.List;

public class Indoor_Adapter extends RecyclerView.Adapter<Indoor_Adapter.viewholder> {

    private List<String> mData;
    private LayoutInflater mInflater;
    List<Data> indoorList;
    String title_indoor;
    Activity activity;

    private static final String HTTPS = "https://";
    private static final String HTTP = "http://";



    public Indoor_Adapter(List<Data> indoorList, String title_indoor,Activity activity) {
        this.mInflater = LayoutInflater.from(activity);
        this.indoorList= indoorList;
        this.title_indoor=title_indoor;
        this.activity=activity;

    }





    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = mInflater.inflate(R.layout.view_track_items, parent, false);
        return new Indoor_Adapter.viewholder(view);



    }

    @Override
    public void onBindViewHolder(final Indoor_Adapter. viewholder holder, @SuppressLint("RecyclerView") int position) {

        holder.data_Tv.setText(indoorList.get(position).getMeet_date());
        holder.name_Tv.setText(indoorList.get(position).getName());
        holder.location_Tv.setText(indoorList.get(position).getLocation());

        if(!indoorList.get(position).getLink().isEmpty()) {
            holder.name_Tv.setTextColor(Color.parseColor("#0A66F4"));

            SpannableString content = new SpannableString(indoorList.get(position).getName());
            content.setSpan(new UnderlineSpan(), 0, content.length(), 0);

            holder.name_Tv.setText(content);
        }else{
            holder.name_Tv.setTextColor(Color.parseColor("#000000"));
        }
        //holder.name_Tv.setTextColor(Color.BLUE);
        holder.name_Tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(indoorList.get(position).getLink().isEmpty()){

                    Toast.makeText(activity,"Url not found",Toast.LENGTH_LONG).show();
                }else {
                    Intent intent = new Intent(activity, ViewTrack_WebViewActivity.class);
                    intent.putExtra("indoorUrl", indoorList.get(position).getLink());
                    activity.startActivity(intent);
                }

            }
        });

    }


    @Override
    public int getItemCount() {
        return indoorList.size();

    }



    public static void openBrowser(final Context context, String url) {

        if (!url.startsWith(HTTP) && !url.startsWith(HTTPS)) {
            url = HTTP + url;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        context.startActivity(Intent.createChooser(intent, "Choose browser"));// Choose browser is arbitrary :)

    }

    public class viewholder extends RecyclerView.ViewHolder {
        LinearLayout data_LL;
        TextView data_Tv,name_Tv,location_Tv;

        public viewholder(View itemView) {
            super(itemView);
            data_LL=(LinearLayout)itemView.findViewById(R.id.data_LL);
            data_Tv=(TextView)itemView.findViewById(R.id.data_Tv);
            name_Tv=(TextView)itemView.findViewById(R.id.name_Tv);
            location_Tv=(TextView)itemView.findViewById(R.id.location_Tv);


        }
    }





}

