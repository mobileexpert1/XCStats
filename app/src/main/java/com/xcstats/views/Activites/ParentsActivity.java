package com.xcstats.views.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.xcstats.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParentsActivity extends AppCompatActivity {

    @BindView(R.id.add_parent)
    ImageView add_parent;

    @BindView(R.id.btn_submit)
    Button btn_submit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.add_parent)
    public void addparent(){


    }

    @OnClick(R.id.btn_submit)
    public void submit(){
        Intent intent=new Intent(ParentsActivity.this,HomeActivity.class);
        startActivity(intent);
    }


}
