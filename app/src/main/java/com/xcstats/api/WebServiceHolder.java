package com.xcstats.api;

import com.xcstats.model.GetLeader.GetLeaderShapeProp;
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
import com.xcstats.model.editparents.EditResult;
import com.xcstats.model.editprofile.EditProfileResult;
import com.xcstats.model.editprofilesubmit.SubmitResult;
import com.xcstats.model.edittrackfield.EditTrackField;
import com.xcstats.model.emailcoach.CoachResult;
import com.xcstats.model.forgotpassword.ForgotResult;
import com.xcstats.model.getimage.GetImageResult;
import com.xcstats.model.getparents.GetParentsResult;
import com.xcstats.model.getreason.ReasonsResult;
import com.xcstats.model.getschool.SchoolResult;
import com.xcstats.model.goaldetailedit.GoalDetailEdit;
import com.xcstats.model.goalmeet.SelectGoalMeet;
import com.xcstats.model.heartRateForm.ShowHeartRateForm;
import com.xcstats.model.leadboard.LeadboardProp;
import com.xcstats.model.login.LoginResponse;
import com.xcstats.model.pastgoals.Pastgoalresults;
import com.xcstats.model.profilephoto.ProfilePhotoProp;
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
import com.xcstats.model.viewtrackschdule.ViewTrack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by Mobile on 12/24/2016.
 */

public interface WebServiceHolder {

    @FormUrlEncoded
    @POST("user/createAcct")
    Call<CreateAccount> createaccount(@Field("first_name") String firstname,
                                      @Field("last_name") String lastname,
                                      @Field("email") String email,
                                      @Field("cell") String cell,
                                      @Field("cell_provider_id") String cell_provider_id,
                                      @Field("grad_year") String grad_year,
                                      @Field("gender") String gender,
                                      @Field("school_id") String school_id,
                                      @Field("password") String password,
                                      @Field("device_token") String devicetoken);

    @FormUrlEncoded
    @POST("user/login")
    Call<ResponseBody> login(@Field("email") String email,
                             @Field("password") String password,
                             @Field("device_token") String device_token);


    @GET("user/getSchools")
    Call<SchoolResult> schoolresult();

    @GET("email/recipient")
    Call<CoachResult> coachresult(@Query("school_id") String schoolid);

    @GET("email/recipient")
    Call<CoachResult> coachresultNew(@Query("school_id") String schoolid,
                                     @Query("coach_id") String coach_id,
                                     @Query("subject_text") String subject_text);


    @GET("email/recipient")
    Call<CoachResult> coachresultNewReply(@Query("school_id") String schoolid,
                                          @Query("coach_id") String coach_id,
                                          @Query("id") String id);


    @FormUrlEncoded
    @POST("parents/getParents")
    Call<GetParentsResult> parentresult(@Field("runner_id") String runnerid);

    @FormUrlEncoded
    @POST("parents/getParent")
    Call<EditResult> editresult(@Field("id") String id,
                                @Field("runner_id") String runner_id);

    @GET("absenceRequest/showAbsence")
    Call<AbsencePlannedResult> absenceresults(@Query("runner_id") String runneridd);

    @FormUrlEncoded
    @POST("user/getUser")
    Call<EditProfileResult> editprofile(@Field("runner_id") String runerid);

    @FormUrlEncoded
    @POST("user/editUser")
    Call<SubmitResult> submitResult(@Field("runner_id") String runnerid,
                                    @Field("email") String email,
                                    @Field("password") String password,
                                    @Field("street") String street,
                                    @Field("city") String city,
                                    @Field("state") String state,
                                    @Field("zip") String zip,
                                    @Field("home_phone") String home_phone,
                                    @Field("cell_phone") String cell_phone,
                                    @Field("cell_provider_id") String cell_provider_id);

    @GET("trainingLog/workout")
    Call<WorkoutResult> trainingWorkout(@Query("school_id") String schoolid);

    @GET("trackGoalSetting/showUpcomingGoals")
    Call<TrackFieldResult> trackResult(@Query("school_id") String schoolid,
                                       @Query("runner_id") String runnerid);

    @FormUrlEncoded
    @POST("parents/deleteParent")
    Call<DeleteparentsResult> deleteParents(@Field("id") String id);


    @GET("trackGoalSetting/showCurrent")
    Call<TrackPrResult> trackPr(@Query("school_id") String schoolid,
                                @Query("runner_id") String runnerid);

    @FormUrlEncoded
    @POST("parents/addEditParent")
    Call<AddParents> addresult(@Field("id") String id,
                               @Field("runner_id") String runnerid,
                               @Field("first_name") String firstname,
                               @Field("last_name") String lastname,
                               @Field("email") String email);

    @Multipart
    @POST("user/uploadImage")
    Call<UploadResult> uploadfile(@Part("runner_id") RequestBody runnerid,
                                  @Part MultipartBody.Part uploadfile);

    @GET("trackGoalSetting/showPastGoals")
    Call<Pastgoalresults> pastGoal(@Query("runner_id") String runnerid,
                                   @Query("school_id") String schoolid);

    @GET("trackGoalSetting/getMeets")
    Call<SelectGoalMeet> goalMeet(@Query("school_id") String schoolid);

    @GET("trackGoalSetting/getTFEvents")
    Call<ResponseBody> responsebody(@Query("school_id") String schoolid,
                                    @Query("runner_id") String runner_id,
                                    @Query("event_type") String eventtype);

    @FormUrlEncoded
    @POST("user/checkRegCode")
    Call<RegisterationCode> checkRegCode(@Field("school_id") String schoolid,
                                         @Field("reg_code") String regcode);


    @GET("trackGoalSetting/showTrackGoal")
    Call<EditTrackField> editTrackfield(@Query("id") String id);

    @FormUrlEncoded
    @POST("user/lostPassword")
    Call<ForgotResult> forgot(@Field("email") String email);


    @GET("user/displayFunctions")
    Call<DisplayButtonResult> displaybuttons(@Query("school_id") String schoolid,
                                             @Query("runner_id") String runner_id);


    @FormUrlEncoded
    @POST("goal/read")
    Call<CrossResult> crossResult(@Field("school_id") String schoolid,
                                  @Field("runner_id") String runner_id);


    @GET("absenceRequest/getReasons")
    Call<ReasonsResult> reasonResult();

    @FormUrlEncoded
    @POST("goal/getGoal")
    Call<CrossGoalDetailResult> crossDetail(@Field("school_id") String schoolid,
                                            @Field("runner_id") String runnerid,
                                            @Field("event_num") String event_num);


    @FormUrlEncoded
    @POST("goal/setUpdateGoal")
    Call<UpdateGoalResult> updateGoalss(@Field("school_id") String schoolid,
                                        @Field("runner_id") String runnerid,
                                        @Field("event_num") String event_num,
                                        @Field("MinutesField") String minutes,
                                        @Field("SecondsField") String secondsfield,
                                        @Field("NotesField") String notessfield,
                                        @Field("ExpectedDistance") String expecteddistance);

    @FormUrlEncoded
    @POST("goal/removeGoal")
    Call<GoalRemoveResult> goalRemove(@Field("school_id") String schoolid,
                                      @Field("runner_id") String runnerid,
                                      @Field("event_num") String eventnum);

    @GET("email/sendm")
    Call<SendEmail> sendEmail(@QueryMap Map<String, String> options,
                              @Query("coach_id[]") ArrayList<String> ids);

    @GET("absenceRequest/removeAbsence")
    Call<RemoveAbsence> removeAbsence(@Query("id") String id);


    @GET("trackGoalSetting/addTrackGoal")
    Call<SubmitGoalResult> submitGoal(@Query("school_id") String schoolid,
                                      @Query("runner_id") String runnerid,
                                      @Query("event_type") String eventtype,
                                      @Query("event_id") String eventid,
                                      @Query("meet_id") String meetId,
                                      @Query("notes_before") String notesBefore,
                                      @Query("long") String min,
                                      @Query("short") String sec);


    @GET("trackGoalSetting/deleteTrackGoal")
    Call<DeleteTrackResult> deleteTrack(@Query("id") String id);


    @GET("trackGoalSetting/addNote")
    Call<ReviewSubmitResult> reviewSubmit(@Query("id") String id,
                                          @Query("notes_after") String notes);

    @GET("trackGoalSetting/editTrackGoal")
    Call<GoalDetailEdit> goalDetails(@Query("id") String id,
                                     @Query("event_type") String eventtype,
                                     @Query("notes_before") String notes,
                                     @Query("long") String long1,
                                     @Query("short") String short1);


    @FormUrlEncoded
    @POST("trainingLog/read2")
    Call<ResponseBody> responseBody(@Field("runner_id") String runnerid,
                                    @Field("weekstartdate") String weekStart);

    @FormUrlEncoded
    @POST("user/getImage")
    Call<GetImageResult> getImage(@Field("runner_id") String runnerid);


    @Multipart
    @POST("user/uploadImage")
    Call<ProfilePhotoProp> updateProfile(@Part MultipartBody.Part part,
                                         @Part("runner_id") RequestBody runner_id);


    @GET("absenceRequest/insertAbsence")
    Call<AddAbsencesRecord> addPlaned(@QueryMap Map<String, String> options,
                                      @Query("coach_id[]") ArrayList<String> ids);

    @Multipart
    @POST("trainingLog/create")
    Call<TrainingLogCreate> trainglogCreate(@Part("runner_id") RequestBody runner_id,
                                            @Part("workout") RequestBody workout,
                                            @Part("logName") RequestBody logName,
                                            @Part("minutes") RequestBody minutes,
                                            @Part("seconds") RequestBody seconds,
                                            @Part("distance") RequestBody distance,
                                            @Part("distanceType") RequestBody distanceType,
                                            @Part("effort") RequestBody effort,
                                            @Part("runnerFeel") RequestBody runnerFeel,
                                            @Part("notes") RequestBody notes,
                                            @Part("logDate") RequestBody logDate,
                                            @Part List<MultipartBody.Part> images);

// @Multipart
//    @POST("trainingLog/create")
//    Call<TrainingLogCreate> trainglogCreate(@Part("runner_id") RequestBody runner_id,
//                                            @Part("workout") RequestBody workout,
//                                            @Part("logName") RequestBody logName,
//                                            @Part("minutes") RequestBody minutes,
//                                            @Part("seconds") RequestBody seconds,
//                                            @Part("distance") RequestBody distance,
//                                            @Part("distanceType") RequestBody distanceType,
//                                            @Part("effort") RequestBody effort,
//                                            @Part("runnerFeel") RequestBody runnerFeel,
//                                            @Part("notes") RequestBody notes,
//                                            @Part("logDate") RequestBody logDate,
//                                            @Part MultipartBody.Part fileUploader1,
//                                            @Part MultipartBody.Part fileUploader2,
//                                            @Part MultipartBody.Part fileUploader3);
//
    @FormUrlEncoded
    @POST("trainingLog/edit")
    Call<TrainingEditResult> trainingEdit(@Field("id") String id);


    @GET("trainingLog/showCoachNote")
    Call<CoachNotesResponse> coachNotes(@Query("id") String id);

    @FormUrlEncoded
    @POST("trainingLog/delete")
    Call<TrainingDeleteResult> traingDelete(@Field("id") String id);

    /*@Multipart
    @POST("trainingLog/update")
    Call<TrainingSubmitResult> traingSubmit(@Part("id") RequestBody id,
                                            @Part("logName") RequestBody logName,
                                            @Part("minutes") RequestBody minutes,
                                            @Part("seconds") RequestBody seconds,
                                            @Part("effort") RequestBody effort,
                                            @Part("runnerFeel") RequestBody runnerFeel,
                                            @Part("notes") RequestBody notes,
                                            @Part("distance") RequestBody distance,
                                            @Part("distanceType") RequestBody distanceType,
                                            @Part("workout") RequestBody workout,
                                            @Part("prv_images") RequestBody previousImages,
                                            @Part MultipartBody.Part fileUploader1,
                                            @Part MultipartBody.Part fileUploader2,
                                            @Part MultipartBody.Part fileUploader3);
*/
    @Multipart
    @POST("trainingLog/update")
    Call<TrainingSubmitResult> traingSubmit(@Part("id") RequestBody id,
                                            @Part("logName") RequestBody logName,
                                            @Part("minutes") RequestBody minutes,
                                            @Part("seconds") RequestBody seconds,
                                            @Part("effort") RequestBody effort,
                                            @Part("runnerFeel") RequestBody runnerFeel,
                                            @Part("notes") RequestBody notes,
                                            @Part("distance") RequestBody distance,
                                            @Part("distanceType") RequestBody distanceType,
                                            @Part("workout") RequestBody workout,
                                            @Part("prv_images") RequestBody previousImages,
                                            @Part List<MultipartBody.Part> images);
    /*
           @FormUrlEncoded
           @POST("trainingLog/update")
           Call<TrainingSubmitResult> traingSubmit(@Field("id") String id, @Field("logName") String logName, @Field("minutes") String minutes, @Field("seconds") String seconds, @Field("effort") String effort, @Field("runnerFeel") String runnerFeel, @Field("notes") String notes, @Field("distance") String distance, @Field("distanceType") String distancetype, @Field("workout") String workout, @Part("images") MultipartBody.Part selectedImageList);
    */
    @GET("goal/getPace")
    Call<PaceResult> paceResult(@Query("ExpectedDistance") String expecteddistance,
                                @Query("MinutesField") String minutes,
                                @Query("SecondsField") String seconds);

    @FormUrlEncoded
    @POST("trainingLog/add")
    Call<AddLogResult> addLog(@Field("school_id") String schoolid,
                              @Field("logDate") String logdate);


    @FormUrlEncoded
    @POST("files/getFiles")
    Call<TeamdocProp> teamdoc(@Field("school_id") String schoolid);

    @FormUrlEncoded
    @POST("leaderboard/getForm")
    Call<LeadboardProp> getLeadsResult(@Field("school_id") String school_id,
                                       @Field("runner_id") String runner_id);


    @FormUrlEncoded
    @POST("leaderboard/getLeaderboard")
    Call<GetLeaderShapeProp> getLeaderPropResult(@Field("school_id") String school_id,
                                                 @Field("runner_id") String runner_id,
                                                 @Field("metric") String metric,
                                                 @Field("gender") String gender,
                                                 @Field("grade") String grade);


    @POST("user/getCellProviders")
    Call<CellProviders> getCellProviders();

    @FormUrlEncoded
    @POST("messaging/getMessages")
    Call<AnnouncementsProp> getCoachesAnnouncements(@Field("school_id") String school_id,
                                                    @Field("runner_id") String runner_id);





    //Sukhwinder

    @FormUrlEncoded
    @POST("trainingLog/read3")
    Call<ResponseHeartRate> responseBodyHeartRate(@Field("runner_id") String runnerid,
                                                  @Field("weekstartdate") String weekStart);
    @FormUrlEncoded
    @POST("trainingLog/showHeartrateForm")
    Call<ShowHeartRateForm> showHeartRateForm(@Field("runner_id") String runnerid,
                                              @Field("heartrate") String heartrate,
                                              @Field("hr_id") String hr_id,
                                              @Field("hr_date") String hr_date);

    @FormUrlEncoded
    @POST("trainingLog/addHeartrate")
    Call<AddHeartRate> addHeartRate(@Field("hr_id") String hr_id,
                                    @Field("hr_date") String hr_date, @Field("runner_id") String runnerid,
                                    @Field("heartrate") String heartrate, @Field("notes") String notes);


    @FormUrlEncoded
    @POST("trainingLog/deleteHeartrate")
    Call<DeleteHeartRate> deleteHeartRate(@Field("hr_id") String hr_id);



  /*  @GET("trackGoalSetting/showSchedule")
    Call<ResponseBody> showSchedule (@Query("schoolid")String schoolid);

*/

    @GET("trackGoalSetting/showSchedule")
    Call<ResponseBody> showSchedule(@Query("school_id") String schoolid);

    @GET("token/createRefreshToken")
    Call<RefreshResult> createRefreshToken();



/*
    @GET("trackGoalSetting/showSchedule")
    Call<ResponseBody> responsebodyviewtrack (@Query("school_id")String school_id);


*/




}


