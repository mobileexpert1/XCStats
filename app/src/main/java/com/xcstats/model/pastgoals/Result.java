package com.xcstats.model.pastgoals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("goal_id")
@Expose
private String goalId;
@SerializedName("meet_date")
@Expose
private String meetDate;
@SerializedName("meet_name")
@Expose
private String meetName;
@SerializedName("name")
@Expose
private String name;
@SerializedName("goal_format")
@Expose
private String goalFormat;
@SerializedName("result")
@Expose
private String result;
@SerializedName("notes_before")
@Expose
private String notesBefore;
@SerializedName("notes_after")
@Expose
private String notesAfter;

public String getGoalId() {
return goalId;
}

public void setGoalId(String goalId) {
this.goalId = goalId;
}

public String getMeetDate() {
return meetDate;
}

public void setMeetDate(String meetDate) {
this.meetDate = meetDate;
}

public String getMeetName() {
return meetName;
}

public void setMeetName(String meetName) {
this.meetName = meetName;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getGoalFormat() {
return goalFormat;
}

public void setGoalFormat(String goalFormat) {
this.goalFormat = goalFormat;
}

public String getResult() {
return result;
}

public void setResult(String result) {
this.result = result;
}

public String getNotesBefore() {
return notesBefore;
}

public void setNotesBefore(String notesBefore) {
this.notesBefore = notesBefore;
}

    public String getNotesAfter(){
        return notesAfter;
    }
    public void setNotesAfter(String notesAfter){
        this.notesAfter=notesAfter;
    }

}

