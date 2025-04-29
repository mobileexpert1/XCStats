package com.xcstats.views.Activites;

import android.content.Intent;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.GetLeader.GetLeaderShapeProp;
import com.xcstats.views.Adapters.LeadBoardAdapter;
import com.xcstats.views.Adapters.TrackField_Adapter;
import com.xcstats.views.Dialogs.dialogs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrainingLogLeadBoard extends AppCompatActivity {

    public static TrainingLogLeadBoard minstance;

    String metric,gender,grade;

    @BindView(R.id.tvMsgs)
    TextView tvMsgs;

    @BindView(R.id.txt_metric)
    TextView txt_metric;

    @BindView(R.id.recycle_lead)
    RecyclerView recycle_lead;

    @BindView(R.id.txterrormsg)
    TextView txterrormsg;

    @BindView(R.id.linear_bar)
    LinearLayout linear_bar;

    public static TrainingLogLeadBoard instance(){
        return minstance;
    }
    @BindView(R.id.image_bckTrainingLog)
    ImageView image_bckTrainingLog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_log_lead_board);
        minstance = this;
        ButterKnife.bind(this);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            metric = extras.getString("Metric").replace("%20","");
            grade = extras.getString("gradeValue");
            gender = extras.getString("genderValues");
            Log.d("###MM",metric+""+grade+""+gender);
            // and get whatever type user account id is
        }
        dialogs.progressdialog(this);
        WebserviceResult.getLeadsResult(TrainingLogLeadBoard.this,SharedPref.getString(this, "schoolid"),SharedPref.getString(this,"runnerid"),metric,gender,grade);

    }


    @OnClick(R.id.image_bckTrainingLog)
    public void backTraingLog(){
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    public void getLeadResult(GetLeaderShapeProp getLeaderShapeProp){
        dialogs.removedialog();
        if(getLeaderShapeProp!=null) {
            Boolean check = getLeaderShapeProp.getSuccess();
            if (check == true) {
                if (getLeaderShapeProp.getSuccess()) {
                    tvMsgs.setText(getLeaderShapeProp.getResults().getMsg());
                    txt_metric.setText(getLeaderShapeProp.getResults().getHeading());
                    if (getLeaderShapeProp != null) {
                        if (getLeaderShapeProp.getResults().getRows()!=null) {
                            LeadBoardAdapter leadBoardAdapter = new LeadBoardAdapter(this, getLeaderShapeProp.getResults().getRows());
                            recycle_lead.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
                            recycle_lead.setAdapter(leadBoardAdapter);
                        }
                        else {
                            Toast.makeText(this, "No athletes meet these criteria", Toast.LENGTH_SHORT).show();
                            txterrormsg.setVisibility(View.VISIBLE);
                           // linear_bar.setVisibility(View.GONE);
                        }
                    }
                    else {
                        dialogs.removedialog();
                        dialogs.showToast(this, "“Something went wrong");
                    }
                }
            }

        }
    }


}
