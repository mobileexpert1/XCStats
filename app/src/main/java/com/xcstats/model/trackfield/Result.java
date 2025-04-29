package com.xcstats.model.trackfield;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("short_date")
@Expose
private String shortDate;
@SerializedName("meet_name")
@Expose
private String meetName;
@SerializedName("event_name")
@Expose
private String eventName;
@SerializedName("goal")
@Expose
private String goal;
@SerializedName("notes_before")
@Expose
private String notesBefore;
@SerializedName("id")
@Expose
private String id;

public String getShortDate() {
return shortDate;
}

public void setShortDate(String shortDate) {
this.shortDate = shortDate;
}

public String getMeetName() {
return meetName;
}

public void setMeetName(String meetName) {
this.meetName = meetName;
}

public String getEventName() {
return eventName;
}

public void setEventName(String eventName) {
this.eventName = eventName;
}

public String getGoal() {
return goal;
}

public void setGoal(String goal) {
this.goal = goal;
}

public String getNotesBefore() {
return notesBefore;
}

public void setNotesBefore(String notesBefore) {
this.notesBefore = notesBefore;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

}