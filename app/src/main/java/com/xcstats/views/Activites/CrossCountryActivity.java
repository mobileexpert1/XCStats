package com.xcstats.views.Activites;

import android.content.Intent;
import android.os.Bundle;

import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.crossgoals.CrossResult;
import com.xcstats.views.Adapters.CrossGoal_Adapter;
import com.xcstats.views.Dialogs.dialogs;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CrossCountryActivity extends AppCompatActivity {

    private static CrossCountryActivity instance;
    @BindView(R.id.Recycle_cross)
    RecyclerView Recycle_cross;

    @BindView(R.id.image_crosshome)
    ImageView image_crosshome;

    public static CrossCountryActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cross_country);
        ButterKnife.bind(this);
        instance = this;
        dialogs.progressdialog(this);
        WebserviceResult.crossUpdate(this,SharedPref.getString(this, "schoolid"), SharedPref.getString(this, "runnerid"));


    }

    @OnClick(R.id.image_crosshome)
    public void backHome() {
        Intent intent = new Intent(CrossCountryActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(CrossCountryActivity.this, HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    public void updateCrossResult(CrossResult result) {
        if (result != null) {
            if (result.getResults().size() > 0) {
                CrossGoal_Adapter crossgoal = new CrossGoal_Adapter(instance, result.getResults());
                Recycle_cross.setLayoutManager(new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false));
                Recycle_cross.setAdapter(crossgoal);
                dialogs.removedialog();
            } else {
                dialogs.removedialog();
                dialogs.showToast(CrossCountryActivity.this, "The cross country meet schedule is not available ");
            }
        } else {
            dialogs.removedialog();
            dialogs.showToast(CrossCountryActivity.this, "The cross country meet schedule is not available ");

        }


    }
}
