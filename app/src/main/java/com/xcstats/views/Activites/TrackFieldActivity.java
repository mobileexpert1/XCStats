package com.xcstats.views.Activites;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.ViewTrackActivity;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.deletetrack.DeleteTrackResult;
import com.xcstats.model.trackfield.TrackFieldResult;
import com.xcstats.views.Adapters.TrackField_Adapter;
import com.xcstats.views.Dialogs.dialogs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrackFieldActivity extends AppCompatActivity {

    private static TrackFieldActivity instance;
    @BindView(R.id.add_goal)
    ImageView add_goal;

    @BindView(R.id.txt_pr)
    TextView txt_pr;

    @BindView(R.id.txt_goals)
    TextView txt_goals;

    @BindView(R.id.image_bckhome)
    ImageView image_bckhome;


    @BindView(R.id.track_recycle)
    RecyclerView track_recycle;

    @BindView(R.id.viewtrack_Tv)
    TextView viewtrack_Tv;

    public static TrackFieldActivity getInstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_field_);
        ButterKnife.bind(this);
        instance = this;
        dialogs.progressdialog(this);
        WebserviceResult.trackGoal(this,SharedPref.getString(this, "schoolid"), SharedPref.getString(this, "runnerid"));

    }

    @OnClick(R.id.add_goal)
    public void goaladd() {
        Intent intent = new Intent(TrackFieldActivity.this, GoalDetailActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();
    }


    @OnClick(R.id.txt_pr)
    public void PrSr() {
        Intent intent = new Intent(TrackFieldActivity.this, CurrentPrActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();


    }

    @OnClick(R.id.viewtrack_Tv)
    public void viewtrack_Tv(){
        Intent intent=new Intent(TrackFieldActivity.this, ViewTrackActivity.class);
        startActivity(intent);
        Log.e("@@@","showschedule");
//        WebserviceResult.showSchedule(SharedPref.getString(this, "schoolid"));

    }

    @OnClick(R.id.image_bckhome)
    public void backHome() {
        Intent intent = new Intent(TrackFieldActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    @OnClick(R.id.txt_goals)
    public void PastGoals() {
        Intent intent = new Intent(TrackFieldActivity.this, ReviewPastGoal.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TrackFieldActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }




    public void deleteTracks(DeleteTrackResult deleteTrackResult) {
        dialogs.removedialog();
        if (deleteTrackResult.getSuccess() == true) {
            dialogs.showToast(TrackFieldActivity.this, deleteTrackResult.getResults());
            WebserviceResult.trackGoal(this, SharedPref.getString(this, "schoolid"), SharedPref.getString(this, "runnerid"));

        } else {
            dialogs.showToast(TrackFieldActivity.this, "Goal Not Deleted");

        }
    }


    public void trackupdate(TrackFieldResult trackFieldResult, String message) {

        if (trackFieldResult != null) {
            if (trackFieldResult.getResults().size() > 0) {
                track_recycle.setVisibility(View.VISIBLE);
                TrackField_Adapter trackField_adapter = new TrackField_Adapter(trackFieldResult.getResults(), TrackFieldActivity.this);
                track_recycle.setLayoutManager(new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false));
                track_recycle.setAdapter(trackField_adapter);
                dialogs.removedialog();

            }
        } else {
            track_recycle.setVisibility(View.GONE);
            dialogs.removedialog();
            // dialogs.showToast(this, "No goals set");
        }


    }
}
