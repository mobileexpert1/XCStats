package com.xcstats.views.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
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
import com.xcstats.model.HeartrateRead3.ResponseHeartRate;
import com.xcstats.model.plannedabsence.DayWiseWorkout;
import com.xcstats.model.plannedabsence.PlannedAbsenseResult;
import com.xcstats.model.plannedabsence.Workoutdata;
import com.xcstats.views.Adapters.TrainingLogGridAdapter;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.ExpandedGridView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrainingLogsActivity extends AppCompatActivity {

    private static TrainingLogsActivity instance;
    Intent intent = getIntent();
    private static int counter = 0;
    Hr mon_model;
    Hr__1 tues_model;
    Hr__2 wed_model;
    Hr__3 thu_model;
    Hr__4 fri_model;
    Hr__5 sat_model;
    Hr__6 sun_model;


    @BindView(R.id.image_homes)
    ImageView image_homes;


    @BindView(R.id.backward)
    ImageView backward;


    @BindView(R.id.ivLeadboard)
    TextView ivLeadboard;

    @BindView(R.id.forward)
    ImageView forward;

    ExpandedGridView gridview;

    @BindView(R.id.txt_date)
    TextView txt_date;

    @BindView(R.id.buttonTime)
    Button buttonTime;

    @BindView(R.id.buttonMiles)
    Button buttonMiles;

    @BindView(R.id.monTV)
    TextView monTV;

    @BindView(R.id.tueTV)
    TextView tueTV;

    @BindView(R.id.wedTv)
    TextView wedTv;

    @BindView(R.id.thuTV)
    TextView thuTV;

    @BindView(R.id.friTV)
    TextView friTV;

    @BindView(R.id.satTV)
    TextView satTV;

    @BindView(R.id.sunTV)
    TextView sunTV;


    private List<Date> dateList;
    private List<String> mondayList;
    private List<String> sundayList;
    int position = 0;
    private String finaldate = "";

    public static TrainingLogsActivity getinstance() {
        return instance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_logs);
        Log.e("12344", "datanew" + mondayList);
        gridview = (ExpandedGridView) findViewById(R.id.gridview);
        gridview.setExpanded(true);
        ButterKnife.bind(this);
        instance = this;
        dialogs.progressdialog(TrainingLogsActivity.this);
        setCalender();



//        WebserviceResult.getHeartRate(RUNNER_ID, WEEK_START_DATE);

    }


    @OnClick(R.id.monTV)
    public void monTV() {

        Intent intent = new Intent(TrainingLogsActivity.this, showHeartrateFormActivity.class);
        intent.putExtra("HeartRate_Mon", mon_model);
        startActivity(intent);

    }


    @OnClick(R.id.tueTV)
    public void tueTV() {

        Intent intent = new Intent(TrainingLogsActivity.this, showHeartrateFormActivity.class);
        intent.putExtra("HeartRate_Tues", tues_model);
        startActivity(intent);

    }

    @OnClick(R.id.wedTv)
    public void wedTv() {

        Intent intent = new Intent(TrainingLogsActivity.this, showHeartrateFormActivity.class);
        intent.putExtra("HeartRate_Wed", wed_model);
        startActivity(intent);

    }


    @OnClick(R.id.thuTV)
    public void thuTV() {

        Intent intent = new Intent(TrainingLogsActivity.this, showHeartrateFormActivity.class);
        intent.putExtra("HeartRate_thu", thu_model);
        startActivity(intent);

    }


    @OnClick(R.id.friTV)
    public void friTV() {

        Intent intent = new Intent(TrainingLogsActivity.this, showHeartrateFormActivity.class);
        intent.putExtra("HeartRate_fri", fri_model);
        startActivity(intent);

    }


    @OnClick(R.id.satTV)
    public void satTV() {
        Intent intent = new Intent(TrainingLogsActivity.this, showHeartrateFormActivity.class);
        intent.putExtra("HeartRate_ sat", sat_model);
        startActivity(intent);

    }


    @OnClick(R.id.sunTV)
    public void sunTV() {

        Intent intent = new Intent(TrainingLogsActivity.this, showHeartrateFormActivity.class);
        intent.putExtra("HeartRate_ sun", sun_model);
        startActivity(intent);

    }


    @OnClick(R.id.image_homes)
    public void home() {
        Intent intent = new Intent(TrainingLogsActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(TrainingLogsActivity.this, HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }


    public void setCalender() {
        Calendar now = Calendar.getInstance();
        System.out.println("Current date : " + (now.get(Calendar.MONTH) + 1) + "-" + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));
        now = Calendar.getInstance();
        mondayList = new ArrayList<>();
        sundayList = new ArrayList<>();
        if (now.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            long enddateMilli = now.getTimeInMillis();
            now.add(Calendar.YEAR, -3);
            System.out.println("date before 3 months : " + (now.get(Calendar.MONTH) + 1) + "-"
                    + now.get(Calendar.DATE) + "-" + now.get(Calendar.YEAR));

            long startdateMilli = now.getTimeInMillis();
            Date startDate = new Date(startdateMilli);
            Date enddate = new Date(enddateMilli);
            dateList = getDaysBetweenDates(startDate, enddate);
        } else {
            Calendar calendar = Calendar.getInstance();
            int weekday = calendar.get(Calendar.DAY_OF_WEEK);
            int days = Calendar.SUNDAY - weekday;
            if (days < 0) {
                //l this will usually be the case since Calendar.SUNDAY is the smallest
                days += 7;
            }
            calendar.add(Calendar.DAY_OF_YEAR, days);

            // String emnDate = getDate(calendar.getTimeInMillis(), "MMM dd yyyy");
            Date enddate = new Date(calendar.getTimeInMillis());
            calendar.setTime(enddate);
            calendar.add(Calendar.YEAR, -3);
            int endWeekday = calendar.get(Calendar.DAY_OF_WEEK);
            Date startDate = new Date(calendar.getTimeInMillis());

            int enddays = Calendar.MONDAY - weekday;
            if (enddays < 0) {
                // this will usually be the case since Calendar.SUNDAY is the smallest
                SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy");
                calendar.setTimeZone(TimeZone.getDefault());

                // get start of this week in milliseconds
                calendar.setFirstDayOfWeek(Calendar.MONDAY);
                calendar.set(Calendar.DAY_OF_WEEK, calendar.MONDAY);
                startDate = calendar.getTime();
                String date = dateFormat.format(enddate);
            }
            dateList = getDaysBetweenDates(startDate, enddate);


        }


        Collections.reverse(dateList);
        for (int j = 0; j < dateList.size(); j++) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateList.get(j));
            WeekDayData data = null;

            if (sundayList.size() == 144 && mondayList.size() == 144) {
                break;
            }
            if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
                mondayList.add(getDate(cal.getTimeInMillis(), "MMM dd, yyyy"));
            } else if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                {
                    sundayList.add(getDate(cal.getTimeInMillis(), "MMM dd, yyyy"));

                }
            }
        }

        txt_date.setText(mondayList.get(0) + " to " + sundayList.get(0));
        //String finaldate = null;
        try {
            finaldate = formatDate();
        }
        catch (ParseException e) {
            e.printStackTrace();
        }
        WebserviceResult.traingLogsRead(this,SharedPref.getString(TrainingLogsActivity.this, "runnerid"), finaldate);
        WebserviceResult.getHeartRate(TrainingLogsActivity.this,SharedPref.getString(TrainingLogsActivity.this, "runnerid"), finaldate);


    }


    public static String getDate(long milliSeconds, String dateFormat) {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }


    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        dates.add(enddate);
        return dates;
    }

    public void getHeartRateInfo(ResponseHeartRate result) {
        if (result.getSuccess()) {

            if (result.getResults().getDaily() != null) {


                mon_model = result.getResults().getDaily().getMon().getHr();
                tues_model = result.getResults().getDaily().getTue().getHr();
                wed_model = result.getResults().getDaily().getWed().getHr();
                thu_model = result.getResults().getDaily().getThu().getHr();
                fri_model = result.getResults().getDaily().getFri().getHr();
                sat_model = result.getResults().getDaily().getSat().getHr();
                sun_model = result.getResults().getDaily().getSun().getHr();


                if (result.getResults().getDaily().getMon().getHr().getHeartrate() == null) {
                    monTV.setText("Add");
                } else {
                    monTV.setText(result.getResults().getDaily().getMon().getHr().getHeartrate().toString());
                }
                if (result.getResults().getDaily().getTue().getHr().getHeartrate() == null) {
                    tueTV.setText("Add");
                } else {
                    tueTV.setText(result.getResults().getDaily().getTue().getHr().getHeartrate().toString());

                }
                if (result.getResults().getDaily().getWed().getHr().getHeartrate() == null) {
                    wedTv.setText("Add");
                } else {
                    wedTv.setText(result.getResults().getDaily().getWed().getHr().getHeartrate().toString());

                }
                if (result.getResults().getDaily().getThu().getHr().getHeartrate() == null) {
                    thuTV.setText("Add");
                } else {
                    thuTV.setText(result.getResults().getDaily().getThu().getHr().getHeartrate().toString());

                }
                if (result.getResults().getDaily().getFri().getHr().getHeartrate() == null) {
                    friTV.setText("Add");
                } else {
                    friTV.setText(result.getResults().getDaily().getFri().getHr().getHeartrate().toString());

                }
                if (result.getResults().getDaily().getSat().getHr().getHeartrate() == null) {
                    satTV.setText("Add");
                } else {
                    satTV.setText(result.getResults().getDaily().getSat().getHr().getHeartrate().toString());

                }

                if (result.getResults().getDaily().getSun().getHr().getHeartrate() == null) {
                    sunTV.setText("Add");
                } else {
                    sunTV.setText(result.getResults().getDaily().getSun().getHr().getHeartrate().toString());

                }
                dialogs.removedialog();
            }

        }
        dialogs.removedialog();
    }


    public void handleData(ArrayList<DayWiseWorkout> list, PlannedAbsenseResult data) {
        buttonTime.setText(data.getSummary().getSummary().getWeeklyTime());
        buttonMiles.setText(data.getSummary().getSummary().getWeeklyDistance() + "");
        List<Workoutdata> workoutdataList = new ArrayList<>();
        for (int j = 0; j < list.size(); j++) {
            workoutdataList.add(list.get(j).getDayone());
            workoutdataList.add(list.get(j).getDaytwo());
        }

        TrainingLogGridAdapter adapter = new TrainingLogGridAdapter(this, workoutdataList);
        gridview.setAdapter(adapter);
        dialogs.removedialog();

    }

    @OnClick(R.id.forward)
    public void forward() {
        try {
            if (position == 0) {
                return;
            }
            position = position - 1;
            txt_date.setText(mondayList.get(position) + " to " + sundayList.get(position));
            finaldate = formatDate();
            dialogs.progressdialog(TrainingLogsActivity.this);
            WebserviceResult.traingLogsRead(this,SharedPref.getString(TrainingLogsActivity.this, "runnerid"), finaldate);
            WebserviceResult.getHeartRate(TrainingLogsActivity.this,SharedPref.getString(TrainingLogsActivity.this, "runnerid"), finaldate);
        } catch (Exception e) {

        }
    }

    private String formatDate() throws ParseException {
        SimpleDateFormat format1 = new SimpleDateFormat("MMM dd, yyyy");
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        Date date = format1.parse(mondayList.get(position));
        return format2.format(date);
    }


    @OnClick(R.id.backward)
    public void setBackward() {

        try {
            if (position == 144) {

                return;

            }

            position = position + 1;

            txt_date.setText(mondayList.get(position) + " to " + sundayList.get(position));
            finaldate = formatDate();
            Log.e("@@@@", "finsldate" + finaldate);
            dialogs.progressdialog(TrainingLogsActivity.this);
            WebserviceResult.traingLogsRead(this,SharedPref.getString(TrainingLogsActivity.this, "runnerid"), finaldate);
            WebserviceResult.getHeartRate(TrainingLogsActivity.this,SharedPref.getString(TrainingLogsActivity.this, "runnerid"), finaldate);



        } catch
        (Exception e) {

        }


    }


    @OnClick(R.id.ivLeadboard)
    public void openLeadBoardScreen() {
        Intent intent = new Intent(TrainingLogsActivity.this, LeadboardActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();

    }


    @Override
    protected void onResume() {
        super.onResume();
        if (!finaldate.isEmpty()) {
            WebserviceResult.traingLogsRead(this,SharedPref.getString(TrainingLogsActivity.this, "runnerid"), finaldate);
        }

        if (SharedPref.getString(this, "OnBackPress").equals("1")) {
            WebserviceResult.getHeartRate(TrainingLogsActivity.this,SharedPref.getString(TrainingLogsActivity.this, "runnerid"), finaldate);

        }
        if (SharedPref.getString(this,"DeleteHeartRate").equals("0")){

          //  WebserviceResult.deleteHeartRateForm(SharedPref.getString(this,"Hr_Id"));
            WebserviceResult.getHeartRate(TrainingLogsActivity.this,SharedPref.getString(TrainingLogsActivity.this, "runnerid"), finaldate);
        }

    }
}
