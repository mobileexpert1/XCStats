package com.xcstats.views.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.GoalTrack.GoalDetailTrackField;
import com.xcstats.model.goaldetailedit.GoalDetailEdit;
import com.xcstats.model.goalmeet.Result;
import com.xcstats.model.submitgoaldetail.SubmitGoalResult;
import com.xcstats.views.Adapters.GoalDetailAdapter;
import com.xcstats.views.Adapters.SelectMeet_Adapter;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Button_simple;
import com.xcstats.views.custom.Edittext_light;
import com.xcstats.views.custom.Textview_Light;

import java.lang.reflect.Field;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GoalDetailActivity extends AppCompatActivity {


    @BindView(R.id.spinner_showmeets)
    Spinner spinner_showmeets;

    @BindView(R.id.image_bckhome)
    ImageView image_bckhome;

    @BindView(R.id.txt_select)
    Textview_Light txt_select;

    @BindView(R.id.txt_eventts)
    Textview_Light txt_eventts;


    Edittext_light edit_min;


    Edittext_light edit_sec;

    @BindView(R.id.radioButton1)
    RadioButton radioButton1;

    @BindView(R.id.radioButton2)
    RadioButton radioButton2;

    @BindView(R.id.spinner_showevents)
    Spinner spinner_showevents;

    @BindView(R.id.detail_Min)
    TextView detail_Min;

    @BindView(R.id.detail_sec)
    TextView detail_sec;

    @BindView(R.id.noteET)
    Edittext_light noteET;


    @BindView(R.id.button_submit_GoalDetail)
    Button_simple button_submit_GoalDetail;

    private static String selectmeet = "";
    private static String selectevent = "";

    GoalDetailAdapter goalDetailAdapter;
    SelectMeet_Adapter selectmeetAdap;


    private static GoalDetailActivity instance;
    private GoalDetailTrackField trackData;
    private GoalDetailTrackField fielddata;
    private String[] trackDataArray;
    private String[] fieldDataArray;
    private int selctetype = 2;
    private GoalDetailTrackField data;
    private String selectmeetId;
    private int selectEventId;
    private boolean editb;
    private int goalselect;
    private boolean checkRadiClick;
    RelativeLayout relative_events;
    private int pos;
    private String event = "";
    private String meet = "Select Meet";
    private FrameLayout textFL;


    private short shrt;
    private long l;

    public static GoalDetailActivity getinstance() {
        return instance;
    }

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_detail__add);
        relative_events = (RelativeLayout) findViewById(R.id.relative_events);
        ButterKnife.bind(this);
        instance = this;
        editb = getIntent().getBooleanExtra("check", false);
        textFL = (FrameLayout) findViewById(R.id.textFL);
        edit_sec = (Edittext_light) findViewById(R.id.edit_sec);
        edit_min = (Edittext_light) findViewById(R.id.edit_min);

        edit_sec.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("####sec", "" + hasFocus);
                if (hasFocus) {
                    ((Edittext_light) v).setHint("");
                    edit_min.clearFocus();
                    edit_sec.setCursorVisible(true);
                } else {
                    setMinFeetValue();


                }

            }
        });
        setMinFeetValue();
        edit_min.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Log.d("####min", "" + hasFocus);
                if (hasFocus) {
                    ((Edittext_light) v).setHint("");
                    edit_sec.clearFocus();
                    edit_min.setCursorVisible(true);
                }
                else {
                    setMinFeetValue();
                }

            }
        });

        textFL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spinner_showevents.performClick();
            }
        });

        txt_eventts.setText("Select Event");
        if (getIntent().getBooleanExtra("check", false) == false) {
            dialogs.progressdialog(this);
            WebserviceResult.goalMeet(this,SharedPref.getString(this, "schoolid"));
        } else {
            dialogs.removedialog();
            WebserviceResult.trackField(this,getIntent().getStringExtra("track_id"));
            radioButton1.setClickable(false);
            radioButton2.setClickable(false);
            txt_select.setText(getIntent().getStringExtra("meet_name"));
            txt_eventts.setText(getIntent().getStringExtra("event_name"));
            noteET.setText(getIntent().getStringExtra("notes_before"));
            spinner_showevents.setVisibility(View.GONE);
            textFL.setVisibility(View.VISIBLE);

        }

        txt_eventts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!checkRadiClick) {
                    if (!editb) {
                        Toast.makeText(GoalDetailActivity.this, "Please Select Event Type First(i.e.Track or Field)", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    spinner_showevents.performClick();
                }
            }
        });

        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radiogroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                if (editb) {
                    return;
                }
                if (i == R.id.radioButton1) {
                    checkRadiClick = true;
                    spinner_showevents.setVisibility(View.VISIBLE);
                    selctetype = 1;
                    if (trackData == null) {
                        dialogs.progressdialog(GoalDetailActivity.this);
                        WebserviceResult.goalDetail(GoalDetailActivity.this,SharedPref.getString(GoalDetailActivity.this, "runnerid"), SharedPref.getString(GoalDetailActivity.this, "schoolid"), "" + selctetype);
                    } else {
                        handleResult(trackData);
                    }
                    setMinFeetValue();


                } else if (i == R.id.radioButton2) {
                    checkRadiClick = true;
                    selctetype = 0;
                    spinner_showevents.setVisibility(View.VISIBLE);
                    if (fielddata == null) {
                        dialogs.progressdialog(GoalDetailActivity.this);
                        WebserviceResult.goalDetail(GoalDetailActivity.this, SharedPref.getString(GoalDetailActivity.this, "runnerid"), SharedPref.getString(GoalDetailActivity.this, "schoolid"), "" + selctetype);

                    } else {
                        handleResult(fielddata);
                    }

                    setMinFeetValue();


                    if (edit_sec.getHint().equals("Inch")) {
                        edit_sec.setInputType(InputType.TYPE_CLASS_NUMBER);

                    }
                }
            }
        });

    }

    private void setMinFeetValue() {
        if (selctetype == 1) {
            detail_Min.setText("Min");
            edit_min.setHint("Min");
            detail_sec.setText("Sec");
            edit_sec.setHint("Sec");
        }
        else if (selctetype == 0) {
            detail_Min.setText("Feet");
            edit_min.setHint("Feet");
            detail_sec.setText("Inch");
            edit_sec.setHint("Inch");
        }
        else {
            detail_Min.setText("Min");
            edit_min.setHint("Min");
            detail_sec.setText("Sec");
            edit_sec.setHint("Sec");
        }
    }


    @OnClick(R.id.txt_select)
    public void opensSp() {
        spinner_showmeets.performClick();
    }


    public void updateTrack(List<com.xcstats.model.edittrackfield.Result> results) {
        if (results.get(0).getEventType().equals("1")) {
            radioButton1.setChecked(true);
            selctetype = 1;
            detail_Min.setText(results.get(0).getLongLabel());
            detail_sec.setText(results.get(0).getShortLabel());

            if (results.get(0).getLongValue() == null) {
                edit_min.setHint("Min");
            } else {
                edit_min.setText(results.get(0).getLongValue() + "");
            }
            if (results.get(0).getShortValue() == null) {
                edit_sec.setText("");

            } else {
                edit_sec.setText(results.get(0).getShortValue() + "");

            }
        } else if (results.get(0).getEventType().equals("0")) {
            selctetype = 0;
            radioButton2.setChecked(true);
            detail_Min.setText(results.get(0).getLongLabel());
            detail_sec.setText(results.get(0).getShortLabel());
            if (results.get(0).getLongValue() == null) {
                edit_min.setHint("Feet");

            } else {
                edit_min.setText(results.get(0).getLongValue() + "");
                edit_min.setHint("Feet");

            }
            if (results.get(0).getShortValue() == null) {
                edit_sec.setText("");

            } else {
                edit_sec.setText(results.get(0).getShortValue() + "");
                edit_sec.setHint("Inch");
                if (edit_sec.getHint().equals("Inch")) {
                    edit_sec.setInputType(InputType.TYPE_CLASS_NUMBER);

                }

            }
        }
    }

    public void setspinner(final List<Result> results) {
        if (results.size() > 0) {
            String[] array = new String[results.size()];
            for (int i = 0; i < results.size(); i++) {
                array[i] = results.get(i).getMeetName();
            }
            selectmeetAdap = new SelectMeet_Adapter(GoalDetailActivity.this, R.layout.select_meets, meet, array);
            spinner_showmeets.setAdapter(selectmeetAdap);
            dialogs.removedialog();
            spinner_showmeets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    if (i == 0) {
                        return;
                    }
                    selectmeet = adapterView.getItemAtPosition(i).toString();
                    selectmeetId = results.get(i).getId();
                    goalselect = i;
                    txt_select.setText(selectmeet);

                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });
        } else {
            dialogs.removedialog();


        }
    }

    @OnClick(R.id.image_bckhome)
    public void backtoGoal() {
        Intent intent = new Intent(GoalDetailActivity.this, TrackFieldActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(GoalDetailActivity.this, TrackFieldActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    public void handleResult(GoalDetailTrackField data) {
        Log.d("", data.toString());
        if (selctetype == 1) {
            trackData = data;
            trackDataArray = new String[data.getList().size()];
            for (int i = 0; i < data.getList().size(); i++) {
                trackDataArray[i] = data.getList().get(i).getName();
            }
            setspinnerEvent(trackDataArray);
            detail_Min.setText(data.getLongi());
            detail_sec.setText(data.getShrt());
        } else {
            fielddata = data;
            fieldDataArray = new String[data.getList().size()];
            for (int i = 0; i < data.getList().size(); i++) {
                fieldDataArray[i] = data.getList().get(i).getName();
            }
            setspinnerEvent(fieldDataArray);
            detail_Min.setText(data.getLongi());
            detail_sec.setText(data.getShrt());


        }

    }

    private void setspinnerEvent(String[] array) {
        goalDetailAdapter = new GoalDetailAdapter(GoalDetailActivity.this, R.layout.selectevent, event, array);
        spinner_showevents.setAdapter(goalDetailAdapter);

        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);


            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner_showevents);
            popupWindow.setHeight(convertToPx(260));
        } catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {

        }

        dialogs.removedialog();
        spinner_showevents.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selectevent = adapterView.getItemAtPosition(i).toString();
                if (i == 0) {
                    return;
                }
                txt_eventts.setText(selectevent);
                pos = i;
                if (selctetype == 0) {
                    selectEventId = fielddata.getList().get(i).getId();
                } else {
                    selectEventId = trackData.getList().get(i).getId();

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


    }

    public int convertToPx(int dp) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


    @OnClick(R.id.button_submit_GoalDetail)
    public void submit() {

        if (editb == true) {
            if (edit_min.getText().toString().trim().isEmpty() && edit_sec.getText().toString().trim().isEmpty()) {
                if (radioButton1.isChecked()) {
                    dialogs.showToast(GoalDetailActivity.this, "Please Enter Seconds");
                } else {
                    dialogs.showToast(GoalDetailActivity.this, "Please Enter Inch");
                }
            } else {
                WebserviceResult.goalDetaiEdit(GoalDetailActivity.this,getIntent().getStringExtra("track_id"), selctetype + "", noteET.getText().toString().trim(), edit_min.getText().toString().trim(), edit_sec.getText().toString().trim());
            }
        } else {
            if (goalselect == 0) {
                dialogs.showToast(GoalDetailActivity.this, "Please Select Meet");
            } else if (!checkRadiClick) {
                Toast.makeText(GoalDetailActivity.this, "Please Select Event", Toast.LENGTH_SHORT).show();
            } else if (txt_eventts.getText().equals("Select Event")) {
                Toast.makeText(instance, "Please Select Event", Toast.LENGTH_SHORT).show();
            } else if (edit_min.getText().toString().trim().isEmpty() && edit_sec.getText().toString().trim().isEmpty()) {
                if (radioButton1.isChecked()) {
                    dialogs.showToast(GoalDetailActivity.this, "Please Enter Seconds");
                } else {
                    dialogs.showToast(GoalDetailActivity.this, "Please Enter Inch");
                }

            } else {
                dialogs.progressdialog(GoalDetailActivity.this);
                String s = edit_min.getText().toString().trim();
                String s1 = edit_sec.getText().toString().trim();
                WebserviceResult.sumitGoals(GoalDetailActivity.this,SharedPref.getString(GoalDetailActivity.this, "schoolid"), SharedPref.getString(GoalDetailActivity.this, "runnerid"), selctetype + "", selectEventId + "", selectmeetId, noteET.getText().toString().trim(), s, s1);
            }
        }
    }

    public void submitDetail(SubmitGoalResult submitGoalResult) {
        dialogs.removedialog();
        if (submitGoalResult.getSuccess() == true) {
            dialogs.showToast(GoalDetailActivity.this, submitGoalResult.getResults());
        }
        Intent intent = new Intent(GoalDetailActivity.this, TrackFieldActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    public void editSubmit(GoalDetailEdit goalDetailEdit) {
        if (goalDetailEdit.getSuccess() == true) {
            dialogs.showToast(GoalDetailActivity.this, goalDetailEdit.getResults());
            Intent intent = new Intent(GoalDetailActivity.this, TrackFieldActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
            finish();

        }
    }


}
