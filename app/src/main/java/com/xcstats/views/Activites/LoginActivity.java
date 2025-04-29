package com.xcstats.views.Activites;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;

import android.util.Log;
import android.util.Patterns;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.appcheck.FirebaseAppCheck;
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory;
import com.google.firebase.messaging.FirebaseMessaging;
import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.login.LoginResponse;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Button_simple;
import com.xcstats.views.custom.Edittext_light;
import com.xcstats.views.custom.Textview_Light;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity {

    private static LoginActivity instance;

    private boolean BackToExitPressedOnce = false;

    @BindView(R.id.button_login)
    Button_simple button_login;

    @BindView(R.id.txt_forgot)
    Textview_Light txt_forgot;

    @BindView(R.id.edit_username)
    Edittext_light edit_username;

    @BindView(R.id.edit_password)
    Edittext_light edit_password;
    private String token;


    public static LoginActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        requestpermission();
        instance = this;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

      /*  FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                         token = task.getResult().getToken();
                        String msg = getString(R.string.fcm_token, token);
                        Log.d("##token", msg);
                    }
                });*/

        FirebaseApp.initializeApp(this);
        FirebaseAppCheck firebaseAppCheck = FirebaseAppCheck.getInstance();
        firebaseAppCheck.installAppCheckProviderFactory(PlayIntegrityAppCheckProviderFactory.getInstance());
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                if (!task.isSuccessful()) {
                    return;
                }
                token = task.getResult();
                String msg = getString(R.string.fcm_token, token);
                Log.e("##token", msg);

            }
        });
    }

    @OnClick(R.id.button_login)
    public void login() {
        doLogin();

    }

    public void doLogin() {
        String email = edit_username.getText().toString().trim();
        String password = edit_password.getText().toString().trim();

        if (email.isEmpty()) {
            dialogs.showToast(instance, "Please enter your email id");
        }
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            dialogs.showToast(instance, "Please enter a valid email id");
        }
        else if (password.isEmpty()) {
            Toast.makeText(instance, "Please enter your password", Toast.LENGTH_SHORT).show();
        }
        else if (!dialogs.isinternetavailable(this)) {
            Toast.makeText(instance, "No  Internet Connection", Toast.LENGTH_SHORT).show();
        }
        else {
            dialogs.progressdialog(this);
            WebserviceResult.Login(email, password,token);
        }

    }

    @OnClick(R.id.txt_forgot)
    public void forgot_password() {
        Intent intent = new Intent(LoginActivity.this, ForgotPassword.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();
    }


    @OnClick(R.id.txtSIGNU)
    public void SignHere(){
        Intent intent = new Intent(LoginActivity.this, RegisterationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
        finish();
    }

    public void updateRequest(LoginResponse body, String message) {
        dialogs.removedialog();
        if(body==null){
            dialogs.showToast(LoginActivity.getinstance(),message);
        }
        else{
            String bearer=body.getResults().get(0).getToken();
            String refresh_token=body.getResults().get(0).getRefresh_token();
            Log.e("Token", "Bearer: "+bearer);
            Log.e("Token", "refresh_token: "+refresh_token);
            SharedPref.setString(this, "runnerid", body.getResults().get(0).getRunnerId());
            SharedPref.setString(this, "schoolid", body.getResults().get(0).getSchoolId());
            SharedPref.setString(this, "email", body.getResults().get(0).getEmail());
            SharedPref.setString(this, "token", body.getResults().get(0).getToken());
            SharedPref.setString(this, "refresh_token", body.getResults().get(0).getRefresh_token());
            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        if (BackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.BackToExitPressedOnce = true;
        Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                BackToExitPressedOnce = false;
            }
        }, 4000);
    }


    public void requestpermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

            } else {

                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        }
    }
}






