package com.xcstats.views.Activites;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.pastgoals.Pastgoalresults;
import com.xcstats.model.reviewsubmit.ReviewSubmitResult;
import com.xcstats.views.Adapters.ReviewPast_Adapter;
import com.xcstats.views.Dialogs.dialogs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewPastGoal extends AppCompatActivity {

    public static Dialog alertDialogBuilder;

    private static ReviewPastGoal instance;

    @BindView(R.id.image_bcckhome)
    ImageView image_bcckhome;

    @BindView(R.id.recycle_pastgoal)
    RecyclerView recycle_pastgoal;


    public static ReviewPastGoal getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_past_goal);
        ButterKnife.bind(this);
        instance = this;
        dialogs.progressdialog(this);
        WebserviceResult.showPastGoal(this,SharedPref.getString(this,"runnerid"),SharedPref.getString(this,"schoolid"));
    }

    @OnClick(R.id.image_bcckhome)
    public void backPlanActivity() {
        Intent intent = new Intent(ReviewPastGoal.this, TrackFieldActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ReviewPastGoal.this, TrackFieldActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    public void submitReviews(ReviewSubmitResult reviewSubmitResult){
        dialogs.removedialog();
        Boolean check=reviewSubmitResult.getSuccess();
        if (check) {
            if (reviewSubmitResult.getSuccess()) {
                dialogs.showToast(ReviewPastGoal.this, reviewSubmitResult.getResults());
                dialogs.progressdialog(ReviewPastGoal.this);
                WebserviceResult.showPastGoal(this,SharedPref.getString(this, "runnerid"), SharedPref.getString(this, "schoolid"));
            }
        }
        else
        {
            dialogs.showToast(ReviewPastGoal.this,reviewSubmitResult.getResults());

         }
        }


    public void updateReviewPast(Pastgoalresults pastgoalresults,String message) {
        if (pastgoalresults != null) {
            ReviewPast_Adapter reviewPast_adapter = new ReviewPast_Adapter(pastgoalresults.getResults(),ReviewPastGoal.this);
            recycle_pastgoal.setLayoutManager(new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false));
            recycle_pastgoal.setAdapter(reviewPast_adapter);
            dialogs.removedialog();
        }
        else {
            dialogs.removedialog();
            dialogs.showToast(ReviewPastGoal.this,"No Past Records So Far");
        }





    }


}

