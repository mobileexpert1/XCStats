package com.xcstats.model.viewtrackschdule;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class _17 {

@SerializedName("meet_date")
@Expose
private String meetDate;
@SerializedName("name")
@Expose
private String name;
@SerializedName("location")
@Expose
private String location;

public String getMeetDate() {
return meetDate;
}

public void setMeetDate(String meetDate) {
this.meetDate = meetDate;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getLocation() {
return location;
}

public void setLocation(String location) {
this.location = location;
}

}