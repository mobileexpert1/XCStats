package com.xcstats.model.trackpr;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {

@SerializedName("event")
@Expose
private String event;
@SerializedName("pr")
@Expose
private String pr;
@SerializedName("sr")
@Expose
private String sr;

public String getEvent() {
return event;
}

public void setEvent(String event) {
this.event = event;
}

public String getPr() {
return pr;
}

public void setPr(String pr) {
this.pr = pr;
}

public String getSr() {
return sr;
}

public void setSr(String sr) {
this.sr = sr;
}

}