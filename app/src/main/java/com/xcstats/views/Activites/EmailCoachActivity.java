package com.xcstats.views.Activites;

import android.content.Intent;
import android.os.Bundle;

import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.emailcoach.CoachResult;
import com.xcstats.model.emailcoach.Result;
import com.xcstats.model.sendemailcoach.SendEmail;
import com.xcstats.views.Adapters.EmailAnnouncementAdapter;
import com.xcstats.views.Adapters.EmailCoach_Adapter;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Edittext_light;
import com.xcstats.views.custom.Textview_Light;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EmailCoachActivity extends AppCompatActivity {
    private static EmailCoachActivity instance;

    @BindView(R.id.image_backhome)
    ImageView image_backhome;

    @BindView(R.id.recycler_coach)
    RecyclerView recycler_coach;

    @BindView(R.id.edit_subjects)
    Edittext_light edit_subjects;

    @BindView(R.id.tvBody)
    Textview_Light tvBody;

    @BindView(R.id.tt)
    Textview_Light tt;

    @BindView(R.id.edit_message)
    Edittext_light edit_message;

    @BindView(R.id.btn_done)
    Button btn_done;

    private String fromAnnouncements, coachId, subjectText,fromNotes;

    boolean fromCoachside ;

    private String subject,body;
    private String schoolId;
    private String id;
    private int type;


    public static EmailCoachActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email__coach);
        ButterKnife.bind(this);
        instance = this;




//        if (fromNotes.equalsIgnoreCase("fromNotes")) {
//
//            Toast.makeText(this, coachId+" "+schoolId+" "+id, Toast.LENGTH_SHORT).show();
//        }


        type = getIntent().getIntExtra("type",0);



        if (type == 2) {
            coachId = getIntent().getStringExtra("coachId");
            subjectText = getIntent().getStringExtra("subjectText");
            dialogs.progressdialog(this);
            fromCoachside = true;
//            WebserviceResult.selectcoach(SharedPref.getString(this, "schoolid") + "", false);

            WebserviceResult.selectcoach(EmailCoachActivity.this,SharedPref.getString(this, "schoolid") + "",false,coachId, subjectText);
        }

        else  if (type == 3){
            fromCoachside = true;

            coachId = getIntent().getStringExtra("coachId");
            schoolId = getIntent().getStringExtra("schoolId");
            id = getIntent().getStringExtra("id");
            WebserviceResult.coachReply(this,schoolId,false,coachId, id);

        }

        else {
            dialogs.progressdialog(this);
            WebserviceResult.selectcoach(this,SharedPref.getString(this, "schoolid") + "", false);
        }


    }

    @OnClick(R.id.image_backhome)
    public void backtohome() {
        if (EmailCoach_Adapter.ids != null)
            EmailCoach_Adapter.ids.clear();
        Intent intent = new Intent(EmailCoachActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    @Override
    public void onBackPressed() {
        if (EmailCoach_Adapter.ids != null)
            EmailCoach_Adapter.ids.clear();
        Intent intent = new Intent(EmailCoachActivity.this, HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    @OnClick(R.id.btn_done)
    public void done() {
        if (EmailCoach_Adapter.ids.size() != 0) {
            if (edit_subjects.getText().toString().trim().length() == 0) {
                Toast.makeText(instance, "Subject Can't be Blank", Toast.LENGTH_SHORT).show();
                return;
            } else
                dialogs.progressdialog(EmailCoachActivity.this);
            WebserviceResult.sendEmail(EmailCoachActivity.this,SharedPref.getString(this, "schoolid"), SharedPref.getString(this, "runnerid"), edit_message.getText().toString().trim(), edit_subjects.getText().toString().trim(), EmailCoach_Adapter.ids);
        } else {
            Toast.makeText(instance, "Please Select Coaches To Send Email", Toast.LENGTH_SHORT).show();
        }


    }

    public void updateserverresonse(CoachResult coachResult) {

        if (coachResult != null) {
            List<Result> tempResult = new ArrayList<>();
            EmailCoach_Adapter.ids.clear();

            if (fromCoachside) {
               for(Result data : coachResult.getResults()){
                   subject = coachResult.getSubjectText();
                   body = coachResult.getBody();
                   edit_subjects.setText(subject);
                   if (!body.equals("")){
                       tvBody.setVisibility(View.VISIBLE);
                       tt.setVisibility(View.VISIBLE);
                       tvBody.setText(body);
                       tvBody.setMovementMethod(new ScrollingMovementMethod());

                   }
                   if (data.getChecked()!=null) {
                       if (data.getChecked().equals("checked")) {
                           EmailCoach_Adapter.ids.add(data.getCoachId());
                           data.setB(true);
                           tempResult.add(data);
                       }
                   }

               }


            }
            else {
//               tempResult.addAll(coachResult.getResults());
            }


                EmailCoach_Adapter emailCoach_adapter = new EmailCoach_Adapter(coachResult.getResults(), this,fromCoachside);
                recycler_coach.setLayoutManager(new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false));
                recycler_coach.setAdapter(emailCoach_adapter);
                dialogs.removedialog();

        } else {
            Toast.makeText(instance, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }


    }



    public void updateserverresonseNew(CoachResult coachResult) {
        if (coachResult != null) {
            EmailAnnouncementAdapter emailCoach_adapter = new EmailAnnouncementAdapter(coachResult.getResults(), this);
            recycler_coach.setLayoutManager(new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false));
            recycler_coach.setAdapter(emailCoach_adapter);
            dialogs.removedialog();
        } else {
            Toast.makeText(instance, "Something Went Wrong", Toast.LENGTH_SHORT).show();
        }


    }

    public void send(SendEmail send) {
        dialogs.removedialog();
        if (send.getSuccess() == true) {
            dialogs.showToast(this, send.getResults());
        }
        Intent intent = new Intent(EmailCoachActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();


    }
}
