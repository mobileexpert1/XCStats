package com.xcstats.views.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.editprofile.CellProvider;
import com.xcstats.model.editprofile.Result;
import com.xcstats.model.editprofilesubmit.SubmitResult;
import com.xcstats.views.Adapters.CellProviderAdapter;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Button_simple;
import com.xcstats.views.custom.Edittext_light;
import com.xcstats.views.custom.Textview_Simple;
import java.util.ArrayList;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EditProfileActivity extends AppCompatActivity {

    private static EditProfileActivity instance;


    @BindView(R.id.txt_firstname)
    Textview_Simple txt_firstname;

    @BindView(R.id.txt_lastname)
    Textview_Simple txt_lastname;

    @BindView(R.id.image_bckhome)
    ImageView image_bckhome;

    @BindView(R.id.edit_email)
    Edittext_light edit_email;

    @BindView(R.id.Textpassword)
    Edittext_light Textpassword;

    @BindView(R.id.edit_street)
    Edittext_light edit_street;

    @BindView(R.id.edit_city)
    Edittext_light edit_city;

    @BindView(R.id.edit_state)
    Edittext_light edit_state;

    @BindView(R.id.edit_zip)
    Edittext_light edit_zip;

    @BindView(R.id.edit_homephone)
    Edittext_light edit_homephone;

    @BindView(R.id.edit_cellphone)
    Edittext_light edit_cellphone;

    @BindView(R.id.btn_submitEP)
    Button_simple btn_submitEP;

    @BindView(R.id.cell_provider)
    RelativeLayout cell_provider;


    TextView txtCellProvider;
    boolean fristProvider=true;
    CellProviderAdapter cellProviderAdapter;
    Spinner cellSpinner;
    private boolean cellfirstTime;
    private static String selected = "";
    private String selectgendertxt = "";
    String cellProviderId="";

    public static EditProfileActivity getinstce() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__profile);
        ButterKnife.bind(this);
        cellSpinner = (Spinner) findViewById(R.id.spinner_cellProvider);
        txtCellProvider = (TextView) findViewById(R.id.txtCellProvider);
        instance = this;
        dialogs.progressdialog(this);
        WebserviceResult.editprofile(this,SharedPref.getString(this, "runnerid"));
    }


    public void onCellProvidersSuccess(final List<CellProvider> list,String placeHolder){
        /*if(list.get(0).getName().equals("Cell Provider")){
            cell.add(list.get(0).getName());
        }*/


        String[] cells=new String[list.size()];
        for(int j=0;j<list.size();j++){
            cells[j]=list.get(j).getName();
        }

        cellProviderAdapter = new CellProviderAdapter(EditProfileActivity.this, R.layout.cellprovider, selectgendertxt, cells);
        cellSpinner.setAdapter(cellProviderAdapter);

        //txtCellProvider.setText(placeHolder);


        cellSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = adapterView.getItemAtPosition(i).toString();
                if (!cellfirstTime) {
                    cellfirstTime = true;
                    cellSpinner.setSelection(-1);
                    return;
                }
                fristProvider=false;
                if(!selected.trim().equals("")) {
                    txtCellProvider.setText(selected);
                }
//                cellProviderId=list.get(i).getId();
                cellProviderId="0";
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @OnClick(R.id.cell_provider)
    public void cellProviders(){
        cellSpinner.performClick();
    }


    @OnClick(R.id.image_bckhome)
    public void backtohome() {
        Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    private void hideSoftKeyboard() {

        if (getCurrentFocus() != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }


    public void updatedat(List<Result> results) {

        if (results.size() > 0) {

            edit_email.setText(results.get(0).getEmail());
            Textpassword.setText(results.get(0).getPassword());
            edit_street.setText(results.get(0).getStreet());
            edit_city.setText(results.get(0).getCity());
            edit_state.setText(results.get(0).getState());
            edit_zip.setText(results.get(0).getZip());
            edit_homephone.setText(results.get(0).getHomePhone());
            edit_cellphone.setText(results.get(0).getCellPhone());
            txt_firstname.setText(results.get(0).getFirstName());
            txt_lastname.setText(results.get(0).getLastName());

            List<CellProvider> list=results.get(0).getCellProvider();


            if(results.get(0).getCellPhone().isEmpty()){
//                txtCellProvider.setText("Cell Provider");
            }else {
//                txtCellProvider.setText(list.get(0).getName());
                cellProviderId = "0";

//            if(results.get(0).getCellPhone().isEmpty()){
//                txtCellProvider.setText("Cell Provider");
//            }else {
//                txtCellProvider.setText(list.get(0).getName());
//                cellProviderId = list.get(0).getId();
//            }
//            if(!txtCellProvider.getText().toString().trim().equals("Cell Provider")){
//                fristProvider=false;
            }

            List<CellProvider> cell=new ArrayList<>();
            cell.clear();

            if(results.get(0).getCellPhone().isEmpty()){
            for(int i=0;i<list.size();i++){
                if(!list.get(i).getName().equals("Cell Provider")){
                    cell.add(list.get(i));
                }
            }
            }else{
                for(int i=1;i<list.size();i++){
                    if(!list.get(i).getName().equals("Cell Provider")){
                        cell.add(list.get(i));
                    }
                }
            }

            onCellProvidersSuccess(cell,list.get(0).getName());
            dialogs.removedialog();
        }

        SharedPref.setString(this, "password", Textpassword.getText().toString());
        SharedPref.setString(this, "street", edit_street.getText().toString());
        SharedPref.setString(this, "city", edit_city.getText().toString());
        SharedPref.setString(this, "state", edit_state.getText().toString());
        SharedPref.setString(this, "zip", edit_zip.getText().toString());
        SharedPref.setString(this, "home_phone", edit_homephone.getText().toString());
        SharedPref.setString(this, "cell_phone", edit_cellphone.getText().toString());

    }

    @OnClick(R.id.btn_submitEP)
    public void submit() {
        String email=edit_email.getText().toString().trim();
        String password =Textpassword.getText().toString().trim();
        String cell_phone =edit_cellphone.getText().toString().trim();
        String home_phone =edit_homephone.getText().toString().trim();

        if (email.isEmpty()){
            dialogs.showToast(EditProfileActivity.this,"Please enter a email id");
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            dialogs.showToast(EditProfileActivity.this,"Please enter a valid email id");
        }else if (password.isEmpty()){
            dialogs.showToast(EditProfileActivity.this,"Please enter a password");
        }else if (password.length()<6){
            dialogs.showToast(EditProfileActivity.this,"Password atleast 6 characters");
        }else if (checkHome(home_phone)) {
            //  Toast.makeText(EditProfileActivity.this, "Phone number must be exactly 10 numbers.", Toast.LENGTH_SHORT).show();
        }else if (checkNumber(cell_phone)) {
            //   Toast.makeText(EditProfileActivity.this, "Phone number must be exactly 10 numbers.", Toast.LENGTH_SHORT).show();
        } else {
            dialogs.progressdialog(this);
            if(edit_cellphone.getText().toString().trim().isEmpty()){
                cellProviderId="0";
            }
            WebserviceResult.sumitProfile(this,SharedPref.getString(this, "runnerid") + "", email,password, edit_street.getText().toString().trim(), edit_city.getText().toString().trim(), edit_state.getText().toString().trim(), edit_zip.getText().toString().trim(), edit_homephone.getText().toString().trim(), edit_cellphone.getText().toString().trim(),cellProviderId.trim());
        }
    }

    private boolean checkNumber(String no){
        // int pno=Integer.parseInt(no.charAt(0)+"");

        if(no.isEmpty()){
            return false;
        }else if(no.length()!=10){
            Toast.makeText(EditProfileActivity.this, "Phone number must be exactly 10 numbers.", Toast.LENGTH_SHORT).show();
            return true;
        }else if(Integer.parseInt(no.charAt(0)+"")==1||Integer.parseInt(no.charAt(0)+"")==0){
            Toast.makeText(EditProfileActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
            return true;
        }/*else if(cellProviderId.isEmpty()){
            Toast.makeText(EditProfileActivity.this, "Please select a cell provider", Toast.LENGTH_SHORT).show();
            return  true;
        }*/else{
            return false;
        }
    }

    private boolean checkHome(String no){
        // int pno=Integer.parseInt(no.charAt(0)+"");

        if(no.isEmpty()){
            return false;
        }else if(no.length()!=10){
            Toast.makeText(EditProfileActivity.this, "Phone number must be exactly 10 numbers.", Toast.LENGTH_SHORT).show();
            return true;
        }else if(Integer.parseInt(no.charAt(0)+"")==1||Integer.parseInt(no.charAt(0)+"")==0){
            Toast.makeText(EditProfileActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
            return true;
        }else{
            return false;
        }
    }


    public void submitProfile(SubmitResult result) {
        if (result.getResults().equals("Account updated.")) {
            WebserviceResult.editprofile(this,SharedPref.getString(this, "runnerid") + "");
            Toast.makeText(getApplicationContext(), result.getResults(), Toast.LENGTH_SHORT).show();
            dialogs.removedialog();
        }
        Intent intent = new Intent(EditProfileActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }
}


