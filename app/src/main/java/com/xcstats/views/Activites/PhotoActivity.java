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
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.xcstats.R;
import com.xcstats.api.PermissionStatus;
import com.xcstats.api.WebServiceConnection;
import com.xcstats.api.WebserviceResult;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.getimage.GetImageResult;
import com.xcstats.model.profilephoto.ProfilePhotoProp;
import com.xcstats.views.Dialogs.dialogs;
import com.xcstats.views.custom.MultipartUtility;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Response;

public class PhotoActivity extends AppCompatActivity implements PermissionStatus {

    private static PhotoActivity instance;
    private static File file;

    private static File dir;
    private String permission;


    @BindView(R.id.image_cancel)
    ImageView image_cancel;

    @BindView(R.id.photoPF)
    FrameLayout photoPF;

    PermissionStatus listener;

    @BindView(R.id.profile_image)
    CircleImageView profile_image;

    @BindView(R.id.btn_done_photo)
    Button btn_done_photo;

    private String picturePath = "";
    private String imagepath1;
    private float imgRatio;
    private float maxRatio;
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final int REQUEST_PERMISSIONS = 2;
    private static final int REQUEST_CODE_PERMISSION = 1;

    public static PhotoActivity getinstance() {
        return instance;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_);
        ButterKnife.bind(this);
        //   setPermissionListener(this);
        createDirIfNotExists("XCstats");
        dialogs.progressdialog(this);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        instance = this;
        WebserviceResult.getImage(this,SharedPref.getString(this, "runnerid"));
        checkPermissions();
    }

    @OnClick(R.id.image_cancel)
    public void cancel() {
        Intent intent = new Intent(PhotoActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    private static void checkPermissions() {
        Context context = PhotoActivity.getinstance();
        Log.e("dvdjvsdm", "Problem creating Image folder"+Build.VERSION.SDK_INT);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            // For Android 13 and above
            if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED ||
                    ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(PhotoActivity.getinstance(),
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

                ActivityCompat.requestPermissions(PhotoActivity.getinstance(),
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA},
                        REQUEST_PERMISSIONS);
            } else {
//                // Permissions already granted, open the camera
//                PhotoActivity.getinstance().dispatchTakePictureIntent();
            }
        }

    }


//    private static void checkPermissions() {
//        Context context = PhotoActivity.getinstance();
//
//        // Check if runtime permissions are needed (Android 6.0+)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//            if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ||
//                    ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED ||
//                    ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
//
//                // Request necessary permissions
//                ActivityCompat.requestPermissions((Activity) context,
//                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
//                        REQUEST_PERMISSIONS);
//            } else {
//                // Permissions already granted, open the camera
//                PhotoActivity.getinstance().dispatchTakePictureIntent();
//            }
//        } else {
//            // No need for runtime permissions, directly open the camera
//            PhotoActivity.getinstance().dispatchTakePictureIntent();
//        }
//    }

    public static boolean createDirIfNotExists(String path) {
        boolean ret = true;

        dir = new File(Environment.getExternalStorageDirectory(), path);
        if (!dir.exists()) {
            if (!dir.mkdirs()) {
                Log.e("Failed :: ", "Problem creating Image folder");
                ret = false;
            }
        }
        return ret;
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PhotoActivity.this, HomeActivity.class);
        startActivity(intent);
        super.onBackPressed();
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();

    }

    public void setPermissionListener(PermissionStatus listener) {
        this.listener = listener;
    }

    @OnClick(R.id.img_backHM)
    public void backHM() {

        Intent intent = new Intent(PhotoActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();


    }

    @OnClick(R.id.profile_image)
    public void uploadimage() {
        dialogs.profiledialog(PhotoActivity.this, 1);
    }


//    public void dispatchTakePictureIntent() {
//        Log.d("CameraIntent", "dispatchTakePictureIntent");
//        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//        file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Profile_" + Calendar.getInstance().getTimeInMillis() + ".jpg");
//        Uri fileUri = FileProvider.getUriForFile(this, "com.xcstats.fileprovider", file);
//        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
//
//        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
//            Log.d("CameraIntent", "Camera activity found, starting intent");
//            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
//        } else {
//            Log.e("CameraIntent", "No camera activity found");
//        }
//    }

    public void dispatchTakePictureIntent() {
        Log.d("svshsv","dispatchTakePictureIntent");
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "Profile_" + Calendar.getInstance().getTimeInMillis() + ".jpg");
        Uri fileUri = FileProvider.getUriForFile(this, "com.xcstats.fileprovider", file);
        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }


    public void gallerypick() {
//
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("image/*");
            startActivityForResult(intent, 2);
        }else {
            Intent takePictureIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(takePictureIntent, 2);
        }

    }

    @OnClick(R.id.activity_photo_)
    public void backtosplash() {
        Intent intent = new Intent(PhotoActivity.this, HomeActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.pull_inleft, R.anim.push_outright);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dialogs.dismissdialog();
        Log.e("scjbckjascj", "onActivityResult "+"requestCode"+requestCode +" data"+ data);
        try {

            if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
                if (file.exists()) {
                    picturePath = file.getAbsolutePath();
                    compressImage(Uri.parse(picturePath), true);
                    Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                    if (bitmap != null) {
                        profile_image.setImageBitmap(getResizedBitmap(bitmap, 100, 100));
                    } else {
                        Log.e("DEBUG", "Bitmap is null");
                    }
                } else {
                    Log.e("DEBUG", "File does not exist: " + file.getAbsolutePath());
                }
            }
            else if (requestCode == 2 && resultCode == RESULT_OK && data != null) {
                Uri selectedImage = data.getData();

//              String  picturePath =  getRealPathFromURI(selectedImage);
//
//                if (picturePath != null) {
//                    compressImage(Uri.parse(picturePath), true);
//                    Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
//                    if (bitmap != null) {
//                        profile_image.setImageBitmap(getResizedBitmap(bitmap, 100, 100));
//                    } else {
//                        Log.e("DEBUG", "Bitmap is null");
//                    }
//                } else {
//                    Log.e("DEBUG", "Could not get the picture path");
//                }


                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                try (Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null)) {
                    if (cursor != null && cursor.moveToFirst()) {
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        picturePath = cursor.getString(columnIndex);
                        cursor.close();
                        compressImage(Uri.parse(picturePath), true);
                        Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
                        if (bitmap != null) {
                            profile_image.setImageBitmap(getResizedBitmap(bitmap, 100, 100));
                        } else {
                            Log.e("DEBUG", "Bitmap is null");
                        }
                    } else {
                        Log.e("DEBUG", "Cursor is null or empty");
                    }
                } catch (Exception e) {
                    Log.e("DEBUG", "Error accessing gallery image", e);
                }
            }
        } catch (Exception e) {
            Log.e("scjbckjascj", "ErroREQUEST_IMAGE_CAPTURE", e);
        }
    }


    private String getRealPathFromURI(Uri uri) {
        String filePath = null;
        String[] proj = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, proj, null, null, null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndexOrThrow(proj[0]);
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
        }
        return filePath;
    }


    private Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        if (bm == null) {
            Log.e("DEBUG", "Bitmap is null in getResizedBitmap");
            return null;
        }
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        return Bitmap.createBitmap(bm, 0, 0, width, height, matrix, false);
    }


   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        dialogs.dismissdialog();
        try {
            if (requestCode == 1 && resultCode == RESULT_OK) {
                picturePath = Uri.fromFile(file).getPath();
                compressImage(Uri.parse(picturePath), true);
                profile_image.setImageBitmap(getResizedBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()), 100, 100));
            } else if (requestCode == 2 && resultCode == RESULT_OK && null != data) {
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                try (Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null)) {
                    if (cursor.getCount() != 0) {
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        picturePath = cursor.getString(columnIndex);
                    }
                }
                compressImage(Uri.parse(picturePath), true);
                profile_image.setImageBitmap(getResizedBitmap(BitmapFactory.decodeFile(picturePath), 100, 100));
            }
        } catch (Exception errpr) {
            Log.e("tagg", "errro" + errpr);
        }

    }*/

    private static void trustAllHosts() {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[]{};
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            }
        }};

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

//    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
//        int width = bm.getWidth();
//        int height = bm.getHeight();
//        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;
//
//        Matrix matrix = new Matrix();
//
//        matrix.postScale(scaleWidth, scaleHeight);
//
//        Bitmap resizedBitmap = Bitmap.createBitmap(
//                bm, 0, 0, width, height, matrix, false);
//        bm.recycle();
//        return resizedBitmap;
//    }

    @Override
    public void PermissionStatus(String Permission, int status) {
        if (permission.equals(Manifest.permission.READ_EXTERNAL_STORAGE) && permission.equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            if (status == 1) {
                // new uploadProfile().execute();
            }
        } else {
            //  Toast.makeText(this, "Please Allow Permission", Toast.LENGTH_SHORT).show();
        }

    }


    public class uploadProfile extends AsyncTask<String, String, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialogs.progressdialog(PhotoActivity.this);
        }


        @Override
        protected String doInBackground(String... strings) {
            if (!picturePath.equals("")) {
                // uploadFileToServer(new File(picturePath).getName(),"");

                trustAllHosts();

                MultipartUtility multipart = null;
                try {
                    multipart = new MultipartUtility("https://xcstats.com/mobileAPI/index.php/user/uploadImage", "UTF-8");
                    multipart.addFormField("runner_id", "" + SharedPref.getString(PhotoActivity.this, "runnerid"));
                    multipart.addFilePart("upload_file", new File(picturePath));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                List<String> response = null;
                try {
                    response = multipart.finish();
                } catch (IOException e) {
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
                        } catch (JSONException e) {
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
            dialogs.showToast(PhotoActivity.this, "Image uploaded Sucessfully");
            onBackPressed();
            super.onPostExecute(s);
        }
    }

    public String uploadFileToServer(String filename, String targetUrl) {
        String response = "error";
        HttpURLConnection connection = null;
        DataOutputStream outputStream = null;

        String pathToOurFile = filename;
        String urlServer = targetUrl;
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        int bytesRead, bytesAvailable, bufferSize;
        byte[] buffer;
        int maxBufferSize = 1 * 1024;
        try {
            FileInputStream fileInputStream = new FileInputStream(new File(
                    picturePath));

            URL url = new URL("https://xcstats.com/mobileAPI/index.php/user/uploadImage");
            connection = (HttpURLConnection) url.openConnection();

            // Allow Inputs & Outputs
            connection.setDoInput(true);
            connection.setDoOutput(true);
            connection.setUseCaches(false);
            connection.setConnectTimeout(50000);
            connection.setChunkedStreamingMode(1024);
            // Enable POST method
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Connection", "Keep-Alive");
            connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);

            outputStream = new DataOutputStream(connection.getOutputStream());
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);

            String token = "35348";
            outputStream.writeBytes("Content-Disposition: form-data; name=\"runner_id\"" + lineEnd);
            outputStream.writeBytes("Content-Type: text/plain;charset=UTF-8" + lineEnd);
            outputStream.writeBytes("Content-Length: " + token.length() + lineEnd);
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(token + lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + lineEnd);


            String connstr = null;
            connstr = "Content-Disposition: form-data; name=\"upload_file\";filename=\"" + pathToOurFile + "\"" + lineEnd;

            outputStream.writeBytes(connstr);
            outputStream.writeBytes(lineEnd);

            bytesAvailable = fileInputStream.available();
            bufferSize = Math.min(bytesAvailable, maxBufferSize);
            buffer = new byte[bufferSize];

            // Read file
            bytesRead = fileInputStream.read(buffer, 0, bufferSize);
            System.out.println("Image length " + bytesAvailable + "");
            try {
                while (bytesRead > 0) {
                    try {
                        outputStream.write(buffer, 0, bufferSize);
                    } catch (OutOfMemoryError e) {
                        e.printStackTrace();
                        response = "outofmemoryerror";
                        return response;
                    }
                    bytesAvailable = fileInputStream.available();
                    bufferSize = Math.min(bytesAvailable, maxBufferSize);
                    bytesRead = fileInputStream.read(buffer, 0, bufferSize);
                }
            } catch (Exception e) {
                e.printStackTrace();
                response = "error";
                return response;
            }
            outputStream.writeBytes(lineEnd);
            outputStream.writeBytes(twoHyphens + boundary + twoHyphens
                    + lineEnd);

            // Responses from the server (code and message)
            int serverResponseCode = connection.getResponseCode();
            String serverResponseMessage = connection.getResponseMessage();
            System.out.println("Server Response Code " + " " + serverResponseCode);
            System.out.println("Server Response Message " + serverResponseMessage);

            if (serverResponseCode == 200) {
                response = "true";
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = null;
                List<String> response1 = new ArrayList<String>();
                while ((line = reader.readLine()) != null) {
                    response1.add(line);
                }
                reader.close();

                for (String line11 : response1) {
                    Log.d(">>>", "Upload Files Response:::" + line11);
                    String responseString = line11;
                    JSONObject object;
                    try {
                        object = new JSONObject(responseString);
                        String image11 = object.getString("results");
                        SharedPref.setString(getApplicationContext(), "image11", image11);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                response = "false";
            }

            fileInputStream.close();
            outputStream.flush();

            connection.getInputStream();
            //for android InputStream is = connection.getInputStream();
            java.io.InputStream is = connection.getInputStream();

            int ch;
            StringBuffer b = new StringBuffer();
            while ((ch = is.read()) != -1) {
                b.append((char) ch);
            }

            String responseString = b.toString();
            System.out.println("response string is" + responseString); //Here is the actual output

            outputStream.close();
            outputStream = null;

        } catch (Exception ex) {
            // Exception handling
            response = "error";
            System.out.println("Send file Exception" + ex.getMessage() + "");
            ex.printStackTrace();
        }
        return response;
    }


    public void updateProfilePhoto(String uploadFile, String runnerid) {
        dialogs.progressdialog(PhotoActivity.this);
        MultipartBody.Part part = null;
        if (uploadFile != null) {
            File file = new File(uploadFile);
            Log.d("size##", "" + file.length());
            RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
            part = MultipartBody.Part.createFormData("upload_file", file.getName(), requestBody);
        }
        RequestBody userID = RequestBody.create(MediaType.parse("multipart/form-data"), "" + 35348);
        Call<ProfilePhotoProp> call = WebServiceConnection.holder.updateProfile(part, userID);
        call.enqueue(new retrofit2.Callback<ProfilePhotoProp>() {
            @Override
            public void onResponse(Call<ProfilePhotoProp> call, Response<ProfilePhotoProp> response) {
                if (response.body().getSuccess()) {
                    dialogs.removedialog();
                    SharedPref.setString(getApplicationContext(), "image11", response.body().getResults());
                    dialogs.showToast(PhotoActivity.this, "Image uploaded Sucessfully");
                    onBackPressed();
                } else {
                    dialogs.removedialog();
                    dialogs.showToast(PhotoActivity.this, response.body().getResults());

                }
            }

            @Override
            public void onFailure(Call<ProfilePhotoProp> call, Throwable t) {

            }
        });
    }


    @OnClick(R.id.btn_done_photo)
    public void uploadImage() {

        //   updateProfilePhoto(Uri.parse(picturePath).toString(), SharedPref.getString(PhotoActivity.this, "runnerid"));
        new uploadProfile().execute();


    }


    public void getImage(GetImageResult getImageResult) {
        if (getImageResult.getSuccess() == true) {
            if (getImageResult.getResults() != null) {
                Picasso.with(this).load(getImageResult.getResults()).into(profile_image, new Callback() {
                    @Override
                    public void onSuccess() {
                        dialogs.removedialog();
                    }

                    @Override
                    public void onError() {
                        dialogs.removedialog();
                    }
                });
            } else {
                dialogs.removedialog();
            }
        }

    }


    public void requestpermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.READ_EXTERNAL_STORAGE) || ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 0);
            }
        }
    }


    public boolean checkPermission(String permission) {
        if (Build.VERSION.SDK_INT >= 23) {
            this.permission = permission;
            int result = ContextCompat.checkSelfPermission(this, permission);
            if (result != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) this, new String[]{permission}, 111);
                return false;
            } else {
                return true;
            }
        } else {
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE_PERMISSION) {
            /*            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {*/
            dispatchTakePictureIntent();
            /*} else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
            }*/
        }
    }

    public String compressImage(Uri imageUri, boolean b) {
        String filePath;
        if (b) {
            filePath = picturePath;
        } else {
            filePath = file.getAbsolutePath();
        }
        Bitmap scaledBitmap = null;
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        Bitmap bmp = BitmapFactory.decodeFile(filePath, options);
        int actualHeight = options.outHeight;
        int actualWidth = options.outWidth;
        float maxHeight = 2400.0f;
        float maxWidth = 2048.0f;
        try {
            imgRatio = actualWidth / actualHeight;
            maxRatio = maxWidth / maxHeight;
        } catch (ArithmeticException e) {

        }
        if (actualHeight > maxHeight || actualWidth > maxWidth) {
            if (imgRatio < maxRatio) {
                imgRatio = maxHeight / actualHeight;
                actualWidth = (int) (imgRatio * actualWidth);
                actualHeight = (int) maxHeight;
            } else if (imgRatio > maxRatio) {
                imgRatio = maxWidth / actualWidth;
                actualHeight = (int) (imgRatio * actualHeight);
                actualWidth = (int) maxWidth;
            } else {
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
        } catch (OutOfMemoryError exception) {
            exception.printStackTrace();
        }
        try {
            scaledBitmap = Bitmap.createBitmap(actualWidth, actualHeight, Bitmap.Config.ARGB_8888);
        } catch (OutOfMemoryError exception) {
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
            } else if (orientation == 3) {
                matrix.postRotate(180);
                Log.d("EXIF", "Exif: " + orientation);
            } else if (orientation == 8) {
                matrix.postRotate(270);
                Log.d("EXIF", "Exif: " + orientation);
            }
            scaledBitmap = Bitmap.createBitmap(scaledBitmap, 0, 0, scaledBitmap.getWidth(), scaledBitmap.getHeight(), matrix, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        FileOutputStream out = null;
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                File ff = new File(filePath);
                if (ff.exists()) {
                } else {
                }
                if (isExternalStorageWritable()) {
                    Log.d("###", "Not writable");
                }

                out = new FileOutputStream(picturePath);
                scaledBitmap.compress(Bitmap.CompressFormat.JPEG, 80, out);
            } else {
                out = new FileOutputStream(picturePath);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

            File directory = new File(Environment.getDataDirectory() + "/xcstat/");

            if (!directory.exists()) {
                directory.mkdirs();
            }
        }
        return picturePath;
    }

    public int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
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

