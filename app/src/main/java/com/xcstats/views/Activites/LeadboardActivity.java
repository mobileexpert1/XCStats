package com.xcstats.views.Activites;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.checkRegestration.RegisterationCode;
import com.xcstats.model.leadboard.LeadboardProp;
import com.xcstats.views.Dialogs.dialogs;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LeadboardActivity extends AppCompatActivity {


    public static LeadboardActivity mInstance;
    private String labelValue;
    private String ab;
    List<String> lab;
    private ArrayList<String> lab1;
    private ArrayList<String> genderList;

    List<String> radioLabel;

    List<String> gender;


    private String gradeValue;
    private String genderValues;


    public static LeadboardActivity instance() {
        return mInstance;
    }

    @BindView(R.id.image_bckTrainingLog)
    ImageView image_bckTrainingLog;

    @BindView(R.id.btn_createLeader)
    Button btn_createLeader;

    Spinner spinner_grade;

    Spinner spinner_gender;

    @BindView(R.id.tvMsg)
    TextView tvMsg;

    @BindView(R.id.rlMetricReport)
    RelativeLayout rlMetricReport;

    @BindView(R.id.radioButton1)
    RadioButton radioButton1;

    @BindView(R.id.radioButton2)
    RadioButton radioButton2;

    @BindView(R.id.radioButton3)
    RadioButton radioButton3;

    String metric;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leadboard);
        mInstance = this;
        ButterKnife.bind(this);
        spinner_grade = (Spinner) findViewById(R.id.spinner_grade);
        spinner_gender = (Spinner) findViewById(R.id.spinner_gender);
        dialogs.progressdialog(this);
        WebserviceResult.getLeadResult(this,SharedPref.getString(this, "schoolid"), SharedPref.getString(this, "runnerid"));
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(LeadboardActivity.this, TrainingLogsActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    @OnClick(R.id.image_bckTrainingLog)
    public void backToTraingScreen() {
        Intent intent = new Intent(LeadboardActivity.this, TrainingLogsActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    @OnClick(R.id.btn_createLeader)
    public void CrateBoard() {
        Intent intent = new Intent(LeadboardActivity.this, TrainingLogLeadBoard.class);
        Log.d("checkMetric:-", metric + "==" + gradeValue);
        intent.putExtra("Metric", metric.replaceAll("\\s", ""));
        intent.putExtra("gradeValue", gradeValue);
        intent.putExtra("genderValues", genderValues);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
    }


    public void getLeadResult(final LeadboardProp leadboardProp) {
        dialogs.removedialog();
        Boolean check = leadboardProp.getSuccess();
        Boolean displayForm = leadboardProp.getResults().getDisplayForm();
        Log.d("displayForm", String.valueOf(displayForm));
        if (leadboardProp.getSuccess()) {
            rlMetricReport.setVisibility(View.VISIBLE);
            if (displayForm == true) {
                tvMsg.setText(leadboardProp.getResults().getMsg());
                lab = new ArrayList<>();
                lab1 = new ArrayList<>();

                radioLabel = new ArrayList<>();

                for (int y = 0; y < leadboardProp.getResults().getRadios().size(); y++) {
                    radioLabel.add(leadboardProp.getResults().getRadios().get(y).getLabel());
                }
                for (int j = 0; j < leadboardProp.getResults().getGradeSelect().getOptions().size(); j++) {
                    lab.add(leadboardProp.getResults().getGradeSelect().getOptions().get(j).getLabel());
                    lab1.add(leadboardProp.getResults().getGradeSelect().getOptions().get(j).getValue());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, lab);
                spinner_grade.setAdapter(adapter);


                spinner_grade.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        ((TextView) view).setTextColor(Color.parseColor("#000000")); //Change selected text color

                        int index = parent.getSelectedItemPosition();
                        gradeValue = lab1.get(index);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });


                gender = new ArrayList<>();
                genderList = new ArrayList<>();
                for (int k = 0; k < leadboardProp.getResults().getGenderSelect().getOptions().size(); k++) {
                    gender.add(leadboardProp.getResults().getGenderSelect().getOptions().get(k).getLabel());
                    genderList.add(leadboardProp.getResults().getGenderSelect().getOptions().get(k).getValue());
                }

                ArrayAdapter<String> adapterGender = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, gender);
                spinner_gender.setAdapter(adapterGender);
                spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        int index = parent.getSelectedItemPosition();
                        genderValues = genderList.get(index);
                        ((TextView) view).setTextColor(Color.parseColor("#000000"));
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

                for (int i = 0; i < leadboardProp.getResults().getRadios().size(); i++) {
                    if (i == 0) {
                        radioButton1.setVisibility(View.VISIBLE);
                        radioButton1.setText(leadboardProp.getResults().getRadios().get(0).getLabel());
                        radioButton1.setChecked(Boolean.parseBoolean(leadboardProp.getResults().getRadios().get(0).getChecked()));
                        if (radioLabel.get(0).contains("(")) {
                            Log.e("@#@", "contains");
                            String[] parts = radioLabel.get(0).split("\\(");
                            String part1 = parts[0];

                            if (part1.toLowerCase().equals("number of workout days")) {
                                metric = "workouts";
                            } else {
                                metric = part1.toLowerCase();
                            }
                        } else {
                            String metricValue = radioLabel.get(0).toLowerCase();
                            if (metricValue.equals("number of workout days")) {
                                metric = "workouts";
                            } else {
                                metric = metricValue;
                            }
                        }

                        radioButton1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    metric = "distance";
                                    Log.e("first@#", "" + radioLabel.get(0));
                                    if (radioLabel.get(0).contains("(")) {
                                        Log.e("@#@", "contains");

                                        String[] parts = radioLabel.get(0).split("\\(");
                                        String part1 = parts[0];
                                        metric = part1.toLowerCase();
                                        if (metric.equals("number of workout days")) {
                                            metric = "workouts";
                                        } else {
                                            metric = part1.toLowerCase();
                                        }
                                    } else {
                                        String metricV = radioLabel.get(0).toLowerCase();
                                        //String metricValue = radioLabel.get(0).toLowerCase();
                                        if (metricV.equals("number of workout days")) {
                                            metric = "workouts";
                                        } else {
                                            metric = metricV;
                                        }
                                    }


                                }
                            }
                        });

                    } else if (i == 1) {
                        radioButton1.setVisibility(View.VISIBLE);
                        radioButton2.setVisibility(View.VISIBLE);
                        radioButton1.setText(leadboardProp.getResults().getRadios().get(0).getLabel());
                        radioButton2.setText(leadboardProp.getResults().getRadios().get(1).getLabel());
                        radioButton1.setChecked(Boolean.parseBoolean(leadboardProp.getResults().getRadios().get(0).getChecked()));
                        radioButton2.setChecked(Boolean.parseBoolean(leadboardProp.getResults().getRadios().get(1).getChecked()));

                        radioButton2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    if (radioLabel.get(1).contains("(")) {
                                        Log.e("@#@", "contains");

                                        String[] parts = radioLabel.get(1).split("\\(");
                                        String part1 = parts[0];
                                        metric = part1.toLowerCase();
                                        if (metric.equals("number of workout days")) {
                                            metric = "workouts";
                                        } else {
                                            metric = part1.toLowerCase();
                                        }

                                    } else {

                                        String metricV = radioLabel.get(1).toLowerCase();
                                        //String metricValue = radioLabel.get(0).toLowerCase();
                                        if (metricV.equals("number of workout days")) {
                                            metric = "workouts";
                                        } else {
                                            metric = metricV;
                                        }
                                    }
                                }
                            }
                        });
                    } else {
                        radioButton1.setVisibility(View.VISIBLE);
                        radioButton2.setVisibility(View.VISIBLE);
                        radioButton3.setVisibility(View.VISIBLE);
                        radioButton1.setText(leadboardProp.getResults().getRadios().get(0).getLabel());
                        radioButton2.setText(leadboardProp.getResults().getRadios().get(1).getLabel());
                        radioButton3.setText(leadboardProp.getResults().getRadios().get(2).getLabel());
                        radioButton1.setChecked(Boolean.parseBoolean(leadboardProp.getResults().getRadios().get(0).getChecked()));
                        radioButton2.setChecked(Boolean.parseBoolean(leadboardProp.getResults().getRadios().get(1).getChecked()));
                        radioButton3.setChecked(Boolean.parseBoolean(leadboardProp.getResults().getRadios().get(2).getChecked()));
                        radioButton3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                if (isChecked) {
                                    if (radioLabel.get(2).contains("(")) {
                                        Log.e("@#@", "contains");

                                        String[] parts = radioLabel.get(2).split("\\(");
                                        String part1 = parts[0];
                                        metric = part1.toLowerCase();
                                        if (metric.equals("number of workout days")) {
                                            metric = "workouts";
                                        } else {
                                            metric = part1.toLowerCase();
                                        }
                                    } else {
                                     //   metric = radioLabel.get(2).toLowerCase();
                                        String metricV = radioLabel.get(2).toLowerCase();
                                        //String metricValue = radioLabel.get(0).toLowerCase();
                                        if (metricV.equals("number of workout days")) {
                                            metric = "workouts";
                                        } else {
                                            metric = metricV;
                                        }
                                    }
                                }

                            }
                        });
                    }
                }
            } else {
                tvMsg.setText(leadboardProp.getResults().getMsg());
                rlMetricReport.setVisibility(View.GONE);
            }
        } else {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }

    }


}
