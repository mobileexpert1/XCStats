package com.xcstats.model.crossgoals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("meet_date")
@Expose
private String meetDate;
@SerializedName("display_date")
@Expose
private String displayDate;
@SerializedName("event")
@Expose
private String event;
@SerializedName("event_num")
@Expose
private String eventNum;
@SerializedName("course")
@Expose
private String course;
@SerializedName("goal_time")
@Expose
private Object goalTime;
@SerializedName("time_i")
@Expose
private Object timeI;
@SerializedName("display_goal")
@Expose
private Object displayGoal;
@SerializedName("display_time")
@Expose
private Object displayTime;
@SerializedName("display_pace")
@Expose
private Object displayPace;
@SerializedName("editable")
@Expose
private Integer editable;

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

public String getEventNum() {
return eventNum;
}

public void setEventNum(String eventNum) {
this.eventNum = eventNum;
}

public String getCourse() {
return course;
}

public void setCourse(String course) {
this.course = course;
}

public Object getGoalTime() {
return goalTime;
}

public void setGoalTime(Object goalTime) {
this.goalTime = goalTime;
}

public Object getTimeI() {
return timeI;
}

public void setTimeI(Object timeI) {
this.timeI = timeI;
}

public Object getDisplayGoal() {
return displayGoal;
}

public void setDisplayGoal(Object displayGoal) {
this.displayGoal = displayGoal;
}

public Object getDisplayTime() {
return displayTime;
}

public void setDisplayTime(Object displayTime) {
this.displayTime = displayTime;
}

public Object getDisplayPace() {
return displayPace;
}

public void setDisplayPace(Object displayPace) {
this.displayPace = displayPace;
}

public Integer getEditable() {
return editable;
}

public void setEditable(Integer editable) {
this.editable = editable;
}

}