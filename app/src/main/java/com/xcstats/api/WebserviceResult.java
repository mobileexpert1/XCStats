package com.xcstats.api;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.xcstats.FileUtils;
import com.xcstats.R;
import com.xcstats.ViewTrackActivity;
import com.xcstats.controller.SharedPref;
import com.xcstats.model.GetLeader.GetLeaderShapeProp;
import com.xcstats.model.GoalTrack.GoalDetailTrackField;
import com.xcstats.model.GoalTrack.SelectEventData;
import com.xcstats.model.HeartrateRead3.ResponseHeartRate;
import com.xcstats.model.absenceplanned.AbsencePlannedResult;
import com.xcstats.model.addHeartRate.AddHeartRate;
import com.xcstats.model.addParent.AddParents;
import com.xcstats.model.addTrainingLog.AddLogResult;
import com.xcstats.model.addplannedAbsence.AddAbsencesRecord;
import com.xcstats.model.announcementsProp.AnnouncementsProp;
import com.xcstats.model.cellProviders.CellProviders;
import com.xcstats.model.checkRegestration.RegisterationCode;
import com.xcstats.model.coachNotes.CoachNotesResponse;
import com.xcstats.model.createaccount.CreateAccount;
import com.xcstats.model.crossgoaldetails.CrossGoalDetailResult;
import com.xcstats.model.crossgoals.CrossResult;
import com.xcstats.model.crosspacegoals.PaceResult;
import com.xcstats.model.deleteHeartRate.DeleteHeartRate;
import com.xcstats.model.deleteparents.DeleteparentsResult;
import com.xcstats.model.deletetrack.DeleteTrackResult;
import com.xcstats.model.displayButtons.DisplayButtonResult;
import com.xcstats.model.editprofile.EditProfileResult;
import com.xcstats.model.editprofilesubmit.SubmitResult;
import com.xcstats.model.edittrackfield.EditTrackField;
import com.xcstats.model.emailcoach.CoachResult;
import com.xcstats.model.forgotpassword.ForgotResult;
import com.xcstats.model.getimage.GetImageResult;
import com.xcstats.model.getparents.GetParentsResult;
import com.xcstats.model.getparents.Parent;
import com.xcstats.model.getreason.ReasonsResult;
import com.xcstats.model.getschool.SchoolResult;
import com.xcstats.model.goaldetailedit.GoalDetailEdit;
import com.xcstats.model.goalmeet.SelectGoalMeet;
import com.xcstats.model.heartRateForm.ShowHeartRateForm;
import com.xcstats.model.leadboard.LeadboardProp;
import com.xcstats.model.login.LoginResponse;
import com.xcstats.model.login.Result;
import com.xcstats.model.pastgoals.Pastgoalresults;
import com.xcstats.model.plannedabsence.Day;
import com.xcstats.model.plannedabsence.DayWiseWorkout;
import com.xcstats.model.plannedabsence.PlannedAbsenseResult;
import com.xcstats.model.plannedabsence.Summary;
import com.xcstats.model.plannedabsence.Summary_;
import com.xcstats.model.plannedabsence.Workoutdata;
import com.xcstats.model.refreshtoken.RefreshResult;
import com.xcstats.model.removeabsence.RemoveAbsence;
import com.xcstats.model.removegoal.GoalRemoveResult;
import com.xcstats.model.reviewsubmit.ReviewSubmitResult;
import com.xcstats.model.sendemailcoach.SendEmail;
import com.xcstats.model.setupdategoal.UpdateGoalResult;
import com.xcstats.model.submitgoaldetail.SubmitGoalResult;
import com.xcstats.model.teamdoc.TeamdocProp;
import com.xcstats.model.trackfield.TrackFieldResult;
import com.xcstats.model.trackpr.TrackPrResult;
import com.xcstats.model.trainglogedit.TrainingEditResult;
import com.xcstats.model.trainingdelete.TrainingDeleteResult;
import com.xcstats.model.traininglogcreate.TrainingLogCreate;
import com.xcstats.model.traininglogworkout.WorkoutResult;
import com.xcstats.model.trainingsubmit.TrainingSubmitResult;
import com.xcstats.model.uploadimage.UploadResult;
import com.xcstats.model.viewtrackschdule.Data;
import com.xcstats.views.Activites.AddPlanAbsenceActivity;
import com.xcstats.views.Activites.CoachAnnouncementsActivity;
import com.xcstats.views.Activites.CoachNotesActivity;
import com.xcstats.views.Activites.CrossCountryActivity;
import com.xcstats.views.Activites.CrossDetailActivity;
import com.xcstats.views.Activites.CurrentPrActivity;
import com.xcstats.views.Activites.EditProfileActivity;
import com.xcstats.views.Activites.EmailCoachActivity;
import com.xcstats.views.Activites.ForgotPassword;
import com.xcstats.views.Activites.GoalDetailActivity;
import com.xcstats.views.Activites.HomeActivity;
import com.xcstats.views.Activites.LeadboardActivity;
import com.xcstats.views.Activites.LoginActivity;
import com.xcstats.views.Activites.ParentsLogin;
import com.xcstats.views.Activites.PhotoActivity;
import com.xcstats.views.Activites.PlannedAbsence;
import com.xcstats.views.Activites.ProfileActivity;
import com.xcstats.views.Activites.RegisterationActivity;
import com.xcstats.views.Activites.ReviewPastGoal;
import com.xcstats.views.Activites.TeamDocumentActivity;
import com.xcstats.views.Activites.TrackFieldActivity;
import com.xcstats.views.Activites.TrainingLogEntryActivity;
import com.xcstats.views.Activites.TrainingLogLeadBoard;
import com.xcstats.views.Activites.TrainingLogsActivity;
import com.xcstats.views.Activites.showHeartrateFormActivity;
import com.xcstats.views.Adapters.EmailCoach_Adapter;
import com.xcstats.views.Dialogs.dialogs;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mobile on 12/24/2016.
 */

public class WebserviceResult {

    public static void register(final String first_name, final String last_name, final String email, final String cell, final String cell_provider, final String grad_year, final String gender, String school_id, final String password, String devicetoken) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.initwithoutHeader();
        webServiceConnection.holder.createaccount(first_name, last_name, email, cell, cell_provider, grad_year, gender, school_id, password, devicetoken).enqueue(new Callback<CreateAccount>() {
            @Override
            public void onResponse(Call<CreateAccount> call, Response<CreateAccount> response) {
                ProfileActivity.getinstance().updateProfile(response.body());
                Log.d("tag", response.toString());
            }

            @Override
            public void onFailure(Call<CreateAccount> call, Throwable t) {

            }
        });

    }

    public static void Login(String email, String password, String devicetoken) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.initwithoutHeader();
        webServiceConnection.holder.login(email, password, devicetoken).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject jsonobject = new JSONObject(response.body().string());
                    if (jsonobject.getBoolean("success")) {
                        JSONArray array = jsonobject.getJSONArray("results");
                        LoginResponse loginResponse = new LoginResponse();
                        List<Result> results = new ArrayList<Result>();
                        for (int i = 0; i < array.length(); i++) {
                            Result result = new Result();
                            result.setRunnerId(array.getJSONObject(0).getString("runner_id"));
                            result.setSchoolId(array.getJSONObject(0).getString("school_id"));
                            result.setEmail(array.getJSONObject(0).getString("email"));
                            result.setRefresh_token(array.getJSONObject(0).getString("refresh_token"));
                            result.setToken(array.getJSONObject(0).getString("token"));
                            results.add(result);
                        }
                        loginResponse.setResults(results);
                        LoginActivity.getinstance().updateRequest(loginResponse, null);
                    } else {
                        String message = jsonobject.getString("results");
                        Log.d("####", message);
                        LoginActivity.getinstance().updateRequest(null, message);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    public static void getschool() {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.initwithoutHeader();
        webServiceConnection.holder.schoolresult().enqueue(new Callback<SchoolResult>() {
            @Override
            public void onResponse(Call<SchoolResult> call, Response<SchoolResult> response) {
                System.out.println("sucess");
                SchoolResult i = response.body();
                ((RegisterationActivity.getinstance())).setspinerdata(response.body().getResults());

            }

            @Override
            public void onFailure(Call<SchoolResult> call, Throwable t) {

            }
        });

    }

    public static void selectcoach(Activity act, String school_id, final boolean b, String coachId, String subjectText) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.coachresultNew(school_id, coachId, subjectText).enqueue(new Callback<CoachResult>() {
            @Override
            public void onResponse(Call<CoachResult> call, Response<CoachResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        if (b) {
                            AddPlanAbsenceActivity.getinstance().addpresence(response.body());
                        } else {
                            EmailCoachActivity.getinstance().updateserverresonse(response.body());
                        }
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }




            }

            @Override
            public void onFailure(Call<CoachResult> call, Throwable t) {

            }
        });

    }


    public static void coachReply(Activity act, String school_id, final boolean b, String coachId, String id) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.coachresultNewReply(school_id, coachId, id).enqueue(new Callback<CoachResult>() {
            @Override
            public void onResponse(Call<CoachResult> call, Response<CoachResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        if (b) {
                            AddPlanAbsenceActivity.getinstance().addpresence(response.body());
                        } else {
                            EmailCoachActivity.getinstance().updateserverresonse(response.body());
                        }
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }



            }

            @Override
            public void onFailure(Call<CoachResult> call, Throwable t) {

            }
        });

    }


    public static void selectcoach(Activity act, String school_id, final boolean b) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.coachresult(school_id).enqueue(new Callback<CoachResult>() {
            @Override
            public void onResponse(Call<CoachResult> call, Response<CoachResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        if (b) {
                            AddPlanAbsenceActivity.getinstance().addpresence(response.body());
                        } else {
                            EmailCoachActivity.getinstance().updateserverresonse(response.body());
                        }
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }





            }

            @Override
            public void onFailure(Call<CoachResult> call, Throwable t) {

            }
        });

    }


    public static void selectcoachNew(String school_id, String coachId, String subjectText) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.coachresultNew(school_id, coachId, subjectText).enqueue(new Callback<CoachResult>() {
            @Override
            public void onResponse(Call<CoachResult> call, Response<CoachResult> response) {
                EmailCoachActivity.getinstance().updateserverresonseNew(response.body());
                CoachResult jsonRespose = response.body();
                List<com.xcstats.model.emailcoach.Result> results = jsonRespose.getResults();
                if (results.size() > 0) {
                    results.remove(0);
                }
                jsonRespose.setResults(results);
            }

            @Override
            public void onFailure(Call<CoachResult> call, Throwable t) {

            }
        });

    }

    public static void getparentresult(Activity act, String runner_id) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.parentresult(runner_id).enqueue(new Callback<GetParentsResult>() {
            @Override
            public void onResponse(Call<GetParentsResult> call, Response<GetParentsResult> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        ParentsLogin.getinstance().updateservice(response.body(), null);
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<GetParentsResult> call, Throwable t) {
                System.out.println("tttttt" + t.getLocalizedMessage());
                ParentsLogin.getinstance().updateservice(null, t.getLocalizedMessage());

            }
        });

    }


    public static void absencehistory(Activity act, String runnerid) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.absenceresults(runnerid).enqueue(new Callback<AbsencePlannedResult>() {
            @Override
            public void onResponse(Call<AbsencePlannedResult> call, Response<AbsencePlannedResult> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        PlannedAbsence.getinstance().updateplannedservice(response.body(), null);
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }



            }

            @Override
            public void onFailure(Call<AbsencePlannedResult> call, Throwable t) {
                PlannedAbsence.getinstance().updateplannedservice(null, t.getLocalizedMessage());

            }
        });

    }

    public static void editprofile(Activity act, String runnerid) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.editprofile(runnerid).enqueue(new Callback<EditProfileResult>() {
            @Override
            public void onResponse(Call<EditProfileResult> call, Response<EditProfileResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        EditProfileActivity.getinstce().updatedat(response.body().getResults());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<EditProfileResult> call, Throwable t) {

            }
        });

    }

    public static void sumitProfile(Activity act, String runnerid, String email, String password, String city, String street, String state, String zip, String phone, String cellphone, String cell_provider_id) {

        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.submitResult(runnerid, email, password, city, street, state, zip, phone, cellphone, cell_provider_id).enqueue(new Callback<SubmitResult>() {
            @Override
            public void onResponse(Call<SubmitResult> call, Response<SubmitResult> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        EditProfileActivity.getinstce().submitProfile(response.body());

                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<SubmitResult> call, Throwable t) {

            }
        });

    }

    public static void TrainingWorkout(Activity act, String schoolid) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.trainingWorkout(schoolid).enqueue(new Callback<WorkoutResult>() {
            @Override
            public void onResponse(Call<WorkoutResult> call, Response<WorkoutResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        TrainingLogEntryActivity.getinstance().setSpinnerWorkout(response.body().getResults());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<WorkoutResult> call, Throwable t) {

            }
        });
    }

    public static void trackGoal(Activity act, String schoolid, String runnerid) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.trackResult(schoolid, runnerid).enqueue(new Callback<TrackFieldResult>() {
            @Override
            public void onResponse(Call<TrackFieldResult> call, Response<TrackFieldResult> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        TrackFieldActivity.getInstance().trackupdate(response.body(), null);
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


                // GoalDetailActivity.getinstance().editGoals(response.body().getResults());

            }

            @Override
            public void onFailure(Call<TrackFieldResult> call, Throwable t) {
                TrackFieldActivity.getInstance().trackupdate(null, t.getLocalizedMessage());


            }
        });

    }

    public static void deleteParents(Activity act, final Parent data) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.deleteParents(data.getId()).enqueue(new Callback<DeleteparentsResult>() {
            @Override
            public void onResponse(Call<DeleteparentsResult> call, Response<DeleteparentsResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        ParentsLogin.getinstance().deleteParents(response.body(), data);
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<DeleteparentsResult> call, Throwable t) {

            }
        });

    }

    public static void trackpr(Activity act, String runnerid, String schoolid) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.trackPr(schoolid, runnerid).enqueue(new Callback<TrackPrResult>() {
            @Override
            public void onResponse(Call<TrackPrResult> call, Response<TrackPrResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        CurrentPrActivity.getinstance().currentPr(response.body(), null);
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<TrackPrResult> call, Throwable t) {
                CurrentPrActivity.getinstance().currentPr(null, t.getLocalizedMessage());

            }
        });

    }

    public static void addEditParent(Activity act, String id, String runnerid, String firstname, String lastname, String email) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.addresult(id, runnerid, firstname, lastname, email).enqueue(new Callback<AddParents>() {
            @Override
            public void onResponse(Call<AddParents> call, Response<AddParents> response) {
                Log.e("tag", "data..." + response.body());

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        ParentsLogin.getinstance().showContactDialog(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }


            @Override
            public void onFailure(Call<AddParents> call, Throwable t) {

            }
        });
    }


    public static void imageupload(String runnerid, File uploadfile) {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Content-Type", "image/*"); // <-- this is the important line

                Request request = requestBuilder.build();
                return null;
            }
        });

        OkHttpClient client = httpClient.build();
        RequestBody runnerid1 = RequestBody.create(MediaType.parse("multipart/form-data"), runnerid);

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), uploadfile);
        MultipartBody.Part part = MultipartBody.Part.createFormData("upload_file", uploadfile.getName(), requestBody);
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://mobile.xcstats.com/api/index.php/").client(client).addConverterFactory(GsonConverterFactory.create()).build();
        WebServiceHolder webserviceholder = retrofit.create(WebServiceHolder.class);
        webserviceholder.uploadfile(runnerid1, part).enqueue(new Callback<UploadResult>() {
            @Override
            public void onResponse(Call<UploadResult> call, Response<UploadResult> response) {
                if ((response.body().getSuccess() == true)) {
                    Log.d("taggg", "upload successful");
                }
            }

            @Override
            public void onFailure(Call<UploadResult> call, Throwable t) {
                Log.d("taggg", t.getLocalizedMessage());
            }
        });
    }


    public static void showPastGoal(Activity act, String runnerid, String schoolid) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.pastGoal(runnerid, schoolid).enqueue(new Callback<Pastgoalresults>() {
            @Override
            public void onResponse(Call<Pastgoalresults> call, Response<Pastgoalresults> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        ReviewPastGoal.getinstance().updateReviewPast(response.body(), null);
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<Pastgoalresults> call, Throwable t) {
                ReviewPastGoal.getinstance().updateReviewPast(null, t.getLocalizedMessage());

            }
        });


    }


    public static void goalMeet(Activity act, String schoolid) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.goalMeet(schoolid).enqueue(new Callback<SelectGoalMeet>() {
            @Override
            public void onResponse(Call<SelectGoalMeet> call, Response<SelectGoalMeet> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        GoalDetailActivity.getinstance().setspinner(response.body().getResults());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<SelectGoalMeet> call, Throwable t) {

            }
        });

    }

    public static void goalDetail(Activity act,String runner_id, String schoolid, String event_type) {

        Retrofit retrofit = new Retrofit.Builder().baseUrl(WebServiceConnection.baseurl).build();
        WebServiceHolder apiSservice;
        apiSservice = retrofit.create(WebServiceHolder.class);
        apiSservice.responsebody(schoolid, runner_id, event_type).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        try {
                            JSONObject object = new JSONObject(response.body().string());
                            JSONObject results = object.getJSONObject("results");
                            Iterator<String> iter = results.keys();
                            GoalDetailTrackField data = new GoalDetailTrackField();
                            ArrayList<SelectEventData> list = new ArrayList<>();

                            while (iter.hasNext()) {
                                String key = iter.next();
                                if (key.equalsIgnoreCase("long")) {
                                    data.setLongi(results.getString("long"));
                                } else if (key.equalsIgnoreCase("short")) {
                                    data.setShrt(results.getString("short"));
                                } else {
                                    SelectEventData cdata = new SelectEventData();
                                    JSONObject obj = results.getJSONObject(key);
                                    cdata.setId(obj.getInt("id"));
                                    cdata.setName(obj.getString("name"));
                                    list.add(cdata);
                                }


                            }
                            data.setList(list);

                            GoalDetailActivity.getinstance().handleResult(data);
                            dialogs.removedialog();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        dialogs.removedialog();
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {


            }
        });

    }

    public static void checkRegCode(Activity act, String schoolid, String regcode) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.initwithoutHeader();
        webServiceConnection.holder.checkRegCode(schoolid, regcode).enqueue(new Callback<RegisterationCode>() {
            @Override
            public void onResponse(Call<RegisterationCode> call, Response<RegisterationCode> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        RegisterationActivity.getinstance().updateRegestraion(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }



            }

            @Override
            public void onFailure(Call<RegisterationCode> call, Throwable t) {


            }
        });
    }

    public static void trackField(Activity act, String id) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.editTrackfield(id).enqueue(new Callback<EditTrackField>() {
            @Override
            public void onResponse(Call<EditTrackField> call, Response<EditTrackField> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        GoalDetailActivity.getinstance().updateTrack(response.body().getResults());
                        System.out.println("sucess" + response.body().toString());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }



            }

            @Override
            public void onFailure(Call<EditTrackField> call, Throwable t) {
                System.out.println(t.getLocalizedMessage());

            }
        });


    }

    public static void forgotPassword( String email) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.initwithoutHeader();
        webServiceConnection.holder.forgot(email).enqueue(new Callback<ForgotResult>() {
            @Override
            public void onResponse(Call<ForgotResult> call, Response<ForgotResult> response) {
                ForgotPassword.getinstance().updateForgotPassword(response.body());
                System.out.println("sucess");
            }

            @Override
            public void onFailure(Call<ForgotResult> call, Throwable t) {
                System.out.println("fail");


            }
        });

    }


    public static void displayFunction(Activity act, String schoolid, String runner_id) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();webServiceConnection.init(act);
        webServiceConnection.holder.displaybuttons(schoolid, runner_id).enqueue(new Callback<DisplayButtonResult>() {
            @Override
            public void onResponse(Call<DisplayButtonResult> call, Response<DisplayButtonResult> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        DisplayButtonResult result = response.body();
                        HomeActivity.getinstance().updateDisplayFunctions(result);

                        int count = result.getResults().get(0).getCount();
                        if (count == 0) {
                            HomeActivity.getinstance().countTV.setVisibility(View.GONE);
                        } else {
                            HomeActivity.getinstance().countTV.setVisibility(View.VISIBLE);
                            HomeActivity.getinstance().countTV.setText(String.valueOf(count));
                            String bgColor = result.getResults().get(0).getBg();
                            if ("#FF0000".equalsIgnoreCase(bgColor)) {
                                HomeActivity.getinstance().countTV.setBackgroundResource(R.drawable.count_circle_red);
                            } else {
                                HomeActivity.getinstance().countTV.setBackgroundResource(R.drawable.count_circle);
                            }
                        }
                        break;

                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;

                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;

                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<DisplayButtonResult> call, Throwable t) {
                Toast.makeText(act, "Network error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    public static void refreshAccess(Activity act) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        dialogs.progressdialog(act);

        webServiceConnection.holder.createRefreshToken().enqueue(new Callback<RefreshResult>() {
            @Override
            public void onResponse(Call<RefreshResult> call, Response<RefreshResult> response) {

                int statusCode = response.code();
                switch (statusCode) {
                    case 200:

                        // Success
//                        SharedPref.clear(act);
//                        Log.e("fgrghtyhthhh", "token: "+response.body().getResults().get(0).getToken()+" refresh_token "+response.body().getResults().get(0).getRefresh_token() );

                        SharedPref.setString(act, "token", "");
                        SharedPref.setString(act, "refresh_token", "");
                        SharedPref.setString(act, "token", response.body().getResults().getToken());
                        SharedPref.setString(act, "refresh_token", response.body().getResults().getRefresh_token());
                        Log.e("fgrghtyhthhh", "token: "+SharedPref.getString(act,"token"));
                        Log.e("fgrghtyhthhh", "refreshtoken: "+SharedPref.getString(act,"refresh_token"));

                        System.out.println("success");
                        dialogs.removedialog();
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
//                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<RefreshResult> call, Throwable t) {
                System.out.println("fail");


            }
        });
    }


    public static void logoutUser(Activity act) {
        new AlertDialog.Builder(act)
                .setTitle("Alert")
                .setMessage("Something went wrong")
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Action on Yes
//                        finish();
                        SharedPref.clear(act); // clear all saved user data
                        Intent intent = new Intent(act, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        act.startActivity(intent);
                    }
                })
                .setNegativeButton("", null) // Dismisses dialog
                .show();

    }


   /* public static void displayFunction(Activity act,String schoolid, String runner_id) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.displaybuttons(schoolid, runner_id).enqueue(new Callback<DisplayButtonResult>() {
            @Override
            public void onResponse(Call<DisplayButtonResult> call, Response<DisplayButtonResult> response) {
                HomeActivity.getinstance().updateDisplayFunctions(response.body());
                if (response.body().getResults().get(0).getCount() == 0) {
                    HomeActivity.getinstance().countTV.setVisibility(View.GONE);

                } else {

                    HomeActivity.getinstance().countTV.setVisibility(View.VISIBLE);
                    HomeActivity.getinstance().countTV.setText(response.body().getResults().get(0).getCount().toString());
                    if (response.body().getResults().get(0).getBg().equalsIgnoreCase("#FF0000")) {
                        HomeActivity.getinstance().countTV.setBackgroundResource(R.drawable.count_circle_red);
                    } else {
                        HomeActivity.getinstance().countTV.setBackgroundResource(R.drawable.count_circle);
                    }
                }
            }

            @Override
            public void onFailure(Call<DisplayButtonResult> call, Throwable t) {

            }
        });

    }*/

    public static void crossUpdate(Activity act,String schoolid, String runnerid) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.crossResult(schoolid, runnerid).enqueue(new Callback<CrossResult>() {
            @Override
            public void onResponse(Call<CrossResult> call, Response<CrossResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        CrossCountryActivity.getinstance().updateCrossResult(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<CrossResult> call, Throwable t) {
                CrossCountryActivity.getinstance().updateCrossResult(null);

            }
        });
    }

    public static void coachAnnouncements(Activity act,String schoolid, String runnerid) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.getCoachesAnnouncements(schoolid, runnerid).enqueue(new Callback<AnnouncementsProp>() {
            @Override
            public void onResponse(Call<AnnouncementsProp> call, Response<AnnouncementsProp> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        CoachAnnouncementsActivity.getinstance().updateCrossResult(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }



            }

            @Override
            public void onFailure(Call<AnnouncementsProp> call, Throwable t) {
                CoachAnnouncementsActivity.getinstance().updateCrossResult(null);

            }
        });
    }


    public static void getReasons(Activity act) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.reasonResult().enqueue(new Callback<ReasonsResult>() {
            @Override
            public void onResponse(Call<ReasonsResult> call, Response<ReasonsResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        AddPlanAbsenceActivity.getinstance().setSpinnerReason(response.body().getResults());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<ReasonsResult> call, Throwable t) {

            }
        });

    }


    public static void crossDetail(Activity act, String schoolid, String runnerid, String eventnum) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.crossDetail(schoolid, runnerid, eventnum).enqueue(new Callback<CrossGoalDetailResult>() {
            @Override
            public void onResponse(Call<CrossGoalDetailResult> call, Response<CrossGoalDetailResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        CrossDetailActivity.getinstance().updateDetails(response.body().getResults());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<CrossGoalDetailResult> call, Throwable t) {

            }
        });

    }

    public static void updateResult(Activity act, String schoolid, String runnerid, String eventnum, String minute, String second, String Notes, String expected) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.updateGoalss(schoolid, runnerid, eventnum, minute, second, Notes, expected).enqueue(new Callback<UpdateGoalResult>() {
            @Override
            public void onResponse(Call<UpdateGoalResult> call, Response<UpdateGoalResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        CrossDetailActivity.getinstance().updateGoals(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<UpdateGoalResult> call, Throwable t) {

            }
        });

    }

    public static void removeResult(Activity act,String schoolid, String runnerid, String eventnum) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.goalRemove(schoolid, runnerid, eventnum).enqueue(new Callback<GoalRemoveResult>() {
            @Override
            public void onResponse(Call<GoalRemoveResult> call, Response<GoalRemoveResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        CrossDetailActivity.getinstance().showAlert(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<GoalRemoveResult> call, Throwable t) {

            }
        });

    }

    public static void sendEmail(Activity act,String schoolid, String runnerid, String Message, String subject, ArrayList<String> coachids) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.initwithoutHeader();
        Map<String, String> map = new HashMap<>();
        map.put("message_text", Message);
        map.put("school_id", schoolid);
        map.put("runner_id", runnerid);
        map.put("subject", subject);

        webServiceConnection.holder.sendEmail(map, EmailCoach_Adapter.ids).enqueue(new Callback<SendEmail>() {
            @Override
            public void onResponse(Call<SendEmail> call, Response<SendEmail> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        EmailCoachActivity.getinstance().send(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }




            }

            @Override
            public void onFailure(Call<SendEmail> call, Throwable t) {
                System.out.println("fail" + t.getLocalizedMessage());

            }
        });

    }

    public static void removeAbsence(Activity act, String id) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.removeAbsence(id).enqueue(new Callback<RemoveAbsence>() {
            @Override
            public void onResponse(Call<RemoveAbsence> call, Response<RemoveAbsence> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        PlannedAbsence.getinstance().deletePlanned(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<RemoveAbsence> call, Throwable t) {

            }
        });

    }


    public static void sumitGoals(Activity act,String schholid, String runnerid, String event_type, String event_id, String meet_id, String notes_before, String min, String sec) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.submitGoal(schholid, runnerid, event_type, event_id, meet_id, notes_before, min, sec).enqueue(new Callback<SubmitGoalResult>() {
            @Override
            public void onResponse(Call<SubmitGoalResult> call, Response<SubmitGoalResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        GoalDetailActivity.getinstance().submitDetail(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<SubmitGoalResult> call, Throwable t) {

            }
        });

    }

    public static void deleteTrack(Activity act,String id) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.deleteTrack(id).enqueue(new Callback<DeleteTrackResult>() {
            @Override
            public void onResponse(Call<DeleteTrackResult> call, Response<DeleteTrackResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        TrackFieldActivity.getInstance().deleteTracks(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }



            }

            @Override
            public void onFailure(Call<DeleteTrackResult> call, Throwable t) {

            }
        });

    }

    public static void reviewSubmit(Activity act,String id, String notes_before) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.reviewSubmit(id, notes_before).enqueue(new Callback<ReviewSubmitResult>() {
            @Override
            public void onResponse(Call<ReviewSubmitResult> call, Response<ReviewSubmitResult> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        ReviewPastGoal.getinstance().submitReviews(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }



            }

            @Override
            public void onFailure(Call<ReviewSubmitResult> call, Throwable t) {

            }
        });

    }


    public static void showSchedule(Activity act, String schoolid) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebServiceConnection.baseurl).addConverterFactory(GsonConverterFactory.create())
                .build();
        WebServiceHolder apiSservice;
        apiSservice = retrofit.create(WebServiceHolder.class);
        apiSservice.showSchedule(schoolid).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        Log.e("@@@@", "" + response.body());
                        try {
                            JSONArray jsonArray = null;
                            Data data = null;
                            String title_indoor = null;
                            String title_outdoor = null;
                            List<Data> indoorList = new ArrayList<Data>();
                            List<Data> outDoorList = new ArrayList<Data>();
                            Boolean isDataEmpty = false;
                            Boolean isIndoorVisible = false;
                            Boolean isOutdoorVisible = false;

                            try {
                                JSONObject object = new JSONObject(response.body().string());
                                Log.e("@@", "JSON Object ::" + object);

                                if (object.getBoolean("success")) {
                                    Log.e("@@", " Object  success::" + object.getBoolean("success"));
                                    jsonArray = object.getJSONArray("results");

                                    if (jsonArray.length() > 0) {


                                        for (int i = 0; i < jsonArray.length(); i++) {
                                            Log.e("@@", "jsonArray.getJSONObject(i)  ::" + jsonArray.getJSONObject(i));

                                            if (jsonArray.getJSONObject(i).getString("season").contains("INDOOR")) {
                                                isIndoorVisible = true;
                                            }

                                            if (jsonArray.getJSONObject(i).getString("season").contains("OUTDOOR")) {
                                                isOutdoorVisible = true;
                                            }

                                            if (isIndoorVisible && isOutdoorVisible) {
                                                Iterator<String> iter = jsonArray.getJSONObject(0).keys();// result keys
                                                List<String> keyList = new ArrayList() {
                                                };
                                                while (iter.hasNext()) {
                                                    String key = iter.next();
                                                    if (!key.equals("season")) {
                                                        keyList.add(key);
                                                    } else {
                                                        title_indoor = jsonArray.getJSONObject(0).getString("season");
                                                    }
                                                }
                                                indoorList.clear();
                                                for (String s : keyList) {
                                                    JSONObject obj = jsonArray.getJSONObject(0).getJSONObject(s);
                                                    data = parseData(obj);
                                                    indoorList.add(data);
                                                }

                                                Iterator<String> iter_1 = jsonArray.getJSONObject(1).keys();// result keys
                                                List<String> keyList_1 = new ArrayList() {
                                                };
                                                while (iter_1.hasNext()) {
                                                    String key = iter_1.next();
                                                    if (!key.equals("season")) {
                                                        keyList_1.add(key);
                                                    } else {
                                                        title_outdoor = jsonArray.getJSONObject(1).getString("season");
                                                    }
                                                }
                                                outDoorList.clear();
                                                for (String s : keyList_1) {
                                                    JSONObject obj = jsonArray.getJSONObject(1).getJSONObject(s);
                                                    data = parseData(obj);
                                                    outDoorList.add(data);
                                                }

                                            } else {
                                                if (isIndoorVisible) {
                                                    Iterator<String> iter = jsonArray.getJSONObject(i).keys();// result keys
                                                    List<String> keyList = new ArrayList() {
                                                    };
                                                    while (iter.hasNext()) {
                                                        String key = iter.next();
                                                        if (!key.equals("season")) {
                                                            keyList.add(key);
                                                        } else {
                                                            title_indoor = jsonArray.getJSONObject(i).getString("season");
                                                        }
                                                    }
                                                    indoorList.clear();
                                                    for (String s : keyList) {
                                                        JSONObject obj = jsonArray.getJSONObject(i).getJSONObject(s);
                                                        data = parseData(obj);
                                                        indoorList.add(data);
                                                    }
                                                }

                                                if (isOutdoorVisible) {
                                                    Iterator<String> iter_1 = jsonArray.getJSONObject(i).keys();// result keys
                                                    List<String> keyList_1 = new ArrayList() {
                                                    };
                                                    while (iter_1.hasNext()) {
                                                        String key = iter_1.next();
                                                        if (!key.equals("season")) {
                                                            keyList_1.add(key);
                                                        } else {
                                                            title_outdoor = jsonArray.getJSONObject(i).getString("season");
                                                        }
                                                    }
                                                    outDoorList.clear();
                                                    for (String s : keyList_1) {
                                                        JSONObject obj = jsonArray.getJSONObject(i).getJSONObject(s);
                                                        data = parseData(obj);
                                                        outDoorList.add(data);
                                                    }
                                                }
                                            }

                                            dialogs.removedialog();
                                            isDataEmpty = false;
                                            ViewTrackActivity.getinstance().getViewSchedule(indoorList, title_indoor, outDoorList, title_outdoor, isDataEmpty, isIndoorVisible, isOutdoorVisible);


                                        }

                                    } else {
                                        isDataEmpty = true;
                                        ViewTrackActivity.getinstance().getViewSchedule(indoorList, title_indoor, outDoorList, title_outdoor, isDataEmpty, isIndoorVisible, isOutdoorVisible);
                                        dialogs.removedialog();

                                    }

                                }

                            } catch (JSONException | IOException e) {
                                e.printStackTrace();
                            }

                        } catch (Exception exception) {
                            exception.printStackTrace();
                        }
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }




            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

                Log.e("@@@@", "" + t.getMessage());

            }

        });
    }


    private static Data parseData(JSONObject obj) throws JSONException {
        Data data = new Data();
        data.setMeet_date(obj.getString("meet_date"));
        data.setName(obj.getString("name"));
        data.setLocation(obj.getString("location"));
        data.setLink(obj.getString("link"));
        return data;

    }

//
//    public static void trackGoalSetting(String school_id) {
//
//        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
//        webServiceConnection.holder.trackGoalSetting(school_id).enqueue(new Callback<ViewTrack>() {
//            @Override
//            public void onResponse(Call<ViewTrack> call, Response<ViewTrack> response) {
//                if (response.body().getSuccess()) {
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ViewTrack> call, Throwable t) {
//                Log.e("@@", "failure::" + t.toString());
//            }
//        });
//    }



/*
    public static void trackGoalSetting(String school_id){

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(WebServiceConnection.baseurl)
                .build();

        WebServiceHolder ViewTrack;
        ViewTrack=retrofit.create(WebServiceHolder.class);
        ViewTrack.trackGoalSetting(school_id).enqueue(new Callback<ViewTrack>() {
            @Override
            public void onResponse(Call<ViewTrack> call, Response<ViewTrack> response) {


                try {
                    JSONObject object=new JSONObject(response.body().toString());
                    Boolean success=object.getBoolean("success");
                    JSONObject results=object.getJSONObject("results");
                    JSONObject object0 =results.getJSONObject("0");
                    List<Dataclass> indoorlist=new ArrayList<>();
//                    ArrayList<String> indoorlist = new ArrayList<>();

                   for (int i=0;true;i++) {

                       Log.e("@@@","Ture"+object0);

                       object = object0.getJSONObject("0" + i);

                       Log.e("@@@","false"+object0);


                       if(object != null){
//                           indoorlist.add("0");

                       }else {

                           break;
                       }

                   }

                }
                catch (Exception e){


                }

            }

            @Override
            public void onFailure(Call<ViewTrack> call, Throwable t) {

            }
        });
    }
*/


    public static void traingLogsRead(Activity act,String runnerid, String weekstartdate) {
        Log.e("@@@@", "traingLogsRead" + runnerid + weekstartdate);
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.init(act);
        webServiceConnection.holder.responseBody(runnerid,weekstartdate).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                int statusCode = response.code();
                switch (statusCode) {
                    case 200:
                        // Success
                        try {
                            JSONObject object = new JSONObject(response.body().string());
                            Boolean success = object.getBoolean("success");
                            JSONObject results = object.getJSONObject("results");
                            JSONObject daily = results.getJSONObject("daily");
                            JSONObject summary = results.getJSONObject("summary");
                            double weekly_distance = summary.getDouble("weekly_distance");
                            String weekly_time = summary.getString("weekly_time");
                            Iterator<String> iter = daily.keys();
                            PlannedAbsenseResult data = new PlannedAbsenseResult();
                            Summary summary1 = new Summary();
                            Summary_ summary2 = new Summary_();
                            summary2.setWeeklyDistance(weekly_distance);
                            summary2.setWeeklyTime(weekly_time);
                            summary1.setSummary(summary2);
                            data.setSummary(summary1);

                            ArrayList<DayWiseWorkout> list = new ArrayList<>();
                            Day day = new Day();
                            while (iter.hasNext()) {
                                String key = iter.next();
                                Day cdata = new Day();
                                JSONObject obj = daily.getJSONObject(key);

                                DayWiseWorkout dayWiseWorkout = new DayWiseWorkout();
                                Iterator<String> iter1 = obj.keys();

                                while (iter1.hasNext()) {
                                    String key1 = iter1.next();
                                    JSONObject obj1 = obj.getJSONObject(key1);

                                    if (key1.equals("1")) {
                                        Workoutdata dayoneData = parseJsondata(obj1);
                                        dayWiseWorkout.setDayone(dayoneData);
                                    } else {
                                        Workoutdata daytwoData = parseJsondata(obj1);
                                        dayWiseWorkout.setDaytwo(daytwoData);
                                    }

                                }
                                list.add(dayWiseWorkout);
                                System.out.println(">>..." + obj.toString());

                            }
                            TrainingLogsActivity.getinstance().handleData(list, data);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable throwable) {

            }
        });

    }



    /* public static void traingLogsRead(String runnerid, String weekstartdate) {
        Log.e("@@@@", "traingLogsRead" + runnerid + weekstartdate);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(WebServiceConnection.baseurl)
                .build();

        WebServiceHolder apiSservice;
        apiSservice = retrofit.create(WebServiceHolder.class);
        apiSservice.responseBody(runnerid, weekstartdate).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    Boolean success = object.getBoolean("success");
                    JSONObject results = object.getJSONObject("results");
                    JSONObject daily = results.getJSONObject("daily");
                    JSONObject summary = results.getJSONObject("summary");
                    double weekly_distance = summary.getDouble("weekly_distance");
                    String weekly_time = summary.getString("weekly_time");
                    Iterator<String> iter = daily.keys();
                    PlannedAbsenseResult data = new PlannedAbsenseResult();
                    Summary summary1 = new Summary();
                    Summary_ summary2 = new Summary_();
                    summary2.setWeeklyDistance(weekly_distance);
                    summary2.setWeeklyTime(weekly_time);
                    summary1.setSummary(summary2);
                    data.setSummary(summary1);

                    ArrayList<DayWiseWorkout> list = new ArrayList<>();
                    Day day = new Day();
                    while (iter.hasNext()) {
                        String key = iter.next();
                        Day cdata = new Day();
                        JSONObject obj = daily.getJSONObject(key);

                        DayWiseWorkout dayWiseWorkout = new DayWiseWorkout();
                        Iterator<String> iter1 = obj.keys();

                        while (iter1.hasNext()) {
                            String key1 = iter1.next();
                            JSONObject obj1 = obj.getJSONObject(key1);

                            if (key1.equals("1")) {
                                Workoutdata dayoneData = parseJsondata(obj1);
                                dayWiseWorkout.setDayone(dayoneData);
                            } else {
                                Workoutdata daytwoData = parseJsondata(obj1);
                                dayWiseWorkout.setDaytwo(daytwoData);
                            }

                        }
                        list.add(dayWiseWorkout);
                        System.out.println(">>..." + obj.toString());

                    }
                    TrainingLogsActivity.getinstance().handleData(list, data);

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }*/

    private static Workoutdata parseJsondata(JSONObject obj) throws JSONException {
        Workoutdata data = new Workoutdata();
        data.setRunnerFeel(obj.getString("runner_feel"));
        data.setId(obj.getString("id"));
        data.setLogName(obj.getString("log_name"));
        data.setPace(obj.getString("pace"));
        data.setDistance(obj.getString("distance"));
        data.setEffort(obj.getString("effort"));
        data.setEveryday(obj.getString("everyday"));
        data.setDistanceType(obj.getString("distance_type"));
        data.setTimeF(obj.getString("time_f"));
        data.setDistanceDisplay(obj.getString("distance_display"));
        data.setCoachNoteFlag(obj.getString("coach_note_flag"));
        data.setDistanceTypeDisplay(obj.getString("distance_type_display"));
        data.setDisplay_image_icon(obj.getInt("display_image_icon"));
        return data;
    }


    public static void goalDetaiEdit(Activity act,String id, String event_type, String notes_before, String lng, String shot) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.goalDetails(id, event_type, notes_before, lng, shot).enqueue(new Callback<GoalDetailEdit>() {
            @Override
            public void onResponse(Call<GoalDetailEdit> call, Response<GoalDetailEdit> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        GoalDetailActivity.getinstance().editSubmit(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<GoalDetailEdit> call, Throwable t) {

            }
        });

    }

    public static void coachNotes(Activity act,String id) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.coachNotes(id).enqueue(new Callback<CoachNotesResponse>() {
            @Override
            public void onResponse(Call<CoachNotesResponse> call, Response<CoachNotesResponse> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        CoachNotesActivity.getinstance().handleResult(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<CoachNotesResponse> call, Throwable t) {

            }
        });


    }


    public static void getImage(Activity act,String runnerid) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.getImage(runnerid).enqueue(new Callback<GetImageResult>() {
            @Override
            public void onResponse(Call<GetImageResult> call, Response<GetImageResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        PhotoActivity.getinstance().getImage(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<GetImageResult> call, Throwable t) {

            }
        });

    }

    public static void addPlannedAbsence(Activity act,String schoolid, String runnerid, String notes, String reason, String dates) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        Map<String, String> map = new HashMap<>();

        map.put("school_id", schoolid);
        map.put("runner_id", runnerid);
        map.put("notes", notes);
        map.put("reason", reason);
        map.put("dates", dates);

        webServiceConnection.holder.addPlaned(map, EmailCoach_Adapter.ids).enqueue(new Callback<AddAbsencesRecord>() {
            @Override
            public void onResponse(Call<AddAbsencesRecord> call, Response<AddAbsencesRecord> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        AddPlanAbsenceActivity.getinstance().addAbsencesItems(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }



            }

            @Override
            public void onFailure(Call<AddAbsencesRecord> call, Throwable t) {

            }
        });


    }

    public static void createTraininLog(Activity act,String runnerid, String workout, String logName, String minutes, String seconds, String distance, String distanceType, String effort, String runnerFeel, String notes, String logDate, ArrayList<String> selectedImageList, Context context) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.initwithoutHeader();
        RequestBody reqId = RequestBody.create(MediaType.parse("multipart/form-data"), runnerid);
        RequestBody reqLog = RequestBody.create(MediaType.parse("multipart/form-data"), workout);
        RequestBody reqMinutes = RequestBody.create(MediaType.parse("multipart/form-data"), logName);
        RequestBody reqSeconds = RequestBody.create(MediaType.parse("multipart/form-data"), minutes);
        RequestBody reqEffort = RequestBody.create(MediaType.parse("multipart/form-data"), seconds);
        RequestBody reqdistance = RequestBody.create(MediaType.parse("multipart/form-data"), distance);
        RequestBody reqDistancetYpe = RequestBody.create(MediaType.parse("multipart/form-data"), distanceType);
        RequestBody reqeffort = RequestBody.create(MediaType.parse("multipart/form-data"), effort);
        RequestBody reqrunnerfeel = RequestBody.create(MediaType.parse("multipart/form-data"), runnerFeel);
        RequestBody reqWorkout = RequestBody.create(MediaType.parse("multipart/form-data"), notes);
        RequestBody reqWorkout1 = RequestBody.create(MediaType.parse("multipart/form-data"), logDate);


        MultipartBody.Part part0 = null;
        MultipartBody.Part part1 = null;
        MultipartBody.Part part2 = null;

        String previousList = "";
        ArrayList<String> uploadFileList = new ArrayList<>();
        for (String filePath : selectedImageList) {
            if (filePath.contains("http")) {
                if (previousList.isEmpty()) {
                    previousList = filePath.replace("https://xcstats.com/log_images/", "");
                } else {
                    previousList += "," + filePath.replace("https://xcstats.com/log_images/", "");
                }
            } else {
                uploadFileList.add(filePath);
            }
        }

//        RequestBody previousImages = RequestBody.create(MediaType.parse("multipart/form-data"), previousList);

        List<MultipartBody.Part> imageParts = new ArrayList<>();
        for (int a = 0; a < uploadFileList.size(); a++) {
            String filePath = uploadFileList.get(a);
            try {
                File file = FileUtils.getFileFromUri(context, Uri.parse(filePath));
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("images[" + a + "]", file.getName(), requestBody);
                imageParts.add(part);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
//        for (int a = 0; a < selectedImageList.size(); a++) {
//            String filePath = selectedImageList.get(a);
//            File file = new File(filePath);
//            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
//            if (a == 0) {
//                part0 = MultipartBody.Part.createFormData("images" + "[" + a + "]", file.getName(), requestBody);
//            } else if (a == 1) {
//                part1 = MultipartBody.Part.createFormData("images" + "[" + a + "]", file.getName(), requestBody);
//
//            } else if (a == 2) {
//                part2 = MultipartBody.Part.createFormData("images" + "[" + a + "]", file.getName(), requestBody);
//            }
//
//
//        }

        webServiceConnection.holder.trainglogCreate(reqId, reqLog, reqMinutes, reqSeconds, reqEffort, reqdistance, reqDistancetYpe, reqeffort, reqrunnerfeel, reqWorkout, reqWorkout1, imageParts).enqueue(new Callback<TrainingLogCreate>() {


            @Override
            public void onResponse(Call<TrainingLogCreate> call, Response<TrainingLogCreate> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        TrainingLogEntryActivity.getinstance().createTrainingLog(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<TrainingLogCreate> call, Throwable t) {

            }
        });

    }

    public static void editTrainingLog(Activity act,String id) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.trainingEdit(id).enqueue(new Callback<TrainingEditResult>() {
            @Override
            public void onResponse(Call<TrainingEditResult> call, Response<TrainingEditResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        TrainingLogEntryActivity.getinstance().setUi(response.body().getResults());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<TrainingEditResult> call, Throwable t) {
                System.out.println("Failurrrr");

            }
        });


    }


    public static void deleteTrainingLogs(Activity act,String id) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.traingDelete(id).enqueue(new Callback<TrainingDeleteResult>() {
            @Override
            public void onResponse(Call<TrainingDeleteResult> call, Response<TrainingDeleteResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        TrainingLogEntryActivity.getinstance().deleteUpdates(response.body());

                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<TrainingDeleteResult> call, Throwable t) {

            }
        });

    }

    public static String trainingLogSubmit(Activity act,String id, String logName, String minutes, String seconds, String effort, String runnerFeel, String notes, String distance, String distanceType, String workout, ArrayList<String> selectedImageList, Context context) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.initwithoutHeader();
        RequestBody reqId = RequestBody.create(MediaType.parse("multipart/form-data"), id);
        RequestBody reqLog = RequestBody.create(MediaType.parse("multipart/form-data"), logName);
        RequestBody reqMinutes = RequestBody.create(MediaType.parse("multipart/form-data"), minutes);
        RequestBody reqSeconds = RequestBody.create(MediaType.parse("multipart/form-data"), seconds);
        RequestBody reqEffort = RequestBody.create(MediaType.parse("multipart/form-data"), effort);
        RequestBody reqRunnerFeel = RequestBody.create(MediaType.parse("multipart/form-data"), runnerFeel);
        RequestBody reqNotes = RequestBody.create(MediaType.parse("multipart/form-data"), notes);
        RequestBody reqDistance = RequestBody.create(MediaType.parse("multipart/form-data"), distance);
        RequestBody reqDistanceType = RequestBody.create(MediaType.parse("multipart/form-data"), distanceType);
        RequestBody reqWorkout = RequestBody.create(MediaType.parse("multipart/form-data"), workout);
        MultipartBody.Part part0 = null;
        MultipartBody.Part part1 = null;
        MultipartBody.Part part2 = null;

        String previousList = "";
        ArrayList<String> uploadFileList = new ArrayList<>();
        for (String filePath : selectedImageList) {
            if (filePath.contains("http")) {
                if (previousList.isEmpty()) {
                    previousList = filePath.replace("https://xcstats.com/log_images/", "");
                } else {
                    previousList += "," + filePath.replace("https://xcstats.com/log_images/", "");
                }
            } else {
                uploadFileList.add(filePath);
            }
        }

        RequestBody previousImages = RequestBody.create(MediaType.parse("multipart/form-data"), previousList);

        List<MultipartBody.Part> imageParts = new ArrayList<>();
        for (int a = 0; a < uploadFileList.size(); a++) {
            String filePath = uploadFileList.get(a);
            try {
                File file = FileUtils.getFileFromUri(context, Uri.parse(filePath));
                RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("images[" + a + "]", file.getName(), requestBody);
                imageParts.add(part);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

       /* String previousList = "";
        ArrayList<String> uploadFileList = new ArrayList<>();
        Log.e("tag","imgeeeeee..."+selectedImageList.size());
        for (int a = 0; a < selectedImageList.size(); a++) {
            Log.e("tag","forlinnerrr..."+selectedImageList.size());
            String filePath = selectedImageList.get(a);
            if (filePath.contains("http")) {
                Log.e("tag","rrrrffffffff......."+selectedImageList.size());
                if (previousList.isEmpty())
                    previousList = filePath.replace("https://xcstats.com/log_images/", "");
                else {
                    previousList = "," + filePath.replace("https://xcstats.com/log_images/", "");
                }
            } else {
                Log.e("tag","last......."+selectedImageList.size());
                uploadFileList.add(filePath);
            }
        }

        RequestBody previousImages = RequestBody.create(MediaType.parse("multipart/form-data"), previousList);

        for (int a = 0; a < uploadFileList.size(); a++) {
            String filePath = selectedImageList.get(a);
            File file = new File(filePath);
            Log.e("tag","last......."+selectedImageList.size());
            RequestBody requestBody = RequestBody.create(MediaType.parse("image/*"), file);
            if (a == 0) {
                part0 = MultipartBody.Part.createFormData("images" + "[" + a + "]", file.getName(), requestBody);

            } else if (a == 1) {
                part1 = MultipartBody.Part.createFormData("images" + "[" + a + "]", file.getName(), requestBody);

            } else if (a == 2) {
                part2 = MultipartBody.Part.createFormData("images" + "[" + a + "]", file.getName(), requestBody);

            }
            Log.e("tag","lastbilkull......."+uploadFileList.size());
        }*/


        webServiceConnection.holder.traingSubmit(reqId, reqLog, reqMinutes, reqSeconds, reqEffort, reqRunnerFeel, reqNotes, reqDistance, reqDistanceType, reqWorkout, previousImages, imageParts).enqueue(new Callback<TrainingSubmitResult>() {
            @Override
            public void onResponse(Call<TrainingSubmitResult> call, Response<TrainingSubmitResult> response) {
                int statusCode = response.code();
                switch (statusCode) {
                    case 200:
                        // Success
                        TrainingLogEntryActivity.getinstance().trainingLogSubmit(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<TrainingSubmitResult> call, Throwable t) {
                Log.d("gg", t.getLocalizedMessage());

            }
        });

        return previousList;
    }

    public static void paceResult(Activity act,String expectedDistance, String minutes, String seconds) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.paceResult(expectedDistance, minutes, seconds).enqueue(new Callback<PaceResult>() {
            @Override
            public void onResponse(Call<PaceResult> call, Response<PaceResult> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        CrossDetailActivity.getinstance().paceresults(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }



            }

            @Override
            public void onFailure(Call<PaceResult> call, Throwable t) {

            }
        });

    }

    public static void AddTrainingLog(Activity act,String schoolid, String logDate) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.addLog(schoolid, logDate).enqueue(new Callback<AddLogResult>() {
            @Override
            public void onResponse(Call<AddLogResult> call, Response<AddLogResult> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        TrainingLogEntryActivity.getinstance().addLogs(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<AddLogResult> call, Throwable t) {

            }
        });


    }

    public static void teamDoc(Activity act, String schoolid) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.teamdoc(schoolid).enqueue(new Callback<TeamdocProp>() {
            @Override
            public void onResponse(Call<TeamdocProp> call, Response<TeamdocProp> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        TeamDocumentActivity.instance().teamDoc(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }
            }

            @Override
            public void onFailure(Call<TeamdocProp> call, Throwable t) {
                TeamDocumentActivity.instance().teamDoc(null);

            }
        });


    }

    public static void getLeadResult(Activity act, String schoolid, String runnerId) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.getLeadsResult(schoolid, runnerId).enqueue(new Callback<LeadboardProp>() {
            @Override
            public void onResponse(Call<LeadboardProp> call, Response<LeadboardProp> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        LeadboardActivity.instance().getLeadResult(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<LeadboardProp> call, Throwable t) {
                LeadboardActivity.instance().getLeadResult(null);
            }
        });

    }

    public static void getLeadsResult(Activity act,String schoolid, String runnerid, String metric, String gender, String grade) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.getLeaderPropResult(schoolid, runnerid, metric, gender, grade).enqueue(new Callback<GetLeaderShapeProp>() {
            @Override
            public void onResponse(Call<GetLeaderShapeProp> call, Response<GetLeaderShapeProp> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        TrainingLogLeadBoard.instance().getLeadResult(response.body());
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<GetLeaderShapeProp> call, Throwable t) {
                TrainingLogLeadBoard.instance().getLeadResult(null);

            }
        });

    }


    public static void deleteHeartRateForm(Activity act,String hr_id) {

        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.deleteHeartRate(hr_id).enqueue(new Callback<DeleteHeartRate>() {
            @Override
            public void onResponse(Call<DeleteHeartRate> call, Response<DeleteHeartRate> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        if (response.body().getSuccess()) {
                            showHeartrateFormActivity.getinstance().deleteHeartRateInfo(response.body());
                        }
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<DeleteHeartRate> call, Throwable t) {
                Log.e("@@", "failure::" + t.toString());
            }
        });
    }


    public static void getCellProviders(Activity act) {
        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.initwithoutHeader();
        webServiceConnection.holder.getCellProviders().enqueue(new Callback<CellProviders>() {
            @Override
            public void onResponse(Call<CellProviders> call, Response<CellProviders> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        if (response.body().getSuccess()) {
                            ProfileActivity.getinstance().onCellProvidersSuccess(response.body());
                        }
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<CellProviders> call, Throwable t) {
                ProfileActivity.getinstance().onCellProvidersSuccess(null);
            }
        });

    }


    public static void getHeartRate(Activity act,String runnerid, String weekstartdate) {

        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.responseBodyHeartRate(runnerid, weekstartdate).enqueue(new Callback<ResponseHeartRate>() {
            @Override
            public void onResponse(Call<ResponseHeartRate> call, Response<ResponseHeartRate> response) {
                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        if (response.body().getSuccess()) {
                            TrainingLogsActivity.getinstance().getHeartRateInfo(response.body());
                        }
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }


            }

            @Override
            public void onFailure(Call<ResponseHeartRate> call, Throwable t) {
                Log.e("@@", "failure::" + t.toString());
            }
        });


    }


    public static void submitHeartRateForm(Activity act,String runnerid, String heartrate, String hr_id, String hr_date) {

        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.showHeartRateForm(runnerid, heartrate, hr_id, hr_date).enqueue(new Callback<ShowHeartRateForm>() {
            @Override
            public void onResponse(Call<ShowHeartRateForm> call, Response<ShowHeartRateForm> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
//                        if (response.body().getSuccess()) {
                            showHeartrateFormActivity.getinstance().getSubmitHeartRateFormInfo(response.body());
//                        }
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }

            }

            @Override
            public void onFailure(Call<ShowHeartRateForm> call, Throwable t) {
                Log.e("@@", "failure::" + t.toString());
            }
        });


    }


    public static void submitHeartRate(Activity act,String hr_id, String hr_date, String runnerid, String heartrate, String notes) {

        WebServiceConnection webServiceConnection = WebServiceConnection.getInstance();
        webServiceConnection.holder.addHeartRate(hr_id, hr_date, runnerid, heartrate, notes).enqueue(new Callback<AddHeartRate>() {
            @Override
            public void onResponse(Call<AddHeartRate> call, Response<AddHeartRate> response) {

                int statusCode = response.code();

                switch (statusCode) {
                    case 200:
                        // Success
                        if (response.body().getSuccess()) {
                            showHeartrateFormActivity.getinstance().getSubmitHeartRateInfo(response.body());
                            Log.e("@@submit", "submitbuttton");
                        } else {
                            showHeartrateFormActivity.getinstance().getSubmitHeartRateInfo(response.body());
                            dialogs.removedialog();
                        }
                        break;
                    case 401:
                    case 403:
                        // Unauthorized or Forbidden: Logout
                        logoutUser(act);
                        break;
                    case 498:
                        // Token expired or invalid: Refresh token
                        refreshAccess(act);
                        break;
                    default:
                        // Other errors
                        Toast.makeText(act, "Unexpected error: " + statusCode, Toast.LENGTH_SHORT).show();
                        break;
                }




            }

            @Override
            public void onFailure(Call<AddHeartRate> call, Throwable t) {
                Log.e("@@", "failure::" + t.toString());
            }
        });


    }


}


