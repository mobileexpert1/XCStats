package com.xcstats.views.Activites;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;

import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.squareup.picasso.Picasso;
import com.xcstats.R;
import com.xcstats.controller.SharedPref;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.MultipartUtility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Calendar;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UploadImage extends AppCompatActivity {

    private static UploadImage instance;
    @BindView(R.id.img_back)
    ImageView img_back;

    @BindView(R.id.add_images)
    ImageView add_images;

    @BindView(R.id.profileUP)
    ImageView profileUP;

    @BindView(R.id.uploadIV)
    FrameLayout uploadIV;



    private String picturePath = "";
    private File file;
    private File dir;
    private float imgRatio;
    private float maxRatio;
    private static final int REQUEST_PERMISSIONS = 2;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_CODE_PERMISSION = 1;

    public static UploadImage getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        ButterKnife.bind(this);
        instance = this;
        createDirIfNotExists("Xcstats");
        if (SharedPref.getString(this, "image11").contains("http"))
            Picasso.with(this).load(SharedPref.getString(this, "image11")).into(profileUP);
        checkPermissions();

    }
//    private static void checkPermissions() {
//        if (ContextCompat.checkSelfPermission(PhotoActivity.getinstance(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(PhotoActivity.getinstance(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(PhotoActivity.getinstance(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(PhotoActivity.getinstance(),
//                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
//                    REQUEST_PERMISSIONS);
//        } else {
////            PhotoActivity.getinstance().dispatchTakePictureIntent();
//        }
//    }

    private static void checkPermissions() {
//        if (ContextCompat.checkSelfPermission(PhotoActivity.getinstance(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(PhotoActivity.getinstance(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
//                ContextCompat.checkSelfPermission(PhotoActivity.getinstance(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(PhotoActivity.getinstance(),
//                    new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
//                    REQUEST_PERMISSIONS);
//        } else {
////            PhotoActivity.getinstance().dispatchTakePictureIntent();
//        }
        Context context = PhotoActivity.getinstance();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // For Android 13 and above
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions((Activity) PhotoActivity.getinstance(),
                        new String[]{Manifest.permission.READ_MEDIA_IMAGES, Manifest.permission.CAMERA},
                        REQUEST_PERMISSIONS);
            } else {
//                // Permissions already granted, open the camera
//                PhotoActivity.getinstance().dispatchTakePictureIntent();
            }
        } else {
            // For Android 12 and below
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(PhotoActivity.getinstance(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(PhotoActivity.getinstance(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions((Activity) PhotoActivity.getinstance(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        REQUEST_PERMISSIONS);
            } else {
//                // Permissions already granted, open the camera
//                PhotoActivity.getinstance().dispatchTakePictureIntent();
            }
        }

    }



    @OnClick(R.id.img_back)
    public void backToLogin() {
        Intent intent = new Intent(UploadImage.this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    public boolean createDirIfNotExists(String path) {
        boolean ret = true;

        dir = new File(Environment.getExternalStorageDirectory(), path);
        if (!dir.exists()) {
          dir.mkdirs();
                Log.e("TravellerLog :: ", "Problem creating Image folder");
                ret = false;

        }
        return ret;
    }

    @OnClick(R.id.add_images)
    public void uploadImage() {
        dialogs.profiledialog(UploadImage.this, 2);

    }

    @OnClick(R.id.btn_upload)
    public void upload() {

        new mytask().execute();
        Intent intent=new Intent(UploadImage.this,LoginActivity.class);
        startActivity(intent);

        /*if (picturePath.equals("")){
            dialogs.showToast(UploadImage.this,"Please Select an image to upload");
        }
        else {
            new mytask().execute();
        }*/
    }
    public class mytask extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogs.progressdialog(UploadImage.this);
        }

        @Override
        protected String doInBackground(String... strings) {
            if (!picturePath.equals("")) {
                trustAllHosts();

                MultipartUtility multipart = null;
                try {
                    multipart = new MultipartUtility("https://xcstats.com/mobileAPI/index.php/user/uploadImage", "UTF-8");
                    multipart.addFormField("runner_id", "" + SharedPref.getString(UploadImage.this, "runnerid"));
                    multipart.addFilePart("upload_file", new File(picturePath));
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                List<String> response = null;
                try {
                    response = multipart.finish();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                Log.d(">>>", "SERVER REPLIED");
                if (response != null) {
                    for (String line : response) {
                        Log.d(">>>", "Upload Files Response:::" + line);
                        String responseString = line;
                        JSONObject object;
                        try {
                            object = new JSONObject(responseString);
                            String image11 = object.getString("results");
                            SharedPref.setString(getApplicationContext(), "image11", image11);
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
            return "";
        }

        @Override
        protected void onPostExecute(String s) {
            dialogs.removedialog();
            dialogs.showToast(UploadImage.this, "Image uploaded Sucessfully");
            Intent intent = new Intent(UploadImage.this,LoginActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
            finish();
            super.onPostExecute(s);
        }
    }


    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[] {};
            }

            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        } };

        // Install the all-trusting trust manager
        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection
                    .setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        dialogs.dismissdialog();

        if (requestCode == 1 && resultCode == RESULT_OK) {
            picturePath = Uri.fromFile(file).getPath();
            compressImage(Uri.parse(picturePath), true);

            //Picasso.with(this).load(file.getAbsolutePath().toString()).into(profileUP);
            profileUP.setImageBitmap(getResizedBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()), 100, 100));
        } else if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            try (Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null)) {
                if (cursor.getCount() != 0) {
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    picturePath = cursor.getString(columnIndex);
                    cursor.close();
                }
            }
            compressImage(Uri.parse(picturePath), true);
            profileUP.setImageBitmap(getResizedBitmap(BitmapFactory.decodeFile(picturePath), 100, 100));

        }
    }
    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }
    public void dispatchTakePictureIntent() {

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(dir, "Profile_" + Calendar.getInstance().getTimeInMillis() + ".jpg");
        Uri fileUri = Uri.fromFile(file);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        startActivityForResult(takePictureIntent, 1);

    }


    public void gallerypick() {
        Intent takePictureIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(takePictureIntent, 2);

    }

    public String compressImage(Uri imageUri , boolean b) {
        String filePath;
        if (b) {
            filePath = picturePath;
        }
        else {
            filePath = file.getAbsolutePath();
        }
        Bitmap scaledBitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);
        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;
        float maxHeight = 2400.0f;
        float maxWidth =  2048.0f;
        try {
            imgRatio = actualWidth / actualHeight;
            maxRatio = maxWidth / maxHeight;
        }
        catch (ArithmeticException e) {

        }
        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            }
            else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            }
            else {
                actualHeight = (int) maxHeight;
                actualWidth = (int) maxWidth;
            }
        }
        options.inSampleSize = calculateInSampleSize(options, actualWidth, actualHeight);
        options.inJustDecodeBounds = false;
        options.inPurgeable = true;
        options.inInputShareable = true;
        options.inTempStorage = new byte[16 * 1024];
        try {
            bmp = BitmapFactory.decodeFile(filePath, options);
        }
        catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        }
        catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }
        float ratioX = actualWidth / (float) options.outWidth;
        float ratioY = actualHeight / (float) options.outHeight;
        float middleX = actualWidth / 2.0f;
        float middleY = actualHeight / 2.0f;
        Matrix scaleMatrix = new Matrix();
        scaleMatrix.setScale(ratioX, ratioY, middleX, middleY);
        Canvas canvas = new Canvas(scaledBitmap);
        canvas.setMatrix(scaleMatrix);
        canvas.drawBitmap(bmp, middleX - bmp.getWidth() / 2, middleY - bmp.getHeight() / 2, new Paint(Paint.FILTER_BITMAP_FLAG));
        ExifInterface exif;
        try {
            exif = new ExifInterface(filePath);
            int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
            Log.d("EXIF", "Exif: " + orientation);
            Matrix matrix = new Matrix();
            if (orientation == 6) {
                matrix.postRotate(90);
                Log.d("EXIF", "Exif: " + orientation);
            }
            else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            }
            else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream out = null;
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File ff = new File(filePath);
                if (ff.exists()) {
                }
                else {
                }
                if (isExternalStorageWritable()) {
                    Log.d("###", "Not writable");
                }

                out = new FileOutputStream(picturePath);
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
            }
            else {
                out = new FileOutputStream(picturePath);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();

            File directory = new File(Environment.getDataDirectory() + "/xcstat/");

            if (!directory.exists()) {
                directory.mkdirs();
            }
        }
        return picturePath;
    }


    public int calculateInSampleSize(BitmapFactory.Options options,int reqWidth,int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        final float totalPixels = width * height;
        final float totalReqPixelsCap = reqWidth * reqHeight * 2;
        while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
            inSampleSize++;
        }

        return inSampleSize;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }




}
