package com.xcstats.model.edittrackfield;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("event")
@Expose
private String event;
@SerializedName("notes_before")
@Expose
private String notesBefore;
@SerializedName("meet_name")
@Expose
private String meetName;
@SerializedName("event_type")
@Expose
private String eventType;
@SerializedName("id")
@Expose
private String id;
@SerializedName("long_value")
@Expose
private Integer longValue;
@SerializedName("long_label")
@Expose
private String longLabel;
@SerializedName("short_value")
@Expose
private String shortValue;
@SerializedName("short_label")
@Expose
private String shortLabel;

public String getEvent() {
return event;
}

public void setEvent(String event) {
this.event = event;
}

public String getNotesBefore() {
return notesBefore;
}

public void setNotesBefore(String notesBefore) {
this.notesBefore = notesBefore;
}

public String getMeetName() {
return meetName;
}

public void setMeetName(String meetName) {
this.meetName = meetName;
}

public String getEventType() {
return eventType;
}

public void setEventType(String eventType) {
this.eventType = eventType;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public Integer getLongValue() {
return longValue;
}

public void setLongValue(Integer longValue) {
this.longValue = longValue;
}

public String getLongLabel() {
return longLabel;
}

public void setLongLabel(String longLabel) {
this.longLabel = longLabel;
}

public String getShortValue() {
return shortValue;
}

public void setShortValue(String shortValue) {
this.shortValue = shortValue;
}

public String getShortLabel() {
return shortLabel;
}

public void setShortLabel(String shortLabel) {
this.shortLabel = shortLabel;
}

}