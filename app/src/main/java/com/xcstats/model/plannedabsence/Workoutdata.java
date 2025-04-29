package com.xcstats.model.plannedabsence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Workoutdata {

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
@SerializedName("everyday")
@Expose
private String everyday;
@SerializedName("distance_display")
@Expose
private String distanceDisplay;
@SerializedName("time_f")
@Expose
private String timeF;
@SerializedName("pace")
@Expose
private String pace;
@SerializedName("distance_type_display")
@Expose
private String distanceTypeDisplay;

    @SerializedName("weekly_distance")
    @Expose
    private float weekly_distance;


    @SerializedName("weekly_time")
    @Expose
    private String weekly_time;

    @SerializedName("coach_note_flag")
    @Expose
    private String coachNoteFlag;

    @SerializedName("display_image_icon")
    @Expose
    private int display_image_icon;

    public int getDisplay_image_icon() {
        return display_image_icon;
    }

    public void setDisplay_image_icon(int display_image_icon) {
        this.display_image_icon = display_image_icon;
    }

    public float getWeekly_distance() {
        return weekly_distance;
    }

    public void setWeekly_distance(int weekly_distance) {
        this.weekly_distance = weekly_distance;
    }

    public String getWeekly_time() {
        return weekly_time;
    }

    public void setWeekly_time(String weekly_time) {
        this.weekly_time = weekly_time;
    }




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

public String getEveryday() {
return everyday;
}

public void setEveryday(String everyday) {
this.everyday = everyday;
}

public String getDistanceDisplay() {
return distanceDisplay;
}

public void setDistanceDisplay(String distanceDisplay) {

    this.distanceDisplay = distanceDisplay;
}

    public String getCoachNoteFlag() {
        return coachNoteFlag;
    }

    public void setCoachNoteFlag(String coachNoteFlag) {
        this.coachNoteFlag = coachNoteFlag;
    }



public String getPace() {
return pace;
}

public void setPace(String pace) {
this.pace = pace;
}

public String getDistanceTypeDisplay() {
return distanceTypeDisplay;
}

public void setDistanceTypeDisplay(String distanceTypeDisplay) {
this.distanceTypeDisplay = distanceTypeDisplay;
}


    public String getTimeF() {
        return timeF;
    }

    public void setTimeF(String timeF) {
        this.timeF = timeF;
    }



}