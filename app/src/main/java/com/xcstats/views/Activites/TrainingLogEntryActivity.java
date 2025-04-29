package com.xcstats.views.Activites;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.xcstats.R;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.addTrainingLog.AddLogResult;
import com.xcstats.model.multipleImage.SelectedImageAdapter;
import com.xcstats.model.trainingdelete.TrainingDeleteResult;
import com.xcstats.model.traininglogcreate.TrainingLogCreate;
import com.xcstats.model.traininglogworkout.Result;
import com.xcstats.model.trainingsubmit.TrainingSubmitResult;
import com.xcstats.model.uploadimage.ImageModel;
import com.xcstats.views.Adapters.WorkoutAdapter;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.Textview_Light;
import com.xcstats.views.multiImagePicker.MultipleImages;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;

public class TrainingLogEntryActivity extends AppCompatActivity {

    private static TrainingLogEntryActivity instance;

    int[] array = {1, 2, 3, 4, 5};

    String[] arry = {"Min", "Easy", "Moderate", "Hard", "Max"};

    String[] efforts = {"Terrible", "Poor", "Average", "Good", "Great"};

    int[] allColors = {R.color.colorblack, R.color.colorblac, R.color.colorPrimary1, R.color.colorPrimaryDark2, R.color.colorAccent3};

    int p = 2;
    private static String selectlogs = "Select predefined workout or..";

    String imagesList;
    ArrayList<String> selectedImageList = new ArrayList<>();
    @BindView(R.id.select_spinner_workout)
    Spinner select_spinner_workout;

    @BindView(R.id.textView_min)
    Textview_Light textView_min;

    @BindView(R.id.up_arrow)
    ImageView up_arrow;

    @BindView(R.id.workoutTV)
    TextView workoutTV;

    @BindView(R.id.down_arrow)
    ImageView down_arrow;

    @BindView(R.id.txt_count)
    Textview_Light txt_count;

    @BindView(R.id.feel_txt)
    Textview_Light feel_txt;

    @BindView(R.id.txtLink)
    Textview_Light txtLink;

    @BindView(R.id.up_feel)
    ImageView up_feel;

    @BindView(R.id.down_feel)
    ImageView down_feel;

    @BindView(R.id.textView_feel)
    Textview_Light textView_feel;

    @BindView(R.id.image_bckhome)
    ImageView image_bckhome;

    @BindView(R.id.edit_distance)
    EditText edit_distance;

   /* @BindView(R.id.relative_selectmeets)
    RelativeLayout relative_selectmeets;*/

    @BindView(R.id.edit_example)
    EditText edit_example;

    @BindView(R.id.FrameMI)
    RelativeLayout FrameMI;

    @BindView(R.id.frameKM)
    RelativeLayout frameKM;

    @BindView(R.id.txtKM)
    Textview_Light txtKM;

    @BindView(R.id.imageTk)
    ImageView imageTk;

    @BindView(R.id.tickMI)
    ImageView tickMI;

    @BindView(R.id.btn_delete)
    Button btn_delete;

    @BindView(R.id.minutesET)
    EditText minutesET;

    @BindView(R.id.secondET)
    EditText secondET;

    @BindView(R.id.notesET)
    EditText notesET;

    @BindView(R.id.txtMI)
    Textview_Light txtMI;

    @BindView(R.id.righttwoLL)
    LinearLayout righttwoLL;

    @BindView(R.id.ll_cameraView)
    LinearLayout ll_cameraView;

    @BindView(R.id.rvImages)
    RecyclerView rvImages;

    private String[] distance;
    private String[] arrayofid;
    private String setDate;
    private String editid;
    private String value;
    String workouttext;
    private String[] arrayworkout;
    private boolean checkEdit;
    private String value12 = "miles";
    private WorkoutAdapter adapter;
    private SelectedImageAdapter selectedImageAdapter;
    private List<com.xcstats.model.trainglogedit.Result> list = new ArrayList<>();
    private boolean checkFirst;
    private String convertBLah;
    Context context;
    String filePath = "";
    File file;
    MultipartBody.Part body;
    private static final int REQUEST_CODE_PERMISSION = 1;
    private static final int REQUEST_CODE_GALLERY = 2;
    ArrayList<String> imageList;


    public static TrainingLogEntryActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_training_logentry);
        ButterKnife.bind(this);
        txt_count.setText(array[2] + "");
        textView_min.setText(arry[2] + "");
        textView_feel.setBackgroundResource(allColors[2]);
        textView_feel.setText(efforts[2] + "");
        feel_txt.setText(array[2] + "");
        instance = this;
        imageList = new ArrayList<>();


        WebserviceResult.TrainingWorkout(this, SharedPref.getString(this, "schoolid"));
        setDate = getIntent().getStringExtra("id");
        editid = getIntent().getStringExtra("editID");
        checkEdit = getIntent().getBooleanExtra("show_deleteBT", false);
        notesET.setOnTouchListener((v, event) -> {
            if (notesET.hasFocus()) {
                v.getParent().requestDisallowInterceptTouchEvent(true);
                switch (event.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_SCROLL:
                        v.getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                }
            }
            return false;
        });

        ll_cameraView.setOnClickListener(v -> {
            if (!(selectedImageList == null) && !selectedImageList.isEmpty()) {
                Intent intent = new Intent(TrainingLogEntryActivity.this, MultipleImages.class);
                intent.putStringArrayListExtra("images", selectedImageList);
                startActivityForResult(intent, 2);
            } else {
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                        ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_CODE_PERMISSION);
                } else {

                    openGallery();
                }
            }
        });


//        notesET.setOnTouchListener(new View.OnTouchListener() {
//
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                notesET.setHint("");
//                return false;
//            }
//        });

        if (checkEdit == true) {
            btn_delete.setVisibility(View.VISIBLE);
        } else {
            // textView_feel.setBackgroundResource(allColors[0]);
            btn_delete.setVisibility(View.INVISIBLE);
        }
        dialogs.progressdialog(this);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            /*            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {*/
            openGallery();
            /*} else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }*/
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_CODE_GALLERY);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is IntentId

        if (requestCode == REQUEST_CODE_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            if (selectedImageUri != null) {
                // Do something with the selected image URI
//                ImageView imageView = findViewById(R.id.imageView);
//                imageView.setImageURI(selectedImageUri);
                imageList.add(String.valueOf(selectedImageUri));
                Intent intent = new Intent(TrainingLogEntryActivity.this, MultipleImages.class);
                intent.putStringArrayListExtra("images", imageList);
                Log.e("tag", "images..." + imageList.size());
                if (imageList.size() == 4) {
                    Toast.makeText(this, "You can't upload more than 3 images", Toast.LENGTH_SHORT).show();
                } else {
                    setSelectedImageList(imageList);
                }


            }
        }
//
        if (requestCode == 2) {
//            if (data != null) {
//                selectedImageList = data.getStringArrayListExtra("images_list");
//                setSelectedImageList(selectedImageList);
//            }
        }
    }

    class ImageData {
        int type;
        String image;
    }

    public void setSelectedImageList(ArrayList<String> selectedImageList) {
        Log.e("tag", "imagesArry..." + imageList.size());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        rvImages.setLayoutManager(layoutManager);
        selectedImageAdapter = new SelectedImageAdapter(this, selectedImageList, true);
        rvImages.setAdapter(selectedImageAdapter);
    }

    @OnClick(R.id.workoutTV)
    public void openSp() {
        select_spinner_workout.performClick();
    }

    public void setUi(List<com.xcstats.model.trainglogedit.Result> results) {
        Log.d("###eee", "" + results.size());
        if (results.size() > 0) {
            list.clear();

            list.addAll(results);

            workoutTV.setText(results.get(0).getLogName());
            //   workoutTV.setVisibility(View.GONE);
            for (int j = 0; j < arrayworkout.length; j++) {
                if (arrayworkout[j].equals(results.get(0).getLogName())) {
                    select_spinner_workout.setSelection(j);
                    break;
                } else {
                    if (j == arrayworkout.length - 1) {
                        adapter.setDefaultText("");
                        adapter.notifyDataSetChanged();
                        workoutTV.setVisibility(View.VISIBLE);
                        workoutTV.setText(results.get(0).getLogName());
                    }
                }
            }
            edit_example.setText(results.get(0).getLogName());
            minutesET.setText(results.get(0).getMinutes() + "");
            secondET.setText(results.get(0).getSeconds() + "");
            if (!results.get(0).getLinkText().equals("")) {
                txtLink.setVisibility(View.VISIBLE);
                txtLink.setText((results.get(0).getLinkText()));
                txtLink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(TrainingLogEntryActivity.this, CoachNotesActivity.class);
                        intent.putExtra("id", results.get(0).getId());
                        startActivity(intent);
                        overridePendingTransition(R.anim.pull_inright, R.anim.push_outleft);
                    }
                });
            }

            if (results.get(0).getDistanceDisplay().contains(".")) {
                String distance = results.get(0).getDistanceDisplay();
                edit_distance.setText((distance));
            } else {
                edit_distance.setText(results.get(0).getDistanceDisplay() + ".00");
            }
            notesET.setText(results.get(0).getNotes());
            String CurrentString = results.get(0).getImages();
            if (CurrentString != null && !CurrentString.isEmpty()) {
                String[] separated = CurrentString.split(",");
                for (int i = 0; i < separated.length; i++) {
                    selectedImageList.add("https://xcstats.com/log_images/" + separated[i]);
                    setSelectedImageList(selectedImageList);
                }
            }
            notesET.setMovementMethod(new ScrollingMovementMethod());

            txt_count.setText(results.get(0).getEffort());
            feel_txt.setText(results.get(0).getRunnerFeel());
            String s = results.get(0).getRunnerFeel();
            textView_feel.setBackgroundResource(allColors[Integer.parseInt(results.get(0).getRunnerFeel()) - 1]);
            textView_feel.setText(efforts[Integer.parseInt(results.get(0).getRunnerFeel()) - 1]);
            textView_min.setText(arry[Integer.parseInt(results.get(0).getEffort()) - 1] + "");
            if (results.get(0).getDistanceTypeDisplay().equalsIgnoreCase("km")) {
                setUnitKm();
            } else {
                setUnitBackground();
            }
            dialogs.removedialog();
        }
    }


    private void setUnitKm() {
        frameKM.setBackgroundColor(Color.parseColor("#dc701e"));
        txtMI.setBackgroundColor(Color.parseColor("#ffffff"));
        tickMI.setVisibility(View.GONE);
        imageTk.setVisibility(View.VISIBLE);
        Drawable drawable = getResources().getDrawable(R.drawable.cal_date_bg);
        txtMI.setBackground(drawable);
        txtMI.setTextColor(Color.parseColor("#CB671C"));
        txtKM.setTextColor(Color.BLACK);
    }

    private void setUnitBackground() {
        frameKM.setBackgroundColor(Color.parseColor("#ffffff"));
        txtMI.setBackgroundColor(Color.parseColor("#dc701e"));
        txtKM.setVisibility(View.VISIBLE);
        tickMI.setVisibility(View.VISIBLE);
        Drawable drawable = getResources().getDrawable(R.drawable.cal_date_bg);
        frameKM.setBackground(drawable);
        txtKM.setTextColor(Color.parseColor("#CB671C"));
        txtMI.setTextColor(Color.BLACK);
    }

    @OnClick(R.id.btn_cancel)
    public void cancel() {
        gotoTrainingLogActivity();
    }

    @OnClick(R.id.image_bckhome)
    public void backhome() {
        gotoTrainingLogActivity();

    }

    @OnClick(R.id.FrameMI)
    public void selectMi() {
        setUnitBackground();
        value12 = "miles";
    }

    @OnClick(R.id.frameKM)
    public void selectKm() {
        setUnitKm();
        value12 = "km";

    }


    @OnClick(R.id.distanceCAN)
    public void cancelDistance() {
        edit_distance.setText("");

    }

    @OnClick(R.id.cancelWorkout)
    public void cancelWork() {
        edit_example.setText("");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoTrainingLogActivity();
    }

    @OnClick(R.id.up_arrow)
    public void upvalue() {
        try {
            p++;
            txt_count.setText(array[p] + "");
            textView_min.setText(arry[p] + "");
        } catch (IndexOutOfBoundsException e) {

        }
    }

    @OnClick(R.id.down_arrow)
    public void downvalue() {

        try {
            p--;
            txt_count.setText(array[p] + "");
            textView_min.setText(arry[p] + "");
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();

        }
    }

    @OnClick(R.id.up_feel)
    public void upfeelvalue() {

        try {
            p++;
            feel_txt.setText(array[p] + "");
            textView_feel.setBackgroundResource(allColors[p]);
            textView_feel.setText(efforts[p] + "");
        } catch (IndexOutOfBoundsException e) {

        }

    }

    @OnClick(R.id.down_feel)
    public void downfeelvalue() {
        try {
            p--;
            feel_txt.setText(array[p] + "");
            textView_feel.setBackgroundResource(allColors[p]);
            textView_feel.setText(efforts[p] + "");

        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();

        }
    }

    public void createTrainingLog(TrainingLogCreate trainingLogCreate) {
        dialogs.removedialog();

        if (trainingLogCreate.getSuccess() == true) {
            dialogs.showToast(TrainingLogEntryActivity.this, trainingLogCreate.getResults());
        }
        gotoTrainingLogActivity();
    }

    public void addLogs(AddLogResult addlogResult) {
        {

            if (select_spinner_workout.getSelectedItemPosition() > 0) {
                if (edit_example.getText().toString().trim().isEmpty()) {
                    dialogs.showToast(TrainingLogEntryActivity.this, "Please Enter a Workout Name");
                } else if (addlogResult.getResults().getTimeReqd().equals("1") && (minutesET.getText().toString().isEmpty() && secondET.getText().toString().isEmpty())) {
                    dialogs.showToast(TrainingLogEntryActivity.this, "Please Enter Seconds");
                } else if (addlogResult.getResults().getDistanceReqd().equals("1") && edit_distance.getText().toString().isEmpty()) {
                    dialogs.showToast(TrainingLogEntryActivity.this, "Please Enter Distance");
                } else if (addlogResult.getResults().getNotesReqd().equals("1") && notesET.getText().toString().isEmpty()) {
                    dialogs.showToast(this, "Please Enter Notes");
                } else {
                    if (arrayworkout[select_spinner_workout.getSelectedItemPosition()].contains("'")) {
                        value = arrayworkout[select_spinner_workout.getSelectedItemPosition()].replace("'", "\\'");
                    } else {
                        value = arrayworkout[select_spinner_workout.getSelectedItemPosition()].replace("'", "\\'");
                    }
                    dialogs.progressdialog(this);

//                    WebserviceResult.createTraininLog(SharedPref.getString(this, "runnerid"), arrayofid[select_spinner_workout.getSelectedItemPosition()], value, minutesET.getText().toString().trim(), secondET.getText().toString().trim(), edit_distance.getText().toString().trim(), value12, txt_count.getText().toString().trim(), feel_txt.getText().toString().trim(), notesET.getText().toString().trim(), setDate, selectedImageList);
                    WebserviceResult.createTraininLog(TrainingLogEntryActivity.this,SharedPref.getString(this, "runnerid"), arrayofid[select_spinner_workout.getSelectedItemPosition()], value, minutesET.getText().toString().trim(), secondET.getText().toString().trim(), edit_distance.getText().toString().trim(), value12, txt_count.getText().toString().trim(), feel_txt.getText().toString().trim(), notesET.getText().toString().trim(), setDate, imageList, this);
                }
            } else {
                if (edit_example.getText().toString().trim().isEmpty()) {
                    dialogs.showToast(TrainingLogEntryActivity.this, "Please Enter a Workout Name");
                } else if (addlogResult.getResults().getTimeReqd().equals("1") && (minutesET.getText().toString().isEmpty() && secondET.getText().toString().isEmpty())) {
                    dialogs.showToast(TrainingLogEntryActivity.this, "Please Enter Seconds");
                } else if (addlogResult.getResults().getDistanceReqd().equals("1") && edit_distance.getText().toString().isEmpty()) {
                    dialogs.showToast(TrainingLogEntryActivity.this, "Please Enter Distance");
                } else if (addlogResult.getResults().getNotesReqd().equals("1") && notesET.getText().toString().isEmpty()) {
                    dialogs.showToast(this, "Please Enter Notes");
                } else {
                    dialogs.progressdialog(this);
//                    WebserviceResult.createTraininLog(SharedPref.getString(this, "runnerid"), "0", edit_example.getText().toString().trim(), minutesET.getText().toString().trim(), secondET.getText().toString().trim(), edit_distance.getText().toString().trim(), value12, txt_count.getText().toString().trim(), feel_txt.getText().toString().trim(), notesET.getText().toString().trim(), setDate, selectedImageList);
                    WebserviceResult.createTraininLog(TrainingLogEntryActivity.this,SharedPref.getString(this, "runnerid"), "0", edit_example.getText().toString().trim(), minutesET.getText().toString().trim(), secondET.getText().toString().trim(), edit_distance.getText().toString().trim(), value12, txt_count.getText().toString().trim(), feel_txt.getText().toString().trim(), notesET.getText().toString().trim(), setDate, imageList, this);
                }
            }
        }
    }

    @OnClick(R.id.btn_submit)
    public void submitTrainingLog() {
        if (checkEdit) {
            if (edit_example.getText().toString().trim().isEmpty()) {
                dialogs.showToast(TrainingLogEntryActivity.this, "Please Enter a Workout Name");
            } else if (list.get(0).getTimeReqd().equals("1") && (minutesET.getText().toString().isEmpty() && secondET.getText().toString().isEmpty())) {
                dialogs.showToast(TrainingLogEntryActivity.this, "Please Enter Seconds");
            } else if (list.get(0).getDistanceReqd().equals("1") && edit_distance.getText().toString().isEmpty()) {
                dialogs.showToast(TrainingLogEntryActivity.this, "Please Enter Distance");
            } else if (list.get(0).getNotesReqd().equals("1") && notesET.getText().toString().isEmpty()) {
                dialogs.showToast(this, "Please Enter Notes");
            } else {

                if (arrayworkout[select_spinner_workout.getSelectedItemPosition()].contains("'")) {
                    convertBLah = arrayworkout[select_spinner_workout.getSelectedItemPosition()].replace("'", "\\'");
                } else {
                    convertBLah = arrayworkout[select_spinner_workout.getSelectedItemPosition()].replace("'", "\\'");
                }
                boolean bWorkout = false;
                for (int i = 0; i < arrayworkout.length; i++) {
                    if (arrayworkout[i].equals(workoutTV.getText().toString())) {
                        bWorkout = true;
                        break;
                    }
                }
                dialogs.progressdialog(this);
                Log.e("tag", "imagesedittttttt..." + imageList.size());
                WebserviceResult.trainingLogSubmit(TrainingLogEntryActivity.this,getIntent().getStringExtra("editID"), bWorkout ? convertBLah : edit_example.getText().toString().trim(),
                        minutesET.getText().toString().trim(),
                        secondET.getText().toString().trim(),
                        txt_count.getText().toString().trim(),
                        feel_txt.getText().toString().trim(),
                        notesET.getText().toString().trim(),
                        edit_distance.getText().toString().trim(),
                        value12,
                        bWorkout ? convertBLah : edit_example.getText().toString().trim(),
                        imageList, this);
            }
            return;
        } else {
            WebserviceResult.AddTrainingLog(TrainingLogEntryActivity.this
                    ,SharedPref.getString(this, "schoolid"), setDate);

        }
    }

    private void gotoTrainingLogActivity() {
//        Intent intent = new Intent(TrainingLogEntryActivity.this, TrainingLogsActivity.class);
//        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    public void setSpinnerWorkout(List<Result> results) {

        workouttext = "";
        arrayworkout = new String[results.size()];
        arrayofid = new String[results.size()];
        distance = new String[results.size()];

        for (int i = 0; i < results.size(); i++) {

            arrayofid[i] = results.get(i).getWorkoutId();
            arrayworkout[i] = results.get(i).getName();
            distance[i] = results.get(i).getDistanceDisplay();
        }
        adapter = new WorkoutAdapter(TrainingLogEntryActivity.this, R.layout.spinner_row, workouttext, arrayworkout);
        select_spinner_workout.setAdapter(adapter);
        dialogs.removedialog();
        select_spinner_workout.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                             @Override
                                                             public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                                                 Log.d("###size", "" + arrayworkout.length);

                                                                 if (i == 0) {
                                                                     select_spinner_workout.setSelection(-1);
                                                                     return;
                                                                 }
                                                                 selectlogs = adapterView.getItemAtPosition(i).toString();
                                                                 if (!(i == 0))
                                                                     edit_example.setText(selectlogs);


                                                                 if (checkEdit) {
                                                                     if (distance[i] != null) {
                                                                         if (!checkFirst) {
                                                                             checkFirst = true;
                                                                             if (list.size() == 0) {
                                                                                 return;
                                                                             }
                                                                             edit_distance.setText("" + list.get(0).getDistanceDisplay());
                                                                         } else {
                                                                             edit_distance.setText("" + (distance[i]));
                                                                         }
                                                                     }
                                                                 } else {
                                                                     if (distance[i] == null) {
                                                                         edit_distance.setHint("Enter distance");
                                                                     } else
                                                                         edit_distance.setText("" + (distance[i]));
                                                                 }


                                                                 workoutTV.setText(selectlogs);
                                                                 //  workoutTV.setVisibility(View.GONE);

                                                             }

                                                             @Override
                                                             public void onNothingSelected(AdapterView<?> adapterView) {

                                                             }
                                                         }

        );
        if (checkEdit)
            WebserviceResult.editTrainingLog(TrainingLogEntryActivity.this,editid);

    }

    @OnClick(R.id.btn_delete)
    public void deleteLogs() {
        deleteTraingentry();

    }

    public void deleteTraingentry() {

        final AlertDialog.Builder builder = new AlertDialog.Builder(TrainingLogEntryActivity.this);
        builder.setMessage(R.string.delete);
        builder.setNegativeButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialogs.progressdialog(TrainingLogEntryActivity.this);
                WebserviceResult.deleteTrainingLogs(TrainingLogEntryActivity.this,editid);

            }
        });
        builder.setPositiveButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.dismiss();
            }
        });
        builder.show();
    }

    public void deleteUpdates(TrainingDeleteResult trainingDeleteResult) {
        dialogs.removedialog();
        if (trainingDeleteResult.getSuccess() == true) {
            dialogs.showToast(TrainingLogEntryActivity.this, trainingDeleteResult.getResults());
//            Intent intent = new Intent(TrainingLogEntryActivity.this, TrainingLogsActivity.class);
//            startActivity(intent);
            overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
            finish();

        }

    }

    public void trainingLogSubmit(TrainingSubmitResult trainingSubmitResult) {
        dialogs.removedialog();
        if (trainingSubmitResult.getSuccess() == true) {
            dialogs.showToast(TrainingLogEntryActivity.this, trainingSubmitResult.getResults());
            gotoTrainingLogActivity();
        }

    }

    private void selectImage(Context context) {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Choose your profile picture");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo")) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);

                } else if (options[item].equals("Choose from Gallery")) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);

                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }
}
