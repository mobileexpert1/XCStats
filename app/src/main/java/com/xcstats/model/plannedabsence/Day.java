package com.xcstats.model.plannedabsence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Day {

@SerializedName("value")
@Expose
private ArrayList<DayWiseWorkout> value;

public ArrayList<DayWiseWorkout> getValue() {
return value;
}

public void setValue(ArrayList<DayWiseWorkout> value) {
this.value = value;
}
}