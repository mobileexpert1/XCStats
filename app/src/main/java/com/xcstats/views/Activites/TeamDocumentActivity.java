package com.xcstats.views.Activites;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.teamdoc.TeamdocProp;
import com.xcstats.model.trackpr.TrackPrResult;
import com.xcstats.views.Adapters.CurrentPr_Adapter;
import com.xcstats.views.Adapters.TeamDocAdapter;
import com.xcstats.views.Dialogs.dialogs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeamDocumentActivity extends AppCompatActivity {


    private static TeamDocumentActivity minstance;

    public static TeamDocumentActivity instance(){
        return minstance;
    }

    @BindView(R.id.rvTeamDoc)
    RecyclerView rvTeamDoc;

    @BindView(R.id.tvNofile)
    TextView tvNofile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_document);
        minstance = this;
        ButterKnife.bind(this);
        dialogs.progressdialog(this);
        WebserviceResult.teamDoc(this,SharedPref.getString(this, "schoolid"));

    }
    public void teamDoc(TeamdocProp teamdocProp) {
        if (teamdocProp != null) {
            if (teamdocProp.getSuccess()) {
                TeamDocAdapter adapter = new TeamDocAdapter(teamdocProp.getResults(), this);
                rvTeamDoc.setLayoutManager(new LinearLayoutManager(minstance, LinearLayoutManager.VERTICAL, false));
                rvTeamDoc.setAdapter(adapter);
                dialogs.removedialog();
            }
        }
        else {
            dialogs.removedialog();
            tvNofile.setVisibility(View.VISIBLE);
        }
    }
    @OnClick(R.id.image_backhomes)
    public void backHome(){
        Intent intent=new Intent(TeamDocumentActivity.this,HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft,R.anim.push_outright);
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(TeamDocumentActivity.this,HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft,R.anim.push_outright);
        finish();

    }



}
