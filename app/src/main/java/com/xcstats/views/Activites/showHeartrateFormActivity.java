package com.xcstats.views.Activites;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.HeartrateRead3.Hr;
import com.xcstats.model.HeartrateRead3.Hr__1;
import com.xcstats.model.HeartrateRead3.Hr__2;
import com.xcstats.model.HeartrateRead3.Hr__3;
import com.xcstats.model.HeartrateRead3.Hr__4;
import com.xcstats.model.HeartrateRead3.Hr__5;
import com.xcstats.model.HeartrateRead3.Hr__6;
import com.xcstats.model.addHeartRate.AddHeartRate;
import com.xcstats.model.deleteHeartRate.DeleteHeartRate;
import com.xcstats.model.heartRateForm.ShowHeartRateForm;
import com.xcstats.views.Dialogs.dialogs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class showHeartrateFormActivity extends AppCompatActivity {


    @BindView(R.id.txt_effort)
    TextView txt_effort;


    @BindView(R.id.up_arrowLL)
    LinearLayout up_arrowLL;

    @BindView(R.id.down_arrowLL)
    LinearLayout down_arrowLL;

    @BindView(R.id.rhrbackIMV)
    ImageView rhrbackIMV;


    @BindView(R.id.notesET)
    EditText notesET;

    @BindView(R.id.btn_submit)
    Button btn_submit;

    @BindView(R.id.btn_delete)
    Button btn_delete;

    @BindView(R.id.btn_cancel)
    Button btn_cancel;

    @BindView(R.id.headerTitleTV)
    TextView headerTitleTV;
    private String editid;

    Hr mon_model;
    Hr__1 tues_model;
    Hr__2 wed_model;
    Hr__3 thu_model;
    Hr__4 fri_model;
    Hr__5 sat_model;
    Hr__6 sun_model;

    String hr_ID, hr_date, hr_heartRate;
    private static showHeartrateFormActivity instance;

    int min = 60;
    int getMin;

    public static showHeartrateFormActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_heartrate_form);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ButterKnife.bind(this);
        instance = this;
//        dialogs.progressdialog(showHeartrateFormActivity.this);
        callIntentModels();

        String effortStr = txt_effort.getText().toString();
        if (!effortStr.isEmpty()) {
            getMin = Integer.parseInt(effortStr);
        } else {
            getMin = min; // default fallback
        }

//        getMin = Integer.parseInt(txt_effort.getText().toString());
        ShowNotes();
        checkHr_Id();


    }

/*    private void checkHr_Id() {
        int currentId=Integer.parseInt(hr_ID);
        if(currentId>0)
        {  btn_delete.setVisibility(View.VISIBLE); }
    }  */


    private void checkHr_Id() {
        if (hr_ID != null && !hr_ID.isEmpty()) {
            try {
                int currentId = Integer.parseInt(hr_ID);
                if (currentId > 0) {
                    btn_delete.setVisibility(View.VISIBLE);
                }
            } catch (NumberFormatException e) {
                e.printStackTrace(); // optional: log or handle parsing error
            }
        }
    }


    private void ShowNotes() {

        WebserviceResult.submitHeartRateForm(showHeartrateFormActivity.this, SharedPref.getString(showHeartrateFormActivity.this, "runnerid"), hr_heartRate, hr_ID, hr_date);
    }


    private void callIntentModels() {


        if (getIntent().getExtras() != null) {
            if (getIntent().getSerializableExtra("HeartRate_Mon") != null) {
                mon_model = (Hr) getIntent().getSerializableExtra("HeartRate_Mon");
                if (mon_model != null) {
                    hr_ID = mon_model.getHrId();
                    hr_date = mon_model.getHrDate();
                    if (mon_model.getHeartrate() != null) {
                        txt_effort.setText(String.valueOf(mon_model.getHeartrate()));
                        hr_heartRate = mon_model.getHeartrate().toString();

                    } else {
                        txt_effort.setText(String.valueOf(min));
                        hr_heartRate = String.valueOf(min);
                        Toast.makeText(this, "Please select Resting Heart Rate", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }
        if (getIntent().getExtras() != null) {
            if (getIntent().getSerializableExtra("HeartRate_Tues") != null) {
                tues_model = (Hr__1) getIntent().getSerializableExtra("HeartRate_Tues");
                if (tues_model != null) {
                    hr_ID = tues_model.getHrId();
                    hr_date = tues_model.getHrDate();
                    if (tues_model.getHeartrate() != null) {
                        txt_effort.setText(String.valueOf(tues_model.getHeartrate()));
                        hr_heartRate = tues_model.getHeartrate().toString();

                    } else {
                        txt_effort.setText(String.valueOf(min));
                        hr_heartRate = String.valueOf(min);
                        Toast.makeText(this, "Please select Resting Heart Rate", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        if (getIntent().getExtras() != null) {
            if (getIntent().getSerializableExtra("HeartRate_Wed") != null) {
                wed_model = (Hr__2) getIntent().getSerializableExtra("HeartRate_Wed");
                if (wed_model != null) {
                    hr_ID = wed_model.getHrId();
                    hr_date = wed_model.getHrDate();
                    if (wed_model.getHeartrate() != null) {
                        txt_effort.setText(String.valueOf(wed_model.getHeartrate()));
                        hr_heartRate = wed_model.getHeartrate().toString();

                    } else {
                        txt_effort.setText(String.valueOf(min));
                        hr_heartRate = String.valueOf(min);
                        Toast.makeText(this, "Please select Resting Heart Rate", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        if (getIntent().getExtras() != null) {
            if (getIntent().getSerializableExtra("HeartRate_thu") != null) {
                thu_model = (Hr__3) getIntent().getSerializableExtra("HeartRate_thu");
                if (thu_model != null) {
                    hr_ID = thu_model.getHrId();
                    hr_date = thu_model.getHrDate();
                    if (thu_model.getHeartrate() != null) {
                        txt_effort.setText(String.valueOf(thu_model.getHeartrate()));
                        hr_heartRate = thu_model.getHeartrate().toString();

                    } else {
                        txt_effort.setText(String.valueOf(min));
                        hr_heartRate = String.valueOf(min);
                        Toast.makeText(this, "Please select Resting Heart Rate", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        if (getIntent().getExtras() != null) {
            if (getIntent().getSerializableExtra("HeartRate_fri") != null) {
                fri_model = (Hr__4) getIntent().getSerializableExtra("HeartRate_fri");
                if (fri_model != null) {
                    hr_ID = fri_model.getHrId();
                    hr_date = fri_model.getHrDate();
                    if (fri_model.getHeartrate() != null) {
                        txt_effort.setText(String.valueOf(fri_model.getHeartrate()));
                        hr_heartRate = fri_model.getHeartrate().toString();

                    } else {
                        txt_effort.setText(String.valueOf(min));
                        hr_heartRate = String.valueOf(min);
                        Toast.makeText(this, "Please select Resting Heart Rate", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        if (getIntent().getExtras() != null) {

            if (getIntent().getSerializableExtra("HeartRate_ sat") != null) {
                sat_model = (Hr__5) getIntent().getSerializableExtra("HeartRate_ sat");
                if (sat_model != null) {
                    hr_ID = sat_model.getHrId();
                    hr_date = sat_model.getHrDate();
                    if (sat_model.getHeartrate() != null) {

                        txt_effort.setText(String.valueOf(sat_model.getHeartrate()));
                        hr_heartRate = sat_model.getHeartrate().toString();

                    } else {
                        txt_effort.setText(String.valueOf(min));
                        hr_heartRate = String.valueOf(min);
                        Toast.makeText(this, "Please select Resting Heart Rate", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
        if (getIntent().getExtras() != null) {
            if (getIntent().getSerializableExtra("HeartRate_ sun") != null) {
                sun_model = (Hr__6) getIntent().getSerializableExtra("HeartRate_ sun");
                if (sun_model != null) {
                    hr_ID = sun_model.getHrId();
                    SharedPref.setString(this, "Hr_Id", hr_ID);
                    hr_date = sun_model.getHrDate();
                    if (sun_model.getHeartrate() != null) {
                        txt_effort.setText(String.valueOf(sun_model.getHeartrate()));
                        hr_heartRate = sun_model.getHeartrate().toString();
                    } else {
                        txt_effort.setText(String.valueOf(min));
                        hr_heartRate = String.valueOf(min);
                        Toast.makeText(this, "Please select Resting Heart Rate", Toast.LENGTH_SHORT).show();
                    }
                }
            }

        }
    }


    @OnClick(R.id.btn_submit)
    public void btn_submit() {

        WebserviceResult.submitHeartRate(showHeartrateFormActivity.this, hr_ID, hr_date, SharedPref.getString(showHeartrateFormActivity.this, "runnerid"), txt_effort.getText().toString(), notesET.getText().toString());
        dialogs.progressdialog(showHeartrateFormActivity.this);


    }

    @OnClick(R.id.btn_cancel)
    public void btn_cancel() {

        onBackPressed();

    }

    @OnClick(R.id.btn_delete)
    public void btn_delete() {

        deleteTraingentry();


    }

    private void deleteTraingentry() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(showHeartrateFormActivity.this);
        builder.setMessage(R.string.delete);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialogs.progressdialog(showHeartrateFormActivity.this);
                dialogs.progressdialog(showHeartrateFormActivity.this);
                int currentId = Integer.parseInt(hr_ID);
                if (currentId > 0) {
                    WebserviceResult.deleteHeartRateForm(showHeartrateFormActivity.this, hr_ID);
                }


            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.show();


    }


    @OnClick(R.id.notesET)
    public void notesET() {


    }

    @OnClick(R.id.txt_effort)
    public void txt_effort() {


    }

    @OnClick(R.id.rhrbackIMV)
    public void rhrbackIMV() {

        onBackPressed();


    }


    @OnClick(R.id.down_arrowLL)
    public void down_arrowLL() {


        if (getMin > 0) {
            getMin = getMin - 1;
            txt_effort.setText(String.valueOf(getMin));
        } else {

        }

    }

    @OnClick(R.id.up_arrowLL)
    public void up_arrowLL() {


        getMin = getMin + 1;
        txt_effort.setText(String.valueOf(getMin));


//        if (min < max) {
//            min = min + 1;
//            txt_effort.setText(String.valueOf(min));
//        } else {
////            Toast.makeText(showHeartrateFormActivity.this, "Range should not be exceed 200", Toast.LENGTH_SHORT).show();
//        }


    }


    public void getSubmitHeartRateFormInfo(ShowHeartRateForm result) {
        if (result.getSuccess()) {
            if (result.getResults().getNotes() != null) {
                notesET.setText(result.getResults().getNotes().toString());
            }

//            if (result.getResults().getNotes() != null) {
            if (result.getResults().getHeading() != null) {
                headerTitleTV.setText(result.getResults().getHeading().toString());
                dialogs.removedialog();

//                } else {
////                    headerTitleTV.setText("Show HateRate Form");
//                }

//            } else {
//                dialogs.removedialog();
//                dialogs.showToast(showHeartrateFormActivity.this, "No Notes Added !");
//            }

            }
        }
    }


    public void getSubmitHeartRateInfo(AddHeartRate result) {
        if (result.getSuccess()) {
            Log.e("call", "@@@@ response " + result.getResults().toString());
            dialogs.showToast(showHeartrateFormActivity.this, "" + result.getResults());
//            Intent intent = new Intent(showHeartrateFormActivity.this, TrainingLogsActivity.class);

            SharedPref.setString(this, "OnBackPress", "1");
//            startActivity(intent);
            super.onBackPressed();
            dialogs.removedialog();

        } else {
            dialogs.showToast(showHeartrateFormActivity.this, "" + result.getResults());
            dialogs.removedialog();
        }


    }

    public void deleteHeartRateInfo(DeleteHeartRate result) {
        if (result.getSuccess()) {
            dialogs.showToast(showHeartrateFormActivity.this, " " + result.getResults());
//            Intent intent = new Intent(showHeartrateFormActivity.this, TrainingLogsActivity.class);
            SharedPref.setString(this, "DeleteHeartRate", "0");
            super.onBackPressed();
//            startActivity(intent);
            dialogs.removedialog();
        } else {
            dialogs.showToast(showHeartrateFormActivity.this, "" + result.getResults());
            dialogs.removedialog();
        }

    }


}
