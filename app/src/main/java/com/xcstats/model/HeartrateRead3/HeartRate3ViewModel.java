package com.xcstats.model.HeartrateRead3;


import androidx.lifecycle.ViewModel;

import com.xcstats.api.WebServiceConnection;
import com.xcstats.model.reviewsubmit.ReviewSubmitResult;
import com.xcstats.views.Activites.ReviewPastGoal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HeartRate3ViewModel extends ViewModel {

    public static void reviewSubmit(String id, String notes_before) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.reviewSubmit(id, notes_before).enqueue(new Callback<ReviewSubmitResult>() {
            @Override
            public void onResponse(Call<ReviewSubmitResult> call, Response<ReviewSubmitResult> response) {
                ReviewPastGoal.getinstance().submitReviews(response.body());


            }

            @Override
            public void onFailure(Call<ReviewSubmitResult> call, Throwable t) {

            }
        });

    }










}
