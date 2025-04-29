package com.xcstats.views.Dialogs;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.trackfield.Result;
import com.xcstats.views.Activites.HomeActivity;
import com.xcstats.views.Activites.LoginActivity;
import com.xcstats.views.Activites.ParentsLogin;
import com.xcstats.views.Activites.PhotoActivity;
import com.xcstats.views.Activites.Splash;
import com.xcstats.views.Activites.UploadImage;
import com.xcstats.views.Adapters.ReviewPast_Adapter;
import com.xcstats.views.custom.Edittext_light;

import java.util.List;

import static com.xcstats.R.id.edit_firstname;

/**
 * Created by Mobile on 12/15/2016.
 */

public class dialogs {
    public static int RESULT_LOAD_IMAGE = 2;
    public static Dialog alertDialogBuilder;
    private static ProgressDialog progressDialog;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMISSIONS = 2;
    public static void showtDialog(final Activity activity, final int count) {


        View alet_view = null;
        alertDialogBuilder = new Dialog(activity, R.style.DialogTheme);
        alertDialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        alet_view = mInflater.inflate(R.layout.add_parents, null);
        alertDialogBuilder.setContentView(alet_view);
        alertDialogBuilder.setCancelable(true);
        final EditText edit_name = (EditText) alet_view.findViewById(R.id.edit_firstname);
        final EditText edit_lastname = (EditText) alet_view.findViewById(R.id.edit_lastname);
        final EditText edit_email = (EditText) alet_view.findViewById(R.id.edit_email);
        final RelativeLayout parentsplash=(RelativeLayout)alet_view.findViewById(R.id.parentsplash);

        ImageView close = (ImageView) alet_view.findViewById(R.id.img_close);
        Button button = (Button) alet_view.findViewById(R.id.button_donealert);
        parentsplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, ParentsLogin.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
                activity .finish();
            }
        });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_name.getText().toString().trim().isEmpty()) {
                    showToast(activity, "Please enter your first name");
                } else if (edit_lastname.getText().toString().isEmpty()) {
                    showToast(activity, "Please  enter your last name");
                } else if (edit_email.getText().toString().isEmpty()) {
                    showToast(activity, "Please enter your email id");
                } else if (!edit_email.getText().toString().contains("@") && !edit_email.getText().toString().contains(".")) {
                    showToast(activity, "Please enter your valid email id");

                } else {
                    if (count < 4) {
                        dialogs.progressdialog(activity);
                        WebserviceResult.addEditParent(activity,"0", SharedPref.getString(activity, "runnerid"), edit_name.getText().toString().trim(), edit_lastname.getText().toString().trim(), edit_email.getText().toString().trim());
                        alertDialogBuilder.dismiss();
                    } else {
                        dialogs.showToast(activity, "You Can Only Add Maximum of Four Parents");

                    }
                }

            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.dismiss();
            }
        });
        alertDialogBuilder.show();

    }


    public static void showToast(Activity act, String msg) {
        Toast.makeText(act, msg, Toast.LENGTH_SHORT).show();
    }

    public static void profiledialog(final Activity activity, final int b) {

        View alet_view = null;
        alertDialogBuilder = new Dialog(activity);
        alertDialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Window window = alertDialogBuilder.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(ActionBar.LayoutParams.FILL_PARENT, ActionBar.LayoutParams.FILL_PARENT);
        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        alet_view = mInflater.inflate(R.layout.profile_layout, null);
        alertDialogBuilder.setContentView(alet_view);
        alertDialogBuilder.setCancelable(true);

        final RelativeLayout relativeLayout = (RelativeLayout) alet_view.findViewById(R.id.relative_gallery);
        final RelativeLayout relativeLayout1 = (RelativeLayout) alet_view.findViewById(R.id.relative_camera);
        final RelativeLayout relativeLayout2 = (RelativeLayout) alet_view.findViewById(R.id.relative_cancel);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (b == 1) {
                    PhotoActivity.getinstance().gallerypick();
                } else {
                    UploadImage.getinstance().gallerypick();
                }


            }
        });

        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (b == 1) {
                    Log.d("gggg","onClick");
                    PhotoActivity.getinstance().dispatchTakePictureIntent();

                } else {
                    UploadImage.getinstance().dispatchTakePictureIntent();

                }
            }
        });

        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogBuilder.dismiss();

            }
        });
        alertDialogBuilder.show();
    }






    public static void dismissdialog() {
        alertDialogBuilder.dismiss();
    }


    public static void Uploaddialog(final Activity activity) {


        View alet_view = null;

        alertDialogBuilder = new Dialog(activity);
        Window window = alertDialogBuilder.getWindow();
        window.setGravity(Gravity.BOTTOM);
        window.setLayout(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        alertDialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        alet_view = mInflater.inflate(R.layout.upload_dialog, null);
        alertDialogBuilder.setContentView(alet_view);
        alertDialogBuilder.setCancelable(false);

        final RelativeLayout relativeLayout = (RelativeLayout) alet_view.findViewById(R.id.relative_gallery);
        final RelativeLayout relativeLayout1 = (RelativeLayout) alet_view.findViewById(R.id.relative_camera);
        final RelativeLayout relativeLayout2 = (RelativeLayout) alet_view.findViewById(R.id.relative_cancel);

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //    ProfileActivity.getinstance().gallerypick();

            }
        });
        relativeLayout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ProfileActivity.getinstance().dispatchTakePictureIntent();
            }
        });

        relativeLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogBuilder.dismiss();

            }
        });
        alertDialogBuilder.show();

    }

    public static void dismissdialog1() {
        alertDialogBuilder.dismiss();
    }

 /*   public static void progressdialog(Activity activity) {
        progressDialog = new ProgressDialog(activity);
        progressDialog.setMessage("Loading...");
//        progressDialog.setCancelable(false);
//        progressDialog.setCanceledOnTouchOutside(false);
        progressDialog.show();
    }*/

    public static void progressdialog(Activity activity) {
        if (activity == null || activity.isFinishing() || activity.isDestroyed()) return;
        try {
            progressDialog = new ProgressDialog(activity);
            progressDialog.setMessage("Loading...");
//            progressDialog.setCancelable(false);
//            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void removedialog() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (progressDialog!=null && progressDialog.isShowing()){
                    try {
                        progressDialog.dismiss();
                    }catch (Exception e){
                        Log.e("call","exception "+e.toString());
                    }
                }
            }
        }, 800);

    }

    public static boolean isinternetavailable(Activity activity) {
        ConnectivityManager conMgr = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        return conMgr.getActiveNetworkInfo() != null && conMgr.getActiveNetworkInfo().isAvailable() && conMgr.getActiveNetworkInfo().isConnected();

    }


    public static void showedit(final Activity activity) {
        View alet_view = null;
        alertDialogBuilder = new Dialog(activity);
        alertDialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        alet_view = mInflater.inflate(R.layout.add_parents, null);
        alertDialogBuilder.setContentView(alet_view);
        alertDialogBuilder.setCancelable(false);

        final EditText edit_name = (EditText) alet_view.findViewById(R.id.edit_firstname);
        final EditText edit_lastname = (EditText) alet_view.findViewById(R.id.edit_lastname);
        final EditText edit_email = (EditText) alet_view.findViewById(R.id.edit_email);
        ImageView close = (ImageView) alet_view.findViewById(R.id.img_close);
        Button button = (Button) alet_view.findViewById(R.id.button_donealert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.dismiss();
            }
        });
        alertDialogBuilder.show();

    }

    public static void showReviewDialog(Activity activity, List<com.xcstats.model.pastgoals.Result> results, int i) {

        View alet_view = null;
        alertDialogBuilder = new Dialog(activity);
        alertDialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        alet_view = mInflater.inflate(R.layout.viewpast_result, null);
        alertDialogBuilder.setContentView(alet_view);
        alertDialogBuilder.setCancelable(false);
        final TextView textview = (TextView) alet_view.findViewById(R.id.date);
        final TextView meet = (TextView) alet_view.findViewById(R.id.meet);
        final TextView event = (TextView) alet_view.findViewById(R.id.event);
        final TextView goal = (TextView) alet_view.findViewById(R.id.goal);
        final TextView result = (TextView) alet_view.findViewById(R.id.result);
        final Button button = (Button) alet_view.findViewById(R.id.button_submit);


        String meetDate = results.get(i).getMeetDate();
        String meetname = results.get(i).getMeetName();
        String name = results.get(i).getName();
        String goalFormat = results.get(i).getGoalFormat();

        textview.setText(meetDate);
        meet.setText(meetname);
        event.setText(name);
        goal.setText(goalFormat);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogBuilder.dismiss();
            }
        });

        alertDialogBuilder.show();

    }


    public static void showAllNotesdialog(final Activity activity, List<com.xcstats.model.pastgoals.Result> results, final int i) {

        View alet_view = null;
        alertDialogBuilder = new Dialog(activity);
        alertDialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        alet_view = mInflater.inflate(R.layout.viewall_notes, null);
        alertDialogBuilder.setContentView(alet_view);
        alertDialogBuilder.setCancelable(true);
        final TextView txt_notes=(TextView)alet_view.findViewById(R.id.txt_notes);
        final TextView textview = (TextView) alet_view.findViewById(R.id.date);
        final TextView meet = (TextView) alet_view.findViewById(R.id.meet);
        final TextView event = (TextView) alet_view.findViewById(R.id.event);
        final TextView goal = (TextView) alet_view.findViewById(R.id.goal);
        final TextView resultshow = (TextView) alet_view.findViewById(R.id.result);
        final EditText notesEt = (EditText) alet_view.findViewById(R.id.notesEt);
        final TextView preNT=(TextView)alet_view.findViewById(R.id.preNT);
        final ImageView img_close = (ImageView) alet_view.findViewById(R.id.img_close);
        final Button button = (Button) alet_view.findViewById(R.id.button_submit);


        String meetDate = results.get(i).getMeetDate();
        String meetname = results.get(i).getMeetName();
        String name = results.get(i).getName();
        String goalFormat = results.get(i).getGoalFormat();
        String before = results.get(i).getNotesBefore();
        if (results.get(i).getNotesBefore()==null){
            preNT.setText("");
        }
        else {
            preNT.setText(results.get(i).getNotesBefore());
        }
        if (results.get(i).getResult().contains("(")){

            String resultn = results.get(i).getResult().substring(0,results.get(i).getResult().indexOf("("));
            String resultn1=results.get(i).getResult().substring(results.get(i).getResult().indexOf("("),results.get(i).getResult().length());
            resultshow.setText(resultn+"\n"+resultn1);
        }
        else {
            resultshow.setText(results.get(i).getResult());
        }

        String notesafter = results.get(i).getNotesAfter();

        preNT.setText(before);
        textview.setText(meetDate);
        meet.setText(meetname);
        event.setText(name);
        goal.setText(goalFormat);
        notesEt.setText(notesafter);

        img_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialogBuilder.dismiss();

            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogs.progressdialog(activity);
                WebserviceResult.reviewSubmit(activity,ReviewPast_Adapter.goalid[i], notesEt.getText().toString().trim());
                alertDialogBuilder.dismiss();



            }
        });

        alertDialogBuilder.show();

    }


    public static void viewUpcomingGoal(Activity activity, List<Result> results, final int i) {


        View alet_view = null;
        alertDialogBuilder = new Dialog(activity);
        alertDialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        alet_view = mInflater.inflate(R.layout.view_upcoming, null);
        alertDialogBuilder.setContentView(alet_view);
        alertDialogBuilder.setCancelable(true);
        final TextView textdate = (TextView) alet_view.findViewById(R.id.date);
        final TextView textmeet = (TextView) alet_view.findViewById(R.id.meet);
        final TextView textevent = (TextView) alet_view.findViewById(R.id.event);
        final TextView textgoal = (TextView) alet_view.findViewById(R.id.goal);

        String date = results.get(i).getShortDate();
        String meet = results.get(i).getMeetName();
        String event = results.get(i).getEventName();
        String goal = results.get(i).getGoal();

        textdate.setText(date);
        textmeet.setText(meet);
        textevent.setText(event);
        textgoal.setText(goal);

        alertDialogBuilder.show();


    }


    public static void showContactDialog(final Activity activity, String a, String b, String c, final int id) {

        View alet_view = null;
        alertDialogBuilder = new Dialog(activity,R.style.DialogTheme);
        alertDialogBuilder.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        alertDialogBuilder.requestWindowFeature(Window.FEATURE_NO_TITLE);
        LayoutInflater mInflater = (LayoutInflater) activity.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        alet_view = mInflater.inflate(R.layout.add_parents, null);
        alertDialogBuilder.setContentView(alet_view);
        alertDialogBuilder.setCancelable(false);

        final Edittext_light edit_name = (Edittext_light) alet_view.findViewById(edit_firstname);
        final Edittext_light edit_lastname = (Edittext_light) alet_view.findViewById(R.id.edit_lastname);
        final Edittext_light edit_email = (Edittext_light) alet_view.findViewById(R.id.edit_email);
        final RelativeLayout parentsplash =(RelativeLayout)alet_view.findViewById(R.id.parentsplash);
        parentsplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(activity, ParentsLogin.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
                activity .finish();

            }
        });
        edit_name.setText(a);
        edit_lastname.setText(b);
        edit_email.setText(c);

        ImageView close = (ImageView) alet_view.findViewById(R.id.img_close);
        Button button = (Button) alet_view.findViewById(R.id.button_donealert);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogs.progressdialog(activity);
                WebserviceResult.addEditParent(activity,id + "", SharedPref.getString(activity, "runnerid"), edit_name.getText().toString().trim(), edit_lastname.getText().toString().trim(), edit_email.getText().toString().trim());
                alertDialogBuilder.dismiss();

            }
        });

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder.dismiss();
            }
        });
        dialogs.removedialog();
        WebserviceResult.getparentresult(activity,SharedPref.getString(activity, "runnerid"));

        alertDialogBuilder.show();

    }


}








