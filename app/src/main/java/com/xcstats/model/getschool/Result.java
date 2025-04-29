package com.xcstats.model.getschool;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("school_name")
@Expose
private String schoolName;
@SerializedName("school_id")
@Expose
private String schoolId;

public String getSchoolName() {
return schoolName;
}

public void setSchoolName(String schoolName) {
this.schoolName = schoolName;
}

public String getSchoolId() {
return schoolId;
}

public void setSchoolId(String schoolId) {
this.schoolId = schoolId;
}

}