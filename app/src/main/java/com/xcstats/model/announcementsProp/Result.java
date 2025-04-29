package com.xcstats.model.announcementsProp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

    @SerializedName("post_day")
    @Expose
    private String postDay;
    @SerializedName("coach_name")
    @Expose
    private String coachName;
    @SerializedName("subject_url")
    @Expose
    private String subjectUrl;
    @SerializedName("subject_text")
    @Expose
    private String subjectText;
    @SerializedName("coach_id")
    @Expose
    private String coachId;
    @SerializedName("font_weight")
    @Expose
    private String fontWeight;


    public String getPostDay() {
        return postDay;
    }

    public void setPostDay(String postDay) {
        this.postDay = postDay;
    }

    public String getCoachName() {
        return coachName;
    }

    public void setCoachName(String coachName) {
        this.coachName = coachName;
    }

    public String getSubjectUrl() {
        return subjectUrl;
    }

    public void setSubjectUrl(String subjectUrl) {
        this.subjectUrl = subjectUrl;
    }

    public String getSubjectText() {
        return subjectText;
    }

    public void setSubjectText(String subjectText) {
        this.subjectText = subjectText;
    }

    public String getCoachId() {
        return coachId;
    }

    public void setCoachId(String coachId) {
        this.coachId = coachId;
    }

    public String getFontWeight() {
        return fontWeight;
    }

    public void setFontWeight(String fontWeight) {
        this.fontWeight = fontWeight;
    }


}