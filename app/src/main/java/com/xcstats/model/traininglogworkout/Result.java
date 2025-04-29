package com.xcstats.model.traininglogworkout;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Result {



@SerializedName("id")
@Expose
private Integer id;
@SerializedName("name")
@Expose
private String name;
@SerializedName("workout_id")
@Expose
private String workoutId;
@SerializedName("description")
@Expose
private String description;
@SerializedName("distance")
@Expose
private String distance;
@SerializedName("coacheID")
@Expose
private String coacheID;
@SerializedName("date_added")
@Expose
private String dateAdded;

    @SerializedName("distance_display")
    @Expose
    private String distancedisplay;



    public Integer getId() {
return id;
}

public void setId(Integer id) {
this.id = id;
}

public String getName() {
return name;
}

public void setName(String name) {
this.name = name;
}

public String getWorkoutId() {
return workoutId;
}

public void setWorkoutId(String workoutId) {
this.workoutId = workoutId;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getDistance() {
return distance;
}

public void setDistance(String distance) {
this.distance = distance;
}

    public String getDistanceDisplay(){
        return distancedisplay;
    }

    public void setDistanceDisplay(String distanceDisplay){
        this.distancedisplay=distanceDisplay;

    }

public String getCoacheID() {
return coacheID;
}

public void setCoacheID(String coacheID) {
this.coacheID = coacheID;
}

public String getDateAdded() {
return dateAdded;
}

public void setDateAdded(String dateAdded) {
this.dateAdded = dateAdded;
}

}