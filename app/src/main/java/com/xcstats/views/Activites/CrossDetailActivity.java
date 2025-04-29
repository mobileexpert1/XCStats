package com.xcstats.views.Activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.crossgoaldetails.Result;
import com.xcstats.model.crosspacegoals.PaceResult;
import com.xcstats.model.removegoal.GoalRemoveResult;
import com.xcstats.model.setupdategoal.UpdateGoalResult;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Button_simple;
import com.xcstats.views.custom.Edittext_light;
import com.xcstats.views.custom.Textview_Light;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CrossDetailActivity extends AppCompatActivity {

    @BindView(R.id.txt_event)
    Textview_Light txt_event;

    @BindView(R.id.btn_delete)
    Button_simple btn_delete;

    @BindView(R.id.btn_submitGoal)
    Button_simple btn_submitGoal;

    @BindView(R.id.txt_courses)
    Textview_Light txt_courses;


    @BindView(R.id.txt_dates)
    Textview_Light txt_dates;

    @BindView(R.id.txt_workrecord)
    Textview_Light txt_workrecord;

    @BindView(R.id.btn_cancel)
    Button_simple btn_cancel;

    @BindView(R.id.image_bcckhome)
    ImageView image_bcckhome;

    @BindView(R.id.txt_pac)
    Textview_Light txt_pac;

    @BindView(R.id.txt_minutes)
    Edittext_light txt_minutes;

    @BindView(R.id.txt_sec)
    Edittext_light txt_sec;

    @BindView(R.id.edit_notes)
    Edittext_light edit_notes;

    @BindView(R.id.txt_distances)
    Textview_Light txt_distances;

    private String distance = "";

    @BindView(R.id.spinner_distance)
    Spinner spinner_distance;

    private static CrossDetailActivity instance;

    private String changeMIN;

    private String changeSEC;


    public static CrossDetailActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_detail);
        ButterKnife.bind(this);

        if (getIntent().getBooleanExtra("show_delBT", false) == true)
            btn_delete.setVisibility(View.VISIBLE);

         else
            btn_delete.setVisibility(View.INVISIBLE);
            dialogs.progressdialog(this);
             instance = this;
             txt_minutes.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!getIntent().getBooleanExtra("show_delBT", false)||distance.isEmpty())
                    return;
                if (s.length()>0){
                    changeMIN = txt_minutes.getText().toString().trim();
                    WebserviceResult.paceResult(CrossDetailActivity.this,distance,changeMIN,changeSEC);

                }


            }
        });

        txt_sec.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!getIntent().getBooleanExtra("show_delBT", false)|| distance.isEmpty())
                    return;
                if (s.length()>0){
                    changeSEC = txt_sec.getText().toString().trim();
                    WebserviceResult.paceResult(CrossDetailActivity.this,distance,changeMIN,changeSEC);

                }


            }
        });

        WebserviceResult.crossDetail(this,SharedPref.getString(this, "schoolid"), SharedPref.getString(this, "runnerid"), SharedPref.getString(this, "id"));
    }


    @OnClick(R.id.btn_delete)
    public void delete() {
        deleteCrossGoal();


    }
    public void deleteCrossGoal(){

        AlertDialog.Builder alertdialog = new AlertDialog.Builder(CrossDetailActivity.this);
        alertdialog.setMessage("Are You Sure?");
        alertdialog.setCancelable(false);
        alertdialog.setNegativeButton("YES", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialogs.progressdialog(CrossDetailActivity.this);
                WebserviceResult.removeResult(CrossDetailActivity.this,SharedPref.getString(CrossDetailActivity.this, "schoolid"), SharedPref.getString(CrossDetailActivity.this, "runnerid"), SharedPref.getString(CrossDetailActivity.this, "id"));


            }
        });
        alertdialog.setPositiveButton("NO", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();

            }
        });
        alertdialog.show();

    }

    @OnClick(R.id.image_bcckhome)
    public void backCrossCountry() {
        Intent intent = new Intent(CrossDetailActivity.this, CrossCountryActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    @OnClick(R.id.btn_submitGoal)
    public void submitGoal() {
        if (txt_minutes.getText().toString().trim().isEmpty()&&txt_sec.getText().toString().trim().isEmpty()){
            dialogs.showToast(CrossDetailActivity.this,"Please Enter Seconds");
        }
        else {
            dialogs.progressdialog(CrossDetailActivity.this);
            WebserviceResult.updateResult(this,SharedPref.getString(this, "schoolid"), SharedPref.getString(this, "runnerid"), SharedPref.getString(this, "id"), txt_minutes.getText().toString(), txt_sec.getText().toString(), edit_notes.getText().toString(), distance);
        }

    }

    public void updateGoals(UpdateGoalResult updateGoalResult) {
        if (updateGoalResult.getSuccess()) {
            dialogs.progressdialog(this);
            dialogs.showToast(this, updateGoalResult.getResults());
            Intent intent = new Intent(CrossDetailActivity.this, CrossCountryActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
            finish();
        }

        dialogs.removedialog();
    }

    @OnClick(R.id.btn_cancel)
    public void Cancel() {
        Intent intent = new Intent(CrossDetailActivity.this, CrossCountryActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CrossDetailActivity.this, CrossCountryActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    public void updateDetails(List<Result> results) {
        dialogs.removedialog();
        txt_event.setText(results.get(0).getEvent());
        txt_courses.setText(results.get(0).getCourse());
        txt_dates.setText(results.get(0).getDisplayDate());
        txt_workrecord.setText(results.get(0).getPersonalRecord());
       // edit_notes.setText(results.get(0).get);


        for (int i = 0; i < results.get(0).getGoal().size(); i++) {

            txt_pac.setText(results.get(0).getGoal().get(i).getPace());
            edit_notes.setText(results.get(0).getGoal().get(i).getNotesBefore());
            txt_minutes.setText(results.get(0).getGoal().get(i).getMinutesField() + "");
            changeMIN=results.get(0).getGoal().get(i).getMinutesField() + "";
            txt_sec.setText(results.get(0).getGoal().get(i).getSecondsField() + "");
            changeSEC=results.get(0).getGoal().get(i).getSecondsField() + "";


        }

        if (results.get(0).getExpectedDistance().size()==0) {
            txt_distances.setText("Distance Unavailable");
            spinner_distance.setVisibility(View.GONE);

        }
        else {
            String[] array = new String[results.get(0).getExpectedDistance().size()];
            for (int i = 0; i < results.get(0).getExpectedDistance().size(); i++) {
                {
                    array[i] = results.get(0).getExpectedDistance().get(i).getDetail().getLabel()+"";

                }
                ArrayAdapter arrayAdapter = new ArrayAdapter<String>(this, R.layout.distance, R.id.spinner_distance, array);
                arrayAdapter.setDropDownViewResource(R.layout.distanceexpected);
                spinner_distance.setAdapter(arrayAdapter);
                spinner_distance.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                        distance = adapterView.getItemAtPosition(i).toString();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {

                    }
                });
                dialogs.removedialog();

            }
        }
    }

    public void showAlert(final GoalRemoveResult goalRemoveResult) {

        if (goalRemoveResult.getSuccess() == true) {
                    dialogs.showToast(CrossDetailActivity.this, goalRemoveResult.getResults());
                    Intent intent = new Intent(CrossDetailActivity.this, CrossCountryActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
                    finish();

                }
    }

    public void paceresults(PaceResult paceResult){
        if (paceResult.getSuccess() == true){
            txt_pac.setText(paceResult.getResults());
        }

    }

}













