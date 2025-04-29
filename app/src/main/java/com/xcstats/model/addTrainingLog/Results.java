package com.xcstats.model.addTrainingLog;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

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

}