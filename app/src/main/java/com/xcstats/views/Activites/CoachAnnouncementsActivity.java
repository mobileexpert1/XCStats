package com.xcstats.views.Activites;

import android.content.Intent;
import android.os.Bundle;

import android.view.Window;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.announcementsProp.AnnouncementsProp;
import com.xcstats.model.crossgoals.CrossResult;
import com.xcstats.views.Adapters.CoachAnnouncementsAdapter;
import com.xcstats.views.Adapters.CrossGoal_Adapter;
import com.xcstats.views.Dialogs.dialogs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoachAnnouncementsActivity extends AppCompatActivity {


    private static CoachAnnouncementsActivity instance;

    @BindView(R.id.announcementsRV)
    RecyclerView announcementsRV;


    public static CoachAnnouncementsActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_announcements);
        ButterKnife.bind(this);
        instance = this;

        dialogs.progressdialog(this);
        WebserviceResult.coachAnnouncements(this,SharedPref.getString(this, "schoolid"), SharedPref.getString(this, "runnerid"));

    }

    @OnClick(R.id.image_crosshome)
    public void backHome() {
        Intent intent = new Intent(CoachAnnouncementsActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CoachAnnouncementsActivity.this, HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    public void updateCrossResult(AnnouncementsProp result) {
        if (result != null) {
            if (result.getResults().size() > 0) {
                CoachAnnouncementsAdapter announcements = new CoachAnnouncementsAdapter(this, result.getResults());
                announcementsRV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                announcementsRV.setAdapter(announcements);
                dialogs.removedialog();
            } else {
                dialogs.removedialog();
                dialogs.showToast(CoachAnnouncementsActivity.this, "No Data Available");
            }
        }
        else {
            dialogs.removedialog();
            dialogs.showToast(CoachAnnouncementsActivity.this, "No Data Available");
        }
    }


}
