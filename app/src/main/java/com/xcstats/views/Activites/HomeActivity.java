package com.xcstats.views.Activites;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.Toast;

import com.xcstats.R;
import com.xcstats.api.PermissionStatus;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.displayButtons.DisplayButtonResult;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Button_simple;
import com.xcstats.views.custom.Textview_Light;
import com.xcstats.views.custom.Textview_Simple;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HomeActivity extends BaseActivity implements PermissionStatus {

    private static HomeActivity instance;

    private boolean BackToExit = false;

    @BindView(R.id.btn_trainning)
    Button_simple btn_trainning;

    @BindView(R.id.btn_plan)
    Button_simple btn_plan;

    @BindView(R.id.btn_emailcoach)
    Button_simple btn_emailcoach;

    @BindView(R.id.btn_track)
    Button_simple btn_track;

    @BindView(R.id.btn_cross)
    Button_simple btn_cross;

    @BindView(R.id.btn_photo)
    Button_simple btn_photo;

    @BindView(R.id.btn_logout)
    Button_simple btn_logout;

    @BindView(R.id.edit_profile)
    Button_simple edit_profile;

    @BindView(R.id.btn_parentlogin)
    Button_simple btn_parentlogin;


    @BindView(R.id.btn_teamDoc)
    Button_simple btn_teamDoc;

    @BindView(R.id.countTV)
    public Textview_Light countTV;


    public static HomeActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        instance = this;
        setPermissionListener(this);
        WebserviceResult.displayFunction(this,SharedPref.getString(this, "schoolid"), SharedPref.getString(this, "runnerid"));


    }

    @OnClick(R.id.btn_trainning)
    public void training() {
        Intent intent = new Intent(HomeActivity.this, TrainingLogsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
//        finish();

    }

    @OnClick(R.id.coachesAnnouncementsRL)
    public void onCoachesClick() {
        Intent intent = new Intent(HomeActivity.this, CoachAnnouncementsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();
    }


    @OnClick(R.id.coachAnnouncementBT)
    public void onCountClick() {
        Intent intent = new Intent(HomeActivity.this, CoachAnnouncementsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();
    }

    @OnClick(R.id.btn_cross)
    public void crossGoal() {
        Intent intent = new Intent(HomeActivity.this, CrossCountryActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();

    }


    @OnClick(R.id.btn_plan)
    public void planned_absence() {
        Intent intent = new Intent(HomeActivity.this, PlannedAbsence.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();

    }

    @OnClick(R.id.btn_teamDoc)
    public void openDoc() {
        Intent intent = new Intent(HomeActivity.this, TeamDocumentActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();

    }

    @OnClick(R.id.btn_emailcoach)
    public void emailcoach() {
        Intent intent = new Intent(HomeActivity.this, EmailCoachActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();

    }

    @OnClick(R.id.btn_photo)
    public void photodialog() {
        if (checkPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) && checkPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Intent intent = new Intent(HomeActivity.this, PhotoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
            finish();
        }
    }

    @OnClick(R.id.btn_track)
    public void track() {
        Intent intent = new Intent(HomeActivity.this, TrackFieldActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();

    }

    @OnClick(R.id.edit_profile)
    public void editprofile() {
        Intent intent = new Intent(HomeActivity.this, EditProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();

    }

    @OnClick(R.id.btn_parentlogin)
    public void parentlogin() {
        Intent intent = new Intent(HomeActivity.this, ParentsLogin.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();


    }

    @OnClick(R.id.btn_logout)
    public void logoutdata() {
        SharedPref.clear(this);
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }

    @Override
    public void onBackPressed() {
        if (BackToExit) {
            super.onBackPressed();
            return;
        }
        this.BackToExit = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                BackToExit = false;
            }
        }, 4000);
    }


    public void updateDisplayFunctions(DisplayButtonResult displayResult) {
//        if(displayResult.getResults().get(0).getCount() == 0){
//            countTV.setVisibility(View.GONE);
//        }
//        else {
//            countTV.setVisibility(View.VISIBLE);
//        }

        if (displayResult.getResults().get(0).getShowTrack().equals(0) && displayResult.getResults().get(0).getShowXc().equals(0)) {
            dialogs.showToast(this, "We're sorry, your school is no longer a subscriber");
            SharedPref.clear(this);
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        } else if (displayResult.getResults().get(0).getShowTrack().equals(0) && displayResult.getResults().get(0).getShowXc().equals(1)) {
            btn_track.setVisibility(View.GONE);

        } else if (displayResult.getResults().get(0).getShowTrack().equals(1) && displayResult.getResults().get(0).getShowXc().equals(0)) {
            btn_cross.setVisibility(View.GONE);
          /*  countTV.setText(displayResult.getResults().get(0).getCount());
            if (displayResult.getResults().get(0).getBg().equalsIgnoreCase("#FF0000")) {
                countTV.setBackgroundColor(Color.RED);
            }*/
        }
    }


    @Override
    public void PermissionStatus(String Permission, int status) {
        if (Permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE) && Permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            if (status == 1) {
                Intent intent = new Intent(HomeActivity.this, PhotoActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
                finish();
            }
        } else {
            Intent intent = new Intent(HomeActivity.this, PhotoActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
            finish();
        }


    }
}





