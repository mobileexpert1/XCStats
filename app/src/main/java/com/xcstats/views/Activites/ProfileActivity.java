package com.xcstats.views.Activites;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;
import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.cellProviders.CellProviders;
import com.xcstats.model.cellProviders.Result;
import com.xcstats.model.createaccount.CreateAccount;
import com.xcstats.views.Adapters.CellProviderAdapter;
import com.xcstats.views.Adapters.GraduationAdapter;
import com.xcstats.views.Adapters.Spinner_Adapter;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Button_simple;
import com.xcstats.views.custom.Edittext_light;
import com.xcstats.views.custom.NDSpinner;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ProfileActivity extends AppCompatActivity {

    public static final int REQUEST_IMAGE_CAPTURE = 1;
    public static int RESULT_LOAD_IMAGE = 2;
    String[] gender = {"Boy", "Girl"};
    private static String selected = "";
    private static String selectedProvider = "";
    private static String gradyear = "";
    private int year = 0;

    @BindView(R.id.btn_next)
    Button_simple btn_next;

    @BindView(R.id.back_profile)
    ImageView back_profile;

    @BindView(R.id.edit_username)
    Edittext_light edit_username;

    @BindView(R.id.edit_lastname)
    Edittext_light edit_lastname;

    @BindView(R.id.emial_address)
    Edittext_light emial_address;

    @BindView(R.id.phone_number)
    Edittext_light phone_number;

    @BindView(R.id.graduation_year)
    RelativeLayout graduation_year;

    @BindView(R.id.cell_provider)
    RelativeLayout cell_provider;

    @BindView(R.id.edit_password)
    Edittext_light edit_password;

    @BindView(R.id.spinner_gradyear)
    NDSpinner spinner_gradyear;

    @BindView(R.id.spinner_cellProvider)
    NDSpinner spinner_cellProvider;

    @BindView(R.id.txtGend)
    TextView txtGend;

    @BindView(R.id.txtGRad)
    TextView txtGRad;

    Spinner spinner;
    Spinner cellSpinner;
    Spinner_Adapter spinner_adapter;
    CellProviderAdapter cellProviderAdapter;
    GraduationAdapter GraduationAdapter;
    TextView txt_gender;
    TextView txtCellProvider;
    RelativeLayout select_gender;
    boolean fristProvider=true;
    private static ProfileActivity instance;
    public int selectgender;
    private ArrayAdapter<String> arrayAdapter;
    private String selectgendertxt = "";
    private String Gradtxt = "";
    private int selectGrad;
    private boolean firstTime;
    private boolean cellfirstTime;
    String cell_provider_id="";
    private String token;

    public static ProfileActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        WebserviceResult.getCellProviders(this);
        spinner = (Spinner) findViewById(R.id.simpleSpinner);
        cellSpinner = (Spinner) findViewById(R.id.spinner_cellProvider);
        txt_gender = (TextView) findViewById(R.id.txt_gender);
        txtCellProvider = (TextView) findViewById(R.id.txtCellProvider);
        select_gender = (RelativeLayout) findViewById(R.id.select_gender);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPF);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        ButterKnife.bind(this);

//        FirebaseInstanceId.getInstance().getInstanceId()
//                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
//                        if (!task.isSuccessful()) {
//                            return;
//                        }
//
//                        token = task.getResult().getToken();
////                        String msg = getString(R.string.fcm_token, token);
////                        Log.d("token", msg);
//
//                    }
//                });


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

        setdata();
        setYears();
        instance = this;
    }

    List<Result> cell=new ArrayList<>();

    public void onCellProvidersSuccess(CellProviders cellProviders){
        if(cellProviders!=null){
            final List<Result> list=cellProviders.getResults();
            String[] cells=new String[list.size()-1];
            for(int i=0,j=0;i<list.size();i++){
                if(!list.get(i).getName().equals("Cell Provider")) {
                    cell.add(list.get(i));
                    cells[j] = list.get(i).getName();
                    j++;
                }
            }

            cellProviderAdapter = new CellProviderAdapter(ProfileActivity.this, R.layout.cellprovider, selectgendertxt, cells);
            cellSpinner.setAdapter(cellProviderAdapter);
            txtCellProvider.setText(list.get(0).getName());
            cellSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    selectedProvider = adapterView.getItemAtPosition(i).toString();
                    if (!cellfirstTime) {
                        cellfirstTime = true;
                        cellSpinner.setSelection(-1);
                        return;
                    }
                    fristProvider=false;
//                    cell_provider_id=cell.get(i).getId();
                    cell_provider_id="0";
                    txtCellProvider.setText(selectedProvider);
                }

                @Override
                public void onNothingSelected(AdapterView<?> adapterView) {

                }
            });

        }else{
            Toast.makeText(this,"Something went wrong!",Toast.LENGTH_SHORT).show();
        }
    }

    @OnClick(R.id.cell_provider)
    public void cellProviders(){
        cellSpinner.performClick();
    }



    static boolean isNumber(String s)
    {
        for (int i = 0; i < s.length(); i++)
            if (Character.isDigit(s.charAt(i)) == false)
                return false;

        return true;
    }

    @OnClick(R.id.graduation_year)
    public void genderlist() {
        spinner_gradyear.performClick();

    }


    @OnClick(R.id.select_gender)
    public void gradList() {
        spinner.performClick();
    }


    @OnClick(R.id.btn_next)
    public void next() {


        setRegisteration();
    }


    public void setdata() {

        spinner_adapter = new Spinner_Adapter(ProfileActivity.this, R.layout.spinner_schools, selectgendertxt, gender);
        spinner.setAdapter(spinner_adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selected = adapterView.getItemAtPosition(i).toString();
                txtGend.setText(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private boolean checkState() {
        String phone = phone_number.getText().toString().trim();
        boolean status = isNumber(phone);
        boolean state = false;
        if (!phone.isEmpty()) {
            if (phone.length() != 10) {
                Toast.makeText(ProfileActivity.this, "Phone number must be exactly 10 numbers.", Toast.LENGTH_SHORT).show();
                state = true;
            } else if (!status) {
                Toast.makeText(ProfileActivity.this, "Phone number must be exactly 10 numbers.", Toast.LENGTH_SHORT).show();
                state = true;
            } else if (Integer.parseInt("" + (phone.charAt(0))) == 0 || Integer.parseInt("" + (phone.charAt(0))) == 1) {
                Toast.makeText(ProfileActivity.this, "Please enter a valid cell number", Toast.LENGTH_SHORT).show();
                state = true;
//            } else if (cell_provider_id.isEmpty()) {
//                    state = true;
//                    Toast.makeText(ProfileActivity.this, "Please select a cell provider", Toast.LENGTH_SHORT).show();
            }
            else {
                state = false;
            }
        }

        return state;
    }

    public void setRegisteration() {
        String firstname = edit_username.getText().toString().trim();
        String lastname = edit_lastname.getText().toString().trim();
        String emialaddress = emial_address.getText().toString().trim();
        String password = edit_password.getText().toString().trim();
        String cell_provider=txtCellProvider.getText().toString().trim();
        String phone=phone_number.getText().toString().trim();
        boolean status=isNumber(phone);



        if (firstname.isEmpty()) {
            dialogs.showToast(this, "Please Enter Your first name");
        } else if (lastname.isEmpty()) {
            dialogs.showToast(this, "Please Enter Your last name");
        } else if (emialaddress.isEmpty()) {
            dialogs.showToast(this, "Please Enter Your Email Address");
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emialaddress).matches()) {
            dialogs.showToast(this, "Please Enter your valid Email Address");
        }else if(checkState()) {
//
        }
        else if (txtGRad.getText().toString().isEmpty()) {
            dialogs.showToast(ProfileActivity.this, "Please Enter Your Graduation Year");

        } else if (txtGend.getText().toString().isEmpty()) {
            dialogs.showToast(ProfileActivity.this, "Please Enter Your Gender");

        } else if (password.isEmpty()) {
            dialogs.showToast(this, "Please Enter Your Password");

        } else if (password.length() < 6) {
            dialogs.showToast(ProfileActivity.this, "Password atleast 6 characters");
        }
        else
        {
            dialogs.progressdialog(ProfileActivity.this);
            if(phone.isEmpty()){
                cell_provider_id="0";
            }

            WebserviceResult.register(firstname,lastname, emialaddress,phone,cell_provider_id, gradyear, selected.substring(0,1).toLowerCase(), getIntent().getStringExtra("school_result"), password,token);

        }
    }


    public void setYears() {
        int year = 0;
        ArrayList<String> years = new ArrayList<String>();
        year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH);
        if ((month + 1) >= 10) {
            year = year + 1;
        }
        for (int i = 0; i <= 4; i++) {
            years.add("" + (year));
            year = year + 1;
        }


        GraduationAdapter = new GraduationAdapter(ProfileActivity.this, R.layout.spinner_row, Gradtxt, years);
        spinner_gradyear.setAdapter(GraduationAdapter);

        spinner_gradyear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String item = adapterView.getItemAtPosition(i).toString();
                if (!firstTime) {
                    firstTime = true;
                    spinner.setSelection(-1);
                    return;

                }
                gradyear = adapterView.getItemAtPosition(i).toString();
                txtGRad.setText(gradyear);
                System.out.println(graduation_year);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    @OnClick(R.id.back_profile)
    public void backregestration() {
        Intent intent = new Intent(ProfileActivity.this, RegisterationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }


    public void updateProfile(CreateAccount createAccount) {
        dialogs.removedialog();
        Boolean check = createAccount.getSuccess();
        if (check == true) {
            if (createAccount.getSuccess()) {
                createAccount.getResults().getRunnerId();
                SharedPref.setString(this, "runnerid", createAccount.getResults().getRunnerId() + "");
                AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
                builder.setTitle("Success");
                builder.setMessage(createAccount.getResults().getMessage());
                builder.setCancelable(false);
                builder.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(ProfileActivity.this, UploadImage.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
                        finish();
                    }
                });
                builder.show();
            }
        } else {
            dialogs.showToast(this, createAccount.getResults().getMessage());
        }

    }


}





