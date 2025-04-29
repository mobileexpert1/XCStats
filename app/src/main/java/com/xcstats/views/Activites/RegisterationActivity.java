package com.xcstats.views.Activites;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.checkRegestration.RegisterationCode;
import com.xcstats.model.getschool.Result;
import com.xcstats.views.Adapters.SelectSchoolAdapter;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Button_simple;
import com.xcstats.views.custom.Edittext_light;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class
RegisterationActivity extends AppCompatActivity {

    public static RegisterationActivity instance;
    @BindView(R.id.btn_done_reg)
    Button_simple btn_done_reg;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.relative_spinner)
    RelativeLayout relative_spinner;

    @BindView(R.id.edit_regcode)
    Edittext_light edit_regcode;

    @BindView(R.id.spinner_enterschool)
    Spinner spinner_enterschool;
    private Boolean exit = false;

    @BindView(R.id.spTV)
    TextView spTV;

    @BindView(R.id.ageCheckBox)
    CheckBox ageCheckBox;

    private static String selectschools = "Select School";
    private SharedPreferences pref;
    private String[] arrayid;
    private int validselect;

    SelectSchoolAdapter selectSchoolAdapter;

    public static RegisterationActivity getinstance() {
        return instance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        instance = this;
        ButterKnife.bind(this);
        dialogs.progressdialog(this);

        WebserviceResult.getschool();

       /* ageCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                Toast.makeText(this, "You are 13 or older", Toast.LENGTH_SHORT).show();
            } else {
//                Toast.makeText(this, "You must be 13 or older", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    public void ageDialog(Activity act, String text) {
        AlertDialog dialog = new AlertDialog.Builder(act)
                .setTitle("Alert")
                .setMessage(text)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Dismiss the dialog explicitly
                        dialog.dismiss();
                    }
                })
                .show();
    }

    @OnClick(R.id.btn_done_reg)
    public void done() {
        if (validselect == 0) {
            dialogs.showToast(RegisterationActivity.this, "Please Select School first");
        } else if (edit_regcode.getText().toString().trim().isEmpty()) {
            dialogs.showToast(RegisterationActivity.this, "Please Enter Registration code");
        } else if(!ageCheckBox.isChecked()){
            ageDialog(this,"You must be at least 13 to register");
        }
        else {
            dialogs.progressdialog(this);
            WebserviceResult.checkRegCode(RegisterationActivity.this,arrayid[spinner_enterschool.getSelectedItemPosition()], edit_regcode.getText().toString().trim());
        }

    }

    @OnClick(R.id.img_back)
    public void back() {
        Intent intent = new Intent(RegisterationActivity.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterationActivity.this, LoginActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    @OnClick(R.id.relative_spinner)
    public void getschools() {


    }

    @OnClick(R.id.spTV)
    public void openSp() {
        spinner_enterschool.performClick();
    }

    public void setspinerdata(List<Result> results) {
        String[] array = new String[results.size()];
        arrayid = new String[results.size()];

        for (int i = 0; i < results.size(); i++) {
            array[i] = results.get(i).getSchoolName();
            arrayid[i] = results.get(i).getSchoolId();

        }
        selectSchoolAdapter = new SelectSchoolAdapter(RegisterationActivity.this, R.layout.spinner_schools, selectschools, array);
        spinner_enterschool.setAdapter(selectSchoolAdapter);
        dialogs.removedialog();

        spinner_enterschool.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i==0){
                    return;
                }
            else   {
                    selectschools = adapterView.getItemAtPosition(i).toString();
                    validselect = i;
                    spTV.setText(selectschools);

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        spinner_enterschool.getBackground().setColorFilter(Color.parseColor("#FFFFFF"), PorterDuff.Mode.SRC_ATOP);
    }

    public void updateRegestraion(RegisterationCode registerationCode) {
        dialogs.removedialog();
        Boolean check = registerationCode.getSuccess();
        if (check == true) {
            if (registerationCode.getSuccess()) {
                Intent intent = new Intent(RegisterationActivity.this, ProfileActivity.class);
                intent.putExtra("school_result",registerationCode.getResults());
                startActivity(intent);
                overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
                finish();
            }

        }
        else
        {
            Toast.makeText(this, registerationCode.getResults(), Toast.LENGTH_SHORT).show();
        }

    }


}




