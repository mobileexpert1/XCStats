package com.xcstats.model.HeartrateRead3;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Daily {

@SerializedName("Mon")
@Expose
private Mon mon;
@SerializedName("Tue")
@Expose
private Tue tue;
@SerializedName("Wed")
@Expose
private Wed wed;
@SerializedName("Thu")
@Expose
private Thu thu;
@SerializedName("Fri")
@Expose
private Fri fri;
@SerializedName("Sat")
@Expose
private Sat sat;
@SerializedName("Sun")
@Expose
private Sun sun;

public Mon getMon() {
return mon;
}

public void setMon(Mon mon) {
this.mon = mon;
}

public Tue getTue() {
return tue;
}

public void setTue(Tue tue) {
this.tue = tue;
}

public Wed getWed() {
return wed;
}

public void setWed(Wed wed) {
this.wed = wed;
}

public Thu getThu() {
return thu;
}

public void setThu(Thu thu) {
this.thu = thu;
}

public Fri getFri() {
return fri;
}

public void setFri(Fri fri) {
this.fri = fri;
}

public Sat getSat() {
return sat;
}

public void setSat(Sat sat) {
this.sat = sat;
}

public Sun getSun() {
return sun;
}

public void setSun(Sun sun) {
this.sun = sun;
}

}