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
import com.xcstats.model.absenceplanned.AbsencePlannedResult;
import com.xcstats.model.removeabsence.RemoveAbsence;
import com.xcstats.views.Adapters.PlannedAbsence_Adapter;
import com.xcstats.views.Dialogs.dialogs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlannedAbsence extends AppCompatActivity {

    private static PlannedAbsence instance;
    @BindView(R.id.image_bckhome)
    ImageView image_bckhome;

    @BindView(R.id.Planabsence)
    RecyclerView Planabsence;


    @BindView(R.id.add_planabsence)
    ImageView add_planabsence;


    public static PlannedAbsence getinstance(){
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planned__absence);
        ButterKnife.bind(this);
        instance=this;
        dialogs.progressdialog(this);
        WebserviceResult.absencehistory(this,SharedPref.getString(this,"runnerid")+"");


    }

    @OnClick(R.id.add_planabsence)
    public void addplans(){
        Intent intent=new Intent(PlannedAbsence.this,AddPlanAbsenceActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright,R.anim.push_outleft);
        finish();


    }

    @OnClick(R.id.image_bckhome)
    public void back(){
        Intent intent=new Intent(PlannedAbsence.this,HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft,R.anim.push_outright);
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(PlannedAbsence.this,HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft,R.anim.push_outright);
        finish();

    }


    public void updateplannedservice(AbsencePlannedResult result,String message){
        if (result!=null){
            if (result.getSuccess()){
                PlannedAbsence_Adapter adapter=new PlannedAbsence_Adapter(result.getResults(),this);
                Planabsence.setLayoutManager(new LinearLayoutManager(instance,LinearLayoutManager.VERTICAL,false));
                Planabsence.setAdapter(adapter);
                dialogs.removedialog();
            }
            if(result.getResults().size()==0){
                dialogs.removedialog();
                dialogs.showToast(PlannedAbsence.this,"No Absences So Far in Last 30 Days");

            }
        }
        else
        {
            dialogs.removedialog();
            dialogs.showToast(PlannedAbsence.this,"NO Data Found");
        }

        }



    public void deletePlanned(RemoveAbsence removeAbsence){
        if (removeAbsence.getSuccess()){
            dialogs.showToast(this,removeAbsence.getResults());
            dialogs.removedialog();
            WebserviceResult.absencehistory(this,SharedPref.getString(this,"runnerid")+"");
        }

    }

    }

