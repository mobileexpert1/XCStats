package com.xcstats.views.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.trackpr.TrackPrResult;
import com.xcstats.views.Adapters.CurrentPr_Adapter;
import com.xcstats.views.Dialogs.dialogs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CurrentPrActivity extends AppCompatActivity {

    private static CurrentPrActivity instance;
    @BindView(R.id.pr_recycle)
    RecyclerView pr_recycle;

    @BindView(R.id.image_bckhome)
    ImageView image_bckhome;

    public static CurrentPrActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_pr_);
        ButterKnife.bind(this);
        instance = this;
        dialogs.progressdialog(this);
        WebserviceResult.trackpr(this,SharedPref.getString(this, "runnerid") + "", SharedPref.getString(this, "schoolid") + "");
    }


    public void currentPr(TrackPrResult trackPrResult, String message) {
        if (trackPrResult != null) {
            if (trackPrResult.getSuccess()) {
                CurrentPr_Adapter adapter = new CurrentPr_Adapter(trackPrResult.getResults(), this);
                pr_recycle.setLayoutManager(new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false));
                pr_recycle.setAdapter(adapter);
                dialogs.removedialog();
            }
            if (trackPrResult.getResults().size() == 0) {
                dialogs.showToast(CurrentPrActivity.this, "No Current Personal & Seasonal Records So Far");

            }
        }


    }

    @OnClick(R.id.image_bckhome)
    public void backHome() {
        Intent intent = new Intent(CurrentPrActivity.this, TrackFieldActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CurrentPrActivity.this, TrackFieldActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }
}
