package com.xcstats.model.crossgoaldetails;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("event_num")
    @Expose
    private String eventNum;
    @SerializedName("meet_date")
    @Expose
    private String meetDate;
    @SerializedName("display_date")
    @Expose
    private String displayDate;
    @SerializedName("event")
    @Expose
    private String event;
    @SerializedName("course")
    @Expose
    private String course;
    @SerializedName("personal_record")
    @Expose
    private String personalRecord;
    @SerializedName("ExpectedDistance")
    @Expose
    private List<ExpectedDistance> expectedDistance = null;
    @SerializedName("goal")
    @Expose
    private List<Goal> goal = null;

    public String getEventNum() {
        return eventNum;
    }

    public void setEventNum(String eventNum) {
        this.eventNum = eventNum;
    }

    public String getMeetDate() {
        return meetDate;
    }

    public void setMeetDate(String meetDate) {
        this.meetDate = meetDate;
    }

    public String getDisplayDate() {
        return displayDate;
    }

    public void setDisplayDate(String displayDate) {
        this.displayDate = displayDate;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getPersonalRecord() {
        return personalRecord;
    }

    public void setPersonalRecord(String personalRecord) {
        this.personalRecord = personalRecord;
    }

    public List<ExpectedDistance> getExpectedDistance() {
        return expectedDistance;
    }

    public void setExpectedDistance(List<ExpectedDistance> expectedDistance) {
        this.expectedDistance = expectedDistance;
    }

    public List<Goal> getGoal() {
        return goal;
    }

    public void setGoal(List<Goal> goal) {
        this.goal = goal;
    }

}