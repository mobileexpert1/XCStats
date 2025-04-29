package com.xcstats.views.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.model.forgotpassword.ForgotResult;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Button_simple;
import com.xcstats.views.custom.Edittext_light;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPassword extends AppCompatActivity {

    private static ForgotPassword instance;

    @BindView(R.id.btn_submit)
    Button_simple btn_submit;

    @BindView(R.id.img_backforgot)
    ImageView img_backforgot;

    @BindView(R.id.edit_emailaddress)
    Edittext_light edit_emailaddress;

    public static ForgotPassword getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        instance = this;
    }

    @OnClick(R.id.btn_submit)
    public void submit() {
        String email = edit_emailaddress.getText().toString().trim();
        if (email.isEmpty()) {
            dialogs.showToast(this, "Please enter a email id");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            dialogs.showToast(this, "Please enter your valid email id");
        } else {
            dialogs.progressdialog(this);
            WebserviceResult.forgotPassword(email);

        }

    }

    @OnClick(R.id.img_backforgot)
    public void back() {
        Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ForgotPassword.this, LoginActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    public void updateForgotPassword(ForgotResult result) {
        dialogs.removedialog();
        Boolean checked = result.getSuccess();
        if (checked) {
            if (result.getSuccess()) {
                dialogs.showToast(this, result.getResults());
                Intent in = new Intent(ForgotPassword.this, LoginActivity.class);
                startActivity(in);
            }
        } else {
            dialogs.showToast(this, result.getResults());
        }

    }


}
