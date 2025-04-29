package com.xcstats;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.viewtrackschdule.Data;
import com.xcstats.views.Adapters.Indoor_Adapter;
import com.xcstats.views.Adapters.Outdoor_Adapter;
import com.xcstats.views.Dialogs.dialogs;

import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ViewTrackActivity extends AppCompatActivity {

    private static ViewTrackActivity instance;

    List<String> dataList =new ArrayList<>();

    Indoor_Adapter indoor_adapter;
    Outdoor_Adapter outdoor_adapter;

    @BindView(R.id.viewtrack_Imv)
    ImageView viewtrack_Imv;

    @BindView(R.id.indoor_RV)
    RecyclerView indoor_RV;

    @BindView(R.id.outdoor_RV)
    RecyclerView outdoor_RV;

    @BindView(R.id.outdoor_title)
    TextView outdoor_title;
    @BindView(R.id.indoor_title)
    TextView indoor_title;

    @BindView(R.id.viewschdule_Tv)
    TextView viewschdule_Tv;

    @BindView(R.id.alert_TV)
    TextView alert_TV;

    @BindView(R.id.date_tv)
    TextView date_tv;

    @BindView(R.id.data_Ly)
    LinearLayout data_Ly;

    @BindView(R.id.outdoordata_Ly)
    LinearLayout outdoordata_Ly;

    public static ViewTrackActivity getinstance() {
        return instance;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_track);
        instance = this;
        ButterKnife.bind(this);
        addDataToList();
        WebserviceResult.showSchedule(this,SharedPref.getString(this, "schoolid"));
        dialogs.progressdialog(this);

    }

    private void addDataToList() {
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");
        dataList.add("");

    }

    @OnClick(R.id.viewtrack_Imv)
    public void viewtrack_Imv(){
        onBackPressed();

    }


    public void getViewSchedule(List<Data> indoorList, String title_indoor, List<Data> outDoorList, String title_outdoor,Boolean isDataEmpty, Boolean isIndoorVisible, Boolean isOutdoorVisible ) {

        Log.e("call","@@@ indoor data "+indoorList.toString());
        Log.e("call","@@@ outdoor data "+outDoorList.toString());

        if (isDataEmpty){
            indoor_title.setVisibility(View.GONE);
            indoor_RV.setVisibility(View.GONE);
            data_Ly.setVisibility(View.GONE);

            outdoor_title.setVisibility(View.GONE);
            outdoor_RV.setVisibility(View.GONE);
            outdoordata_Ly.setVisibility(View.GONE);

            alert_TV.setVisibility(View.VISIBLE);

        }else {

            if (isIndoorVisible){
                dialogs.removedialog();

                indoor_title.setVisibility(View.VISIBLE);
                indoor_RV.setVisibility(View.VISIBLE);
                data_Ly.setVisibility(View.VISIBLE);
                indoor_title.setText(title_indoor);
                indoorAdapter(indoorList,title_indoor);
            }else {
                indoor_title.setVisibility(View.GONE);
                indoor_RV.setVisibility(View.GONE);
                data_Ly.setVisibility(View.GONE);
            }

            if (isOutdoorVisible){
                outdoor_title.setVisibility(View.VISIBLE);
                outdoor_RV.setVisibility(View.VISIBLE);
                outdoordata_Ly.setVisibility(View.VISIBLE);
                outdoor_title.setText(title_outdoor);
                setOutdoorAdapter(outDoorList,title_outdoor);
            }else {
                outdoor_title.setVisibility(View.GONE);
                outdoor_RV.setVisibility(View.GONE);
                outdoordata_Ly.setVisibility(View.GONE);
            }

        }

    }

    private void setOutdoorAdapter (List<Data> outDoorList, String title_outdoor) {

        outdoor_adapter = new Outdoor_Adapter(outDoorList,title_outdoor,this);
        outdoor_RV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        outdoor_RV.setAdapter(outdoor_adapter);

    }

    private void indoorAdapter (List<Data> indoorList, String title_indoor) {

        indoor_adapter = new Indoor_Adapter(indoorList,title_indoor,this );
        indoor_RV.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        indoor_RV.setAdapter(indoor_adapter);
    }


/*

    @OnClick(R.id.date_tv)
    public void date_tv(){

      */
/*  date_tv.setTextColor(Color.WHITE);*//*

        date_tv.setTextColor(Color.parseColor("#FFFF00"));

    }
*/

    }