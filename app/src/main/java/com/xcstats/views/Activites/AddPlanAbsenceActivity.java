package com.xcstats.views.Activites;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.roomorama.caldroid.CaldroidFragment;
import com.roomorama.caldroid.CaldroidListener;
import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.addplannedAbsence.AddAbsencesRecord;
import com.xcstats.model.emailcoach.CoachResult;
import com.xcstats.model.getreason.Result;
import com.xcstats.views.Adapters.EmailCoach_Adapter;
import com.xcstats.views.Adapters.ReasonAdapter;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Button_simple;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddPlanAbsenceActivity extends AppCompatActivity {

    private static AddPlanAbsenceActivity instance;

    @BindView(R.id.workoutTV)
    TextView workoutTV;

    public static String selectreason = "";

    private CaldroidFragment caldroidFragment;
    private CaldroidFragment dialogCaldroidFragment;
    private static String selectschools = "Select a Reason";

    ArrayList<String> selectedDat = new ArrayList<>();

    private ReasonAdapter reasonAdapter;

    @BindView(R.id.list_add)
    RecyclerView list_add;

    @BindView(R.id.image_home)
    ImageView image_home;

    @BindView(R.id.btn_cancel)
    Button_simple btn_cancel;

    @BindView(R.id.spinner_selectreason)
    Spinner spinner_selectreason;

    @BindView(R.id.notifySubmit)
    Button_simple notifySubmit;

    @BindView(R.id.edit_notes)
    EditText edit_notes;
    private int sppos;
    private int currentMonth;


    private void setCustomResourceForDates() {
        Calendar cal = Calendar.getInstance();

        if (caldroidFragment != null) {
            ColorDrawable blue = new ColorDrawable(getResources().getColor(R.color.caldroid_holo_blue_dark));
            ColorDrawable green = new ColorDrawable(Color.RED);

        }
    }

    public static AddPlanAbsenceActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_plan_absence_);
        instance = this;
        ButterKnife.bind(this);
        caldroidFragment = new CaldroidFragment();


        if (savedInstanceState != null) {
            caldroidFragment.restoreStatesFromKey(savedInstanceState, "CALDROID_SAVED_STATE");
        } else {
            Bundle args = new Bundle();
            Calendar cal = Calendar.getInstance();
            args.putInt(CaldroidFragment.MONTH, cal.get(Calendar.MONTH) + 1);
            args.putInt(CaldroidFragment.YEAR, cal.get(Calendar.YEAR));
            args.putBoolean(CaldroidFragment.ENABLE_SWIPE, true);
            args.putBoolean(CaldroidFragment.SIX_WEEKS_IN_CALENDAR, true);
            dialogs.progressdialog(this);
            WebserviceResult.selectcoach(this,SharedPref.getString(this, "schoolid") + "", true);
            WebserviceResult.getReasons(this);
            caldroidFragment.setArguments(args);
        }


        FragmentTransaction t = getSupportFragmentManager().beginTransaction();
        t.replace(R.id.calendar1, caldroidFragment);
        t.commit();

        final CaldroidListener listener = new CaldroidListener() {
            final SimpleDateFormat formatter = new SimpleDateFormat("dd MMM yyyy");
            final SimpleDateFormat myFormat = new SimpleDateFormat("yyyy-MM-dd");


            @Override
            public void onSelectDate(Date date, View view) {
                Calendar cal = Calendar.getInstance();
                String checkMonth = getDate(date.getTime(), "MM");
                String curDate = getDate(cal.getTimeInMillis(), "dd/MM/yyyy");
                String datecheck = getDate(date.getTime(), "dd/MM/yyyy");

                if (!curDate.equals(datecheck) && date.before(cal.getTime())) {
                    return;
                }
                String selectedDate = myFormat.format(date);
                if (selectedDat.contains(selectedDate)) {
                    selectedDat.remove(selectedDate);
                    ColorDrawable green = new ColorDrawable(Color.WHITE);
                    caldroidFragment.setBackgroundDrawableForDate(green, date);
                    if (curDate.equals(datecheck)) {
                        Drawable d = getResources().getDrawable(R.drawable.cal_date_bg);
                        caldroidFragment.setBackgroundDrawableForDate(d, date);
                    }

                } else {
                    selectedDat.add(selectedDate);
                    ColorDrawable green = new ColorDrawable(Color.BLUE);
                    caldroidFragment.setBackgroundDrawableForDate(green, date);
                }
                if ((currentMonth) < Integer.parseInt(checkMonth)) {
                    caldroidFragment.nextMonth();
                } else if ((currentMonth) > Integer.parseInt(checkMonth)) {

                    caldroidFragment.prevMonth();
                }
                caldroidFragment.refreshView();
            }

            @Override
            public void onCaldroidViewCreated() {
                caldroidFragment.setShowNavigationArrows(false);

                if (caldroidFragment.getLeftArrowButton() != null) {

                }
            }

            @Override
            public void onChangeMonth(int month, int year) {
                currentMonth = month;
                String text = "month: " + month + " year: " + year;
            }
        };

        caldroidFragment.setCaldroidListener(listener);
    }


    @OnClick(R.id.workoutTV)
    public void openSp()  {
        spinner_selectreason.performClick();
    }

    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


    public void addpresence(CoachResult coachResult) {
        if (coachResult != null) {
            EmailCoach_Adapter emailCoach_adapter = new EmailCoach_Adapter(coachResult.getResults(), AddPlanAbsenceActivity.this,false);
            list_add.setLayoutManager(new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false));
            list_add.setAdapter(emailCoach_adapter);
            dialogs.removedialog();
        } else {
            Toast.makeText(instance, "null", Toast.LENGTH_SHORT).show();
        }

    }


    public void setSpinnerReason(List<Result> results) {

        if (results.size() > 0) {

            String[] array = new String[results.size()];
            for (int i = 0; i < results.size(); i++) {
                array[i] = results.get(i).getLabel();
            }

            reasonAdapter = new ReasonAdapter(AddPlanAbsenceActivity.this, R.layout.selectreason, selectschools, array);
            spinner_selectreason.setAdapter(reasonAdapter);
            dialogs.removedialog();
            spinner_selectreason.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                    if (i == 0) {
                        spinner_selectreason.setSelection(-1);
                        return;
                    } else {
                        selectreason = adapterView.getItemAtPosition(i).toString();
                        sppos = i;
                        workoutTV.setText(selectreason);
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        } else {
            dialogs.removedialog();

        }
    }

    @OnClick(R.id.btn_cancel)
    public void cancel() {
        Intent intent = new Intent(AddPlanAbsenceActivity.this, PlannedAbsence.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    @OnClick(R.id.image_home)
    public void backtoPlanScreen() {
        Intent intent = new Intent(AddPlanAbsenceActivity.this, PlannedAbsence.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(AddPlanAbsenceActivity.this, PlannedAbsence.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    public void addAbsencesItems(AddAbsencesRecord absencesResult) {
        dialogs.removedialog();
        if (absencesResult.getSuccess() == true) {
            dialogs.showToast(AddPlanAbsenceActivity.this, absencesResult.getResults());
            Intent intent = new Intent(AddPlanAbsenceActivity.this, PlannedAbsence.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
            finish();
        }
    }

    @OnClick(R.id.notifySubmit)
    public void submitAbsence() {

        if (selectedDat.size() == 0) {
            dialogs.showToast(AddPlanAbsenceActivity.this, "Please Select Dates on which you are Planning Holidays");
        } else if (EmailCoach_Adapter.ids.size() == 0) {
            dialogs.showToast(AddPlanAbsenceActivity.this, "Please Select Coaches to Send Email");
        } else {
            String date = null;
            for (int j = 0; j < selectedDat.size(); j++) {
                if (j == 0) {
                    date = selectedDat.get(0);
                } else {
                    date = date + "," + selectedDat.get(j);
                }
            }
            dialogs.progressdialog(AddPlanAbsenceActivity.this);
            WebserviceResult.addPlannedAbsence(AddPlanAbsenceActivity.this,SharedPref.getString(this, "schoolid") + "", SharedPref.getString(this, "runnerid"), edit_notes.getText().toString().trim(), selectreason, date);

        }
    }
}










