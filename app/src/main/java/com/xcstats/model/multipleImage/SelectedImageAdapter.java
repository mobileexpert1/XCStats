package com.xcstats.model.multipleImage;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.xcstats.R;

import java.util.ArrayList;
import java.util.List;

public class SelectedImageAdapter extends RecyclerView.Adapter<SelectedImageAdapter.ViewHolder> {

    private final boolean check;
    Context context;
    ArrayList<String> stringArrayList;

    public SelectedImageAdapter(Context context, ArrayList<String> stringArrayList,boolean check) {
        this.context = context;
        this.check= check;
        this.stringArrayList = stringArrayList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.selected_image_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Log.e("tag","imagesArry..."+stringArrayList.size());

        Glide.with(context)
                .load(stringArrayList.get(position))
                .placeholder(R.color.caldroid_gray)
                .centerCrop()
                .into(holder.image);

        if(check) {
            holder.ivCancel.setTag(stringArrayList.get(position));
            holder.ivCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String image = (String) v.getTag();
                    stringArrayList.remove(image);
                    notifyDataSetChanged();
                    //context.startActivity(new Intent(context, FullImageActivity.class).putExtra("image", stringArrayList.get(position)));
                }
            });
        }
        else
        {
            holder.ivCancel.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return stringArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image, ivCancel;

        public ViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            ivCancel = (ImageView) itemView.findViewById(R.id.ivCancel);
        }
    }

}