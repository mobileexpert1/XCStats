package com.xcstats.model.trainglogedit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("log_name")
    @Expose
    private String logName;
    @SerializedName("effort")
    @Expose
    private String effort;
    @SerializedName("runner_feel")
    @Expose
    private String runnerFeel;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("distance")
    @Expose
    private String distance;
    @SerializedName("distance_type")
    @Expose
    private String distanceType;
    @SerializedName("notes")
    @Expose
    private String notes;
    @SerializedName("distance_display")
    @Expose
    private String distanceDisplay;
    @SerializedName("minutes")
    @Expose
    private Integer minutes;
    @SerializedName("seconds")
    @Expose
    private String seconds;

    private String distance_type_display;
    @SerializedName("distance_reqd")
    @Expose
    private String distanceReqd;
    @SerializedName("time_reqd")
    @Expose
    private String timeReqd;
    @SerializedName("notes_reqd")
    @Expose
    private String notesReqd;
    @SerializedName("logDate")
    @Expose
    private String logDate;


    @SerializedName("link_text")
    @Expose
    private String linkText;

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    @SerializedName("images")
    @Expose
    private String images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLogName() {
        return logName;
    }

    public void setLogName(String logName) {
        this.logName = logName;
    }

    public String getEffort() {
        return effort;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }

    public String getRunnerFeel() {
        return runnerFeel;
    }

    public void setRunnerFeel(String runnerFeel) {
        this.runnerFeel = runnerFeel;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getDistanceType() {
        return distanceType;
    }

    public void setDistanceType(String distanceType) {
        this.distanceType = distanceType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getDistanceDisplay() {
        return distanceDisplay;
    }

    public void setDistanceDisplay(String distanceDisplay) {
        this.distanceDisplay = distanceDisplay;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public String getSeconds() {
        return seconds;
    }

    public void setSeconds(String seconds) {
        this.seconds = seconds;
    }

    public String getDistanceTypeDisplay() {
        return distance_type_display;
    }

    public void setDistanceTypeDisplay(String distanceTypeDisplay) {
        this.distance_type_display = distanceTypeDisplay;
    }

    public String getDistanceReqd() {
        return distanceReqd;
    }

    public void setDistanceReqd(String distanceReqd) {
        this.distanceReqd = distanceReqd;
    }

    public String getTimeReqd() {
        return timeReqd;
    }

    public void setTimeReqd(String timeReqd) {
        this.timeReqd = timeReqd;
    }

    public String getNotesReqd() {
        return notesReqd;
    }

    public void setNotesReqd(String notesReqd) {
        this.notesReqd = notesReqd;
    }

    public String getLogDate() {
        return logDate;
    }

    public void setLogDate(String logDate) {
        this.logDate = logDate;
    }

    public String getLinkText() {
        return linkText;
    }

    public void setLinkText(String linkText) {
        this.linkText = linkText;
    }

}