package com.xcstats;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.xcstats.controller.SharedPref;
import com.xcstats.views.Activites.LoginActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class FileUtils {

    public static File getFileFromUri(Context context, Uri uri) throws Exception {
        InputStream inputStream = context.getContentResolver().openInputStream(uri);
        String fileName = UUID.randomUUID().toString() + ".jpg";
        File file = new File(context.getCacheDir(), fileName);
        writeToFile(inputStream, file);
        return file;
    }

    private static void writeToFile(InputStream inputStream, File file) throws Exception {
        try (OutputStream outputStream = new FileOutputStream(file)) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, length);
            }
        }
    }

    public static void logout(Activity activity){
        SharedPref.clear(activity);
        Intent intent = new Intent(activity, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }


}
