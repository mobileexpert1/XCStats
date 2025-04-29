package com.xcstats.model.coachNotes;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("coach_note")
@Expose
private String coachNote;
@SerializedName("heading")
@Expose
private String heading;
@SerializedName("id")
@Expose
private String id;
@SerializedName("school_id")
@Expose
private String schoolId;
@SerializedName("coach_id")
@Expose
private String coachId;

public String getCoachNote() {
return coachNote;
}

public void setCoachNote(String coachNote) {
this.coachNote = coachNote;
}

public String getHeading() {
return heading;
}

public void setHeading(String heading) {
this.heading = heading;
}

public String getId() {
return id;
}

public void setId(String id) {
this.id = id;
}

public String getSchoolId() {
return schoolId;
}

public void setSchoolId(String schoolId) {
this.schoolId = schoolId;
}

public String getCoachId() {
return coachId;
}

public void setCoachId(String coachId) {
this.coachId = coachId;
}

}