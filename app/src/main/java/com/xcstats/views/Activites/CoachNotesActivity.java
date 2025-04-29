package com.xcstats.views.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.GoalTrack.GoalDetailTrackField;
import com.xcstats.model.coachNotes.CoachNotesResponse;
import com.xcstats.views.Dialogs.dialogs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CoachNotesActivity extends AppCompatActivity {

    private static CoachNotesActivity instance;
    private String schoolid;
    private String coachid;
    private String id;

    public static CoachNotesActivity getinstance() {
        return instance;
    }


    @BindView(R.id.txtCoachHeading)
    TextView txtCoachHeading;


    @BindView(R.id.txtNote)
    TextView txtNote;

    @BindView(R.id.btnReply)
    Button btnReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coach_notes);
        ButterKnife.bind(this);
        instance = this;
        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

//        Toast.makeText(instance, id, Toast.LENGTH_SHORT).show();
        dialogs.progressdialog(CoachNotesActivity.this);
        WebserviceResult.coachNotes(CoachNotesActivity.this,id);



    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }


    @OnClick(R.id.homecoach)
    public void backHome() {
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }


    @OnClick(R.id.btnReply)
    public void  moveEmailPage(){
        Intent intent = new Intent(this, EmailCoachActivity.class);
        intent.putExtra("type", 3);
        intent.putExtra("coachId", coachid);
        intent.putExtra("schoolId",schoolid);
        intent.putExtra("id",id);
//        intent.putExtra("subjectText", announcementList.get(position).getSubjectText());
         startActivity(intent);
         overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
    }



    public void handleResult(CoachNotesResponse data) {
        if (data.getSuccess()){
            txtCoachHeading.setText(data.getResults().getHeading());
            txtNote.setText(data.getResults().getCoachNote());
            coachid = data.getResults().getCoachId();
            schoolid= data.getResults().getSchoolId();
            id = data.getResults().getId();
//            Toast.makeText(instance, schoolid, Toast.LENGTH_SHORT).show();
//            txtNote.setMovementMethod(new ScrollingMovementMethod());
//            Toast.makeText(instance, data.getResults().getCoachNote(), Toast.LENGTH_SHORT).show();
        }

        dialogs.removedialog();

    }



}
