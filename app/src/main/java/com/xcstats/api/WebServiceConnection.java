package com.xcstats.api;

import android.app.Activity;
import android.content.Context;

import com.xcstats.controller.SharedPref;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class WebServiceConnection {

        public static final String baseurl = "https://xcstats.com/mobileAPI/index.php/";
//    public static final String baseurl = "http://192.168.11.4/sumitrana/2025/gitlab/php/mobileApi/index.php/";
    public static WebServiceHolder holder;

    private static WebServiceConnection instance;

    private WebServiceConnection() {
        // Constructor left empty to allow init method calls explicitly
    }

    public static WebServiceConnection getInstance() {
        if (instance == null) {
            instance = new WebServiceConnection();
        }
        return instance;
    }

    // Initialize with headers
  /*  public void init(Activity act) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)

                .addInterceptor(chain -> {
                    Request newRequest = chain.request().newBuilder()
//                            .addHeader("Authorization", "Bearer " + SharedPref.getString(act, "token"))
                            .addHeader("Authorization", SharedPref.getString(act, "token"))
                            .addHeader("refresh-token", SharedPref.getString(act, "refresh_token"))
                            .build();
                    return chain.proceed(newRequest);
                })
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        holder = retrofit.create(WebServiceHolder.class);
    }*/

    public void init(Activity act) {
        // Ensure baseurl is properly defined

        // Create an HTTP logging interceptor for debugging
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // Build OkHttpClient with necessary configurations
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(chain -> {
                    // Fetch tokens from SharedPref
                    String token = SharedPref.getString(act, "token");
                    String refreshToken = SharedPref.getString(act, "refresh_token");

                    // Ensure tokens are not null or empty
                    if (token == null || token.isEmpty()) {
                        throw new IllegalStateException("Authorization token is missing");
                    }
                    if (refreshToken == null || refreshToken.isEmpty()) {
                        throw new IllegalStateException("Refresh token is missing");
                    }

                    // Add headers to the request
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer " + token) // Include "Bearer " prefix
                            .addHeader("refresh-token", refreshToken)
                            .build();
                    return chain.proceed(newRequest);
                })
                .addInterceptor(interceptor)
                .build();

        // Initialize Retrofit with the base URL and client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Ensure WebServiceHolder interface matches Retrofit service definitions
        holder = retrofit.create(WebServiceHolder.class);
    }


    // Initialize without headers
    public void initwithoutHeader() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        holder = retrofit.create(WebServiceHolder.class);
    }

}

/**
 * Created by Mobile on 12/24/2016.
 */
/*

public class WebServiceConnection {
//    public static final String baseurl = "https://xcstats.com/mobileAPI/index.php/";
    public static final String baseurl = "http://192.168.11.4/sumitrana/2025/gitlab/php/mobileApi/";
 // public static final String baseurl = "http://mobile.xcstats.com/api/index.php/";
    public static WebServiceHolder holder;

    public WebServiceConnection() {
        init();
    }


    public void init() {

        // CONTENT TYPE

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(chain -> {
                    Request newRequest = chain.request().newBuilder()
                            .addHeader("Authorization","Bearer (token)")
                            .addHeader("refresh-token","refreshToken")
                            .build();
                    return chain.proceed(newRequest);
                })
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        holder = retrofit.create(WebServiceHolder.class);
    }

    */
/*public void init() {

        // CONTENT TYPE

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(3, TimeUnit.MINUTES)
                .writeTimeout(3, TimeUnit.MINUTES)
                .readTimeout(3, TimeUnit.MINUTES)
                .addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseurl).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        holder = retrofit.create(WebServiceHolder.class);
    }*//*



    public static WebServiceConnection getInstance() {
        return new WebServiceConnection();


    }
}
*/
