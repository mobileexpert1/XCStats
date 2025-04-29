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
import com.xcstats.model.addParent.AddParents;
import com.xcstats.model.deleteparents.DeleteparentsResult;
import com.xcstats.model.getparents.GetParentsResult;
import com.xcstats.model.getparents.Parent;
import com.xcstats.views.Adapters.ParentsLogin_Adapter;
import com.xcstats.views.Adapters.Spinner_Adapter;
import com.xcstats.views.Dialogs.dialogs;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ParentsLogin extends AppCompatActivity {

    private static ParentsLogin instance;

    @BindView(R.id.Image_add)
    ImageView Image_add;

    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.parent_recycle)
    RecyclerView parent_recycle;
    private List<Parent> list;
    private ParentsLogin_Adapter parentadapter;
    private int count;

    Spinner_Adapter spinner_adapter;


    public static ParentsLogin getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parents__login);
        ButterKnife.bind(this);
        instance = this;
        dialogs.progressdialog(this);
        WebserviceResult.getparentresult(this,SharedPref.getString(this, "runnerid"));
    }

    @OnClick(R.id.Image_add)
    public void add() {
        dialogs.showtDialog(this,count);

    }

    @OnClick(R.id.img_back)
    public void back() {
        Intent intent = new Intent(ParentsLogin.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ParentsLogin.this, HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    public void deleteParents(DeleteparentsResult result, Parent data) {
        dialogs.removedialog();
        if (result.getSuccess() == true) {
            dialogs.showToast(this, result.getResults());
            list.remove(data);
            parentadapter.notifyDataSetChanged();
            dialogs.removedialog();
        }
        else
        {
            dialogs.removedialog();
        }
    }

    public void updateservice(GetParentsResult parentresulut, String message) {
        dialogs.removedialog();
        if (parentresulut != null) {
            list = parentresulut.getResults().getParents();
            parentadapter = new ParentsLogin_Adapter(list, ParentsLogin.this);
            parent_recycle.setLayoutManager(new LinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false));
            parent_recycle.setAdapter(parentadapter);
            count= parentresulut.getResults().getCountOfParents();
            dialogs.removedialog();
        }
        else
        {
            dialogs.removedialog();
            count= 0;
            dialogs.showToast(this, "Please Add Your Parents");

        }
    }

    public void showContactDialog(AddParents addParents) {
        dialogs.removedialog();
        if (addParents.getSuccess() == true) {
            WebserviceResult.getparentresult(this,SharedPref.getString(ParentsLogin.this, "runnerid"));
            dialogs.showToast(ParentsLogin.this, addParents.getResults());
        }
    }
}
