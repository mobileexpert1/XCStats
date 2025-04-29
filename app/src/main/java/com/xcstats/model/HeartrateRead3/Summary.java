package com.xcstats.model.HeartrateRead3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Summary {

@SerializedName("weekly_distance")
@Expose
private Double weeklyDistance;
@SerializedName("weekly_time")
@Expose
private String weeklyTime;

public Double getWeeklyDistance() {
return weeklyDistance;
}

public void setWeeklyDistance(Double weeklyDistance) {
this.weeklyDistance = weeklyDistance;
}

public String getWeeklyTime() {
return weeklyTime;
}

public void setWeeklyTime(String weeklyTime) {
this.weeklyTime = weeklyTime;
}

}