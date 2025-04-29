package com.xcstats.model.HeartrateRead3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Results {

@SerializedName("daily")
@Expose
private Daily daily;
@SerializedName("summary")
@Expose
private Summary summary;

public Daily getDaily() {
return daily;
}

public void setDaily(Daily daily) {
this.daily = daily;
}

public Summary getSummary() {
return summary;
}

public void setSummary(Summary summary) {
this.summary = summary;
}

}