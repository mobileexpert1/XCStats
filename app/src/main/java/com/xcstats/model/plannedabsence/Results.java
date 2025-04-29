package com.xcstats.model.plannedabsence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Results {

@SerializedName("day")
@Expose
private ArrayList<Day> day;

public ArrayList<Day> getDay() {
return day;
}

public void setDay(ArrayList<Day> day) {
this.day = day;
}

}